package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * @Date 2021/4/7 16:39
 **/
public class MergeList {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head=null,cur=null;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                if (cur == null){
                    head = cur = l1;
                } else {
                    cur.next = l1;
                    cur = l1;
                }
                l1 = l1.next;
            } else {
                if (cur == null){
                    head = cur = l2;
                } else {
                    cur.next = l2;
                    cur = l2;
                }
                l2 = l2.next;
            }
        }
        if (l1 != null)cur.next = l1;
        if (l2 != null) cur.next = l2;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
