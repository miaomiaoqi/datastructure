package com.miaoqi.datastructure.tree.bst;


import java.util.*;

/**
 * 二分搜索树:
 * 二分搜索树是二叉树, 二分搜索树具备所有二叉树的特性
 *
 * 特殊:
 * 二分搜索树的每个节点的值大于其左子树的所有节点的值, 每个节点的值小于其右子树的所有节点的值
 * 每一颗子树也是二分搜索树
 * 存储的元素必须有可比较性
 *
 * 深度优先: 前中后序遍历
 * 广度优先: 层序遍历
 *
 * @author miaoqi
 * @date 2019-06-16
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        private E e;
        /**
         * 左孩子
         */
        private Node left;
        /**
         * 右孩子
         */
        private Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 向二分搜索树中添加元素 e
     */
    public void add(E e) {
        // if (this.root == null) {
        //     this.root = new Node(e);
        //     this.size++;
        // } else {
        //     add(root, e);
        // }
        // 改进版
        this.root = add(this.root, e);
    }

    /**
     * 向以 root 为根的二分搜索树中插入元素
     */
    // private void add(Node root, E e) {
    //     if (e.compareTo(root.e) == 0) {
    //         return;
    //     }
    //     if (e.compareTo(root.e) < 0) {
    //         if (root.left != null) {
    //             add(root.left, e);
    //         } else {
    //             root.left = new Node(e);
    //             this.size++;
    //         }
    //     } else {
    //         if (root.right != null) {
    //             add(root.right, e);
    //         } else {
    //             root.right = new Node(e);
    //             this.size++;
    //         }
    //     }
    // }


    /**
     * 向以 root 为根的二分搜索树中插入元素
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param root
     * @param e
     * @return
     */
    private Node add(Node root, E e) {
        if (root == null) {
            this.size++;
            return new Node(e);
        }

        if (e.compareTo(root.e) < 0) {
            root.left = add(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            root.right = add(root.right, e);
        }
        return root;
    }

    /**
     * 查看树中是否包含元素 e
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(this.root, e);
    }

    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }
        if (e.compareTo(root.e) < 0) {
            return contains(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            return contains(root.right, e);
        }
        return true;
    }

    /**
     * 前序遍历, 中左右
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public void preOrder() {
        this.preOrder(this.root);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历, 左中右
     * 中序遍历的结果就是所有节点排序的结果
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public void inOrder() {
        this.inOrder(this.root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.e);
        inOrder(root.right);
    }

    /**
     * 后序遍历, 左右中
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public void postOrder() {
        this.postOrder(this.root);
    }

    private void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.e);
    }

    /**
     * 利用栈, 实现非递归前序遍历
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public void preOrderNotRecursion() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树利用队列实现广度优先遍历
     *
     * 更快找到问题的解 - 最短路径
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.print(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找树中最小的元素
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E minimum() {
        if (this.size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(this.root).e;
    }

    /**
     * 以 root 为根节点查找最小的节点
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param root
     * @return
     */
    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    /**
     * 寻找树中最大的元素
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E maximum() {
        if (this.size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(this.root).e;
    }

    private Node maximum(Node root) {
        if (root.right == null) {
            return root;
        }
        return maximum(root.right);
    }

    /**
     * 从二分搜索树中删除最小值所在的节点, 并且返回最小值
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        this.root = removeMin(this.root);
        return ret;
    }

    /**
     * 删除掉以 node 为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param root
     * @return
     */
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


    /**
     * 从二分搜索树中删除最大值所在的节点, 并且返回最大值
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        this.root = removeMax(this.root);
        return ret;
    }

    /**
     * 删除掉以 node 为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param root
     * @return
     */
    private Node removeMax(Node root) {
        if (root.right == null) {
            Node leftNode = root.left;
            root.left = null;
            this.size--;
            return leftNode;
        }
        root.right = removeMax(root.right);
        return root;
    }

    /**
     * 从二分搜索树中删除元素为 e 的节点
     * 1. 如果只有左子树或右子树只需要直接替代就可以
     * 2. 如果要删除左右节点都有孩子的节点 d(Hibbard 提出的 Hibbard Deletion)
     *  2.1 找到 s = min(d -> right) 找到 d 节点的右子树中最小的元素 s
     *  2.2 s -> right = delMin(d -> right) s 的右子树是 d 的右子树删除最小元素后的树
     *  2.3 s -> left = d -> left s 的左子树就是 d 的左子树, 没有变化
     *  2.4 删除 d, s 是新的根
     *
     * @author miaoqi
     * @date 2019-06-17
     * @param e
     * @return
     */
    public void remove(E e) {
        this.root = this.remove(this.root, e);
    }

    private Node remove(Node root, E e) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.e) > 0) {
            root.right = remove(root.right, e);
            return root;
        } else if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
            return root;
        } else {
            // e == root.e, delete root
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
            // Hibbard Deletion
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(root.right);
            successor.left = root.left;
            successor.right = removeMin(root.right);
            root.left = null;
            root.right = null;
            return successor;
        }
    }

    public static void main(String[] args) {
        // test1();
        test2();
    }

    public static void test2() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();

        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }

        System.out.println(nums);
    }

    public static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////
        System.out.println("前序遍历");
        bst.preOrder();
        System.out.println();
        System.out.println("非递归前序遍历");
        bst.preOrderNotRecursion();
        System.out.println();
        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();
        System.out.println("后序遍历");
        bst.postOrder();
        System.out.println();
        System.out.println("层序遍历");
        bst.levelOrder();
        System.out.println();
        System.out.println("最小元素");
        System.out.print(bst.minimum());
        System.out.println();
        System.out.println("最大元素");
        System.out.print(bst.maximum());
        System.out.println();
    }
}
