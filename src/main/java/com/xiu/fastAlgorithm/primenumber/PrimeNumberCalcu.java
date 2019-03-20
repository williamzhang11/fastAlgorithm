package com.xiu.fastAlgorithm.primenumber;

//素数计算工具
public abstract class PrimeNumberCalcu {

	public Boolean isPrimeNumber(Integer num) {
		for(int i = 2;i< num/2;i++) {
			if(num % i==0) {
				return false;
			}
		}
		return true;
	}
	
	public Integer calTatolPrimeNumber(Integer begin,Integer end) throws Exception {
		
		if(end <begin) {
			throw new Exception("");
		}
		int count =0;
		for(int i = begin ;i<=end ; i++) {
			if(isPrimeNumber(i)) {
				count +=i;
			}
		}
		return count;
	}
	
	public abstract Integer countPrimeNums(int number);
	
	
	public void timeAndCompute(int number) {
		long beginTime =  System.currentTimeMillis();
		
		int count = countPrimeNums(number);
		System.out.println("count:"+count);
		
		long endTime =  System.currentTimeMillis();
		
		System.out.println("花费时间（ms）:"+(endTime-beginTime));
		
		
	}
}
