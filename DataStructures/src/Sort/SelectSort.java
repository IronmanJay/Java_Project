package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); // 会生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:"+datestr1);
        selectSort(arr);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:"+datestr2);
    }

    // 选择排序
    // 算法时间复杂度是O（n^2）
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值不是最小的
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }
            // 将最小值，放在arr[0]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

}
