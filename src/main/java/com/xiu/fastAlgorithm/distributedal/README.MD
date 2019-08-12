# 三大数据分布算法/分布式寻址算法

hash 算法（大量缓存重建）
一致性 hash 算法（自动缓存迁移）+ 虚拟节点（自动负载均衡）
redis cluster 的 hash slot 算法

## 1.hash 算法

来了一个 key，首先计算 hash 值，然后对节点数取模。然后打在不同的 master 节点上。
一旦某一个 master 节点宕机，所有请求过来，都会基于最新的剩余 master 节点数去取模，
尝试去取数据。这会导致大部分的请求过来，全部无法拿到有效的缓存，导致大量的流量涌入数据库。

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/hash.jpg)

## 2.一致性 hash 算法

## 3.redis cluster 的 hash slot 算法

redis cluster 有固定的 16384 个 hash slot，对每个 key 计算 CRC16 值，然后对 16384 取模，可以获取 key 对应的 hash slot。

redis cluster 中每个 master 都会持有部分 slot，比如有 3 个 master，那么可能每个 master 持有 5000 多个 hash slot。hash slot 让 node 的增加和移除很简单，增加一个 master，就将其他 master 的 hash slot 移动部分过去，减少一个 master，就将它的 hash slot 移动到其他 master 上去。移动 hash slot 的成本是非常低的。客户端的 api，可以对指定的数据，让他们走同一个 hash slot，通过 hash tag 来实现。

任何一台机器宕机，另外两个节点，不影响的。因为 key 找的是 hash slot，不是机器。
![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/hashslot.jpg)
