package com.zst.javabase.algorithm.swordoffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/2 16:26
 **/
public class FindRepeatInArr {
    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        System.out.println(findRepeat(arr));
    }

    public static int findRepeat(int[] arr){
        Set<Integer> temp = new HashSet<>();
        for (int i : arr) {
            if (temp.contains(i)){
                return i;
            }
            temp.add(i);
        }
        return -1;
    }
}
