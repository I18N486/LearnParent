package com.zst.javabase.algorithm.swordoffer;

import java.util.HashSet;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/2 10:59
 **/
public class CharCombSort {
    public static void main(String[] args) {
        //combSort("abc".toCharArray(),0);
        String str = "abc";
        //dfs(str.toCharArray(),"",new boolean[str.length()]);

        String s = "abb";
        combSort(s.toCharArray(),0);
    }

    /**
     * 输出由字符串的所有字符组成的任意字串
     * ex: abc -> abc,acb,bac,bca,cab,cba
     * @param chars
     */
    private static void combSort(char[] chars,int index){
        if (index == chars.length -1){
            System.out.println(new String(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i= index;i<chars.length;i++){
            //set里面已经包含，进行剪枝
            if (set.contains(chars[i])) continue;
            if (i == index || chars[i] != chars[index]){
                //向set中存入当前选中的字符，便于去重，剪枝
                set.add(chars[i]);
                //选中当前字符
                swap(chars,index,i);
                //递归选取后续字符
                combSort(chars,index+1);
                //回溯，恢复字串，跳过当前字符，继续后续遍历
                swap(chars,index,i);
            }
        }
    }
    private static void swap(char[] chars,int a,int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    /**
     * 借助标志位数组，每次都是遍历字符数组，选取的字符，就将标志位置为true，避免重复选取，然后递归重复下一次选取，
     * 直到选取了所有的字符，即可打印结果；然后再重置当前选中的字符标志位false，跳过当前字符，继续后续遍历，逐层递归返回。
     * @param chars
     * @param s
     * @param visited
     */
    public static void dfs(char[] chars,String s,boolean[] visited){
        if (s.length() == chars.length) {
            System.out.println(s);
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            //顺序遍历，选取当前元素，并将标志位设置true
            dfs(chars,s+chars[i],visited);
            //递归返回，说明当前元素已经选中过并且打印了所有可能的结果，现在重置标志位false，跳过当前元素，继续遍历后续元素
            visited[i] = false;
        }
    }


}
