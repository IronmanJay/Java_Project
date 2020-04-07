package Tree.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {

    public static void main(String[] args) {
//        // 要求将数组进行升序排列（大顶堆）
//        int arr[] = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int)(Math.random() * 8000000); // 会生成[0,8000000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:"+datestr1);
        headSort(arr);
        Date date2 = new Date();
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是:"+datestr2);
    }

    // 编写一个堆排序的方法
    public static void headSort(int arr[]) {
        int temp = 0;
//        System.out.println("堆排序");
        // 分步完成
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));
        // 完成最终代码
        // 1、将无序序列构建成一个堆，根据升序降序需求选择大顶堆或者小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 2、将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        // 3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            // 交換
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
//        System.out.println(Arrays.toString(arr));
    }

    // 将一个数组（二叉树），调整成一个大顶堆
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i]; // 先取出当前元素的值保存在临时变量
        // 开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子结点的值小于右子结点的值
                k++; // k指向右子结点
            }
            if (arr[k] > temp) { // 如果子结点大于父节点
                arr[i] = arr[k]; // 把较大的值赋给当前结点
                i = k; // 让i指向k继续循环比较
            } else {
                break;
            }
        }
        // 当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶上(局部)
        arr[i] = temp; // 将temp赋值放到调整后得位置
    }

}
