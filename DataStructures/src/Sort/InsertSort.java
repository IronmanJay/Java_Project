package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 会生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:" + datestr1);
        insertSort(arr);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:" + datestr2);
    }

    // 插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1; // 即arr[1]的前面这个数的下标
            // 给insertVal找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // 保证不越界，并且保证待插入的数还没有找到适当的插入位置，就需要将arr[insertIndex]后移
                arr[insertIndex + 1] = arr[insertIndex]; // 后移
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

}
