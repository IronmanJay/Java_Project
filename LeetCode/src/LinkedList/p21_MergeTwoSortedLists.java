package LinkedList;

public class p21_MergeTwoSortedLists {

    int val;
    p21_MergeTwoSortedLists next;

    p21_MergeTwoSortedLists() {

    }

    p21_MergeTwoSortedLists(int val) {
        this.val = val;
    }

    p21_MergeTwoSortedLists(int val, p21_MergeTwoSortedLists next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        p21_MergeTwoSortedLists l1Num1 = new p21_MergeTwoSortedLists(1);
        p21_MergeTwoSortedLists l1Num2 = new p21_MergeTwoSortedLists(2);
        p21_MergeTwoSortedLists l1Num3 = new p21_MergeTwoSortedLists(4);
        p21_MergeTwoSortedLists l2Num1 = new p21_MergeTwoSortedLists(1);
        p21_MergeTwoSortedLists l2Num2 = new p21_MergeTwoSortedLists(3);
        p21_MergeTwoSortedLists l2Num3 = new p21_MergeTwoSortedLists(4);
        l1Num1.next = l1Num2;
        l1Num2.next = l1Num3;
        l2Num1.next = l2Num2;
        l2Num2.next = l2Num3;
        p21_MergeTwoSortedLists res = mergeTwoLists(l1Num1, l2Num1);
        res = res.next;
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static p21_MergeTwoSortedLists mergeTwoLists(p21_MergeTwoSortedLists l1, p21_MergeTwoSortedLists l2) {
        p21_MergeTwoSortedLists resHead = new p21_MergeTwoSortedLists(-1);
        p21_MergeTwoSortedLists res = resHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                res.next = l1;
                l1 = l1.next;
            } else {
                res.next = l2;
                l2 = l2.next;
            }
            res = res.next;
        }
        res.next = l1 == null ? l2 : l1;
        return resHead;
    }

}
