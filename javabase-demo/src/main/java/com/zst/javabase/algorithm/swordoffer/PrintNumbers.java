package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 17:54
 **/
public class PrintNumbers {
    public static void main(String[] args) {

    }

    public static int[] printNumbers(int n) {
        int max = 9;
        for (int i = 1; i < n; i++) {
            max = max * 10 +9;
        }
        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = i+1;
        }
        return data;
    }
}
