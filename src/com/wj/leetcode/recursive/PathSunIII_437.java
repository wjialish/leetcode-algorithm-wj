package com.wj.leetcode.recursive;

import java.util.ArrayDeque;
import java.util.Deque;

public class PathSunIII_437 {

	/*
	 * You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

	 */
	
	//double recursion solution
	
	
	public int pathSum(TreeNode root, int sum) {

		if(root == null) {
			return 0;
		}
		
		
		return Sum(sum, root) + pathSum(root.left, sum) + pathSum(root.right, sum);
		
    }
	
	
	public int Sum(int sum,TreeNode root) {
		if(root == null) {
			return 0;
		}
		
		sum -= root.val;
		
		int result = sum == 0 ? 1: 0;
		
		return result + Sum(sum, root.left) + Sum(sum, root.right);
		
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
