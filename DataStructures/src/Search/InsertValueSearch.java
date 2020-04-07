package Search;

// 插值查找算法也要求数组有序
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(resIndex);
    }

    // 插值查找算法
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        // 注意，下面这个判断条件必须需要，否则会出现mid越界的情况
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 说明应该向右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明应该向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else { // 相等
            return mid;
        }
    }

}
