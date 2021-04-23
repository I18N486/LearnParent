package com.zst.javabase.algorithm.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3,6,2,9,8,4,1,5,7};
        for (int i : insert(arr)) {
            System.out.print(i);
        }
    }

    public static int[] insert(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr,j-1,j);
                } else {
                    //优化，当遇到不用替换时，说明前面的部分已经有序，可以停止本次循环
                    break;
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
