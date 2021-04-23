package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description  二维数组，从左至右递增，从上到下递增，从数组中查找元素是否存在
 * @Date 2021/3/31 16:54
 **/
public class FindInSortArray {
    public static void main(String[] args) {
        int[][] data = {{1,3,5,7},{2,4,6,8},{7,9,11,13},{8,10,12,14}};
        System.out.println("13 is in ? "+ isFind(data,13,0,data.length-1));
        System.out.println("13 is in ? "+ isFindByErFen(data,13));
    }

    //从左下角开始查找，大于则在当前元素的右边，小于则当前元素的上边
    //时间复杂度O(m+n) 空间负责度O(1)
    private static boolean isFind(int[][] data, int x, int s1, int s2) {
        //边界值判断，停止递归
        if (s2 <0){
            return false;
        }
        if (s1 >= data[0].length){
            return false;
        }
        if (data[s2][s1] == x){
            return true;
        } else if (data[s2][s1] > x){
            return isFind(data,x,s1,--s2);
        } else {
            return isFind(data,x,++s1,s2);
        }
    }

    private static boolean isFindByErFen(int[][] arr,int target){
        if (arr == null || arr.length == 0) return false;
        int left = 0;
        int right = arr.length * arr[0].length - 1;
        int col = arr[0].length;
        while (left <= right){
            int mid = (left+right)/2;
            int value = arr[mid/col][mid%col];
            if (value == target){
                return true;
            } else if (value < target){
                left = mid+1;
            } else {
                right = mid -1;
            }
        }
        return false;
    }

}
