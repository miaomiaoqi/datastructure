package com.atguigu.linkedlist;

/**
 * 单向链表
 *
 * @author miaoqi
 * @date 2023-03-24 0:49:44
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

    }

}

// 定义 SingleLinkedList 管理英雄
class SingleLinkedList {

    // 初始化一个头结点, 不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的 next 指向新的节点
    public void add(HeroNode heroNode) {
        // 因为 head 节点不能动, 因此我们需要一个辅助节点
        HeroNode temp = this.head;
        // 遍历链表找到最后
        while (true) {
            
        }
    }

}

// 定义 HeroNode, 每个 HeroNode 对象就是一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方便, 我们重写 toString 方法
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HeroNode{");
        sb.append("no=").append(this.no);
        sb.append(", name='").append(this.name).append('\'');
        sb.append(", nickname='").append(this.nickname).append('\'');
        sb.append(", next=").append(this.next);
        sb.append('}');
        return sb.toString();
    }

}




