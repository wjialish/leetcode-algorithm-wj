package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence_60 {

	
	/*
	 * The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

	 */
	
	public static void main(String[] args) {
		PermutationSequence_60 ps = new PermutationSequence_60();
		String res = ps.getPermutation(4,9
				);
		System.out.println(res);
	}
	
	
	
	String res = "";
	
	public String getPermutation(int n, int k) {
         
         if(n <= 0 || k <=0) return "";
         

         List<String> list = new ArrayList<>();
         
         dfs(list,new StringBuilder(),n,k,new boolean[n]);
         
         res = list.get(k-1);
         
         return res;
         
    }
	
	
	private void dfs(List<String> list, StringBuilder sb,int n,int k,boolean[] isvisit) {
		
			if(sb.length() == n) {
				list.add(sb.toString());
				return;
			}
			
//			if(list.size() == k) {
//				 res = list.get(list.size()-1);
//				 return res;
//			}
			
			for(int i = 0;i<n;i++) {
					if(isvisit[i]) continue;
					sb.append(i+1);
					isvisit[i] = true;
					dfs(list, sb, n, k, isvisit);
					sb.deleteCharAt(sb.length()-1);
					isvisit[i] = false;
			}
			

	}
}


