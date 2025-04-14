// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Class representing the genre breakdown of a song
 * 
 * @author Connor Stratton
 * @version Oct 28, 2024
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    // ~ Fields ................................................................

    private int pop;
    private int rock;
    private int country;

    // ~ Constructors ..........................................................

    /**
     * Constructor for the GenreSet class
     * 
     * @param pop
     *            amount of pop in the song
     * @param rock
     *            amount of rock in the song
     * @param country
     *            amount of country in the song
     */
    public GenreSet(int pop, int rock, int country)
    {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }

    // ~Public Methods ........................................................


    /**
     * Gets pop value
     * 
     * @return pop amount of pop in the song
     */
    public int getPop()
    {
        return pop;
    }


    /**
     * Gets rock value
     * 
     * @return rock amount of rock in the song
     */
    public int getRock()
    {
        return rock;
    }


    /**
     * Gets country value
     * 
     * @return country amount of country in the song
     */
    public int getCountry()
    {
        return country;
    }


    /**
     * Checks if given GenreSet is less than or equal to this GenreSet
     * 
     * @param other
     *            GenreSet to compare to this one
     * @return true if all genre value less than or equal to, false otherwise
     */
    private boolean isLessThanOrEqualTo(GenreSet other)
    {
        return (this.getPop() <= other.getPop()
            && this.getRock() <= other.getRock()
            && this.getCountry() <= other.getCountry());
    }


    /**
     * Checks if the GenreSet is between or equal to the max and min set
     * 
     * @param minGenreSet
     *            Set with lower bound to be checked against
     * @param maxGenreSet
     *            Set with upper bound to be checked against
     * @return true If equal or between bounds, false otherwise
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {

        return (minGenreSet.isLessThanOrEqualTo(this)
            && this.isLessThanOrEqualTo(maxGenreSet));
    }


    /**
     * Checks if 2 GenreSets are equal
     * 
     * @param obj
     *            object being checked if equal
     * @return true if pop, rock, and country values all equal, false otherwise
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
            GenreSet other = (GenreSet)obj;
            if (this.getPop() == other.getPop()
                && this.getRock() == other.getRock()
                && this.getCountry() == other.getCountry())
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Compares sums of values of this GenreSet to another
     * 
     * @param other
     *            GenreSet being compared to
     * @return sumThis - sumOther Difference of the sums of values of the 2 sets
     */
    @Override
    public int compareTo(GenreSet other)
    {
        int sumThis = this.getPop() + this.getRock() + this.getCountry();
        int sumOther = other.getPop() + other.getRock() + other.getCountry();
        return sumThis - sumOther;
    }


    /**
     * Puts values into string representation
     * 
     * @return String string representation of values
     */
    public String toString()
    {
        return "Pop:" + this.getPop() + " Rock:" + this.getRock() + " Country:"
            + this.getCountry();
    }

}
