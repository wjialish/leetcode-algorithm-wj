package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.wj.leetcode.BinaryTreeInorderTraversal.TreeNode;

public class SameTree {

	/*
	 * 100. Same Tree
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
	 */
	//List<Integer> list = new ArrayList<Integer>();
	public boolean isSameTree(TreeNode p, TreeNode q) {
 
		if(p == null && q == null) return true;
		
		if(p != null && q !=null && p.val==q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}else {
			return false;
		}
    }
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){this.val = x;}
	}
}
