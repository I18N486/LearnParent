package com.zst.javabase.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/3/30 17:18
 **/
public class PufaTest {
    public static void main(String[] args) {
        int[] arr = {1,0,3,5,3,0,8,0};
        int[] arr1 = {1,0,0,3,0,5,3,0,8,0};
        int[] arr2 = {1,3,5,0,0,8,0,0,3,0,0,0};
        //testReverseZero(arr2);
        testThrowDice(3);
        testMineThrowDice(3);

    }

    /**
     * 重排数组，将数组中所有零元素移动至数组末尾，并保持数组中非零元素的顺序不变
     * ex：{1,0,3,5,3,0,8,0}  -> {1,3,5,3,8,0,0,0}
     * 要求：不允许使用额外的数据结构，在本来的数组上修改
     */
    public static void testReverseZero(int[] arr){
        reverse(arr,-1,arr.length);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    /**
     * 解题步骤：主要分为两步：第一步顺序扫描数组，找到第一个零元素的下标，继续朝后查找，找到零元素之后的第一个非零元素下标。
     *          这里分为两种情况：1，没有找到零元素（isFind = false）；2，找到零元素，但是没有找到之后的非零元素（cur = last） 说明此时的数组已经符合要求，无需再操作，直接返回；
     *          经过第一步找到两个下标：zeroIndex，currentIndex；
     *          执行数组操作：1.前移元素（arr[zeroIndex++] = arr[currentIndex++]） 将非零元素之后的所有元素前移至zeroIndex；
     *                      2.末尾补零（arr[last-1 --] = 0）前移了多少，说明末尾需要补对应个0（currentIndex - zeroIndex）。
     *          递归：经过上述操作之后，再继续对zeroIndex至 last = last - （curIndex - zeroIndex）之间的数组进行操作 （此时zeroIndex之前都是顺序的非零元素，last之后都是补零）
     *          时间复杂度：O(n)
     *          base： zeroIndex >= lastIndex  无需再遍历，返回
     * @param arr
     * @param zeroIndex
     * @param lastIndex
     */
    private static void reverse(int[] arr,int zeroIndex,int lastIndex) {
        if (zeroIndex >= lastIndex) return;
        boolean isFind = false;
        int currentIndex;
        for (currentIndex = zeroIndex+1; currentIndex < lastIndex; currentIndex++) {
            //遍历找到第一个为零元素下标
            if (!isFind && arr[currentIndex] == 0){
                zeroIndex = currentIndex;
                isFind = true;
            }
            //找到为零元素之后的第一个非零元素
            if (isFind && arr[currentIndex] != 0){
                int k = zeroIndex;
                //前移非零元素
                for (int j = currentIndex; j < lastIndex; j++) {
                    arr[k++] = arr[j];
                }
                //末尾补零
                for (int j = lastIndex-1; j >= lastIndex - currentIndex + zeroIndex ; j--) {
                    arr[j] = 0;
                }
                lastIndex = lastIndex - currentIndex + zeroIndex;
                break;
            }
        }
        //没有找到中间零元素或者仅在末尾找到零元素，说明数组已符合要求
        if (!isFind || currentIndex == lastIndex) return;

        //递归调用
        reverse(arr,zeroIndex,lastIndex);
    }


    /**
     * 掷骰子：n个骰子，抛出后，打印出所有可能结果及其概率
     * ex：掷3个骰子，打印输出：3 0.004629629629629629  4 0.013888888888888888   5 0.027777777777777776
     * 本例使用数组暂存结果，采用自底向上循环，代码复杂于传统动态规划，好处是没有像动态规划那样方法栈很深入，存在大量重复计算。
     */
    public static void testThrowDice(int n){
        if (n < 1){
            return;
        }
        int singleMax = 6;
        int[][] probability = new int[2][singleMax*n+1];
        int flag = 0;
        //掷一枚骰子，初始化数组的值
        for (int i = 1; i <= singleMax ; i++) {
            probability[0][i] = 1;
        }
        //掷多枚骰子
        for (int i = 2; i <= n; i++) {
            //首先将低于骰子数量的无效值设置为0，因为不可能出现小于骰子数量的值
            for (int j = 0; j < i; j++) {
                probability[1- flag][j] = 0;
            }
            //计算概率，当前i枚骰子所有可能出现的值
            for (int j = i; j <= singleMax*i ; j++) {
                //因为这两个数组一直重复利用,先清掉上次的值
                probability[1-flag][j] = 0;
                //f(n,m)=f(n-1,m-1)+...+f(n-1,m-6)
                for (int k = 1; k <= j && k<= singleMax ; k++) {
                    probability[1-flag][j] += probability[flag][j-k];
                }
            }
            //轮换两个数组，重复利用
            flag = 1-flag;
        }
        double total = Math.pow(singleMax,n);
        for (int sum = n; sum <= singleMax*n; sum++) {
            double ratio = probability[flag][sum]/total;
            System.out.println(sum+ " "+ratio);
        }
    }

    public static void testMineThrowDice(int n){
        double total = Math.pow(6,n);
        for (int i = n; i <= n*6; i++) {
            System.out.println(i+" "+throwDice(n,i)/total);
        }
    }
    //利用递归，核心思路：共计n枚骰子，则当和为sum时，其概率为：n-1枚骰子总和为sum-1的概率+sum-2的概率+...+sum-6的概率。
    //动态规划方程：f(n,sum) = f(n-1,sum-1)+f(n-1,sum-2)+...+f(n-1,sum-6)
    //需要注意: sum-6必须要大于等于当前骰子数量
    //base:一枚骰子，1~6的情况都只有一种；
    //这种递归属于自顶向下，由于没有备份，会导致大量的重复计算
    public static double throwDice(int n,int sum){
        if (n < 1) return 0.0;
        if (n == 1) {
            switch (sum){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return 1.0;
                default:
                    return 0.0;
            }
        }
        double result = 0.0;
        //递归，sum-j，注意下sum - j >= 当前骰子数量（n-1）
        for (int j = 1; j <= 6 && j <= sum - n + 1; j++) {
            result += throwDice(n-1,sum-j);
        }
        return result;
    }
}
