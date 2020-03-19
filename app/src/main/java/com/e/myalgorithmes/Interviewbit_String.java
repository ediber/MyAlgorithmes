package com.e.myalgorithmes;

import java.util.ArrayList;

public class Interviewbit_String {

    /*    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

        Example:

                "A man, a plan, a canal: Panama" is a palindrome.

    "race a car" is not a palindrome.

        Return 0 / 1 ( 0 for false, 1 for true ) for this problem*/
    public int isPalindrome(String s) {
        char c1 = ' ';
        char c2 = ' ';
        int i = 0;
        int j = s.length() - 1;
        for (; i < s.length() && j >= 0 && i < j; ) {
            c1 = s.charAt(i);
            c2 = s.charAt(j);
            c1 = manipulateChar(c1);
            c2 = manipulateChar(c2);
            if (c1 == ':') {
                i++;
            } else if (c2 == ':') {
                j--;
            } else {
                if (c1 != c2) {
                    return 0;
                } else {
                    i++;
                    j--;
                }
            }
        }

        return 1;
    }

    private char manipulateChar(char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char) ((int) c + 32);

        } else if (c >= 48 && c <= 57 || c >= 64 && c <= 122) {

        } else {
            c = ':';
        }
        return c;
    }


    /*    You are given a string S, and you have to find all the amazing substrings of S.

        Amazing Substring is one that starts with a vowel (a, e, i, o, u, A, E, I, O, U).

        Input

        Only argument given is string S.
        Output

        Return a single integer X mod 10003, here X is number of Amazing Substrings in given string.
        Constraints

    1 <= length(S) <= 1e6
        S can have special characters
                Example

        Input
                ABEC

        Output
        6

        Explanation
        Amazing substrings of given string are :
                1. A
        2. AB
        3. ABE
        4. ABEC
        5. E
        6. EC
        here number of substrings are 6 and 6 % 10003 = 6.*/
/*    public int solve(String s) {
        return solveHelper(s, false, 0);

    }

    private int solveHelper(String s, boolean specialStart, int i) {
        int count = 0;
        if (s.length() == 0){
            return 0;
        }
        *//*if(specialStart){
            count = 1 + solveHelper()
        }*//*
        count = 1 + solveHelper(s, false, i+1) + solveHelper(s.substring(1), false, i+1);
        return count;
    }

    public ArrayList<String> allOrderedPermutations(String s){
        ArrayList<String> ans = new ArrayList<>();
        allOrderedPermutationsHelper(s, ans, 0);
        return ans;
    }

    private void allOrderedPermutationsHelper(String s, ArrayList<String> ans, int i) {
        if(s.equals("") || i == s.length()){
            return;
        }
        ans.add(s.substring(i,i+1));
        ans.add(s);
        allOrderedPermutationsHelper(s, ans, i+1);
        allOrderedPermutationsHelper(s.substring(i+1), ans, i);
    }*/




