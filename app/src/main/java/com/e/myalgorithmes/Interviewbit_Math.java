package com.e.myalgorithmes;

import java.util.ArrayList;

public class Interviewbit_Math {


    /*    Given a column title as appears in an Excel sheet, return its corresponding column number.

        Example:

        A -> 1

        B -> 2

        C -> 3

                ...

        Z -> 26

        AA -> 27

        AB -> 28*/
    public int titleToNumber(String s) {  // Bse 26
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int n = (int) c;
            ans = (int) (ans + (n - 64) * Math.pow(26, s.length() - i - 1));
        }
        return ans;
    }


    /*    Given an even number ( greater than 2 ), return two prime numbers whose sum will be equal to given number.

        NOTE A solution will always exist. read Goldbach’s conjecture

        Example:


        Input : 4
        Output: 2 + 2 = 4

        If there are more than one solutions possible, return the lexicographically smaller solution.

        If [a, b] is one solution with a <= b,
        and [c,d] is another solution with c <= d, then

    [a, b] < [c, d]

        If a < c OR a==c AND b < d.*/
    public ArrayList<Integer> primesum(int a) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 2; i <= a / 2; i++) {
            if (isPrime(i) && isPrime(a - i)) {
                ans.add(i);
                ans.add(a - i);
                return ans;
            }
        }

        return ans;
    }

    private boolean isPrime(int a) {
        if (a < 2) {
            return false;
        }
        for (int i = 2; i <= a / 2; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }


    /*    Given 2 non negative integers m and n, find gcd(m, n)

        GCD of 2 integers m and n is defined as the greatest integer g such that g is a divisor of both m and n.
        Both m and n fit in a 32 bit signed integer.

                Example

        m : 6
        n : 9

        GCD(m, n) : 3*/
    public int gcd(int a, int b) {
        int a1, b1;
        if(a > b){
            a1 = a;
            b1 = b;
        } else {
            a1 = b;
            b1 = a;
        }
        while (b1 != 0){
            int t = b1;
            b1 = a1 % b1;
            a1 = t;
        }
        return a1;
    }

 /*   Determine whether an integer is a palindrome. Do this without extra space.

    A palindrome integer is an integer x for which reverse(x) = x where reverse(x) is x with its digit reversed.
    Negative numbers are not palindromic.

            Example :

    Input : 12121
    Output : True

    Input : 123
    Output : False*/
    public int isPalindrome(int a) {

        if(a < 0){
            return 0;
        }
        if(a < 10){
            return 1;
        }


        long a1 = a;
        long b = 0;
        long div = 1;
        while(div < a1){
            b = b + ((a1%(div*10)) - a1%div) / div;
            div = div * 10;
            b = b * 10;
        }
        b = b/10;
        if(a1==b){
            return 1;
        }
        return 0;
    }


/*    Given a set of digits (A) in sorted order, find how many numbers of length B are possible whose value is less than number C.

    NOTE: All numbers can only have digits from the given set.
    Examples:

    Input:
            0 1 5
            1
            2
    Output:
            2 (0 and 1 are possible)

    Input:
            0 1 2 5
            2
            21
    Output:
            5 (10, 11, 12, 15, 20 are possible)*/
    public int solve(ArrayList<Integer> arr, int b, int c) {
        int count = 0;
        int total = b;
        ArrayList<Integer> tmpArr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> d2 = new ArrayList<>();
        solveHelper(d2, arr, b, tmpArr, 0);

        for (ArrayList<Integer> tmp : d2) {
            int num = arrToNum(tmp);
            if(num < c){
                count++;
            }
        }
        return count;
    }

    private int arrToNum(ArrayList<Integer> arr) {
        int num = 0;
        for (int i = arr.size()-1; i >= 0 ; i--) {
            num = num*10 + arr.get(i);
        }

        return num;
    }

    private void solveHelper(ArrayList<ArrayList<Integer>> d2, ArrayList<Integer> arr, int b, ArrayList<Integer> tmpArr, int i) {
        if(b == 0){
            d2.add(tmpArr);
            return;
        }
        if(i == arr.size()){
            return;
        }
        ArrayList<Integer> copyFull = deepCopy(tmpArr);
        solveHelper(d2, arr, b, copyFull, i+1); // not taken
        tmpArr.add(arr.get(i));
        solveHelper(d2, arr, b-1, tmpArr, i+1); // taken
    }

    private ArrayList<Integer> deepCopy(ArrayList<Integer> arr) {
        ArrayList<Integer> copy = new ArrayList<>();
        for (Integer num : arr) {
            copy.add(num);
        }
        return copy;
    }


}
