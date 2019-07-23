# 计数排序
## 1.基本思想

计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外数组空间中。作为一种线性时间复杂度的排序，技术排序要求
输入的数据必须是有确定范围的整数

## 2.算法描述

	花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
	
	开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1)
	
	数组 B 中 index 的元素记录的值是 A 中某元素出现的次数
	
	最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/countingsort/image/countingsort.gif)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	