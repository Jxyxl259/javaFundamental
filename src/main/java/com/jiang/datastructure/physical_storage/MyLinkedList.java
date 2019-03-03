package com.jiang.datastructure.physical_storage;

import com.jiang.datastructure.physical_storage.entity.Node;

/**
 * @description: LinkedList简单实现
 * @author: jiangbug@outlook.com
 * @create: 2019-03-03 10:40
 */
public class MyLinkedList {


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.add(1);
        list.add(3);
        list.add(6);
        list.add(5);
        list.add(2);
        list.add(11);
        list.add(0);

        list.add(12,66);

        list.set(0,5);
        //list.del(7);

        for(int i = 0 ; i < list.size() ; i ++){
            System.out.println(list.get(i));
        }

    }

    // 链表中节点的个数
    int size;

    // 链表中最重要的是头节点，只要找到头节点就能顺藤摸瓜找到后续节点
    Node start;


    public int size(){
        return this.size;
    }

    /**
     * 向链表尾部追加一个新的节点
     * 由第一个节点不断的向后面去寻找，这里引入指针的概念，所谓指针即当前节点，
     * 当当前节点的下一个节点为 null时，说明当前节点是链表的最后一个节点，
     * 直接调用当前节点的setNext()方法追加节点即可
     * <see>向链表中添加元素.png</see>
     * @param value
     */
    public void add(Object value){
        Node node = new Node(value);
        if(this.start != null){
            // 创建一个指针节点
            Node current = this.start;
            // 指针节点如果有下一个节点，说明指针节点不是还不是最后一个节点
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(node);
        }else{
            this.start = node;
        }
        size++;
    }

    /**
     * 向链表中间指定位置处插入一个节点
     * @param index
     */
    public void add(int index, Object value){
        Node node = new Node(value);
        // 如果当前链表中没有节点，则初始化头节点，
        if(this.size == 0){
            this.start = node;
            size++;
            return;
        }
        // 先判断插入位置与 当前链表中节点个数 size的大小关系
        // 直接将链表的头部节点替换
        if(index == 0){
            node.setNext(this.start);
            this.start = node;
            return;
        }

        // 直接将节点插入到链表的尾部
        if(index >= this.size){
            this.add(value);
            return;
        }else{
            Node next = this.start;
            while((index-1) > 0){
                next = next.getNext();

                index --;
            }
            node.setNext(next.getNext());
            next.setNext(node);
        }
    }


    /**
     * 获取链表中指定节点的值
     * @param index
     * @return
     */
    public Object get(int index){
        Node node = this.start;
        while(index > 0){
            index--;
            node = node.getNext();
        }
        return node.getValue();
    }


    /**
     * 设置链表中指定节点的值
     * @param index
     * @param value
     * @return
     */
    public void set(int index, Object value){
        Node node = this.start;
        while(index > 0){
            index--;
            node = node.getNext();
        }
        node.setValue(value);
    }


    /**
     * 删除指定位置的节点
     * @param index
     */
    public void del(int index){
        Node node = this.start;

        // 删除第一个节点
        if(index == 0) {
            this.start = start.getNext();
            this.size--;
            return;
        }

        while((index - 1)> 0){
            node = node.getNext();
            index--;
        }
        Node reserve = node.getNext().getNext();
        node.setNext(reserve);
        this.size--;
    }

    /**
     * 清空链表
     */
    public void clearAll(){
        this.start = null;
        this.size = 0;
    }
}
