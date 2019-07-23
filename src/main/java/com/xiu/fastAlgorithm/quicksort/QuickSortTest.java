package com.xiu.fastAlgorithm.quicksort;
//平均时间复杂度（O(nlogn)）
public class QuickSortTest {

	static int[] sortarray= {10,2,5,2,8,6,1,7};
	public static void main(String[] args) {
		
		
		quicksort(sortarray,0,sortarray.length-1);
		
		for(int a:sortarray) {
			System.out.println(a);
		}
		
	}

	static void quicksort(int[] arr,int low,int high) {
		
		int left,right,temp,t;
        if(low>high){
        	return ;
        }
        left=low;
        right=high;
        //temp就是基准位
        temp = arr[low];
 
        while (left<right) {
            //先看右边，依次往左递减
            while (arr[right] >= temp && left < right) {
                right--;
            }
            //再看左边，依次往右递增
            while (arr[left] <= temp && left < right) {
                left++;
            }
            //如果满足条件则交换
            if (left < right) {
                t = arr[right];
                arr[right] = arr[left];
                arr[left] = t;
            }
        }
        //最后将基准为与left和right相等位置的数字交换
         arr[low] = arr[left];
         arr[left] = temp;
        //递归调用左半数组
         quicksort(arr, low, right-1);
        //递归调用右半数组
         quicksort(arr, right+1, high);
	}
}
