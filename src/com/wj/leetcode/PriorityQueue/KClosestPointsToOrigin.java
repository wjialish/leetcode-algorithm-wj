package com.wj.leetcode.PriorityQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

	
	/*
	 * 973. K Closest Points to Origin
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
	 */
	
	
	//方法一：排序
	 public int[][] KClosest(int[][] points,int K){
		 /*
		  * 思路：将所有点到原点的距离进行排序，然后输出距离最近的k个点
		  * 算法：创建一个距离数组，然后排序找到第k大的距离，然后我们返回距离小于等于这个第k大距离的k个点
		  */
		 
		int N = points.length;
        int[] dists = new int[N];
        //求每个点到原点的距离
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
         
	 }
	 
	 public int dist(int[] point) {
		 return point[0]*point[0] + point[1]*point[1];
	 }
	 
	
	 
	 
	 //方法二：分治法
	 /*
	  * 分治法：
	  * 思想：我们想要一个复杂度比NlogN更低的算法，显然，做到这件事情的唯一办法就是按照题目中可以按照任何顺序返回K个点的条件,
	  *      否则，必要的排序将会花费NlogN的时间
	  *      我们随机选择一个元素x=A[i]然后将数组分为两部分，一部分是到原点距离小于x的，一部分是到原点距离大于等于x的。
	  *      这个快速选择的过程与快速排序中选择一个关键元素将数组分成两部分的过程类似。
	  *      如果我们快速选择一些关键元素，那么每次就可以将问题规模缩减为原来的一半，平均下来时间复杂度也是线性的。
	  * 算法：
	  *     我们定义一个函数work(i,j,k) 它的功能是部分排序（point[i], point[i+1],...ponit[j]）使得最小的k个元素出现在数组的首部，
	  *     也就是(i,i+1,...i+k-1)
	  *     首先，我们从数组中选择一个随机元素作为关键元素，然后使用这个元素将数组分为如上两部分，为了使用线性时间来完成这件事，
	  *     我们需要两个指针i和j  然后将他们移动到放置了位置元素的地方，然后交换这些元素。
	  *     然后我们就有了两个元素[oi,i]和[i+1,oj], 其中[oi,oj]是原来调用函数work(i,j,k)时候(i,j)的值。
	  *     假设第一部分有10个元素，第二部分有15个元素。
	  *     如果k=5的话，只需要对第一部分调用（oi,i,5）
	  *     否则，如果k=17，那么第一部分的10个元素都应被选择，我们只需要对第2部分元素调用work(i+1,oj,7)就可以了。
	  *      
	  */
	 int[][] points;
	 public int[][] kClosest2(int[][] points, int K) {
         this.points = points;
         work(0,points.length-1,K);
         return Arrays.copyOfRange(points, 0, K);
	 }
	 
	 public void work(int low,int high,int k) {
		 if(high <= low) return;
		 //选择最左端元素，将数组分为两部分
		 int pivot = distance(points[low]);
		 int i = low; int j = high;
		 while(i<j) {
			 while(i<j && distance(points[j]) >= pivot) j--;
			 int[] mid = points[i]; points[i] = points[j]; points[j]=mid;
			 while(i<j && distance(points[i]) <= pivot) i++;
			 mid= points[i]; points[i] =points[j]; points[j]=mid;
		 }
		 //分治的两种情况
		 if(i-low+1>=k) work(low,j,k);
		 else work(i+1,high,k-(i-low+1));
	 }
	 
	 
	 public int distance(int[] a) {
		 return a[0]*a[0]+a[1]*a[1];
	 }
	 
	 
	 
	 
	 
	 
	 //方法三：大根堆
	 public int[][] kClosest(int[][] points, int K){
		 int[][] res = new int[K][2];
		 PriorityQueue<int[]> maxheap = new PriorityQueue<int[]>(
				 (o1,o2) -> {
					 return o2[0]*o2[0]+o2[1]*o2[1] - (o1[0]*o1[0]+o1[1]*o1[1]);
				 }
				 );
		 
		 for(int i = 0; i<K; i++) {
			 maxheap.offer(points[i]);
		 }
		 
		 for(int i =K;i<points.length; i++) {
			 int[] p = maxheap.peek();
			 int[] q = points[i];
			 if(p[0]*p[0]+p[1]*p[1] > q[0]*q[0]+q[1]*q[1]) {
				 maxheap.poll();
				 maxheap.offer(q);
			 }
		 }
		 
		 Iterator<int[]> iterator = maxheap.iterator();
		 int i=0;
		 while(iterator.hasNext()) {
			 res[i++] = iterator.next();
		 }
		 return res;
	 }
	 
	 
	 
	     //方法四：小根堆
		 public int[][] kClosest3(int[][] points, int K){
			 int[][] res = new int[K][2];
			 //构建一个小根堆
			 PriorityQueue<int[]> maxheap = new PriorityQueue<int[]>(
					 (o1,o2) -> {
						 return o1[0]*o1[0]+o1[1]*o1[1] - (o2[0]*o2[0]+o2[1]*o2[1]);
					 }
					 );
			 
			 //将所有元素入列
			 for(int i = 0; i<points.length; i++) {
				 maxheap.offer(points[i]);
			 }
			 
			 //将前k个元素出列，就是我们要的结果
			 for(int i =0;i<K; i++) {
				 res[i] = maxheap.poll();
			 }
			 
			 return res;
		 }
	 
}
