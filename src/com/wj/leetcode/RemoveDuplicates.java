package com.wj.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveDuplicates {

	
	public static void main(String[] args) {
		String s="aababaab";


		System.out.println(removeDuplicates5(s));
	}
	
	/*
	 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

 

Note:

    1 <= S.length <= 20000
    S consists only of English lowercase letters.

	 */
	
    public static String removeDuplicates(String S) {
        String res="";
        List<Character> list=new ArrayList<>();
        char[] c=S.toCharArray();
        //list.add(c[0]);
        /*for(int i=0;i<c.length-1;i++) {
        	if(c[i]!=c[i+1]) {
        		list.add(c[i]);
        		if(i==(c.length-2)) {
        			list.add(c[c.length-1]);
        		}
        	}else {
        		i++;
        	} 	
        }*/
        for(int i=0;i<c.length;i++) {
        	list.add(c[i]);
        } 
        
        //if(list.size()==0) return "";
        
        for(int i=0;i<list.size()-1;) {
        	if(list.size()>1 &&list.get(i)==list.get(i+1)) {
        		/*
        		 * note: can't do use the following list.remove(list.get(i));,it can remove the first same value that matched in the array
        		 */
        		//list.remove(list.get(i));
        		//list.remove(list.get(i));
        		list.remove(i);
        		list.remove(i);
        		i=0;
        	}else {
        		i++;
        	}
        }
        
        for(int i=0;i<list.size();i++) {
        	res+=list.get(i);
        }
        return res;
    }
    
    
    
    
    public static String removeDuplicates2(String S) {
        // generate 26 possible duplicates
        HashSet<String> duplicates = new HashSet();
        StringBuilder sb = new StringBuilder();
        for(char i = 'a'; i <= 'z'; ++i) {
          sb.setLength(0);
          sb.append(i); sb.append(i);
          duplicates.add(sb.toString());
        }

        int prevLength = -1;
        while (prevLength != S.length()) {
          prevLength = S.length();
          for (String d : duplicates) S = S.replace(d, "");
        }

        return S;
      }
    
    
    
    //less Time complexity
    public String removeDuplicates3(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for(char character : S.toCharArray()) {
          if (sbLength != 0 && character == sb.charAt(sbLength - 1))
            sb.deleteCharAt(sbLength-- - 1);
          else {
            sb.append(character);
            sbLength++;
          }
        }
        return sb.toString();
      }
    
    
    
    public static String removeDuplicates4(String S) {
        String res="";
        List<Character> list=new ArrayList<>();
        //char[] c=S.toCharArray();
        int sublength=0;
        for(char c:S.toCharArray()) {
        	if(sublength!=0 && c==list.get(sublength-1)) {
        		list.remove(sublength-1);
        		sublength--;
        	}else {
        		list.add(c);
        		sublength++;
        	}
        }
        
        for(int i=0;i<list.size();i++) {
        	res+=list.get(i);
        }
        return res;
    }
    
    
    
    public static String removeDuplicates5(String s) {
        char [] m = s.toCharArray();
        int i = -1;
        int j = 0;
        while(j < m.length) {
            if(i < 0 || m[i] != m[j]) {
                m[++i] = m[j++]; 
            } else {
                i--;
                j++;
            }
        }
   
        return new String(m, 0, i+1);
    }
}
