package com.wj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TwoCityScheduling {
	
	public static void main(String[] args) {
		int[][] costs=new int[][] {{10,20},{30,200},{400,50},{30,20}};
		int res=twoCitySchedCost5(costs);
		System.out.println(res);
		//System.out.println(costs[0].length);
		//System.out.println(costs.length);
	}
	
	
	public static int twoCitySchedCost5(int[][] costs) {
		
	    if(costs.length==0 || costs[0].length==0)
	    	return 0;
	    
	    int sum=0;
	    
	    int[] diff=new int[costs.length];
	    for(int i=0;i<costs.length;i++) {
	    	//A 比 B多花的
	    	diff[i]=costs[i][0]-costs[i][1];
	    	//把所有的都派往城市A
	    	sum+=costs[i][0];
	    }
	    Arrays.sort(diff);
	    for(int i=costs.length-1;i>=costs.length/2;i--) {
	    	sum-=diff[i];
	    }
	    
	    return sum;
	}

	/*
	 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

       Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
       
	 *  Input: [[10,20],[30,200],[400,50],[30,20]]
	 *  sums[10,30,400,30]
	 *  diff[10 170 -350 -10]
	 *  
		Output: 110
		Explanation: 
		The first person goes to city A for a cost of 10.
		The second person goes to city A for a cost of 30.
		The third person goes to city B for a cost of 50.
		The fourth person goes to city B for a cost of 20.
		
		The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
		
		Note:
		
		    1 <= costs.length <= 100
		    It is guaranteed that costs.length is even.
		    1 <= costs[i][0], costs[i][1] <= 1000

	 */
	
	
	/*[[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
			Output
			1706
			Expected
			1859*/
	
	public static int twoCitySchedCost4(int[][] costs) {
		// Handling edge cases.
	        if(costs.length == 0 || costs[0].length == 0){
	            return 0;
	        }
			// Initializing array with length of costs.
	        int[] diff = new int[costs.length];
	        int sum = 0;
	        for(int i = 0; i <diff.length;i++){
			// Sending everyone to City A and record costs difference
			// if sending to City B instead of City A. 
	            sum+=costs[i][0];
	            diff[i] = costs[i][1]- costs[i][0];
	        }
			// Sorting array.
	        Arrays.sort(diff);
			// Sending the first N people with lowest costs to B.
	        for(int i = 0; i < diff.length/2;i++){
	            sum+=diff[i];
	        }
	        return sum;
	    }
	
	
	
	public int twoCitySchedCost(int[][] costs) {
        int sol;
        int A = 0; int B = 0; int minCost = 0;
        PriorityQueue<Integer> Asavings = new PriorityQueue<Integer>(costs.length);
        PriorityQueue<Integer> Bsavings = new PriorityQueue<Integer>(costs.length);
        for(int i = 0; i < costs.length; i++) {
            if(costs[i][0] > costs[i][1]) {
                Bsavings.add(costs[i][0] - costs[i][1]);
                B++;
                minCost+= costs[i][1];
            }
            else if(costs[i][0] < costs[i][1]) {
                Asavings.add(costs[i][1]-costs[i][0]);
                A++;
                minCost+=costs[i][0];
            }
            else {
                Asavings.add(0);
                A++;
                minCost+=costs[i][0];
            }
        }
            // Assume A >= B
            if(B > A) {
                PriorityQueue<Integer> temp = Asavings;
                int temp2 = A;
                Asavings = Bsavings;
                Bsavings = temp;
                A = B;
                B = temp2;
            }
            while(A-B>0) {
                minCost+=Asavings.poll();
                A--;
                B++;
            }
            return minCost;
      
    }	
	
	public int twoCitySchedCost2(int[][] costs) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->{
		return cal(b)-cal(a);
		});
		int ans = 0;
		int index = 0;
		int n = costs.length;
		List<Integer> cityA = new ArrayList<>();
		List<Integer> cityB = new ArrayList<>();
		List<Integer> ansList = new ArrayList<>();
		for(int i[] : costs){
		q.offer(i);
		}
		int sizeA = 0;
		int sizeB = 0;
		int b = n/2;
		while(!q.isEmpty() && sizeA<b && sizeB<b){
		int[] a = q.poll();
		int val = Math.min(a[0],a[1]);
		if(a[0]==val){
		cityA.add(val);
		sizeA++;
		}
		else{
		cityB.add(val);
		sizeB++;
		}
		}
		if(cityA.size()<n/2){
		while(!q.isEmpty()){
		int a[] = q.poll();
		int m = a[0];
		cityA.add(m);
		}
		}
		else{
		while(!q.isEmpty()){
		int a[] = q.poll();
		int m = a[1];
		cityB.add(m);
		}
		}
		int val1 = 0;
		for(int i:cityA){
		val1 += i;
		}
		int val2 = 0;
		for(int i:cityB){
		val2 += i;
		}
		ans = val1+val2;
		return ans;
		}
		public int cal(int[] a){
		int ans = Math.abs(a[0]-a[1]);
		return ans;
		}
			
	public static int twoCitySchedCost3(int[][] costs) {
	      
	     int res=0;
	     
	     for(int i=0;i<costs.length;i++) {
	    	 res+=Math.min(costs[i][0], costs[i][1]);
	     }
	     
	     return res;
		
	}
	
}
