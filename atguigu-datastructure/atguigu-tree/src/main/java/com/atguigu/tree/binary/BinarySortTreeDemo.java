package com.atguigu.tree.binary;

/**
 * 二叉排序树
 *
 * 使用数组
 * 1. 数组未排序， 优点：直接在数组尾添加，速度快。 缺点：查找速度慢. [示意图]
 * 2. 数组排序，优点：可以使用二分查找，查找速度快，缺点：为了保证数组有序，在添加新数据时，找到插入位置后，后面的数据需整体移动，速度慢。[示意图]
 * 3. 使用链式存储-链表不管链表是否有序，查找速度都慢，添加数据速度比数组快，不需要数据整体移动。[示意图]
 *
 * 二叉排序树：BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
 *
 * @author miaoqi
 * @date 2023-05-05 15:2:0
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加节点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        // 1, 3, 5, 7, 9, 10, 12
        binarySortTree.infixOrder();
    }

}

class BinarySortTree {

    private Node root;

    // 查找要删除的结点
    public Node search(int value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.search(value);
        }
    }

    // 查找父结点
    public Node searchParent(int value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.searchParent(value);
        }
    }

    // 编写方法:
    // 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
    // 2. 删除node 为根结点的二叉排序树的最小结点

    /**
     *
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时 target 就指向了最小结点
        // 删除最小结点
        this.delNode(target.value);
        return target.value;
    }


    // 删除结点

    /**
     *
     * 二叉排序树的删除情况比较复杂，有下面三种情况需要考虑
     *
     *             7
     *         3       10
     *      1    5  9     12
     *        2
     *
     * 删除叶子节点 (比如：2, 5, 9, 12)
     * 删除只有一颗子树的节点 (比如：1)
     * 删除有两颗子树的节点. (比如：7, 3，10 )
     *
     * 第一种情况:
     * 删除叶子节点 (比如：2, 5, 9, 12)
     * 思路
     * 1. 需求先去找到要删除的结点  targetNode
     * 2. 找到targetNode 的 父结点 parent
     * 3.  确定 targetNode 是 parent的左子结点 还是右子结点
     * 4. 根据前面的情况来对应删除
     * 左子结点 parent.left = null
     * 右子结点 parent.right = null;
     *
     * 第二种情况: 删除只有一颗子树的节点 比如 1
     * 思路
     * 1. 需求先去找到要删除的结点  targetNode
     * 2. 找到targetNode 的 父结点 parent
     * 3. 确定targetNode 的子结点是左子结点还是右子结点
     * 4. targetNode 是 parent 的左子结点还是右子结点
     * 5. 如果targetNode 有左子结点
     * 5.1 如果 targetNode 是 parent 的左子结点
     * parent.left = targetNode.left;
     * 5.2 如果 targetNode 是 parent 的右子结点
     * parent.right = targetNode.left;
     * 6. 如果targetNode 有右子结点
     * 6.1 如果 targetNode 是 parent 的左子结点
     * parent.left = targetNode.right;
     * 6.2 如果 targetNode 是 parent 的右子结点
     * parent.right = targetNode.right
     *
     * 情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
     * 思路
     * 1. 需求先去找到要删除的结点  targetNode
     * 2. 找到targetNode 的 父结点 parent
     * 3. 从targetNode 的右子树找到最小的结点
     * 4. 用一个临时变量，将 最小结点的值保存 temp = 11
     * 5. 删除该最小结点
     * 6. targetNode.value = temp
     *
     * @author miaoqi
     * @date 2023-05-05 16:54:16
     *
     * @return
     */
    public void delNode(int value) {
        if (this.root == null) {
            return;
        } else {
            // 1.需求先去找到要删除的结点  targetNode
            Node targetNode = this.search(value);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
                return;
            }
            // 去找到 targetNode 的父结点
            Node parent = this.searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断 targetNode 是父结点的左子结点，还是右子结点
                if (parent.left != null && parent.left.value == value) { //是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是由子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { //删除有两颗子树的节点
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

    // 添加节点的方法s
    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉排序树为空, 不能遍历");
        }
    }

}

class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 递归的形式添加节点, 注意需要满足二叉排序树的要求
    public void add(Node node) {
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
    }

    // 查找要删除的结点

    /**
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
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
    public Node searchParent(int value) {
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
