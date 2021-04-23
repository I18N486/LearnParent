package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description  找出旋转数组的最小值（旋转数组的定义：1,2,3,4,5  -> 3,4,5,1,2 或者 5,1,2,3,4 就属于旋转数组）
 * @Date 2021/3/31 18:03
 **/
public class FindMinFromCycle {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3};
        System.out.println("min:"+findMin(arr));
    }

    /**
     * 由于旋转数组的特性，遍历当出现arr[i]>arr[i+1]时，则说明走到了最小值，可以停止遍历
     * 时间复杂度最差O(n)，最好O(1)
     * @param arr
     * @return
     */
    private static int findMin(int[] arr){
        int min = -1;
        if (arr == null || arr.length == 0){
            return min;
        }
        min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                break;
            }
        }
        return min;
    }
}
