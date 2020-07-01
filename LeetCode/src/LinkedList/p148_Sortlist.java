package LinkedList;

public class p148_Sortlist {

    int val;
    p148_Sortlist next;

    p148_Sortlist(int x) {
        val = x;
    }

    public static void main(String[] args) {
        p148_Sortlist num1 = new p148_Sortlist(4);
        p148_Sortlist num2 = new p148_Sortlist(2);
        p148_Sortlist num3 = new p148_Sortlist(1);
        p148_Sortlist num4 = new p148_Sortlist(3);
        num1.next = num2;
        num2.next = num3;
        num3.next = num4;
        p148_Sortlist res = sortList(num1);
        res = res.next;
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static p148_Sortlist sortList(p148_Sortlist head) {
        // 如果链表为空直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 测试后发现head就是链表第一个节点，所以需要自己添加头结点，返回时去掉即可
        p148_Sortlist newHead = new p148_Sortlist(-1);
        newHead.next = head;
        return quickSort(newHead, null);
    }

    public static p148_Sortlist quickSort(p148_Sortlist head, p148_Sortlist end) {
        // 递归终止条件
        if (head == end || head.next == end) {
            return head;
        }
        // 临时链表，存放所有小于分点的值
        p148_Sortlist tempList = new p148_Sortlist(-1);
        // 分点，选择第一个作为分点即可
        p148_Sortlist partition = head.next;
        // 和分点比较的指针
        p148_Sortlist p = partition;
        // 临时链表的指针
        p148_Sortlist tempIndex = tempList;
        while (p.next != end) {
            // 如果分点的下一个点的值小于分点，那么把小的这个值放入临时链表，并移动指针，否则继续找下一个
            if (p.next.val < partition.val) {
                tempIndex.next = p.next;
                tempIndex = tempIndex.next;
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        // 临时链表保存的都是比第一个节点小的值，那么合并临时链表和原链表分点，小的在前面
        tempIndex.next = head.next;
        // 使头结点重新指向新的分好的临时链表
        head.next = tempList.next;
        // 分别向左和向右排序
        quickSort(head, partition);
        quickSort(partition, end);
        // 返回时去掉头结点
        return head;
    }

}
