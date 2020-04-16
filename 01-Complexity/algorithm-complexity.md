我们需要一个不用具体的测试数据来测试，就可以粗略地估计算法的执行效率的方法。
这就是我们今天要讲的时间、空间复杂度分析方法。

### 大 O 复杂度表示法

```c
 int cal(int n) {
   int sum = 0;
   int i = 1;
   int j = 1;
   for (; i <= n; ++i) {
     j = 1;
     for (; j <= n; ++j) {
       sum = sum +  i * j;
     }
   }
 }
```
大 O 时间复杂度实际上并不具体表示代码真正的执行时间，而是表示代码执行时间随数据规模增长的变化趋势，
所以，也叫作渐进时间复杂度（asymptotic time complexity），简称时间复杂度。

我们在分析一个算法、一段代码的时间复杂度的时候，也只关注循环执行次数最多的那一段代码就可以了。

### 最好、最坏、平均和均摊时间复杂度

1. 最好情况时间复杂度（best case time complexity）:最好情况时间复杂度就是，在最理想的情况下，执行这段代码的时间复杂度。
2. 最坏情况时间复杂度（worst case time complexity）: 在最糟糕的情况下，执行这段代码的时间复杂度.
3. 平均情况时间复杂度（average case time complexity）:加权平均时间复杂度或者期望时间复杂度。
4. 均摊时间复杂度（amortized time complexity）:

```C

// n表示数组array的长度
int find(int[] array, int n, int x) {
  int i = 0;
  int pos = -1;
  for (; i < n; ++i) {
    if (array[i] == x) {
       pos = i;
       break;
    }
  }
  return pos;
}
```

1. best case time complexity: O(1)
2. worst case time complexity: O(n)
3. average case time complexity: 
> 假设在数组中和不在数组中的概率分别是1/2，
>另P(n=i|n in array) = 0.5 * (1/n) = 1/(2n)。
>![avg-case](./images/avg-case.jpg)
>所以平均情况时间复杂度是O((3n+1)/4)，也就是O(n)


对一个数据结构进行一组连续操作中，大部分情况下时间复杂度都很低，只有个别情况下时间复杂度比较高，而且这些操作之间存在前后连贯的时序关系，这个时候，我们就可以将这一组操作放在一块儿分析，看是否能将较高时间复杂度那次操作的耗时，平摊到其他那些时间复杂度比较低的操作上。而且，在能够应用均摊时间复杂度分析的场合，一般均摊时间复杂度就等于最好情况时间复杂度。


### 习题

```java

// 全局变量，大小为10的数组array，长度len，下标i。
int array[] = new int[10]; 
int len = 10;
int i = 0;

// 往数组中添加一个元素
void add(int element) {
   if (i >= len) { // 数组空间不够了
     // 重新申请一个2倍大小的数组空间
     int new_array[] = new int[len*2];
     // 把原来array数组中的数据依次copy到new_array
     for (int j = 0; j < len; ++j) {
       new_array[j] = array[j];
     }
     // new_array复制给array，array现在大小就是2倍len了
     array = new_array;
     len = 2 * len;
   }
   // 将element放到下标为i的位置，下标i加一
   array[i] = element;
   ++i;
}
```
最好是O(1)，最差是O(n), 均摊是O(1)。

看到好多人纠结于清空数组的问题: 对于可反复读写的存储空间，使用者认为它是空的它就是空的。
如果你定义清空是全部重写为0或者某个值，那也可以！但是老师举的例子完全没必要啊！
写某个值和写任意值在这里有区别吗，使用值只关心要存的新值！所以老师的例子，
清空把下标指到第一个位置就可以了！