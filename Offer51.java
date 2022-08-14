/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.offer51;

/**
 *
 * @author 86139
 */
public class Offer51 {
    
     public int reversePairs(int[] nums){
       int[] temp=new int[nums.length];
       return sort(nums,0,nums.length-1,temp);   
    }


    private int sort(int[] arr,int l,int r,int[] temp){

        if(l>=r) return 0;

        int res=0;
        int mid=l+(r-l)/2;
        res+=sort(arr,l,mid,temp);
        res+=sort(arr,mid+1,r,temp);

        if(arr[mid]>arr[mid+1])//如果无序，则进行归并
            res+=merge(arr,l,mid,r,temp);
        
        return res;
    }

    private int merge(int[] arr,int l,int mid,int r,int[] temp){
        System.arraycopy(arr,l,temp,l,r-l+1);

        int i=l,j=mid+1,res=0;

        //每轮循环为arr[k]赋值
        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i];
                i++;
            }
            else if(temp[i]<=temp[j]){
                arr[k]=temp[i];
                i++;
            }
            else{

                res+=mid-i+1;
                arr[k]=temp[j];
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
