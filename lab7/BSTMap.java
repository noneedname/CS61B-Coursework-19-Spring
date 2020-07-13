import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node leftNode;
        private Node rightNode;
        private int size;

        public Node(K k, V v, int size) {
            this.key = k;
            this.value = v;
            this.size = size;
        }
    }

    // initialize the empty map.
    public BSTMap() {
    }

    // helper method for put.
    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftNode = put(node.leftNode, key, value);
        } else if (cmp > 0) {
            node.rightNode = put(node.rightNode, key, value);
        } else {
            node.value = value;
        }
        node.size = 1 + size(node.leftNode) + size(node.rightNode);
        return node;
    }

    // helper method for size(), in case of NullPointerException caused by empty child node.
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    // helper method for searching.
    private Node get(Node n, K key) {
        if (n == null) {
            return null;
        }
        if (key.equals(n.key)) {
            return n;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(n.leftNode, key);
        } else {
            return get(n.rightNode, key);
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls containsKey() with a null key");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        Node foundNode = get(root, key);
        return foundNode == null ? null : foundNode.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("keyset is not supported.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("removing is not supported.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("removing is not supported.");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("iterator is not supported.");
    }
}
