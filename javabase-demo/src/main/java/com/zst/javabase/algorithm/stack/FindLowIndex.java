package com.zst.javabase.algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * todo 再做一遍，理解单调栈
 * 找出数组元素左右两边离他最近的小于它的元素的下标,未找到返回-1,限制数组元素不重复
 * ex：{3,4,1,5}
 * 返回二维数组为：{{-1,2},{0,2},{-1,-1},{2,-1}}
 */
public class FindLowIndex {

    public static void main(String[] args) {
        int[] arr = {3,4,1,5,6,2,7};
        int[][] baoli = baoli(arr);
        for (int[] ints : baoli) {
            System.out.print("{"+ints[0]+","+ints[1]+"} ");
        }
        System.out.println("\n----------------");
        int[][] nearLess = getNearLessNoRepeat(arr);
        for (int[] less : nearLess) {
            System.out.print("{"+less[0]+","+less[1]+"} ");
        }
        System.out.println("\n----------------");
        int[] arr2 = {3,1,3,4,3,5,3,2,2};
        int[][] less = getNearLess(arr2);
        for (int[] near : less) {
            System.out.print("{"+near[0]+","+near[1]+"} ");
        }

    }

    /**
     * 暴力解法，每次都是向前、向后查找，找到最近最小值下标
     * 时间复杂度O（n^2）
     * @param arr
     * @return
     */
    public static int[][] baoli(int[] arr){
        int[][] lowIndex = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            int[] curIndex = {-1,-1};
            for (int j = i-1; j >= 0 ; j--) {
                if (arr[j] < arr[i]) {
                    curIndex[0] = j;
                    break;
                }
            }
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    curIndex[1] = j;
                    break;
                }
            }
            lowIndex[i] = curIndex;
        }
        return lowIndex;
    }

    /**
     * 单调栈解决
     * @param arr
     * @return
     */
    public static int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1: stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * 数组元素允许重复，继续使用单调栈解决
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> popList = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer popIndex : popList) {
                    res[popIndex][0] = leftLessIndex;
                    res[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                stack.push(indexList);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> popList = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (Integer popIndex : popList) {
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }
}
