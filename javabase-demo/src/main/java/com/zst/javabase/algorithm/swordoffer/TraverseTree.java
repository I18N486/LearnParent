package com.zst.javabase.algorithm.swordoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author stzhang
 * @Description
 *      遍历二叉树：先序（根）遍历（《=》根左右）、中序（根）遍历（《=》左根右）、后序（根）遍历（《=》左右根）
 *      以上三种遍历，均是深度优先搜索 dfs
 *      了解了二叉树的三种遍历，可以很好的掌握dfs（理清回溯）
 *
 *      拓展：从上到下按层打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *      这显然不是深度优先，而是广度优先搜索 bfs，广度优先搜索需要借助队列来实现
 * @Date 2021/4/9 14:21
 **/
public class TraverseTree {
    
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static List<Integer> orderList = new LinkedList<>();
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);
        TreeNode left2 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode left3 = new TreeNode(0);
        TreeNode right3 = new TreeNode(4);
        root.left = left1;
        root.right = right1;
        left1.left = left2;
        left1.right = right2;
        right1.left = left3;
        right1.right = right3;

        System.out.println("======先序遍历======");
        for (Integer i : firstRoot(root)) {
            System.out.print(i+" ");
        }
        orderList.clear();

        System.out.println("\n======中序遍历======");
        for (Integer i : secondRoot(root)) {
            System.out.print(i+" ");
        }
        orderList.clear();

        System.out.println("\n======后序遍历======");
        for (Integer i : lastRoot(root)) {
            System.out.print(i+" ");
        }
        orderList.clear();

        System.out.println("\n======层序遍历======");
        for (Integer i : levelOrder(root)) {
            System.out.print(i+" ");
        }

        System.out.println("\n======层序遍历plus======");
        List<List<Integer>> lists = levelOrderWithLevel(root);
        for (List<Integer> list : lists) {
            System.out.print("[");
            for (Integer integer : list) {
                System.out.print(integer+" ");
            }
            System.out.println("]");
        }

        int[] post = {4, 8, 6, 12, 16, 14, 10};
        System.out.println(verifyPostorder(post));

        List<List<Integer>> pathSum = pathSum(root, 27);
        for (List<Integer> path : pathSum) {
            for (Integer val : path) {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    public static List<Integer> firstRoot(TreeNode root){
        if (root == null) return orderList;
        orderList.add(root.val);
        if (root.left != null) firstRoot(root.left);
        if (root.right != null) firstRoot(root.right);
        return orderList;
    }

    public static List<Integer> secondRoot(TreeNode root){
        if (root == null) return orderList;
        if (root.left != null) secondRoot(root.left);
        orderList.add(root.val);
        if (root.right != null) secondRoot(root.right);
        return orderList;
    }

    public static List<Integer> lastRoot(TreeNode root){
        if (root == null) return orderList;
        if (root.left != null) lastRoot(root.left);
        if (root.right != null) lastRoot(root.right);
        orderList.add(root.val);
        return orderList;
    }

    /**
     * 按层遍历，广度优先搜索bfs，借助队列辅助实现
     * @param root
     * @return
     */
    public static List<Integer> levelOrder(TreeNode root){
        if (root == null) return orderList;
        Queue<TreeNode> temp = new LinkedList<>();
        //首先根节点入队
        temp.add(root);
        while (!temp.isEmpty()){
            //当队列不空时，依次出队，并将出队的节点值存入遍历list，然后再把左节点和右节点入队
            //借助队列先入先出的特性，保证了按层遍历bfs
            TreeNode node = temp.poll();
            orderList.add(node.val);
            if (node.left != null) temp.add(node.left);
            if (node.right != null) temp.add(node.right);
        }
        return orderList;
    }

    /**
     * 按层遍历，广度优秀搜索bfs，借助队列辅助实现
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderWithLevel(TreeNode root){
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> temp = new LinkedList<>();
        List<List<Integer>> order = new ArrayList<>();
        //首先根节点入队
        temp.add(root);
        while (!temp.isEmpty()){
            //按题意，要实现按层存储同一个list中
            LinkedList<Integer> level = new LinkedList<>();
            //当前队列存储的是同一层所有节点，遍历所有
            for (int i = temp.size(); i > 0; i--) {
                TreeNode node = temp.poll();
                if (order.size() % 2 == 0) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) temp.add(node.left);
                if (node.right != null) temp.add(node.right);
            }
            //遍历完一层
            order.add(level);
        }
        return order;
    }

    public static boolean verifyPostorder(int[] postorder) {
        return isTree(postorder,0,postorder.length-1);
    }

    /**
     * 判断是否是某一颗二叉搜索树的后续遍历
     * 二叉搜索树的后续遍历序列有个特点：
     *      前一部分一定都小于根（arr[arr.length-1]）,后一部分一定都大于根.
     *      然后前一部分又属于一个二叉搜索树的后续遍历，后一部分也是，这样就分成了两个小序列，继续判断。
     * 上述这种思想叫做递归分治，将一个大问题分为多个小问题
     * @param postorder
     * @param start
     * @param end
     * @return
     */
    public static boolean isTree(int[] postorder,int start,int end){
        if (start >= end) return true;
        int cur = start;
        while (postorder[cur] > postorder[end]) cur++;
        int m = cur;
        while (postorder[cur] > postorder[end]) cur++;
        return cur == end && isTree(postorder,0,m-1) && isTree(postorder,m,end-1);
    }


    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> data = new ArrayList<>();
        //path(root,27,new ArrayList<>(),data);
        path1(root,27,0,new ArrayList<>(),data);
        return data;
    }

    public static boolean path(TreeNode root,int target,List<Integer> path,List<List<Integer>> data){
        while (root != null){
            int sum = 0;
            for (Integer i : path) {
                sum += i;
            }
            sum += root.val;
            if (sum > target) {
                return false;
            } else {
                path.add(root.val);
                if (sum == target) {
                    return true;
                }
            }
            boolean isFind = path(root.left, target, path,data);
            if (isFind){
                //找到则新建一个list复制当前path，存储进data，防止数据覆盖
                List<Integer> newPath = new ArrayList<Integer>();
                newPath.addAll(path);
                data.add(newPath);
                //回溯继续查找后续可能,走另一条节点路径（注意移除当前节点,重置sum）
                path.remove(path.size()-1);
            }
            isFind =  path(root.right,target,path,data);
            if (isFind){
                //找到则新建一个list复制当前path，存储进data，防止数据覆盖
                List<Integer> newPath = new ArrayList<Integer>();
                newPath.addAll(path);
                data.add(newPath);
                //回溯继续查找后续可能,走另一条节点路径（注意移除当前节点,重置sum）
                path.remove(path.size()-1);
            } else {
                //回退节点
            }

        }
        return false;
    }

    public static boolean path1(TreeNode root,int target,int sum,List<Integer> path,List<List<Integer>> data){
        if (root != null){
            sum += root.val;
            if (sum > target) {
                return false;
            } else {
                path.add(root.val);
                if (sum == target) {
                    //找到则新建一个list复制当前path，存储进data，防止数据覆盖
                    List<Integer> newPath = new ArrayList<Integer>();
                    newPath.addAll(path);
                    data.add(newPath);
                    //同时移除path中的当前节点，重置sum
                    path.remove(path.size()-1);
                    return true;
                }
            }
            path1(root.left, target,sum,path,data);
            path1(root.right,target,sum,path,data);
            path.remove(path.size()-1);
        }
        return false;
    }
}
