package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/2 10:21
 **/
public class PrintArrWithCycle{
    public static void main(String[] args) {
        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] arr1 = {{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24}};
        printArr(arr1);
    }

    /**
     * 顺时针打印数组
     * ex：1  2  3  4
     *     5  6  7  8
     *     9  10 11 12
     *     13 14 15 16
     * 打印输出：1、2、3、4、8、12、16、15、14、13、9、5、6、7、11、10
     * @param arr
     */
    public static void printArr(int[][] arr){
        if (arr == null || arr.length == 0) return;
        int r1=0,r2=arr.length -1;
        int c1=0,c2= arr[0].length -1;
        while (r1<=r2 && c1<=c2){
            //从左至右
            for (int c = c1;c<=c2;c++){
                System.out.print(arr[r1][c] +" ");
            }
            //从上到下
            for (int r = r1+1;r<= r2;r++){
                System.out.print(arr[r][c2] +" ");
            }
            if (r1<r2 && c1<c2){
                //从右至左
                for (int c= c2-1;c>c1;c--){
                    System.out.print(arr[r2][c] +" ");
                }
                //从下到上
                for (int r = r2;r > r1; r--){
                    System.out.print(arr[r][c1] +" ");
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
    }
}
