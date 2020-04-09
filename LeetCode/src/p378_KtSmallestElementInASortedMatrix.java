public class p378_KtSmallestElementInASortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        p378_KtSmallestElementInASortedMatrix p378_ktSmallestElementInASortedMatrix = new p378_KtSmallestElementInASortedMatrix();
        int res = p378_ktSmallestElementInASortedMatrix.kthSmallest(matrix, k);
        System.out.println("res = " + res);
    }

    public int kthSmallest(int[][] matrix, int k) {
        // 创建对应的一维数组
        int[] mergeRes = merge(matrix);
        // 排序
        int[] sortRes = quickSort(mergeRes, 0, mergeRes.length - 1);
        return sortRes[k - 1];
    }

    public int[] merge(int[][] matrix) {
        int[] res;
        int len = 0;
        // 计算长度
        for (int[] element : matrix) {
            len += element.length;
        }
        // 创建相应的一维数组
        res = new int[len];
        int index = 0;
        for (int[] element1 : matrix) {
            for (int element2 : element1) {
                res[index++] = element2;
            }
        }
        return res;
    }

    public int[] quickSort(int[] arr, int left, int right) {
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
        return arr;
    }

}
