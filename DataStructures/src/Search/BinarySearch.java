package Search;

import java.util.ArrayList;
import java.util.List;

// 二分法查找（要求数组必须有序）
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        List<Integer> resIndex = binarySearch2(arr,0,arr.length-1,1000);
        System.out.println(resIndex);
    }

    // 二分查找算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) { // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    // 升级版
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) { // 向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexlist = new ArrayList<Integer>();
            // 向左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) { // 退出
                    break;
                }
                // 否则，就把temp放入到resIndexlist
                resIndexlist.add(temp);
                temp -= 1; // temp左移
            }
            resIndexlist.add(mid); // 中间
            // 向右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) { // 退出
                    break;
                }
                // 否则，就把temp放入到resIndexlist
                resIndexlist.add(temp);
                temp += 1; // temp右移
            }
            return resIndexlist;
        }
    }

}
