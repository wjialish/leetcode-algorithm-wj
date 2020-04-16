package com.wj.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenttheses {

	public static void main(String[] args) {
		String s="{[]}";
		System.out.println(isValid(s));
	}
	/*
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false

Example 4:

Input: "([)]"
Output: false

Example 5:

Input: "{[]}"
Output: true


	 */
	
	 public static boolean isValid(String s) {
		 //将字符串中每个元素分隔
		 char[] arr = new char[s.length()];
		 
		 // 存放对应的括号值
         Map<Character, Character> map = new HashMap<Character,Character>();
         map.put(')', '(');
         map.put(']', '[');
         map.put('}', '{');
         
         //用栈来判断括号是否有效
         Stack<Character> stack = new Stack<Character>();
         
         for(char c : s.toCharArray()) {
        	 if(stack.isEmpty()) {
        		 stack.push(c);
        	 }else {
        		 // if(stack.peek().equals(map.get(c)))
        		 if(map.get(c) == stack.peek()) {
        			 stack.pop();
        		 }else {
        			 stack.push(c);
        		 }
        	 }
         }
         
         return stack.isEmpty();
	 }
	 
	 
	 public boolean isValid2(String s) {
		 //将字符串中每个元素分隔
		 char[] arr = new char[s.length()];
		 
		 /*
		  * 思考：
		  * 为减少时间和空间复杂度，怎么可以不使用map
		  */
         
         //用栈来判断括号是否有效
         Stack<Character> stack = new Stack<Character>();
         
         for(char c : s.toCharArray()) {
        	 if(c == '(') {
        		 stack.push(')');
        	 }else if(c == '[') {
        		 stack.push(']');
        	 }else if(c == '{') {
        		 stack.push('}');
        	 }else if(stack.isEmpty() || c != stack.pop()){
        		 return false;
        	 }
         }
         
         return stack.isEmpty();
	 }
	  
	
	
	
    public boolean isValid3(String s) {
        Stack<Character> stack=new Stack<Character>();
        for(char ch:s.toCharArray()) {
        	if(stack.isEmpty()) {
        		stack.push(ch);
        	}else {
	            if(stack.peek().equals(reverse(ch))) {
	        	    stack.pop();
	            }else {
	        		stack.push(ch);
	        	}
        	}
        }
        
        if(stack.isEmpty()) {
        	return true;
        }
        
        return false;
    }
    
    public Character reverse(Character s) {
    	if(s.equals(')')) {
    		return '(';
    	}else if(s.equals(']')) {
    		return '[';
    	}else if(s.equals('}')){
    		return '{';
    	}else {
    		return ' ';
    	}
    }
}
