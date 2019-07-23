package com.xiu.fastAlgorithm.insertionsort;

//平均时间复杂度O(n的平方)
public class InsertionSortTest {

	static int[] sortarray= {10,2,5,2,8,6,1,7};
	
	public static void main(String[] args) {
		
		
		
		int[] after = insertionSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
	}
	
	public static int[] insertionSort(int[] array) {
		
		if(array.length==0) {
			return array;
		}
		int current;
		//循环每一个元素
		for(int i=0;i < array.length-1;i++) {
			//当前元素
			current = array[i+1];
			//上一个元素索引
			int preIndex =i;
			//如果上一个元素索引大于0，并且当前元素小于上个元素的值，则将上个元素后移一位，循环上个元素直到不满足条件
			while (preIndex >= 0 && current < array[preIndex]) {
				array[preIndex+1] = array[preIndex];
				preIndex --;
			}
			//将当前元素，插入到找到的这个位置
			array[preIndex+1] = current;
		}
		
		return array;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
