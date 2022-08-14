/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mergesortmain;

/**
 *
 * @author 86139
 */
import java.util.Random; 
public class ArrayGenerator {
     private ArrayGenerator(){} 
    
    public static Integer[] generateOrderArray(int n){
        Integer[] arr=new Integer[n];
        for(int i=0;i<n;i++)
            arr[i]=i;
        return arr;
    } 
    //生成一个长度为n的随机数组，每个数组是从0到bound 前闭后开
    public static Integer[] generateRandomArray(int n,int bound){
        Integer[] arr=new Integer[n];
        Random rnd=new Random();
        for(int i=0;i<n;i++)
            arr[i]=rnd.nextInt(bound);
        return arr;
        
    }   
}
