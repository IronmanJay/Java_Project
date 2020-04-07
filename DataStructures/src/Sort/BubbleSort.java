package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {
        // 测试冒泡排序
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); // 会生成[0,8000000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:"+datestr1);

//        int[] arr = {20, 9, -1, 10, 3};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:"+datestr2);

    }

    // 将前面的冒泡排序算法，封装成一个方法
    public static void bubbleSort(int[] arr) {
        // 每趟排序，就是将最大的排序在最后
        int temp = 0; //临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) { // 外循环，一共循环数组长度-1次
            for (int j = 0; j < arr.length - 1 - i; j++) { // 内循环，每次只需要排序沉底元素之前的元素
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag，进行下次判断
            }
        }
    }

}
