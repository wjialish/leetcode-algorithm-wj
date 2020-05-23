package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesies {
	
	
	public static void main(String[] args) {
		List<String> res=new ArrayList<String>();
		GenerateParenthesies gp = new GenerateParenthesies();
		res=gp.generateParenthesis(3);
		System.out.println(res);
	}

	/*
	 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
()()
(())

	 */

	

	
	
	/*
	 * 回溯法：
	 * 优化：
	 * /*
	 * 该问题的限制：
	 * A. 如果左括号用完了，就不能再加左括号了
	 * B. 当左括号和右括号一样多的时候，这时候再加入右括号就是无效的
	 * 
	 * 如果左括号数量不大于n 我们可以放置一个左括号，如果右括号数量小于左括号 则可以放置右括号
	 * 
	 * 前一行放入左括号后，调用回溯，相当于探索了当前位置放置了左括号的所有解；但是当前位置也可能可以放入右括号，
	 * 所以我们要reset当前位置，再尝试放置右括号
	 *
	 */
	
	
	public List<String> generateParenthesis(int n){
		backtracking(new StringBuilder(), 0, 0, n);
		return resList;
	}
	
	public void backtracking(StringBuilder sb,int open,int close,int n) {
		if(sb.length() == 2* n) {
			resList.add(sb.toString());
		}
		
		if(open<n) {
			sb.append("(");
			backtracking(sb, open+1, close, n);
			sb.deleteCharAt(sb.length()-1);
		}
		
		if(close<open) {
			sb.append(")");
			backtracking(sb, open, close+1, n);
			sb.deleteCharAt(sb.length()-1);
		}
		
		
	}
	
	
	
	
	
	/*
	 * 暴力解法：
	 * 生成第一个字符+加上生成剩余的2个字符，是可以使用递归的
	 * 原因是子问题与原问题有相同的结构
	 */
	
  
	List<String> resList = new ArrayList<String>();
	public List<String> generateParenthesis2(int n) {
		generateAll(new char[2*n], 0);
		return resList;
	}
	
	/*
	 * char类型数组， 直接new String(current)
	 */
	public void generateAll(char[] current,int pos) {
		if(pos == current.length) {
			if(isValid(current)) {
				resList.add(new String(current));

			}
		}else {
			//暴力解法中生成了很多无效的序列，如下
			current[pos] = '(';
			generateAll(current, pos+1);
			current[pos] = ')';
			generateAll(current, pos+1);
		}
		
	}
	
	
	public boolean isValid(char[] current) {
		int balance=0;
		for(char c:current) {
			if(c == '(') {
                balance++;
			}else {
				balance--;
			}
			if(balance<0) return false;
		}
		return balance==0;
	}
	
	
	
    
    
    
    
    public static List<String> generateParenthesisXX(int n) {
        List<String> res=new ArrayList<String>();
        String basicStr="";
        if(n==1) {
        	basicStr="()";
        }
        
        for(String str:generateParenthesisXX(n-1)) {
        	StringBuilder sb=new StringBuilder();
        	sb.append(basicStr).append(str);
        	
        }
        
        
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=i;j++) {
            	sb.append(basicStr);
            }
            
        }
        res.add(sb.toString());
        return res;
    }

}
