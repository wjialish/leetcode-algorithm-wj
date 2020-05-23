package com.wj.leetcode.treenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class PathSumII_113 {

	/*
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

	 */
	
	
	List<List<Integer>> resList = new ArrayList<List<Integer>>();
	public List<List<Integer>> pathSum(TreeNode root, int sum) {

		if(root == null) return resList;
		
		Deque<Integer> list = new ArrayDeque<Integer>();
		
		dfs(root, sum, list);
		
		return resList;
		
    }
	
	
	
	public void dfs(TreeNode root, int sum, Deque<Integer> list) {
		if(root == null) {
			return ;
		}
		
		if(root.val == sum && root.left == null && root.right == null) {
			list.addLast(root.val);
			resList.add(new ArrayList<>(list));
			list.removeLast();
		}
		
		list.addLast(root.val);
		dfs(root.left, sum-root.val, list);
		dfs(root.right, sum-root.val, list);
        list.removeLast();
        
	}
	
	
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			// TODO Auto-generated constructor stub
			val = x;
		}
	}
}
