package com.wj.leetcode;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindMedianSortedArrays {

	
	
	public static void main(String[] args) {
		
		int[] nums1=new int[] {1,2,3,4};
		
		int[] nums2=new int[] {2,3,5,3,4,7};
		
		String[] s1=new String[]{"a","s","d","er"};
		String[] s2=new String[]{"aaaa","s","d","er"};
		
         List<String> list=new ArrayList<>();
         
         List<String> list2=Arrays.asList(s1);
         
         List<String> list3=Arrays.asList(s2);
         
         list.addAll(list2);
         list.addAll(list3);
    	 
    	 /*String.valueOf(nums1);
    	 
    	 list=Arrays.asList(String.valueOf(nums1));
    	 
    	 list.addAll(Arrays.asList(String.valueOf(nums2)));*/
    	 
    	 for (String string : list) {
			System.out.println(string);
		}
   	 
	}
	
	/*
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 * You may assume nums1 and nums2 cannot be both empty.
	 * 
	 * Example 1:
	 * nums1 = [1, 3]
	 * nums2 = [2]
	 * 
	 * The median is 2.0
	 * Example 2:
	 * nums1 = [1, 2]
	 * nums2 = [3, 4]
	 * 
	 * The median is (2 + 3)/2 = 2.5
	 */
	
     public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
    	 double res=0.0f;
    	    	 
    	 List<Integer> list=new ArrayList<>();
    	 
    	 for(int i=0;i<nums1.length;i++) {
    		 list.add(nums1[i]);
    	 }
    	 
    	 for(int i=0;i<nums2.length;i++) {
    		 list.add(nums2[i]);
    	 }
    	 
    	 list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
    	
    	if(list.size()%2==0) {
     		int restmp=list.get(list.size()/2)+list.get(list.size()/2-1);
             res=restmp/2.0;
     	}else {
     		res=list.get(list.size()/2);
     	}
    	 
    	 return res;
     }
     
     
     
     public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
         
    	 double res=0.0f;
    	    	 
    	 List<String> list=new ArrayList<>();
    	 
         List<String> list2=Arrays.asList(String.valueOf(nums1));
         
         List<String> list3=Arrays.asList(String.valueOf(nums2));
         
         list.addAll(list2);
         list.addAll(list3);
    	 
    	 /*
    	 
    	 List<Integer> listInteger=Integer.parseUnsignedInt(list);
    	 
    	 listInteger.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
    	
    	if(list.size()%2==0) {
     		int restmp=list.get(list.size()/2)+list.get(list.size()/2-1);
             res=restmp/2.0;
     	}else {
     		res=list.get(list.size()/2);
     	}*/
    	 
    	 return res;
     }
     
     
	    public double findMedianSortedArrays3(int[] A, int[] B) {
	        int m = A.length;
	        int n = B.length;
	        if (m > n) { // to ensure m<=n
	            int[] temp = A; A = B; B = temp;
	            int tmp = m; m = n; n = tmp;
	        }
	        
	        /*A= {1,2,3};
	        B= {2,3};
	        m=3;
	        n=2;
	        temp=A;
	        A= {2,3};
	        B= {1,2,3};
	        tmp=3,
	        m=2,
	        n=3;*/
	        
	        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
	        while (iMin <= iMax) {
	            int i = (iMin + iMax) / 2;
	            int j = halfLen - i;
	            if (i < iMax && B[j-1] > A[i]){
	                iMin = i + 1; // i is too small
	            }
	            else if (i > iMin && A[i-1] > B[j]) {
	                iMax = i - 1; // i is too big
	            }
	            else { // i is perfect
	                int maxLeft = 0;
	                if (i == 0) { maxLeft = B[j-1]; }
	                else if (j == 0) { maxLeft = A[i-1]; }
	                else { maxLeft = Math.max(A[i-1], B[j-1]); }
	                if ( (m + n) % 2 == 1 ) { return maxLeft; }

	                int minRight = 0;
	                if (i == m) { minRight = B[j]; }
	                else if (j == n) { minRight = A[i]; }
	                else { minRight = Math.min(B[j], A[i]); }

	                return (maxLeft + minRight) / 2.0;
	            }
	        }
	        return 0.0;
	    }
    	
	    
	    
	   
	    
	    public double findMedianSortedArrays4(int[] A, int[] B) {
	        
	    	int m=A.length;
	    	int n=B.length;
	    	if(m>n) {
	    		int[] temp=A;A=B;B=temp;
	    		int tmp=m;m=n;n=tmp;
	    	}
	    	int iMin=0;
	    	int iMax=m;
	    	int halfLen=(m+n+1)/2;
	    	
	    	while(iMin<=iMax) {
	    		int i=(iMin+iMax)/2;
	    		int j=halfLen-i;
	    		
	    		if(i <= iMax && i> iMin && A[i-1] > B[j]) {
	    			iMax=i-1;
	    		}else if(i < iMax && i>= iMin && B[j-1]>A[i]) {
	    			iMin=i+1;
	    		}else {
	    			int maxLeft=0;
	    			if(i==0) {
	    				maxLeft=B[j-1];
	    			}else if(j==0) {
	    				maxLeft=A[i-1];
	    			}else {
	    				maxLeft=Math.max(A[i-1], B[j-1]);
	    			}
	    			
	    			if((m+n)%2==1){
	    				return maxLeft;
	    			}
	    			
	    			
	    			int minRight=0;
	    			if(i==m) {
	    				minRight=B[j];
	    			}else if(j==n) {
	    				minRight=A[i];
	    			}else {
	    				minRight=Math.min(A[i], B[j]);
	    			}
	    			
	    			return(maxLeft+minRight)/2.0;
	    		}
	    	}
	    	
	    	
	    	return 0.0;
	    	//return 0.0比和return0.0f 执行如上方法 速度快！！！
	    	
	    }
}
