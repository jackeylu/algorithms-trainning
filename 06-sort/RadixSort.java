import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
            }
        }

        for (int exp = 1; max/exp > 0; exp *= 10) {
            countingSort(a, exp);
        }
    }


    private static void countingSort(int[] a, int exp) {
        if (a.length <= 1){
            return;
        }

        // (a[i]/exp)%10,将a中每个数值缩小exp倍后取个位上的数
        // 计算每个个位数的出现个数
        int[] c = new int[10];
        for (int i = 0; i < a.length; i++) {
            c[(a[i]/exp)%10]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[a.length];
        for (int i = a.length - 1; i >=0; i--) {
            r[c[(a[i]/exp) % 10] - 1] = a[i];
            c[(a[i]/exp)%10]--;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = r[i];
        }
    }

    public static void main(String[] args) {
        System.out.println(11/100);
        System.out.printf("%d, %d%n", (13/10)%10, (124/10)%10);
        int[] d = {18023, 232, 12,21,3232,12,1,212,2121,121,12,11,22};
        radixSort(d);
        System.out.println(Arrays.toString(d));
    }
}
