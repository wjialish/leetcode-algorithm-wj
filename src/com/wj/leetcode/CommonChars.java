package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CommonChars {

	
	public static void main(String[] args) {
		int a='a'+3;
		System.out.println(a);
		
		char c=(char)a;
		System.out.println(c);
		
		String s=String.valueOf(a)+a;
		System.out.println(s);
		
		int ina=Integer.parseInt(s);
		
	}
	
	
/*
 * Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]

 */
	
    /*public List<String> commonChars(String[] A) {
       
    	List<String> res=new ArrayList<>();
    	
    	for(char c: A[0].toCharArray()) {
    		res.add(""+c);
    	}
    	
    	
    	for(int i=1;i<A.length;i++) {
    		
    		for(char c: A[i].toCharArray()) {
    			if(res.contains(c)) {
    				
    			}
    		}
    	}
    	
    	return res;
    	
   
    }*/
    
	public List<String> commonChars(String[] A) {
		List<String> res=new ArrayList<>();
		
		int[] initial=new int[26];
		int[] compare=new int[26];
		for(char c: A[0].toCharArray()) {
			initial[c-'a']++;
		}
		
		for(int i=1;i<A.length;i++) {
			for(char ch:A[i].toCharArray()) {
				compare[ch-'a']++;
			}
			
			for(int j=0;j<26;j++) {
				initial[j]=Math.min(initial[j], compare[j]);
				compare[j]=0;
			}
			
		}
		
	    for(int i=0;i<26;i++) {
	    	while(initial[i]>0) {
	    		//char 转String Charactor.toString-->实际上是直接调用了String.valueOf（‘a’）方法
	    		/*int tmp='a'+i;
	    		char c = (char)tmp;
	    		res.add(String.valueOf(c));*/
	    		res.add(Character.toString((char)('a'+i)));
	    		initial[i]--;
	    	}
	    }
		
		return res;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
    /*public List<String> commonChars(String[] A) {
        // use two frequency arrays
        // 'freqs' holds our answer as we build it
        // 'compare' holds each String as we iterate
        // as we inspect it
        int[] freqs = new int[26];
        int[] compare = new int[26];
        
        // initialize the 'freqs' array with the frequencies
		// found in the first String
        String first = A[0];
        for (char c : first.toCharArray()) {
            freqs[c - 'a']++;
        }

        for (int i = 1; i < A.length; i++) {
			// build the 'compare' array with frequency of
			// each character occurring in the current String
            for (char c : A[i].toCharArray()) {
                compare[c - 'a']++;
            }
			// whittle the 'freqs' values down by using Math.min()
			// with the frequencies found in the current String
			// also reset the 'compare' array for next iteration
            for (int j = 0; j < 26; j++) {
                freqs[j] = Math.min(freqs[j], compare[j]);
                compare[j] = 0;
            }
        }
		
		// at this point, 'freqs' contains the minimum occurrence
		// frequency for each character a-z across all the Strings
        
        // construct the required List<String> answer
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            while (freqs[i]-- > 0) {
                result.add(Character.toString((char)('a' + i)));
            }
        }
        return result;
    }*/
}
