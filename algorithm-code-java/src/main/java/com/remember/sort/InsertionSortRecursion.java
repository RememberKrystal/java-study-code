package com.remember.sort;

import java.util.Arrays;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/24 14:20
 * @Description : 插入排序
 */
public class InsertionSortRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 4, 9, 6, 7, 3, 5};
        insertionSort1(arr,4,6);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] arr) {
        int i = 1; // 待排序的左边界
        while (i < arr.length) {
            int j = i - 1; // 已排序区域的右边界
            int temp = arr[i]; // 待插入的元素
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j]; // 将大于temp的元素向后移动一位
                j--;
            }
            arr[j + 1] = temp; // 将temp插入到正确位置
            i++;
        }
    }

    /**
     * 插入排序（递归实现） 指定待排序的左边界
     *
     * @param arr  待排序数组
     * @param left 待排序数组的左边界
     */
    public static void insertionSort(int[] arr, int left) {
        // 结束条件
        if (left == arr.length) {
            return;
        }
        int i = arr[left]; // 待插入的元素
        int j = left - 1; // 已排序区域的右边界

        while (j >= 0 && arr[j] > i) {
            arr[j + 1] = arr[j]; // 将大于i的元素向后移动一位
            j--;
        }

        arr[j + 1] = i; // 将i插入到正确位置

        insertionSort(arr, left + 1); // 递归调用，继续处理下一个元素
    }

    /**
     * 插入排序：指定待排序的左右边界
     */
    public static void insertionSort1(int[] arr, int left, int right) {
        while (left <= right) {
            int i = arr[left]; // 待插入的元素
            int j = left - 1; // 已排序区域的右边界
            while (j >= 0 && arr[j] > i){
                arr[j + 1] = arr[j]; // 将大于i的元素向后移动一位
                j--;
            }
            arr[j + 1] = i; // 将i插入到正确位置
            left++;
        }

    }


    /**
     * 插入排序：指定待排序的左右边界（递归实现）
     */
    public static void insertionSort2(int[] arr, int left, int right) {
        if (left > right) {
            return;// 结束条件
        }
        int i = arr[left]; // 待插入的元素
        int j = left - 1; // 已经排序的右边界
        while (j >= 0 && arr[j] > i) {
            arr[j + 1] = arr[j]; // 将大于i的元素向后移动一位
            j--;
        }
        arr[j + 1] = i; // 将i插入到正确位置

        insertionSort2(arr, left + 1, right);
    }

}
