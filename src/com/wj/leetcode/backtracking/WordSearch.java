package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearch {

	
	/*
	 * 79. Word Search
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
	 */
	
	
	private char[][] _board;
	private String _word;
	private boolean[][] marked;
	private int[][] direction = {{-1,0},{0,-1},{1,0},{0,1}};
	private int m;
	private int n;
	
	public boolean exist(char[][] board,String word) {
		if(board.length == 0)
			return false;
		
		this._board=board;
		this._word=word;
		this.m = board.length;
		this.n = board[0].length;
		
		marked = new boolean[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(dfs(i, j, 0)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	private boolean dfs(int i, int j,int start) {
		if(start == _word.length()-1)
			return _board[i][j] == _word.charAt(start);
		
		if(_board[i][j] == _word.charAt(start)) {
			marked[i][j] = true;
			for(int k=0;k<4;k++) {
				int newX = i + direction[k][0];
				int newY = j + direction[k][1];
				if(inArea(newX, newY) && !marked[newX][newY]) {
					if(dfs(newX, newY, start+1)) {
						return true;
					}
				}
			}
			//！！！pay attention
			marked[i][j] = false;
		}
		return false;
	}
	
	
	
	
	
	
	

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
}
