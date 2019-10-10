package com.xiu.fastAlgorithm.abcprint;

public class PrintABCTest {

	public static void main(String[] args) {
		
		final PrintABC printABC = new PrintABC(10);
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					printABC.printA();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					printABC.printB();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					printABC.printC();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
		
		
	}
}
