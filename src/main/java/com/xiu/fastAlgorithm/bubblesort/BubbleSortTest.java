package com.xiu.fastAlgorithm.bubblesort;

//平均时间复杂度O(n的平方)
public class BubbleSortTest {

	static int[] sortarray= {10,2,5,2,8,6,1,7};
	public static void main(String[] args) {
		int[] after = bubblesort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
		
	}
	
	public static int[] bubblesort(int[] array) {
		
		if(array.length ==0){
			return array;
		}
		
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array.length-1-i;j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				}
			}
		}
		
		return array;
	}
	
	
	
	
	
	
	
	
	
}
