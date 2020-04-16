package com.wj.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UniquePathIII {

	
	
	/*
	 * 980. 不同路径 III
在二维网格 grid 上，有 4 种类型的方格：

1 表示起始方格。且只有一个起始方格。
2 表示结束方格，且只有一个结束方格。
0 表示我们可以走过的空方格。
-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。

 

示例 1：

输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
输出：2
解释：我们有以下两条路径：
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
示例 2：

输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
输出：4
解释：我们有以下四条路径： 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
示例 3：

输入：[[0,1],[2,0]]
输出：0
解释：
没有一条路能完全穿过每一个空的方格一次。
请注意，起始和结束方格可以位于网格中的任意位置。
	 */
	
	/*
	 * 方案一
	 */
	int[][] grid;
	int row;
	int col;
	int ei,ej; //结束点坐标end是个二维点
	int ans;
	int[][] directions = {{-1,0},{0,-1},{1,0},{0,1}};
	
	public int uniquePathsIII(int[][] grid) {
		this.grid=grid;
		row = grid.length;
		col = grid[0].length;
		
		int todo = 0;
		int  si= 0; int sj=0; //start坐标是个二维点
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(grid[i][j] != -1) {
					todo++; //接下来剩余的步骤
				}
				if(grid[i][j] == 1) { //起始方格
					si = i;
					sj = j;
				}else if(grid[i][j] == 2){ //结束方格
					ei = i;
					ej = j;
				}
			}
			
		}
		ans=0;
		dfs(si, sj, todo);
		return ans;
	}

	
	public void dfs(int i,int j,int todo) {
		todo--;
		if(todo<0) return;
		if(i == ei && j == ej) {
			if(todo==0)
				ans++;
			return;
		}
		
		grid[i][j] = 3;
		
		for(int k =0; k<4;k++) {
			int ni = i + directions[k][0];
			int nj = j + directions[k][1];
			if(inArea(ni, nj)) {
				if(grid[ni][nj] %2 == 0) {
					dfs(ni, nj, todo);
				}
			}
		}
		
		grid[i][j]=0;
	}
	
	public boolean inArea(int i, int j) {
		return i>=0 && i<row && j>=0 && j<col;
	}
	
	
	
	/*
	 * 方案二
	 */
	int m;
	int n;
	int resNum=0;
	int[] end = new int[2];//终点坐标
	public int uniquePathsIII2(int[][] grid) {
		m = grid.length;
		n = grid[0].length;
		//用来记录这个点是否走过
		boolean[][] used = new boolean[m][n];
		//记录剩余需要走的点数
		int rest = m * n;
		//起始点坐标
		int[] start = new int[2];
		//终点坐标
		//int[] end = new int[2];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j] == -1) {
					used[i][j] = true;
					rest--;
				}else if(grid[i][j] == 1) {
					start[0]=i;
					start[1]=j;
				}else if(grid[i][j] == 2) {
					end[0] = i;
					end[1] = j;
				}
			}
		}
		helper(grid, used, rest, start[0], start[1]);
		return resNum;
	}
	
	private void helper(int[][] grid, boolean[][] used, int rest, int i,int j) {
		//如果走到了终点，剩余需要走的点只生终点了，则满足条件
		if(i == end[0] && j == end[1] ) {
			if(rest == 1) {
				resNum++;
			}
			return;
		}
		//把当前点记为标记走过
		rest--;
		used[i][j]=true;
		//向上走
		if(i-1>=0 && !used[i-1][j])
			helper(grid, used, rest, i-1, j);
		//向下走
		if(i+1<m && !used[i+1][j])
			helper(grid, used, rest, i+1, j);
		//向左走
		if(j-1>=0 && !used[i][j-1])
			helper(grid, used, rest, i, j-1);
		//向右走
		if(j+1<n && !used[i][j+1])
			helper(grid, used, rest, i, j+1);
		
		//把当前点的标记还原
		used[i][j]=false;
		rest++;
	}
	
	
	
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
     
     
    
     
     /*
      * 广度优先搜索BFS:
      * 1. 解决最短路径问题
      * 2. 广度优先的搜索一般是从起始点出发，一层一层地进行
      * 3. 每层当中的点距离起始点的步数都是相同的
      * 
      * 双端BFS
      * 1. 同时从起点和终点开始进行的广度优先搜索称为双端BFS
      * 2. 双端BFS可以大大提高搜索效率
      * 例如需要判断社交程序中两个人之间需要经过多少个朋友介绍才能互相认识
      * 
      * 如何对图进行广度优先遍历呢？
      * 1. 广度优先遍历用到的数据结构是队列Queue
      * 2. 队列的特点是先进先出FIFO
      */
     
     
     /*
      * 如何运用广度优先搜索BFS在迷宫中寻找最短路径
      * 
      */
     
     public void bfs(int[][] grid,int x,int y) {
    	 //step1: 创建一个队列，并将起始点加入队列
    	 Queue<Integer[]> queue = new LinkedList<Integer[]>();
    	 queue.add(new Integer[] {x,y});
    	 //step2: 只要队列不为空，就一直循环下去
    	 while(!queue.isEmpty()) {
    		 //step3: 从队列中取出当前要处理的点
    		 Integer[] pos = queue.poll();
    		 x = pos[0];
    		 y = pos[1];
    		 //step4: 从四个方向上进行BFS搜索
    		 for(int d=0; d<4; d++) {
                  int i = x + dx[d];
                  int j = y + dy[d];
                  //step5:判断下该方向上的点是否已经被访问过了
                  if(isSafe(grid,x,y)) {
                	 //step6: 如果没有被访问过，则记录步数，并加入到队列
                	  grid[i][j] = grid[x][y] + 1;
                	  queue.add(new Integer[] {x,y});
                	  //step7: 当找到目的地的时候，就立即返回
                	  if(i == B[0] && j == B[1])
                		  return;
                  }
    		 }
    	 }
     }
     
     
     
     /*
      * BFS 复杂度分析
      * 由于BFS是图论里面的算法，分析BFS解题的复杂度时，应借助图的思想，图有两种表达方式：
      * 1. 邻接表（图中有V个定点，E条边）
      *    访问所有顶点的时间复杂度为O(V),查找所有顶点邻居的时间为O(E),所以总的时间为O(V+E)
      * 2. 邻接矩阵（图中有V个定点，E条边）
      *    查找每个顶点的邻居都需要O(V)的时间，所以查找整个矩阵就需要O(v^2)的时间
      *    
      * 利用DFS在迷宫里找一条路径：
      *    由于迷宫用矩阵表示，所以假设它是一个M行N列的矩阵
      *    时间复杂度为O(M*N)：
      *       一共有M*N个顶点，所以时间复杂度是O(M*N)
      *    空间复杂度为O(M*N)：
      *       BFS需要借助一个队列，所有顶点都进入队列一次，都弹出队列一次，所以时间复杂度为O(V)，即O(M*N)
      *    
      * 
      */
     
     
     
     
     /*
      * 拓展：从A到B最多允许打通3堵墙，求最短路径的步数
      * 
      * 暴力解法：
      * 
      * 如何将BFS的数量减少？
      * 1. 在不允许打通墙的情况下，只有一个人进行BFS搜索，时间复杂度是n^2
      * 2. 允许打通一堵墙的情况下，分身为两个人进行BFS搜索，时间复杂度是2n^2
      * 3. 允许打通两堵墙的情况下，分身为3个人进行BFS搜索，时间复杂度是3n^2
      * 4. 允许打通三堵墙的情况下，分身为4个人进行BFS搜索，时间复杂度是4n^2
      * 
      * 关键问题： 如果第一个人再遇到了一堵墙，那么他是否需要再次分身呢？不用，如果需要，那就变成暴力搜索了
      *          第一个人怎么告诉第二个人可以去访问这个点呢？把这个点放入队列中就可以了
      *          如何让4个人在独立的平面进行搜索呢？利用一个三维矩阵记录每个层面里的点即可
      *          
      *  
      * 
      */
     
     public int AToBGoThroughTheWall(int[][] grid ,int x,int y,int w) {
    	 //step1: 初始化变量，steps记录步数，z记录Level,用一个队列来存储各个层面的点
    	 int steps = 0, z = 0;
    	 Queue<Integer[]> queue = new LinkedList<>();
    	 //初始化队列的时候除了把起始点A加入到队列，还加入了一个null,这是使用了bfs中的一个小技巧，用来帮助我们记录当前遍历了多少步数
    	 queue.add(new Integer[] {x,y,z});
    	 queue.add(null);
    	 //step2: 用三维布尔数组来存储各个层面的点，用来记录各个层面的点是否被访问过
    	 boolean[][][] visited = new boolean[N][N][W+1]; //??
    	 visited[x][y][z] = true;
    	 //step3: 只要队列不为空，就一直循环下去
    	 while(!queue.isEmpty()) {
    		 Integer[] pos = queue.poll();
    		 //step4: 取出当前点，如遇目的地则返回步数
    		 if(pos != null) {
    			 x = pos[0]; 
    			 y = pos[1];
    			 z = pos[2];
    			 if(x == B[0] && y == B[1]) {
    				 return steps;
    			 }
    			 //step5:如果不是，则朝4个方向尝试下一步
    			 for(int d = 0; d<4;d++) {
    				 int i = x + dx[d];
    				 int j = y + dy[d];
    				 if(!isSafe(grid,,x,y,z,visited)) {
    					 continue;
    				 }
    				 //step6: getLayer函数判断是否遇到了可打通的墙壁
    				 int k = getLayer(grid,w,i,j,z);
    				 if(k>=0) {
    					 visited[i][j][k] = true;
    					 queue.add(new Integer[] {i,j,k});
    				 }
    			 }
    		 }else {
    			 //若当前是null,则继续下一步
    			 steps++;
    			 if(!queue.isEmpty()) {
    				 queue.add(null);
    			 }
    		 }
    	 }
    	 
    	 return -1;
    	 
     }
     
     //如何判断应该通知哪一层的人进行搜索呢？
     public int getLayer(int[][] grid, int w, int x, int y, int z) {
    	 /*
    	  * getLayer的思想：
    	  *    如果当前遇到的是一睹墙，判断当前遇到的墙的个数是否已经超出了规定，如果没有，则将其打通，否则返回-1
    	  */
    	 if(grid[x][y] == -1) {
    		 return z<w ? z+1 : -1;
    	 }
    	 return z;
     }
     
     
     
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
	
	
}
