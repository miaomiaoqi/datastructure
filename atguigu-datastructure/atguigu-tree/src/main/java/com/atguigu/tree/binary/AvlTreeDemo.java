package com.atguigu.tree.binary;


/**
 * 平衡二叉树(AVL树)
 *
 * 给你一个数列{1,2,3,4,5,6}，要求创建一颗二叉排序树(BST), 并分析问题所在
 * 1. 左子树全部为空，从形式上看，更像一个单链表.
 * 2. 插入速度没有影响
 * 3. 查询速度明显降低(因为需要依次比较), 不能发挥BST的优势，因为每次还需要比较左子树，其查询速度比单链表还慢
 * 4. 解决方案-平衡二叉树(AVL)
 *
 * 基本介绍
 *
 * 1. 平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为AVL树， 可以保证查询效率较高。
 * 2. 具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL(算法)、替罪羊树、Treap、伸展树等。
 *
 * @author miaoqi
 * @date 2023-05-06 2:0:0
 */
public class AvlTreeDemo {

    public static void main(String[] args) {
        // int[] arr = {4,3,6,5,7,8}; // 左旋
        // int[] arr = { 10, 12, 8, 9, 7, 6 }; // 右旋
        int[] arr = {10, 11, 7, 6, 8, 9}; // 双旋
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AvlNode(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); // 4
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 1
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 3
    }

}

// 创建 AvlTree
class AvlTree {

    private AvlNode root;

    public AvlNode getRoot() {
        return this.root;
    }

    // 查找要删除的结点
    public AvlNode search(int value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.search(value);
        }
    }

    // 查找父结点
    public AvlNode searchParent(int value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.searchParent(value);
        }
    }

    // 编写方法:
    // 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
    // 2. 删除 node 为根结点的二叉排序树的最小结点

    /**
     *
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(AvlNode node) {
        AvlNode target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时 target就指向了最小结点
        // 删除最小结点
        this.delNode(target.value);
        return target.value;
    }

    // 删除结点
    public void delNode(int value) {
        if (this.root == null) {
            return;
        } else {
            // 1.需求先去找到要删除的结点 targetNode
            AvlNode targetNode = this.search(value);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉排序树只有一个结点
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
                return;
            }
            // 去找到targetNode的父结点
            AvlNode parent = this.searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode 是父结点的左子结点，还是右子结点
                if (parent.left != null && parent.left.value == value) { // 是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {// 是由子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
                int minVal = this.delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { // 删除只有一颗子树的结点
                // 如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { // targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        this.root = targetNode.left;
                    }
                } else { // 如果要删除的结点有右子结点
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { // 如果 targetNode 是 parent 的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        this.root = targetNode.right;
                    }
                }
            }
        }
    }

    // 添加结点的方法
    public void add(AvlNode node) {
        if (this.root == null) {
            this.root = node;// 如果root为空则直接让root指向node
        } else {
            this.root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

}

class AvlNode {

    int value;
    AvlNode left;
    AvlNode right;

    public AvlNode(int value) {
        this.value = value;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    // 返回当前节点的高度, 以该节点为根节点的这棵树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 怎么处理进行左旋转
     * 1. 创建一个新的节点 newNode, 值等于当前根节点的值
     * 2. 把新节点的左子树设置成当前节点的左子树 newNode.left = left
     * 3. 把新节点的右子树设置成当前节点的右子树的左子树 newNode.right = right.left
     * 4. 把当前节点的值换位右子节点的值 value = right.value
     * 5. 把当前节点的右子树设置成右子树的右子树 right = right.right
     * 6. 把当前节点的左子树设置成为新节点 left = newNode
     */
    // 左旋转方法
    private void leftRotate() {
        AvlNode newNode = new AvlNode(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;
    }

    /**
     * 怎么处理进行左旋转
     * 1. 创建一个新的节点 newNode, 值等于当前根节点的值
     * 2. 把新节点的右子树设置成当前节点的右子树 newNode.right = right
     * 3. 把新节点的座子树设置成当前节点的左子树的右子树 newNode.left = left.right
     * 4. 把当前节点的值换位左子节点的值 value = left.value
     * 5. 把当前节点的左子树设置成右子树的左子树 left = left.left
     * 6. 把当前节点的右子树设置成为新节点 right = newNode
     */
    // 左旋转方法
    private void rightRotate() {
        AvlNode newNode = new AvlNode(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    // 递归的形式添加节点, 注意需要满足二叉排序树的要求
    public void add(AvlNode node) {
        if (node == null) {
            return;
        }
        // 判断当前传入的节点的值, 和当前子树的根节点的值进行比较
        if (node.value < this.value) {
            // 如果当前节点的左子节点为 null
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 当添加完一个节点后, 如果:(右子树高度 - 左子树高度) > 1, 进行左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                // 先对右子结点进行右旋转
                this.right.rightRotate();
                // 然后在对当前结点进行左旋转
                this.leftRotate(); // 左旋转..
            } else {
                // 直接进行左旋转即可
                this.leftRotate();
            }
            return; //必须要!!!
        }

        // 当添加完一个节点后, 如果:(左子树高度 - 右子树高度) > 1, 进行左旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            // 问题分析
            // 1. 当符号右旋转的条件时
            // 2. 如果它的左子树的右子树高度大于它的左子树的高度
            // 3. 先对当前这个结点的左节点进行左旋转
            // 4. 在对当前结点进行右旋转的操作即可
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                this.left.leftRotate();
                //再对当前结点进行右旋转
                this.rightRotate();
            } else {
                //直接进行右旋转即可
                this.rightRotate();
            }
        }

    }

    // 查找要删除的结点

    /**
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public AvlNode search(int value) {
        if (value == this.value) { //找到就是该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            //如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除结点的父结点

    /**
     *
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public AvlNode searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Node{");
        sb.append("value=").append(this.value);
        sb.append('}');
        return sb.toString();
    }

}


