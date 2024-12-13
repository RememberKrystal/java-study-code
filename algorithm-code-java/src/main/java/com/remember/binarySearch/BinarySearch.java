package com.remember.binarySearch;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/9/26 11:30
 * @Description : 二分查找
 */
public class BinarySearch {
    /**
     * 典型的二分查找算法
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;  // 起始索引
        int right = nums.length - 1; // 结束索引
        while (left <= right) {
            int mid = (right + left) >>> 1; // 计算中间索引
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * x的平方根：给一个非负整数 x ，计算并返回 x 的 算术平方根
     */
    public static int binarySearch2(int x) {
        int left = 0, right = x, result = -1;
        while (left <= right) {
            int avg = left + (right - left) / 2;
            if ((long) avg * avg <= x) {
                result = avg;
                left = avg + 1;
            } else {
                right = avg - 1;
            }
        }
        return result;
    }
}
