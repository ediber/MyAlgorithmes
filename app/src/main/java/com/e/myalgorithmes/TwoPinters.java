package com.e.myalgorithmes;

import java.util.ArrayList;
import java.util.List;

class TwoPinters {

   /* Find the intersection of two sorted arrays.
    OR in other words,
    Given 2 sorted arrays, find all the elements which occur in both the arrays.

            Example :

    Input :
    A : [1 2 3 3 4 5 6]
    B : [3 3 5]

    Output : [3 3 5]

    Input :
    A : [1 2 3 3 4 5 6]
    B : [3 5]

    Output : [3 5]*/

    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        ArrayList<Integer> ans = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < a.size() && p2 < b.size()) {
            if (a.get(p1) < b.get(p2)) {
                p1++;
            } else if (a.get(p1) > b.get(p2)) {
                p2++;
            } else {
                ans.add(a.get(p1));
                p1++;
                p2++;
            }
        }
        return ans;
    }
}
