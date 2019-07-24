package com.xiu.fastAlgorithm.radixsort;

import java.util.ArrayList;
import java.util.List;

public class RadixSortTest {
	static int[] sortarray = {10,2,5,2,8,6,1,7};
	
	public static void main(String[] args) {
		
		
		
		int[] after = radixSort(sortarray);
		
		for(int a:after) {
			System.out.println(a);
		}
		
		
	}
	
	static int[] radixSort(int[] array) {
		
		if(array == null || array.length <2) {
			return array;
		}
		
		
		int max = array[0];
		//获取最大数
		for(int i=1;i<array.length;i++) {
			max = Math.max(max, array[i]);
		}
		int maxDigit = 0;
		//获取最大数的个位，以及位数
		while(max != 0) {
			max/=10;
			maxDigit ++;
		}
		
		
		int mod = 10, div=1;
		List<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<10;i++) {
			bucketList.add(new ArrayList<Integer>());
		}
		
		
		for(int i=0;i < maxDigit; i++, mod *=10, div *=10) {
			
			for(int j=0;j<array.length;j++) {
				int num = (array[j]%mod)/div;
				bucketList.get(num).add(array[j]);
			}
			
			int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }

		}
		
		return array;
	}
}





























