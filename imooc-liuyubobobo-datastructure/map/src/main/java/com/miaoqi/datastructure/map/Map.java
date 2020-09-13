package com.miaoqi.datastructure.map;

/**
 * 存储(键, 值)数据对的数据结构(Key, Value)
 * 根据键(Key), 寻找值(Value)
 *
 * @author miaoqi
 * @date 2019-06-17
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V value);

    int getSize();

    boolean isEmpty();

}
