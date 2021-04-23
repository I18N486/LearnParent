package com.zst.javabase.algorithm.swordoffer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 17:23
 **/
public class Count1 {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        int count = 0;
        for (int i = 0; i < 10000; i++) {
           count = hammingWeight(11111111011011l);
        }
        System.out.println("count:"+count);
        System.out.println(timer.intervalRestart());
        for (int i = 0; i < 10000; i++) {
            count = hammingWeight2(11111111011011l);
        }
        System.out.println("count:"+count);
        System.out.println(timer.intervalRestart());
        for (int i = 0; i < 10000; i++) {
            count = hammingWeight3(11111111011011l);
        }
        System.out.println("count:"+count);
        System.out.println(timer.intervalRestart());
    }

    private static int hammingWeight(long n){
        int count = 0;
        while (n > 0){
            count += n&1;
            n = n >>> 1;
        }
        return count;
    }

    private static int hammingWeight2(long n){
        int res = 0;
        while (n != 0){
            res++;
            n &= n-1;
        }
        return res;
    }

    private static int hammingWeight3(long n){
        int count = 0;
        char[] chars = String.valueOf(n).toCharArray();
        for (char aChar : chars) {
            if (aChar == '1'){
                count++;
            }
        }
        return count;
    }
}
