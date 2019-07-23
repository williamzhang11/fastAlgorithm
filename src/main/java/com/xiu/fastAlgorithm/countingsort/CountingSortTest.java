package com.xiu.fastAlgorithm.countingsort;

public class CountingSortTest {

	static int[] sortarray= {10,2,5,2,8,6,1,7};
	
	public static void main(String[] args) {
		
		int[] after = countingSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
	}
	
	static int[] countingSort(int[] array) {
		
		//1.查找最大值和最小值
		int max = array[0],min = array[0];
		for(int value : array) {
			if(value >max) {
				max = value;
			}
			if(value <min) {
				min = value;
			}
		}
		//2.构建新数组
		int[] newArray = new int[max-min+1];
		//3.统计每个数出现次数，将次数赋予B
		for(int value : array) {
			newArray[value-min]+=1;
		}
		
		//4.遍历输出
		int[] result = new int[array.length];
		
		int index =0;//记录最终数组的小标
		
		for(int i=0;i<newArray.length;i++) {
			
			for(int j=0;j<newArray[i];j++) {
				result[index++]=i+min;
			}
		}
		
		return result;
	}
}







































