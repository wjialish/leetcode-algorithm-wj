package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesies {
	
	
	public static void main(String[] args) {
		List<String> res=new ArrayList<String>();
		res=generateParenthesis(3);
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
	
	
	
	
	public static List<String> generateParenthesis(int n) {
       List<String> reslist=new ArrayList<>();
       
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public static boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }*/
    
    
    
    
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
