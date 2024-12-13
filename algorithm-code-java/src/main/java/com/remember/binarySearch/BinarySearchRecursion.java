package com.remember.binarySearch;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/24 11:33
 * @Description : 二分查找递归实现
 */
public class BinarySearchRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(arr, 8));   // 左闭右开
        System.out.println(binarySearchRecursion(arr, 8, 0, arr.length)); // 左闭右开
    }

    /**
     * 普通版本--二分查找
     *
     * @param arr 待查找数组
     * @param target 目标值
     * @return 目标值索引
     */
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length;             // 左闭右开
        while (left < right) {
            int mid = (left + right) >>> 1; // 获取到中间值
            if (arr[mid] < target) {         // 目标值在中间值的右侧
                left = mid + 1;             // 左指针右移
            } else if (arr[mid] > target) {  // 目标值在中间值的左侧
                right = mid;                // 右指针左移
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归版本--二分查找
     *
     * @param arr 带查找数组
     * @param target 目标值
     * @param left 左指针
     * @param right 右指针
     * @return 目标值索引
     */
    private static int binarySearchRecursion(int[] arr, int target, int left, int right) {
        if (left >= right) { // 递归结束条件
            return -1;
        }
        int mid = (left + right) >>> 1; // 获取到中间元素
        if (arr[mid] > target) {         // 目标值在中间值的左侧
            return binarySearchRecursion(arr, target, left, mid); // 右指针左移
        } else if (arr[mid] < target) {   // 目标值在中间值的右侧
            return binarySearchRecursion(arr, target, mid + 1, right); // 左指针右移
        } else {
            return mid;
        }
    }
}
