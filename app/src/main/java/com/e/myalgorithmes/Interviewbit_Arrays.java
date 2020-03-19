package com.e.myalgorithmes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Interviewbit_Arrays {


    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        ArrayList ans = new ArrayList();

        int left = 1;
        int[] arr = new int[A.size() + 1];
        for (int i = A.size() - 1; i >= 0; i--) {
            int sum = left + A.get(i);
            if (sum >= 10) {
                sum = sum - 10;
                left = 1;
            } else {
                left = 0;
            }
            arr[i + 1] = sum;
        }

        arr[0] = left;

        int j = 0;
        for (; j < arr.length && arr[j] == 0; j++) {

        }

        for (; j < arr.length; j++) {
            ans.add(arr[j]);
        }
        return ans;
    }


    /*    Given an array of integers, A of length N, find out the maximum sum sub-array of non negative numbers from A.

        The sub-array should be contiguous i.e., a sub-array created by choosing the second and fourth element and skipping the third element is invalid.

        Maximum sub-array is defined in terms of the sum of the elements in the sub-array.

        Find and return the required subarray.

                NOTE:

                1. If there is a tie, then compare with segment's length and return segment which has maximum length.
                2. If there is still a tie, then return the segment with minimum starting index.


        Input Format:

        The first and the only argument of input contains an integer array A, of length N.
        Output Format:

        Return an array of integers, that is a subarray of A that satisfies the given conditions.
                Constraints:

                1 <= N <= 1e5
                1 <= A[i] <= 1e5
        Examples:

        Input 1:
        A = [1, 2, 5, -7, 2, 3]

        Output 1:
                [1, 2, 5]

        Explanation 1:
        The two sub-arrays are [1, 2, 5] [2, 3].
        The answer is [1, 2, 5] as its sum is larger than [2, 3].

        Input 2:
        A = [10, -1, 2, 3, -4, 100]

        Output 2:
                [100]

        Explanation 2:
        The three sub-arrays are [10], [2, 3], [100].
        The answer is [100] as its sum is larger than the other two.*/
    public ArrayList<Integer> maxset(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> arrs = new ArrayList<>();

        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) >= 0) {
                tmp.add(A.get(i));
            } else {
                if (tmp.size() > 0) {
                    arrs.add(tmp);
                }
                tmp = new ArrayList<>();
            }
        }
        if (tmp.size() > 0) {
            arrs.add(tmp);
        }

        int maxIndex = 0;
        for (int i = 0; i < arrs.size(); i++) {
            if (sum(arrs.get(i)) > sum(arrs.get(maxIndex))) {
                maxIndex = i;
            } else if (sum(arrs.get(i)) == sum(arrs.get(maxIndex))) {
                if (arrs.get(i).size() > arrs.get(maxIndex).size()) {
                    maxIndex = i;
                }
            }
        }
        if (arrs.size() > 0) {
            return arrs.get(maxIndex);
        } else {
            return new ArrayList<>();
        }
    }

    private long sum(ArrayList<Integer> arr) {
        long ans = 0;
        for (int num : arr) {
            ans = ans + num;
        }
        return ans;
    }


    /*  Given an integer array, find if an integer p exists in the array such that the number of integers greater than p in the array equals to p
      If such an integer is found return 1 else return -1.
         example:     “4 1 2 3 4” return 1*/
    public int solve(ArrayList<Integer> A) {
        int ans = -1;
        for (int i = 0; i < A.size() - 1; i++) {
            if (A.get(i) == A.size() - i - 1) {
                ans = 1;
            }
        }
        return ans;
    }


    /*    You are given an array A containing N integers. The special product of each ith integer in this array is defined as the product of the following:<ul>

        LeftSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (i>j). If multiple A[j]’s are present in multiple positions, the LeftSpecialValue is the maximum value of j.

                RightSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (j>i). If multiple A[j]s are present in multiple positions, the RightSpecialValue is the minimum value of j.

        Write a program to find the maximum special product of any integer in the array.

                Input: You will receive array of integers as argument to function.

        Return: Maximum special product of any integer in the array modulo 1000000007.

        Note: If j does not exist, the LeftSpecialValue and RightSpecialValue are considered to be 0.

        Constraints 1 <= N <= 10^5 1 <= A[i] <= 10^9*/
    public int maxSpecialProduct_O_N_sqr_2(ArrayList<Integer> a) {  // too slow
        int ans = Integer.MIN_VALUE;
        int left;
        int right;
        int product;

        for (int i = 0; i < a.size(); i++) {
            left = 0;
            right = 0;

            for (int j = 0; j < i; j++) {
                if (a.get(j) > a.get(i)) {
//                    left = a.get(j);
                    left = j;
                }
            }
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(j) > a.get(i)) {
//                    right = a.get(j);
                    right = j;
                    break;
                }
            }
            product = left * right;
            if (product > ans) {
                ans = product;
            }
        }

        return ans % 1000000007;
    }


    /*    Print concentric rectangular pattern in a 2d matrix.
        Let us show you some examples to clarify what we mean.

                Example 1:

        Input: A = 4.
        Output:

                4 4 4 4 4 4 4
                4 3 3 3 3 3 4
                4 3 2 2 2 3 4
                4 3 2 1 2 3 4
                4 3 2 2 2 3 4
                4 3 3 3 3 3 4
                4 4 4 4 4 4 4
        Example 2:

        Input: A = 3.
        Output:

                3 3 3 3 3
                3 2 2 2 3
                3 2 1 2 3
                3 2 2 2 3
                3 3 3 3 3
        The outermost rectangle is formed by A, then the next outermost is formed by A-1 and so on.

        You will be given A as an argument to the function you need to implement, and you need to return a 2D array.*/
    public ArrayList<ArrayList<Integer>> prettyPrint(int n) {
        int size = 1 + 2 * (n - 1);
        ArrayList<ArrayList<Integer>> m = new ArrayList<>();
        int middle = size / 2;

        for (int i = 0; i < size; i++) {
            m.add(new ArrayList<Integer>());
            for (int j = 0; j < size; j++) {
                int value = Math.max(Math.abs(i - middle), Math.abs(j - middle)) + 1;
                m.get(i).add(value);
            }
        }

        return m;
    }


    /*    Kth Smallest Element in the Array
        Find the kth smallest element in an unsorted array of non-negative integers.

        Definition of kth smallest element

        kth smallest element is the minimum possible n such that there are at least k elements in the array <= n.
        In other words, if the array A was sorted, then A[k - 1] ( k is 1 based, while the arrays are 0 based )
        NOTE
        You are not allowed to modify the array ( The array is read only ).
        Try to do it using constant extra space.

                Example:

        A : [2 1 4 3 2]
        k : 3

        answer : 2*/
// DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int kthsmallest(final List<Integer> arr, int k) {

        quickSort(arr, 0, arr.size()-1);
        return arr.get(k-1);

       /* int chosenIndex = -1;
        int tmpMin;
        int min = Integer.MIN_VALUE;
        int minIndex = -1;

        for (int i = 0; i < k; i++) {
            tmpMin = Integer.MAX_VALUE;
            for (int j = 0; j < arr.size(); j++) {
                if( arr.get(j) < tmpMin && (arr.get(j) > min || arr.get(j) == min && j > minIndex)){
                    tmpMin = arr.get(j);
                    chosenIndex = j;
                }
            }
            minIndex = chosenIndex;
            min = tmpMin;
        }


        return min;*/
    }

    private void quickSort(List<Integer> arr, int l, int r) {
        if(l >= r){
            return;
        }
        int pivot = arr.get(r);
        int j = l;
        for (int i = l; i < r; i++) {
            if(arr.get(i) <= pivot){
                swap(arr, i, j);
                j ++;
            }
        }
        swap(arr, r, j); // swap pivot

        quickSort(arr, l, j-1);
        quickSort(arr, j+1, r);
    }

    private void swap(List<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i ,arr.get(j));
        arr.set(j, t);
    }


}
