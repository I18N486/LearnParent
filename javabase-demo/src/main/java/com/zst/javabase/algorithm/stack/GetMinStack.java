package com.zst.javabase.algorithm.stack;

import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
 *  pop、push、getMin（返回栈中最小元素）的复杂度都是O(1)
 *  思路：由于栈是一个数据结构，要想实现O(1)找到最小值，则肯定是在每次入栈的时候就得到了最小值，并保留着，且随着出栈而动态变化。
 *          因此可以使用一个额外的栈，用于存储当前栈的最小值。
 */
public class GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    /**
     * 每次入栈时，判断stackMin当前栈顶元素是否小于新元素，stackMin栈顶总是存储当前栈中最小元素
     * 注意这里一定是等于也要入栈，因为当元素出栈的时候，对应的stackMin栈中相等的元素也要出栈，如果最小值相等的元素仅入栈一个，
     * 则当出栈时，就会导致stackMin中不存在最小元素了。
     * @param newNum
     */
    public void push(int newNum){
        if (stackMin.isEmpty() || newNum <= getMin()){
            stackMin.push(newNum);
        }
        stackData.push(newNum);
    }

    /**
     * 出栈时需要判断stackMin栈顶元素是否与出栈元素相等，相等则需要移除stackMin栈顶元素，
     * 时刻保持stackMin栈顶总是当前栈中最小值
     * @return
     */
    public Integer pop(){
        if (stackData.isEmpty()){
            throw new RuntimeException("The stack is empty!");
        }
        Integer value = stackData.pop();
        if (value == getMin()){
            stackMin.pop();
        }
        return value;
    }

    /**
     * peek方法仅仅是返回栈顶元素，不从栈中移除元素
     * @return
     */
    private int getMin() {
        if (stackMin.isEmpty()){
            throw  new RuntimeException("The stack is empty!");
        }
        return stackMin.peek();
    }
}
