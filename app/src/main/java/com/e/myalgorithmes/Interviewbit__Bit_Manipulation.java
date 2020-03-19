package com.e.myalgorithmes;

public class Interviewbit__Bit_Manipulation {


    /*Number of 1 Bits
    Asked in:
    Adobe
            Yahoo
    Write a function that takes an unsigned integer and returns the number of 1 bits it has.

            Example:

    The 32-bit integer 11 has binary representation

00000000000000000000000000001011
    so the function should return 3.

    Note that since Java does not have unsigned int, use long for Java*/
    public int numSetBits(long a) {
        int count = 0;
        int mod;
        while(a > 0){
            mod = (int) (a%2);
            if(mod == 1){
                count++;
            }
            a = a/2;
        }
        return count;
    }



    /*Reverse Bits
    Asked in:
    Nvidia
            HCL
    Amazon
    Reverse the bits of an 32 bit unsigned integer A.

    Input Format:

    First and only argument of input contains an integer A
    Output Format:

            return a single unsigned integer denoting minimum xor value
    Constraints:

            0 <= A < 2^32
    For Examples :

    Example Input 1:
    A = 0
    Example Output 1:
            0
    Explanation 1:
            00000000000000000000000000000000
            =>      00000000000000000000000000000000
    Example Input 2:
    A = 3
    Example Output 2:
            3221225472
    Explanation 2:
            00000000000000000000000000000011
            =>        11000000000000000000000000000000
    Since java does not have unsigned int, use long*/

    public long reverse(long a) {
        long ans = a^Long.MAX_VALUE;

        return ans;
    }









}
