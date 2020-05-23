package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII_47 {

	/*
	 * Given a collection of numbers that might contain duplicates, return all possible unique permutations(全排列).

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

	 */
	
	
	public static void main(String[] args) {
		int[] nums = {1,1,2};
		List<List<Integer>> resList = new ArrayList<List<Integer>>();
		PermutationsII_47 permu = new PermutationsII_47();
		resList = permu.permuteUnique(nums);
		for(List<Integer> list : resList) {
			System.out.println(list);
		}
		
	}
	//m1
	public List<List<Integer>> permuteUnique(int[] nums){
		List<List<Integer>> resList = new ArrayList<>();
		if(nums.length<1) return resList;
		//this step is very important, in order to prune
		Arrays.sort(nums);
		dfs(resList,new ArrayList<>(),nums,new boolean[nums.length]);
		
		return resList;
	}
	
	
	private void dfs(List<List<Integer>> resList, List<Integer> curList, int[] nums, boolean[] isvisit) {
		if(curList.size() == nums.length) {
			resList.add(new ArrayList<>(curList));
			return;
		}
		
		for(int i=0;i<nums.length;i++) {
			if(isvisit[i]) continue;
			/* how to decide to not duplicate?
			 * We can sort all elements, then if the current element is the same as the previous, and previous one is unvisit, we'll skip the current elemnet
			 * 
			 * how to decide it can't missing element?
			 * [1,1,2]
			 * for example: if we alreadly add the first element to list, we meet the second element 1, due to the first one is visited, so we still add the seconde to result list
			 */
			if(i<0 && nums[i] == nums[i-1] && !isvisit[i-1]) {
				continue;
			}
			curList.add(nums[i]);
			isvisit[i] = true;
			dfs(resList, curList, nums, isvisit);
			curList.remove(curList.size()-1);
			isvisit[i] = false;
		}
	}
    //m2
	List<List<Integer>> resList = new ArrayList<List<Integer>>();
	Set<List<Integer>> set = new HashSet<List<Integer>>();//这地方改成treeset就不行
	
	
	
	
	
	public List<List<Integer>> permuteUnique2(int[] nums) {

		List<Integer> num_list = new ArrayList<>();
		for(int num:nums) {
			num_list.add(num);
		}
		
		int n = num_list.size();
		
		backtracking(n,num_list,0);
		
		//return new ArrayList<>(set);
		return resList;
    }
	
	
	public void backtracking(int n, List<Integer> num_list,int first) {
		//if all integer are used up, put the num_list into resList,end recursion
		if(first == n) {
			//resList.add(num_list);//XXXX有问题
			List<Integer> list = new ArrayList<Integer>(num_list);
			resList.add(list);
//			if(!resList.contains(list)) {
//				
//			}
		}
		
		for(int i = first;i<n;i++) {
			//place i-th integer first
			//in the current permutations
			if(!isRepeat(num_list, first, i)) {
				Collections.swap(num_list, first, i);
				//use the next integer to complete the permutation
				backtracking(n, num_list, first+1);
				//backtrack
				if(first == i) continue;
				Collections.swap(num_list, first, i);
			}
			
		}
	}
	
	
	public boolean isRepeat(List<Integer> num_list, int first ,int n) {
		int temp = num_list.get(n);
		for(int i = first;i<n;i++) {
			if(num_list.get(i) == temp) {
				return true;
			}
		}
		return false;
	}
	
	
	
//	public static void main(String[] args) {
//		String[] nums = {"q","q","e"};
//		List<List<String>> resList = new ArrayList<List<String>>();
//		PermutationsII_47 permu = new PermutationsII_47();
//		resList = permu.permuteUnique(nums);
//		for(List<String> list : resList) {
//			System.out.println(list);
//		}
//		
//	}
//	
//
//	List<List<String>> resList = new ArrayList<List<String>>();
//	Set<List<String>> set = new HashSet<List<String>>();//这地方改成treeset就不行
//	
//	
//	
//	
//	
//	public List<List<String>> permuteUnique(String[] nums) {
//
//		List<String> num_list = new ArrayList<>();
//		for(String num:nums) {
//			num_list.add(num);
//		}
//		
//		int n = num_list.size();
//		
//		backtracking(num_list,n,0);
//		
//		//return new ArrayList<>(set);
//		return resList;
//    }
//	
//	
//
//	public void backtracking(List<String> list, int n, int index) {
//		if(index == n) {
//			resList.add(new ArrayList<>(list));
//		}
//		
//		for(int i = index;i<n;i++) {
//			if(!isRepeat(list, index, i)) {
//				Collections.swap(list, index, i);
//				backtracking(list, n, index+1);
//				if(index == i) continue;
//				Collections.swap(list, index, i);
//			}
//		}
//	}
//	
//	
//	public boolean isRepeat(List<String> list, int first,int index) {
//		String temp = list.get(index);
//		for(int i= first;i<index;i++) {
//			if(list.get(i) == temp) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	
	
	
}
