package com.remember.sort;

import java.util.Arrays;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/24 11:45
 * @Description : 冒泡排序递归实现
 */
public class BubbleSortRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 4, 9, 6, 7, 3, 5};
//        System.out.println(Arrays.toString(bubbleSort1(arr)));
//        System.out.println(Arrays.toString(bubbleSort2(arr)));
//        System.out.println(Arrays.toString(bubbleSortRecursion1(arr,0)));
        System.out.println(Arrays.toString(bubbleSortRecursion2(arr, arr.length - 1)));
    }

    /**
     * 冒泡排序--非递归实现1
     *
     * @param arr 待排序数组
     * @return 已排序数组
     */
    private static int[] bubbleSort1(int[] arr) {
        // 检查数组是否为空或长度小于2
        if (arr == null || arr.length < 2) {
            return arr; // 无需排序
        }

        // 获取数组长度
        int n = arr.length;

        // 外层循环控制总共需要多少趟
        for (int i = 0; i < n - 1; i++) {
            // 内层循环控制每一趟的比较次数
            for (int j = 0; j < n - 1 - i; j++) {
                // 如果前一个元素大于后一个元素，则交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序--非递归实现2
     *
     * @param arr 待排序数组
     * @return 已排序数组
     */
    private static int[] bubbleSort2(int[] arr) {
        int i = 0;
        while (i < arr.length - 1) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            i++;
        }
        return arr;
    }

    /**
     * 冒泡排序--递归实现1
     */
    private static int[] bubbleSortRecursion1(int[] arr, int i) {
        if (i > arr.length - 1) { // 递归结束条件
            return arr;
        }

        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] > arr[j]) { // 比较相邻元素
                int temp = arr[i]; // 临时变量
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        return bubbleSortRecursion1(arr, i);
    }

    /**
     * 冒泡排序--递归实现2
     */
    private static int[] bubbleSortRecursion2(int[] arr, int j) {
        if (j == 0){
            return arr;
        }
        int x = 0;
        for (int i = 0; i < j; i++){
            if (arr[i] > arr[i + 1]){
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                x = i;
            }
        }
        return bubbleSortRecursion2(arr, x);
    }
}
