package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/1 9:32
 **/
public class ReOrderArray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int[] arr1 = {1,2,3,4,6,5,7,8,9};
        reOrder(arr1,0);
        for (int i : arr1) {
            System.out.print(i+" ");
        }
    }

    /**
     * 重排数组，要求所有奇数在数组前段，偶数在后段，并且保持奇数和偶数的相对顺序不变
     * ex：{1,2,3,4,5,6}->{1,3,5,2,4,6}
     * @param arr
     * @param evenIndex
     */
    private static void reOrder(int[] arr,int evenIndex){
        if (evenIndex >= arr.length-1) return;
        if (arr == null || arr.length <= 1) return;
        boolean isFind = false;
        for (int i = evenIndex; i < arr.length; i++) {
            if (!isFind && arr[i]%2 == 0){
                //找到第一个偶数，记录下标
                evenIndex = i;
                isFind = true;
            }
            if (isFind && arr[i]%2 != 0){
                //找到偶数之后首个奇数，需要执行重排操作
                if (i - evenIndex == 1){
                    //偶数和奇数是相邻的两个，只需要互相更换即可
                    int temp = arr[i];
                    arr[i] = arr[evenIndex];
                    arr[evenIndex] = temp;
                } else if (i - evenIndex > 1){
                    //连续几个偶数之后有一个奇数，需要调换奇数，然后整体后移偶数
                    int temp = arr[i];
                    for (int j = i; j > evenIndex; j--) {
                        arr[j] = arr[j-1];
                    }
                    arr[evenIndex] = temp;
                }
                break;
            }
        }
        reOrder(arr,evenIndex+1);
    }

    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int evenIndex = -1;
        boolean isFind = false;
        for (int i = 0; i < nums.length; i++) {
            if (!isFind && nums[i] % 2 == 0){
                //找到第一个偶数
                isFind = true;
                evenIndex = i;
            }
            if (isFind && nums[i] % 2 != 0){
                //找到紧邻偶数之后的首个奇数，进行奇偶互换
                int temp = nums[evenIndex];
                nums[evenIndex] = nums[i];
                nums[i] = temp;
                //重置偶数下标，继续后续遍历
                evenIndex += 1;
            }
        }
        return nums;
    }
}
