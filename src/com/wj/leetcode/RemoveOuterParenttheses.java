package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RemoveOuterParenttheses {

	public static void main(String[] args) {
		String s="(()())(())(()(()))";
		System.out.println(removeOuterParentheses(s));
	}
	
	/*
	 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

 

Example 1:

Input: "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".

Example 2:

Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".

Example 3:

Input: "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".

	 */
	
	
	
	/*
	 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Remove Outermost Parentheses.
       Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Remove Outermost Parentheses.
	 */
	 public static String removeOuterParentheses3(String S) {
	     //String s="";
	     char[] c=S.toCharArray();
	     int count=0;
	     //int rightcount=0;
	     StringBuilder sb=new StringBuilder();
	     StringBuilder sbres=new StringBuilder();
	     for(char tmp:S.toCharArray()) {
	    	if(tmp=='(') {
	    		count++;
	    		if(count!=1) {
	    		   sb.append(tmp);
	    		}
	    	}else {
	    		count--;
	    		if(count!=0) {
		    		   sb.append(tmp);
		    	}
	    	}
	 
	     }
	     
	     return sbres.toString();
	    		 
	 }
	 
	 
	 
	 public static String removeOuterParentheses(String S) {
	     int leftcount=0;
	     int rightcount=0;
	     StringBuilder sb=new StringBuilder();
	     StringBuilder sbres=new StringBuilder();
	     for(char tmp:S.toCharArray()) {
	    	if(tmp=='(') {
	    		leftcount++;
	    	}else {
	    		rightcount--;
	    	}
	    	
	    	if(leftcount==rightcount) {
	    		sb.deleteCharAt(0);
		    	sb.deleteCharAt(sb.length()-1);
	    		sbres.append(sb);
	    		sb=new StringBuilder();
	    	}
	     }
	     
	     return sbres.toString();
	    		 
	 }
	 
	 
	 
	
	 public static String removeOuterParentheses2(String S) {
	     //String s="";
	     char[] c=S.toCharArray();
	     int leftcount=0;
	     int rightcount=0;
	     List<StringBuilder> list=new ArrayList<StringBuilder>();
	     StringBuilder sb=new StringBuilder();
	     for(char tmp:c) {
	    	if(tmp=='(') {
	    		leftcount++;
	    	}else {
	    		rightcount++;
	    	}
	    	sb.append(tmp);
	    	if(leftcount==rightcount) {
	    		list.add(sb);
	    		sb=new StringBuilder();
	    	}
	     }
	     sb=new StringBuilder();
	     for(StringBuilder ss:list) {
	    	 ss.deleteCharAt(0);
	    	 ss.deleteCharAt(ss.length()-1);
	    	 sb.append(ss);
	     }
	     return sb.toString();
	    		 
	 }
}
