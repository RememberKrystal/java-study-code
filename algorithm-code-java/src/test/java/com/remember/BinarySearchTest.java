package com.remember;

import com.remember.binarySearch.BinarySearch;
import org.junit.Test;

public class BinarySearchTest {
    @Test
    public void binarySearch() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;
        int result = BinarySearch.binarySearch(nums, target);
        System.out.println(result);
    }

    @Test
    public void binarySearch2() {
        int result = BinarySearch.binarySearch2(20);
        System.out.println(result);
    }

}