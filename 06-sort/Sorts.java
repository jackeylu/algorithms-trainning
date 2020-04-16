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
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort2(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
