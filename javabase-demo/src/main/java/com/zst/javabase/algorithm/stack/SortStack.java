package com.zst.javabase.algorithm.stack;

import java.util.Random;
import java.util.Stack;

/**
 * 实现栈从大到小排序
 * 仅允许使用一个栈作为辅助数据结构
 */
public class SortStack {

    public static void main(String[] args) {
        Stack<Integer> dataStack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            dataStack.push(new Random().nextInt(10));
            System.out.print(dataStack.peek());
        }
        System.out.println("\n-------------------");
        sort(dataStack);
        while (!dataStack.isEmpty()){
            System.out.print(dataStack.pop());
        }
    }

    public static void sort(Stack<Integer> dataStack){
        if (dataStack.isEmpty()) return;
        Stack<Integer> temp = new Stack<>();
        while (!dataStack.isEmpty()){
            Integer cur = dataStack.pop();
            while (!temp.isEmpty() && cur > temp.peek()){
                dataStack.push(temp.pop());
            }
            temp.push(cur);
        }
        while (!temp.isEmpty()){
            dataStack.push(temp.pop());
        }
    }
}
