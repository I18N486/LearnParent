package com.zst.javabase.algorithm.sort;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};
        int[] arr = {1,3,5,2,4,6};
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println("\n------------------");
        for (int i : merge(arr,0,3,5)) {
            System.out.print(i+" ");
        }
    }

    public static int[] merge(int[] arr,int leftPtr,int rightPtr,int rightBound){
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr +1];
        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound ){
            temp[k++] = arr[i] <= arr[j] ? arr[i++]: arr[j++];
        }

        //最后剩下的部分，直接移入temp
        while (i <= mid){
            temp[k++] = arr[i++];
        }

        while (j <= rightPtr) {
            temp[k++] = arr[j++];
        }

        return temp;
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
