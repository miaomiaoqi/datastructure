package com.miaoqi.datastructure.liner.linked04;

public class LinkedList<E> {

    private class Node {
        private E e;
        private Node next;

        private Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        private Node(E e) {
            this(e, null);
        }

        private Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 真正的头节点前, 添加一个虚拟头节点, 方便统一操作
     */
    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    /**
     * 获取列表中的元素个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 返回链表是否为空
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 在链表的 index 位置(0-based)位置添加一个元素e
     * 在链表中不是一个常用操作, 练习用
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // Node node = new Node(e);
        // node.next = prev.next;
        // prev.next = node;
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加新的元素 e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取链表的第 index(0-based) 个位置的元素
     * 在链表中不是一个常用的操作
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = this.dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表中第 index(0-based)位置的元素
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = this.dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素 e
     */
    public boolean contains(E e) {
        Node cur = this.dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 从链表中删除 index(0-based)位置的元素, 返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    /**
     * 从链表中删除第一个元素, 返回删除的元素
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从链表中删除最后一个元素, 返回删除的元素
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素 e
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param e
     * @return
     */
    public void removeElement(E e) {
        Node prev = this.dummyHead;
        while (prev.next != null) {
            if (e.equals(prev.next.e)) {
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    /**
     * 单向链表反转
     */
    public void reverse() {
        if (this.size == 0) {
            throw new IllegalArgumentException("linked is empty!");
        }
        // prev -> cur -> next
        // dummyHead -> node1 -> node2 -> node3 -> node4 -> NULL
        // NULL <- node1 <- node2 <- node3 <- node4 <- dummyHead
        // Node prev = this.dummyHead.next;
        // Node cur = prev.next;
        // while (cur != null) {
        //     Node next = cur.next;
        //     cur.next = prev;
        //     prev = cur;
        //     cur = next;
        // }
        // this.dummyHead.next.next = null;
        // this.dummyHead.next = prev;


        Node next = this.dummyHead.next;
        Node cur = next.next;
        while (cur != null) {
            Node prev = cur.next;
            cur.next = next;
            next = cur;
            cur = prev;
        }

        this.dummyHead.next.next = null;
        this.dummyHead.next = next;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this.dummyHead.next;
        while (cur != null) {
            sb.append(cur);
            sb.append(" -> ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
        }
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println(linkedList);
        // linkedList.add(2, 666);
        // System.out.println(linkedList);
        //
        // linkedList.remove(2);
        // System.out.println(linkedList);
        //
        // linkedList.removeFirst();
        // System.out.println(linkedList);
        //
        // linkedList.removeLast();
        // System.out.println(linkedList);
    }
}
