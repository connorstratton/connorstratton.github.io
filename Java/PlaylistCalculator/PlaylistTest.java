// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Test class for the Playlist class
 * 
 * @author Connor Stratton
 * @version Nov 4, 2024
 */
public class PlaylistTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private Playlist tester;

    // ~ Constructors ..........................................................

    /**
     * Instantiates field variable
     */
    public void setUp()
    {
        tester = new Playlist("tester", 10, 20, 30, 70, 80, 90, 3);
    }

    // ~Public Methods ........................................................


    /**
     * Tests getMinGenreSet and getMaxGenreSet methods
     */
    public void testGenreSets()
    {
        assertEquals(10, tester.getMinGenreSet().getPop());
        assertEquals(20, tester.getMinGenreSet().getRock());
        assertEquals(30, tester.getMinGenreSet().getCountry());

        assertEquals(70, tester.getMaxGenreSet().getPop());
        assertEquals(80, tester.getMaxGenreSet().getRock());
        assertEquals(90, tester.getMaxGenreSet().getCountry());
    }


    /**
     * Tests getName and setName methods
     */
    public void testNames()
    {
        assertEquals("tester", tester.getName());
        tester.setName("newName");
        assertEquals("newName", tester.getName());
    }


    /**
     * Tests getCapacity, isFull, getNumberOfSongs, and getSpacesLeft methods
     */
    public void testSizing()
    {
        assertEquals(3, tester.getCapacity());
        assertFalse(tester.isFull());
        assertEquals(0, tester.getNumberOfSongs());
        assertEquals(3, tester.getSpacesLeft());

        tester.addSong(new Song("test", 20, 30, 40, "country"));

        assertEquals(3, tester.getCapacity());
        assertFalse(tester.isFull());
        assertEquals(1, tester.getNumberOfSongs());
        assertEquals(2, tester.getSpacesLeft());

        tester.addSong(new Song("test2", 30, 40, 50, "country"));
        tester.addSong(new Song("test3", 40, 50, 60, "country"));

        assertEquals(3, tester.getCapacity());
        assertTrue(tester.isFull());
        assertEquals(3, tester.getNumberOfSongs());
        assertEquals(0, tester.getSpacesLeft());
    }


    /**
     * Tests the getSongs method
     */
    public void testGetSongs()
    {
        Song[] arrTest = new Song[3];

        arrTest[0] = new Song("test0", 10, 20, 30, "country");
        arrTest[1] = new Song("test1", 20, 30, 40, "country");
        arrTest[2] = new Song("test2", 30, 40, 50, "country");

        tester.addSong(new Song("test0", 10, 20, 30, "country"));
        tester.addSong(new Song("test1", 20, 30, 40, "country"));
        tester.addSong(new Song("test2", 30, 40, 50, "country"));

        for (int i = 0; i < tester.getNumberOfSongs(); i++)
        {
            assertTrue(arrTest[i].equals(tester.getSongs()[i]));
        }
    }


    /**
     * Tests compareTo and equals methods
     */
    public void testComparisons()
    {
        Playlist listNull = null;

        assertFalse(tester.equals(listNull));

        assertTrue(tester.equals(tester));

        Integer i = 10;

        assertFalse(tester.equals(i));

        Playlist testDiffCap =
            new Playlist("tester", 10, 20, 30, 70, 80, 90, 2);

        tester.addSong(new Song("test0", 10, 20, 30, "country"));
        testDiffCap.addSong(new Song("test0", 10, 20, 30, "country"));

        assertEquals(1, tester.compareTo(testDiffCap));
        assertFalse(tester.equals(testDiffCap));

        Playlist testDiffSpaces =
            new Playlist("tester", 10, 20, 30, 70, 80, 90, 3);

        testDiffSpaces.addSong(new Song("test0", 10, 20, 30, "country"));
        testDiffSpaces.addSong(new Song("test1", 20, 30, 40, "country"));

        assertEquals(1, tester.compareTo(testDiffSpaces));
        assertFalse(tester.equals(testDiffSpaces));

        Playlist testDiffMin = new Playlist("tester", 5, 15, 25, 70, 80, 90, 3);

        testDiffMin.addSong(new Song("test0", 10, 20, 30, "country"));

        assertEquals(15, tester.compareTo(testDiffMin));
        assertFalse(tester.equals(testDiffMin));

        Playlist testDiffMax =
            new Playlist("tester", 10, 20, 30, 65, 75, 85, 3);

        testDiffMax.addSong(new Song("test0", 10, 20, 30, "country"));

        assertEquals(15, tester.compareTo(testDiffMax));
        assertFalse(tester.equals(testDiffMax));

        Playlist diffName = new Playlist("utester", 10, 20, 30, 65, 75, 85, 3);

        diffName.addSong(new Song("test0", 10, 20, 30, "country"));

        assertEquals(15, tester.compareTo(diffName));
        assertFalse(tester.equals(diffName));

        Playlist same = new Playlist("tester", 10, 20, 30, 70, 80, 90, 3);

        same.addSong(new Song("test0", 10, 20, 30, "country"));

        assertEquals(0, tester.compareTo(same));
        assertTrue(tester.equals(same));

        Playlist sameSize = new Playlist("tester", 10, 20, 30, 70, 80, 90, 3);

        sameSize.addSong(new Song("test1", 10, 20, 30, "country"));

        assertFalse(tester.equals(sameSize));
    }


    /**
     * Tests isQualified method
     */
    public void testIsQualified()
    {
        Playlist full = new Playlist("full", 10, 20, 30, 60, 70, 80, 1);

        full.addSong(new Song("name", 30, 40, 50, "country"));

        Song test = new Song("test", 40, 50, 60, "country");

        assertFalse(full.isQualified(test));

        Song withinRange = new Song("inRange", 50, 60, 70, "country");

        assertTrue(tester.isQualified(withinRange));

        Song outOfRange = new Song("outRange", 5, 10, 15, "country");

        assertFalse(tester.isQualified(outOfRange));
    }


    /**
     * Tests addSong method
     */
    public void testAddSong()
    {
        assertFalse(tester.addSong(new Song("noAdd", 5, 10, 15, "country")));

        assertEquals(0, tester.getNumberOfSongs());

        Song toAdd = new Song("test", 30, 40, 50, "country");

        assertTrue(tester.addSong(toAdd));

        assertEquals(1, tester.getNumberOfSongs());
        assertEquals(toAdd, tester.getSongs()[0]);
    }


    /**
     * Tests toString method
     */
    public void testToString()
    {
        assertEquals(
            "Playlist: tester, # of songs: 0 (cap: 3), Requires: Pop:10%-70%, "
                + "Rock:20%-80%, Country:30%-90%",
            tester.toString());

        tester.addSong(new Song("test", 40, 50, 60, "country"));

        assertEquals(
            "Playlist: tester, # of songs: 1 (cap: 3), Requires: Pop:10%-70%, "
                + "Rock:20%-80%, Country:30%-90%",
            tester.toString());

    }

}
