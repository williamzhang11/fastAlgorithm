# 基数排序

## 1.基本思想
	
基数排序是按照低位先排序，然后收集，再按照高位排序，然后收集。依此类推，直到最高位。

## 2.算法描述

	1.取得数组中的最大数，并取得位数
	2.arr为原始数组，从最低位开始取每个位组成radix数组
	3.对radix进行计数排序

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/radixsort/image/radixsort.gif)