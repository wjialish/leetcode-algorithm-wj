package com.wj.leetcode;

public class FindLeftBottomTreeValue {

	/*
	 * maxdeep记录最大深度
	 * 
	 */
//    int MaxDeep = 0;
//    int value = 0;
//	public int findBottomLeftValue(TreeNode root) {
//		value = root.val;
//		helper(root, 0, 0);
//		return value;
//    }
//	
//	public void helper(TreeNode root, int depth,int pre) {
//		if(root == null) {
//			if(depth>MaxDeep) {
//				MaxDeep = depth;
//				value = pre;
//			}
//			return;
//		}
//		helper(root.left, depth+1, root.val);
//		helper(root.right, depth+1, root.val);
//	}
	
	
	/*
	 * 递归，中序遍历，找到最深层，将左边第一个元素保存到结果中
	 */
	int MaxDeep = -1;
	int result = 0;
	public int findBottomLeftValue(TreeNode root) {
		helper(root, 0);
		return result;
    }
	
	public void helper(TreeNode root, int depth) {
		if(root == null) return;
		helper(root.left, depth+1);
		//判断是都是最大深度
		if(depth>MaxDeep) {
			MaxDeep = depth;
			result = root.val;
		}
		helper(root.right, depth+1);
	}
	
	
	/*
	 * 一般的遍历是每层从左到右，遍历到最右的元素就是最右下角的元素
	 * 如果反过来，每层从右到左进行遍历，最后一个就是最左下角的元素。
	 * 
	 */
	int res = 0;
	/*
	 * 该解答方法不对
	 */
	public int findBottomLeftValue2(TreeNode root) {
		
		if(root == null) {
			return 0;
		}
		findBottomLeftValue(root.right);
		res = root.val;
		findBottomLeftValue(root.left);
		
		return res;

    }
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){this.val=x;}
	}
}
