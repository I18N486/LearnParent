package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/2 9:50
 **/
public class FindEndKNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        head.next = second;
        second.next = third;
        third.next = fourth;
        int k = 4;
        ListNode kNode = findEndKNode(head,k);
        if (kNode != null) {
            System.out.println(kNode.value);
        } else {
            throw new RuntimeException("链表不存在倒数第"+k+"个节点");
        }
    }

    /**
     * 查找链表的倒数第K个节点
     * 解题思路：采用双指针，先行指针顺序遍历链表节点，同时计数，
     *      当计数器等于K时，后行指针从链表头开始与先行指针一起同步遍历链表；
     *      当先行指针遍历结束，此时后行指针指向倒数第K个节点。
     * @param head
     * @param k
     * @return
     */
    public static ListNode findEndKNode(ListNode head,int k){
        int count = 0;
        ListNode find = null;
        ListNode cur = head;
        while (cur != null){
            cur = cur.next;
            count++;
            if (count == k){
               find = head;
            } else if (count > k){
                find = find.next;
            }
        }
        return find;
    }

    public static class ListNode{
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}
