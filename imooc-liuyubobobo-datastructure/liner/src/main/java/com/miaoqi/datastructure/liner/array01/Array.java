package com.miaoqi.datastructure.liner.array01;

/**
 * 数组基本使用
 *
 * @author miaoqi
 * @date 2018/12/13
 */
public class Array {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] scores = new int[]{60, 99, 100};
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        for (int score : scores) {
            System.out.println(score);
        }
    }
}
