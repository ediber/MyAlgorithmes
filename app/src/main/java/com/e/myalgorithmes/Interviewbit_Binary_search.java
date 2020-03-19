package com.e.myalgorithmes;

import java.util.ArrayList;
import java.util.List;

class Interviewbit_Binary_search {

/*    Given an integar A.

    Compute and return the square root of A.

    If A is not a perfect square, return floor(sqrt(A)).

    DO NOT USE SQRT FUNCTION FROM STANDARD LIBRARY



    Input Format

    The first and only argument given is the integer A.
    Output Format

    Return floor(sqrt(A))
    Constraints

1 <= A <= 10^9
    For Example

    Input 1:
    A = 11
    Output 1:
            3

    Input 2:
    A = 9
    Output 2:
            3*/

    public int sqrt(int a) {
        if (a == 2) {
            return 1;
        }

        if (a == 1) {
            return 1;
        }

        long left = 0;
        long right = a;
        while (left < right) {
            long mid = (right - left) / 2 + left;
            if (mid * mid == a || (mid + 1) * (mid + 1) > a && a > mid * mid) {
                return (int) mid;
            }
            if (mid * mid > a) { // go left
                right = mid;
            } else { // go right
                left = mid + 1;
            }
        }

        return 0;
    }


    /*    Given a matrix of integers A of size N x M and an integer B.

        Write an efficient algorithm that searches for integar B in matrix A.

        This matrix A has the following properties:

        Integers in each row are sorted from left to right.
        The first integer of each row is greater than or equal to the last integer of the previous row.
                Return 1 if B is present in A, else return 0.

        Note: Rows are numbered from top to bottom and columns are numbered from left to right.



                Input Format

        The first argument given is the integer matrix A.
        The second argument given is the integer B.
        Output Format

        Return 1 if B is present in A, else return 0.
        Constraints

    1 <= N, M <= 1000
                1 <= A[i][j], B <= 10^6
        For Example

        Input 1:
        A =
                [ [1,   3,  5,  7],
                [10, 11, 16, 20],
                [23, 30, 34, 50]  ]
        B = 3
        Output 1:
                1

        Input 2:
        A = [   [5, 17, 100, 111]
                [119, 120,  127,   131]    ]
        B = 3
        Output 2:
                0*/
    public int searchMatrix(ArrayList<ArrayList<Integer>> arr, int b) {
        int l = 0;
        int r = arr.size() * arr.get(0).size() - 1; // last
        int m;
        while (l <= r) {
            m = (r - l) / 2 + l;
            int val = getValSreialaize(arr, m);
            if (val == b) {
                return 1;
            }
            if (val > b) { // go left
                r = m - 1;
            } else { // go right
                l = m + 1;
            }
        }
        return 0;
    }

    private int getValSreialaize(ArrayList<ArrayList<Integer>> arr, int a) {
        int i = a / arr.get(0).size();
        int j = a % arr.get(0).size();
        return arr.get(i).get(j);
    }


    /*    Painter's Partition Problem
        Asked in:
        Google
                Codenation
        Given 2 integers A and B and an array of integars C of size N.

        Element C[i] represents length of ith board.

        You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of board.

        Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board.

                2 painters cannot share a board to paint. That is to say, a board
        cannot be painted partially by one painter, and partially by another.
        A painter will only paint contiguous boards. Which means a
        configuration where painter 1 paints board 1 and 3 but not 2 is
        invalid.
        Return the ans % 10000003



        Input Format

        The first argument given is the integer A.
        The second argument given is the integer B.
        The third argument given is the integer array C.
                Output Format

        Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.
        Constraints

    1 <=A <= 1000
                1 <= B <= 10^6
                1 <= C.size() <= 10^5
                1 <= C[i] <= 10^6
        For Example

        Input 1:
        A = 2
        B = 5
        C = [1, 10]
        Output 1:
                50
        Explanation 1:
        Possibility 1:- same painter paints both blocks, time taken = 55units
        Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
        There are no other distinct ways to paint boards.
                ans = 50%10000003

        Input 2:
        A = 10
        B = 1
        C = [1, 8, 11, 3]
        Output 2:
                11*/
    public int paint(int a, int b, ArrayList<Integer> arr) {
        return (int) paintHelper(a, b, arr, 0, 0);
    }

