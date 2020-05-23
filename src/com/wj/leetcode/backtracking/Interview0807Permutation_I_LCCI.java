package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Interview0807Permutation_I_LCCI {

	
	/*
	 * Write a method to compute all permutations of a string of unique characters.

Example1:

 Input: S = "qwe"
 Output: ["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
Example2:

 Input: S = "ab"
 Output: ["ab", "ba"]

	 */
	public String[] permutation(String S) {
        List<String> resList = new ArrayList();
        
        char[] arr = S.toCharArray();

        dfs(resList,new StringBuilder(), arr,new boolean[arr.length]);

        String[] resArr = resList.toArray(new String[resList.size()]);

        return resArr;
         
    }


    private void dfs(List<String> resList, StringBuilder sb, char[] arr,boolean[] isvisit){
        if(sb.length() == arr.length){
            resList.add(sb.toString());
            return;
        }

       
        for(int i=0;i<arr.length;i++){
        	if(isvisit[i]) continue;
        	 
            sb.append(arr[i]);
            isvisit[i] = true;
            dfs(resList,sb,arr,isvisit);
            isvisit[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
