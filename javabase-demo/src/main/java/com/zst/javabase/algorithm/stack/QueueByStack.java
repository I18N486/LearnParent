package com.zst.javabase.algorithm.stack;

import java.util.Stack;

/**
 * 设计由两个栈组成的队列
 * 队列的基本方法：
 * add  元素入队，如果队列满，则插入失败，返回false，并不抛异常；
 * poll 元素出队，如果队列为空，则返回null，并不抛异常；
 * offer 元素入队，如果使用有界队列，队列满，则插入失败，抛出异常；
 * remove 元素出队，如果队列为空，则抛出异常；
 * peek 返回队首元素
 *
 * 思路：队列与栈的区别仅仅是一个先入先出，一个先入后出。
 * 所以需要有一个栈专门用于反序存储元素
 * 两个栈，一个栈用于顺序存入元素stackPush；一个栈用于反序存入元素stackPop；分别对应队列的入队和出队。
 *
 */
public class QueueByStack {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public QueueByStack(){
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    private void pushToStackPop(){
        if (stackPop.isEmpty()){
            while (!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt){
        stackPush.push(pushInt);
        pushToStackPop();
    }

    public int poll(){
        if (stackPop.empty() && stackPush.empty()){
            throw  new RuntimeException("queue is empty!");
        }
        pushToStackPop();
        return stackPop.pop();
    }

    public int peek(){
        if (stackPop.empty() && stackPush.empty()){
            throw  new RuntimeException("queue is empty!");
        }
        pushToStackPop();
        return stackPop.peek();
    }
}
