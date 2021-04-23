package com.zst.javabase.algorithm.linkedlist;

/**
 * 反转单向链表/双向链表
 */
public class ReverseList {

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

    private static void printCircle(Node node){
        Node temp = node;
        while (temp != null){
            System.out.print(temp.value + " ");
            if (temp.next == temp) break;
            temp = temp.next;
        }
        System.out.println();
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
        //circle list
        fourth.next = head1;
        //print(reverseNode(head1));
        //print(reverseNodeBetweenij(head1,2,3));
        printCircle(killWhenNum(head1,3));

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
        print(reverseDNode(dHead));
    }

    public static Node reverseNode(Node head){
        Node temp = null;
        while (head != null){
            Node cur = head.next;
            head.next = temp;
            temp = head;
            head = cur;
        }
        return temp;
    }

    public static DNode reverseDNode(DNode head){
        DNode temp = null;
        while (head != null){
            DNode cur = head.next;
            head.next = temp;
            if (temp != null) temp.pre = head;
            temp = head;
            head = cur;
        }
        temp.pre = null;
        return temp;
    }

    public static Node reverseNodeBetweenij(Node head,int from,int to){
        int count = 1;
        Node list = head;
        Node cur;
        Node temp = null;
        Node pre = null;
        Node last = null;
        Node reverse_last = null;
        while (list != null){
            cur = list.next;
            if (count < from){
                pre = list;
            } else if (from <= count && count <= to){
                if (count == from){
                    reverse_last = list;
                } else if (count == to){
                    last = list.next;
                }
                list.next = temp;
                temp = list;
            }
            count++;
            list = cur;
        }

        if (null != pre){
            pre.next = temp;
        }
        if (null != last){
            reverse_last.next = last;
        }
        return head;
    }

    public static Node killWhenNum(Node head,int num){
        int count = 1;
        while (head != null && head.next != head){
            head = head.next;
            count++;
            if (count == 2){
                //下一个是要删除的节点，执行删除,同时清空计数器
                head.next = head.next.next;
                count = 0;
            }
        }
        return head;
    }
}
