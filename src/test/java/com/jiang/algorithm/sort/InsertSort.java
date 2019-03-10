package com.jiang.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;



/**
 * @description: 插入排序算法实现
 * @author: jiangbug@outlook.com
 * @create: 2019-03-02 09:16
 */
public class InsertSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2,4,5,3,7,6,1,8};

//        int[] ints = insertSort(arr);
//        List<Integer> integers = Arrays.asList(ArrayUtils.toObject(ints));
//        integers.forEach(System.out::println);

        System.out.println();

        int[] ints1 = insertSort(arr, 2, 6);
        List<Integer> integers1 = Arrays.asList(ArrayUtils.toObject(ints1));
        integers1.forEach(System.out::println);

    }



    public static int[] insertSort(int[] ints){
        if( ints.length == 0 ){
            // 数组中没有任何元素
            return ints;
        }
        // 从数组的第二个元素开始做与前一个数组元素大小的比较，
        // 也即最外层循环，称为 i循环
        for( int i = 1 ; i < ints.length ; i++ ){
            // 当发现ints[i](数组中当前元素的值) < ints[i-1](它前面一个元素的值)，
            // 则进行从ints[0](原数组的开头)到 ints[i-1](当前元素前一个元素位置)的循环，查找ints[i](当前元素的插入位置)
            // 寻找当前元素的插入位置，称为 j循环
            if( ints[i] < ints[i-1] ){
                int tmp = ints[i];
                for( int j = 0; j < i ; j++ ){
                    // 找到在ints[0] ~ ins[i-1] 这个范围内比 ints[i]的值大的第一个数的下标，即ints[i]的插入位置
                    if( ints[j] > ints[i] ){
                        // j即ints[i]的插入位置，从下标 j ~ i-1这个区域的元素统统后移，为ints[i]的插入腾出位置
                        // 注：这里的后移需要从 i-1下标处开始，从后往前来
                        // ints[j] ~ ints[i-1]各自元素的下标 +1, 称为 k循环
                        for(int k = i; k > j; k--){
                            ints[k] = ints[k-1];
                        }
                        // 将ints[i]的值插入到腾出的位置上
                        ints[j] = tmp;
                        break;// 已经找到元素的插入位置，后面的循环无意义，break终止本次循环
                    }
                }
            }
        }
        return ints;
    }


    /**
     * 对数组中指定索引范围的数组元素进行排序
     * @param ints
     * @param startIndex
     * @param endIndex
     * @return
     *         startIndex -> 2  endIndex -> 6
     *         2,4,5,3,7,6,1,8  -> 2,4,1,3,5,6,7,8
     *             5,3,7,6,1
     * startIndex = 2; endIndex = 6
     * i =
     */
    public static int[] insertSort(int[] ints, int startIndex, int endIndex){
        if( ints.length == 0 || startIndex == endIndex ){
            // 数组中没有任何元素
            return ints;
        }
        // 从数组的第二个元素开始做与前一个数组元素大小的比较，
        // 也即最外层循环，称为 i循环
        for( int i = startIndex + 1 ; i < endIndex + 1; i++ ){
            // 当发现ints[i](数组中当前元素的值) < ints[i-1](它前面一个元素的值)，
            // 则进行从ints[0](原数组的开头)到 ints[i-1](当前元素前一个元素位置)的循环，查找ints[i](当前元素的插入位置)
            // 寻找当前元素的插入位置，称为 j循环
            if( ints[i] < ints[i-1] ){
                int tmp = ints[i];
                for( int j = startIndex; j < i ; j++ ){
                    // 找到在ints[0] ~ ins[i-1] 这个范围内比 ints[i]的值大的第一个数的下标，即ints[i]的插入位置
                    if( ints[j] > ints[i] ){
                        // j即ints[i]的插入位置，从下标 j ~ i-1这个区域的元素统统后移，为ints[i]的插入腾出位置
                        // 注：这里的后移需要从 i-1下标处开始，从后往前来
                        // ints[j] ~ ints[i-1]各自元素的下标 +1, 称为 k循环
                        for(int k = i; k > j; k--){
                            ints[k] = ints[k-1];
                        }
                        // 将ints[i]的值插入到腾出的位置上
                        ints[j] = tmp;
                        break;// 已经找到元素的插入位置，后面的循环无意义，break终止本次循环
                    }
                }
            }
        }
        return ints;
    }

}
