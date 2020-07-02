package LinkedList;

public class p147_InsertionSortList {

    int val;

    p147_InsertionSortList next;

    p147_InsertionSortList(int x) {
        this.val = x;
    }

    public static void main(String[] args) {
        p147_InsertionSortList num1 = new p147_InsertionSortList(4);
        p147_InsertionSortList num2 = new p147_InsertionSortList(2);
        p147_InsertionSortList num3 = new p147_InsertionSortList(1);
        p147_InsertionSortList num4 = new p147_InsertionSortList(3);
        num1.next = num2;
        num2.next = num3;
        num3.next = num4;
        p147_InsertionSortList res = insertionSortList(num1);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static p147_InsertionSortList insertionSortList(p147_InsertionSortList head) {
        // 如果为空直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 首先定义指向头结点的指针
        p147_InsertionSortList newHead = new p147_InsertionSortList(Integer.MIN_VALUE);
        newHead.next = head;
        // 定义前驱结点和链表指针
        p147_InsertionSortList cur = head;
        p147_InsertionSortList pre = newHead;
        // 遍历每一个结点
        while (cur != null) {
            // 如果待插入值小于基准值，那么进入查找插入位置并交换的代码
            if (cur.val < pre.val) {
                // 首先定义新的头节点，这个不是传统的最开始的结点，而是插入的那个头结点
                p147_InsertionSortList newIndex = newHead.next;
                // 定义新的前驱结点，这个newPre最后是待插入结点的前驱的前驱，用于链的修改
                p147_InsertionSortList newPre = newHead;
                // 这步很重要，如果newIndex==pre，说明找到了待插入结点，否则需要向下查找插入位置；并且如果newIndex.val<cur.val，说明这个结点不能被待插入结点插入，需要向下查找找到可以插入的位置
                while (newIndex != pre && newIndex.val < cur.val) {
                    newIndex = newIndex.next;
                    newPre = newPre.next;
                }
                // 已经找到待插入位置，首先将pre指向cur的下一个，断开pre和cur的连接
                pre.next = cur.next;
                // 然后cur指向插入位置，新建连接
                cur.next = newIndex;
                // 修改cur的前驱，将newPre指向cur，也就是修改之前的待插入结点的前驱的前驱不再指向pre，而是指向cur，因为cur和pre交换了位置
                newPre.next = cur;
                // 因为还需要继续排序，所以cur继续向下，此时cur为pre的下一个，也就是之前cur的下一个
                cur = pre.next;
            }
            // 否则两个指针继续向下比较
            else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        // 最后返回头结点
        return newHead.next;
    }

}
