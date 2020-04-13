package com.e.myalgorithmes;


import android.os.Build;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import androidx.annotation.RequiresApi;

public class LeetCode {



/*    121. Best Time to Buy and Sell Stock
            Easy

3947

        180

    Add to List

            Share
    Say you have an array for which the ith element is the price of a given stock on day i.

    If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    Note that you cannot sell a stock before you buy one.

            Example 1:

    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Not 7-1 = 6, as selling price needs to be larger than buying price.
    Example 2:

    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.*/

    public int maxProfit(int[] prices) {
        // int max = 0;
        // for(int i=0; i<prices.length; i++){
        //     for(int j=i+1; j<prices.length; j++){
        //         int diff = -1 *(prices[i] - prices[j]);
        //         if(diff > max){
        //             max = diff;
        //         }
        //     }
        // }
        // return max;

        int max = 0;
        int min = Integer.MAX_VALUE;
        int prof = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) { // buy
                min = prices[i];
            } else if (prices[i] > max && prices[i] - min > prof) { // sell
                max = prices[i];
                prof = max - min;
            }
        }
        return prof;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxCoins(int[] nums) {
        HashMap<String, Integer> map = new HashMap<>();
        return maxCoinsHelper(nums, map);
    }

    public int maxCoinsHelper(int[] nums, HashMap<String, Integer> map) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 1;
        int right = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 != -1) {
                left = nums[i - 1];
            }
            if (i + 1 < nums.length) {
                right = nums[i + 1];
            }
            int mul = nums[i] * left * right;
            int res;
            int recur;
            if (resultFromMap(map, nums) != -1) {
                recur = resultFromMap(map, nums);
            } else {
                int[] lessNums = copyMinus(nums, i);
                recur = maxCoinsHelper(lessNums, map);
            }
            res = mul + recur;
            if (res > max) {
                max = res;
            }
            left = 1;
            right = 1;
        }
        resultToMap(map, nums, max);
        return max;
    }

    private int resultFromMap(HashMap<String, Integer> map, int[] nums) {
        String s = "";
        for (int i = 0; i < nums.length; i++) {
            s = s + nums[i] + "";
        }
        Integer res = map.get(s);
        if (res == null) {
            return -1;
        }
        return res;
    }

    private void resultToMap(HashMap<String, Integer> map, int[] nums, int value) {
        String s = "";
        for (int i = 0; i < nums.length; i++) {
            s = s + nums[i] + "";
        }
        map.put(s, value);
    }

    public int[] copyMinus(int[] nums, int i) {
        int[] arr = new int[nums.length - 1];
        for (int j = 0; j < nums.length; ) {
            if (j != i) {
                arr[j] = nums[j];
                j++;
            }
        }
        return arr;
    }
//////////////////////////////////////////////////////////////////////////////

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean b = true;
            for (int j = i + 1; j <= s.length() && b; j++) {
                String sub = s.substring(i, j);
                if (max2dis(sub)) {
                    if (sub.length() > max) {
                        max = sub.length();
                    } else {
                        b = false;
                    }
                }
            }
        }
        return max;
    }

    public boolean max2dis(String s) {

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i + 1);
            int index = arr.indexOf(sub);
            if (index == -1) {
                arr.add(sub);
                if (arr.size() > 2) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int moveI = 0;
        int moveJ = 0;

        while (i >= 0 && j >= 0) {
               /* i = moveIndex(S, i);
                j =  moveIndex(T, j);*/

            if (S.charAt(i) == '#') {
                moveI++;
                i--;
            } else if (T.charAt(j) == '#') {
                moveJ++;
                j--;
            } else if (moveI > 0) {
                moveI--;
                i--;
            } else if (moveJ > 0) {
                moveJ--;
                j--;
            } else if (S.charAt(i) != T.charAt(j)) {
                return false;
            } else {
                i--;
                j--;
            }

        }
        if (i == -1 && j == -1) { // both finished
            return true;
        }
        return true;
    }

    public int moveIndex(String s, int i) {
        int moveI = 0;
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                moveI++;
            }
            i--;
        }
        /*int moveI = 0;
        while(s.charAt(i) == '#'){
            i --;
            moveI ++;
        }
        for(int ii=0; ii<moveI; ii++){
            i --;
        }*/
        return i;
    }


    public int numUniqueEmails(String[] emails) {
        Map map = new HashMap();
        for (int i = 0; i < emails.length; i++) {
            String key = cleanMail(emails[i]);
            map.put(key, 1);
        }

        return map.size();
    }

    private String cleanMail(String s) {
        String ans = "";
        int i = 0;
        boolean plus = false;
        for (; s.charAt(i) != '@'; i++) {
            if (!plus) {
                if (s.charAt(i) == '+') {
                    plus = true;
                } else if (s.charAt(i) != '.') {
                    ans = ans + s.charAt(i);
                }
            }
        }

        while (i < s.length()) {
            ans = ans + s.charAt(i);
        }

        return ans;
    }

