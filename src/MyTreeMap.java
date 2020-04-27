import java.util.Comparator;

// 这里的 K,V 是类型变量，形参
public class MyTreeMap<K, V> {
    // 这里的 M，N 是类型变量，形参
    public static class Entry<M, N> {
        // 这里的 M，N是类型变量，是M，N背后类型的运行
        private M key;
        private N value;

        private Entry<M, N> left;
        private Entry<M, N> right;
    }

    private Entry<K, V> root;
    private Comparator<K> comparator = null;

    public MyTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public MyTreeMap() {
    }

    public V get(K key) {
        Entry<K, V> cur = root;
        while (cur != null) {
            // key 和 cur.key 比较
            // 或者 key 的类型是 Comparable
            // 要不需要一个 Comparator
            int r;
            if (comparator != null) {
                r = comparator.compare(key, cur.key);
            } else {
                Comparable<K> kComparable = (Comparable<K>)key;
                r = kComparable.compareTo(cur.key);
            }

            if (r == 0) {
                return cur.value;
            } else if (r < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return null;
    }
}
