// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Test class for the Song class
 * 
 * @author Connor Stratton
 * @version Oct 31, 2024
 */
public class SongTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private Song song1;

    // ~ Constructors ..........................................................

    /**
     * Instantiates field variable
     */
    public void setUp()
    {
        song1 = new Song("Locked Out of Heaven", 70, 30, 0, "pop");
    }

    // ~Public Methods ........................................................


    /**
     * Tests accessor methods
     */
    public void testAccessors()
    {
        assertEquals("Locked Out of Heaven", song1.getName());
        assertEquals("pop", song1.getPlaylistName());

        GenreSet sameSet = new GenreSet(70, 30, 0);

        assertEquals(sameSet, song1.getGenreSet());

    }


    /**
     * Tests equals method
     */
    public void testEquals()
    {
        Song songNull = null;
        assertFalse(song1.equals(songNull));

        assertTrue(song1.equals(song1));

        Integer i = 10;
        assertFalse(song1.equals(i));

        Song songAllDiff = new Song("Last Night", 30, 10, 60, "country");
        assertFalse(song1.equals(songAllDiff));

        Song diffName = new Song("Time of Our Lives", 70, 30, 0, "pop");
        assertFalse(song1.equals(diffName));

        Song diffPop = new Song("Locked Out of Heaven", 60, 30, 0, "pop");
        assertFalse(song1.equals(diffPop));

        Song diffRock = new Song("Locked Out of Heaven", 70, 40, 0, "pop");
        assertFalse(song1.equals(diffRock));

        Song diffCountry = new Song("Locked Out of Heaven", 70, 30, 10, "pop");
        assertFalse(song1.equals(diffCountry));

        Song diffSuggested =
            new Song("Locked Out of Heaven", 70, 30, 0, "rock");
        assertFalse(song1.equals(diffSuggested));

        Song same = new Song("Locked Out of Heaven", 70, 30, 0, "pop");
        assertTrue(song1.equals(same));

    }


    /**
     * Tests toString
     */
    public void testToString()
    {
        assertEquals(
            "Locked Out of Heaven Pop:70 Rock:30 Country:0 Suggested: pop",
            song1.toString());

        Song noSuggested = new Song("Last Night", 30, 10, 60, "");

        assertEquals(
            "No-Playlist Last Night Pop:30 Rock:10 Country:60",
            noSuggested.toString());
    }

}
