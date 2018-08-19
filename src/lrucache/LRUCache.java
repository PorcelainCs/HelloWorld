package lrucache;

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

    private int initialCapacity;//定义初始容量
    private int lruCapacity;//定义缓存容量
    private float loadFactor;//定义加载因子
    private boolean isLRU;//是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序）

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

    public static <K, V> LRUCacheBuilder<K, V> CacheBuilder() {
        return new LRUCacheBuilder<K, V>();
    }

    public static class LRUCacheBuilder<K, V> {
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private int initialCapacity;
        private int lruCapacity;
        private float loadFactor = DEFAULT_LOAD_FACTOR;
        private boolean isLRU;
        private LRUCacheBuilder() {
        }

        public LRUCacheBuilder initialCapacity(int initialCapacity) {
            this.initialCapacity = initialCapacity;
            return this;
        }

        public LRUCacheBuilder initLoadFactor(float loadFactor) {
            this.loadFactor = loadFactor;
            return this;
        }

        public LRUCacheBuilder initIsLRU(boolean isLRU) {
            this.isLRU = isLRU;
            return this;
        }

        public LRUCacheBuilder initLruCapacity(int lruCapacity) {
            this.lruCapacity = lruCapacity;
            return this;
        }

        public LRUCache<K, V> builder() {
            return new LRUCache<K, V>(initialCapacity, loadFactor, isLRU, lruCapacity);
        }
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

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public int getLruCapacity() {
        return lruCapacity;
    }

    public void setLruCapacity(int lruCapacity) {
        this.lruCapacity = lruCapacity;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
    }

    public boolean isLRU() {
        return isLRU;
    }

    public void setLRU(boolean LRU) {
        isLRU = LRU;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = LRUCache.<Integer, Integer>CacheBuilder().initialCapacity(16)
                .initIsLRU(true).initLruCapacity(4).builder();
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
