package com.e.myalgorithmes;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    ArrayList<LinkedList<Integer>> connections;

    public Graph() {
        this.connections = new ArrayList<>();
    }

    public void addVertex(int vertex) {
        LinkedList<Integer> link = new LinkedList<Integer>();
        link.add(vertex);
        connections.add(link);
    }

    public void addEdge(int from, int to) {
        for (LinkedList<Integer> link : connections) {
            if (link.getFirst() == from) {
                link.add(to);
            }
        }
    }

    String BFS(int a) {
        String ans = "";
        MyQueue queue = new MyQueue<Integer>();
        int index;
        ArrayList<Boolean> mark = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            mark.add(false);
        }

        queue.enqueue(a);

        while (!queue.isEmpy()) {
            Integer num = (Integer) queue.dequeue();
            ans += num + ", ";
            index = indexOfVertex(num);
            LinkedList<Integer> link = connections.get(index);
            Object[] arr = link.toArray();
            for (int i = 1; i < arr.length; i++) { // get only conected vertexes without the original one
                index = indexOfVertex((Integer) arr[i]);
                if (!mark.get(index)) {
                    queue.enqueue(arr[i]);
                    mark.set(index, true);
                }
            }
        }

        return ans;
    }

    private int indexOfVertex(Integer num) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).getFirst() == num) {
                return i;
            }
        }
        return -1;
    }

    public String DFS(int a) {

        ArrayList<Boolean> mark = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            mark.add(false);
        }

        return DFSHelper(a, mark);
    }

    public String DFSHelper(int a, ArrayList<Boolean> mark) {

        String ans;
        int index = indexOfVertex(a);
        if(mark.get(index) == true){
            return "";
        }
        mark.set(index, true);
        ans = a + ",";
        LinkedList<Integer> link = connections.get(index);
        Object[] arr = link.toArray();
        for (int i = 0; i < arr.length; i++) {
            ans = ans +  DFSHelper((int)arr[i], mark);
        }

        return ans;
    }

    int BFS_ShortestPath(int from, int to){
        String ans = "";
        MyQueue queue = new MyQueue<Pair<Integer, Integer>>();
        int index;
        ArrayList<Boolean> mark = new ArrayList<>();
        int minPath = Integer.MAX_VALUE;

        for (int i = 0; i < connections.size(); i++) {
            mark.add(false);
        }

        queue.enqueue(new Pair<>(from, 0));

        while (! queue.isEmpy()){
            Pair p = (Pair) queue.dequeue();
            int node = (int) p.first;
            int path = (int) p.second;
            if(node == to){
                minPath = Math.min(minPath, path);
            } else {
                index = indexOfVertex(node);
                LinkedList<Integer> link = connections.get(index);
                Object[] arr = link.toArray();
                for (int i = 1; i < arr.length; i++) {
                    queue.enqueue(new Pair<>(arr[i], path + 1));
                }
            }
        }
        return minPath;
    }

    public class MyQueue<T> {
        ArrayList<T> lst = new ArrayList<>();

        public void enqueue(T a) {
            lst.add(a);
        }

        public boolean isEmpy() {
            return lst.isEmpty();
        }

        public T dequeue() {
            T arg = lst.get(0);
            lst = new ArrayList<T>(lst.subList(1, lst.size()));

            return arg;
        }
    }
}
