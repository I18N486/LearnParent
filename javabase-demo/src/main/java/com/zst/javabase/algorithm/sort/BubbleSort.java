package com.zst.javabase.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,6,2,9,8,4,1,5,7};
        for (int i : bubble(arr)) {
            System.out.print(i);
        }
    }

    public static int[] bubble(int[] arr){
        for (int i = arr.length-1; i > 0; i--){
            //加flag优化，最佳是O（n）
            int flag = 0;

            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j + 1);
                    flag = 1;
                }
            }

            //flag == 0说明遍历一次后完全没有交换元素，即数组本身就是有序的，可以直接返回
            if (flag == 0){
                return arr;
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

