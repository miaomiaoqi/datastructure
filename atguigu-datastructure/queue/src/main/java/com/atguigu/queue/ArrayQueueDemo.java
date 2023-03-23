package com.atguigu.queue;

import java.util.Scanner;

/**
 * 使用数组实现队列
 *
 * @author miaoqi
 * @date 2023-03-22 20:58:8
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("p(peek): 查看队列头的数据");
            key = scanner.next().charAt(0); // 接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

// 使用数组模拟队列-编写一个 ArrayQueue 类
class ArrayQueue {

    private int maxSize; // 表示最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数组用于存放数据, 模拟队列

    // 创建队列构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部, 分析出 front 是指向队列头的前一个位置, 不是队列头的真正位置
        rear = -1; // 指向队列尾部, 指向队列尾的数据(即就是队列的最后一个数据)

    }

    /**
     * 判断队列是否满
     *
     * @author miaoqi
     * @date 2023-03-23 3:27:6
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @author miaoqi
     * @date 2023-03-23 3:27:33
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据
     *
     * @author miaoqi
     * @date 2023-03-23 3:29:32
     *
     * @return
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满, 不能添加数据");
            return;
        }
        rear++; // rear 后移
        arr[rear] = n;
    }

    /**
     * 获取队列数据, 出队操作
     *
     * @author miaoqi
     * @date 2023-03-23 3:30:30
     *
     * @return
     */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过抛出异常处理
            throw new RuntimeException("队列空, 不能取出数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示队列的所有数据
     *
     * @author miaoqi
     * @date 2023-03-23 3:34:5
     *
     * @return
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列空的, 没有数据~~~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示队列头数据, 注意不取出数据
     *
     * @author miaoqi
     * @date 2023-03-23 3:36:15
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的, 没有数据~~~~");
        }
        return arr[front + 1];
    }

}