// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

// -------------------------------------------------------------------------
/**
 * Runner class for SpotifyPlaylist
 * 
 * @author Connor Stratton
 * @version Nov 10, 2024
 */
public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Runs PlaylistReader
     * 
     * @param args
     *            file names put into run configuration
     * @throws DailyMixDataException
     *             If there is an issue with the data in the file
     * @throws FileNotFoundException
     *             If the file is not in the directory
     * @throws ParseException
     *             If there is an issue parsing a file
     */
    public static void main(String[] args)
        throws DailyMixDataException,
        FileNotFoundException,
        ParseException
    {
        if (args.length == 2)
        {
            PlaylistReader read = new PlaylistReader(args[0], args[1]);
        }
        else
        {
            PlaylistReader read =
                new PlaylistReader("input.txt", "playlists.txt");
        }
    }
}
