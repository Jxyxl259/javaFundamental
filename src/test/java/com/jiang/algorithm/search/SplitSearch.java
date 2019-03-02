package com.jiang.algorithm.search;

import com.jiang.algorithm.sort.InsertSort;

/**
 * @description: 二分查找算法实现 二分查找是针对排好序的数组进行的查找，而非无序，所以使用二分查找需要先对数组进行排序！！！
 * @author: jiangbug@outlook.com
 * @create: 2019-03-02 11:04
 */
public class SplitSearch {

    public static void main(String[] args) {
        int[] ints = new int[]{4,2,7,10,6,3,9,5}; // length = 8

        // 二分查找是针对排好序的数组进行的，所以需要先对数组进行排序
        int[] sortedInts = InsertSort.insertSort(ints);

        Object targetIndex = splitSearch(sortedInts, 0, ints.length - 1, 9);
        System.out.println(targetIndex == null ? "数组中不存在该值" : "数组中存在值为：" + ints[Integer.valueOf(targetIndex.toString())] + "的数，索引下标为：" + targetIndex.toString());
    }


    /**
     * 使用二分查找法，找出目的元素
     * 用到递归算法
     * @param ints 查找数组
     * @param startIndex 查找数组的起始索引
     * @param endIndex 查找数组的结束索引
     * @param target 查找目标
     * @return
     */
    public static Object splitSearch(int[] ints, int startIndex, int endIndex, int target){

        // 拿到一个给定的数组，g数组的长度
        int len = ints.length;

        if( startIndex > endIndex ){
            // 如果出现要查找的数组的起始索引 > 结束索引时 则说明要找的元素不存在
            return null;
        }

        // 计算数组 中间元素的下标（不用考虑数组中元素的个数的奇偶性）
        int mediumIndex = ( startIndex + endIndex ) /2;

        // 数组中间索引处的值正好是要找的值，找到直接返回
        if(ints[mediumIndex] == target){
            return mediumIndex;
        // 要查找的值落在数组的左侧分支上
        }else if( target < ints[mediumIndex] ){
            // 落在坐标，则起始下标不变
//            startIndex = startIndex;
            endIndex = mediumIndex - 1;
            return splitSearch(ints, startIndex, endIndex, target);
        }else{
            startIndex = mediumIndex +1;
            // 落在右边则结束下标不变
//            endIndex = endIndex;
            return splitSearch(ints, startIndex, endIndex, target);
        }

    }


}
