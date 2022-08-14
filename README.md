# mergesort
mergesort two ways   

1.归并排序法的原理   

时间复杂度：O(nlogn)   

思想：将数组持续一分为二，对子数组进行排序，排序后进行合并   

递归：  

MergeSort(arr,l,r){
      if(l>=r) return;//求解最基本问题
      int mid=（l+r)/2;
      MergeSort(arr,l,mid);//对arr[l,mid]进行排序
      MergeSort(arr,mid+1,r);//对arr[mid+1,r]进行排序
      merge(arr,l,mid,r);//将arr[l,mid]和arr[mid+1,r]合并
}

2.归并过程

使用辅助空间，无法原地完成

import java.util.Arrays;

public class MergeSortMain {
    
    private MergeSortMain(){}
    
    public static<E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }
    
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r){
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r){
   
        E[] temp=Arrays.copyOfRange(arr,l,r+1);//前闭后开，arr[i]=temp[0]
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            } 
            else{
                arr[k]=temp[j-l];
                j++;
            }
        }   
    }
    
    public static void main(String[] args) {
       int n=10000;
       Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
       SortingHelper.sortTest("MergeSort", arr);  
 
       /*Integer[] arr={3,9,5,8,7,23,20};
       System.out.println(Arrays.toString(arr));
       MergeSortMain.sort(arr);
       System.out.println(Arrays.toString(arr));*/
       
    }
}

3.归并排序算法的微观解读(差显示每一步的过程的代码）

import java.util.Arrays;

public class MergeSortMain {
    
    private MergeSortMain(){}
   
    public static<E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1,0);
    }
  
    private static <E extends Comparable<E>>void sort(E[] arr,int l,int r,int depth){
       
        //生成深度字符串
        String depthString = generateDepthString(depth);
        
        //打印当前sort处理的数组区间信息
        System.out.print(depthString);
        System.out.println(String.format("mergesort arr[%d,%d]", l,r));
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid,depth+1);//深度加一
        sort(arr,mid+1,r,depth+1);//深度加一
        
        //打印这次merge要处理的区间范围
        System.out.print(depthString);
        System.out.println(String.format("merge arr[%d,%d] and arr[%d,%d]", l,mid,mid+1,r));
        
        merge(arr,l,mid,r);
        
        //打印merge后的数组
        System.out.print(depthString);
        System.out.println(String.format("after mergesort arr[%d,%d]:", l,r)); 
        for(E e:arr)
            System.out.print(e+" ");
        System.out.println();
    }
    
   private static String generateDepthString(int depth){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<depth;i++)
            res.append("--");
        return res.toString();
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r] 
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r){
        
        E[] temp=Arrays.copyOfRange(arr,l,r+1);//前闭后开
        
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){ 
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            } 
            else{
                arr[k]=temp[j-l];
                j++;
            }
        }   
    }
         
    public static void main(String[] args) {
       Integer[] arr={3,9,5,8,7,23,20};
       SortingHelper.sortTest("MergeSort", arr);  
    }
}
        
4.归并排序法的时间复杂度分析

O(nlogn)  
共有logn+1层，logn级别  
每层中级别o(n)    
时间复杂度标准算法参考《算法导论》主定理

5.优化归并排序  优化1：判断是否需要merge  

//进行判断之后，如果完全有序的话，时间复杂度是O(n)
import java.util.Arrays;

public class MergeSortMain {
    
    private MergeSortMain(){}
    
    public static<E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }
    
    public static<E extends Comparable<E>>void sort2(E[] arr){
        sort2(arr,0,arr.length-1);
    }
    
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid);//深度加一
        sort(arr,mid+1,r);//深度加一
      
        //调用merge
        merge(arr,l,mid,r);
            
    }
    
     private static<E extends Comparable<E>>void sort2(E[] arr,int l,int r){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort2(arr,l,mid);
        sort2(arr,mid+1,r);
        
        if(arr[mid].compareTo(arr[mid+1])>0)//若无序，执行以下代码
             merge(arr,l,mid,r);
            
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r){
   
        E[] temp=Arrays.copyOfRange(arr,l,r+1);//前闭后开，arr[i]=temp[0]
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            } 
            else{
                arr[k]=temp[j-l];
                j++;
            }
        }   
    }
    
    public static void main(String[] args) {
       int n=1000000;
       //Integer[] arr=ArrayGenerator.generateRandomArray(n,n);
       Integer[] arr=ArrayGenerator.generateOrderArray(n);
       Integer[] arr2=Arrays.copyOf(arr, arr.length);
       SortingHelper.sortTest("MergeSort", arr); 
       SortingHelper.sortTest("MergeSort2", arr2); 
        
       /*Integer[] arr3=ArrayGenerator.generateRandomArray(n, n);
       SortingHelper.sortTest("SelectionSort", arr); 
       SortingHelper.sortTest("InsertionSort", arr2); 
       SortingHelper.sortTest("MergeSort", arr3);  */
       
       /*Integer[] arr={3,9,5,8,7,23,20};
       System.out.println(Arrays.toString(arr));
       MergeSortMain.sort(arr);
       System.out.println(Arrays.toString(arr));*/     
    }
}

非叶子节点：n/2+n/4+n/8+...+	1  
=n/2(1-(1/2)^m)/(i/2)<n   =O(n)  
叶子节点：n  

6.使用插入排序优化归并排序

