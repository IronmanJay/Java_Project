import java.util.PriorityQueue;

// 使用大顶堆和小顶堆的做法，分为奇数和偶数两种情况
// 在整个数据流中，中位数之前的有序数组中的最大值动态的放在大顶堆中，中位数之后的有序数组最大值放在小顶堆中
// 其实就是把整个数据流根据奇偶数分别放在不同的顶堆中
// 同时大顶堆的堆顶元素，要小于或者等于小顶堆的堆顶元素
// 大顶堆的元素个数是大于等于小顶堆的元素个数
public class p295_FindMedianFromDataStream {
    public static void main(String[] args) {
        p295_FindMedianFromDataStream p295_findMedianFromDataStream = new p295_FindMedianFromDataStream();
        p295_findMedianFromDataStream.addNum(1);
        p295_findMedianFromDataStream.addNum(3);
        p295_findMedianFromDataStream.addNum(1);
        p295_findMedianFromDataStream.addNum(5);
        p295_findMedianFromDataStream.addNum(3);
        double res = p295_findMedianFromDataStream.findMedian();
        System.out.println("res = " + res);
    }

    // 表示顶堆的容量大小，从而判断大顶堆和小顶堆的数量变化
    private int count;
    // 初始化大顶堆和小顶堆
    private PriorityQueue<Integer> maxheap;
    private PriorityQueue<Integer> minheap;

    // 初始化
    public p295_FindMedianFromDataStream() {
        count = 0; // 初始化容量为0
        maxheap = new PriorityQueue<>((x, y) -> y - x); // 这里使用lambda表达式，规定比较顺序
        minheap = new PriorityQueue<>();
    }

    // 加入数据，构建大顶堆和小顶堆
    public void addNum(int num) {
        count += 1; // 容量加1
        maxheap.offer(num); // 使用优先队列，加入大顶堆
        minheap.add(maxheap.poll()); // 先进入大顶堆，然后根据大顶堆的优先队列弹出的元素加入小顶堆
        if ((count & 1) != 0) { // 判断两个堆的元素和如果是奇数，那么根据前面分析，要保证大顶堆元素个数大于小顶堆的元素个数，所以小顶堆要拿出一个元素给大顶堆
            maxheap.add(minheap.poll());
        }
    }

    // 找出中位数
    public double findMedian() {
        if ((count & 1) == 0) { // 这说明两个堆元素和是偶数，那么整个数据流的中位数就是两个堆顶元素的平均值
            return (double) (maxheap.peek() + minheap.peek()) / 2;
        } else { // 这说明两个堆元素和是奇数，那么整个数据流的中位数就是中的堆顶元素
            return (double) maxheap.peek();
        }
    }

}


