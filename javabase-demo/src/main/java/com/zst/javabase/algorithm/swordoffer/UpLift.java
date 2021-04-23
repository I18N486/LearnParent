package com.zst.javabase.algorithm.swordoffer;

/**
 * 上楼梯问题：
 * n阶楼梯，每次可以上1阶或者2阶,共有多少种上楼方法
 * 动态规划方程：f(n) = f(n-1)+f(n-2)
 * 推导：因为每次只能上一阶或者2阶，所以要到达N阶，可以从N-1阶上来或者从n-2阶上来
 */
public class UpLift {
    public static void main(String[] args) {
        System.out.println("18阶楼梯共有" + lift(18) + "种上楼方法");
        System.out.println("18阶楼梯共有" + liftPlus(18) + "种上楼方法");
    }

    public static int lift(int n) {
        if (n <= 2) return n;
        return lift(n - 1) + lift(n - 2);
    }

    //优化，上面简单的递归，效率低下。因为存在大量重复计算
    //利用备忘录，减少计算量
    public static int liftPlus(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i < n; i++) {
            second = second + first;
            first = second - first;
        }
        return second + first;
    }
}
