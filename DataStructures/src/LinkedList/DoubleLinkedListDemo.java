package LinkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 添加
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        // 修改前的链表情况
        System.out.println("修改前的链表情况：");
        // 显示
        doubleLinkedList.list();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        // 修改后的链表情况
        System.out.println("修改后的链表情况：");
        // 显示
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.del(3);
        // 删除后的链表情况
        System.out.println("删除后的链表情况：");
        // 显示
        doubleLinkedList.list();
    }

}

// 创建一个双向链表的类
class DoubleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 遍历双向链表的方法
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移， 一定小心
            temp = temp.next;
        }
    }

    // 添加节点到双向链表
    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动，因为我们需要一个辅助变量temp
        HeroNode2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode2 newHeroNode) {
        // 首先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 先定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; // 表示找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到要修改的节点
        if (flag == true) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            // 没有找到
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    // 删除节点
    public void del(int no) {
        // 判断当前链表是否为空
        if (head.next == null) { // 空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next; // 辅助变量（指针）
        boolean flag = false; // 标志是否找到待删除节点
        while (true) {
            if (temp.next == null) { // 已经到链表的最后
                break;
            }
            if (temp.no == no) {
                // 找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        if (flag) {
            // 找到，可以删除
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}

// 定义HeroNode2，每个HeroNode2对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点，默认为null
    public HeroNode2 pre; // 指向前一个节点，默认为null

    // 构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    // 为了显示方便，我们重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}