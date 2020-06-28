package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class I0202_KthNodeFromEndOfListLcc {

    int val;
    I0202_KthNodeFromEndOfListLcc next;

    I0202_KthNodeFromEndOfListLcc(int x) {
        val = x;
    }

    public static void main(String[] args) {
        I0202_KthNodeFromEndOfListLcc num1 = new I0202_KthNodeFromEndOfListLcc(1);
        I0202_KthNodeFromEndOfListLcc num2 = new I0202_KthNodeFromEndOfListLcc(2);
        I0202_KthNodeFromEndOfListLcc num3 = new I0202_KthNodeFromEndOfListLcc(3);
        I0202_KthNodeFromEndOfListLcc num4 = new I0202_KthNodeFromEndOfListLcc(4);
        I0202_KthNodeFromEndOfListLcc num5 = new I0202_KthNodeFromEndOfListLcc(5);
        num1.next = num2;
        num2.next = num3;
        num3.next = num4;
        num4.next = num5;
        int res = kthToLast(num1, 2);
        System.out.println("res = " + res);
    }

    public static int kthToLast(I0202_KthNodeFromEndOfListLcc head, int k) {
        List<Integer> arr = new ArrayList();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        return arr.get(arr.size() - k);
    }

}
