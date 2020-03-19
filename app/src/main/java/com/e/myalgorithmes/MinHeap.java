package com.e.myalgorithmes;

import androidx.annotation.NonNull;

public class MinHeap {
    final int INIT = 5;
    int lastIndex;
    int[] arr;

    public MinHeap() {
        arr = new int[INIT];
        lastIndex = 0;
    }

    public void insert(int num){
        if(lastIndex == arr.length - 1){
            doubleUpArr();
        }
        arr[lastIndex] = num;
        heapifyUp(lastIndex);
        lastIndex++;
    }

    public void delete(int i){
        arr[i] = Integer.MAX_VALUE;
        heapifyUp(i);
        swap(0, lastIndex);
        lastIndex --;
        heapifyDown(0);
    }

    private void heapifyDown(int i) {
        int index = i;
        //while ()
    }

    private void heapifyUp(int i) {
        int index = i;
        while(arr[parentOf(index)] > arr[index]){
            swap(parentOf(index), index);
            index = parentOf(index);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index*2 + 1;
    }

    private int rightChild(int index){
        return index*2 + 2;
    }

    private void doubleUpArr() {
        int[] temp = new int[2 * (lastIndex +1)];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public void remove (int num){

    }

    @NonNull
    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < lastIndex; i++) {
            ans += arr[i] + ", ";
        }
        return ans;
    }
}
