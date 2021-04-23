package com.zst.javabase.algorithm.linkedlist;

/**
 * 删除单链表/双链表的倒数第K个节点
 */
public class DeleteNode {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static class DNode{
        public int value;
        public DNode next;
        public DNode pre;
        public DNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node second = new Node(3);
        Node third = new Node(4);
        Node fourth = new Node(5);
        head1.next = head2;
        head2.next = second;
        second.next = third;
        third.next = fourth;
        //print(deleteKNode(head1,2));
        //print(deleteKNode(head1,5));
        print(deleteKNode(head1,3));


        DNode dHead = new DNode(1);
        DNode dSecond = new DNode(2);
        DNode dThird = new DNode(3);
        DNode dFourth = new DNode(4);
        DNode dFifth = new DNode(5);
        dHead.next = dSecond;
        dSecond.pre = dHead;
        dSecond.next = dThird;
        dThird.pre = dSecond;
        dThird.next = dFourth;
        dFourth.pre = dThird;
        dFourth.next = dFifth;
        dFifth.pre = dFourth;
        print(deleteKDNode(dHead,3));
    }

    private static void print(Node node){
        Node temp = node;
        while (temp != null){
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static void print(DNode node){
        DNode temp = node;
        while (temp != null){
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static Node deleteKNode(Node head,int k){
        if (head == null || k<1) return head;
        Node temp = head;
        while (temp != null){
            --k;
            temp = temp.next;
        }
        if (k == 0){
            return head.next;
        } else if (k > 0) {
            throw new RuntimeException("K超出链表长度！");
        } else {
            temp = head;
            while (++k < 0){
                temp = temp.next;
            }
            //删除节点
            temp.next = temp.next.next;
            return head;
        }
    }

    /**
     * 双链表与单链表基本一致，需要注意下前指针的连接
     * @param head
     * @param k
     * @return
     */
    public static DNode deleteKDNode(DNode head,int k){
        if (head == null || k<1) return head;
        DNode temp = head;
        while (temp != null){
            --k;
            temp = temp.next;
        }
        if (k == 0){
            //置空前指针
            head.next.pre = null;
            return head.next;
        } else if (k > 0) {
            throw new RuntimeException("K超出链表长度！");
        } else {
            temp = head;
            while (++k < 0){
                temp = temp.next;
            }
            //删除节点
            temp.next = temp.next.next;
            temp.next.next.pre = temp;
            return head;
        }
    }
}
