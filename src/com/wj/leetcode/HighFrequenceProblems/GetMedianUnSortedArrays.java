package com.wj.leetcode.HighFrequenceProblems;

public class GetMedianUnSortedArrays {

    public double findMedianUnSortedArrays(int[] nums1,int[] nums2){

        int m = nums1.length;
        int n = nums2.length;

        int k = (m+n)/2;

        return ((m+n)%2==1) ? findKth(nums1,nums2,k+1) : (findKth(nums1,nums2,k)
                  +findKth(nums1,nums2,k+1))/2.0;
    }
    

    public double findKth(int[] nums1,int[] nums2,int k){

        return quickSorted(nums1, nums2, 0, nums1.length+nums2.length-1, k);
    }

    public double quickSorted(int[] nums1,int[] nums2,int low,int high,int k){

        int pivot = low;
         
        //use quickSort's idea
        //put nums that are <= pivot to the left
        //put nums that are >= pivot tp the right
        for(int j = low;j<high;j++){
            if(getNum(nums1, nums2, j) <= getNum(nums1, nums2, high)){
                swap(nums1, nums2, pivot++, j);  //做交换的是当前位置在j前面的数
            }
        }

        swap(nums1, nums2, pivot, high);

        //count the nums that > pivot from high
        int count = high - pivot +1;
        if(count == k){
            return getNum(nums1,nums2,pivot);
        }
        if(count>k){
            return quickSorted(nums1, nums2, pivot+1, high, k);
        }else{
            return quickSorted(nums1, nums2, low, pivot-1, k-count);
        }



    }

    private int getNum(int[] nums1,int[] nums2,int index){

        //把两个数组虚拟拼在一起成为一个数组,在0-m 长度之间的是nums1，其余是nums2 
        return (index<nums1.length) ? nums1[index]:nums2[index-nums1.length]; 
    }

    private void swap(int[] nums1,int[] nums2,int i,int j){
        int m = nums1.length;

        if(i<m && j<m){ //说明i和j位置的数都是nums1的数
            swap(nums1, i, j);
        }else if(i>=m && j>=m){ //说明i和j位置的数都是nums2的数
            swap(nums2, i-m, j-m);
        }else if(i<m && j>=m){  //i位置的数是nums1的数，j位置的数是nums2的数
            int temp = nums1[i];
            nums1[i] = nums2[j-m];
            nums2[j-m] = temp;
        }

        

    }

    private void swap(int[] nums1,int i,int j){
        int temp = nums1[i];
        nums1[i] = nums1[j];
        nums1[j] = temp;
    }
}