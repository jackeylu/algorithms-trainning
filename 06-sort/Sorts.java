import java.util.Arrays;

public class Sorts {

    public static void bubbleSort(int[] a, int n){
        if (n <= 1){
            return;
        }
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean switched = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    // 此次冒泡有数据交互
                    switched = true;
                }
            }

            if (!switched) {
                // 没有数据交换，提前退出
                break;
            }
        }
    }

    public static void bubbleSort2(int[] a, int n){
        if (n <= 1){
            return;
        }

        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界，每次只需要比较到这里就可以了
        int sortBorder = n -1;
        for (int i = 0; i < n; i++) {
            // 提前退出标志
            boolean switched = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    switched = true;
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!switched){
                break;
            }
        }
    }

    public static void insertionSort(int[] a, int n){
        if (n <= 1){
            return;
        }

        for (int i = 0; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            // 查找要插入的位置，并移动数据
            for (; j >= 0; --j){
                if (a[j] > value){
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }

    public static void selectionSort(int[] a, int n){
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // exchange
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

    public static void mergeSort(int[] a, int left, int right){
        if (left >= right){
            return;
        }
        int q = (left + right)/2;
        mergeSort(a, left, q);
        mergeSort(a, q+1, right);
        merge(a, left, q, right);
    }

    private static void merge(int[] a, int left, int q, int right) {
        int i = left;
        int j = q + 1;
        int k = 0;
        // 因为现在要对数组a的[left, right]区间的数据进行合并，所以要申请一个相同大小的数组缓存一下。
        int[] tmp = new int[right - left + 1];
        // 将[left, q] [q+1, right]两个有序数组的数据按有序的方式拷贝到tmp数组
        while (i <= q && j <= right){
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        // 原来的两个有序数组可能有一个还没拷贝完，需要接着拷贝
        // 我们需要找到是哪一边的数组，以及开始和结束位置
        // 一开始假设是左边那个数组
        int start = i;
        int end = q;
        if (j <= right) {
            // 此时发现是右边的数组没拷贝完
            start = j;
            end = right;
        }
        // 将没拷贝完的数组剩余数据拷贝到tmp数组中
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp数组这个完全有序的数组数据，拷贝回原数组。
        for (int l = 0; l <= right - left; l++) {
            a[l + left] = tmp[l];
        }
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        // 将数组a在[left, right]区间的数据分成a[left:q-1] < a[q] <= a[q+1:right]三部分
        int q = partition(a, left, right);
        quickSort(a, left, q - 1);
        // a[q] 不需要参与排序，因为左边比它小，右边比它大
        quickSort(a, q+ 1, right);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        /**
         * 我们通过游标 i 把 A[p…r-1]分成两部分。
         * A[p…i-1]的元素都是小于 pivot 的，我们暂且叫它“已处理区间”，
         * A[i…r-1]是“未处理区间”。
         *
         * 我们每次都从未处理的区间 A[i…r-1]中取一个元素 A[j]，与 pivot 对比，
         * 如果小于 pivot，则将其加入到已处理区间的尾部，也就是 A[i]的位置。
         */
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                if (i == j){
                    // 成立时，没有换的必要，因为是自己和自己换
                    i++;
                } else {
                    // swap a[j] with a[i]
                    int tmp = a[i];
                    a[i++] = a[j]; // a[i]赋值后，i的值要加1
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }

    /**
     * O(n)  时间复杂度内求无序数组中的第 K小元素。
     * 比如， 4 ， 2 ， 5 ， 12 ， 3  这样一组数据，第 3  大元素就是 4 。
     * @param a
     * @param k
     * @return
     */
    public static int findKSmallest(int[] a, int k) {
        if (a == null || a.length < k){
            return -1;
        }

        int p = partition(a, 0, a.length -1);
        while (p + 1 != k){
            if (p + 1 < k){
                p = partition(a, p + 1, a.length -1);
            } else {
                p = partition(a, 0, p - 1);
            }
        }
        return a[p];
    }

    public static int findKLargest(int[] a, int k){
        if (a == null || a.length < k){
            return -1;
        }

        // p是原始数组a中的pivot的位置，是从0开始索引
        int p = partition(a, 0, a.length -1 );
        // 当pivot节点右边的数组大小不等于k时，循环继续
        while (p+k != a.length){
            if (p + k < a.length){
                // 右边数组的大小大于k，则还需要在右边数组中分裂成两个数组
                p = partition(a, p+1, a.length -1);
            } else {
                // 右边数组的大小小于k，则在左边的数组中分裂成两个数组
                p = partition(a, 0, p -1);
            }
        }
        return a[p];
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort2(a, a.length);
        System.out.println(Arrays.toString(a));

        int[] b = {3, 4, 2, 1, 5, 6, 7, 8};
        int value = findKSmallest(b, 5);
        System.out.println(value + ", expected 5");
        value = findKLargest(b, 3);
        System.out.println(value + ", expected 6");

        int[] c = {4, 2, 5, 12, 3};
        int p = partition(c,0, c.length -1);
        System.out.println(p);
        value = findKSmallest(c, 2);
        System.out.println(Arrays.toString(c));
        System.out.println(value + ", expected 3");
        value = findKLargest(c, 3);
        System.out.println(Arrays.toString(c));
        System.out.println(value + " expected 4");
    }
}
