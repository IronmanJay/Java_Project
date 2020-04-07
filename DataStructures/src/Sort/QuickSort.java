package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); // 会生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:"+datestr1);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:"+datestr2);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        int pivot = arr[(left + right) / 2]; // 中轴值
        int temp = 0; // 临时变量，作为交换时使用
        while (l < r) { // while循环的目的是让比pivot小的值放到pivot的左面，比pivot大的放到右边
            while (arr[l] < pivot) { // 在pivot的左边一直找，找到大于等于pivot的值才退出
                l += 1;
            }
            while (arr[r] > pivot) { // 在pivot的右边一直找，找到小于等于pivot的值才退出
                r -= 1;
            }
            // 如果l >= r成立，说明pivot的左右两边值，已经按照左边小于等于pivot，右边大于等于pivot的顺序排列
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后发现arr[l]==pivot，就让r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后发现arr[r]==pivot，就让l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果l == r，必须让l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
