# 三大限流算法

常见的限流算法：计数器算法，令牌桶算法，漏桶算法

这主要针对业务代码层面的
应用层面的可以使用nginx,与tomcat,都可以配置使用


## 1.计数器算法

使用计数器限流有点简单粗暴，一般会限制一秒能够通过的请求数。如限流qps为100

思路：

从第一个请求进来开始计时，在接下来的1s内，每来一个请求就把计算加1，如果累加的数字达到100，
那么后续请求被全部拒绝，等1s结束后，把计数恢复成0，重新开始计数

实现：

每次调用服务，可通过AtomicLong.incrementAndGet()方法给计数器加1，并返回最新值，
通过这个最新值和阈值进行比较

缺点：

（1）临界问题
假设有一个恶意用户,在0:59时,瞬间发送100个请求,并且1:00又瞬间发送100个请求,那么其实这个用户在1秒内,瞬间发送
200个请求,就可能是系统挂掉。因此用户通过在时间窗口的重置节点处突发请求,可用瞬时超过我们的速率限制.

（2）突刺效应

如果在单位时间1s内的前10ms，已经通过了100个请求，那后面的990ms，后续的请求只能拒绝

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/currentlimit.JPG)


应用：使用计数器来进行限流，主要用来限制总并发数，比如数据库连接池、线程池、秒杀的并发数；
只要全局总请求数或者一定时间段的总请求数设定的阀值则进行限流，是简单粗暴的总数量限流，而不是平均速率限流。

为了解决上述的临界问题引入滑动窗口算法

## 2.滑动窗口算法

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/rollingwindow.JPG)

在上图中,整个红色的矩形框标识一个时间窗口,假如一个时间窗口是1分钟.然后将时间窗口进行划分,比如图中将滑动窗口
划分成6格,每格代表10秒钟.每过10秒时间窗口往右滑动一格.每一个格子有自己独立的计数器.如当一个请求在0:35时到达,
那么0:30~0:39对应的counter加1.

滑动窗口怎么解决临界问题呢?0:59到达的100个请求会落在灰色格子中,而1:00到达的请求会落在橘黄色格子中.
当时间到达1:00时,窗口往右移动一格,那么此时时间窗口内的总请求数量一共是200个，超过了限定的100个，
所以此时能够检测出来触 发了限流


计数器算法其实就是滑动窗口算法。只是它没有对时间窗口做进一步地划分，所以只有1格。

当滑动窗口的格子划分的越多，那么滑动窗口的滚动就越平滑，限流的统计就会越精确


滑动窗口计数有很多使用场景，比如说限流防止系统雪崩。相比计数实现，滑动窗口实现会更加平滑，能自动消除毛刺。

滑动窗口原理是在每次有访问进来时，先判断前 N 个单位时间内的总访问量是否超过了设置的阈值，并对当前时间片上的请求数 +1。


## 3.漏桶算法

为了消除突刺现象，可以采用漏桶算法实现限流。

思想：请求可以粗略认为是注水漏水的过程，以任意速率注水，但流出时的速率是固定的，如果请求来不及处理则放入桶中，当请求超过桶流量则丢弃，因为桶容量是不变的，
保证了整体速率。

通过它突发流量可被整形为稳定流量

缺点：不能应对实际场景，对短时突发流量缺乏效率，无法应对短时间的突发流量。

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/leakybucket.JPG)


## 4.令牌桶算法

思想

令牌桶会以一个恒定速率向固定容量大小桶中放入令牌，当有流量来时则取走一个或多个令牌。如果桶中没有令牌则将当前请求丢弃或阻塞。
相比之下令牌桶可以应对一定的突发流量。

1)所有的请求在处理之前都需要拿到一个可用的令牌才会被处理
2)根据限流大小,设置按照一定的速率往桶里添加令牌
3)桶设置最大的放置令牌限制,当桶满时,新添加的令牌会被丢弃或拒绝
4)请求达到后首先要获取令牌桶中的令牌,拿着令牌才进行其他业务逻辑,处理完业务逻辑后,将令牌直接删除
5)令牌桶有最低限额,当桶中令牌达到最低限额时,请求处理完之后将不会删除令牌，以此保证足够的限流

![image](https://github.com/williamzhang11/fastAlgorithm/blob/master/src/main/java/com/xiu/fastAlgorithm/image/tokenbucket.JPG)

令牌桶算法与漏桶算法的主要区别在于：

漏桶算法前行限制数据的传输速率

令牌桶算法除了限制数据的平均速率外，还允许某种程度的突发传输。只要桶中存在令牌，就允许突发的请求，直到达到用户配置的上限。



实现：Google开源的guava包RateLimiter类

## 5.集群限流

前面的集中算法属于单机限流范畴，有时业务需求根本无法满足

如：为了限制某个资源被每个用户的访问次数，5s只能访问2次，或一天只能调用1000次，这种需求单机限流无法实现

为了控制访问次数，需要一个计数器，这个计数器保存在第三方服务redis

思路：每次有相关操作，向redis服务器发送一个incr命令，如需要限制某个用户 某个接口的次数，只需拼接用户id和接口生成reids的key
每次该用户访问次接口时，只需要对这个key执行incr命令，在这个key带上过期时间，可以实现指定时间的访问频率。

































