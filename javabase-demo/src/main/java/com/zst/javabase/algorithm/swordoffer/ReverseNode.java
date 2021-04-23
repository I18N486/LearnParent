package com.zst.javabase.algorithm.swordoffer;

import java.util.Stack;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/2 17:24
 **/
public class ReverseNode {
    public static void main(String[] args) {
        FindEndKNode.ListNode head = new FindEndKNode.ListNode(1);
        FindEndKNode.ListNode second = new FindEndKNode.ListNode(2);
        FindEndKNode.ListNode third = new FindEndKNode.ListNode(3);
        FindEndKNode.ListNode fourth = new FindEndKNode.ListNode(4);
        head.next = second;
        second.next = third;
        third.next = fourth;
        int[] result = reversePrint(head);
        for (int i : result) {
            System.out.print(i+" ");
        }
    }

    public static int[] reversePrint(FindEndKNode.ListNode head) {
        Stack<Integer> temp = new Stack<>();
        while (head != null){
            temp.push(head.value);
            head = head.next;
        }
        int[] result = new int[temp.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp.pop();
        }
        return result;
    }
}
