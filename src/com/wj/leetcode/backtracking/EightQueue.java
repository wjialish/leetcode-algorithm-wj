package com.wj.leetcode.backtracking;


public class EightQueue {
	
	private int[] column; //同栏是否有皇后，1表示有
    private int[] rup; //右上至左下是否有皇后
    private int[] lup; //左上至右下是否有皇后
    private int[] queen; //解答
    private int num; //解答编号
	   
	public EightQueue() {
		column=new int[8+1];
		rup=new int[2*8+1];
		lup=new int[2*8+1];
		for(int i=1;i<=8;i++) {
			column[i]=0;
		}
		for(int i=1;i<=(2*8);i++) {
			rup[i]=lup[i]=0;
		}
		queen=new int[8+1];
		
	}
	   
	   

	public void backtrack(int i) {
		if(i>8) {
			showAnswer();
		}else {
			for(int j=1;j<=8;j++) {
				if(column[j]==0 && rup[i+j]==0 && lup[i-j+8]==0) {
					queen[i]=j;
					column[j] = rup[i+j] = lup[i-j+8] = 1;//设定为占有
					backtrack(i+1);
					column[j] = rup[i+j] = lup[i-j+8] = 0;
				}
			}
		}
	}
	

	private void showAnswer() {
		// TODO Auto-generated method stub
		num++;
		System.out.println("解答编号："+num);
		for(int i=1;i<=8;i++) {
			for(int j=1;j<=8;j++) {
				if(queen[i]==j) {
					System.out.print("Q");
				}else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	} 
	
   
	   
	public static void main(String[] args) {
		EightQueue eightQueue=new EightQueue();
		//eightQueue.backtrack(1);
		int a = 1<<8;
		int b = a-1;
		System.out.println(a);
		System.out.println(b);
		int res = num(8);
	    //System.out.println(res);
	}
	
	
	
	//回溯法解决八皇后问题
	public static int num(int n) {
		if(n<1)
			return 0;
		int[] record = new int[n];
		return backtracking(0, record, n);
	}
	
	public static int backtracking(int i,int[] record, int n) {
		if(i ==n ) {
			return 1;
		}
		int res=0;
		for(int j=0;i<n;j++) {
			if(isValid(record, i, j)) {
				record[i]=j;
				res += backtracking(i+1, record, n);
			}
		}
		return res;
	}
	
	public static boolean isValid(int[] record, int i, int j) {
		for(int k=0;k<i;k++) {
			if(Math.abs(i-k) == Math.abs(j-record[k]) || j==record[k]) {
				return false;
			}
		}
		return true;
	}
	
	
	
	//最优解，位运算
	public int num2(int n) {
		//因为本方法中位运算的载体是int型变量，所以该方法智能计算1～32皇后问题
		//如果想计算更多的皇后问题，需使用包含更多位的变量
		if(n < 1 || n > 32) {
			return 0;
		}
		int upperLim = n == 32 ? -1: (1>>n)-1;
		return backtracking2(upperLim,0,0,0);
	}
	public int backtracking2(int upperLim,int colLim,int leftDiaLim,int rightDiaLim) {
		if(colLim == upperLim) {
			return 1;
		}
		int pos=0;
		int mostRightOne=0;
		pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
		int res = 0;
		while(pos != 0) {
			mostRightOne = pos & (~pos +1);
			pos = pos - mostRightOne;
			res += backtracking2(upperLim, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}
	
	
}
