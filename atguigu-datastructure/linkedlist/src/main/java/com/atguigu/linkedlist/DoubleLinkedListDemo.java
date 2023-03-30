package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }

}

class DoubleLinkedList {

    // 初始化一个头结点, 不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return this.head;
    }

    // 显示列表
    public void list() {
        if (this.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = this.head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加节点到单向链表
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的 next 指向新的节点
    public void add(HeroNode2 heroNode) {
        // 因为 head 节点不能动, 因此我们需要一个辅助节点
        HeroNode2 temp = this.head;
        // 遍历链表找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后, temp 后移
            temp = temp.next;
        }
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.prev = temp;
    }


    // 修改节点的信息, 根据 no 编号来修改, 即 no 编号不能改.
    // 1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode2 newHeroNode) {
        // 判断是否空
        if (this.head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据 no 编号
        // 定义一个辅助变量
        HeroNode2 temp = this.head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { // 没有找到
            System.out.printf("没有找到 编号 %d 的节点, 不能修改\n", newHeroNode.no);
        }
    }

    public void del(int no) {

        // 判断当前链表是否为空
        if (this.head.next == null) {
            System.out.println("链表为空, 无法删除");
            return;
        }

        HeroNode2 temp = this.head.next;
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp == null) { // 已经到链表的最后
                break;
            }
            if (temp.no == no) {
                // 找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            temp = temp.next; // temp 后移, 遍历
        }
        //判断flag
        if (flag) { //找到
            //可以删除
            temp.prev.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

}

class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 prev; // 指向前一个节点

    // 构造器
    public HeroNode2(int no, String name, String nickname) {
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
        sb.append(", nickname='").append(this.nickname);
        sb.append('}');
        return sb.toString();
    }

}
