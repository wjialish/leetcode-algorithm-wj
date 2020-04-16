package com.wj.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

	
	/*
	 * Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

	 */
	
	 /*
	  * 思考：此题相当于给定一个前序遍历的树，返回一个中序遍历的树
	  */
	 public List<Integer> inorderTraversal(TreeNode root) {
           /*
            * 基于栈的中序遍历
            */
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while(cur!=null || !stack.isEmpty()) {
			if(cur!=null) {//当遍历到最左子树最小的叶子节点的时候，cur==null
				stack.push(cur);
				cur = cur.left;
			}else {
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}
		
		return list;
		 
	 }
	 
	 //注意声明的list要放到方法外面，因为里面有递归方法
	 List<Integer> list = new ArrayList<Integer>();
	 
	 public List<Integer> inorderTraversal2(TreeNode root) {
         /*
          * 基于递归的中序遍历
          */
		if(root==null) {
			//注意：这里不能用 return null;
			return Collections.emptyList();
		}
		inorderTraversal(root.left);
		list.add(root.val);
		inorderTraversal(root.right);
		
		return list;
		 
	 }
	 
	 
	 public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 public TreeNode(int x) {
			val=x;
		}
	 }
}
