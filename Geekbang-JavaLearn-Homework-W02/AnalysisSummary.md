总结：

用不同的GC运行GCLogAnalysis，通过查看GC Log，运行环境是JDK 17

1. 要在运行程序的参数中设置Xmx 和Xms 才能模拟GC的情况。通常物理机的内存都比较大，例如：32G。默认情况下，最大堆内存是物理内存的 1/4, 也就是8G，而初始堆内存是512M。我用Serial GC，直到第八次的Young GC 才发生full GC自动扩大初始堆内存（从512M - 665M）
2. 如果使用SerialGC, 在Xmx=512M的情况下，发生了6次的young GC就开始频繁发生full GC。基本上young GC 持续的时间大概是30ms，跟一次的full GC 差不多
3. 发现 -XX:+UseParallelGC  必须放在 class 文件名之前，否则会出错说， Could not find or load main class ‐XX:+UseParallelGC
4. 当使用ParallelGC时，前9次都是Young 区的GC，基本控制在18ms以内，然后时一次的full GC, 用了32ms。效率比SerialGC明显提高
5. 发现JDK17，已经没有了 -XX:+UseConcMarkSweepGC 参数配置。而在JDK8 和 11是有的
6. 当使用JDK11，运行CMS GC时，发现young GC 基本上都要30-40ms,full GC 则需要 52ms，越到后面的GC，时间越长，最长的100ms以上
7. 当运行G1 GC时，GC的次数明显增加，一共发生了124次的GC，每次Young GC都控制在10ms以下，除了一个Full GC 用了28ms。Young GC 更频繁，发生了95次的Young GC 才发生第一次的Full GC。保证的业务的连续性。