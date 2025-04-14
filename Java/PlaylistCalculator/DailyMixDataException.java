// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Exception class
 * 
 * @author Connor Stratton
 * @version Nov 6, 2024
 */
public class DailyMixDataException
    extends RuntimeException
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Constructor for exception
     * 
     * @param string
     *            message to be outputted
     */
    public DailyMixDataException(String string)
    {
        super(string);
    }

    // ~Public Methods ........................................................

}
