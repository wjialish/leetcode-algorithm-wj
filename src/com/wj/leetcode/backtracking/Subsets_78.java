package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {

	/*
	 * Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
	 */
	
	 private List<List<Integer>> resList = new ArrayList<List<Integer>>(); 
	 public List<List<Integer>> subsets(int[] nums) {

		 //backtracking(new ArrayList<>(),nums,0);
		 dfs(new ArrayList<>(), nums, 0);
		 
		 return resList;
		 
	 }
	 
	 
	 

	 //m2
	 private void dfs(List<Integer> curList,int[] nums,int index) {
		 if(index == nums.length) {
			 resList.add(new ArrayList<>(curList));
			 return;
		 }
		 
		 //两种情况“；一种是选当前元素
		 curList.add(nums[index]);
		 dfs(curList, nums, index + 1);
		 //另一种情况是不选当前元素
		 curList.remove(curList.size()-1);
		 dfs(curList, nums, index+1);
	 }
	 
	 
	 
	 
	 //m1
	 //递归三要素：
	 //1.定义：递归需要放进去什么
	 private void backtracking(List<Integer> subset, int[] nums,int index) {
		 //怎么保证是不包含duplicate subset（重复的子集）
		 //2.递归的拆解-怎么拆解成实现的code：对于每一个子集，每次都要把子集放进去
		 //resList.add(subset);//这样写是有问题的，这里是浅拷贝，应该使用深拷贝
		 /*
		  * 深拷贝和浅拷贝的区别：
		  * 深拷贝和浅拷贝只针对于object 和 Arrays 这样的引用类型数组。
		  * 浅拷贝只复制指向某个对象的指针，而不复制对象本身，新老对象还是共享同一块内存。
		  * 深拷贝会另外创建一个一模一样的对象，新对象和原对象不共享内存，修改新对象不会修改到原对象。
		  */
		 resList.add(new ArrayList<>(subset));
		 
		 //怎么得到每一次的subset，我们需要像树一样的把nums展开
//		先把空的子集放进去                                                         []
//		要么取1，要么不取1                 [1]                                                                            []
//		要么取2，要么不取2          [1,2]       [1]                                                                  [2]       []   
//	    要么取3，要么不取3      [1,2,3] [1,2]  [1,3][1]                                                           [2,3][2]    [3][]
		 
		 //这样得到的最后一层就是所有子集的集合
		 //这里取一个element 或者 不取一个element,实际上就是实现的一个递归回溯的过程，也就是一层一层往下往深走，先取1的时候，再2的时候，到最后取完了，
		 //就会回到上一层[1,2],[1,2]也会继续往下取，取完以后再回溯往上到[1],依次类推。
		 
		 //for循环应该从哪一个位置开始forloop，不能每次都从位置0（即1）开始，这里用index来保证每次从index开始
		 for(int i = index;i<nums.length;i++) {
			 subset.add(nums[i]);//把当前的element加进去
			 backtracking(subset, nums, i + 1); //每次往后移一位
			 subset.remove(subset.size()-1); //回溯的过程，这里相当于[1,2]取两次，回溯的时候删掉
		 }
          
		 //3.结束
		 //return;
	 }
	 
	 
	 
	 
}
