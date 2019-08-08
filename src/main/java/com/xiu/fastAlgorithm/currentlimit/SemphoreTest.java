package com.xiu.fastAlgorithm.currentlimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 *Semphore可以控制某个资源可被同时访问的线程数，计数器法 
 *
 */
public class SemphoreTest {

	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(5);
		
		for(int i=0;i<20;i++) {
			
			final int no =i;
			Runnable runnable = new Runnable() {
				
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("Accessing:"+no);
						semaphore.release();
						System.out.println("*"+ semaphore.availablePermits());
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
			
			executorService.execute(runnable);
		}
		
		executorService.shutdown();
	}
	
}



























