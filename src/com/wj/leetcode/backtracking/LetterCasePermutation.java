package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import com.wj.leetcode.dfsandbfs.DFS;

public class LetterCasePermutation {

	/*
	 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]

	 */
	
	
	public static void main(String[] args) {
		char c = 'a' ^ (1<<5);
		System.out.println(c);
		
		LetterCasePermutation lc = new LetterCasePermutation();
		List<String> list = lc.letterCasePermutation("A1bc");
		System.out.println(list.size());
		for(String s: list) {
			System.out.println(s);
		}
	}
	
	public List<String> letterCasePermutation(String S) {
        List<String> resList = new ArrayList<>();
        int len = S.length();

        if(len == 0) return resList;

        char[] arr = new char[len];

        dfs(S,resList,new StringBuilder(),arr,0,len);

        return resList;

   }

   private void dfs(String S,List<String> resList, StringBuilder sb, char[] arr,int start,int len){
       if(start == len){
    	   resList.add(new String(arr));
           //resList.add(sb.toString());
           //sb.delete(0, sb.length()-1);
           return;
       }

       //sb.append(arr[start]);
       arr[start] = S.charAt(start);
       dfs(S,resList, sb, arr, start + 1,len);

       if(Character.isLetter(arr[start])){
           arr[start] = (char) (S.charAt(start) ^ (1<<5));
           //sb.append(arr[start]);
           dfs(S,resList,sb,arr,start + 1,len);
       }    
   }
	
	
//	public List<String> letterCasePermutation(String S) {
//        int len = S.length();
//        List<String> res = new ArrayList<>();
//        if (len == 0) {
//            return res;
//        }
//        char[] charArray = new char[len];
//        dfs(S, 0, len, charArray, res);
//        return res;
//    }
//
//    private void dfs(String S, int start, int len, char[] charArray, List<String> res) {
//        if (start == len) {
//            res.add(new String(charArray));
//            return;
//        }
//        charArray[start] = S.charAt(start);
//        dfs(S, start + 1, len, charArray, res);
//
//        // 如果是字符，就可以派生出一个新分支
//        if (Character.isLetter(S.charAt(start))) {
//            // 这一步直接修改，相当于回溯
//            charArray[start] = (char) (S.charAt(start) ^ (1 << 5));
//            dfs(S, start + 1, len, charArray, res);
//        }
//    }

//	public List<String> letterCasePermutation(String S) {
//
//        List<String> resList = new ArrayList<>();
//
//        char[] arr = S.toCharArray();
//
//        dfs(resList,new StringBuilder(),arr,0);
//
//        return resList;
//    }
//
//    private void dfs(List<String> resList, StringBuilder sb,char[] arr,int start){
//        if(start == arr.length){
//            resList.add(sb.toString());
//            return;
//        }
//
//        //one case: toUpperCase
//        //another case: toLowerCase
//        if(arr[start] >= 0 && arr[start] <= 9) {
//        	sb.append(arr[start]);
//        }else {
//        	//sb.append(String.valueOf(arr[start]).toLowerCase());
//        	sb.append(arr[start] ^ (1<<5));
//        }
//        dfs(resList, sb, arr, start + 1);
//        
//       // sb.deleteCharAt(sb.length()-1);
//        
//        
//    }
}
