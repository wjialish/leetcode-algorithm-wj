package com.wj.leetcode.backtracking;

import java.rmi.Remote;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
/**
 * @author wenjiali
 *
 */
public class PalindromePartitioning_131 {

	/*
	 * Given a string s, partition s such that every substring of the partition is a palindrome(回文数).

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

	 */
	
	/*
	 * 
	 * 回溯，优化（使用动态规划预处理数组）
	 * 
	 * 思考：如何根据这颗递归树编码
	 * 1. 每一个节点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀。
	 * 2. 产生前缀字符串的时候，判断前缀字符串是否是回文数
	 *    如果是回文数，则产生分支和节点；
	 *    如果不是回文数，则不产生分支和节点，这一步是剪枝操作。
	 * 3. 在叶子节点是空字符串的时候结算，从跟节点到叶子结点的路径，就是结果集中的一个字集，使用深度优先遍历，记下所有的结果。
	 * 
	 */
	
	/*
	 *  该题就是怎么分割整个字符串，可以使的每个字串都是回文数
	 */
	 List<List<String>> resList = new ArrayList<List<String>>();
	 
	 public List<List<String>> partition(String s) {

		// List<String> list = new ArrayList<>();
		 Deque<String> list = new ArrayDeque<>();
		 backtracking(list, s, 0);
		 return resList;
		 
	 }
	 
	 
	 
	 public void backtracking(Deque<String> list, String s,int start) {
		 //结束条件，如果每一个都是回文数，就加入到返回列表中
//		 for(String s : list) {
//			 if(!isPalindrome(new StringBuilder(s))) {
//				 return;
//			 }
//		 }
		//结束条件，遍历到字符串的最后一位
		 if(start == s.length()) {
			 resList.add(new ArrayList<>(list));
		 }
		 
		 //遍历整个字符串
		 for(int i = start;i<s.length();i++) {
			 //判断是否是回文数，不是，则剪枝
			 if(!isPalindrome(s, start, i)) {
				 continue;
			 }
			 
			 //如果是，加入到结果集中,因为这里需要add remove list无序，考虑用deque
//			 list.add(s.substring(start,i+1));
//			 backtracking(list, s, i+1);
//			 // list.remove(index)
//			 list.remove(s.length()-1);
			 list.addLast(s.substring(start,i+1));
			 backtracking(list, s, i+1);
			 // list.remove(index)
			 list.removeLast();
		 }
		 
	 }
	 
	 
	 
	 //判断是否是回文数方法一：采用传字串索引的方式
	 /*
	  * 时间复杂度是O(n), 因此，可以采用动态规划先把回文子串的结果记录在一个表格里
	  * @param left 子串的左边界，可以取到
	  * @param right 子串的右边界，可以取到
	  */
	 public boolean isPalindrome(String str, int left, int right) {
		 //严格小于即可
		 while(left < right) {
			 if(str.charAt(left) != str.charAt(right)) {
				 return false;
			 }
			 left++;
			 right--;
		 }
		 return true;
	 }
	 
	 
	 
	 
	 //判断是否是回文数方法二：
	 public boolean isPalindrome2(StringBuilder sb) {
		 StringBuilder sb2 = sb.reverse();
		 for(int i =0 ;i<sb.length();i++) {
			 if(sb.charAt(i) != sb2.charAt(i)) {
				 return false;
			 }
		 }
		 return true;
	 }
}
