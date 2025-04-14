// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Test class for GenreSet class
 * 
 * @author Connor Stratton
 * @version Oct 28, 2024
 */
public class GenreSetTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private GenreSet tester;
    private GenreSet equal;

    // ~ Constructors ..........................................................

    /**
     * Instantiates GenreSet objects for testing
     */
    public void setUp()
    {
        tester = new GenreSet(30, 40, 50);
        equal = new GenreSet(30, 40, 50);
    }
    // ~Public Methods ........................................................


    /**
     * Tests getRock, getPop, and getCountry method
     */
    public void testGetters()
    {
        assertEquals(40, tester.getRock());
        assertEquals(30, tester.getPop());
        assertEquals(50, tester.getCountry());
    }


    /**
     * Tests isWithinRange and isLessThanOrEqualTo methods
     */
    public void testIsWithinRange()
    {
        GenreSet less = new GenreSet(10, 20, 30);
        GenreSet more = new GenreSet(50, 60, 70);

        assertTrue(tester.isWithinRange(equal, more));
        assertTrue(tester.isWithinRange(less, equal));

        assertTrue(tester.isWithinRange(less, more));

        GenreSet tooMuchPop = new GenreSet(40, 20, 30);

        assertFalse(tester.isWithinRange(tooMuchPop, more));

        GenreSet tooMuchRock = new GenreSet(10, 50, 30);

        assertFalse(tester.isWithinRange(tooMuchRock, more));

        GenreSet tooMuchCountry = new GenreSet(10, 20, 60);

        assertFalse(tester.isWithinRange(tooMuchCountry, more));

        GenreSet notEnoughPop = new GenreSet(20, 60, 70);

        assertFalse(tester.isWithinRange(less, notEnoughPop));

        GenreSet notEnoughRock = new GenreSet(50, 30, 70);

        assertFalse(tester.isWithinRange(less, notEnoughRock));

        GenreSet notEnoughCountry = new GenreSet(20, 60, 40);

        assertFalse(tester.isWithinRange(less, notEnoughCountry));

        GenreSet samePop = new GenreSet(30, 20, 30);

        assertTrue(tester.isWithinRange(samePop, more));

        GenreSet sameRock = new GenreSet(50, 40, 70);

        assertTrue(tester.isWithinRange(less, sameRock));

        GenreSet sameCountry = new GenreSet(10, 20, 50);

        assertTrue(tester.isWithinRange(sameCountry, more));
    }


    /**
     * Tests the equals method
     */
    public void testEquals()
    {
        assertTrue(tester.equals(tester));

        GenreSet setNull = null;

        assertFalse(tester.equals(setNull));

        Integer i = 10;

        assertFalse(tester.equals(i));

        GenreSet diffPop = new GenreSet(20, 40, 50);

        assertFalse(tester.equals(diffPop));

        GenreSet diffRock = new GenreSet(30, 30, 50);

        assertFalse(tester.equals(diffRock));

        GenreSet diffCountry = new GenreSet(30, 40, 60);

        assertFalse(tester.equals(diffCountry));

        GenreSet same = new GenreSet(30, 40, 50);

        assertTrue(tester.equals(same));
    }


    /**
     * Tests compareTo method
     */
    public void testCompareTo()
    {
        GenreSet more = new GenreSet(40, 50, 60);
        GenreSet less = new GenreSet(20, 30, 40);

        assertEquals(0, tester.compareTo(equal));
        assertEquals(-30, tester.compareTo(more));
        assertEquals(30, tester.compareTo(less));
    }


    /**
     * Tests the toString method
     */
    public void testToString()
    {
        GenreSet diff = new GenreSet(40, 10, 80);

        assertEquals("Pop:30 Rock:40 Country:50", tester.toString());
        assertEquals("Pop:40 Rock:10 Country:80", diff.toString());
    }

}
