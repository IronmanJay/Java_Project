package ABCAlternate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 *	如：ABCABCABC…… 依次递归
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        AlternameDemo ad = new AlternameDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    ad.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    ad.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    ad.loopC(i);
                    System.out.println("----------------");
                }
            }
        },"C").start();
    }
    
}

class AlternameDemo {
    private int number = 1; // 当前正在执行线程的标记

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // A
    public void loopA(int totalLoop) {
        lock.lock();
        try {
            // 判断
            if (number != 1) {
                condition1.await();
            }
            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            // 唤醒
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // B
    public void loopB(int totalLoop) {
        lock.lock();
        try {
            // 判断
            if (number != 2) {
                condition2.await();
            }
            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            // 唤醒
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // C
    public void loopC(int totalLoop) {
        lock.lock();
        try {
            // 判断
            if (number != 3) {
                condition3.await();
            }
            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            // 唤醒
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}