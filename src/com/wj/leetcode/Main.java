package com.wj.leetcode;

import java.util.concurrent.CountDownLatch;

public class Main {

	
	public static void main(String[] args) {
		
		
		CountDownLatch cdl=new CountDownLatch(1);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<5000;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						cdl.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			
          System.out.println("i= "+i);
			
		}
		
		
		/*for(int i=0;i<10;i++) {
			System.out.println("hello world "+i);
		}*/
	}
}
