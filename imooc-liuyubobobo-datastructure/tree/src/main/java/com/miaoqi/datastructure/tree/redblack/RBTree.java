package com.miaoqi.datastructure.tree.redblack;

/**
 * Red-Black-Tree
 *
 * @author miaoqi
 * @date 2019-06-25
 */
public class RBTree<K extends Comparable, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        public boolean color;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = RED;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 左旋转
     */
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.left;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色翻转
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向红黑树中添加新的元素
     */
    public void add(K key, V value) {
        this.root = add(this.root, key, value);
        this.root.color = BLACK;
    }

    /**
     * 向以 root 为根的红黑树中添加新的节点(key, value), 递归算法
     * 返回插入新节点后红黑树的根
     */
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
        return root;
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

    public V remove(K key) {
        Node retNode = getNode(this.root, key);
        if (retNode != null) {
            this.root = this.remove(this.root, key);
            return retNode.value;
        }
        return null;
    }

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

    public boolean contains(K key) {
        return getNode(this.root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(this.root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(this.root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}