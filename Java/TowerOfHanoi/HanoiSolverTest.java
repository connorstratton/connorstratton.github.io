package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Test class for HanoiSolver class
 * 
 * @author Connor Stratton
 * @version Oct 21, 2024
 */
public class HanoiSolverTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private HanoiSolver tester;

    // ~ Constructors ..........................................................

    /**
     * setUp method for the test class
     */
    public void setUp()
    {
        tester = new HanoiSolver(3);
        tester.getTower(Position.LEFT).push(new Disk(15));
        tester.getTower(Position.LEFT).push(new Disk(10));
        tester.getTower(Position.LEFT).push(new Disk(5));
    }

    // ~Public Methods ........................................................


    /**
     * tests the getTower method
     */
    public void testGetTower()
    {
        Tower tempL = new Tower(Position.LEFT);

        tempL.push(new Disk(15));
        tempL.push(new Disk(10));
        tempL.push(new Disk(5));

        assertEquals(
            tempL.position(),
            tester.getTower(Position.LEFT).position());

        assertEquals(
            tempL.toString(),
            tester.getTower(Position.LEFT).toString());

        assertEquals("[5, 10, 15]", tester.getTower(Position.LEFT).toString());
    }


    /**
     * tests the toString method
     */
    public void testToString()
    {
        assertEquals("[5, 10, 15][][]", tester.toString());

        tester.getTower(Position.RIGHT).push(new Disk(25));

        assertEquals("[5, 10, 15][][25]", tester.toString());

        tester.getTower(Position.RIGHT).push(new Disk(20));

        assertEquals("[5, 10, 15][][20, 25]", tester.toString());

        tester.getTower(Position.CENTER).push(new Disk(30));

        assertEquals("[5, 10, 15][30][20, 25]", tester.toString());

        tester.getTower(Position.RIGHT).clear();
        tester.getTower(Position.LEFT).clear();
        tester.getTower(Position.CENTER).clear();

        assertEquals("[][][]", tester.toString());

    }


    /**
     * tests the disks method
     */
    public void testDisks()
    {
        HanoiSolver testEmpty = new HanoiSolver(0);
        assertEquals(3, tester.disks());
        assertEquals(0, testEmpty.disks());
    }


    /**
     * tests the solve method
     */
    public void testSolve()
    {
        assertEquals("[5, 10, 15][][]", tester.toString());

        tester.solve();

        assertEquals("[][][5, 10, 15]", tester.toString());
    }
}
