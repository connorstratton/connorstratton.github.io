// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Test class for Disk class
 * 
 * @author Connor Stratton
 * @version Oct 7, 2024
 */
public class DiskTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private Disk tester;

    // ~ Constructors ..........................................................

    /**
     * Method initializing object for each test
     */
    public void setUp()
    {
        tester = new Disk(5);
    }

    // ~Public Methods ........................................................


    /**
     * method testing compareTo method
     */
    public void testCompareTo()
    {
        Disk testSame = new Disk(5);
        assertEquals(0, tester.compareTo(testSame));

        Disk less = new Disk(2);
        assertEquals(3, tester.compareTo(less));

        Disk greater = new Disk(7);
        assertEquals(-2, tester.compareTo(greater));

        Disk diskNull = null;

        Exception thrown = null;
        try
        {
            tester.compareTo(diskNull);
        }
        catch (Exception exception)
        {
            thrown = exception;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * method testing equals method
     */
    public void testEquals()
    {
        assertTrue(tester.equals(tester));

        Disk testNull = null;

        assertFalse(tester.equals(testNull));

        Integer i = 10;

        assertFalse(tester.equals(i));

        Disk sameClass = new Disk(8);

        assertFalse(tester.equals(sameClass));

        Disk diskEqual = new Disk(5);

        assertTrue(tester.equals(diskEqual));
    }


    /**
     * method testing toString method
     */
    public void testToString()
    {
        assertEquals("5", tester.toString());

        Disk diskTest = new Disk(10);

        assertEquals("10", diskTest.toString());
    }
}
