// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

import list.AList;

// -------------------------------------------------------------------------
/**
 * Test class for PlaylistCalculator class
 * 
 * @author Connor Stratton
 * @version Nov 7, 2024
 */
public class PlaylistCalculatorTest
    extends student.TestCase
{
    // ~ Fields ................................................................

    private PlaylistCalculator tester;
    private Song test0;
    private Song test1;
    private Song test2;
    private Playlist pop;
    private Playlist rock;
    private Playlist country;

    // ~ Constructors ..........................................................

    /**
     * Instantiates objects for testing
     */
    public void setUp()
    {
        ArrayQueue<Song> songs = new ArrayQueue<Song>();
        test0 = new Song("test0", 50, 30, 40, "pop");
        songs.enqueue(test0);
        test1 = new Song("test1", 20, 30, 40, "rock");
        songs.enqueue(test1);
        test2 = new Song("test2", 30, 50, 40, "country");
        songs.enqueue(test2);
        pop = new Playlist("pop", 10, 20, 30, 70, 80, 90, 3);
        rock = new Playlist("rock", 10, 20, 30, 70, 80, 90, 3);
        country = new Playlist("country", 10, 20, 30, 70, 80, 90, 3);
        Playlist[] list = { pop, rock, country };
        tester = new PlaylistCalculator(songs, list);

        Exception thrown = null;
        try
        {
            PlaylistCalculator testNull = new PlaylistCalculator(null, list);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    // ~Public Methods ........................................................


    /**
     * Tests reject and getRejectTracks methods
     */
    public void testReject()
    {
        AList<Song> testList = new AList<Song>();

        assertEquals(testList, tester.getRejectedTracks());

        testList.add(test0);

        tester.reject();

        assertEquals(testList, tester.getRejectedTracks());
    }


    /**
     * Tests getPlaylists method
     */
    public void testGetPlaylists()
    {
        Playlist p1 = new Playlist("pop", 10, 20, 30, 70, 80, 90, 3);
        Playlist p2 = new Playlist("rock", 10, 20, 30, 70, 80, 90, 3);
        Playlist p3 = new Playlist("country", 10, 20, 30, 70, 80, 90, 3);
        Playlist[] test = { p1, p2, p3 };

        for (int i = 0; i < PlaylistCalculator.NUM_PLAYLISTS; i++)
        {
            assertTrue(test[i].equals(tester.getPlaylists()[i]));
        }
    }


    /**
     * Tests getQueue method
     */
    public void testGetQueue()
    {
        ArrayQueue<Song> test = new ArrayQueue<Song>();

        test.enqueue(test0);
        test.enqueue(test1);
        test.enqueue(test2);

        assertTrue(tester.getQueue().equals(test));
    }


    /**
     * Tests getPlaylistIndex method
     */
    public void testGetPlaylistIndex()
    {
        assertEquals(0, tester.getPlaylistIndex("pop"));
        assertEquals(1, tester.getPlaylistIndex("rock"));
        assertEquals(2, tester.getPlaylistIndex("country"));
        assertEquals(-1, tester.getPlaylistIndex("rap"));
    }


    /**
     * Tests getPlaylistForSong method
     */
    public void testGetPlaylistForSong()
    {
        assertNull(tester.getPlaylistForSong(null));

        assertEquals(pop, tester.getPlaylistForSong(test0));

        Song popOutOfRange = new Song("test", 20, 30, 10, "pop");

        assertNull(tester.getPlaylistForSong(popOutOfRange));

        assertEquals(rock, tester.getPlaylistForSong(test1));

        Song rockOutOfRange = new Song("test", 20, 30, 10, "rock");

        assertNull(tester.getPlaylistForSong(rockOutOfRange));

        assertEquals(country, tester.getPlaylistForSong(test2));

        Song countryOutOfRange = new Song("test", 20, 30, 10, "country");

        assertNull(tester.getPlaylistForSong(countryOutOfRange));

        Song noSuggested = new Song("test", 20, 30, 40, "");

        assertEquals(rock, tester.getPlaylistForSong(noSuggested));

        Song none = new Song("none", 100, 100, 100, "");

        assertNull(tester.getPlaylistForSong(none));

        Song nullSugg = new Song("none", 30, 40, 50, null);

        assertEquals(rock, tester.getPlaylistForSong(nullSugg));

        Song invalidPL = new Song("none", 30, 40, 50, "invalid");

        assertEquals(rock, tester.getPlaylistForSong(invalidPL));
    }


    /**
     * Tests the addSongToPlaylist method
     */
    public void testAddSongToPlaylist()
    {
        ArrayQueue<Song> empty = new ArrayQueue<Song>();

        Playlist[] list = { pop, rock, country };

        PlaylistCalculator testEmpty = new PlaylistCalculator(empty, list);

        assertFalse(testEmpty.addSongToPlaylist());

        empty.enqueue(test0);
        empty.enqueue(test1);
        assertEquals(test0, empty.getFront());
        assertTrue(testEmpty.addSongToPlaylist());
        assertEquals(
            test0,
            testEmpty.getPlaylists()[testEmpty.getPlaylistIndex("pop")]
                .getSongs()[0]);
        assertEquals(test1, empty.getFront());
        assertTrue(testEmpty.addSongToPlaylist());
        empty.enqueue(null);
        assertFalse(testEmpty.addSongToPlaylist());

    }
}
