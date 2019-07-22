package com.xiu.fastAlgorithm.selectionsort;

import java.util.Arrays;
import java.util.List;

//平均时间复杂度：O(n的平方)
public class SelectSortTest {
	static int[] sortarray= {10,2,5,2,8,6,1,7};
	
	public static void main(String[] args) {
		
		int[] after = selectionSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
	}
	
	public static int[] selectionSort(int[] array) {
		
		if(array.length ==0) {
			return array;
		}
		
		for(int i=0;i < array.length;i++) {
			
			int minIndex =i;
			
			for(int j=i;j<array.length;j++) {
				if(array[j] < array[minIndex]) {
					minIndex =j;
				}
			}
			
			int temp = array[minIndex];
			array[minIndex] = array[i];
			array[i] = temp;
			
		}
		
		return array;
		
	}

}
