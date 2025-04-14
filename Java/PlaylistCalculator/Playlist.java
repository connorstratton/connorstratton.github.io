// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Class representing a playlist object
 * 
 * @author Connor Stratton
 * @version Nov 3, 2024
 */
public class Playlist
    implements Comparable<Playlist>
{
    // ~ Fields ................................................................

    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    // ~ Constructors ..........................................................

    /**
     * Constructor for playlist object
     * 
     * @param playlistName
     *            name of the playlist
     * @param minPop
     *            minimum pop composition for the playlist
     * @param minRock
     *            minimum rock composition for the playlist
     * @param minCountry
     *            minimum country composition for the playlist
     * @param maxPop
     *            maximum pop composition for the playlist
     * @param maxRock
     *            maximum rock composition for the playlist
     * @param maxCountry
     *            maximum country composition for the playlist
     * @param playlistCap
     *            maximum allowed songs in the playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        name = playlistName;
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        numberOfSongs = 0;
        capacity = playlistCap;
        songs = new Song[capacity];
    }

    // ~Public Methods ........................................................


    /**
     * Gets the minimum genre set
     * 
     * @return minGenreSet minimum genre set
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    /**
     * Changes the name of the playlist
     * 
     * @param newName
     *            new name of the playlist
     */
    public void setName(String newName)
    {
        name = newName;
    }


    /**
     * Gets the number of spaces remaining in the playlist
     * 
     * @return capacity - numberOfSongs number of spaces left in the playlist
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    /**
     * Gets the mamimum genre set
     * 
     * @return maxGenreSet maximum genre set
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    /**
     * Compares this playlist to another based on different attributes
     * 
     * @param other
     *            playlist being compared to
     * @return int difference in different attributes depending on order
     */
    public int compareTo(Playlist other)
    {
        if (this.capacity != other.capacity)
        {
            return this.capacity - other.capacity;
        }

        if (this.getSpacesLeft() != other.getSpacesLeft())
        {
            return this.getSpacesLeft() - other.getSpacesLeft();
        }

        if (this.minGenreSet.compareTo(other.minGenreSet) != 0)
        {
            return this.minGenreSet.compareTo(other.minGenreSet);
        }

        if (this.maxGenreSet.compareTo(other.maxGenreSet) != 0)
        {
            return this.maxGenreSet.compareTo(other.maxGenreSet);
        }

        return this.getName().compareTo(other.getName());
    }


    /**
     * gets the number of songs in the playlist
     * 
     * @return numberOfSongs how many songs are currently in the playlist
     */
    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    /**
     * Adds song if song is qualified to be added
     * 
     * @param newSong
     *            song to be added
     * @return true if add successful, false otherwise
     */
    public boolean addSong(Song newSong)
    {
        if (isQualified(newSong))
        {
            songs[numberOfSongs] = newSong;
            numberOfSongs++;
            return true;
        }

        return false;
    }


    /**
     * Puts playlist in string representation
     * 
     * @return str.toString() string representation of a playlist
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("Playlist: " + name);
        str.append(", # of songs: " + numberOfSongs);
        str.append(" (cap: " + capacity + "), ");
        str.append("Requires: Pop:");

        str.append(minGenreSet.getPop());
        str.append("%-");
        str.append(maxGenreSet.getPop());
        str.append("%, ");
        str.append("Rock:");

        str.append(minGenreSet.getRock());
        str.append("%-");
        str.append(maxGenreSet.getRock());
        str.append("%, ");
        str.append("Country:");

        str.append(minGenreSet.getCountry());
        str.append("%-");
        str.append(maxGenreSet.getCountry());
        str.append("%");

        return str.toString();
    }


    /**
     * checks if the playlist is full
     * 
     * @return numberOfSongs - capacity true if array is full, false otherwise
     */
    public boolean isFull()
    {
        return (numberOfSongs == capacity);
    }


    /**
     * Checks if 2 playlists are equal
     * 
     * @param obj
     *            object being compared to
     * @return true if playlists contain same songs in same order, false
     *             otherwise
     */
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (this.getClass().equals(obj.getClass()))
        {
            Playlist other = (Playlist)obj;

            if (this.getName().equals(other.getName())
                && this.getMinGenreSet().equals(other.getMinGenreSet())
                && this.getMaxGenreSet().equals(other.getMaxGenreSet())
                && this.getCapacity() == other.getCapacity()
                && this.getNumberOfSongs() == other.getNumberOfSongs())
            {
                for (int i = 0; i < numberOfSongs; i++)
                {
                    if (!(this.songs[i].equals(other.getSongs()[i])))
                    {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }


    /**
     * Gets array of songs
     * 
     * @return songs array of the songs in the playlist
     */
    public Song[] getSongs()
    {
        return songs;
    }


    /**
     * Gets the capacity of the array
     * 
     * @return capacity capacity of the songs array
     */
    public int getCapacity()
    {
        return capacity;
    }


    /**
     * Gets the name of the playlist
     * 
     * @return name name of the playlist
     */
    public String getName()
    {
        return name;
    }


    /**
     * Checks if a song is qualified or not
     * 
     * @param possibleSong
     *            song being check if valid to enter playlist
     * @return true if song's genre set is within range and if playlist is not
     *             full, false otherwise
     */
    public boolean isQualified(Song possibleSong)
    {
        if (isFull())
        {
            return false;
        }

        return (possibleSong.getGenreSet()
            .isWithinRange(minGenreSet, maxGenreSet));
    }
}
