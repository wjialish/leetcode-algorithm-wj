package com.wj.leetcode.backtracking;

public class WildCardMatching_44 {

	/*
	 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

	 */
	
	/*
	 * 思考：怎么检查星号的所有可能性？
	 *       匹配0个字符
	 *       匹配1个字符
	 *       匹配2个字符
	 *       。。。
	 *       匹配所有剩余的字符
	 *       
	 *       让我们从匹配0个字符开始，如果这个假设导致不匹配，就回溯：回到前一个星号，假设它匹配1个字符，然后继续。
	 *       若又是不匹配的情况，再次回溯，回到上一星号，假设匹配两个字符，等等。
	 *       
	 * 算法：
	 *    我们用s_idx遍历输入字符串，用p_idx遍历输入字符模式，
	 *    当s_idx < s_len：
	 *    1)如果字符模式仍有字符p_idx < p_len 且指针下的字符匹配 p[p_idx] = s[s_idx], 或p[p_idx] == "?", 则两个指针向前移动。
	 *    2)反之如果字符模式仍有p_idx < p_len 且p[idx] == "*",则首先检查匹配o字符的情况，即只增加模式指针p_idx++.
	 *       记下可能回溯的位置 star_idx 和当前字符串的位置 s_tmp_index 
	 *    3)反之如果出现不匹配的情况：
	 *       1)如果字符模式中没有*号，则返回false
	 *       2)如果有星号，则回溯：设置p_idx = star_idx+1 和 s_idx = s_tmp_index+1 , 假设这次的星匹配多个字符，则可能回溯为 s_tmp_dx = s_idx
	 *    4)如果字符模式剩余的所有字符都是星号，则返回true
	 *    
	 *    
	 */
	
	
	public boolean isMatch(String s ,String p) {
		int slen = s.length();
	    int plen = p.length();
	    		
	    int sIdx = 0; //遍历输入字符串
	    int pIdx = 0; //遍历输入字符模式
	    
	    int starIdx = -1;// 可能回溯的位置
	    int sTmpIdx = -1;//当前字符串的位置
	    
	    while(sIdx < slen) {
	    	//如果字符模式中仍有字符pIdx < plen 且 指针下的字符匹配p[pIdx] = s[sIdx] 或者 p[pIdx] = '?'则两个指针向下移动
	    	if(pIdx < plen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx) )){
	    		sIdx++;
	    		pIdx++;
	    	}else if(pIdx < plen && p.charAt(pIdx) == '*') {
	    		starIdx = pIdx;  //记录下可能回溯的位置
	    		sTmpIdx = sIdx;  //当前字符串的位置
	    		pIdx++; //模式指针向前移动
	    	}else if(starIdx == -1) {// 没明白 没有*号 为什么这种表达
	    		return false;
	    	}else {
	    		//如果碰到星号，则回溯
	    		pIdx = starIdx + 1;
	    		sIdx = sTmpIdx + 1;
	    		//如果*匹配多个字符，则回溯为：
	    		sTmpIdx = sIdx;
	    	}
	    }
	    
	    //剩余的字符应该都是* 否则返回false
	    for(int i = pIdx;i<plen;i++) {
	    	if (p.charAt(i) != '*') {
	    		return false;
	    	}
	    }
	    
	    return true;
	    		
	}

	
	public boolean isMatch2(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		
		int sIdx = 0; //遍历输入字符串
		int pIdx = 0;  //遍历输入字符模式
		
		int starIdx = -1; //可能回溯的位置
		int sTmpIdx = -1; //当前字符串的位置
		
		while(sIdx < slen) {
			if(pIdx < plen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
				sIdx++;
				pIdx++;
			}else if(pIdx < plen && p.charAt(pIdx) == '*') {
				//check tthe situation
				//when '*' matches no character
				starIdx = pIdx;
				sTmpIdx = sIdx;
				pIdx++;
			}else if(starIdx == -1) {//if the pattern character != string character or pattern is used up and there was no '*' character in pattern
				return false;
			}else {
				//backtrack: check the situation
				//when '*' matches one more character
				pIdx = starIdx+1;
				sIdx = sTmpIdx+1;
				sTmpIdx =sIdx;
			}
		}
		
		
		//the remianing character in the pattern should all be "*" characters
		for(int i = pIdx;i<plen;i++) {
			if(p.charAt(i) != '*') {
				return false;
			}
			
		}
		return true;
    }
	
	
	
//	public boolean isMatch(String s, String p) {
//	    int sLen = s.length(), pLen = p.length();
//	    int sIdx = 0, pIdx = 0;
//	    int starIdx = -1, sTmpIdx = -1;
//
//	    while (sIdx < sLen) {
//	      // If the pattern caracter = string character
//	      // or pattern character = '?'
//	      if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))){
//	        ++sIdx;
//	        ++pIdx;
//	      }
//	      // If pattern character = '*'
//	      else if (pIdx < pLen && p.charAt(pIdx) == '*') {
//	        // Check the situation
//	        // when '*' matches no characters
//	        starIdx = pIdx;
//	        sTmpIdx = sIdx;
//	        ++pIdx;
//	      }
//	      // If pattern character != string character
//	      // or pattern is used up
//	      // and there was no '*' character in pattern 
//	      else if (starIdx == -1) {
//	        return false;
//	      }
//	      // If pattern character != string character
//	      // or pattern is used up
//	      // and there was '*' character in pattern before
//	      else {
//	        // Backtrack: check the situation
//	        // when '*' matches one more character
//	        pIdx = starIdx + 1;
//	        sIdx = sTmpIdx + 1;
//	        sTmpIdx = sIdx;
//	      }
//	    }
//
//	    // The remaining characters in the pattern should all be '*' characters
//	    for(int i = pIdx; i < pLen; i++)
//	      if (p.charAt(i) != '*') return false;
//	    return true;
//	  }

	
	
	
	
}
