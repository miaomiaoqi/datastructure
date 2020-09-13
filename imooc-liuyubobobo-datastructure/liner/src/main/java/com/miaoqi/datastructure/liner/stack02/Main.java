package com.miaoqi.datastructure.liner.stack02;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * 测试使用 stack 运营 opCount 个 push 和 pop 操作所需的时间, 单位: 秒
     */
    private static void testStack(Stack<Integer> stack, int opCount) {
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
    }

    /**
     * 比较 ArrayStack 和 LinkedListStack 性能差异
     *
     * @author miaoqi
     * @date 2019-06-15
     * @param args
     * @return
     */
    public static void main(String[] args) {
        int opCount = 1000000;

        StopWatch sw = new StopWatch();

        sw.start();
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        testStack(arrayStack, opCount);
        sw.stop();
        System.out.println("ArrayStack, time " + sw.getTime(TimeUnit.MILLISECONDS) + "s");

        sw.reset();
        sw.start();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        testStack(linkedListStack, opCount);
        sw.stop();
        System.out.println("LinkedListStack, time " + sw.getTime(TimeUnit.MILLISECONDS) + "s");
    }
}
