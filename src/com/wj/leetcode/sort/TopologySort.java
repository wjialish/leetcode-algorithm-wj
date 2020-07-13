package com.wj.leetcode.sort;

import java.util.Queue;

public class TopologySort {


    /*
      应用场合
      拓扑排序就是要将图论里的顶点按照相连的性质进行排序

      前提：
      图必须是有向图
      图里面没有环

      方法：
      1. 对每个顶点统计他们各自的前驱，也就是入度
      2. 选择其中一个没有前驱（也就是入度为0 的顶点）
      3. 将这个点作为结果输出，同时删除掉和这个点相邻的边
      4. 更新顶点的入读表
      5. 循环2-4


      时间复杂度：
      统计顶点的入度需要O(n)的时间
      接下来每个dingdian 被遍历一遍,同样需要 O(n) 的时间
      
    */

    public void topologySort(){
       //adv[] adv链接矩阵来表示这个图的拓扑结构
       //indegree[] 统计每个顶点的入度
       
       //队列q用来保存需要遍历到的顶点
       Queue<Integer> q = new LinkedList<Integer>();

       //将所有入度为0的顶点加入到队列中
       for(int v = 0; v < V; v++){
           if(indegree[v] == 0){
               q.add(v);
           }
       }

       //不断循环，直到队列为空，在每次循环中，从队列中取出顶点，将其打印或保存到结果当中
       while(!q.isEmpty(){
           q.poll();
            print(v);
       }

       //将跟这个顶点相连的其他顶点入度减1，如果发现那个顶点的入度变成了0，将其加入到队列的末尾，等待将来的处理
       for(int u =0; u<adv[v].length;u++){
           if(--indegree[u] == 0){
               q.add(u);
           }
       }

    }
    
}