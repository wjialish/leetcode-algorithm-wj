package com.wj.leetcode.backtracking;

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.INTERNAL;

public class Combinations_77 {

	/*
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

	 */
	
	
	//backtracking
	public List<List<Integer>> combine(int n, int k) {

		List<List<Integer>> resList = new ArrayList<>();
		
		if(k <= 0 || n<=0 || n < k) return resList;
		
		dfs(resList,new ArrayList<>(),n,k,0);
		
		return resList;
	       
    }
	
	private void dfs(List<List<Integer>> resList, List<Integer> curList,int n,int k,int index) {
		if(curList.size() == k) {
			resList.add(new ArrayList<>(curList));
			return;
		}
		
		for(int i = index; i<n; i++) {
			
		     if(i == n-1 && curList.size() < k-1) break;//执行用时 :2 ms, 在所有 Java 提交中击败了99.06%的用户
		     
			//if(n - i <= k - curList.size() - 1 ) break; //执行用时 :16 ms, 在所有 Java 提交中击败了60.06%的用户
			
			curList.add(i+1);
			dfs(resList, curList, n, k,i+1);
			
			curList.remove(curList.size()-1);
			
		}
	}
	
	
	
	//backtracking2: add isvisit
//	public List<List<Integer>> combine(int n, int k) {
//
//		List<List<Integer>> resList = new ArrayList<>();
//		
//		if(k == 0) return resList;
//		
//		dfs(resList,new ArrayList<>(),n,k,new boolean[n],0);
//		
//		return resList;
//	       
//    }
//	
//	private void dfs(List<List<Integer>> resList, List<Integer> curList,int n,int k,boolean[] isvisit,int index) {
//		if(curList.size() == k) {
//			resList.add(new ArrayList<>(curList));
//			return;
//		}
//		
//		for(int i = index; i<n; i++) {
//			if(isvisit[i]) continue;
//			
//			curList.add(i+1);
//			isvisit[i] = true;
//			dfs(resList, curList, n, k, isvisit,i+1);
//			
//			curList.remove(curList.size()-1);
//			isvisit[i] = false;
//			
//		}
//	}
}
