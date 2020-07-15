import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        int hashcode;

        Node(K key, V value, Node<K, V> next, int hashcode) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashcode = hashcode;
        }
    }

    private final int initialSize;
    private final double loadFactor;
    private int length;
    private Node<K, V>[] buckets;
    private Set<K> keys;
    private int size;
    private int threshold;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        length = initialSize;
        buckets = (Node<K, V>[]) new Node[length];
        size = 0;
        threshold = (int) (length * loadFactor);
        keys = new HashSet<>();
    }

    @Override
    public void clear() {
        buckets = (Node<K, V>[]) new Node[initialSize];
        length = initialSize;
        size = 0;
        keys = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null!");
        }
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            int hashcode = hash(key, length);
            Node<K, V> node = buckets[hashcode];
            while (true) {
                if (node.hashcode == hashcode && node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hashcode = hash(key, buckets.length);
        if (containsKey(key)) {
            Node<K, V> node = buckets[hashcode];
            while (true) {
                if (node.hashcode == hashcode && node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
        } else {
            size++;
            if (size > threshold) {
                resize(2 * length);
            }
            keys.add(key);
            hashcode = hash(key, length);
            buckets[hashcode] = new Node<>(key, value, buckets[hashcode], hashcode);
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("unsupported!");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("unsupported!");
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    private int hash(K key, int bucketsLength) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % bucketsLength;
    }

    private void resize(int newSize) {
        Node<K, V>[] newBucks = (Node<K, V>[]) new Node[newSize];
        for (Node<K, V> bucket : buckets) {
            while (bucket != null) {
                int hashcode = hash(bucket.key, newSize);
                newBucks[hashcode] = new Node<>(bucket.key, bucket.value,
                                                newBucks[hashcode], hashcode);
                bucket = bucket.next;
            }
        }
        buckets = newBucks;
        length = newSize;
        threshold = (int) (loadFactor * length);
    }
}
