package com.xiu.fastAlgorithm.mergesort;

import java.util.Arrays;
//平均时间复杂度O(logn)
public class MergeSortTest {

	static int[] sortarray = {10,2,5,2,8,6,1,7};
	
	
	public static void main(String[] args) {
		
		int[] after = mergeSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
	}
	//先分解再合并
	public static int[] mergeSort(int[] array) {
		if(array.length<2) return array;
		
		int mid = array.length/2;
		int[] left = Arrays.copyOfRange(array, 0, mid);
		int[] right = Arrays.copyOfRange(array, mid, array.length);
		return merge(mergeSort(left), mergeSort(right));
	}
	//按照顺序合并的过程
	static int[] merge(int[] left,int[]right) {
		
		int[] result = new int[left.length+right.length];
		
		for(int index = 0,i=0,j=0;index < result.length;index++) {
			
			if(i>=left.length) {
				result[index]= right[j++];
			}else if(j>=right.length) {
				result[index] = left[i++];
			}else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
