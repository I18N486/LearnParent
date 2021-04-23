package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 17:43
 **/
public class MyPow {
    public static void main(String[] args) {
        System.out.println(myPow(3, 3));
        System.out.println(myPow(3, -2));
    }

    public static double myPow(double x, int n) {
        long b = n;
        if (b<0){
            return 1/multiply(x,-b);
        } else if (b > 0){
            return multiply(x,b);
        } else return 1;
    }

    private static double multiply(double x,long n){
        double product = 1;
        for (long i = 0; i < n; i++) {
            product *= x;
        }
        return product;
    }
}
