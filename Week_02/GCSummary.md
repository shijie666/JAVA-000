# GC总结
## 命令解读
指定堆内存大小（单位可以是m或g）  
```java -Xmx128m -XX:+PrintGCDetails GCLogAnalysis```   
指定垃圾回收器  
```text
串行GC SerialGC  
java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```  
通过串行GC进行进行垃圾回收，通过命令分析串行GC主要分为几个步骤，  
执行过程中发生GC，会发生长时间的STW，是单线程操作，回收线程和用户线程不会同时执行
适用于允许长时间停顿的应用：如桌面应用等
<img src="C:\wake\JAVA-000\Week_02\static\SerialGC.png">          

```
ParGC ParallelGC  
java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis  
```
通过 ParallelGC，进行垃圾回收，运用的算法是复制算法，GC时，是多线程进行操作，但是回收线程和用户线程仍然不会同时执行，parGC也是目前JDK8中年轻代在用的垃圾回收器，现在JDK15中已经被彻底删除了。
主要用于年轻代的回收，主要缺点就是可能回影响用户线程执行，
<img src="C:\wake\JAVA-000\Week_02\static\ParNewGC.png">   

```
ConcMarkSweepGC        
java -XX:+UseConcMarkSweepGC-Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis    
```
CMS主要用于老年代垃圾回收，运用的算法是（标记-整理算法）  
<img src="C:\wake\JAVA-000\Week_02\static\CMSGC.png">  

```
G1GC  
java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis   
```  
G1的好处在于将堆分为大小相等的若干个Region，老年代和新生代也由物理概念变成了逻辑上的概念，这样的好处在于，GC产生的暂停时可预见性的，时间也更短。
<img src="C:\wake\JAVA-000\Week_02\static\G1GC.png">  
  
  