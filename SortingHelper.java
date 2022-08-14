/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mergesortmain;

/**
 *
 * @author 86139
 */
public class SortingHelper {
    private SortingHelper(){}
    
    public static <E extends Comparable<E>> boolean isSorted(E[] arr){
        for(int i=1;i<arr.length;i++)
            if(arr[i-1].compareTo(arr[i])>0)
                    return false;   
        return true;  
    }
   public static<E extends Comparable<E>>void sortTest(String sortname,E[] arr){
       long startTime=System.nanoTime();
       if(sortname.equals("InsertionSort"))
           InsertionSort.sort(arr); 
       else if(sortname.equals("SelectionSort"))
            SelectionSort.sort(arr);
       else if(sortname.equals("MergeSort"))
            MergeSortMain.sort(arr);
       else if(sortname.equals("MergeSortBU"))
            MergeSortMain.sortBU(arr);
       long endTime =System.nanoTime();
       
       double time=(endTime-startTime)/1000000000.0;
       if(!SortingHelper.isSorted(arr))
           throw new RuntimeException(sortname+"failed"); 
       System.out.println(String.format("%s,n=%d:%f s", sortname,arr.length,time));
   }  
}

