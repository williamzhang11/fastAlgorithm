# 分布式主键生成算法

1.数据库自增

MySQL、Oracle、PGSQL等关系数据库的id主键自增

Redis、Memcached等K/V数据库的incr操作自增，需要考虑持久化的问题

MongoDB自己维护一个id自增集合
MongoDB的Update操作支持incr操作，因此可以这么做。
该方式适用于支持该操作的其他数据库。

2.类UUID算法

UUID
MongoDB的ObjectId

3. SnowFlake



