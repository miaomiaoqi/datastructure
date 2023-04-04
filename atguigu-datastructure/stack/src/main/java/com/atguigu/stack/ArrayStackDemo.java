package com.atguigu.stack;

import java.util.Scanner;

/**
 * 使用数组实现栈
 *
 * @author miaoqi
 * @date 2023-04-01 1:7:27
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        // 测试一下 ArrayStack 是否正确
        // 先创建一个 ArrayStack 对象 -> 表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }

}

// 定义一个 ArrayStack 表示栈
class ArrayStack {

    private int maxSize; // 栈的大小
    private int[] stack; // 数组模拟栈, 数据就存放在该数组中
    private int top = -1; // 表示栈顶的位置

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    // 判断栈满
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return this.top == -1;
    }

    // 入栈-push
    public void push(int value) {
        // 先判断栈是否满
        if (this.isFull()) {
            System.out.println("栈满");
            return;
        }
        this.top++;
        this.stack[this.top] = value;
    }

    // 出栈
    public int pop() {
        if (this.isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空, 没有数据");
        }
        int value = this.stack[this.top];
        this.top--;
        return value;
    }

    // 显示栈的情况, 需要从栈顶显示数据
    public void list() {
        if (this.isEmpty()) {
            System.out.println("没有数据");
            return;
        }
        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, this.stack[i]);
        }
    }

}
