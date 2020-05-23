package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsSum_40 {

	/*
	 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]

	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {

		List<List<Integer>> resList = new ArrayList<List<Integer>>();
		
		if(candidates.length < 1) return resList;
		Arrays.sort(candidates);
		
		int len = candidates.length;
		
		dfs(resList,new ArrayList<>(), candidates, target,0,new boolean[len]);
		
		return resList;
		
    }
	
	private void dfs(List<List<Integer>> resList, List<Integer> curList, int[] candidates, int target,int index,boolean[] isvisit) {
		if(target == 0) {
			resList.add(new ArrayList<>(curList));
			return;
		}
			
		for(int i = index;i<candidates.length;i++) {
			 
			if(isvisit[i]) continue;
			
			if(target - candidates[i] < 0) {
				break;
			}
			
			//prevent dupliacte results from the resList
			if(i > 0 && candidates[i] == candidates[i-1] && !isvisit[i-1]) continue;
			
			curList.add(candidates[i]);
			isvisit[i] = true;
			dfs(resList, curList, candidates, target - candidates[i],i,isvisit);
			
			curList.remove(curList.size() - 1);
			isvisit[i] = false;
		}
		
	}
	
//	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//
//		List<List<Integer>> resList = new ArrayList<List<Integer>>();
//		
//		if(candidates.length < 1) return resList;
//		Arrays.sort(candidates);
//		
//		int len = candidates.length;
//		
//		dfs(resList,new ArrayList<>(), candidates, target,0,new boolean[len]);
//		
//		return resList;
//		
//    }
//	
//	private void dfs(List<List<Integer>> resList, List<Integer> curList, int[] candidates, int target,int index,boolean[] isvisit) {
//		if(target == 0) {
//			if(!resList.contains(curList)) {
//				resList.add(new ArrayList<>(curList));
//				return;
//			}
//		
//		}
//			
//		for(int i = index;i<candidates.length;i++) {
//			 
//			if(target - candidates[i] < 0) {
//				break;
//			}
//			
//			if(isvisit[i]) continue;
//			curList.add(candidates[i]);
//			isvisit[i] = true;
//			dfs(resList, curList, candidates, target - candidates[i], i,isvisit);
//			
//			curList.remove(curList.size() - 1);
//			isvisit[i] = false;
//		}
//		
//	}
}
