package com.miaoqi.datastructure.liner.stack02;

/**
 * 栈
 */
public interface Stack<E> {

    /**
     * 压栈
     */
    void push(E e);

    /**
     * 弹栈
     */
    E pop();

    /**
     * 取栈顶元素但不弹栈
     */
    E peek();

    /**
     * 获取元素个数
     */
    int getSize();

    /**
     * 判断是否为空
     */
    boolean isEmpty();
}
