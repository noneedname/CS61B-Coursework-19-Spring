public interface Deque<T> {

    void addLast(T item);

    void addFirst(T item);

    boolean isEmpty();

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
