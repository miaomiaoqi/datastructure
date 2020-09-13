package com.miaoqi.datastructure.tree.bt;

/**
 * 二叉树:
 * 二叉树具有唯一的根节点
 * 二叉树每个节点最多有两个孩子
 * 没有子节点的节点称为叶子节点
 * 二叉树每个节点最多有一个父节点
 *
 * 满二叉树:
 * 除了叶子节点, 所有节点的左右孩子均不为空, 且高度(h)相同
 *
 * h 层共有 2^h - 1 = n 个节点, 2^h = n + 1  -->  log2(n+1) = h  -->  log(n)
 *
 * 完全二叉树
 *
 * @author miaoqi
 * @date 2019-06-15
 */
public class BinaryTree {
}

            // logn        n
// n = 16       4           16
// n = 1024     10         1024
// n = 100万    20         100万