package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LetterCombinationOfAPhoneNumber_17 {
	
/*
 * 17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:说不定会分解成的、纯度按 donned收到出扽入付出时间我们农村的】监督和那不行 重新被白蛇传说实习医生 ：必须素 粗大小编也没叙述 

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].


 */
	
	List<String> resList = new ArrayList<String>();
	
	//this type is not correct, why?
//	Map<Character, String> map = new HashMap<Character, String>(){{
//		put('2', "abc");
//		put('3', "def");
//		put('4', "ghi");
//		put('5', "jkl");
//		put('6', "mno");
//		put('7', "pqrs");
//		put('8', "tuv");
//		put('9', "wxyz");
//	}};
		
	
	Map<String, String> map = new HashMap<String, String>(){{
		put("2", "abc");
		put("3", "def");
		put("4", "ghi");
		put("5", "jkl");
		put("6", "mno");
		put("7", "pqrs");
		put("8", "tuv");
		put("9", "wxyz");
	}};
	
	
	
	public static void main(String[] args) {
		LetterCombinationOfAPhoneNumber_17 lc = new LetterCombinationOfAPhoneNumber_17();
		List<String> list = lc.letterCombinations("23");
		for(String s: list) {
			System.out.println(s);
		}
	}
	
	public List<String> letterCombinations(String digits) {

	    if(digits.length()!=0) {
	    	backtrack("", digits);
	    }
		return resList;
		
    }
	
	
	public void backtrack(String combinations,String next_digits) {
		//if there is no more digits to check 
		if(next_digits.length()==0) {
			//the combination is done
			resList.add(combinations);
		}else {//if there are still digits to check
			//iterator over all letters which map
			//the next available digits
			String digit = next_digits.substring(0,1);
			String letters = map.get(digit);
			for(int i =0;i<letters.length();i++) {
				String letter = map.get(digit).substring(i,i+1);
				//append the current letter to combination and proceed to next digit
				backtrack(combinations+letter, next_digits.substring(1));
			}
		}
		
	}


		
}
