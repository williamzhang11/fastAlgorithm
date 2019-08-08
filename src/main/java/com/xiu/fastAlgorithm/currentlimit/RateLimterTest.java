package com.xiu.fastAlgorithm.currentlimit;

import com.google.common.util.concurrent.RateLimiter;
/**
 * 
 *令牌算法：RateLimiter
 *
 * 1、使用acquire()方法，如果没有可用令牌，会一直阻塞直到有足够的令牌。
2、使用tryAcquire()方法，如果没有可用令牌，就直接返回false。
3、使用tryAcquire()带超时时间的方法，如果没有可用令牌，就会判断在超时时间内是否可以等到令牌，如果不能，就返回false，如果可以，就阻塞等待。

 *
 */
public class RateLimterTest {

	public static void main(String[] args) {
		//每秒产生2个令牌
		final RateLimiter limiter = RateLimiter.create(2.0);
		
		//trywithRateLimter(limiter);
		
		notTrywithRateLimter(limiter);
	}
	
	
	/**
	 * 模拟每秒产生2个令牌，没有令牌放弃
	 * @param limiter
	 */
	public static void trywithRateLimter(final RateLimiter limiter) {
		
		System.out.println("start:"+System.currentTimeMillis()/1000);
		
		for(int i=0; i<10;i++) {
			
			new Thread(new Runnable() {
				
				public void run() {
					
					if(limiter.tryAcquire()) {
						limiter.acquire();
						System.out.println("pass"+System.currentTimeMillis()/1000);
					}
					
					
				}
			}).start();
		}
		
	}
	/**
	 * 模拟每秒仅仅允许二个请求，没有令牌则阻塞等待
	 * @param limiter
	 */
	public static void notTrywithRateLimter(final RateLimiter limiter) {
		
		System.out.println("start:"+System.currentTimeMillis()/1000);
		for(int i=0; i<10;i++) {
			
			new Thread(new Runnable() {
				
				public void run() {
					limiter.acquire();
					System.out.println("pass"+System.currentTimeMillis()/1000);
					
				}
			}).start();
		}
		
		
	}
	
	
}
