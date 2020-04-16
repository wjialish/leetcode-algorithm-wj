package com.wj.leetcode;


public class SumRootToLeaf {
	
	public static void main(String[] args) {
		int a=0;
		int b=a<<=1;
		System.out.println(b);
	}

	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
	/*
	 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit. 
	 *  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

 110+111=6+7=13

Example 1:

Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

	 */
	/*
	 * int ans = 0;
    
    // Top-down recursion
    public int sumRootToLeaf(TreeNode root) {
        helper(root, 0);
        return ans;
    }
    
    private void helper(TreeNode root, int pathSum) {
        if (root == null) {
            return;
        }
        
        pathSum <<= 1; // Right-shift one when enter new level
        pathSum += root.val;
        
        // Meet leaf node, path end.
        if (root.left == null && root.right == null) {
            ans += pathSum;
        }
        
        helper(root.left, pathSum);
        helper(root.right, pathSum);
    }
	 */
	 
	 int ans=0;
	 public int sumRootToLeaf(TreeNode root) {
	      helper(root, 0);
	      return ans;		 
	 }
	 
	 private void helper(TreeNode root,int pathSum) {
		 if(root == null) {
			 return;
		 }
		 
		 pathSum <<= 1;
		 pathSum+=root.val;
		 
		 if(root.left==null && root.right==null) {
			 ans+=pathSum;
		 }
		 
		 helper(root.left, pathSum);
		 helper(root.right, pathSum);
	 }
}
