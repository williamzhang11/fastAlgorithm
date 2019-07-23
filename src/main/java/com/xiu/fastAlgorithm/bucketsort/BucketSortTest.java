package com.xiu.fastAlgorithm.bucketsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BucketSortTest {

	
	public static void main(String[] args) {
		
		Integer[] sortarray= {101,10,2,5,2,8,6,1,7};
        List<Integer> asList = Arrays.asList(sortarray);
        
        List<Integer> after = bucketSort(asList,1);
		
		for(int a:after) {
			System.out.println(a);
		}   
		
		
	}
	
	public static List<Integer> bucketSort(List<Integer> array,int bucketSize){
		
		if(array == null || array.size() < 2)
			return array;
		
		int max = array.get(0),min = array.get(0);
		
		//1.找到最大值最小值
		for(int i=0;i<array.size();i++) {
			if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
		}
		
		
		int bucketCount = (max - min)/bucketSize +1;
		List<ArrayList<Integer>> bucketArr = new ArrayList<ArrayList<Integer>>(bucketSize);
		
		ArrayList<Integer> resultArr = new ArrayList<Integer>();
		
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
		
		//将数组中的元素分配给桶里，按数值分配位置
		for(int i =0;i<array.size();i++) {
			bucketArr.get((array.get(i)-min)/bucketSize).add(array.get(i));
		}
		
		for(int i=0;i < bucketCount;i++) {
			if(bucketSize == 1) {
				for(int j=0;j<bucketArr.get(i).size();j++) {
					resultArr.add(bucketArr.get(i).get(j));
				}
			}else {
				
				if(bucketCount ==1) {
					bucketSize --;
				}
				List<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
				for(int j =0;j < temp.size();j++)
					resultArr.add(temp.get(j));
				
			}
		}
		
		return  resultArr;
	}
	
	
	
}



















