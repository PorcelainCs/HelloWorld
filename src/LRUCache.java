import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: shacs.cheng
 * Date: 2018/7/21
 * 使用LinkedHashMap实现一个LRU缓存
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5933045562735378538L;

    /**
     * 定义缓存容量
     */
    private int lruCapacity;

    /**
     * 默认构造方法
     */
    public LRUCache() {
        super();
    }

    /**
     * 带参数构造方法
     *
     * @param initialCapacity 容量
     * @param loadFactor      装载因子
     * @param isLRU           是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序）
     * @param lruCapacity     lru存储数据容量
     */
    public LRUCache(int initialCapacity, float loadFactor, boolean isLRU, int lruCapacity) {
        super(initialCapacity, loadFactor, true);
        this.lruCapacity = lruCapacity;
    }

    /**
     * LRUCache的get操作
     */
    public V get(Object key) {
        synchronized (this) {
            V v = super.get(key);
            if (v != null) {
                return v;
            } else {
                return null;
            }
        }
    }

    /**
     * put操作
     */
    public V put(K key, V value) {
        return super.put(key, value);
    }

    /**
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if ((size() > lruCapacity)) {
            System.out.println("超出已定的内存容量，把链表顶端元素移除：" + eldest.getKey() + "--" + eldest.getValue());
        }
        return size() > lruCapacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(16, 0.75f, true, 4);
        cache.put(9, 3);
        cache.put(7, 4);
        cache.put(5, 9);
        System.out.println("原始数据：");
        for (Map.Entry<Integer, Integer> integerIntegerEntry : cache.entrySet()) {
            System.out.println(integerIntegerEntry.getKey());
        }
        cache.get(7);
        System.out.println("访问缓存数据7后排序：");
        for (Map.Entry<Integer, Integer> integerIntegerEntry : cache.entrySet()) {
            System.out.println(integerIntegerEntry.getKey());
        }
        cache.put(3, 4);
        cache.put(6, 6);
        //总共put了5个元素，超过了指定的缓存最大容量
        //遍历结果
        System.out.println("添加5组数据后结果：");
        for (Map.Entry<Integer, Integer> integerIntegerEntry : cache.entrySet()) {
            System.out.println(integerIntegerEntry.getKey());
        }
    }
}
