package com.wj.leetcode;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KodyRrentLock implements Lock {

	//
	AtomicReference<Thread> owner=new AtomicReference<Thread>();
	
	private LinkedBlockingQueue<Thread> waiter=new LinkedBlockingQueue<>();
	
	
	//
	AtomicInteger count=new AtomicInteger(0);
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		int ct=count.get();
		/*if(!ct=0) {
			
		}*/
		
		ReentrantLock 
		Lock lock=new 
		//owner.compareAndSet(expect, update)
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
