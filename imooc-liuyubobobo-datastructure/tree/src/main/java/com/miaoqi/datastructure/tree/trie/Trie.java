package com.miaoqi.datastructure.tree.trie;

import com.miaoqi.datastructure.tree.bst.BinarySearchTree;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 字典树, 前缀树, 专门处理字符串, 空间消耗比较大
 *
 * @author miaoqi
 * @date 2019-06-24
 */
public class Trie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * 获得 trie 中存储的单词数量
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 向 Trie 中添加一个新的单词 word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            this.size++;
        }
    }

    /**
     * 查询单词 word 是否在 trie 树中
     */
    public boolean contains(String word) {
        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询在 Trie 中是否有单词以 prefix 为前缀
     */
    public boolean isPrefix(String prefix) {
        Node cur = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Projudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("asnofmaos");
            long startTime = System.nanoTime();

            BinarySearchTree<String> set = new BinarySearchTree<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");
        }
    }

}
