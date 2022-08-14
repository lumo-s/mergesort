/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mergesortmain;

/**
 *
 * @author 86139
 */

import java.util.Arrays;
public class MergeSortMain {
    
    private MergeSortMain(){}
    
    //自顶向下
    public static<E extends Comparable<E>>void sort(E[] arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
        sort(arr,0,arr.length-1,temp);
    }
  
    
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r,E [] temp){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid,temp);
        sort(arr,mid+1,r,temp);
        
        if(arr[mid].compareTo(arr[mid+1])>0)
             merge(arr,l,mid,r,temp);
            
    }
    
    //自底向上的归并排序
    public static<E extends Comparable<E>>void sortBU(E[] arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
     
        int n=arr.length;
        //使用插入排序优化
        //遍历一遍，对所有arr[i,i+15]的区间，使用插入排序法
        for(int i=0;i<n;i+=16)
            InsertionSort.sort2(arr,i,Math.min(n-1, i+15));
        
        //遍历合并的区间长度,注意sz从16开始
        for(int sz=16;sz<n;sz+=sz){
            //遍历合并的两个区间的起始位置i
            //合并[i,i+sz-1]和[i+sz,i+sz+sz-1]
            for(int i=0;i+sz<n;i+=sz+sz){
                if(arr[i+sz-1].compareTo(arr[i+sz])>0)
                    merge(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),temp);
            }
        }
    }
    
      //合并两个有序的区间arr[l,mid]和arr[mid+1,r] 
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r,E[] temp){
        
        System.arraycopy(arr, l, temp, l, r-l+1);
        
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){ 
                arr[k]=temp[j];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i];
                i++;
            }
            else if(temp[i].compareTo(temp[j])<=0){
                arr[k]=temp[i];
                i++;
            } 
            else{
                arr[k]=temp[j];
                j++;
            }
        }   
    }
         
    public static void main(String[] args) {
       int n=5000000;
       Integer[] arr=ArrayGenerator.generateRandomArray(n,n);
       Integer[] arr2=Arrays.copyOf(arr, arr.length);
       
       SortingHelper.sortTest("MergeSort", arr); 
       SortingHelper.sortTest("MergeSortBU", arr2); 
     
    }
}
