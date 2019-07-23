# 归并排序

## 基本思想

归并排序是建立在归并操作上的一种有效排序算法，该算法是采用分治法。
将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序，

## 步骤
	（1）将长度为n的输入序列分为2个长度为n/2的子序列
	（2）对这两个子序列分别采用归并排序；
	（3）将两个排序好的子序列合并成一个最终的排序序列。
	
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/mergesort/image/mergesort.png)



























