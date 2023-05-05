package com.atguigu.tree.binary;

/**
 * 顺序存储二叉树
 *
 * 顺序存储二叉树的特点:
 *
 * 顺序二叉树通常只考虑完全二叉树
 * 第n个元素的左子节点为  2 * n + 1
 * 第n个元素的右子节点为  2 * n + 2
 * 第n个元素的父节点为  (n-1) / 2
 *
 * n : 表示二叉树中的第几个元素(按0开始编号如图所示)
 *
 * @author miaoqi
 * @date 2023-05-03 23:50:50
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }

}

// 编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
class ArrBinaryTree {

    private int[] arr;//存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    // 编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空，或者 arr.length = 0
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(this.arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < this.arr.length) {
            this.preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < this.arr.length) {
            this.preOrder(2 * index + 2);
        }
    }

}
