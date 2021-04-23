package com.zst.javabase.algorithm.stack;

import java.util.LinkedList;

/**
 * todo 继续理解
 * 输出窗口最大值
 * 输入数组{4,3,5,4,3,3,6,7}，窗口大小3，则共有8-3+1=6个窗口情况；
 * 分别是{4,3,5}，最大值5；
 * {3,5,4} 最大值5；
 * {5,4,3} 最大值5；
 * {4,3,3} 最大值4；
 * {3,3,6} 最大值6；
 * {3,6,7} 最大值7；
 * 输出数组{5,5,5,4,6,7}
 */
public class GetWindowMax {
    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int[] windowMax = windowMax(arr, 3);
        for (int max : windowMax) {
            System.out.print(max);
        }
    }

    public static int[] windowMax(int[] arr,int size){
        if (arr == null || size < 1 || arr.length < size){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] window = new int[arr.length-size+1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i-size){
                qmax.pollFirst();
            }
            if (i>= size-1){
                window[index++] = arr[qmax.peekFirst()];
            }
        }
        return window;
    }
}
