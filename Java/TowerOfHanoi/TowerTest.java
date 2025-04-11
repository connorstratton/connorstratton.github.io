// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Test class for Tower class
 * 
 * @author Connor Stratton
 * @version Oct 8, 2024
 */
public class TowerTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private Tower testerD;
    private Tower testerL;
    private Tower testerR;
    private Tower testerC;

    // ~ Constructors ..........................................................

    /**
     * Method instantiating tester for test class
     */
    public void setUp()
    {
        testerD = new Tower(Position.DEFAULT);
        testerL = new Tower(Position.LEFT);
        testerR = new Tower(Position.RIGHT);
        testerC = new Tower(Position.CENTER);
    }

    // ~Public Methods ........................................................


    /**
     * Tests position method of Tower class
     */
    public void testPosition()
    {
        assertEquals(Position.DEFAULT, testerD.position());
        assertEquals(Position.LEFT, testerL.position());
        assertEquals(Position.RIGHT, testerR.position());
        assertEquals(Position.CENTER, testerC.position());
    }


    /**
     * Tests push method of Tower class
     */
    public void testPush()
    {
        Exception thrown = null;
        try
        {
            testerD.push(null);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof IllegalArgumentException);

        testerD.push(new Disk(10));

        thrown = null;
        try
        {
            testerD.push(new Disk(15));
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof IllegalStateException);

        testerL.push(new Disk(10));

        assertEquals(10, testerL.peek().getWidth());

        testerR.push(new Disk(15));
        testerR.push(new Disk(5));

        assertEquals(5, testerR.peek().getWidth());
    }
}
