// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

import queue.EmptyQueueException;

// -------------------------------------------------------------------------
/**
 * Test class for the ArrayQueue class
 * 
 * @author Connor Stratton
 * @version Nov 3, 2024
 */
public class ArrayQueueTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private ArrayQueue<String> tester;
    private ArrayQueue<String> tester2;

    // ~ Constructors ..........................................................

    /**
     * Instantiates fields
     */
    public void setUp()
    {
        tester = new ArrayQueue<String>(3);
        tester2 = new ArrayQueue<String>();
    }

    // ~Public Methods ........................................................


    /**
     * tests getSize method
     */
    public void testGetSize()
    {
        assertEquals(0, tester.getSize());

        tester.enqueue("test1");
        assertEquals(1, tester.getSize());

        tester.dequeue();
        assertEquals(0, tester.getSize());
    }


    /**
     * tests getLengthOfUnderlyingArray method
     */
    public void testGetLengthOfUnderlyingArray()
    {
        assertEquals(4, tester.getLengthOfUnderlyingArray());
        assertEquals(22, tester2.getLengthOfUnderlyingArray());

        tester.enqueue("test1");
        tester.enqueue("test2");
        tester.enqueue("test3");
        tester.enqueue("test4");

        assertEquals(7, tester.getLengthOfUnderlyingArray());
    }


    /**
     * Tests enqueue, ensureCapacity, incrementIndex, and isFull methods
     */
    public void testEnque()
    {
        assertEquals(0, tester.getSize());
        tester.enqueue("test1");
        assertEquals(1, tester.getSize());
        tester.enqueue("test2");
        assertEquals(2, tester.getSize());
        assertEquals(4, tester.getLengthOfUnderlyingArray());

        tester.enqueue("test3");
        assertEquals(3, tester.getSize());
        assertEquals(4, tester.getLengthOfUnderlyingArray());

        tester.enqueue("test4");
        assertEquals(4, tester.getSize());
        assertEquals(7, tester.getLengthOfUnderlyingArray());
    }


    /**
     * Tests dequeue method
     */
    public void testDequeue()
    {
        Exception thrown = null;

        try
        {
            tester.dequeue();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        tester.enqueue("test1");

        assertEquals(1, tester.getSize());
        assertEquals("test1", tester.dequeue());
        assertEquals(0, tester.getSize());
    }


    /**
     * Tests getFront
     */
    public void testGetFront()
    {
        Exception thrown = null;

        try
        {
            tester.getFront();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        tester.enqueue("test1");
        assertEquals(1, tester.getSize());
        assertEquals("test1", tester.getFront());
        assertEquals(1, tester.getSize());
        tester.enqueue("test2");
        assertEquals("test1", tester.getFront());
    }


    /**
     * Tests isEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(tester.isEmpty());
        tester.enqueue("test1");
        assertFalse(tester.isEmpty());
    }


    /**
     * Tests clear method
     */
    public void testClear()
    {
        tester.enqueue("test1");
        tester.enqueue("test2");
        assertEquals(2, tester.getSize());
        tester.clear();
        assertEquals(0, tester.getSize());
    }


    /**
     * Tests toString method
     */
    public void testToString()
    {
        assertEquals("[]", tester.toString());
        tester.enqueue("test1");
        assertEquals("[test1]", tester.toString());
        tester.enqueue("test2");
        assertEquals("[test1, test2]", tester.toString());
        tester.dequeue();
        assertEquals("[test2]", tester.toString());
    }


    /**
     * Tests toArray method
     */
    public void testToArray()
    {
        Exception thrown = null;
        try
        {
            tester.toArray();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        Object[] arrTest = { "test1" };

        tester.enqueue("test1");

        Object[] arrReturned = tester.toArray();

        for (int i = 0; i < tester.getSize(); i++)
        {
            assertEquals(arrTest[i], arrReturned[i]);
        }

        Object[] arrTest2 = { "test2", "test3", "test4" };

        tester.clear();
        tester.enqueue("test1");
        tester.enqueue("test2");
        tester.dequeue();
        tester.enqueue("test3");
        tester.enqueue("test4");

        Object[] arrSecond = tester.toArray();

        for (int i = 0; i < tester.getSize(); i++)
        {
            assertEquals(arrTest2[i], arrSecond[i]);
        }
    }


    /**
     * Tests equals method
     */
    public void testEquals()
    {
        ArrayQueue<String> testNull = null;

        assertFalse(tester.equals(testNull));

        assertTrue(tester.equals(tester));

        Integer i = 10;

        assertFalse(tester.equals(i));

        ArrayQueue<String> testDiffOrder = new ArrayQueue<String>(2);

        tester.enqueue("test1");
        tester.enqueue("test2");

        testDiffOrder.enqueue("test2");
        testDiffOrder.enqueue("test1");

        assertFalse(tester.equals(testDiffOrder));

        ArrayQueue<String> testDiffSize = new ArrayQueue<String>(1);

        testDiffSize.enqueue("test1");

        assertFalse(tester.equals(testDiffSize));

        ArrayQueue<String> testDiff = new ArrayQueue<String>(2);

        testDiff.enqueue("test1");
        testDiff.enqueue("test3");

        assertFalse(tester.equals(testDiff));

        ArrayQueue<String> testSame = new ArrayQueue<String>(3);

        testSame.enqueue("test1");
        testSame.enqueue("test2");

        assertTrue(tester.equals(testSame));

        testSame.dequeue();
        testSame.dequeue();
        testSame.enqueue("test1");
        testSame.enqueue("test2");

        assertTrue(tester.equals(testSame));
        
        ArrayQueue<String> testWrap = new ArrayQueue<String>(3);
        
        testWrap.enqueue("remove");
        testWrap.enqueue("remove");
        testWrap.enqueue("remove");
        
        testWrap.dequeue();
        testWrap.dequeue();
        testWrap.dequeue();
        testWrap.enqueue("test1");
        testWrap.enqueue("test2");

        assertTrue(tester.equals(testWrap));
        
        
        tester.enqueue("test3");
        testWrap.enqueue("test3");
        tester.enqueue("test4");
        testWrap.enqueue("test4");
        tester.enqueue("test5");
        testWrap.enqueue("test5");
        
        assertTrue(tester.equals(testWrap));
    }
}
