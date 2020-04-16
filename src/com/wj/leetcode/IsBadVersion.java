package com.wj.leetcode;

public class IsBadVersion {

	
	public int firstBadVersion(int n) {
		int left=1;
		int right=n;
		
		while(left<right) {
        /*
         * If you are setting mid=(left+right)/2, you have to be very careful.
         *  Unless you are using a language that does not overflow such as Python, left+rightleft could overflow. 
         *  One way to fix this is to use mid=left+(right-left)/2 instead.
         */
			int mid=(left+right)/2;
			if(isBadVersion(mid)) {
				right=mid;  //如果当前mid 就是第一个bad,right=mid-1 就会错过第一个badversion
				//right=mid-1;
			}else {
				left=mid+1;
			}
		}
		
		return left;
		
	}
	
	
	
	
    public boolean isBadVersion(int n) {
    	int random=(int)Math.random()%2;
    	if(random==1) {
    		return true;
    	}
    	return false;
    }
	
	public static void main(String[] args) {
		
		
	}
}
