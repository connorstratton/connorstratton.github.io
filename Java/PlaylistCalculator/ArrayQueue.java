// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

// -------------------------------------------------------------------------
/**
 * Class representing an ArrayQueue data structure
 * 
 * @author Connor Stratton
 * @version Oct 31, 2024
 * @param <T>
 *            Generic type for ArrayQueue
 */
public class ArrayQueue<T>
    implements QueueInterface<T>
{

    // ~ Fields ................................................................

    /**
     * DEFAULT_CAPACITY starting capacity of queue if no value is specified
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    // ~ Constructors ..........................................................

    /**
     * 1 parameter constructor for ArrayQueue
     * 
     * @param capacity
     *            initial capacity of queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        queue = (T[])new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;

    }


    /**
     * No parameter constructor for ArrayQueue
     */
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY + 1);
    }

    // ~Public Methods ........................................................


    /**
     * Gets number of elements in the queue
     * 
     * @return size number of elements
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Gets capacity of the array
     * 
     * @return queue.length Capacity of underlying array
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    /**
     * Checks if array needs to be expanded
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        if (isFull())
        {
            T[] oldQueue = queue;
            int oldCap = oldQueue.length - 1;
            int newCap = 2 * oldCap;
            queue = (T[])new Object[newCap + 1];

            int j = dequeueIndex;
            int i = 0;
            while (j != enqueueIndex)
            {
                queue[i] = oldQueue[j];
                j = (j + 1) % oldQueue.length;
                i++;
            }

            dequeueIndex = 0;
            enqueueIndex = oldCap;
        }
    }


    /**
     * Adds 1 to index and checks if it wraps around
     * 
     * @param index
     *            index entered to be incremented
     * @return ((index + 1 ) % queue.length) one added to index, or wrapped
     *             around to front of array
     */
    private int incrementIndex(int index)
    {
        return ((index + 1) % queue.length);
    }


    /**
     * Removes all elements from ArrayQueue
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear()
    {
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }


    /**
     * Puts ArrayQueue into array format
     * 
     * @return arr array representation of queue
     */
    public Object[] toArray()
    {
        if (size == 0)
        {
            throw new EmptyQueueException();
        }

        Object[] arr = new Object[this.size];

        int i = 0;

        int index = dequeueIndex;

        while (i < this.size)
        {
            arr[i] = queue[index];
            i++;
            index = incrementIndex(index);
        }

        return arr;
    }


    /**
     * Puts queue into String representation
     * 
     * @return str.toString() String representation of queue built by string
     *             builder
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("[");

        if (size != 0)
        {
            int i = dequeueIndex;

            while (i != enqueueIndex)
            {
                str.append(queue[i]);
                i = incrementIndex(i);
                if (i != enqueueIndex)
                {
                    str.append(", ");
                }
            }
        }

        str.append("]");
        return str.toString();
    }


    /**
     * checks if 2 ArrayQueues are equal
     * 
     * @param obj
     *            object being compared to
     * @return true if objects are equal, false otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (this.getClass() == obj.getClass())
        {
            ArrayQueue<T> other = (ArrayQueue<T>)obj;
            if (this.size == other.size)
            {
                int i = this.dequeueIndex;
                int end = this.enqueueIndex;
                int j = other.dequeueIndex;
                while (i != end)
                {
                    if (!this.queue[i].equals(other.queue[j]))
                    {
                        return false;
                    }
                    i = this.incrementIndex(i);
                    j = other.incrementIndex(j);
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Checks if queue has no elements in it
     * 
     * @return true if there are no elements in the array, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }


    /**
     * Returns data in the front of the queue
     * 
     * @return queue[dequeueIndex] Index items are added to, or the front of the
     *             queue
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        return (queue[dequeueIndex]);
    }


    /**
     * Checks if all slots in the queue are filled
     * 
     * @return true if next enqueue would reach dequeue index, false otherwise
     */
    private boolean isFull()
    {
        int i = enqueueIndex;
        return (incrementIndex(i) == dequeueIndex);
    }


    /**
     * Adds an elements to the Queue
     * 
     * @param data
     *            element to be added to queue
     */
    @Override
    public void enqueue(T data)
    {
        this.ensureCapacity();

        queue[enqueueIndex] = data;

        enqueueIndex = incrementIndex(enqueueIndex);

        size++;
    }


    /**
     * Remove element from queue
     */
    @Override
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        T temp = queue[dequeueIndex];

        queue[dequeueIndex] = null;

        dequeueIndex = incrementIndex(dequeueIndex);

        size--;

        return temp;
    }

}