/*    Given the array of strings A,
    you need to find the longest string S which is the prefix of ALL the strings in the array.

    Longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix of both S1
    and S2.

    For Example, longest common prefix of "abcdefgh" and "abcefgh" is "abc".



    Input Format

    The only argument given is an array of strings A.
    Output Format

    Return longest common prefix of all strings in A.
            For Example

    Input 1:
    A = ["abcdefgh", "aefghijk", "abcefgh"]
    Output 1:
            "a"
    Explanation 1:
    Longest common prefix of all the strings is "a".

    Input 2:
    A = ["abab", "ab", "abcd"];
    Output 2:
            "ab"
    Explanation 2:
    Longest common prefix of all the strings is "ab".*/

    public String longestCommonPrefix(ArrayList<String> arr) {
        if (arr.size() == 0) {
            return "";
        }
        if (arr.size() == 1) {
            return arr.get(0);
        }
        String pref = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            pref = comonPrefix(pref, arr.get(i));
        }
        return pref;


    }

    private String comonPrefix(String s1, String s2) {
        String pref = "";
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            pref = pref + s1.charAt(i);
        }
        return pref;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
        Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
        You should pack your words in a greedy approach; that is, pack as many words as you can in each line.

        Pad extra spaces ‘ ‘ when necessary so that each line has exactly L characters.
        Extra spaces between words should be distributed as evenly as possible.
        If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
        For the last line of text, it should be left justified and no extra space is inserted between words.

        Your program should return a list of strings, where each string represents a single line.

        Example:

        words: ["This", "is", "an", "example", "of", "text", "justification."]

        L: 16.

        Return the formatted lines as:

                [
                "This    is    an",
                "example  of text",
                "justification.  "
                ]*/
    public ArrayList<String> fullJustify(ArrayList<String> arr, int m) {
        ArrayList<ArrayList<String>> d2;
        ArrayList<String> ans = new ArrayList<>();
        d2 = arrToRows(arr, m);
        for (int i = 0; i < d2.size() - 1; i++) {
            String row = rowWithSpaces(d2.get(i), m, false);
            ans.add(row);
        }
        String row = rowWithSpaces(d2.get(d2.size() - 1), m, true);
        ans.add(row);
        return ans;
    }

    private String rowWithSpaces(ArrayList<String> row, int m, boolean last) {
        int length = 0;
        String ans = "";

        for (String word : row) {
            length += word.length();
        }

        int spaces = m - length;
        int betweens = row.size() - 1;
        if (betweens > 0) {
            int mod = spaces % betweens;
            int div = spaces / betweens;

            if (last) {
                div = 0;
                mod = 0;
            }

            for (int i = 0; i < row.size() - 1; i++) {
                ans += row.get(i);
                for (int j = 0; j < div; j++) {
                    ans += " ";
                }
                if (mod > 0) {
                    ans += " ";
                    mod--;
                }
            }
            ans += row.get(row.size() - 1);
        } else {
            ans = row.get(0);
        }

        return ans;
    }

    private ArrayList<ArrayList<String>> arrToRows(ArrayList<String> arr, int m) {
        ArrayList<ArrayList<String>> d2 = new ArrayList<>();
        ArrayList<String> tmp;
        int count = 0;
        tmp = new ArrayList<>();

        for (int i = 0; i < arr.size(); ) {
            if (count + arr.get(i).length() <= m) {
                tmp.add(arr.get(i));
                count = count + arr.get(i).length() + 1;
                i++;
            } else {
                d2.add(tmp);
                tmp = new ArrayList<>();
                count = 0;

            }
        }
        if (tmp.size() > 0) {
            d2.add(tmp);
        }
        return d2;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

    public static int maxSumCol(int[][] a) // minimum
    {
        int[] minCol = new int[1];
        minCol[0] = 0;
        minSumColHelper(a, 0, minCol);
        return minCol[0];
    }

    private static int minSumColHelper(int[][] a, int j, int[] minCol) {
        if (j == a[0].length) { // out of matrix
            return Integer.MAX_VALUE;
        }

        int myCol = colSum(a, 0, j);
        int other = minSumColHelper(a, j + 1, minCol);
        if (myCol < other) {
            minCol[0] = j;
        }
        return Math.min(myCol, other);
    }

    private static int colSum(int[][] a, int i, int j) {
        if (i == a.length - 1) {
            return a[i][j];
        }
        return a[i][j] + colSum(a, i + 1, j);
    }


    /*
        Multiply Strings
        Asked in:
        Microsoft
                Google
        Given two numbers represented as strings, return multiplication of the numbers as a string.

                Note: The numbers can be arbitrarily large and are non-negative.
                Note2: Your answer should not have leading zeroes. For example, 00 is not a valid answer.
                For example,
        given strings "12", "10", your answer should be “120”.

        NOTE : DO NOT USE BIG INTEGER LIBRARIES ( WHICH ARE AVAILABLE IN JAVA / PYTHON ).
        We will retroactively disqualify such submissions and the submissions will incur penalties.*/
    public String multiply(String A, String B) {
        long a, b ;
        a = strToInt(A);
        b = strToInt(B);

        return a * b + "";
    }

    private long strToInt(String A) {
        long a = 0;
        for (int i = A.length()-1; i >= 0; i--) {
            int num = A.charAt(i) - '0';
            a = (int) (a * 10 + num);
        }
        return a;
    }





}
