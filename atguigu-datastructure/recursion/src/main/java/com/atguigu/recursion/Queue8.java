package com.atguigu.recursion;

/**
 * 八皇后问题
 *
 * 1) 第一个皇后先放第一行第一列
 * 2) 第二个皇后放在第二行第一列、然后判断是否OK[即判断是冲突]， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3) 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 * 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
 * // 对应arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列
 *
 * @author miaoqi
 * @date 2023-04-12 0:34:58
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[this.max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w

    }


    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if (n == this.max) {  //n = 8 , 其实8个皇后就既然放好, n 从 0 开始, 所以 n=8 时就是第 9 个皇后
            this.print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < this.max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            this.array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (this.judge(n)) { // 不冲突
                //接着放n+1个皇后,即开始递归
                this.check(n + 1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }

    //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 说明
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            // n = 1  放置第 2列 1 n = 1 array[1] = 1
            // Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (this.array[i] == this.array[n] || Math.abs(n - i) == Math.abs(this.array[n] - this.array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

}
