package DFS;

import java.util.ArrayList;
import java.util.List;

public class p515_FindLargestValueInEachTreeRow {

    int val;
    p515_FindLargestValueInEachTreeRow left;
    p515_FindLargestValueInEachTreeRow right;

    p515_FindLargestValueInEachTreeRow(int x) {
        val = x;
    }

    public static void main(String[] args) {
        p515_FindLargestValueInEachTreeRow root = new p515_FindLargestValueInEachTreeRow(1);
        p515_FindLargestValueInEachTreeRow left = new p515_FindLargestValueInEachTreeRow(3);
        p515_FindLargestValueInEachTreeRow right = new p515_FindLargestValueInEachTreeRow(2);
        p515_FindLargestValueInEachTreeRow left1 = new p515_FindLargestValueInEachTreeRow(5);
        p515_FindLargestValueInEachTreeRow left2 = new p515_FindLargestValueInEachTreeRow(3);
        p515_FindLargestValueInEachTreeRow right1 = new p515_FindLargestValueInEachTreeRow(9);
        root.left = left;
        root.right = right;
        left.left = left1;
        left.right = left2;
        right.right = right1;
        List<Integer> res = largestValues(root);
        System.out.println("res = " + res);
    }

    public static List<Integer> largestValues(p515_FindLargestValueInEachTreeRow root) {
        // 新建结果数组
        List<Integer> res = new ArrayList<>();
        // 如果为空直接返回
        if (root == null) {
            return res;
        }
        // 这里level代表层数，从0层开始和数组开始位置一样
        getMaxValue(root, res, 0);
        return res;
    }

    public static void getMaxValue(p515_FindLargestValueInEachTreeRow root, List<Integer> res, int level) {
        // 如果当前节点为空直接返回
        if (root == null) {
            return;
        }
        // 如果来到了下一个节点，直接加入，这里判断条件是层数和数组元素个数，如果两者相等，说明层数已经+1了，那么把当前节点加入即可
        if (res.size() == level) {
            res.add(root.val);
        }
        // 如果进入这里，说明来到了兄弟节点，那么就要比较两个兄弟节点谁大，把大的放到结果数组，level就是结果数组的位置索引，代表每一层的最大值
        else {
            res.set(level, Math.max(res.get(level), root.val));
        }
        // 分別向左右递归，每次递归层数+1，说明递归的是下一层
        getMaxValue(root.left, res, level + 1);
        getMaxValue(root.right, res, level + 1);
    }

}
