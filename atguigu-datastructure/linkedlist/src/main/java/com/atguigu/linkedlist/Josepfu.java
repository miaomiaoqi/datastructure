package com.atguigu.linkedlist;

/**
 * 约瑟夫问题
 *
 * Josephu  问题为：设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数
 * 数到 m 的那个人出列，它的下一位又从1开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * n = 5 , 即有 5 个人
 * k = 1, 从第一个人开始报数
 * m = 2, 数 2 下
 *
 * @author miaoqi
 * @date 2023-03-31 1:31:56
 */
public class Josepfu {

    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确
        // circleSingleLinkedList.countBoy(10, 20, 125); // 2->4->1->5->3
        //String str = "7*2*2-5+1-5+3-3";
    }

}

// 创建一个环形单向链表
class CircleSingleLinkedList {

    // 创建一个 first 节点, 当前没有编号
    private Boy first = new Boy(-1);

    // 添加小孩节点, 构建一个环形链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针, 帮助构建环形链表
        // 使用 for 循环创建环境链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号, 创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                this.first = boy;
                this.first.setNext(this.first); // 构成一个环状
                curBoy = this.first;
                continue;
            }
            curBoy.setNext(boy);
            boy.setNext(this.first);
            curBoy = boy;
        }
    }

    // 遍历当前环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (this.first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        // 因为 first 不能动, 因此我们使用一个辅助指针完成遍历
        Boy cur = this.first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            if (cur.getNext() == this.first) { // 说明遍历完毕
                break;
            }
            cur = cur.getNext();
        }
    }


    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (this.first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Boy helper = this.first;
        // 需求创建一个辅助指针(变量)helper, 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == this.first) { // 说明 helper 指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让 first 和  helper 移动 k - 1 次, 即将 first 移动到 startNum 处, helper 是 startNum 的前一个位置
        for (int j = 0; j < startNo - 1; j++) {
            this.first = this.first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时，让 first 和 helper 指针同时 的移动 m - 1 次, 然后出圈
        // 这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == this.first) { // 说明圈中只有一个节点
                break;
            }
            // 让 first 和 helper 指针同时 的移动 countNum - 1, 移动 first 到要出圈的小孩的位置, helper 到要出圈的小孩的前一个位置
            for (int j = 0; j < countNum - 1; j++) {
                this.first = this.first.getNext();
                helper = helper.getNext();
            }
            // 这时 first 指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", this.first.getNo());
            // 这时将 first 指向的小孩节点出圈
            this.first = this.first.getNext();
            helper.setNext(this.first); //

        }
        System.out.printf("最后留在圈中的小孩编号%d \n", this.first.getNo());
    }

}

// 创建一个 Boy 类, 表示一个节点
class Boy {

    private int no; // 编号
    private Boy next; // 指向下一个节点, 默认 null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return this.next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
