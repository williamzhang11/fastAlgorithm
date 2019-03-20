package com.xiu.fastAlgorithm.primenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 流程：
 *第一步：线程池初始化，任务份数初始化
 *第二步：计算每一份任务的上下界限，并将每一份任务封装为Callable对象
 *第三步：线程池执行线程，线程中进行素数计算
 *第四步：从线程池返回的Future中获取计算结果
 *
 *抽象出2个对象，线程对象，任务是分配任务，线程操作
 *素数操作工具对象，任务是提供素数算法
 */

//线程工具
public class MultithreadingTest extends PrimeNumberCalcu{
	
	private int nThreads; //线程池初始化数量
	
	private int taskNums;//任务份数
	
	
	public MultithreadingTest(int nThreads, int taskNums) {
		this.nThreads = nThreads;
		this.taskNums = taskNums;
	}
	
	@Override
	public Integer countPrimeNums(int number) {
		
		int count =0;
		List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>();
		
		int numsPerTask = number/taskNums;//每一份执行的数量
		
		for(int i=0;i<taskNums;i++) {
			
			final int begin = i * numsPerTask +1;
			final int end= numsPerTask*(i+2) > number ? number:(i==numsPerTask-1)? number:begin +numsPerTask-1;
			System.out.println("每一份的长度："+(end -begin));
			System.out.println("begin:"+begin +"end:"+end);
			
			Callable<Integer> callable = new Callable<Integer>() {
				public Integer call() throws Exception {
					return calTatolPrimeNumber(begin,end);
				}
				
			};
			
			callableList.add(callable);
		}
		int countresult = 0;
		try {
			ExecutorService executor = Executors.newFixedThreadPool(nThreads);
			List<Future<Integer>> futures = executor.invokeAll(callableList);
			
			executor.shutdown();
			for(Future<Integer> future :futures) {
				countresult += future.get();
				
			}
			
			return countresult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countresult;
		
	}
	
	public static void main(String[] args) {
		new MultithreadingTest(4, 7).timeAndCompute(100);
	}
	
}
