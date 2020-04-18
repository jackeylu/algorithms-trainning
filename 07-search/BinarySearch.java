public class BinarySearch {


    /**
     * 最简单的二分查找实现
     * @param a  the array with sorted values in ascent order
     * @param n  the length of this array
     * @param value target to be found
     * @return position of target value, or -1 for not found.
     */
    public static int bSearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1); // 防止两大数相加溢出，位运算是为了加快除法
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }


    /**
     * 能够正确返回第一个值等于目标值的二分法
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bSearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 以下讨论数组中存在目标值的情况：
            // 即使mid位置的值就是第一个等于目标值的位置，也将high位置左移
            // 那么接下来肯定是找不到等于目标值的结果，并且比较的值都是比value小，
            // 那么循环内部就会一直在else分支内，那么low坐标就会不停右移，
            // 这个过程中high值一直不变，就是目标位置的前一个。
            // 最后一次循环内部low通过mid + 1就变成大于high值了，此时 low - high = 1
            // low的位置恰好就是第一个值等于目标值的位置
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // 如果目标值 > a[-1]，那么low值就会等于n
        // 如果 a[0] < 目标值 < a[-1]，但不存在该数组中，则a[low]不等于目标值
        System.out.println("Last position of low index: " + low);
        if (low < n && a[low]==value) {
            return low;
        } else {
            return -1;
        }
    }


    /**
     * bSearch2的结构优化，使得更加易读，比较次数也会更少。
     * @param a array
     * @param n size
     * @param value target
     * @return pos
     */
    public static int bSearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            // 以上都是一样

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                // a[mid] == value 的情况进行分类处理
                // 当前位置恰好是第一个符合的值，则直接返回
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                }else {
                    // 当前位置不是第一个出现的位置，那就让二分法继续在左边中查找吧！
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个与目标值匹配的位置
     * @param a array
     * @param n size
     * @param value target
     * @return pos
     */
    public static int bSearch4(int[] a, int n, int value){
        int low = 0;
        int high = n -1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == (n-1) || a[mid + 1] != value){
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于目标值的元素位置
     * @param a array
     * @param n size
     * @param value target
     * @return pos
     */
    public static int bSearch5(int[] a, int n, int value){
        int low = 0;
        int high = n -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] >= value){
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于目标值的元素位置
     * @param a array
     * @param n size
     * @param value target
     * @return pos
     */
    public static int bSearch6(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);

            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n -1 || a[mid + 1] > value){
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,3,4,5,6,8,8,8,11,18};
        int target = 8;
        System.out.println("[INFO]: The first value of 8 is at position 5");
        System.err.println("[WA]: found by simple binary search : " + bSearch1(a, a.length, target));
        System.out.println("[CA]: found by smarter binary search : " + bSearch2(a, a.length, target));
        System.out.println("[CA]: found by clever binary search : " + bSearch3(a, a.length, target));

        target = 19;
        System.out.println("array size: " + a.length);
        System.out.println("Finding large target by smarter binary search: " + bSearch2(a, a.length, target));
        target = 2;
        System.out.println("Finding hole target by smarter binary search: " + bSearch2(a, a.length, target));

        System.out.println("Finding last one matched on position 7");
        target = 8;
        System.out.println("[CA]" + bSearch4(a, a.length, target));

        target = 9;
        System.out.println("Finding the first great than position of value 9, expected position is 8");
        System.out.println("[CA]:" + bSearch5(a, a.length, target));

        target = 8;
        System.out.println("Finding the first great than position of value 8, expected position is 5");
        System.out.println("[CA]:" + bSearch5(a, a.length, target));

        target = 7;
        System.out.println("Finding the last less than 7, expected position is 4.");
        System.out.println("[CA]:" + bSearch6(a, a.length, target));
    }
}
