package com.zst.javabase.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println("\n------------------");
        for (int i : shellWithHalf(arr)) {
            System.out.print(i+" ");
        }
    }

    public static int[] shellWithHalf(int[] arr){
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }
        return arr;
    }

    public static int[] shellWithKnuth(int[] arr){
        int h = 1;
        while (h<= arr.length/3){
            h = h*3+1;
        }
        for (int gap = h; gap > 0; gap = (gap-1)/3) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j - gap, j);
                    }
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
