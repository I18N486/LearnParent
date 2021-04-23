package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description   删除链表节点
 * @Date 2021/4/7 10:28
 **/
public class DeleteNode {
    public static void main(String[] args) {

    }

    public static FindEndKNode.ListNode deleteNode(FindEndKNode.ListNode head, int val) {
        if(head.value == val) return head.next;
        FindEndKNode.ListNode pre = head, cur = head.next;
        while(cur != null && cur.value != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) pre.next = cur.next;
        return head;
    }
}
