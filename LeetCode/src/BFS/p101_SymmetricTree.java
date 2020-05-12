package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class p101_SymmetricTree {

    int val;
    p101_SymmetricTree left;
    p101_SymmetricTree right;
    p101_SymmetricTree(int x){
        val = x;
    }

    public static void main(String[] args) {
        // 创建这棵树
        p101_SymmetricTree root = new p101_SymmetricTree(1);
        p101_SymmetricTree left = new p101_SymmetricTree(2);
        p101_SymmetricTree right = new p101_SymmetricTree(2);
        p101_SymmetricTree left1 = new p101_SymmetricTree(3);
        p101_SymmetricTree left2 = new p101_SymmetricTree(4);
        p101_SymmetricTree right1 = new p101_SymmetricTree(4);
        p101_SymmetricTree right2 = new p101_SymmetricTree(3);
        root.left = left;
        root.right = right;
        left.left = left1;
        left.right = left2;
        right.left = right1;
        right.right = right2;
        boolean res = isSymmetric(root);
        if(res){
            System.out.println("这棵树是对称的");
        }else{
            System.out.println("这棵树不是对称的");
        }
    }

    public static boolean isSymmetric(p101_SymmetricTree root) {
        // 首先判断是否是空树
        if(root==null){
            return true;
        }
        // 利用队列的先进先出特性，类似BFS，后面会比较每个弹出的元素是否相等
        Queue<p101_SymmetricTree> temp = new LinkedList<>();
        // 因为对称，可以理解为镜像，所以可以理解左右两棵树是否镜像对称
        temp.add(root.left);
        temp.add(root.right);
        while(!temp.isEmpty()){
            // 依次弹出每个节点
            p101_SymmetricTree t1 = temp.poll();
            p101_SymmetricTree t2 = temp.poll();
            // 如果两个节点都为空，那么说明已经遍历结束，所以跳出这次循环
            if(t1==null && t2 == null){
                continue;
            }
            if(t1==null || t2 == null){
                return false;
            }
            // 如果两个节点的值不相等，直接返回false
            if(t1.val != t2.val){
                return false;
            }
            // 能走到这里说明两个节点值相等
            // 这里是最重要的，因为是对称，所以入队列的次序非常重要
            // 我们首先将t1的左节点加入，再将t2的右节点加入，然后将t1的右节点加入，再将t2的左节点加入
            temp.add(t1.left);
            temp.add(t2.right);
            temp.add(t1.right);
            temp.add(t2.left);
        }
        // 跳出while循环后说明每一个节点都遍历到了，而且是对称的，所以返回true
        return true;
    }

}
