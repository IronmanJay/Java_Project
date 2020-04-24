package Tree;

import java.util.ArrayList;
import java.util.List;

public class p257_BinaryTreePaths {

    p257_BinaryTreePaths left;
    p257_BinaryTreePaths right;
    int val;

    p257_BinaryTreePaths() {

    }

    p257_BinaryTreePaths(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        p257_BinaryTreePaths root = new p257_BinaryTreePaths(1);
        p257_BinaryTreePaths left1 = new p257_BinaryTreePaths(2);
        p257_BinaryTreePaths left2 = new p257_BinaryTreePaths(5);
        p257_BinaryTreePaths right = new p257_BinaryTreePaths(3);
        root.left = left1;
        left1.right = left2;
        root.right = right;
        p257_BinaryTreePaths p257_binaryTreePaths = new p257_BinaryTreePaths();
        List<String> res = p257_binaryTreePaths.binaryTreePaths(root);
        System.out.println("res = " + res);
    }

    public List<String> binaryTreePaths(p257_BinaryTreePaths root) {
        List<String> list = new ArrayList<String>();
        // 如果root节点为null，直接返回list
        if (root == null) {
            return list;
        }
        // 如果是叶子节点，说明一条路径已经找到就把当前结点的值加入到list中，并返回
        if (root.left == null && root.right == null) {
            list.add(Integer.toString(root.val));
            return list;
        }
        // 首先新建左子树的路径集合，这里因为是递归，每一次返回一条路径
        List<String> leftPath = new ArrayList<String>();
        // 然后使用这个list接收返回的一条路径，使用增强for循环遍历
        leftPath = binaryTreePaths(root.left);
        for (String left : leftPath) {
            // 使用StringBufferi字符串缓冲提供对每一个节点值的存储
            // 首先我们需要把头结点root加入进来
            // 因为root.val是数字，最后要求字符串存储，所以需要转换一下
            StringBuffer sb = new StringBuffer(Integer.toString(root.val));
            // 因为题目要求以->分割，所以加入->
            sb.append("->");
            // 加入当前遍历到的节点值
            sb.append(left);
            // 加入最终结果集
            list.add(sb.toString());
        }
        // 右子树同理，不再赘述
        List<String> rightPath = new ArrayList<String>();
        rightPath = binaryTreePaths(root.right);
        for (String right : rightPath) {
            StringBuffer sb = new StringBuffer(Integer.toString(root.val));
            sb.append("->");
            sb.append(right);
            list.add(sb.toString());
        }
        return list;
    }

}
