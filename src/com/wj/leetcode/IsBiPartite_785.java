package com.wj.leetcode;

import java.lang.Thread.State;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.management.Query;

public class isBipartite {

	/*
	 * 给定一个无向图graph，当这个图为二分图时返回true。

如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。

graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。

	 */
	
	
	/*
	 * 方法一：深度优先搜索DFS着色
	 * 思路：如果节点属于第一个集合，将其着为蓝色，否则为红色。
	 *      只有在二分图的情况下，可是使用贪心思想给图着色：
	 *      一个节点为蓝色，说明它的所有邻接点为红色，所有邻接点的邻接点为蓝色，以此类推。
	 * 算法：使用数组或者哈希表记录每个节点的颜色，color[node],颜色可以为（0，1），或者未着色-1或者null
	 *      搜索节点时，需要考虑图是非连通的情况。对于每个未着色节点，从该节点开始深度优先搜索着色，每个邻接点都可以通过当前节点着相反的色。
	 *      如果存在当前点和邻接点颜色相同，则着色失败。
	 *      使用栈完成深度优先搜索，栈类似与节点的“todolist”,存储着下一个要访问节点的顺序，在graph[node],对每个未着色邻接点，着色该节点将其放入栈中。
	 * 复杂度分析
	 *      时间复杂度：O(N + E)O(N+E)，其中 NN 是节点的数量，EE 是边的数量。着色每个节点时，遍历其所有边。
	 *      空间复杂度：O(N)O(N)，存储 color 的栈。
	 */
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] color = new int[n];
		Arrays.fill(color, -1);
		
		for(int start=0; start<n; ++start) {
			if(color[start] == -1) {
				Stack<Integer> stack = new Stack<>();
				stack.push(start);
				color[start] = 0;
				
				while(!stack.isEmpty()) {
					Integer node = stack.pop();
					for(int nei:graph[node]) {
						if(color[nei] == -1) {
							stack.push(nei);
							color[nei] = color[node]^1;
						}else if(color[nei] == color[node]) {
							return false;
						}
					}
				}
			}
		}
		return true;
    }
	
	
	
	/*
	 * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗 :40.5 MB, 在所有 Java 提交中击败了97.14%的用户
	 */
	private static int[][] mGroup;
	private static int[] groups;
	
	public boolean isBipartite3(int[][] graph) {
		mGroup = graph;
		groups = new int[mGroup.length];
		for(int i=0; i<mGroup.length;i++) {
			if(groups[i]==0) {
				groups[i]=1;
				if(!DFS(mGroup[i], 1)) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean DFS(int[] point,int group) {
		boolean result = true;
		for(int i : point) {
			if(groups[i] == 0) {
				groups[i] = -group;
				result = DFS(mGroup[i], groups[i]);
			}else if(groups[i] == group) {
				return false;
			}
		}
		return result;
	}
	

    public static void main(String[] args) {
		int[][] graph = new int[][]{{1,3}, {0,2}, {1,3}, {0,2}};
		System.out.println(isBipartite2(graph));
	}
	
	
	/*
	 * 方法二：广度优先搜索BFS着色
	 * 思路：如果当前节点的邻接节点未被标记，则将当前节点的邻接节点着为相反色，标记
	 *      如果当前节点的邻接节点已经被标记，检查其着色，若为相同色，返回false
	 */
	private final boolean RED=true;
	private final boolean BLACK=false;
	public static boolean isBipartite2(int[][] graph) {
		if(graph==null || graph.length==0 ) return false;
		boolean[] marked = new boolean[graph.length]; //用于标记
		boolean[] color = new boolean[graph.length];  //用于着色
		for(int i=0;i<graph.length;i++) {
			if(!marked[i]) {
				if(!bfs(i, graph, marked, color))
					return false;
			}
		}
		return true;
    }
	
	
	//图可能不是连通图，因此需进行多次广度优先搜索，确保所有子图被遍历到
	private boolean bfs(int i, int[][] graph, boolean[] marked,boolean[] color) {
		 marked[i]=true;
		 color[i] = RED;
		 Queue<Integer> queue= new LinkedList<>();
		 queue.offer(i);
		 while(!queue.isEmpty()) {
			 int size = queue.size();
			 for(int j=0;j<size;j++) {
				 int s= queue.poll();
				 for(int t:graph[s]) {//检查邻边
					 if(!marked[t]) { //如果未被标记
						 color[t] = !color[s]; //着相反色
						 marked[t] = true;
						 queue.offer(t);
					 }else if(color[t]==color[s])
						 return false; //无法填充二分图
				 }
			 }
		 }
		 return true;//从起点i开始进行广度优先搜索得到的子图可以划分为二分图
	}
	
	
	
	
}
