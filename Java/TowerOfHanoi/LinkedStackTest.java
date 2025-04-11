package towerofhanoi;

import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * Test class for LinkedStack test
 * 
 * @author Connor Stratton
 * @version Oct 8, 2024
 */
public class LinkedStackTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private LinkedStack<String> tester;

    // ~ Constructors ..........................................................

    /**
     * setUp method to initialize fields for testing
     */
    public void setUp()
    {
        tester = new LinkedStack<String>();
        tester.push("test1");
    }
    // ~Public Methods ........................................................


    /**
     * tests clear method of LinkedStack class
     */
    public void testClear()
    {
        assertEquals(1, tester.size());
        assertEquals("[test1]", tester.toString());
        tester.clear();
        assertEquals(0, tester.size());
        assertEquals("[]", tester.toString());
    }


    /**
     * tests isEmpty method of LinkedStack class
     */
    public void testIsEmpty()
    {
        assertFalse(tester.isEmpty());
        tester.clear();
        assertTrue(tester.isEmpty());
    }


    /**
     * tests peek method of LinkedStack class
     */
    public void testPeek()
    {
        assertEquals("test1", tester.peek());

        tester.clear();

        Exception thrown = null;
        try
        {
            tester.peek();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * tests pop method of LinkedStack class
     */
    public void testPop()
    {
        tester.push("test2");

        assertEquals("test2", tester.peek());

        assertEquals(2, tester.size());

        assertEquals("test2", tester.pop());

        assertEquals("test1", tester.peek());

        assertEquals(1, tester.size());

        assertEquals("test1", tester.pop());

        Exception thrown = null;
        try
        {
            tester.pop();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * tests push method of LinkedStack class
     */
    public void testPush()
    {
        assertEquals(1, tester.size());

        tester.push("test2");

        assertEquals(2, tester.size());

        tester.push("test3");
        tester.push("test4");
        tester.push("test5");
        tester.push("test6");
        tester.push("test7");
        tester.push("test8");
        tester.push("test9");
        tester.push("test10");

        assertEquals("test10", tester.peek());

        tester.pop();
        tester.pop();

        assertEquals("test8", tester.peek());

    }


    /**
     * tests size method of LinkedStack class
     */
    public void testSize()
    {
        assertEquals(1, tester.size());

        tester.push("test2");

        assertEquals(2, tester.size());

        tester.clear();

        assertEquals(0, tester.size());

    }


    /**
     * tests toString method of LinkedStack class
     */
    public void testToString()
    {
        assertEquals("[test1]", tester.toString());

        tester.push("test2");

        assertEquals("[test2, test1]", tester.toString());

        tester.clear();

        assertEquals("[]", tester.toString());
    }

}
