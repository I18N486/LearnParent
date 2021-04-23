package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description 剪绳子，长度为n的绳子，剪成m段，可能的最大乘积是多少（m，n >1）？
 * ex：10M绳子剪成三段3,3,4使得面积最大3*3*4=36
 * 数学推导得到结论：要尽可能分成长度为3的小段，剩下的绳段长度为0,1,2；
 * 注意剩下1时，则应该拿一段3的小段与剩下的1合并，分为2*2的小段（2*2>1*3）
 * @Date 2021/4/6 15:12
 **/
public class CutTheRope {
    public static void main(String[] args) {
        System.out.println(cuttingRope(3));
    }

    private static int cuttingRope(int n){
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }
}
