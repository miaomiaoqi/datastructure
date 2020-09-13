package com.miaoqi.datastructure.tree.avl;

import java.util.ArrayList;

/**
 * avl 树在二分搜索树的基础上, 添加了高度, 达到一种相对宽松的平衡状态
 * 改进二分搜索树退换成链表的问题, 引入了平衡因子的概念
 *
 * @author miaoqi
 * @date 2019-06-25
 */
public class AVLTree<K extends Comparable, V> {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 判断该二叉树是否是一颗二分搜索树
     * 中序遍历是有序的
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(this.root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node root, ArrayList<K> keys) {
        if (root == null) {
            return;
        }
        inOrder(root.left, keys);
        keys.add(root.key);
        inOrder(root.right, keys);
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    /**
     * 判断以 root 为根节点的二叉树是否是一颗平衡二叉树, 递归算法
     */
    private boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 获得节点 node 的平衡因子, 左右子树高度差的绝对值
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add(K key, V value) {
        this.root = this.add(this.root, key, value);
    }

    private Node add(Node root, K key, V value) {
        if (root == null) {
            this.size++;
            return new Node(key, value);
        }
        if (key.compareTo(root.key) > 0) {
            root.right = add(root.right, key, value);
        } else if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else {
            root.value = value;
        }

        // --------------------- AVL -----------------------
        // 更新 height
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }
        // 平衡维护, 当新加入节点后, 平衡可能被打破, 被打破平衡节点的左侧的左侧插入元素(LL), 进行右旋转操作
        // LL 进行右旋
        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            // 代表左子树打破了平衡, 要进行右旋转操作
            return rightRotate(root);
        }
        // 被打破平衡节点的右侧的右侧插入元素(RR), 进行左旋转操作
        // RR 进行左旋
        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }
        // LR 先对左孩子进行左旋
        if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // RL 先对右孩子进行右旋, 再对该节点进行左旋
        if (balanceFactor < -1 && getBalanceFactor(root.left) > 0) {
            root.right = rightRotate(root.right);
            return rightRotate(root);
        }
        // --------------------- AVL -----------------------
        return root;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    /**
     * 右旋操作
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 右旋过程
        x.right = y;
        y.left = T3;
        // 维护 x 和 y 的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node getNode(Node root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) == 0) {
            return root;
        } else if (key.compareTo(root.key) < 0) {
            return getNode(root.left, key);
        } else {
            return getNode(root.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(this.root, key) != null;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        return node.value;
    }

    /**
     * 删除方法是找到被删除节点的右子树中的最小节点替换当前节点(或左子树中最大的节点)
     */
    private Node remove(Node root, K key) {
        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
            return root;
        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                this.size--;
                return rightNode;
            }
            if (root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                this.size--;
                return leftNode;
            }
            // 1. 找到右子树中最小的节点
            Node successor = minimum(root.right);
            // 2. 删除右子树中最小的节点
            successor.right = removeMin(root.right);
            successor.left = root.left;
            root.left = null;
            root.right = null;
            return successor;
        }
    }

    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            Node rightNode = root.right;
            root.right = null;
            this.size--;
            return rightNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("avl/pride-and-prejudice.txt", words)) {
            System.out.println("Total words" + words.size());
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST: " + map.isBST());
            System.out.println("is Balanced: " + map.isBalanced());
        }
    }

}
