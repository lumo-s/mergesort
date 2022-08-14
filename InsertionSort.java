/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mergesortmain;
import java.util.Arrays;

/**
 *
 * @author 86139
 */
public class InsertionSort {
    private InsertionSort(){}
    /*public static<E extends Comparable<E>>void sort(E[]arr){
        for(int i=0;i<arr.length;i++){
            //将arr[i]插入到合适的位置
            for(int j=i;j-1>=0&&arr[j].compareTo(arr[j-1])<0;j--){
               // if(arr[j].compareTo(arr[j-1])<0)
                    swap(arr,j,j-1);
               // else break;
            }
        }   
    }*/
    
    public static<E extends Comparable<E>>void sort(E[]arr){
        for(int i=0;i<arr.length;i++){
            //将arr[i]插入到合适的位置
            E t=arr[i];
            int j;
            for(j=i;j-1>=0&&t.compareTo(arr[j-1])<0;j--){
               arr[j]=arr[j-1]; 
            }
            arr[j]=t;
        }   
    }
    
    public static<E extends Comparable<E>>void sort2(E[]arr,int l,int r){
        for(int i=l;i<=r;i++){
            //将arr[i]插入到合适的位置
            E t=arr[i];
            int j;
            for(j=i;j-1>=l&&t.compareTo(arr[j-1])<0;j--){
               arr[j]=arr[j-1]; 
            }
            arr[j]=t;
        }   
    }
    
   /* public static<E extends Comparable<E>>void sort(E[]arr){
        for(int i=arr.length-1;i>=0;i--){
            //将arr[i]插入到合适的位置
            E t=arr[i];
            int j;
            for(j=i;j+1<arr.length&&t.compareTo(arr[j+1])>0;j++){
               arr[j]=arr[j+1]; 
            }
            arr[j]=t;
        }  
    }*/
    
    /*public static<E>void swap(E[]arr,int i,int j){
        E t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }*/

     public static void main(String[] args) {
       int[]dataSize={10000,100000};
       for(int n:dataSize){
           
            System.out.println("Rondom Array:");
           
            Integer[]arr=ArrayGenerator.generateRandomArray(n, n);
            Integer[]arr2=Arrays.copyOf(arr,arr.length);
            
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);
            
            System.out.println();  
            
            System.out.println("Ordered Array :");
            
            arr=ArrayGenerator.generateOrderArray(n);
            arr2=Arrays.copyOf(arr,arr.length);
            
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);
            
            System.out.println();
            
            
       } 
        /*Integer[] arr={1,4,5,6,3,2};
        InsertionSort.sort(arr);
        for(int e:arr)
            System.out.print(e+" ");
        System.out.println();*/
        
    }
 }

