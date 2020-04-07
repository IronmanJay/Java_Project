package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

// 归并排序
public class MergetSort {

    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 会生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:" + datestr1);
        int temp[] = new int[arr.length]; // 归并排序需要一个额外的空间
        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:" + datestr2);
    }

    // 分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间索引
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 到合并
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并的方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i，左边有序序列的初始索引
        int j = mid + 1; // 初始化j，右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引
        // （一）
        // 先把左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) { // 继续
            // 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            // 即将左边的当前元素填充到temp数组
            // 然后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { // 反之，将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // （二）
        // 把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) { // 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { // 右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        // （三）
        // 将temp数组的元素拷贝到arr
        // 注意：并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) { // 第一次合并时tempLeft = 0，right = 1，最后一次合并时tempLeft = 0，right = 7
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