/////////////////////////////////////////////////////////////////////////////

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

/////////////////////////////////////////////////////////////////////////////

    public int countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> m = new HashMap();
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j1 = 0; j1 < grid[0].length - 1; j1++) {
                for (int j2 = j1 + 1; j2 < grid[0].length; j2++) {
                    if (grid[i][j1] == 1 && 1 == grid[i][j2]) {

                        int key = j1 * 300 + j2;
                        if (m.containsKey(key)) {
                            ans = ans + m.get(key);
                            m.put(key, m.get(key) + 1);
                        } else {
                            m.put(key, 1);
                        }

                    }
                }

            }
        }
        return ans;
    }

    /////////////////////////////////////////////////////////////////////////////
    public boolean PredictTheWinner(int[] nums) { // player 1 can win ?
        int i = 0;
        int j = nums.length - 1;
        int[] scores = new int[2]; // 0 for player1, 1 for player 2
        return p1(nums, i, j, scores);
    }

    private boolean p1(int[] nums, int i, int j, int[] scores) {
        if (i == j) {
            if (scores[0] + nums[i] >= scores[1]) { // player 1 won
                return true;
            } else {
                return false;
            }
        }
        int[] scores0 = copyArr(scores);
        int[] scores1 = copyArr(scores);
        scores0[0] = scores0[0] + nums[i];
        scores1[0] = scores1[0] + nums[j];
        return !p2(nums, i + 1, j, scores0) || !p2(nums, i, j - 1, scores1);
    }

    private boolean p2(int[] nums, int i, int j, int[] scores) {
        if (i == j) {
            if (scores[1] + nums[i] > scores[0]) { // player 2 won
                return true;
            } else {
                return false;
            }
        }
        int[] scores0 = copyArr(scores);
        int[] scores1 = copyArr(scores);
        scores0[1] = scores0[1] + nums[i];
        scores1[1] = scores1[1] + nums[j];

        return !p1(nums, i + 1, j, scores0) || !p1(nums, i, j - 1, scores1);
    }

    private int[] copyArr(int[] arr) {
        int[] ans = new int[2];
        ans[0] = arr[0];
        ans[1] = arr[1];

        return ans;
    }

    /////////////////////////////////////////////////////////////////////////////
    public int numSquares(int n) {
        Map<Integer, Integer> map = new HashMap();
        return numSquaresHelper(n, 0, n, map);
    }

    private int numSquaresHelper(int n, int c, int index, Map<Integer, Integer> map) {
        if (n < 0) {
            return Integer.MAX_VALUE;
        }
        if (index <= 0) {
            return Integer.MAX_VALUE;
        }
        if (n == 0) {
            return c;
        }
        Integer ans;
        Integer key;

        key = n * 1000000 + index * 100 + c;
        ans = map.get(key);

        if (ans != null) {
            return ans;
        }
        int take = Integer.MAX_VALUE;
        int i = index;

        double d = Math.sqrt(i);
        int notTake = numSquaresHelper(n, c, i - 1, map);

        if (d == (int) d) {
            take = numSquaresHelper(n - i, c + 1, i, map);
        }

        ans = Math.min(notTake, take);
        key = n * 1000000 + index * 100 + c;
        map.put(key, ans);
        return ans;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int repeatedStringMatch(String A, String B) {
        int i = 1;
        String sum = A;
        while (sum.length() - A.length() <= B.length()) {
            if (substringOf(B, sum)) {
                return i;
            }
            sum += A;
            i++;
        }
        return -1;
    }


    private boolean substringOf(String b, String a) { // b <= a
        if (b.length() > a.length()) {
            return false;
        }
        for (int i = 0; i + b.length() <= a.length(); i++) {
            String sub = a.substring(i, i + b.length());
            if (sub.equals(b)) {
                return true;
            }
        }
        return false;
    }


    /*1. Warmup
    - 1D version: Given a string S and two chars: c1 and c2 as input - find the minimal distance between the two chars. Example: s = “aabaaacabaaac”, c1=b, c2=c → return 2
                - 2D version: Given a matrix with one char in each cell, and input c1, c2, find minimal Manhattan distance between c1 and c2*/
    public int shortestPath2D(char[][] matrix, char c1, char c2) {

        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        Integer[][] m = new Integer[matrix.length][matrix[0].length];
        shortestPathHelper(matrix, 0, 0, c1, c2, 0, 0, min, m);
        return min[0];
    }

    private void shortestPathHelper(char[][] matrix, int i, int j, char c1, char c2, int count, int found, int[] min, Integer[][] m) {
        if (i == matrix.length || j == matrix[0].length) {
            return;
        }
        if (m[i][j] != null) {
            return;
        }
        m[i][j] = 1;

        if (matrix[i][j] != c1 && matrix[i][j] != c2) { // nothing found

        } else if (matrix[i][j] == c1) {
            if (found == 2) {
                min[0] = Math.min(min[0], count);
                count = 0;
                found = 1;
            } else { // found = 1
                count = 0;
            }
            found = 1;
        } else { // found c2
            if (found == 1) {
                min[0] = Math.min(min[0], count);
                count = 0;
                found = 2;
            } else { //found = 2
                count = 0;
            }
            found = 2;
        }

        shortestPathHelper(matrix, i + 1, j, c1, c2, count + 1, found, min, m);
        shortestPathHelper(matrix, i, j + 1, c1, c2, count + 1, found, min, m);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        ParenthesisHelper(n, res, 0, "");
        return res;
    }

    private void ParenthesisHelper(int n, List<String> res, int count, String s) {
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }
        if (count > 0) {
            ParenthesisHelper(n, res, count - 1, s + ")");
        }
        if (2 * n - s.length() >= count + 2) {
            ParenthesisHelper(n, res, count + 1, s + "(");
        }
    }


    public int jumpGame(int[][] arr) {
        return jumpHelper(arr, 0, 0);
    }

    private int jumpHelper(int[][] arr, int i, int j) {
        if (i >= arr.length || j >= arr[0].length || i < 0 || j < 0 || arr[i][j] == -1) {
            return 0;
        }
        int jump = arr[i][j];
        arr[i][j] = -1; // making it a hole
        int j0 = jumpHelper(arr, i + jump, j);
        int j1 = jumpHelper(arr, i, j + jump);
        int j2 = jumpHelper(arr, i - jump, j);
        int j3 = jumpHelper(arr, i, j - jump);
        return Math.max(j0, Math.max(j1, Math.max(j2, j3))) + 1;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static class AutocompleteSystem {

        // Map<String, Integer> map = new HashMap<>();
        String sentence = "";
        private ArrayList<Sentence> sentenceList;

        public AutocompleteSystem(String[] sentences, int[] times) {
            sentenceList = new ArrayList<>();
            for (int i = 0; i < sentences.length; i++) {
                sentenceList.add(new Sentence(sentences[i], times[i]));
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                sentence = "";
                return null;
            }
            sentence += c;
            ArrayList<String> ans = new ArrayList<>();
            Collections.sort(sentenceList);
            int count = 0;
            for (int i = 0; i < sentenceList.size() && count < 3; i++) {
                if (isSubstring(sentence, sentenceList.get(i).sentence)) {
                    ans.add(sentenceList.get(i).sentence);
                    count++;
                }
            }

            return ans;

        }

        private boolean isSubstring(String sub, String sentence) {
            if (sub.length() > sentence.length()) {
                return false;
            }
            for (int i = 0; i < sentence.length() - sub.length(); i++) {
                if (sub.equals(sentence.substring(i, i + sub.length()))) {
                    return true;
                }
            }
            return false;
        }

        private class Sentence implements Comparable<Sentence> {
            int time;
            String sentence;

            public Sentence(String sentence, int time) {
                this.sentence = sentence;
                this.time = time;
            }


            @Override
            public int compareTo(Sentence o) {
                if (this.time < o.time) {
                    return 1;
                }
                if (this.time > o.time) {
                    return -1;
                }
                return 0;
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int openLock(String[] deadends, String target) {
        Map<String, Integer> mapRes = new HashMap<>();
        Map<String, Integer> mapBeen = new HashMap<>();
        int min = openHelper(deadends, target, 0, 0, 0, 0, mapRes, mapBeen);
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }

    }

    private int openHelper(String[] deadends, String target, int a0, int a1, int a2, int a3, Map<String, Integer> mapRes, Map<String, Integer> mapBeen) {

        a0 = a0 % 10;
        a1 = a1 % 10;
        a2 = a2 % 10;
        a3 = a3 % 10;

        String key = a0 + "" + a1 + a2 + a3;
        if (key.equals(target)) {
            return 0;
        }

        Integer res = mapRes.get(key);
        if (res != null) {
            return res;
        }

        Integer been = mapBeen.get(key);
        if (been != null) {
            return Integer.MAX_VALUE;
        } else {
            mapBeen.put(key, -1);
        }

        if (contains(deadends, a0, a1, a2, a3)) {
            return Integer.MAX_VALUE;
        }

        int[] ret = new int[8];
        ret[0] = openHelper(deadends, target, a0 + 1, a1, a2, a3, mapRes, mapBeen);
        ret[1] = openHelper(deadends, target, a0 - 1, a1, a2, a3, mapRes, mapBeen);
        ret[2] = openHelper(deadends, target, a0, a1 + 1, a2, a3, mapRes, mapBeen);
        ret[3] = openHelper(deadends, target, a0, a1 - 1, a2, a3, mapRes, mapBeen);
        ret[4] = openHelper(deadends, target, a0, a1, a2 + 1, a3, mapRes, mapBeen);
        ret[5] = openHelper(deadends, target, a0, a1, a2 - 1, a3, mapRes, mapBeen);
        ret[6] = openHelper(deadends, target, a0, a1, a2, a3 + 1, mapRes, mapBeen);
        ret[7] = openHelper(deadends, target, a0, a1, a2, a3 - 1, mapRes, mapBeen);
        int min = minimum(ret);
        int ans = min + 1;
        mapRes.put(key, ans);
        return ans;
    }

    private int minimum(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    private boolean contains(String[] deadends, int a0, int a1, int a2, int a3) {
        String key = a0 + a1 + a2 + a3 + "";
        for (int i = 0; i < deadends.length; i++) {
            if (deadends[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    class Solution {
        public int maxDepth(Node root) {  // iterative
            if (root == null) {
                return 0;
            }
            Queue<Pair> q = new LinkedList();
            q.add(new Pair(root, 1));
            Integer depth = 1;
            //q.poll()
            while (!q.isEmpty()) {
                Pair p = q.poll();
                Node node = (Node) p.node;
                depth = (Integer) p.depth;
                if (node != null && node.children != null) {
                    for (int i = 0; i < node.children.size(); i++) {
                        q.add(new Pair(node.children.get(i), depth + 1));
                    }
                }
            }
            return depth;
        }

        class Pair {
            Node node;
            Integer depth;

            public Pair(Node node, Integer depth) {
                this.node = node;
                this.depth = depth;
            }
        }


/*        public int maxDepth1(Node root) {   // recursion
            if(root == null){
                return 0;
            }
            if(root.children == null || root.children.size() == 0){
                return 1;
            }
            int max = 0;
            for (int i = 0; i < root.children.size(); i++) {
                int depth = maxDepth1(root.children.get(i));
                max = Math.max(max, depth);
            }
            return max +1;
        }*/
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int orangesRotting(int[][] grid) {
        Queue queue = new LinkedList();

        int[][] dephs = new int[grid.length][grid[0].length];
        for (int i = 0; i < dephs.length; i++) {
            for (int j = 0; j < dephs[0].length; j++) {
                dephs[i][j] = -1;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) { // rotten
                    queue.add(new Pair(grid[i][j], 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = (Pair) queue.remove();

        }

        return -99;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution1 {
        TreeNode[] nodes;

        public TreeNode[] splitBST(TreeNode root, int V) {
            nodes = new TreeNode[2];
            if (root == null) {
                return nodes;
            }
            if (root.left == null && root.right == null) {
                nodes[0] = root;
                return nodes;
            }
            nodes[1] = root;
            splitHelper(root, V);
            return nodes;
        }

        public void splitHelper(TreeNode root, int V) {
            if (root == null) {
                return;
            }
            if (root.left != null && root.left.val == V) {
                TreeNode tmp = root.left;
                root.left = root.left.right;
                tmp.right = null;
                nodes[0] = tmp;
            } else if (root.right != null && root.right.val == V) {
                TreeNode tmp = root.right;
                root.right = root.right.left;
                tmp.left = null;
                nodes[0] = tmp;
            }
            splitHelper(root.left, V);
            splitHelper(root.right, V);

        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String reorganizeString(String s) {

        int maxNum = 1;
        char maxChar = s.charAt(0);

        Map<Character, Integer> m = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer n = m.get(c);
            if (n != null) {
                m.put(c, n + 1);
                if (n + 1 > maxNum) {
                    maxChar = c;
                    maxNum = Math.max(maxNum, n + 1);
                }
            } else {
                m.put(c, 1);
            }
        }

        if (maxNum > s.length() / 2 + s.length() % 2) {
            return "";
        }

        char[] chars = new char[s.length()];
        int point = 0;
        for (int i = 0; i < maxNum; i++) {
            chars[point] = maxChar;
            point = point + 2;
        }
        m.remove(maxChar);

        for (Map.Entry<Character, Integer> entry : m.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                if (point >= s.length()) {
                    point = 1;
                }
                chars[point] = key;
                point = point + 2;
            }


        }

        return String.valueOf(chars);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }
        if (pushed.length == 0) {
            return true;
        }
        Stack<Integer> s = new Stack<>();
        int push = 1;
        int pop = 0;
        s.push(pushed[0]);

        while (push < pushed.length) {
            if (s.empty() && s.peek() == popped[pop]) {
                s.pop();
                pop++;
            } else {
                s.push(pushed[push]);
                push++;
            }
        }

        while (pop < popped.length) {
            if (s.peek() == popped[pop]) {
                s.pop();
                pop++;
            } else {
                return false;
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] g = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            seen.clear();
            if (!g[edge[0]].isEmpty() && !g[edge[1]].isEmpty() &&
                    dfs(g, edge[0], edge[1])) {
                return edge;
            }
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        return null;
    }

    private boolean dfs(ArrayList<Integer>[] g, int e, int v) {

        if (!seen.contains(e)) {
            seen.add(e);
            if (e == v) {
                return true;
            }
            for (int i = 0; i < g[e].size(); i++) {
                if (dfs(g, g[e].get(i), v)) {
                    return true;
                }
            }
        }

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Map<Integer, Integer> m = new HashMap<>();

    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] g = new int[N+1][N+1];
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                g[i][j] = 0;
            }
        }

        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            g[u][v] = w;
        }

         dfs1(g, K, 0);

        if(m.size() < N){
            return -1;
        }

        Iterator it = m.entrySet().iterator();
        int max = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
             max = Math.max(max, (Integer) pair.getValue());
        }

        return max;
    }

    private void dfs1(int[][] g, int start, int w) {
        Integer myW = m.get(start);
        if(myW != null && myW <= w){
            return;
        }
        if(myW == null){
            myW = 0;
        }
        m.put(start, w);
        for (int i = 0; i < g[start].length; i++) {
            if(g[start][i] > 0){
                dfs1(g, i, w + g[start][i]);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Map<String, List<String[]>> m1;
    Map<String, List<String[]>> rm1;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        m1 = new HashMap<>();
        rm1 = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String u = equation.get(0);
            String v = equation.get(1);
            if(m1.get(u) == null){
                m1.put(u, new ArrayList<String[]>());
                rm1.put(v, new ArrayList<String[]>());
            }
            String[] tmp = new String[2];
            tmp[0] = v;
            tmp[1] = values[i] + "";
            m1.get(u).add(tmp);

            String[] rTmp = new String[2];
            rTmp[0] = u;
            rTmp[1] = 1 / values[i] + "";
            rm1.get(v).add(rTmp);
        }

        double[] answers = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);
            Map<String,Boolean> seen = new HashMap<>();
            answers[i] = dfs2(u, v, seen);
        }

        return answers;
    }

    private double dfs2(String u, String v, Map<String, Boolean> seen) {
        if(seen.get(u) != null){
            return 0;
        }
        seen.put(u, true);

        List<String[]> suns = m1.get(u);
        List<String[]> reversedSuns = rm1.get(u);
        if(suns == null && reversedSuns == null){
            return -1;
        }

        if(u.equals(v)){
            return 1;
        }

        double ans = -1;

        if(suns != null){
            for (int i = 0; i < suns.size(); i++) {
                String[] sun = suns.get(i);
                double res1 = Double.parseDouble(sun[1]) * dfs2(sun[0], v, seen);
                if(res1 > 0){
                    ans = res1;
                }
            }
        }


        if(reversedSuns != null){
            for (int i = 0; i < reversedSuns.size(); i++) {
                String[] sun = reversedSuns.get(i);
                double res1 = Double.parseDouble(sun[1]) * dfs2(sun[0], v, seen);
                if(res1 > 0){
                    ans = res1;
                }
            }
        }

        return ans;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    boolean[] visited;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        visited = new boolean[rooms.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        dfs3(rooms, 0);

        for (int i = 0; i < visited.length; i++) {
            if(visited[i] == false){
                return false;
            }
        }

        return true;
    }

    private void dfs3(List<List<Integer>> rooms, int start) {
        if(visited[start] == true){
            return;
        }
        visited[start] = true;

        List<Integer> keys = rooms.get(start);
        for (int i = 0; i < keys.size(); i++) {
            dfs3(rooms, keys.get(i));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });

        for (Integer num: nums) {
            heap.add(num);
            if(heap.size() > k){
                heap.poll();
            }
        }
        return heap.poll();
    }

    ////////////////////////////////////////////////////////////////////////////
    boolean isSequence(int[] arr, int dest){
        int l = 0;
        int r = 0;
        int sum = 0;


        while(r < arr.length - 1 || l < arr.length - 1){
            if(sum == dest){
                return true;
            }
            if(sum < dest){
                sum = sum + arr[r];
                r ++;
            } else { // larger
                sum = sum - arr[l];
                l ++;
            }
        }

        if(sum == dest){
            return true;
        }



        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////


}
