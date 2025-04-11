// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

import stack.StackInterface;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * Class representing a Linked Stack object
 * 
 * @author Connor Stratton
 * @version Oct 7, 2024
 * @param <T>
 *            Generic data type for Linked Stack
 */
public class LinkedStack<T>
    implements StackInterface<T>
{
    // ~ Fields ................................................................

    private int size;
    private Node topNode;

    // ~ Constructors ..........................................................

    /**
     * Constructor for linked stack object
     */
    public LinkedStack()
    {
        topNode = null;
        size = 0;
    }

    // ~Public Methods ........................................................


    /**
     * Removes all elements from the linked stack
     */
    public void clear()
    {
        topNode = null;
        size = 0;
    }


    /**
     * Checks if there are no elements in the linked stack
     * 
     * @return size == 0 true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }


    /**
     * Gets the top element in the linked stack
     * 
     * @return topNode.getData() Data value of the element on the top of the
     *             linked stack
     */
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        return topNode.getData();
    }


    /**
     * Removes and returns top element of the linked stack
     * 
     * @return temp Data of the element being removed
     */
    public T pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        T temp = topNode.getData();

        topNode.setNextNode(topNode.getNextNode());

        topNode = topNode.getNextNode();

        size--;

        return temp;
    }


    /**
     * Adds an element to the top of the linked stack
     * 
     * @param anEntry
     *            element being added
     */
    public void push(T anEntry)
    {
        Node newNode = new Node(anEntry, topNode);

        size++;

        topNode = newNode;
    }


    /**
     * Gets the current size of the linked stack
     * 
     * @return size number of elements in the linked stack
     */
    public int size()
    {
        return size;
    }


    /**
     * Returns linked stack in an styled string
     * 
     * @return toString() formatted string of elements in the linked stack
     */
    public String toString()
    {
        String str = "";

        int i = 0;
        int iterations = size;

        Node currentNode = topNode;

        while (currentNode != null)
        {
            str += (currentNode.getData());
            currentNode = currentNode.getNextNode();

            i++;

            if (i != iterations)
            {
                str += ", ";
            }
        }

        return "[" + str + "]";

    }

    // -------------------------------------------------------------------------
    /**
     * Class representing a node object
     * 
     * @author Connor Stratton
     * @version Oct 8, 2024
     */
    private class Node
    {
        // ~ Fields

        private T data;
        private Node next;

        // ~ Constructors

        /**
         * 2 parameter constructor for Node object
         * 
         * @param entry
         *            data to be assigned to the node
         * @param node
         *            node to be set to as the next node
         */
        public Node(T entry, Node node)
        {
            this(entry);
            this.setNextNode(node);
        }


        /**
         * 1 parameter constructor for Node object
         * 
         * @param data
         *            data to be assigned to the node
         */
        public Node(T data)
        {
            this.data = data;
        }

        // ~ Public Methods


        /**
         * Gets data assigned to current node
         * 
         * @return data current node's data
         */
        public T getData()
        {
            return data;
        }


        /**
         * Gets the reference to the next node in the stack
         * 
         * @return next next node in the stack
         */
        public Node getNextNode()
        {
            return next;
        }


        /**
         * Changes the next node of the current node
         * 
         * @param entry
         *            Node to be referenced as next for the stack
         */
        public void setNextNode(Node entry)
        {
            next = entry;
        }

    }

}
