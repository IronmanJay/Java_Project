package LinkedList;

import java.util.Stack;

public class p25_ReverseNodesInKGroup {

    int val;
    p25_ReverseNodesInKGroup next;

    p25_ReverseNodesInKGroup(int x) {
        this.val = x;
    }

    public static void main(String[] args) {

        p25_ReverseNodesInKGroup num1 = new p25_ReverseNodesInKGroup(1);
        p25_ReverseNodesInKGroup num2 = new p25_ReverseNodesInKGroup(2);
        p25_ReverseNodesInKGroup num3 = new p25_ReverseNodesInKGroup(3);
        p25_ReverseNodesInKGroup num4 = new p25_ReverseNodesInKGroup(4);
        p25_ReverseNodesInKGroup num5 = new p25_ReverseNodesInKGroup(5);
        num1.next = num2;
        num2.next = num3;
        num3.next = num4;
        num4.next = num5;

        int k = 2;

        p25_ReverseNodesInKGroup res = reverseKGroup(num1, k);

        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }

    }

    public static p25_ReverseNodesInKGroup reverseKGroup(p25_ReverseNodesInKGroup head, int k) {
        // 利用栈的后进先出特性实现反转
        Stack<p25_ReverseNodesInKGroup> stack = new Stack<p25_ReverseNodesInKGroup>();
        // 定义指向头结点的指针
        p25_ReverseNodesInKGroup newHead = new p25_ReverseNodesInKGroup(Integer.MIN_VALUE);
        newHead.next = head;
        // 定义前驱结点，这个就是出栈时把结点插入的指针
        p25_ReverseNodesInKGroup pre = newHead;
        // 开始循环遍历
        while (true) {
            // 用于计数剩下的元素是否够用
            int count = 0;
            // 临时结点，用于找到K个元素，找到之后为第K个元素的下一个结点，注意要从head开始，另外head后面会更新，否则就会一直从头遍历，更新之后的head就是现在的temp，所以第二次遍历时temp就会从上次的位置继续
            p25_ReverseNodesInKGroup temp = head;
            while (temp != null && count < k) {
                // 把符合位置的结点加入栈
                stack.push(temp);
                temp = temp.next;
                count++;
            }
            // 如果剩下的结点不够了，那么直接结束，并且pre指向head，目的是链接剩下的结点
            if (count != k) {
                pre.next = head;
                break;
            }
            // 从栈中依次弹出元素，重新插入，pre就是插入的指针
            while (!stack.isEmpty()) {
                pre.next = stack.pop();
                pre = pre.next;
            }
            // 当一部分反转完毕后，将反转之后的链表与temp链接，也就是和还没有反转的链表进行合并，这里的head很重要，每次都要更新到temp的位置，并且不会移动，只有在这里会移动一次，它不仅是每次满足条件K时temp的起始位置，也是不满足条件K时链接后面链表的指针
            pre.next = temp;
            head = temp;
        }
        // 返回结果
        return newHead.next;
    }

}
