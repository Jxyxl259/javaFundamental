package com.jiang.datastructure.physical_storage.entity;

import java.util.Objects;

/**
 * @description: LinkedList中的节点类
 * @author: jiangbug@outlook.com
 * @create: 2019-03-03 10:40
 */
public class Node {

    // 本节点中存放的值
    Object value;

    // java中没有地址的概念，所以以对象的形式保存下一个节点的内存地址引用
    Node next;

    public Node(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue() {

        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value) &&
                Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, next);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
