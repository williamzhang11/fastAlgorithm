# 三大数据分布算法/分布式寻址算法

hash 算法（大量缓存重建）
一致性 hash 算法（自动缓存迁移）+ 虚拟节点（自动负载均衡）
redis cluster 的 hash slot 算法

## 1.hash 算法

来了一个 key，首先计算 hash 值，然后对节点数取模。然后打在不同的 master 节点上。
一旦某一个 master 节点宕机，所有请求过来，都会基于最新的剩余 master 节点数去取模，
尝试去取数据。这会导致大部分的请求过来，全部无法拿到有效的缓存，导致大量的流量涌入数据库。

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/hash.JPG)

## 2.一致性 hash 算法


  在分布式集群中，对机器的添加删除，或者机器故障后自动脱落集群这些操作是分布式集群管理最基本的功能。如果采用常用的hash(object)%N算法，那么在有机器添加或者删除
  后，很多原有的数据就无法找到了.
  
环形Hash空间

按照常用的hash算法来将对应的key哈希到一个具有2^32次方个桶的空间中，即0~（2^32）-1的数字空间。现在我们可以将这些数字头尾相连，想象成一个闭合的环形。如下图
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash.JPG)

把数据(对象)通过一定的hash算法处理后映射到环上

现在我们将object1、object2、object3、object4四个对象通过特定的Hash函数计算出对应的key值，然后散列到hash换上。如下图：

Hash(object1)=key1;Hash(object2)=key2;Hash(object3)=key3;Hash(object4)=key4;


![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash1.JPG)

将机器通过hash算法映射到环上

 在采用一致性哈希算法的分布式集群中将新的机器加入，其原理是通过使用与对象存储一样的Hash算法将机器也映射到换种（一般情况下对机器的hash计算是采用机器的IP或者唯一的别名作为输入值），然后以顺时针的方向计算，将所有对象存储到离自己最近的机器中。

假设现在有NODE1,NODE2,NODE3三台机器中，通过hash算法得到对应的KEY值，映射到环中，其示意图如下：

Hash（NODE1）=KEY1;Hash（NODE2）=KEY2;Hash（NODE3）=KEY3;


![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash2.JPG)


通过上图可以看出对象与机器处于同一个哈希空间中，这样按顺时针转动object1（对象）存储到了NODE1（机器）中,object3（对象）存储到了NODE2（机器）中,object2、object4（对象）存储到了NODE3（机器）中。在这样的部署环境中，hash环是不会变更的，因此，通过算出对象的hash值就能快速的定位到对应的机器中，这样就能找到对象真正的存储位置了。




机器删除与添加

普通hash求余算法最为不妥的地方就是在有机器的添加与删除以后会造成大量的对象存储位置的失效，这样就大大的不满足单调性了。下面来分析一下一致性哈希算法是如何处理的。

1、节点（机器）的删除

      以上面的分布式集群为例，如果NODE2出现故障被删除了，那么按照顺时针迁移的方法，object3将会被迁移到NODE3中，这样仅仅是object3的映射位置发生了变化，其他的对象没有任何的变动，如下图：



![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash3.JPG)


2、节点（机器）的添加

     如果往集群中添加一个新的节点NODE4,通过对应的Hash算法得到KEY4，并映射到环中，如下图：
     
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash4.JPG)
     
 通过按照顺时针迁移的规则，那么object2被迁移到NODE4中，其他对象还保持这原有的存储位置。通过对节点的添加和删除的分析，一致性哈希算法在保持了单调性的同时，还是数据的迁移达到了最小，这样的算法对分布式集群来说非常合适的，避免了大量收数据迁移，减少了服务器的压力。 
 
 
 平衡性

根据上面的图解分析，一致性哈希算法满足了单调性和负载均衡的特性以及一般hash算法的分散性，但这还并不能当做其被广泛应用的原由，因为缺少了平衡性。下面将分析一致性哈希算法是如何满足平衡性的。hash算法是不保证平衡性的，如上面只部署了NODE1和NODE3的情况(NODE2被删除的图)，object1存储在NODE1中，而object2、object3、object4都存储在NODE3中，这样就造成了非常不平衡的状态。在一致性哈希算法中，为了尽可能的满足平衡性，其引入了虚拟节点。

何为虚拟节点？虚拟节点（Virtual node）是实际节点（机器）在hash空间的复制品（replica），一个实际节点对应了若干个“虚拟节点”，这个对应个数也称为“复制个数”，“虚拟节点”在hash空间中以hash值排列。

在上面只部署了NODE1和NODE3的情况（NODE2被删除的图）为例，之前的对象在机器上的分布很不均衡，现在我们以2个副本（每个节点复制2个）为例，这样整个hash环就存在4个虚拟节点，最后对象映射的关系图如下：

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash5.JPG)


根据上图可知对象的映射关系：object1->NODE1-1,object2->NODE1-2 ,object3->NODE3-2,object4->NODE3-1，通过虚拟节点的引入，对象的分布就比较均衡了。那么在实际操作中，真正的对象查询是如何工作的呢？对象从hash到虚拟节点到实际节点的转换如下图：

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/consisthash6.JPG)

虚拟节点”的hash计算可以采用对应节点的IP地址加数字后缀的方式。例如假设NODE1的IP地址为192.168.1.100。引入“虚拟节点”前，计算 cache A 的 hash 值：

Hash(“192.168.1.100”);

引入“虚拟节点”后，计算“虚拟节”点NODE1-1和NODE1-2的hash值：

Hash(“192.168.1.100#1”); // NODE1-1

Hash(“192.168.1.100#2”); // NODE1-2


## 3.redis cluster 的 hash slot 算法

redis cluster 有固定的 16384 个 hash slot，对每个 key 计算 CRC16 值，然后对 16384 取模，可以获取 key 对应的 hash slot。

redis cluster 中每个 master 都会持有部分 slot，比如有 3 个 master，那么可能每个 master 持有 5000 多个 hash slot。hash slot 让 node
 的增加和移除很简单，增加一个 master，就将其他 master 的 hash slot 移动部分过去，减少一个 master，就将它的 hash slot 移动到其他 master 上去。
 移动 hash slot 的成本是非常低的。客户端的 api，可以对指定的数据，让他们走同一个 hash slot，通过 hash tag 来实现。

任何一台机器宕机，另外两个节点，不影响的。因为 key 找的是 hash slot，不是机器。
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/hashslot.jpg)
