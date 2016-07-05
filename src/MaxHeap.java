
/**
 * Your implementation of a max heap.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>> implements
        HeapInterface<T> {

    private T[] backingArray;
    private int size;

    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE}.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    @SuppressWarnings("unchecked")
    public MaxHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;

    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size > backingArray.length - 2) {
            T[] newArray = (T[]) new Comparable[(size + 1) * 2];
            for (int i = 0; i <= size; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }

        backingArray[size + 1] = item;
        size++;

        int index = size;
        while (index != 1) {
            if (item.compareTo(backingArray[index / 2]) > 0) {
                // added item larger than its parent

                backingArray[index] = backingArray[index / 2];
                backingArray[index / 2] = item;
                index = index / 2;
            } else {
                index = 1;
                break;
            }
        }

    }

    @Override
    public T remove() {

        if (size == 0) {

            throw new java.util.NoSuchElementException();
        }
        T removed = backingArray[1];

        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        int index = 1;
        while (index < (size / 2 + 1)) {

            T item = backingArray[index];

            if ((item.compareTo(backingArray[index * 2]) < 0
                    && backingArray[index * 2 + 1] == null)
                    || (item.compareTo(backingArray[index * 2]) < 0
                            && backingArray[index * 2].compareTo(
                                    backingArray[index * 2 + 1])
                            > 0)) {
                // left node is larger

                backingArray[index] = backingArray[index * 2];
                backingArray[index * 2] = item;
                index = index * 2;
            } else if (backingArray[index * 2 + 1] != null
                    && item.compareTo(backingArray[index * 2 + 1]) < 0) {

                // right node is larger

                backingArray[index] = backingArray[index * 2 + 1];
                backingArray[index * 2 + 1] = item;
                index = index * 2 + 1;
            } else {

                index = size;
                break;
            }
        }
        return removed;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }


}
