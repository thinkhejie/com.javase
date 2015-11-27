package j.se.concurrency.collections;

public class Doc {

}
/*
　J2SE 6(代号：Mustang野马)主要设计原则之一就是提升J2SE的性能和扩展能力，主要通过最大程度提升运行效率，更好的垃圾收集和一些客户端性能来达到。
 
　　1、偏向锁(Biased locking)
 
　　Java 6以前加锁操作都会导致一次原子CAS(Compare-And-Set)操作，CAS操作是比较耗时的，即使这个锁上实际上没有冲突，只被一个线程拥有，也会带来较大开销。
          
          为解决这一问题，Java 6中引入偏向锁技术，即一个锁偏向于第一个加锁的线程，该线程后续加锁操作不需要同步。
          
          大概的实现如下：一个锁最初为NEUTRAL状态，当第一个线程加锁时，将该锁的状态修改为BIASED，并记录线程ID，当这一线程进行后续加锁操作时，若发现状态是BIASED并且线程ID是当前线程ID，则只设置一下加锁标志，不需要进行CAS操作。
          
          其它线程若要加这个锁，需要使用CAS操作将状态替换为REVOKE，并等待加锁标志清零，以后该锁的状态就变成 DEFAULT，常用旧的算法处理。这一功能可用-XX:-UseBiasedLocking命令禁止。
 
　　2、锁粗化(Lock coarsening)
 
　　如果一段代码经常性的加锁和解锁，在解锁与下次加锁之间又没干什么事情，则可以将多次加加锁解锁操作合并成一对。

          这一功能可用-XX：-EliminateLocks禁止。
 
　　3、自适应自旋(Adaptive spinning)
 
　　一般在多CPU的机器上加锁实现都会包含一个短期的自旋过程。自旋的次数不太好决定，自旋少了会导致线程被挂起和上下文切换增加，自旋多了耗CPU.为此Java 6中引入自适应自旋技术，即根据一个锁最近自旋加锁成功概率动态调整自旋次数。
 
　　4、常用大内存分布的堆(large page heap)
 
　　在大内分页是x86/amd64架构上用来减小TLB(虚拟地址到物理地址翻译缓存)大小的TLB失配率。Java 6中的内存堆可以使用这一技术。
 
　　5、提高数组拷贝性能
 
　　对每种类型大小写一个定制的汇编数组拷贝程序。
 
　　6、后台进行代码优化
 
　　Background Compilation in HotSpot™ Client Compiler： 后台进行代码优化
 
　　7、线性扫描寄存器分配算法(Linear Scan Register Allocation)
 
　　一种新的寄存器分配策略，基于SSA(static single assignment)，性能提高10%左右。常用的寄存器分配算法将寄存器分配看作图着色问题，时间复杂度是O(n^4)，不适用于Java的JIT编译。原来的JVM里是根据一些本地启发式规则来分配寄存器，效果不太好，Java 6中使用的线性扫描寄存器算法能够达到与图颜色算法相似的效果，并且时间复杂度是线性的。
 
　　8、并行缩并垃圾收集器(Parallel Compaction Collector)
 
　　进行Full GC时使用并行垃圾收集(JDK 5里原来非Full GC是并行的但Full GC是串行的)，使用-XX：+UseParallelOldGC开启这一功能
 
　　9、并行低停顿垃圾收集器(Concurrent Low Pause Collector)
 
　　显式调用gc(如System.gc)时也可以并行进行标记-清扫式垃圾收集，使用-XX：+ExplicitGCInvokesConcurrent开启。
 
　　10、Ergonomics in the 6.0 Java Virtual Machine
 
　　自动调整垃圾收集策略、堆大小等配置，这一功能在JDK 5中加入，JDK 6中得到显著增强，SPECjbb2005性能提高70%.
 
　　11、boot类装载器的优化
 
　　jre中增加一个描述package所在jar文件的元索引文件，加快classloader加载类性能，提高桌面Java应用启动速度(+15%)。内存占用也减少了10%
 
　　12、图形程序优化
 
　　在jvm启动之前显示splash.
 
　　Swing程序中每个窗口有一个后台显示缓存，当该窗口原来被遮挡，现在要显示时直接从该缓存拷贝数据进行渲染，即使该窗口的绘制线程被阻塞也可以完成这一渲染。

*/