// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

import list.AList;
import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 * Carries out back end calculations to determine eligibility for playlists, and
 * managing playlists
 * 
 * @author Connor Stratton
 * @version Nov 6, 2024
 */
public class PlaylistCalculator
{

    // ~ Fields ................................................................
    /**
     * NUM_PLAYLISTS constant for number of playlists
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * MIN_PERCENT minimum percent for [FILL IN] --------------------------
     */
    public static final int MIN_PERCENT = 0;
    /**
     * MAX_PERCENT maximum percent for [FILL IN] --------------------------
     */
    public static final int MAX_PERCENT = 100;
    private Playlist[] playlists;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;

    // ~ Constructors ..........................................................

    /**
     * Constructor for the PlaylistCalculator
     * 
     * @param songQueue
     *            ArrayQueue of songs
     * @param playlists
     *            Array of playlists
     */
    public PlaylistCalculator(ArrayQueue<Song> songQueue, Playlist[] playlists)
    {
        if (songQueue == null)
        {
            throw new IllegalArgumentException();
        }

        this.songQueue = songQueue;
        this.playlists = playlists;
        rejectedTracks = new AList<Song>();

    }

    // ~Public Methods ........................................................


    /**
     * Gets playlist for song to be added to
     * 
     * @param nextSong
     *            song to be checked
     * @return Playlist Playlist eligible to be added to, or null
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }

        if (nextSong.getPlaylistName() == null)
        {
            return getPlaylistWithMaximumCapacity(nextSong);
        }

        int suggestedIndex = -1;

        suggestedIndex = getPlaylistIndex(nextSong.getPlaylistName());

        if (suggestedIndex >= 0)
        {
            if (playlists[suggestedIndex].isQualified(nextSong))
            {
                return playlists[suggestedIndex];
            }
            return null;
        }

        return getPlaylistWithMaximumCapacity(nextSong);
    }


    /**
     * Checks which playlist to add to if no suggested playlist
     * 
     * @param aSong
     *            song being checked
     * @return copy[i] Playlist to add to
     */
    private Playlist getPlaylistWithMaximumCapacity(Song aSong)
    {
        Playlist[] copy;
        copy = Arrays.copyOf(playlists, playlists.length);

        Arrays.sort(copy);

        int i = playlists.length - 1;
        
        while (i >= 0)
        {
            if (copy[i].isQualified(aSong))
            {
                return copy[i];
            }
            i--;
        }

        return null;
    }


    /**
     * Adds a song to the playlist if applicable
     * 
     * @return true if addition successful, false otherwise
     */
    public boolean addSongToPlaylist()
    {
        if (songQueue.isEmpty())
        {
            return false;
        }

        Song suggested = songQueue.getFront();

        if (getPlaylistForSong(suggested) != null)
        {
            getPlaylistForSong(suggested).addSong(suggested);
            songQueue.dequeue();
            return true;
        }

        return false;

    }


    /**
     * Takes the rejected song out of the queue, and moves it to the list of
     * rejected songs
     */
    public void reject()
    {
        rejectedTracks.add(songQueue.dequeue());
    }


    /**
     * Finds index of a certain playlist in the array
     * 
     * @param playlist
     *            playlist being searched
     * @return i index of element
     */
    public int getPlaylistIndex(String playlist)
    {
        int i = 0;

        while (i < playlists.length)
        {
            if (playlists[i].getName().equals(playlist))
            {
                return i;
            }
            i++;
        }

        return -1;
    }


    /**
     * Gets ArrayQueue of songs
     * 
     * @return songQueue ArrayQueue of songs
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    /**
     * Gets the list of playlists
     * 
     * @return playlists array of playlists
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }


    /**
     * Gets the list of tracks that have been rejected
     * 
     * @return rejectedTracks list of rejected tracks
     */
    public AList<Song> getRejectedTracks()
    {
        return rejectedTracks;
    }
}

