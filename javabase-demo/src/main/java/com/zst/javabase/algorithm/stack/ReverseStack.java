package com.zst.javabase.algorithm.stack;

import java.util.Stack;

/**
 * 仅用递归和栈基本操作实现逆序一个栈，要求空间复杂度O（1）
 * 空间复杂度O（1），说明不能使用其他数据结构
 */
public class ReverseStack {

    //移除并返回栈底元素
    private int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    //每次取出栈底元素，直到栈空了，然后再依次入栈
    public void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int last = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(last);
    }
}
