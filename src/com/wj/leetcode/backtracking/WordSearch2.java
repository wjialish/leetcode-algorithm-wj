package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wj.leetcode.backtracking.WordSearch.TrieNode;

public class WordSearch2 {

	
	
	/*
	 * 一系列题目：208 79
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.

       Each word must be constructed from letters of sequentially adjacent cell, 
       where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

		Example:
		
		Input: 
		board = [
		  ['o','a','a','n'],
		  ['e','t','a','e'],
		  ['i','h','k','r'],
		  ['i','f','l','v']
		]
		words = ["oath","pea","eat","rain"]
		
		Output: ["eat","oath"]

	 */
	
	
	/*
	 * 方案一
	 */
	private char[][] _board;
	private String _word;
	private boolean[][] marked;
	private int[][] direction = {{-1,0},{0,-1},{1,0},{0,1}};
	private int m;
	private int n;
	
	List<String> resList = new ArrayList<String>();
	public List<String> findWords(char[][] board, String[] words) {
		this._board = board;
		this.m = board.length;
		this.n = board[0].length;
		
		//Step1. Construct the Trie
		TrieNode root = new TrieNode();
		for(String word:words) {
			TrieNode node = root;
			for(Character c: word.toCharArray()) {
				if(node.children.containsKey(c)) {
					node = node.children.get(c);
				}else {
					TrieNode curNode = new TrieNode();
					node.children.put(c, curNode);
					node = curNode;
				}
			}
			//Store words in Trie
			node.word=word;
		}
		
		//Step2: Backtracking starting foreach cell in the board
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(root.children.containsKey(this._board[i][j])) {
					backtracking(i, j, root);
				}
			}
		}
		return this.resList;
	}
	

	private void backtracking(int i,int j, TrieNode root) {
		Character letter = this._board[i][j];
		TrieNode curNode = root.children.get(letter);
		
		//check if there is any match
		if(curNode.word != null) {
			this.resList.add(curNode.word);
			curNode.word = null; //????
		}
		
		//mark the current letter before the exploration
		this._board[i][j] = '#';
		
		for(int k=0;k<4;k++) {
			int newX = i + direction[k][0];
			int newY = j + direction[k][1];
			if(inArea(newX, newY)) {
				if(curNode.children.containsKey(this._board[newX][newY])) {
					backtracking(newX, newY, curNode);
				}
			}
			
		}
		
		//end of exploration, restore the original letter in the board
		this._board[i][j] = letter;
		
		//Optimization: incrementally remove the leaf nodes
		if(curNode.children.isEmpty()) {
			root.children.remove(letter);
		}
		
	}
	
	
	public class TrieNode{
		String word;
		Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		public TrieNode() {
			
		}
	}
	
	
	private boolean inArea(int x, int y) {
		return x>=0 && x<m && y>=0 && y<n;  
	}
	
	
	public static void main(String[] args) {

//      char[][] board =
//              {
//                      {'A', 'B', 'C', 'E'},
//                      {'S', 'F', 'C', 'S'},
//                      {'A', 'D', 'E', 'E'}
//              };
//
//      String word = "ABCCED";


      char[][] board = {{'a', 'a'}};
      String[] words = {"aaa"};
      WordSearch ws = new WordSearch();
      //ws.findWords(board, words);
      List<String> list = new ArrayList<String>();
      list = ws.findWords(board, words);
      for(String s : list) {
    	  System.out.println(s);
      }
  }
	
	
	
	/*
	 * 方案二
	 */
	
	class TrieNode2{
		HashMap<Character, TrieNode> children = new HashMap<Character,TrieNode>();
		String word = null;
		public TrieNode2(){
			
		}
	}
	
	 char[][] _board2=null;
	 List<String> resultList = new ArrayList<String>();
	 public List<String> findWords2(char[][] board,String[] words){
		 //Step1. Construct the Trie
		 TrieNode root = new TrieNode();
		 for(String word:words) {
			 TrieNode node = root;
			 for(Character letter:word.toCharArray()) {
				 if(node.children.containsKey(letter)) {
					 node = node.children.get(letter);
				 }else {
					 TrieNode newNode = new TrieNode();
					 node.children.put(letter, newNode);
					 node = newNode;
				 }
			 }
			 //Store words in Trie
			 node.word = word;
		 }
		 this._board=board;
		 
		 //Step2: Backtracking starting foreach cell in the board
		 for(int row=0;row<board.length;row++) {
			 for(int col = 0; col<board[row].length;col++) {
				 if(root.children.containsKey(board[row][col])) {
					 backtracking2(row, col, root);
				 }
			 }
		 }
		 return this.resultList;
	 }
	 
	 private void backtracking2(int row,int col,TrieNode parent) {
		 Character letter = this._board[row][col];
		 TrieNode currNode = parent.children.get(letter);
		 //check if there is any match 
		 if(currNode.word!=null) {
			 resultList.add(currNode.word);
			 currNode.word=null;  //???
		 }
		 
		 //mark the current letter before the exploration
		 this._board[row][col]='#';
		 
		 //explore neighbor cells in around-clock directions:up right down left 
		 int[] rowOffset = {-1,0,1,0};
		 int[] colOffset = {0,1,0,-1};
		 for(int i=0;i<4;i++) {
			 int newRow = row + rowOffset[i];
			 int newCol = col + colOffset[i];
			 if(newRow < 0 || newRow >= this._board.length || newCol<0 || newCol>= this._board[0].length) {
				 continue;
			 }
			 if(currNode.children.containsKey(this._board[newRow][newCol])) {
				 backtracking2(newRow, newCol, currNode);
			 }
		 }
		 
		 //end of exploration,restore the original letter in th board
		 this._board[row][col] = letter;
		 
		 //Optimization: incrementally remove the leaf nodes
		 if(currNode.children.isEmpty()) {
			 parent.children.remove(letter);
		 }
				 
	 }
	 
	
	 
	 /*
	  * 方案三
	  */
	  public List<String> findWords3(char[][] board, String[] words) {
	        //构建字典树
	        wordTrie myTrie=new wordTrie();
	        trieNode root=myTrie.root;
	        for(String s:words)
	            myTrie.insert(s);

	        //使用set防止重复
	        Set<String> result =new HashSet<>();
	        int m=board.length;
	        int n=board[0].length;
	        boolean [][]visited=new boolean[m][n];
	        //遍历整个二维数组
	        for(int i=0;i<board.length; i++){
	            for (int j = 0; j < board [0].length; j++){
	                find(board,visited,i,j,m,n,result,root);
	            }
	        }
	        System.out.print(result);
	        return new LinkedList<String>(result);
	    }
	  
	  
	    private void find(char [] [] board, boolean [][]visited,int i,int j,int m,int n,Set<String> result,trieNode cur){
	        //边界以及是否已经访问判断
	        if(i<0||i>=m||j<0||j>=n||visited[i][j])
	            return;
	        cur=cur.child[board[i][j]-'a'];
	        visited[i][j]=true;
	        if(cur==null)
	        {
	            //如果单词不匹配，回退
	            visited[i][j]=false;
	            return;
	        }
	        //找到单词加入
	        if(cur.isLeaf)
	        {
	            result.add(cur.val);
	            //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
//	            visited[i][j]=false;
//	            return;
	        }
	        find(board,visited,i+1,j,m,n,result,cur);
	        find(board,visited,i,j+1,m,n,result,cur);
	        find(board,visited,i,j-1,m,n,result,cur);
	        find(board,visited,i-1,j,m,n,result,cur);
	        //最后要回退，因为下一个起点可能会用到上一个起点的字符
	        visited[i][j]=false;
	    }


	}

	//字典树
	class wordTrie{
	    public trieNode root=new trieNode();
	    public void insert(String s){
	        trieNode cur=root;
	        for(char c:s.toCharArray()){
	            if(cur.child[c-'a']==null){
	                cur.child [c-'a'] = new trieNode();
	                cur=cur.child[c-'a'];
	            }else
	                cur=cur.child [c-'a'];
	        }
	        cur.isLeaf=true;
	        cur.val=s;
	    }
	}
	//字典树结点
	class trieNode{
	    public String val;
	    public trieNode[] child=new trieNode[26];
	    public boolean isLeaf=false;

	    trieNode(){

	    }

	
	
}
