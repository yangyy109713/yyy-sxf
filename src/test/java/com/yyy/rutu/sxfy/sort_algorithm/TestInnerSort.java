package com.yyy.rutu.sxfy.sort_algorithm;


import org.junit.Test;

public class TestInnerSort {

    int arr[] = {7,6,8,5,3,2,4,9,10};

    /**
     * 冒泡排序
     * 通过一次遍历，获取最小值/最大值
     * 将最小值/最大值放在数组头/尾部
     * 然后对剩余数据继续遍历，获取最小值/最大值
     */
    @Test
    public void testBubbleSort(){

        // 将数组从小到大排
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int x : arr) {
            System.out.print(x);
        }
    }

    /**
     * 选择排序
     * 将第一个值看成最小值
     * 和后续数值比较，获取最小值和下标
     * 交换第一个值和最小值
     * 说明：
     * 每次遍历的时候，将前面获取的最小值，看成一个有序列表
     * 后面的看成无序列表，每次遍历无序列表获取最小值
     */
    @Test
    public void testChooseSort(){
        for(int i = 0; i < arr.length; i++){
            int minT = arr[i];
            int indexT = i;
            // 和后续数值比较，获取最小值和下标
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < minT){
                    minT = arr[j];
                    indexT = j;
                }
            }
            // 交换第一个值和最小值
            int temp = arr[i];
            arr[i] = minT;
            arr[indexT] = temp;
        }

        for (int x : arr) {
            System.out.print(x);
        }
    }

    /**
     * 插入排序
     * 默认从第 2 个数据开始比较
     * 如果第 2 个数据比第 1 个小，则交换。然后再用第 3 个比较，如果比前面小，则插入；否则退出循环
     * 说明：默认将第一个数据看成有序的，后面无序的循环每一个数据，如果比前面小，则插入
     */
    @Test
    public void testInsertSort(){
        for(int i = 1; i < arr.length; i++){
            for (int j = i; j > 0; j--){
                if(arr[j] < arr[j - 1]){
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }else {
                    break;
                }
            }
        }

        for (int x : arr) {
            System.out.print(x);
        }
    }

    /**
     * 希尔排序
     * 和插入排序类似
     * 不一样的地方在于：每次循环的步长，通过减半的方式实现
     */
    @Test
    public void testXiSort(){
        for(int i = arr.length / 2; i > 0 ; i /= 2){
            for (int j = i; j < arr.length; j++){
                for (int k = j; k > 0 && k - i >= 0; k -= i){
                    if(arr[k] < arr[k - i]){
                        int temp = arr[k -i];
                        arr[k - i] = arr[k];
                        arr[k] = temp;
                    }else {
                        break;
                    }
                }
            }
        }

        for (int x : arr) {
            System.out.print(x);
        }
    }
}
