package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description  替换字符串中的空格为%20
 * @Date 2021/3/31 17:47
 **/
public class ReplaceSpace {
    public static void main(String[] args) {
        replace("We are family");
        replaceByMine("We are family");
    }


    public static void replace(String str){
        String s = str.replaceAll(" ", "%20");
        System.out.println(s);
    }

    public static void replaceByMine(String str){
        StringBuilder append = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                append.append("%20");
            } else {
                append.append(c);
            }
        }
        System.out.println(append.toString());
    }
}
