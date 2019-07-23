package com.xiu.fastAlgorithm.shellsort;

//平均时间复杂度O(nlogn)
public class ShellSortTest {

	static int[] sortarray = {10,2,5,2,8,6,1,7};
	
	public static void main(String[] args) {
		
		int[] after = shellSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
		
	}
	
	static int[] shellSort(int array[]) {
		
		if(array.length==0) {
			return array;
		}
		
		int len = array.length;
		
		int temp, gap = len/2;
		//gap 排序元素间隔
		while(gap >0) {
			
			for(int i=gap;i<len;i++) {
				//
				temp = array[i];
				
				int preIndex = i-gap;
				while(preIndex >=0 && array[preIndex]>temp) {
					array[preIndex+gap] = array[preIndex];
					preIndex -=gap;
				}
				array[preIndex+gap] = temp;
			}
			gap /= 2;
		}
		
		return array;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
