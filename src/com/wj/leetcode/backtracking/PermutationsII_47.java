package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII_47 {

	/*
	 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

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
	
	
	List<List<Integer>> resList = new ArrayList<List<Integer>>();
	Set<List<Integer>> set = new HashSet<List<Integer>>();//这地方改成treeset就不行
	
	
	
	
	
	public List<List<Integer>> permuteUnique(int[] nums) {

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
}
