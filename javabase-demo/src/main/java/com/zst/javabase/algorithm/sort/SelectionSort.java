package com.zst.javabase.algorithm.sort;


/**
 * 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] data = {3,5,2,1,8,6,7};
//        for (int i : selectSort(data)) {
//            System.out.print(i);
//        }

        for (int i : selectSortPlus(data)) {
            System.out.print(i);
        }
    }

    public static int[] selectSort(int[] list){
        for (int i = 0; i < list.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < list.length; j++) {
                //这里仅记录最小下标,不要在这里进行值替换
                minIndex = list[minIndex] > list[j] ? j:minIndex;
            }
            int temp = list[minIndex];
            list[minIndex] = list[i];
            list[i] = temp;
        }
        return list;
    }


    public static int[] selectSortPlus(int[] list){
        for (int i = 0; i <= list.length/2; i++) {
            int minIndex = i;
            int maxIndex = i;
            for (int j = i+1; j < list.length-i; j++) {
                //这里仅记录下标,不要在这里进行值替换
                minIndex = list[minIndex] > list[j] ? j:minIndex;
                maxIndex = list[maxIndex] < list[j] ? j:maxIndex;
            }
            int temp = 0;
            if (minIndex != i) {
                temp = list[minIndex];
                list[minIndex] = list[i];
                list[i] = temp;
            }
            if (maxIndex != list.length-i -1) {
                temp = list[maxIndex];
                list[maxIndex] = list[list.length - i - 1];
                list[list.length - i - 1] = temp;
            }
        }
        return list;
    }
}
