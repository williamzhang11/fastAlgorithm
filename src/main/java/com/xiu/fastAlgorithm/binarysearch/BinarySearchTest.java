package com.xiu.fastAlgorithm.binarysearch;


//时间复杂度O(logn)
public class BinarySearchTest {

	static int[] sortarray= {1,2,5,6,8,9,10,17};
	public static void main(String[] args) {
		
		System.out.println(binarySearch(sortarray, 3, 0, sortarray.length-1));
	}
    public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
    	
        int midIndex = (beginIndex+endIndex)/2;  //取中间索引
        
        //如果查找的数小于第一位数  或者  查找的数大于最后一位数  或者  起始索引大于结束索引 都说明所查找的数不存在
        if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){
            return -1;
        }
        
        //如果查找的数小于中间索引对应的值说明找的数在左半边，递归调用该查找方法，结束索引为中间索引向左移动一位
        if(data <dataset[midIndex]){
            return binarySearch(dataset,data,beginIndex,midIndex-1);
        //如果查找的数大于于中间索引对应的值说明找的数在右半边，递归调用该查找方法，起始索引为中间索引向右移动一位
        }else if(data>dataset[midIndex]){
            return binarySearch(dataset,data,midIndex+1,endIndex);
        //如果所查找的数正好等于中间索引对应的值，那么就将该索引返回
        }else {
            return midIndex;
        }
    }
}

















