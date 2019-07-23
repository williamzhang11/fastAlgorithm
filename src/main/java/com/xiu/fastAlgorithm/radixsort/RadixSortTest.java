package com.xiu.fastAlgorithm.radixsort;

public class RadixSortTest {
	
	
	public static void main(String[] args) {
		
	}
	
	static int[] radixSort(int[] array) {
		
		if(array == null || array.length <2) {
			return array;
		}
		
		//1.先算出最大数的位数
		int max = array[0];
		
		for(int i=1;i<array.length;i++) {
			max = Math.max(max, array[i]);
		}
		
		int maxDigit = 0;
		
		return array;
	}
}









