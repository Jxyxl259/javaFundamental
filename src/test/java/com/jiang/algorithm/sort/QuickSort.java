package com.jiang.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 快速排序算法实现
 *
 *              快速排序算法的核心思想是将大数组分而治之，通过递归实现数组元素最终的有序性
 *              但是快排的缺点是如果待排序数组过大，对堆栈的性能损耗是不容小觑的，递归调递归的算法实现很容易出现栈溢出，
 *              对于标准的快排算法的优化，改进措施有如下几点
 *              1.  参照值的选取，最理想的情况是所选取的参照值处于待排序子数组的中间位置，所以可以考虑优化参照值的选取
 *              2.  最小子列的长度，限定最小子列的长度，小于等于该长度的子列使用插入排序算法完成排序，在数据量很小的情况下，插入排序耗费的时间要优于快排，并且能减少的递归的深度，减少堆栈性能的消耗
 *              3.  对是否交换
 *                  （交替循环时，从左往右找比标杆值大的数值的过程中，低位元素两两相比，arr[low] > arr[low+1] 则交换，
 *                              从右往左找比标杆值小的数值的过程中，高位元素两两相比，arr[high] < arr[high-1] 则交换）
 *                  做标记，如果全过程没有发生过交换，直接返回即可
 *              4.  优先对较小的数组进行排序，这样可以减少递归调用栈的深度
 *
 * @author:
 * @create: 2019-03-08 12:56
 */
public class QuickSort {

    //
    public static void main(String[] args) {

        int[] arr = new int[100000];
        for(int i = 0; i<arr.length ; i++){
            arr[i] = (int)(Math.random()*100000) +1;
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
//        // qSort(arr, 0, arr.length-1);
//        splitSort(arr, 0, arr.length-1);
//        System.out.println("快排耗时------------>"+(System.currentTimeMillis()-start)/1000D + "秒");

        // 优化后的快速排序
        long start_01 = System.currentTimeMillis();
        // qSort(arr, 0, arr.length-1);
        qSort_Optimize(arr, 0, arr.length-1);
        System.out.println("快排优化后耗时------------>"+(System.currentTimeMillis()-start_01)/1000D + "秒");


        int[] nums = new int[]{-1,-3,-2,-6,-7,-8,7,-4,9,1,6,8,0};
//        int[] nums = new int[]{5,3,7,4,6,8,2,9};
//        nums = splitSort(nums, 0, nums.length-1);
//        qSort_Optimize(nums, 0, nums.length-1);
//
//        Integer[] numArr = ArrayUtils.toObject(nums);
//        List<Integer> numList = Arrays.asList(numArr);
//        numList.forEach(System.out::println);

    }


    /**
     * 快速排序标准实现
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int qSort(int[] arr, int start, int end){
        int referIndex;
        if(start < end){
            referIndex = partition(arr, start, end);
            qSort(arr, start, referIndex-1);
            qSort(arr, referIndex + 1, end);
        }
        return 0;
    }


    /**
     * 快排标准实现，返回中间元素的索引
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] arr, int start, int end) {

        int refer = arr[start];
        while(start < end){
            while(start < end && arr[end] >= refer){
                end--;
                arr[start] = arr[end];
            }
            while(start < end && arr[start] <= refer){
                start++;
                arr[end] = arr[start];
            }
        }
        arr[start] = refer;
        return start;
    }



    /**
     * 自己实现的快排
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
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



    public static void qSort_Optimize(int[] arr, int startIndex, int endIndex){
        // 优化2：最小子列长度，当最小子列长度小于某个范围的时候，就改用插入排序，减少堆栈性能损耗
        if(endIndex - startIndex + 1 <= 10){
            InsertSort.insertSort(arr, startIndex, endIndex);
            return;
        }
        int referIndex = -1;

        // 优化3：对是否交换做标记，如果全程未交换则说明子列是有序的，无需继续递归
        HasSwaped swaped = new HasSwaped(false,false);

        if(startIndex < endIndex){
            referIndex = findReferIndex(arr, startIndex, endIndex, swaped);
            // 优化4：优先对小的数组进行排序，尽量减少递归栈的深度
            if(referIndex-1 - startIndex <= endIndex - referIndex+1){
                if(swaped.leftFlag){
                    qSort_Optimize(arr, startIndex, referIndex-1 );
                }
                if(swaped.rightFlag){
                    qSort_Optimize(arr, referIndex+1, endIndex);
                }
            }else{
                if(swaped.rightFlag){
                    qSort_Optimize(arr, referIndex+1, endIndex);
                }
                if(swaped.leftFlag){
                    qSort_Optimize(arr, startIndex, referIndex-1 );
                }
            }
        }
    }


    private static int findReferIndex(int[] arr, int startIndex, int endIndex, HasSwaped swaped){
        // 优化1：参照值的选取，参照值为数组元素均值效率最高
        // int refer = selectMediumOfThreeValue(arr, startIndex, endIndex);
        int refer = arr[ startIndex ];
        swaped.rightFlag = false;
        swaped.leftFlag = false;

        while(startIndex < endIndex){
            while(startIndex < endIndex && arr[ endIndex ] >= refer){
                if(arr[ endIndex ] < arr[ endIndex -1 ]){
                    arr[ endIndex ] ^= arr[ endIndex-1 ];
                    arr[ endIndex-1 ] ^= arr[ endIndex ];
                    arr[ endIndex ] ^= arr[ endIndex-1 ];
                    swaped.leftFlag = true;
                }
                endIndex--;
            }
            arr[ startIndex ] = arr[ endIndex ];

            while(startIndex < endIndex && arr[ startIndex ] <= refer){
                if(arr[ startIndex ] > arr[ startIndex +1]){
                    arr[ startIndex ] ^= arr[ startIndex+1 ];
                    arr[ startIndex+1 ] ^= arr[ startIndex ];
                    arr[ startIndex ] ^= arr[ startIndex+1 ];
                    swaped.rightFlag = true;
                }
                startIndex++;
            }
            arr[ endIndex ] = arr[ startIndex ];
        }
        arr[ startIndex ] = refer;
        return startIndex;
    }

    private static class HasSwaped{
        private Boolean leftFlag;
        private Boolean rightFlag;

        private HasSwaped(Boolean leftFlag, Boolean rightFlag) {
            this.leftFlag = leftFlag;
            this.rightFlag = rightFlag;
        }

        /*private Boolean getLeftFlag() {
            return leftFlag;
        }

        private void setLeftFlag(Boolean leftFlag) {
            this.leftFlag = leftFlag;
        }

        private Boolean getRightFlag() {
            return rightFlag;
        }

        private void setRightFlag(Boolean rightFlag) {
            this.rightFlag = rightFlag;
        }*/
    }

}
