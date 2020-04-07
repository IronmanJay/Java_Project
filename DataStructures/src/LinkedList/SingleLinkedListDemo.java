package LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        // 先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        // 加入，先创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);*/
        // 加入按照编号的顺序
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        // 显示
        // 修改前的链表情况
        System.out.println("修改前的链表情况：");
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        // 修改后的链表情况
        System.out.println("修改后的链表情况：");
        // 显示
        singleLinkedList.list();
        // 测试删除一个节点
        singleLinkedList.del(1);
        // 删除后的链表情况
        System.out.println("删除后的链表情况：");
        // 显示
        singleLinkedList.list();
        // 测试：求单链表的有效个数
        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));
        // 测试：看看是否得到了倒数第K个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("倒数第K个节点为:" + res);
        // 测试：单链表的反转功能
        reversetList(singleLinkedList.getHead());
        // 反转后的链表情况
        System.out.println("反转后的链表情况：");
        // 显示
        singleLinkedList.list();
        // 测试：单链表的逆序打印功能
        // 逆序打印后的链表情况
        System.out.println("逆序打印后的链表情况：");
        reversePrint(singleLinkedList.getHead());
    }

    // 将单链表进行逆序打印【百度面试题】(使用栈Stack)
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return; // 空链表，不能打印
        }
        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // cur后移，这样就可以压入下一个节点
        }
        // 将栈中的节点进行打印，pop，出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); // Stack的特点是先进后出
        }
    }

    // 将单链表进行反转【腾讯面试题】
    public static void reversetList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 先定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next; // 让cur后移
        }
        // 将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    // 查找单链表的倒数的第K个节点【新浪面试题】
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表的长度
        int size = getLength(head);
        // 第二次遍历到size-index位置，就是我们倒数的第K个节点
        // 先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 获取到的有效的节点的个数(如果带头节点的链表，需要不统计头节点)
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            // 带头节点的空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助的变量，没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

// 定义SingleLinkList管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因为我们需要一个辅助变量temp
        HeroNode temp = head;
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
        temp.next = heroNode;
    }

    // 第二种添加英雄的方法
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为单链表,因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的heroNode的编号已经存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断flag的值
        if (flag == true) {
            // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在,不能加入\n", heroNode.no);
        } else {
            // 插入到链表中,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode) {
        // 首先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 先定义一个辅助变量
        HeroNode temp = head.next;
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
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的前一个节点
        while (true) {
            if (temp.next == null) {
                // 已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                // 找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        if (flag) {
            // 找到，可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }

    // 显示链表【遍历】
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将next后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int hNo, String hName, String hNickname) {
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