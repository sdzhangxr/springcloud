import java.util.HashMap;
import java.util.Map;

/**
 * @className test
 * @Description 测试类
 * @Author sdzha
 * @Date 2020/12/7 16:42
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        /**
         * 1. static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
         *  默认初始化数组大小:
         *    假设用户没有手动传入初始长度，那么初始的数组容量就是：1 << 4 = 16
         *    PS：HashMap 数组部分的容量大小，必然为 2 的 N 次方
         * 2. static final int MAXIMUM_CAPACITY = 1 << 30;
         *  数组的最大容量:
         *    数组的最大容量：2^30
         * 3. static final float DEFAULT_LOAD_FACTOR = 0.75f;
         *  默认负载因子:
         *    用于计算初始化时数组的容量大小，最主要用于计算初始的扩容阈值
         * 4. static final int TREEIFY_THRESHOLD = 8;
         *  单链表转红黑树阈值:
         *    链表桶转红黑树临界值：8，即长度大于等于 8 转红黑树
         * 5. static final int UNTREEIFY_THRESHOLD = 6;
         *  红黑树转单链表阈值:
         *    红黑树转链表桶临界值：6，即长度小于等于 6 转链表
         * 6. static final int MIN_TREEIFY_CAPACITY = 64;
         *  最小树容量阈值:
         *    转变成树的 Table 的最小容量，小于该值则不会进行树化，即如果数组长度小于 64 不会转红黑树
         *【注意】
         *    transient关键字：将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会被序列化。
         * 7. transient Node<K,V>[] table;
         *   HashMap 的数组部分
         * 8. transient int size;
         *   HashMap 长度，换种理解方式就是 HashMap 中键值对数量
         * 9. transient int modCount;
         *   HashMap 被修改的次数
         *     由于 HashMap 是非线程安全的，所以可能出现线程A在操作的过程中，线程B对HashMap进行了修改，
         *     很明显这样的操作是有问题的，这时候可以利用这个属性快速失败，即抛出 ConcurrentModificationException 并发修改异常
         *     可以理解为是乐观锁
         * 10. int threshold;
         *   扩容阈值：当 HashMap 中的键值对数量达到 threshold 时，进行扩容
         * 11. final float loadFactor;
         *   负载因子：如果用户未指定，loadFactor == DEFAULT_LOAD_FACTOR
         *
         *
         *
         *
         *
         *
         */
    }
}
