import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.*;

public class Main {
    static final int N = 50000;

    static long timeList(List list) {
        long start = System.currentTimeMillis();
        Object o = new Object();
        for (int i = 0; i < N; i++)
            list.add(0, o);
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        System.out.println("ArrayList耗时：" + timeList(new ArrayList()));
        System.out.println("LinkedList耗时：" + timeList(new LinkedList()));
    }

    @Test
    public void testMultiset() {
        Set<Integer> set = new HashSet();
        set.add(3);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(null);
        System.out.println(set.size());
        for (Integer i : set)
            System.out.println(i);
        List<Integer> arrayList = Lists.newArrayList(1, 2, 4, 4, 5, 6, 3, 2, 1);
        Multiset<Integer> multiset = HashMultiset.create(arrayList);
        for (Integer i : multiset.elementSet()) {
            System.out.printf("count %d:%d\n", i, multiset.count(i));
        }
    }

}
