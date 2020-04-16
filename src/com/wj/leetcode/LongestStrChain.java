package com.wj.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class LongestStrChain {
	private static AtomicInteger count=new AtomicInteger(0);
	
public static void main(String[] args) {
		
		
		
		String[] s=new String[] {"a","b","ba","bca","bda","bdca"};
		String[] s2=new String[] {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
		System.out.println(longestStrChain(s2));
		
		
		
	}
	

	/*
	 * Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".

 

Note:

    1 <= words.length <= 1000
    1 <= words[i].length <= 16
    words[i] only consists of English lowercase letters.

	 */


	public static int longestStrChain(String[] words) {
		int res=1;
		Map<String, Integer> map=new HashMap<>();
		Map<Integer, Set<String>> ws=new HashMap<>();
		int maxLen=0;
		for(String s:words) {
			map.put(s, 1);
			maxLen=Math.max(maxLen, s.length());
			if(!ws.containsKey(s.length())) {
				ws.put(s.length(), new HashSet<>());
			}
			ws.get(s.length()).add(s);
		}
		
		for(int i=maxLen;i>=1;i--) {
			int next=i-1;
			if(!ws.containsKey(next)) continue;
			Set<String> nextSet=new HashSet<>();
			nextSet=ws.get(next);
			for(String w:ws.get(i)) {
				for(int j=0;j<w.length();j++) {
					StringBuilder sbBuilder=new StringBuilder(w);
					String nextWord=sbBuilder.deleteCharAt(j).toString();
					if(nextSet.contains(nextWord)) {
						int currentVal=Math.max(map.get(w)+1, map.get(nextWord));
						res=Math.max(res, currentVal);
						map.put(nextWord, currentVal);
					}
				}
			}
		}
		return res;
		
	}
	
	public static int longestStrChain2(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, Set<String>> ws = new HashMap<>();
        int maxLen = 0;
        for(String w : words) {
            maxLen = Math.max(maxLen, w.length());
            map.put(w, 1);
            if(!ws.containsKey(w.length())) ws.put(w.length(), new HashSet<>());
            ws.get(w.length()).add(w);
        }
        int res = 1;
        for(int i = maxLen; i >= 1; i--) {
            int next = i - 1;
            if(!ws.containsKey(next)) continue;
            Set<String> nextSet = ws.get(next);
            for(String w : ws.getOrDefault(i, new HashSet<>())) {
                for(int j = 0; j < w.length(); j++) {
                    StringBuilder sb = new StringBuilder(w);
                    String nextWord = sb.deleteCharAt(j).toString();
                    if(nextSet.contains(nextWord)) {
                        int currVal = Math.max(map.get(nextWord), map.get(w) + 1);
                        res = Math.max(currVal, res);
                        map.put(nextWord, currVal);
                    }
                }
            }

        }
        return res;
    }
	
	
    public static int longestStrChainXX(String[] words) {
        int res=0;
        int[] alp=new int[26];
        int[] cur=new int[26];
       
        for(char c:words[0].toCharArray()) {
        		alp[c-'a']++;
        }
        
        
        for(int i=1;i<words.length;i++) {
        	for(char c:words[i].toCharArray()) {
        		if(cur[c-'a']==0) {
        			cur[c-'a']++;
        		}	
        	}
        	for(int j=0;j<26;j++) {
        		alp[j]=Math.max(alp[j], cur[j]);
        		cur[j]=0;
        	}
        }
        
        for(int i=0;i<26;i++) {
        	res+=alp[i];
        }
        return res;
    }
}
