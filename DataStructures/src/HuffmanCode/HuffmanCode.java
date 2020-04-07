package HuffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

        // 测试压缩文件
        /*String srcFile = "d://src.bmp";
        String dstFile = "d://dst.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件ok");*/

        // 测试解压文件
        /*String zipFile = "d://dst.zip";
        String dstFile = "d://src2.bmp";
        unZipFile(zipFile,dstFile);
        System.out.println("解压文件ok");*/

        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原来的字符串：" + new String(sourceBytes));*/

    }

    // 编写方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile, String dstFile) {
        // 先定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写出数据到dstFile文件
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 编写方法，将文件进行压缩
    public static void zipFile(String srcFile, String dstFile) {
        // 创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        // 创建文件输入流
        FileInputStream is = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte数组
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutPutStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 这里以对象流的方式写入赫夫曼编码，是为了以后恢复源文件时使用
            // 一定要把赫夫曼编码写入到压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 编写一个方法，完成对压缩数据的解码
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 先得到huffmanBytes的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        // 把字符串按照指定的赫夫曼编码进行解码
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        // i可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 递增的取出key
                String key = stringBuilder.substring(i, i + count); // i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) { // 说明没有匹配到
                    count++;
                } else { // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i直接移动到count
        }
        // 当for循环结束后，list中就存放了所有的字符
        // 把list中的数据放到byte[]并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    // 完成数据的解压
    // 1、将huffmanCodesBytes重新先转成赫夫曼编码对应的二进制的字符串
    // 2、将赫夫曼编码对应的二进制的字符串对照赫夫曼编码重新转成原字符串
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b
        int temp = b; // 将b转成int
        // 如果是正数，还存在一个补高位的问题
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp); // 返回temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    // 使用一个方法，将前面的方法封装起来，便于我们的调用
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据node创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 生成对应的赫夫曼编码（根据赫夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    // 编写一个方法，将字符串对应的btye[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的btye[]数组
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1、利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试stringBuilder:" + stringBuilder.toString());
        // 将上面对应的字符串转成byte[]
        // 统计返回的长度
        // 一句话:int len = (stringBuilder.length() + 7) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte[]
        byte[] by = new byte[len];
        int index = 0; // 计数器，记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为每八位对应一个byte，所以步长为8
            String strByte;
            if (i + 8 > stringBuilder.length()) { // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 將strByte转成byte，放入到by
            by[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return by;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将传入的code加入到stringBuilder2
        stringBuilder2.append(code);
        if (code != null) { // 如果为空结点，不处理
            // 判断当前node是叶子结点还是非叶子结点
            if (node.data == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是一个叶子结点
                // 说明找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 前序遍历方法
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空，不能遍历");
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        // 创建一个ArrayLst
        ArrayList<Node> nodes = new ArrayList<Node>();
        // 遍历bytes，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每个键值对转化成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过List，创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序(从小到大)
            Collections.sort(nodes);
            // 取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一棵新的二叉树，他的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 将已经处理的两棵二叉树从nodes中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树加入到nodes
            nodes.add(parent);
        }
        // nodes最后的结点就是赫夫曼树的根节点
        return nodes.get(0);
    }

}

// 创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放数据本身
    int weight; // 权值，表示数据出现次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}