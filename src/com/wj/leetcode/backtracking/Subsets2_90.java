package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2_90 {

	/*
	 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

	 * 
	 */
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if(nums.length < 1) return resList;

        Arrays.sort(nums);
        dfs(resList,new ArrayList<Integer>(), nums, 0);

        return resList;
       
    }

    private void dfs(List<List<Integer>> resList,List<Integer> curList, int[] nums, int index){
        if(index == nums.length){
            resList.add(new ArrayList<Integer>(curList));
            return;
        }

        curList.add(nums[index]);
        dfs(resList,curList,nums,index + 1);

        curList.remove(curList.size() - 1);

        while(index + 1 < nums.length && nums[index] == nums[index+1]){
            index++;
        }

        dfs(resList,curList,nums,index + 1);
    }
}
