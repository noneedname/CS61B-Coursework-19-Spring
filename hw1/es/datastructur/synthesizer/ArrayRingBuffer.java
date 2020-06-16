package es.datastructur.synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Buffer size. */
    private int capacity;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Helper class for iterator. */
    private class RingBufferIterator implements Iterator<T> {
        private int index;
        private int remaining;
        RingBufferIterator() {
            index = first;
            remaining = fillCount;
        }

        @Override
        public boolean hasNext() {
            return remaining != 0;
        }

        @Override
        public T next() {
            T item = rb[index];
            index = (index + 1) % capacity;
            remaining--;
            return item;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount++;
        rb[last] = x;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount--;
        T returnItem = rb[first];
        first = (first + 1) % capacity;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

}

