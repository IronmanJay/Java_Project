package Array;

public class p11_ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = maxArea(height);
        System.out.println("res = " + res);
    }

    public static int maxArea(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int res = 0;
        while (leftIndex < rightIndex) {
            res = height[leftIndex] < height[rightIndex] ? Math.max(res, (rightIndex - leftIndex) * height[leftIndex++]) : Math.max(res, (rightIndex - leftIndex) * height[rightIndex--]);
        }
        return res;
    }

}
