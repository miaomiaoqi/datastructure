package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 *
 * @author miaoqi
 * @date 2023-03-24 0:49:44
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        //
        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        System.out.println("反转单链表~~");
        reversetList3(singleLinkedList.getHead());
        singleLinkedList.list();

        // System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        // reversePrint(singleLinkedList.getHead());


        // // 加入按照编号的顺序
        // singleLinkedList.addByOrder(hero1);
        // singleLinkedList.addByOrder(hero4);
        // singleLinkedList.addByOrder(hero2);
        // singleLinkedList.addByOrder(hero3);
        // singleLinkedList.addByOrder(hero3);
        //
        // // 显示一把
        // singleLinkedList.list();
        //
        // // // 测试修改节点的代码
        // HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        // singleLinkedList.update(newHeroNode);
        // //
        // System.out.println("修改后的链表情况~~");
        // singleLinkedList.list();
        // //
        // // //删除一个节点
        // singleLinkedList.del(1);
        // singleLinkedList.del(4);
        // System.out.println("删除后的链表情况~~");
        // singleLinkedList.list();
        // //
        // // 测试一下 求单链表中有效节点的个数
        // System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead())); // 2
        // // 测试一下看看是否得到了倒数第K个节点
        // HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
        // System.out.println("res=" + res);
    }


    // 方式2：
    // 可以利用栈这个数据结构, 将各个节点压入到栈中, 然后利用栈的先进后出的特点, 就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表, 不能打印
        }
        // 创建要给一个栈, 将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // cur 后移, 这样就可以压入下一个节点
        }
        // 将栈中的节点进行打印, pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); // stack 的特点是先进后出
        }
    }

    // 将单链表反转, 头插法
    public static void reversetList(HeroNode head) {
        // 如果当前链表为空, 或者只有一个节点, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助的指针(变量), 帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表, 每遍历一个节点, 就将其取出, 并放在新的链表 reverseHead 的最前端
        // 动脑筋
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点, 因为后面需要使用
            cur.next = reverseHead.next; // 将 cur 的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将 cur 连接到新的链表上
            cur = next; // 让 cur 后移
        }
        // 将 head.next 指向 reverseHead.next, 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 头插法
     *
     * @author miaoqi
     * @date 2023-03-30 2:29:35
     *
     * @return
     */
    public static void reversetList2(HeroNode head) {
        // 如果当前链表为空, 或者只有一个节点, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode newHead = null;
        HeroNode cur = head.next;
        while (cur != null) {
            HeroNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        head.next = newHead;
    }

    /**
     * 双指针法
     *
     * @author miaoqi
     * @date 2023-03-30 2:53:50
     *
     * @return
     */
    public static void reversetList3(HeroNode head) {
        // 如果当前链表为空, 或者只有一个节点, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode prev = head.next;
        HeroNode cur = head.next.next;
        while (cur != null) {
            HeroNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head.next.next = null;
        head.next = prev;
    }

    // 自己写的反转
    // public void reverse() {
    //     if (this.size == 0) {
    //         throw new IllegalArgumentException("linked is empty!");
    //     }
    //     // prev -> cur -> next
    //     // dummyHead -> node1 -> node2 -> node3 -> node4 -> NULL
    //     // NULL <- node1 <- node2 <- node3 <- node4 <- dummyHead
    //     // Node prev = this.dummyHead.next;
    //     // Node cur = prev.next;
    //     // while (cur != null) {
    //     //     Node next = cur.next;
    //     //     cur.next = prev;
    //     //     prev = cur;
    //     //     cur = next;
    //     // }
    //     // this.dummyHead.next.next = null;
    //     // this.dummyHead.next = prev;
    //
    //
    //     Node next = this.dummyHead.next;
    //     Node cur = next.next;
    //     while (cur != null) {
    //         Node prev = cur.next;
    //         cur.next = next;
    //         next = cur;
    //         cur = prev;
    //     }
    //
    //     this.dummyHead.next.next = null;
    //     this.dummyHead.next = next;
    // }

    // 超精简链表反转
    // public ListNode reverseList(ListNode head) {
    //     ListNode pre = null;
    //     while(head != null) {
    //         ListNode tmp = head.next;
    //         head.next = pre;
    //         pre = head;
    //         head = tmp;
    //     }
    //     return pre;
    // }

    // 查找单链表中的倒数第 k 个结点 【新浪面试题】
    // 1. 编写一个方法, 接收 head 节点, 同时接收一个index
    // 2. index 表示是倒数第 index 个节点
    // 3. 先把链表从头到尾遍历, 得到链表的总的长度 getLength
    // 4. 得到 size 后, 我们从链表的第一个开始遍历 (size-index)个, 就可以得到
    // 5. 如果找到了, 则返回该节点, 否则返回 nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断如果链表为空, 返回 null
        if (head.next == null) {
            return null; // 没有找到
        }
        // 第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        // 第二次遍历 size - index 位置, 就是我们倒数的第 K 个节点
        // 先做一个 index 的校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义给辅助变量, for 循环定位到倒数的 index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 巧妙求倒数第 k 个元素, 利用两个链表的差距求出
    // public ListNode getKthFromEnd(ListNode head, int k) {
    //     ListNode former = head;
    //     ListNode latter = head;
    //     while(k-- > 0){
    //         latter = latter.next;
    //     }
    //     while(latter != null) {
    //         former = former.next;
    //         latter = latter.next;
    //     }
    //     return former;
    // }


    // 方法：获取到单链表的节点的个数(如果是带头结点的链表, 需求不统计头节点)
    public static int getLength(HeroNode head) {
        if (head.next == null) { // 空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

// 定义 SingleLinkedList 管理英雄
class SingleLinkedList {

    // 初始化一个头结点, 不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return this.head;
    }

    // 添加节点到单向链表
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的 next 指向新的节点
    public void add(HeroNode heroNode) {
        // 因为 head 节点不能动, 因此我们需要一个辅助节点
        HeroNode temp = this.head;
        // 遍历链表找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后, temp 后移
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    // 第二种方式在添加英雄时, 根据排名将英雄插入到指定位置
    // (如果有这个排名, 则添加失败, 并给出提示)
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动, 因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为单链表, 因为我们找的temp 是位于 添加位置的前一个节点, 否则插入不了
        HeroNode temp = this.head;
        boolean flag = false; // flag标志添加的编号是否存在, 默认为false
        while (true) {
            if (temp.next == null) { // 说明 temp 已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到, 就在 temp 的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的 heroNode 的编号已然存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移, 遍历当前链表
        }
        // 判断 flag 的值
        if (flag) { // 不能添加, 说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            // 插入到链表中, temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 修改节点的信息, 根据 no 编号来修改, 即 no 编号不能改.
    // 1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        // 判断是否空
        if (this.head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据 no 编号
        // 定义一个辅助变量
        HeroNode temp = this.head.next;
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

    // 删除节点
    // 1. head 不能动, 因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
    // 2. 说明我们在比较时, 是 temp.next.no 和需要删除的节点的 no 比较
    public void del(int no) {
        HeroNode temp = this.head;
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp.next == null) { // 已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                // 找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            temp = temp.next; // temp 后移, 遍历
        }
        //判断flag
        if (flag) { //找到
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    // 显示列表
    public void list() {
        if (this.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = this.head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
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
        sb.append(", nickname='").append(this.nickname);
        sb.append('}');
        return sb.toString();
    }

}




