package com.zst.javabase.algorithm.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 求最大子矩阵
 * 给定一个矩阵，值只有0或者1，求其中全是1的最大子矩阵，输出该子矩阵元素数量
 * 特定解法：第一步逐行找高度数组；第二步找最近小于元素下标，第三步计算矩形面积（宽*高）
 */
public class FindMaxSubmatrix {
    public static void main(String[] args) {
        int[][] arrs = {{1,0,1,1},{1,1,1,1},{1,1,1,0},{0,1,1,1}};
        int[] height = heightArr(arrs,1);
        for (int i : height) {
            System.out.print(i+" ");
        }

        System.out.println("\n---------------------");
        int[][] nearLess = getNearLess(height);
        for (int[] less : nearLess) {
            System.out.print("{"+less[0]+","+less[1]+"} ");
        }

        System.out.println("\n---------------------");
        System.out.println("当前矩阵的最大子矩阵的元素个数是："+maxSubmatrix(arrs));

        int[][] arrs2 = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        System.out.println("当前矩阵的最大子矩阵的元素个数是："+maxSubmatrix(arrs2));
        int[][] arrs3 = {{1,0,1,1}};
        System.out.println("当前矩阵的最大子矩阵的元素个数是："+maxSubmatrix(arrs3));
        int[][] arrs4 = {{1,1,1,0,1},{1,1,0,1,1},{1,0,1,1,1},{0,1,1,1,1},{1,1,1,0,1}};
        /**
         * 1 1 1 0 1
         * 1 1 0 1 1
         * 1 0 1 1 1
         * 0 1 1 1 1
         * 1 1 1 0 1
         */
        System.out.println("当前矩阵的最大子矩阵的元素个数是："+maxSubmatrix(arrs4));
    }

    /**
     * 根据高度数组和下标，可以计算出最大矩形面积
     * 0,3,4,1
     * {{-1,-1},{0,3},{1,3},{0,-1}}
     * 柱形图计算矩形面积：高*宽
     * nearLess[i][1]<0 ? i - nearLess[i][0] -1 : nearLess[i][1] - nearLess[i][0] -1 得到等高的下标间距（矩形宽）
     * @param arrs
     * @return
     */
    public static int maxSubmatrix(int[][] arrs){
        int maxArea = 0;
        for (int row = 1; row <= arrs.length; row++) {
            //计算出以当前行为底的最大矩形面积
            int[] height = heightArr(arrs,row);
            int[][] nearLess = getNearLess(height);
            //遍历，计算最大值
            for (int i = 0; i < height.length; i++) {
                //首先计算出矩形宽
                int width = 0;
                //第一种情况：下标0 >=0,下标1为-1，说明右边没有比当前高小的，则宽就是height.length - 下标0 -1)
                if (nearLess[i][1] < 0 && nearLess[i][0] >=0){
                    width = height.length - nearLess[i][0] -1;
                }
                //第二种情况：下标0 =-1,下标1 >i,说明左边没有比当前高小的，则宽就是nearLess[i][1]
                else if (nearLess[i][0] <0 && nearLess[i][1] >i){
                    width = nearLess[i][1];
                }
                //第三种情况：下标0和下标1都是-1，说明左边和右边都没有比当前高小，则宽就是height.length
                else if (nearLess[i][0] <0 && nearLess[i][1] <0){
                    width = height.length;
                }
                //第四种情况:下标0和下标1都大于-1，说明左右两边都有确定的位置小于当前高，则宽就是nearLess[i][1] - nearLess[i][0] -1
                else {
                    width = nearLess[i][1] - nearLess[i][0] -1;
                }
                //保留最大面积 宽*高 height[i]*width
                maxArea = Math.max(maxArea,height[i]*width);
            }
        }
        return maxArea;
    }

    /**
     * 第一步：计算出高度数组
     *  1 0 1 1
     *  1 1 1 1
     *  1 1 1 0
     *  0 1 1 1
     * 高度数组：
     * 第1行: {1,0,1,1}
     * 第2行: {2,1,2,2}
     * 第3行: {3,2,3,0}
     * 第4行：{0,3,4,1}
     * @param arrs
     * @return
     */
    public static int[] heightArr(int[][] arrs,int curRow){
        if (curRow > arrs.length) throw new RuntimeException("当前行不可超出矩阵行数!");
        if (arrs.length == 0 || arrs[0].length == 0 || curRow <= 0) return new int[1];
        int[] height = new int[arrs[0].length];
        for (int i = 0; i < curRow; i++) {
            for (int j = 0; j < height.length; j++) {
                height[j] = arrs[i][j] == 0 ? 0:height[j]+1;
            }
        }
        return height;
    }

    /**
     * 找出最近小于的下标（单调栈）
     * @param height
     * @return
     */
    public static int[][] getNearLess(int[] height){
        int[][] less = new int[height.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] < height[stack.peek().get(0)]){
                List<Integer> popList = stack.pop();
                for (Integer popIndex : popList) {
                    less[popIndex][0] = stack.isEmpty() ? -1:stack.peek().get(stack.peek().size()-1);
                    less[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && height[stack.peek().get(0)] == height[i]){
                stack.peek().add(i);
            } else {
                List<Integer> indexList = new LinkedList<>();
                indexList.add(i);
                stack.push(indexList);
            }

        }
        while (!stack.isEmpty()){
            List<Integer> popList = stack.pop();
            for (Integer popIndex : popList) {
                less[popIndex][0] = stack.isEmpty() ? -1:stack.peek().get(stack.peek().size()-1);
                less[popIndex][1] = -1;
            }
        }
        return less;
    }
}
