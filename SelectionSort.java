/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mergesortmain;

/**
 *
 * @author 86139
 */
public class SelectionSort {
    private SelectionSort(){}
    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=0;i<arr.length;i++){
            //选择arr[i...n)中的最小值的索引
            int minIndex=i;
            for(int j=i;j<arr.length;j++){
                if(arr[j].compareTo(arr[minIndex])<0)
                    minIndex=j;
            }
            swap(arr,i,minIndex);
        }    
    }
    
    public static <E extends Comparable<E>> void sort2(E[] arr){
        for(int i=arr.length-1;i>=0;i--){
            //选择arr[i...n)中的最大值的索引
            int maxIndex=i;
            for(int j=i;j>=0;j--){
                if(arr[j].compareTo(arr[maxIndex])>0)
                    maxIndex=j;
            }
            swap(arr,i,maxIndex);
        }    
    }
     
    private static <E> void swap(E[] arr,int i,int j){
        E t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

    public static void main(String[] args) {
        /*Integer[] arr={1,4,5,6,3,2};
        SelectionSort.sort2(arr);
        for(int e:arr)
            System.out.print(e+" ");
        System.out.println();*/
       int[]dataSize={10000,100000};
       for(int n:dataSize){
            Integer[]arr=ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", arr);
       } 
    }
}
