package com.e.myalgorithmes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("algo", "test");
                AVL_Tree tree = new AVL_Tree();
                AVL_Tree.Node root = tree.insert(null, 5);
                root = tree.insert(root, 1);
                root = tree.insert(root, 3);
                root = tree.insert(root, 7);
                root = tree.insert(root, 9);
                root = tree.insert(root, 34);
                root = tree.insert(root, 12);
                root = tree.insert(root, 15);
                root = tree.insert(root, 11);
                root = tree.insert(root, 41);
                root = tree.insert(root, 42);
                root = tree.insert(root, 43);
                root = tree.insert(root, 44);
                root = tree.insert(root, 45);

                Log.d("algo", tree.toString(root));
            }
        });

        findViewById(R.id.heap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinHeap heap = new MinHeap();
                heap.insert(6);
                heap.insert(3);
                heap.insert(1);
                heap.insert(9);
                heap.insert(5);
                heap.insert(2);
                heap.insert(8);

                Log.d("algo", heap.toString());

                heap.delete(4);
                Log.d("algo", heap.toString());

            }
        });

        findViewById(R.id.Interviewbit_Arrays).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> arr = new ArrayList<>(
                        Arrays.asList(2, 1, 4, 3, 2));

                Interviewbit_Arrays a = new Interviewbit_Arrays();
                //        a.plusOne(arr);
                //        a.maxset(arr);
                //       a.prettyPrint(3);
                a.kthsmallest(arr, 3);
            }
        });

        findViewById(R.id.Interviewbit_Math).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interviewbit_Math m = new Interviewbit_Math();
                // m.titleToNumber("AA");
                // m.gcd(4, 6);
                //            m.isPalindrome(2147447412);

                ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2, 1));
                //    m.solve(arr, 2, 21);
            }
        });

        findViewById(R.id.hackerrank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.Interviewbit_String).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interviewbit_String s = new Interviewbit_String();
                //  s.isPalindrome(" A man, a plan, a canal: Panama");     //  "A man, a plan, a canal: Panama"
                // s.allOrderedPermutations("abe");
                ArrayList<String> arr = new ArrayList<>(
                        Arrays.asList("abcd", "abde", "abcf"));
                s.longestCommonPrefix(arr);

                ArrayList<String> arr2 = new ArrayList<>(
//                        Arrays.asList(  "This", "is", "an", "example", "of", "text", "justification."));
                        Arrays.asList(""));

                s.fullJustify(arr2, 10);

                s.multiply("99999", "99999");
            }
        });

        findViewById(R.id.Interviewbit_twoPointers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoPinters t = new TwoPinters();
            }
        });

        findViewById(R.id.graph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Graph g = new Graph();
                g.addVertex(1);
                g.addVertex(2);
                g.addVertex(3);
                g.addVertex(4);
                g.addVertex(5);
                g.addVertex(6);

                g.addEdge(1, 2);
                g.addEdge(1, 3);
                g.addEdge(2, 4);
                g.addEdge(2, 5);
                g.addEdge(3, 5);
                g.addEdge(4, 5);
                g.addEdge(4, 6);
                g.addEdge(5, 6);

                g.BFS(3);
                g.DFS(1);
                g.BFS_ShortestPath(1, 6);
            }
        });

        findViewById(R.id.Interviewbit_Binary_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interviewbit_Binary_search b = new Interviewbit_Binary_search();

                //      b.sqrt(8);

                ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
                arr.add(new ArrayList<Integer>());
                arr.get(0).add(1);
                // arr.add((ArrayList<Integer>) Arrays.asList(1));
                //            b.searchMatrix(arr, 1);

                ArrayList<Integer> arr2 = new ArrayList<>(
//                        Arrays.asList( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
                        Arrays.asList(1));
                //              int ans = b.paint( 1 ,1000000, arr2);
                b.searchRange(arr2, 1);

//                int[][] arr3 = {{-1, 3, 2}, {5, -3, -9}, {7, -4, 10}, {1, 2, -111}};
//                Interviewbit_String.maxSumCol(arr3);

                int a = 1;
            }
        });

        findViewById(R.id.leet_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeetCode l = new LeetCode();
                int[] arr2 = {1, 10, 14, 19};
                // l.maxProfit(arr2,2);
                // l.lengthOfLongestSubstringTwoDistinct("bacc");
                //l.backspaceCompare("xywrrmp", "xywrrmu#p");
                //    l.numSquares(5);
                //   l.repeatedStringMatch("abcd", "cdabcdab");

//                char[][] arr3 = {{'a', 'c', 'd', 'e'}, {'d', 'd', 'd', 'c'}, {'d', 'd', 'd', 'b'}} ;
//              l.shortestPath2D(arr3, 'a', 'b');

//                int[][] arr4 = {{1, 1, 2, 4}, {-1, -1, 1, 2}, {1, 4, 1, 3}};
//                l.jumpGame(arr4);

                //          new LeetCode.AutocompleteSystem();

//                String[] dead = {"5555"};
//                l.openLock(dead, "1000");

                l.reorganizeString("zhmyo");

/*                int[] arr5 = {1,2,3,4,5};
                int[] arr6 = {4,5,3,2,1};
                l.validateStackSequences(arr5, arr6);*/

                ArrayList<List<String>> equations = new ArrayList<>();
                equations.add(Arrays.asList("x1", "x2"));
                equations.add(Arrays.asList("x2", "x3"));
                equations.add(Arrays.asList("x1", "x4"));
                equations.add(Arrays.asList("x2", "x5"));

                double[] values = {3.0, 0.5, 3.4, 5.6};
                ArrayList<List<String>> queries = new ArrayList<>();

                queries.add(Arrays.asList("x2", "x4"));
                queries.add(Arrays.asList("x1", "x5"));
                queries.add(Arrays.asList("x1", "x3"));
                queries.add(Arrays.asList("x5", "x5"));
                queries.add(Arrays.asList("x5", "x1"));
                queries.add(Arrays.asList("x3", "x4"));
                queries.add(Arrays.asList("x4", "x3"));
                queries.add(Arrays.asList("x6", "x6"));
                queries.add(Arrays.asList("x0", "x0"));

                //              l.calcEquation(equations, values, queries);

//                int[] arr5 = {1,3,1,4,23};
//                l.isSequence(arr5, 8);

                LeetCode_Kotlin lk = new LeetCode_Kotlin();
//                int[] arr6 = {1,2,3,3,4,5};
//                lk.isPossible(arr6);

//                ArrayList<String> arr7 = new ArrayList<>(
//                        Arrays.asList("hello", "world"));

//                LeetCode_Kotlin.Codec c = new LeetCode_Kotlin.Codec();
//                String s = c.encode(arr7);

            }
        });
    }
}
