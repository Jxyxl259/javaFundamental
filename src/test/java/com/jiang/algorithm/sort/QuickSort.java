package com.jiang.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 快速排序算法实现
 * @author:
 * @create: 2019-03-08 12:56
 */
public class QuickSort {

    //
    public static void main(String[] args) {

        int[] arr = new int[10000];
        for(int i = 0; i<arr.length ; i++){
            arr[i] = (int)(Math.random()*10000) +1;
        }

        // 性能测试 对10W个随机数
        // 冒泡
//        long start = System.currentTimeMillis();
//        for(int i = 0 ; i <arr.length ; i++ ) {
//            for(int j = 0 ; j < arr.length -i -1; j++){ // 注意是内层循环中两个相邻的元素相比较, 所以下标必须是 j 和 j+1, 因为下标为j+1所以内层循环条件为arr.length -i -1
//                if(arr[j] > arr[j+1]){
//                    int tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                }
//            }
//        }
//        System.out.println("冒泡耗时------------>"+(System.currentTimeMillis()-start)/1000D + "秒");


        // 快速排序
//        long start = System.currentTimeMillis();
//        arr = splitSort(arr, 0, arr.length-1);
//        System.out.println("快排耗时------------>"+(System.currentTimeMillis()-start)/1000D + "秒");



        int[] nums = new int[]{-1,-3,-2,-6,-7,-8,7,-4,9,1,6,8,0};
        nums = splitSort(nums, 0, nums.length-1);

        Integer[] numArr = ArrayUtils.toObject(nums);
        List<Integer> numList = Arrays.asList(numArr);
        numList.forEach(System.out::println);
    }


    // 给定一个数组，对这个数组分两次进行循环（从左往右循环 从右往左循环）
    // 先从左往右循环，当右边的循环满足一定条件时再进行左边的循环
    public static int[] splitSort(int[] array, int startIndex, int endIndex ){
        if(startIndex == endIndex)
            return array;

        // 将数组起始索引处的值记为参照值
        int refer = array[startIndex];
//        int leftCursor = startIndex;
//        int rightCursor = endIndex;

        int referIndex = splitArrAndFindReferIndex(array, refer, startIndex, endIndex, false);
        array[referIndex] = refer;

        // 分为三种情况，参照值本本来就是最小的
        if(referIndex == startIndex){
            return splitSort(array, ++startIndex, endIndex);
        // 数组中有比参照值大的也有比参照值小的，将数组从 referIndex处拆分成两段
        }else if(referIndex > startIndex && referIndex < endIndex) {
            array = splitSort(array, startIndex, --referIndex);
            return splitSort(array, ++referIndex, endIndex);
            // 参照值本来就是最大的
        }else if(referIndex == endIndex){
            splitSort(array, startIndex, --endIndex);
        }

        return array;
    }


    /**
     * 先从右往左循环
     * 两边交替循环，最终确定 refer在数组中的正确索引位置，
     * 并且保证 refer左侧的数组元素都比refer要小，右侧的数组元素都比refer要大
     * @param array
     * @param refer
     * @param leftCursor
     * @param rightCursor
     * @param fromLeftToRight true 表示从左往右循环， false 表示从右往左循环
     * @return
     */
    public static int splitArrAndFindReferIndex(int[] array, int refer, int leftCursor, int rightCursor, boolean fromLeftToRight){

        if(fromLeftToRight){
            while(array[leftCursor] <= refer){
                leftCursor++;
                if(leftCursor == rightCursor)
                    return leftCursor;
            }
            array[rightCursor] = array[leftCursor];
        }
        if(!fromLeftToRight){
            while(array[rightCursor] >= refer){
                rightCursor--;
                if(leftCursor == rightCursor)
                    return rightCursor;
            }
            array[leftCursor] = array[rightCursor];
        }
        return splitArrAndFindReferIndex(array, refer, leftCursor, rightCursor, !fromLeftToRight);
    }





    @Test
    public void aa_01(){
        char l = 'l';
        System.out.println(l == 'l');


    }

}
