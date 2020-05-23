package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Permutations_46 {

	/*
	 * Given a collection of distinct integers, return all possible permutations(排列).

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


	 */
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		List<List<Integer>> resList = new ArrayList<List<Integer>>();
		Permutations_46 permu = new Permutations_46();
		resList = permu.permute(nums);
		for(List<Integer> list : resList) {
			System.out.println(list);
		}
		
	}
	/*
	 * backtracking (recursion and constraints / brute force search and pruning)
	 * three basic steps:
	 * 1. choose-- choose current path and mark visit
	 * 2. expore/backtracking  --- transfer dfs()
	 * 3. un-choose  -- remove last element from currentlist and mark it unvisit
	 */
	//m1
	public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> resList = new ArrayList<>();
        if(nums.length < 1) return resList;
        boolean[] isvisited = new boolean[nums.length];
        dfs(resList,new ArrayList<Integer>(), nums,isvisited);
        return resList;
    }


	/*
	 * why we need isvisit arr?
	 * in permutation, all the num need to cur once and only once, so they must be in order
	 * [1,2,3]
	 *    [1,2,3]
	 *    [1,3,2]
	 *    [2,1,3]
	 *    [2,3,1]
	 *    [3,1,2]
	 *    [3,2,1]
	 * eg: on the pos of index 0, there is 1, so on the pos of index 1, there are two choose(2 and 3),and on the pos of index of 2, there is only one choice
	 *     so we use isvisit to mark it.
	 *     //dajiahao zhehsi yige zhenao
	 *     
	 */
    private void dfs(List<List<Integer>> resList,List<Integer> curlist,int[] nums,boolean[] isvisited){
        //how do we decide whether or not to add curlist or reslist
        //all the permutation should have the same size
        //once the cur size equal to nums length, add it!
        if(curlist.size() == nums.length){
            resList.add(new ArrayList<Integer>(curlist));
            return;
        }

        /*
         * every time we fill in a new num, 
         * 1. we use a forloop for index 0 to traverse(遍历)
         * 2. if no visit, we'll put the element to the curlist
         * 3. recursive the function of dfs
         * 4. un-choose current element
         * 5. remove this element from curlist
         * 
         */
        for(int i = 0; i<nums.length;i++){
            if(isvisited[i]) continue;
            curlist.add(nums[i]);
            isvisited[i]= true;
            dfs(resList, curlist, nums, isvisited);
            curlist.remove(curlist.size()-1);
            isvisited[i] = false;
        }
       
    }
	
	
	
	
	
	
	
	//m2:
	List<List<Integer>> resList = new ArrayList<List<Integer>>();
	
	private boolean[] used;
	
	
	
	public List<List<Integer>> permute2(int[] nums) {

		List<Integer> num_list = new ArrayList<>();
		for(int num:nums) {
			num_list.add(num);
		}
		
		int n = num_list.size();
		
		used = new boolean[n];
		backtracking(n,num_list,0);
		
		return resList;
    }
	
	

	
	public void backtracking(int n, List<Integer> num_list,int first) {
		//if all integer are used up, put the num_list into resList,end recursion
		if(first == n) {
			//resList.add(num_list);//XXXX有问题
			resList.add(new ArrayList<>(num_list));
		}
		
		for(int i = first;i<n;i++) {
			//place i-th integer first
			//in the current permutations
			Collections.swap(num_list, first, i);
			//use the next integer to complete the permutation
			backtracking(n, num_list, first+1);
			//backtrack
			Collections.swap(num_list, first, i);
		}
	}
	
	
	
	
	
	
	
	
}
