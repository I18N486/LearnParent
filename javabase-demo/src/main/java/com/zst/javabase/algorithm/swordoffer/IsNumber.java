package com.zst.javabase.algorithm.swordoffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author stzhang
 * @Description
 *      请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"
 *      都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * @Date 2021/4/7 14:15
 **/
public class IsNumber {
    public static void main(String[] args) {
        System.out.println(isNumberPlus("32.e-80123"));
        System.out.println(isNumber("32.e-80123"));
    }

    public static boolean isNumber(String s){
        char[] chars = s.toCharArray();
        boolean hasSpace = false;
        boolean hasSign = false;
        boolean hasNum = false;
        boolean hasDot = false;
        boolean hasE = false;
        for (char c : chars) {
            if (' ' == c){
                //前面什么都没有，说明是起始空格，继续遍历
                if (!hasSign && !hasNum && !hasDot && !hasE)continue;
                //已经有任何其它非空字符，则后续只能全是空格，否则就不是数字
                if (hasSign || hasNum || hasDot || hasE) hasSpace = true;
            } else if ('+' == c || '-' == c){
                //已经有空格、数字、符号、小数点，后续不可再有符号
                if (hasSpace || hasNum || hasSign || hasDot) return false;
                else hasSign = true;
            } else if ('.' == c){
                //前面已经有了空格、小数点、e，后续不可再有小数点
                if (hasSpace || hasDot || hasE) return false;
                else hasDot = true;
            } else if ('0' <= c && c <= '9'){
                //前面有空格，后续不可再有数字
                if (hasSpace) return false;
                else hasNum = true;
            } else if ('e' == c || 'E' == c){
                //前面有空格、没有数字、有e，后续不可再有e
                if (hasSpace || !hasNum || hasE) return false;
                else {
                    hasE = true;
                    //e之后重置，可以有符号，且必须要有数字，不可以有小数点
                    hasNum = false;
                    hasSign = false;
                    hasDot = false;
                }
            } else {
                //其它非法字符，直接返回
                return false;
            }
        }
        //这里最后再判断一下，防止e之后没有数字的情况
        if (hasNum) return true;
        else return false;
    }

    public static boolean isNumberPlus(String s) {
        Map[] states = {
                new HashMap<Character,Integer>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<Character,Integer>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<Character,Integer>() {{ put('d', 3); }},                                        // 4.
                new HashMap<Character,Integer>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<Character,Integer>() {{ put('d', 7); }},                                        // 6.
                new HashMap<Character,Integer>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<Character,Integer>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if(!states[p].containsKey(t)) return false;
            p = (int)states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}
