package com.zst.javabase.algorithm.swordoffer;

import java.util.Stack;

/**
 * @Author stzhang
 * @Description
 *      输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 *      假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 *      序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2}
 *      就不可能是该压栈序列的弹出序列。
 * @Date 2021/4/7 17:50
 **/
public class IsStackPush {
    public static void main(String[] args) {
        int[] push = {1,2,3,4,5,};
        int[] pop = {4,3,2,5,1};
        System.out.println(validateStackSequences(push,pop));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            if (pushed[i] == popped[popIndex]) {
                //发现与出栈元素匹配，则执行出栈操作（这里就直接不入栈即可），同时将出栈数组下标后移
                popIndex++;
                //继续判断栈中元素是否与出栈数组匹配，匹配则执行出栈操作
                while (!stack.isEmpty()){
                    if (stack.peek() == popped[popIndex]){
                        stack.pop();
                        popIndex++;
                    } else {
                        //栈中元素不匹配出栈数组，则说明此时栈中元素不符合出栈顺序，停止判断
                        break;
                    }
                }
            } else {
                //不匹配出栈数组，执行入栈操作
                stack.push(pushed[i]);
            }
        }
        //最后栈中剩余的元素依次出栈并匹配出栈数组，全部一致，返回true，否则说明不匹配，返回false
        while (!stack.isEmpty()){
            if (stack.pop() != popped[popIndex]) return false;
            popIndex++;
        }
        if (stack.isEmpty()) return true;
        return false;
    }
}
