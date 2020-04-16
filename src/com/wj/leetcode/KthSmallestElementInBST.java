package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {

	
	/*
	 * 230. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
	 */
	
	
	/*
	 * 前序遍历：根-左-右
	 * 中序遍历：左-根-右
	 * 后序遍历：左-右-根
	 */
	
	public int kthSmallest(TreeNode root, int k) {
        /*
         * 思考：题目中给出的是前序遍历的树，想求最小的第k个元素-》中序遍历比较合适？
         * 根据前序求中序，不太可行！！
         * 
         */
		
		/*
		 * 若查找左子树节点个数为left,整个数所有节点个数为left+right+1
		 * 若k<=left,那么所查找的第k个元素在左子树上
		 * 若k=left+1，那么所查找的第k个元素在根节点上
		 * 若k>left+1，那么所查找的第k个元素在右子树上
		 * 
		 */
		
	    List<Integer> list = new ArrayList<Integer>();
	    int left = findChild(root.left);
	    if(k==left+1) {
	    	return root.val;
	    }else if(k<left) {
	    	return kthSmallest(root.left, k);
	    }else {
	    	return kthSmallest(root.right, k-left-1);
	    }

	}
	
	//查找所有子节点的个数
	public int findChild(TreeNode root) {
		if(root==null) return 0;
		return findChild(root.left)+findChild(root.right)+1;
	}
	
	
	public int kthSmallest2(TreeNode root, int k) {
        /*
         * 思考：题目中给出的是前序遍历的树，想求最小的第k个元素-》中序遍历比较合适？
         * 根据前序求中序，不太可行！！
         * 
         */
		
		/*
		 * 中序遍历，到第k个停下来，根据二叉搜索树的特点，中序遍历的结果就是从小到大排序后的结果
		 */
		
		if(root==null) return 0;
	    List<Integer> list = new ArrayList<Integer>();
	    
	    findK(root, k, list);
	   
	    return list.get(k-1);

	}
	
	public void findK(TreeNode root,int k, List<Integer> list) {
		if(root==null) return;
		if(list.size()==k) return;
		findK(root.left, k, list);
		if(list.size()==k) return;
		list.add(root.val);
		if(list.size()==k) return;
		findK(root.right, k, list);
	}
		
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val=x;}
	}
}
