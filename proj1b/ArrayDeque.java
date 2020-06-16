
public class ArrayDeque<T> implements Deque<T> {
    /**
     * Invariants:
     * 1. Add and remove must take constant time, except during resizing operations.
     * 2. Get and size must take constant time.
     * 3. The starting size of your array should be 8.
     * 4. The amount of memory that your program uses at any given time must be proportional to
     * the number of items. For example, if you add 10,000 items to the deque, and then
     * remove 9,999 items, you shouldn’t still be using an array of length 10,000ish.
     * For arrays of length 16 or more, your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low.
     */
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst = --nextFirst < 0 ? items.length - 1 : nextFirst;
        resize();
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        nextLast = ++nextLast % items.length;
        resize();
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
        for (T item : items) {
            if (item != null) {
                System.out.print(item + " ");
            }
        }
    }

    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            nextFirst = ++nextFirst % items.length;
            T item = items[nextFirst];
            items[nextFirst] = null;
            size--;
            resize();
            return item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (!isEmpty()) {
            nextLast = --nextLast < 0 ? items.length - 1 : nextLast;
            T item = items[nextLast];
            items[nextLast] = null;
            size--;
            resize();
            return item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        index = (nextFirst + index + 1) % items.length;
        return items[index];
    }


    private void resize() {
        if (size == items.length) {
            T[] temp = (T[]) new Object[size * 2];
            if (nextLast == 0 && nextFirst == size - 1) {
                //Copy the whole array because it is just in order.
                System.arraycopy(items, 0, temp, 0, items.length);
            } else {
                //Copy the front part of the array into the new one.
                System.arraycopy(items, nextFirst + 1, temp, 0, items.length - 1 - nextFirst);
                //Copy the last part of the array into the new one.
                System.arraycopy(items, 0, temp, items.length - 1 - nextFirst, nextLast);
            }

            items = temp;
            nextFirst = items.length - 1;
            nextLast = size;
        }

        if (size < 0.25 * items.length && items.length > 16) {
            T[] temp = (T[]) new Object[items.length / 2];
            if (nextLast > nextFirst) {
                System.arraycopy(items, nextFirst + 1, temp, 0, size);
            } else if (nextFirst == items.length - 1) {
                System.arraycopy(items, 0, temp, 0, size);
            } else {
                //Copy the front part of the array into the new one.
                System.arraycopy(items, nextFirst + 1, temp, 0, items.length - 1 - nextFirst);
                //Copy the last part of the array into the new one.
                System.arraycopy(items, 0, temp, items.length - 1 - nextFirst, nextLast);
            }

            items = temp;
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }
}
