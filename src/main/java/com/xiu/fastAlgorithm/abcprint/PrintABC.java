package com.xiu.fastAlgorithm.abcprint;

import java.util.concurrent.Semaphore;

public class PrintABC {

	private int n ;
	public PrintABC(int n) {
		this.n = n;
	}
	
	Semaphore a = new Semaphore(1);
	Semaphore b = new Semaphore(0);
	Semaphore c = new Semaphore(0);
	
	public void printA() throws InterruptedException {
		
		for(int i = 1; i < n; i++) {
			
			a.acquire();
			System.out.println("A");
			b.release();
			
		}
		
	}
	
	public void printB() throws InterruptedException {
		for(int i = 1; i< n; i++) {
			b.acquire();
			System.out.println("B");
			c.release();
		}
		
	}
	
	public void printC() throws InterruptedException {
		
		for(int i = 1; i< n; i++) {
			c.acquire();
			System.out.println("C");
			a.release();
		}
	}
}
