package com.miaoqi.datastructure.liner.recursion;

/**
 * 递归是将原来的问题转化为更小的同一问题
 *
 * @author miaoqi
 * @date 2019-06-15
 */
public class ArraySumRecursion {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(arr));
    }

}
