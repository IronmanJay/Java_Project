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
        if (head == null || head.next == null) {
            return head;
        }
        p148_Sortlist newHead = new p148_Sortlist(-1);
        newHead.next = head;
        return quickSort(newHead, null);
    }

    public static p148_Sortlist quickSort(p148_Sortlist head, p148_Sortlist end) {
        if (head == end || head.next == end) {
            return head;
        }
        p148_Sortlist tempList = new p148_Sortlist(-1);
        p148_Sortlist partition = head.next;
        p148_Sortlist p = partition;
        p148_Sortlist tempIndex = tempList;
        while (p.next != end) {
            if (p.next.val < partition.val) {
                tempIndex.next = p.next;
                tempIndex = tempIndex.next;
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        tempIndex.next = head.next;
        head.next = tempList.next;
        quickSort(head, partition);
        quickSort(partition, end);
        return head;
    }

}
