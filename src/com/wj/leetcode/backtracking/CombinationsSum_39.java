package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsSum_39 {

	/*
	 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

	 */
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {

		List<List<Integer>> resList = new ArrayList<List<Integer>>();
		
		if(candidates.length < 1) return resList;
		Arrays.sort(candidates);
		dfs(resList,new ArrayList<>(), candidates, target,0);
		
		return resList;
		
    }
	
	private void dfs(List<List<Integer>> resList, List<Integer> curList, int[] candidates, int target,int index) {
		if(target == 0) {
			resList.add(new ArrayList<>(curList));
			return;
		}
			
		for(int i = index;i<candidates.length;i++) {
			 
			if(target - candidates[i] < 0) {
				break;
			}
			
			curList.add(candidates[i]);
			
			dfs(resList, curList, candidates, target - candidates[i], i);
			
			curList.remove(curList.size() - 1);
		}
		/*
		 * the following method is remove Arrays.sort(candidates);
		 */
//		for(int i = index;i<candidates.length;i++) {
//			 
//			if(target - candidates[i] >= 0) {
//				curList.add(candidates[i]);
//		
//		        dfs(resList, curList, candidates, target - candidates[i], i);
//		
//		        curList.remove(curList.size() - 1);
//			}	
//		}
	}
}