在脚本语言中使用插入排序优化可能不稳定，更耗时

import java.util.Arrays;

public class MergeSortMain {
    
    private MergeSortMain(){}
    
    public static<E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }
    
    public static<E extends Comparable<E>>void sort2(E[] arr){
        sort2(arr,0,arr.length-1);
    }
    
    public static<E extends Comparable<E>>void sort3(E[] arr){
        sort3(arr,0,arr.length-1);
    }
    
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
      
        //调用merge
        merge(arr,l,mid,r);
            
    }
    
    private static<E extends Comparable<E>>void sort2(E[] arr,int l,int r){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort2(arr,l,mid);
        sort2(arr,mid+1,r);
        
        if(arr[mid].compareTo(arr[mid+1])>0)
             merge(arr,l,mid,r);
            
    }
     
    private static<E extends Comparable<E>>void sort3(E[] arr,int l,int r){
       
        if(r-l<=15){//处理的数据规模较小，使用插入排序较快
            InsertionSort.sort2(arr,l,r);
            return;    
        }
        
        int mid=l+(r-l)/2;
        sort3(arr,l,mid);
        sort3(arr,mid+1,r);
        
        if(arr[mid].compareTo(arr[mid+1])>0)
             merge(arr,l,mid,r);
            
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r){
   
        E[] temp=Arrays.copyOfRange(arr,l,r+1);//前闭后开，arr[i]=temp[0]
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            } 
            else{
                arr[k]=temp[j-l];
                j++;
            }
        }   
    }
    
    public static void main(String[] args) {
       int n=5000000;
       Integer[] arr=ArrayGenerator.generateRandomArray(n,n);
       Integer[] arr2=Arrays.copyOf(arr, arr.length);
       Integer[] arr3=Arrays.copyOf(arr, arr.length);
       SortingHelper.sortTest("MergeSort", arr); 
       SortingHelper.sortTest("MergeSort2", arr2);
       SortingHelper.sortTest("MergeSort3", arr3); 

        
       /*Integer[] arr3=ArrayGenerator.generateRandomArray(n, n);
       SortingHelper.sortTest("SelectionSort", arr); 
       SortingHelper.sortTest("InsertionSort", arr2); 
       SortingHelper.sortTest("MergeSort", arr3);  */
       
       /*Integer[] arr={3,9,5,8,7,23,20};
       System.out.println(Arrays.toString(arr));
       MergeSortMain.sort(arr);
       System.out.println(Arrays.toString(arr));*/
          
    }
}

7.归并排序法的内存操作优化

import java.util.Arrays;

public class MergeSortMain {
    
    private MergeSortMain(){}
    
    public static<E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }
  
    
    private static<E extends Comparable<E>>void sort(E[] arr,int l,int r){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        
        if(arr[mid].compareTo(arr[mid+1])>0)
             merge(arr,l,mid,r);
            
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static<E extends Comparable<E>>void merge(E[] arr,int l,int mid,int r){
   
        E[] temp=Arrays.copyOfRange(arr,l,r+1);//前闭后开，arr[i]=temp[0]
        int i=l,j=mid+1;
        //每轮为arr[k]赋值
        for(int k=l;k<=r;k++){
            //arr[i]和arr[j]
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            } 
            else{
                arr[k]=temp[j-l];
                j++;
            }
        }   
    }
    
    public static<E extends Comparable<E>>void sort2(E[] arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
        sort2(arr,0,arr.length-1,temp);
    }
  
    
    private static<E extends Comparable<E>>void sort2(E[] arr,int l,int r,E [] temp){
       
        if(l>=r) return;
        
        int mid=l+(r-l)/2;
        sort2(arr,l,mid,temp);
        sort2(arr,mid+1,r,temp);
        
        if(arr[mid].compareTo(arr[mid+1])>0)
             merge(arr,l,mid,r);
            
    }
    
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static<E extends Comparable<E>>void merge2(E[] arr,int l,int mid,int r,E[] temp){
        
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
       SortingHelper.sortTest("MergeSort2", arr2); 
     
    }
}

7.自底向上的归并排序算法

把每个元素看成有序的数组  
两两为一组归并

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
        
        //遍历合并的区间长度
        for(int sz=1;sz<n;sz+=sz){
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

8.作业：使用插入排序优化自底向上的归并排序算法

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

9.快速求解数组的逆序对数问题

例：7 1 4 2 8 3 6 5  
7 1为一对逆序数，7 4也是一对逆序数,7 8不是  
（1）最朴素的想法：遍历所有的数据对

for(int i=0;i<n;i++)
     for(int j=i+1;j<n;j++)
         if(arr[i]>arr[j] res++;
         
时间复杂度：O(n^2)

 public int reversePairs(int[] nums){
        int res=0;
        for(int i=0;i<nums.length;i++)
            for(int j=i+1;j<nums.length;j++)
                if(nums[i]>nums[j]) res++;
        return res;     
    }

快速求解：归并过程O(nlogn)

如果arr[j]<arr[i] res+=(mid-i+1)

class Solution {

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
}

10.小结  
归并排序法是递归算法  
分治算法  
优化1：判断是否需要merge  
优化2: 对小规模数据使用插入排序  
优化3：只创建一个临时空间

归并排序算法不是原地排序算法，空间复杂度o(n)  
应用：解决逆序数对数问题
