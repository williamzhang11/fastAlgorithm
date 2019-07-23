# 希尔排序

## 1.基本思想

希尔排序也是一种插入排序，它是简单插入排序经过改进后的更高效版本，也称为缩小增量排序，同时算法是冲破O(n的平方)的第一批算法之一。

设待排序元素序列有n个元素，首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，
所有距离为increment的元素放在同一个子序列中，在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，
重复上述子序列划分和排序工作。直到最后取increment=1，将所有元素放在同一个子序列中排序为止。

由于开始时，increment的取值较大，每个子序列中的元素较少，排序速度较快，到排序后期increment取值逐渐变小，
子序列中元素个数逐渐增多，但由于前面工作的基础，大多数元素已经基本有序，所以排序速度仍然很快


## 2.算法描述

	(1)下面给出一个数据列：
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/shellsort/image/shellsort1.png)

	(2)第一趟取increment的方法是：n/3向下取整+1=3（关于increment的取法之后会有介绍）。将整个数据列划分为间隔为3的3个子序列，
	然后对每一个子序列执行直接插入排序，相当于对整个序列执行了部分排序调整。图解如下： 
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/shellsort/image/shellsort2.png)

	(3)第二趟将间隔increment= increment/3向下取整+1=2，将整个元素序列划分为2个间隔为2的子序列，分别进行排序。图解如下：
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/shellsort/image/shellsort3.png)

	(4)第3趟把间隔缩小为increment= increment/3向下取整+1=1，当增量为1的时候，实际上就是把整个数列作为一个子序列进行插入排序，图解如下： 
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/shellsort/image/shellsort4.png)
	
	(5)直到increment=1时，就是对整个数列做最后一次调整，因为前面的序列调整已经使得整个序列部分有序，所以最后一次调整也变得十分轻松，这也是希尔排序性能优越的体现
	
	

https://blog.csdn.net/weixin_37818081/article/details/79202115





























