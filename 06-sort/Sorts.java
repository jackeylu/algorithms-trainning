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

    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort2(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
