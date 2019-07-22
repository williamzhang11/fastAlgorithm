# 经典排序算法总结
## 1.概念

将杂乱无章的数据元素，通过一定的方法按关键字顺序排列过程叫排序


## 2.分类
	
非线性实践比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)
因此称为非线性时间比较类排序

线性时间非比较累排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间
运行，因此称为线性时间非比较类排序

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastTech/classicsortsummary/image/sortalg.jpg)

## 3.比较

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastTech/classicsortsummary/image/sortcompare.jpg)

稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面
不稳定：如果a原本在b的前面，而a=b，排序后a可能在b的后面
内排序：所有排序在内存中完成
外排序：由于数据太大，因此把数据放在磁盘中，而排序通过磁盘和内存的数据传输才能进行










