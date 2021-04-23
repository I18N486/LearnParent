package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 9:35
 **/
public class Fibonacci {
    public static void main(String[] args) {
        fib(48);
    }

    public static int fib(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 2; i < n; i++) {
            second = second+first;
            first = second-first;
        }
        return second+first;
    }

    public static int fibPlus(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
