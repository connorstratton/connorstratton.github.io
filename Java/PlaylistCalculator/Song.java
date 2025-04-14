// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Class representing a song object
 * 
 * @author Connor Stratton
 * @version Oct 31, 2024
 */
public class Song
{
    // ~ Fields ................................................................

    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    // ~ Constructors ..........................................................

    /**
     * Constructor for song object;
     * 
     * @param name
     *            name of song
     * @param pop
     *            percent pop composition
     * @param rock
     *            percent rock composition
     * @param country
     *            percent county composition
     * @param suggested
     *            suggested playlist
     */
    public Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        genreSet = new GenreSet(pop, rock, country);
        suggestedPlaylist = suggested;
    }

    // ~Public Methods ........................................................


    /**
     * Accessor method for the suggestedPlaylist
     * 
     * @return suggestedPlaylist playlist name
     */
    public String getPlaylistName()
    {
        return suggestedPlaylist;
    }


    /**
     * Accessor method for the name
     * 
     * @return name name of the song
     */
    public String getName()
    {
        return name;
    }


    /**
     * Accessor method for the genreSet
     * 
     * @return genreSet genre set of the song
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }


    /**
     * Checks if 2 songs are equal
     * 
     * @param obj
     *            object being compared to
     * @return true if equal, false otherwise
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (this.getClass().equals(obj.getClass()))
        {
            Song other = (Song)obj;
            if (this.getName().equals(other.getName())
                && this.getGenreSet().equals(other.getGenreSet())
                && this.getPlaylistName().equals(other.getPlaylistName()))

            {
                return true;
            }
        }

        return false;
    }


    /**
     * Puts song into string representation
     * 
     * @return str.toString() string representation of a song
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        boolean addToEnd = true;

        if (this.getPlaylistName().length() == 0)
        {
            str.append("No-Playlist ");
            addToEnd = false;
        }

        str.append(this.getName() + " ");
        str.append(this.getGenreSet().toString());

        if (addToEnd)
        {
            str.append(" Suggested: " + this.getPlaylistName());
        }

        return str.toString();
    }

}
