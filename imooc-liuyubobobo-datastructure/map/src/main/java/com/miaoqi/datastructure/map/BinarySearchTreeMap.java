package com.miaoqi.datastructure.map;

public class BinarySearchTreeMap<K extends Comparable, V> implements Map<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    private Node root;
    private int size;

    public BinarySearchTreeMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        this.root = add(this.root, key, value);
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

    @Override
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

    @Override
    public boolean contains(K key) {
        return getNode(this.root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(this.root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(this.root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
