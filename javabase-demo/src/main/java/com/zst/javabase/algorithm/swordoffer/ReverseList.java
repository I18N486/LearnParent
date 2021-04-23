package com.zst.javabase.algorithm.swordoffer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author stzhang
 * @Description  倒序输出链表
 * @Date 2021/3/31 17:54
 **/
public class ReverseList {
    public static void main(String[] args) {

    }

    //借助栈
    public static void reverseLinkedList(LinkedList<String> list){
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            stack.push(s);
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    public FindEndKNode.ListNode reverseList(FindEndKNode.ListNode head) {
        Stack<FindEndKNode.ListNode> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) return head;
        head = stack.pop();
        FindEndKNode.ListNode cur = head;
        while (!stack.isEmpty()){
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    public FindEndKNode.ListNode reverseListByPointer(FindEndKNode.ListNode head) {
        FindEndKNode.ListNode cur = null, pre = head;
        while (pre != null){
            FindEndKNode.ListNode temp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = temp;
        }
        return cur;
    }

    public FindEndKNode.ListNode reverseListByDFS(FindEndKNode.ListNode head){
        if (head == null || head.next == null) return head;
        FindEndKNode.ListNode cur = reverseListByDFS(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public FindEndKNode.ListNode reverseListByPoint(FindEndKNode.ListNode head){
        if (head == null || head.next == null) return head;
        FindEndKNode.ListNode cur = head;
        while (head.next != null){
            FindEndKNode.ListNode temp = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = temp;
        }
        return cur;
    }
}
