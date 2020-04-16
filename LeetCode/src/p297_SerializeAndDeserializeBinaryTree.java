import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p297_SerializeAndDeserializeBinaryTree {

    /**
     * 初始化结点
     */
    int val; // 当前节点的值
    p297_SerializeAndDeserializeBinaryTree left; // 左节点
    p297_SerializeAndDeserializeBinaryTree right; // 右节点
    // 构造方法给节点赋值
    p297_SerializeAndDeserializeBinaryTree(int x) {
        val = x;
    }

    public static void main(String[] args) {
        p297_SerializeAndDeserializeBinaryTree root = new p297_SerializeAndDeserializeBinaryTree(1);
        p297_SerializeAndDeserializeBinaryTree left1 = new p297_SerializeAndDeserializeBinaryTree(2);
        p297_SerializeAndDeserializeBinaryTree right1 = new p297_SerializeAndDeserializeBinaryTree(3);
        p297_SerializeAndDeserializeBinaryTree right2 = new p297_SerializeAndDeserializeBinaryTree(4);
        p297_SerializeAndDeserializeBinaryTree right3 = new p297_SerializeAndDeserializeBinaryTree(5);
        // 创建这棵树
        root.left = left1;
        root.right = right1;
        right1.left = right2;
        right1.right = right3;
        // 序列化结果
        String sres = serializa(root);
        System.out.println("序列化结果为:" + sres);
        // 反序列化结果
        p297_SerializeAndDeserializeBinaryTree dres = deserialize(sres);
        System.out.println("反序列化结果为:" + dres);
    }

    /**
     * 序列化
     */
    public static String serializa(p297_SerializeAndDeserializeBinaryTree root) {
        StringBuffer res = Seri(root, new StringBuffer()); // 这里使用StringBuffer存储序列化的结果，调用Seri方法实现序列化
        return res.toString(); // 返回String类型
    }

    /**
     * 序列化核心方法
     * @param root 根节点
     * @param sb StringBuffer
     * @return
     */
    public static StringBuffer Seri(p297_SerializeAndDeserializeBinaryTree root, StringBuffer sb) {
        if (root == null) { // 先判断根节点是否为空，如果为空，sb添加null，我们不能忽略根节点，如果忽略就无法反序列化，如果到最后到底之后返回整个数据
            sb.append("null,"); // 添加为空的节点为null
            return sb;
        } else if (root != null) { // 如果不为空，就把当前节点值添加进来
            sb.append(root.val);
            sb.append(","); // 以逗号分隔，方便反序列化
            Seri(root.left, sb); // 递归遍历左子树
            Seri(root.right, sb); // 递归遍历右子树
        }
        return sb;
    }

    /**
     * 反序列化
     * @param data 传进来的String类型的数据
     * @return
     */
    public static p297_SerializeAndDeserializeBinaryTree deserialize(String data) {
        String[] temp = data.split(","); // 以逗号拆分，放入临时数组
        List<String> list = new LinkedList<>(Arrays.asList(temp)); // 使用list保存，方便操作
        return Deri(list); // 调用核心方法
    }

    /**
     * 反序列化核心方法
     * @param list // 传入list数据
     * @return
     */
    private static p297_SerializeAndDeserializeBinaryTree Deri(List<String> list) {
        p297_SerializeAndDeserializeBinaryTree root; // 先初始化一个根节点
        if (list.get(0).equals("null")) { // 判断list第一个数据是否为空，如果为空，将这个数据删除，因为二叉树不需要null节点，使下一个结点成为根节点
            list.remove(0); // 删除null结点
            return null;
        } else { // 如果不为空，给root初始化结点为第一个结点，并将此数据从list中删除，以此向左向右递归，向root结点的左右添加值
            root = new p297_SerializeAndDeserializeBinaryTree(Integer.valueOf(list.get(0))); // 将第一个不为null的结点赋与root结点
            list.remove(0); // 从list中删除这个结点
            root.left = Deri(list); // 向左递归
            root.right = Deri(list); // 向右递归
        }
        return root; // 返回这棵树
    }

}
