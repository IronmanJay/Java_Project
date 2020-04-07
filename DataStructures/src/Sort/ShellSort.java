package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 会生成[0,8000000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:" + datestr1);
        shellSort2(arr);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:" + datestr2);
    }

    // 交换法（效果不好）
    public static void shellSort(int[] arr) {
        int temp = 0;
        // 增量gap，并逐步地缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素，共有gap组，每组有(根据实际情况)个元素，步长是gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 移位法（效果好）
    public static void shellSort2(int[] arr) {
        // 增量gap，并逐步地缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while循环后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }

        }
    }

}
