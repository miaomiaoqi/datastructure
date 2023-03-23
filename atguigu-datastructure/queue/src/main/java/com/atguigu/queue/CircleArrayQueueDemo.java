package com.atguigu.queue;

import java.util.Scanner;

/**
 * 数组实现环型队列
 *
 * @author miaoqi
 * @date 2023-03-23 23:54:25
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        System.out.println("测试数组模拟环型队列~~~~");

        CircleArrayQueue queue = new CircleArrayQueue(3);
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

class CircleArrayQueue {

    private int maxSize; // 表示最大容量
    private int front; // 队列头, front 就指向队列的第一个元素, 初始值为 0
    private int rear; // 队列尾, 指向队列最后一个元素的后一个位置, 初始值为 0
    private int[] arr; // 该数组用于存放数据, 模拟队列

    // 创建队列构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; // 指向队列头部, 分析出 front 是指向队列头的前一个位置, 不是队列头的真正位置
        rear = 0; // 指向队列尾部, 指向队列尾的数据(即就是队列的最后一个数据)
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满, 不能添加数据");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将 rear 后移
        rear = (rear + 1) % maxSize;
    }


    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过抛出异常处理
            throw new RuntimeException("队列空, 不能取出数据");
        }
        // 这里需要分析出 front 是指向队列的第一个元素
        // 1. 先把 front 对象的值保存到临时变量
        int value = arr[front];
        // 2. 将 front 后移
        front = (front + 1) % maxSize;
        // 3. 将临时保存变量返回
        return value;
    }


    public void show() {
        if (isEmpty()) {
            System.out.println("队列空的, 没有数据~~~~");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的, 没有数据~~~~");
        }
        return arr[front];
    }

}