    private long paintHelper(int a, int b, ArrayList<Integer> arr, int i, long leftOver) {
        if (a == 0) {
            return Integer.MAX_VALUE;
        }

        leftOver = leftOver + arr.get(i);

        if (i == arr.size() - 1) {
            return (leftOver * b) % 10000003;
        }

        long samePainter = paintHelper(a, b, arr, i + 1, leftOver);
        long differentPainter = Math.max(leftOver * b, paintHelper(a - 1, b, arr, i + 1, 0));
        long ans = Math.min(samePainter, differentPainter);
        return (ans % 10000003);
    }


    /* Implement Power Function
     Asked in:
     Google
             LinkedIn
     Implement pow(x, n) % d.

     In other words, given x, n and d,

     find (xn % d)

     Note that remainders on division cannot be negative.
     In other words, make sure the answer you return is non negative.

             Input : x = 2, n = 3, d = 3
     Output : 2

             2^3 % 3 = 8 % 3 = 2.*/
    public int pow(int x, int n, int d) {  // wrong
        int pow = 1;
        int ans;
        x = Math.abs(x);
        for (int i = 0; i < n; i++) {
            pow = pow * x;
        }
        int div = pow / d;
        if (div > 0) {
            ans = pow - (pow / d) * d;
        } else {
            ans = pow;
        }
        return ans;
    }


/*    Search for a Range
    Asked in:
    Google
            Microsoft
    Given a sorted array of integers A(0 based index) of size N, find the starting and ending position of a given integar B in array A.

    Your algorithm’s runtime complexity must be in the order of O(log n).

    Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].



    Input Format

    The first argument given is the integer array A.
    The second argument given is the integer B.
    Output Format

    Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].
    Constraints

1 <= N <= 10^6
            1 <= A[i], B <= 10^9
    For Example

    Input 1:
    A = [5, 7, 7, 8, 8, 10]
    B = 8
    Output 1:
            [3, 4]
    Explanation 1:
    First occurence of 8 in A is at index 3
    Second occurence of 8 in A is at index 4
    ans = [3, 4]

    Input 2:
    A = [5, 17, 100, 111]
    B = 3
    Output 2:
            [-1, -1]*/

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> searchRange(final List<Integer> arr, int n) {
        ArrayList<Integer> ans;
        int l = 0;
        int r = arr.size()-1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) == n) {
                ans = searchMargins(arr, m);
                return ans;
            } else if (arr.get(m) < n) { // go right
                l = m + 1;
            } else { // go left
                r = m - 1;
            }
        }
        ans = new ArrayList<>();
        ans.add(-1);
        ans.add(-1);
        return ans;
    }

    private ArrayList<Integer> searchMargins(List<Integer> arr, int index) {
        ArrayList<Integer> ans = new ArrayList<>();
        int left = -1, right = - 1;
        int n = arr.get(index);

        int l = 0;
        int r = index;


        while (l <= r) {  // most left
            int m = l + (r - l) / 2;
            if (l == r) {
                left = l;
                break;
            } else if (arr.get(m) < n) { // go right
                l = m + 1;
            } else { // go left
                r = m;
            }
        }

        l = index;
        r = arr.size() - 1;
        while (l <= r) {  // most right
            int m = l + (r - l) / 2;
            if(l == arr.size()-1){
                right = l;
                break;
            }
            else if (arr.get(l) > n) {
                right = l-1;
                break;
            } else if (arr.get(m) > n) { // go left
                r = m;
            } else { // go right
                l = m + 1;
            }
        }

        ans.add(left);
        ans.add(right);
        return ans;
    }


}
