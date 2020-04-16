package com.wj.leetcode;

import java.util.Stack;

public class BitwiseComplement {

	
	public static void main(String[] args) {
		System.out.println(bitwiseComplement4(5));
		int res=0;
		res+=(1*Math.pow(2, 2));
		res=Integer.parseInt("101", 2);
		System.out.println(res);
		
		double d=Math.log10(100.0);
		System.out.println("d="+d);
	}
	
	/*
	 * Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.

The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.  For example, the complement of "101" in binary is "010" in binary.

For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.

 

Example 1:

Input: 5
Output: 2
Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.

Example 2:

Input: 7
Output: 0
Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.

Example 3:

Input: 10
Output: 5
Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.

	 */
	
	
    public static int bitwiseComplement(int N) {
    	int res=0;
    	if(N==0) return 1;
        Stack<Integer> stack=new Stack<Integer>();
        
    	while(N>0) {
        	stack.push(N%2);
        	N/=2;
        }
        
        int length=stack.size();
        
        
        while(!stack.isEmpty()) {
        	if(stack.pop()==1) {
        		length-=1;
        		continue;
        	}else {
        		res+=(1*Math.pow(2, length-1));
        		//res+=(1*(2^(length-1)));
            	length-=1;
        	}
       
        }       
        return res;
    }
    
    
    public static int bitwiseComplement2(int N) {
    	int res=0;
    	/*
    	 * Integer.toBinaryString(N)将N转化为2进制数
    	 */
         String s=Integer.toBinaryString(N);
         //String str="";
         StringBuilder sb=new StringBuilder();
         sb.append("");
         
         for(int i=0;i<s.length();i++) {
        	 if(s.charAt(i)=='0') {
        		 sb.append('1');
        		 //str+='1';
        	 }else {
        		 sb.append('0');
        		 //str+='0';
        	 }
         }
        // System.out.println(sb);
        /*
         * Integer.parseInt("1010", 8);1010 为8进制数，结果是将其转化为十进制--》520
         * Integer.parseInt("1010", 16);1010 为16进制数，结果是将其转化为十六进制--》4112
         * Integer.parseInt("1010", 2);1010 为2进制数，结果是将其转化为十进制--》10
         */
        res=Integer.parseInt(sb.toString(), 2);
 
        return res;
    }
    
    
    
    /*
     *  Here in this solution we subtract the number N with it's next greater all 1's number i.e.
    subtract 5 by 7 or 9 by 15.
    The length of digits if the lowest number of all one's will be floor of log2(N) + 1 i.e. "length";

     */
   
 
        public static int bitwiseComplement3(int N) {
            if(N == 0) return 1;
            /*
             * 换底公式
             * loga(N)=logm(N)/logm(a),
             * log10(55)/log10(2)=log2(55) 
             */
            int length = ((int)(Math.log10(N)/Math.log10(2)) + 1);
            return (int)Math.pow(2, length) - 1 - N;
            
            
            //Math.log10(100);
        }
   
        public static int bitwiseComplement4(int N) {
        	int res=0;
        	if(N==0) return 1;
            Stack<Integer> stack=new Stack<Integer>();
            int initicialN=N;
        	while(N>0) {
            	stack.push(N%2);
            	N/=2;
            }
            
            int length=stack.size();
            
            res=(int)Math.pow(2, length)- 1 - initicialN;
                  
            return res;
        }   
        

} 
