package com.wj.leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {

	
	/*
	 * DFS的递归实现：
	 * 1. 代码看上去更简洁
	 * 2. 递归的时候需要将当前的程序的变量或者状态压入到系统栈里面
	 * 3. 压入和弹出栈都需要较多的时间，如果压入很深的栈，会造成效率低下
	 * 
	 */
	 private int[] dx = new int[]{-1,0,1,0};
	 private int[] dy = new int[]{0,1,0,-1};
     public boolean dfs(int[][] grid,int x,int y) {
    	 //step1: 判断是否到达了,是的话则立即返回
    	 if(x==B[0] && y==B[1]) {
    		 return true;
    	 }
    	 //step2: 标记当前点已经被访问过了
    	 /*
    	  * 标记是否访问过一般有几种方法：
    	  * 1. 直接修改输入的数据，例如：我们这里直接修改为-1，这样isSafe就会判定不会再对它进行处理了
    	  *     这样的好处是不需要额外创建空间来辅助记忆，但是工作中这样的方法不推荐，因为直接修改输入可能会对程序的其他地方造成麻烦
    	  * 2. 利用一个额外的数据结构，可以是矩阵，哈希表，或者哈希集合
    	  *    
    	  */
    	 grid[x][y] = -1;
    	 //step3:在规定的四个方向上进行尝试
    	 for(int d=0; d<4;d++) {
    		  int i = x + dx[d];
    		  int j = y + dy[d];
    		  //如果有一条路径被找到了，则返回true
    		  //isSafe函数实现的是是否越界
    		  if(isSafe(grid,i,j) && dfs(grid,i,j)) {
    			  return true;
    		  }
    	 }
    	 //当尝试了所有的可能都没有抵达目的地，则返回false
    	 return false;
     }
    	 
     
     
     /*
      * DFS的非递归实现：
      * 1. 栈的数据结构也支持压入和弹出
      * 2。利用栈来提高运行效率
      * 
      */
     
     public boolean dfs2(int[][] grid,int x,int y) {
    	//step1:创建一个stack,用来将 要被访问的点压入以及弹出
    	 Stack<Integer[]> stack = new Stack<>();
    	 //step2:将起始点压入stack,并标记它为访问过
    	 stack.push(new Integer[] {x,y});
    	 grid[x][y] = -1;
    	//step3:只要stack不为空，就不断的循环，处理每个点
    	 while(!stack.isEmpty()) {
    		 //step4:从堆栈中取出要处理的点
    		 Integer[] pos = stack.pop();
    		 //step5:判断是否抵达了目的地B,是则返回 true
    		 if(pos[0] == B[0] && pos[1] == B[1]) {
    			 return true;
    		 }
    		//step6: 如果你不是，就从四个方向上去尝试
    		 for(int d=0; d<4; d++) {
    			  int i = x + dx[d];
    			  int j = y + dy[d];
    			//step7: 将各个方向上的点压入堆栈，并标记为访问过
    			 if(isSafe(grid,i,j)) {
    				 stack.push(new Integer[] {i,j});
    				 grid[i][j] = -1;
    		     }
    		 }
    		 //step8: 当尝试了所有的可能，都还没找到B，则返回false
    		 return false;
    	 }
     }
     
     
    
    /*
     * DFS怎么解决迷宫问题？
     * 优化解题思路：
     * 1. 一边寻找目的地，一边记录它和起始点的距离（也就是步数）
     * 2. 当发现从某个方向过来所需要的步数更少， 则更新到这个点的步数
     * 3. 如果发现步数更多，则就不再尝试
     * 
     * 
     */
     public void dfsOptimization(int[][] grid) {
    	 int m = grid.length;
    	 int n = grid[0].length;
    	//step1:除了A之外的零都变成MAX
        for(int i=0;i<m;i++) {
        	for(int j=0;j<n;j++) {
        		if(grid[i][j] == 0 && !(i == A[0] && j == A[1])) {
        			grid[i][j] = Integer.MAX_VALUE;
        		}
        	}
        }
        //step2: 对矩阵进行DFS遍历
        dfs(grid, A[0], A[1]);
        //step3:判断是否抵达B点
        if(grid[B[0]][B[1]] < Integer.MAX_VALUE) {
        	System.out.println("Shortest path count is: " + grid[B[0]][B[1]]);
        }else {
        	System.out.println("Can't find B!");
        }
     }
     
     //dfs具体实现
     public void dfsAFindB(int[][] grid,int x,int y) {
    	 //step1: 判断是否找到目的地B
    	 if(x == B[1] && y == B[1]) return;
    	 //step2:从四个方向上进行尝试
    	 for(int d=0; d<4; d++) {
    		 int i = x + dx[d];
    		 int j = y + dy[d];
    		 //step3: 下个点步数是否大于当前点步数+1
    		 if(grid[i][j ] > grid[x][y] + 1) {
    			 //step4:是则更新下个点步数，并继续DFS
    			 grid[i][j] = grid[i][j] + 1;
    			 dfs(grid, i, j);
    		 }
    	 }
     }
     
     
     
     /*
      * DFS 复杂度分析
      * 由于DFS是图论里面的算法，分析DFS解题的复杂度时，应借助图的思想，图有两种表达方式：
      * 1. 邻接表（图中有V个定点，E条边）
      *    访问所有顶点的时间复杂度为O(V),查找所有顶点邻居的时间为O(E),所以总的时间为O(V+E)
      * 2. 邻接矩阵（图中有V个定点，E条边）
      *    查找每个顶点的邻居都需要O(V)的时间，所以查找整个矩阵就需要O(v^2)的时间
      *    
      * 利用DFS在迷宫里找一条路径：
      *    由于迷宫用矩阵表示，所以假设它是一个M行N列的矩阵
      *    时间复杂度为O(M*N)：
      *       一共有M*N个顶点，所以时间复杂度是M*N
      *    空间复杂度为O(M*N)：
      *       DFS需要堆栈来辅助，最坏的情况下每个顶点都需要被压入栈，所以时间复杂度为O(V)，即O(M*N)
      *    
      * 
      */
     
     
}
