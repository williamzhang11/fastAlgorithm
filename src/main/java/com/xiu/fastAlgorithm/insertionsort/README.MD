# 插入排序

## 1.基本思想
	在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数
	也是排好顺序的。如此反复循环，直到全部排好顺序。
 ## 2.算法描述
 
 	(1)从第一个元素开始，该元素可以认为已经被排序
 	(2)取出下一个元素，在已经排序的元素序列中从后向前扫描
 	(3)如果该元素（已排序）大于新元素，将该元素移动下一位置
 	(4)重复步骤3,直到找到已排序的元素小于或等于新元素的位置
 	(5)将新元素插入到该位置后
 	(6)重复步骤2-5
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/insertionsort/image/insertionsort.gif)
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	