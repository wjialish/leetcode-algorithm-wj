package com.wj.leetcode.treenode;

import java.util.Stack;

public class PathSum_112 {

	/*
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

	 */
	
	
	
	public boolean hasPathSum(TreeNode root, int sum) {

		if(root == null) {
			return false;
		}
		
		if(root.left == null && root.right == null) {
			return sum - root.val == 0;
		}
		
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
		
    }
	
	
	
//	public boolean backtrack(TreeNode root,int sum,int countSum) {
//		if(root == null) {
//			return false;
//		}
//		
//		Stack<Integer> stack = new Stack<>();
//		stack.push(root.val);
//		countSum+=stack.peek();
//		if(root.left == null && root.right == null && countSum == sum) {
//			return true;
//		}
//		if(root.left != null) {
//			backtrack(root.left, sum, countSum);
//		}
//		if(root.right != null) {
//			backtrack(root.right, sum, countSum);
//		}
//		
//		stack.pop();
//		countSum -= root.val;
//		
//	}
	
	
	
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
}
