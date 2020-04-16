
## 快速排序（Quick Sort）的要点：

快排的思想是这样的：如果要排序数组中下标从 p 到 r 之间的一组数据，
我们选择 p 到 r 之间的任意一个数据作为 pivot（分区点）。
我们遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，
将 pivot 放到中间。

经过这一步骤之后，数组 p 到 r 之间的数据就被分成了三个部分，
前面 p 到 q-1 之间都是小于 pivot 的，中间是 pivot，
后面的 q+1 到 r 之间是大于 pivot 的。


其逻辑伪代码如下：
```shell script
// 快速排序，A是数组，n表示数组的大小
quick_sort(A, n) {
  quick_sort_c(A, 0, n-1)
}
// 快速排序递归函数，p,r为下标
quick_sort_c(A, p, r) {
  if p >= r then return
  
  q = partition(A, p, r) // 获取分区点
  quick_sort_c(A, p, q-1)
  quick_sort_c(A, q+1, r)
}
```


## partition的要点

分区的思想前面提到了，就是要将数组根据选出的pivot，将数据分成三部分，每部分都是排好序的。

找好了pivot点，将数组分区时，暴力的方法是申请两个与原数组一样大小的数组，对比原数组的值，
并将数值填入到两个新申请的数组中。过程如下图
![simple-one](./images/simple-partition.jpg)


但是这种方式在n很大时对内存消耗很大，于是有一种原地排序的方式，不需要新增空间，如下图示例
![O(1)-one](./images/partition-example.jpg)

这个图中原地分区的思想伪代码是
```shell script

partition(A, p, r) {
  pivot := A[r]
  i := p
  for j := p to r-1 do {
    if A[j] < pivot {
      swap A[i] with A[j]
      i := i+1
    }
  }
  swap A[i] with A[r]
  return i

```

这里的处理有点类似选择排序。我们通过游标 i 把 A[p…r-1]分成两部分。
A[p…i-1]的元素都是小于 pivot 的，我们暂且叫它“已处理区间”，A[i…r-1]是“未处理区间”。
我们每次都从未处理的区间 A[i…r-1]中取一个元素 A[j]，与 pivot 对比，
如果小于 pivot，则将其加入到已处理区间的尾部，也就是 A[i]的位置。
