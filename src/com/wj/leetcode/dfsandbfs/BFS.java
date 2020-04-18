package com.wj.leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	

    
    
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
