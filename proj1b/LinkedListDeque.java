
public class LinkedListDeque<T> implements Deque<T> {
    private final ItemNode<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode<>((T) " ", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ItemNode<T> temp = sentinel.next;

        for (int i = 0; i < size; i++) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size != 0) {
            T item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size != 0) {
            T item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        ItemNode<T> temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    private T getRecursive(ItemNode<T> currentItem, int i) {
        if (i == 0) {
            return currentItem.item;
        } else {
            return getRecursive(currentItem.next, i - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /**
     * Invariants:
     * 1. Add and remove operations must not involve any looping or recursion.
     * A single such operation must take “constant time”,
     * i.e. execution time should not depend on the size of the deque.
     * 2. Get must use iteration, not recursion.
     * 3. Size must take constant time.
     */
    private static class ItemNode<T> {
        private final T item;
        private ItemNode<T> next;
        private ItemNode<T> prev;

        ItemNode(T i, ItemNode<T> n, ItemNode<T> p) {
            item = i;
            next = n;
            prev = p;
        }
    }
}
