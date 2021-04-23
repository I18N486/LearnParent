package com.zst.javabase.algorithm.stack;

import java.util.Stack;

/**
 * 可见山峰问题
 *  相邻的两座山峰是可见的；不相邻的两座山峰，只要环形两个方向任何一个没有大于最小值的山峰，则也是可见的。
 *  1,2,4,5,3  可见山峰对有：(1,2)(1,3)(2,3)(2,4)(4,5)(3,5)(3,4)
 *
 * 升级：有重复高度的山峰时，简单的推导公式不再适用，需要用到单调栈
 */
public class VisibleMountain {

    public static void main(String[] args) {
        int[] arr = {1,2,4,5,3};
        System.out.println("可见山峰对有："+visibleNoRepeat(arr)+" 对");
        System.out.println("可见山峰对有："+getVisibleCount(arr)+" 对");
        int[] arr2 = {1,2,4,5,7,3,6,8};
        System.out.println("可见山峰对有："+visibleNoRepeat(arr2)+" 对");
        System.out.println("可见山峰对有："+getVisibleCount(arr2)+" 对");
        int[] arr3 = {3,2,5,4,3,5,4,2,4,4,5};
        //有重复高度的山峰时，简单的推导公式不再适用。
        System.out.println("可见山峰对有："+visibleNoRepeat(arr3)+" 对");
        System.out.println("可见山峰对有："+getVisibleCount(arr3)+" 对");
    }

    /**
     * 没有重复高度的山峰时，就是一个简单的推导公式
     * 2*n-3
     * 推导：当N为1，可见山峰对为0；当N为2，可见山峰对为1；
     *      当N大于等于3时，由于元素不重复，所以一定可以找到两个值：一个是最大值，一个是次大值。
     *      剩下N-2个元素，按照小找大的原则，不管从哪个方向去找，都一定能找到首个比它大的元素（其中比它小的不管,有可能就是刚刚找出的最大值和次大值）
     *      因此（N-2）*2，再加上最大值和次大值之间也可见，所以是（N-2）*2+1=2*N-3.
     * 时间复杂度O（1）
     * @param arr
     * @return
     */
    public static int visibleNoRepeat(int[] arr){
        if (arr.length >= 2){
            return 2*arr.length -3;
        }
        return 0;
    }


    /**
     * 有高度重复的山峰(单调栈解决)
     * @param arr
     * @return
     */
    public static int getVisibleCount(int[] arr){
        if (null == arr || arr.length<2) return 0;
        int visibleCount = 0;
        //第一步找到最大值
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i: maxIndex;
        }
        //遍历数组，利用单调栈实现找左右两个方向最近大于的山峰（小找大原则）
        Stack<Record> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            //从最大值下标开始遍历数组
            int curIndex = (maxIndex+i) % arr.length;
            while (!stack.isEmpty() && arr[curIndex] > stack.peek().value){
                Record pop = stack.pop();
                if (pop.count > 1){
                    //出栈的节点，count大于1时，每个都有左右两个可见山峰，所以是2*count，另外相互之间也是可见的，所以再加上CN2
                    visibleCount += pop.count*2+ cN2(pop.count);
                } else {
                    visibleCount += 2;
                }
            }
            if (!stack.isEmpty() && stack.peek().value == arr[curIndex]){
                stack.peek().count++;
            } else {
                Record record = new Record(arr[curIndex]);
                stack.push(record);
            }
        }
        //对栈中剩下的元素进行遍历
        while (!stack.isEmpty()){
            Record pop = stack.pop();
            //第一种情况：当前栈中还有两个及以上节点,说明出栈节点左右两个方向还是可以找到最近大于的山峰
            if (stack.size() >=2){
                if (pop.count > 1){
                    visibleCount += pop.count*2+ cN2(pop.count);
                } else {
                    visibleCount += 2;
                }
            }
            //第二种情况：当前栈中还有一个节点，说明出栈节点左右方向都是可以找到最近大于的山峰，不过是同一个节点
            else if (stack.size() == 1){
                //细分，这个节点count>1,则说明出栈节点左右方向找到的不是同一个
                if (stack.peek().count>1){
                    if (pop.count > 1){
                        visibleCount += pop.count*2+ cN2(pop.count);
                    } else {
                        visibleCount += 2;
                    }
                }
                //细分，这个节点count = 1，说明左右方向找的是同一个，不能算两个
                else {
                    if (pop.count > 1){
                        visibleCount += pop.count+ cN2(pop.count);
                    } else {
                        visibleCount += 1;
                    }
                }
            }
            //第三种情况：当前栈中没有节点，说明出栈节点仅可以自身之间寻找可见山峰
            else {
                if (pop.count > 1){
                    visibleCount += cN2(pop.count);
                }
            }
        }

        return visibleCount;
    }

    public static class Record {
        private int value;
        private int count;

        public Record(int value){
            this.value = value;
            this.count = 1;
        }
    }

    private static int cN2(int n){
        return n*(n-1)/2;
    }
}
