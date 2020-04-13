package com.e.myalgorithmes;

import androidx.annotation.NonNull;

public class MinHeap {
    final int INIT = 5;
    int size;
    int[] arr;

    public MinHeap() {
        arr = new int[INIT];
        size = 0;
    }

    public void insert(int num){
        if(size == arr.length - 1){
            doubleUpArr();
        }
        arr[size] = num;
        heapifyUp(size);
        size++;
    }

    public void delete(int i){
        arr[i] = Integer.MIN_VALUE;
        heapifyUp(i);
        //swap(0, lastIndex);
        arr[0] = arr[size-1];
        size--;
        heapifyDown(0);
    }

    private void heapifyDown(int i) {
        int index = i;
        boolean b = true;
        while (index <= size){
            if(leftChild(index) < size && arr[leftChild(index)] < arr[index]){
                swap(leftChild(index), index);
                index = leftChild(index);
            } else if(leftChild(index) < size && arr[rightChild(index)] < arr[index]){
                swap(rightChild(index), index);
                index = rightChild(index);
            } else {
                break;
            }
        }
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
        int[] temp = new int[2 * (size +1)];
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
        for (int i = 0; i < size; i++) {
            ans += arr[i] + ", ";
        }
        return ans;
    }
}
