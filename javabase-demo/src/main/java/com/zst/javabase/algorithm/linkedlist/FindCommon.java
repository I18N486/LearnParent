package com.zst.javabase.algorithm.linkedlist;

/**
 * 打印两个递增链表的公共部分
 *
 */
public class FindCommon {

    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
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
        //printCommon(head1,head2);
        printCommonPlus(head1,head2);
    }

    public static void printCommon(Node head1,Node head2){
        if (head1.value > head2.value){
            while (head2.next != null){
                if (head1 != null && head1.value == head2.next.value){
                    print(head1.value);
                    head1 = head1.next;
                } else if (head1 == null){
                    break;
                }
                head2 = head2.next;
            }
        } else if (head1.value < head2.value){
            while (head1.next != null){
                if (head2 != null && head2.value == head1.next.value){
                    print(head2.value);
                    head2 = head2.next;
                } else if (head2 == null){
                    break;
                }
                head1 = head1.next;
            }
        } else {
            print(head1.value);
        }
    }

    public static void printCommonPlus(Node head1,Node head2){
        while (head1 != null || head2 != null){
            if (head1.value > head2.value){
                head2 = head2.next;
            } else if (head1.value < head2.value){
                head1 = head1.next;
            } else {
                print(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    private static void print(int value){
        System.out.print(value+"->");
    }

}
