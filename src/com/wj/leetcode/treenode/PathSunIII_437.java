package com.wj.leetcode.treenode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import com.wj.leetcode.treenode.PathSum_112.TreeNode;

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
	
	
	
	public int pathSum(TreeNode root, int sum) {

		if(root == null) {
			return 0;
		}
		//获取所有的path 需要root+left+right
		
		//由于每一个node都可以作为起始node 和 结束 node，所以还需要加上左边和右边
		return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
		
    }
	
	
	public int dfs(TreeNode root,int sum) {
		//递归的出口
		if(root == null) {
			return 0;
		}
		
		//sum -= root.val;
		
		//root+left+right
		// root: sum == 0 ? 1:0
		//left: dfs(root.left, sum - root.val)
		//right: dfs(root.right, sum - root.val)
		return (sum-root.val == 0 ? 1 :0 )+ dfs(root.left, sum-root.val) + dfs(root.right, sum-root.val);
	}
	
	
	//or
	
	public int dfs2(TreeNode root,int sum) {
		//递归的出口
		if(root == null) {
			return 0;
		}
		
		sum -= root.val;
		
		//root+left+right
		// root: sum == 0 ? 1:0
		//left: dfs(root.left, sum - root.val)
		//right: dfs(root.right, sum - root.val)
		return (sum == 0 ? 1 :0 )+ dfs(root.left, sum) + dfs(root.right, sum);
	}
	
	/*
	 * 判断pathsum1和这道题的区别：
	 * 1. 不需要从root开始 也不需要在leaf结束，所以if( root.left == null && root.right == null) 这个条件就不需要满足了
	 * 2. 获取num  不仅仅判断存在
	 * 
	 * 
	 */
	
	//以下是pathsum1的题答案
//	public boolean hasPathSum(TreeNode root, int sum) {
//
//		if(root == null) {
//			return false;
//		}
//		
//		return dfs(root, sum);
//		
//    }
//	
//	//pathSum1是判断有没有这样的路径，存不存在问题
//	public boolean dfs(TreeNode root,int sum) {
//		if(root == null) return false;
//		if((sum - root.val) == 0 && root.left == null && root.right == null) {
//			return true;
//		}
//		return dfs(root.left, sum-root.val) || dfs(root.right, sum-root.val);
//	}
	
	
	
	
	
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			// TODO Auto-generated constructor stub
			val = x;
		}
		public TreeNode() {
			// TODO Auto-generated constructor stub
		}
	}
}
