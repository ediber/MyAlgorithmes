package com.e.myalgorithmes;

import java.lang.reflect.Array;

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
        size ++;

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

    private void doubleUpArr() {
        int[] temp = new int[2 * (size+1)];
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
