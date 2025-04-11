// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Class representing a tower object
 * 
 * @author Connor Stratton
 * @version Oct 8, 2024
 */
public class Tower
    extends LinkedStack<Disk>
{
    // ~ Fields ................................................................

    private Position position;

    // ~ Constructors ..........................................................

    /**
     * Constructor for a tower
     * 
     * @param position
     *            position of the tower
     */
    public Tower(Position position)
    {
        super();
        this.position = position;
    }

    // ~Public Methods ........................................................


    /**
     * Method getting the position of the tower
     * 
     * @return position position of the tower
     */
    public Position position()
    {
        return position;
    }


    /**
     * Adds disk to tower if move is allowed
     * 
     * @param disk
     *            disk being added to tower
     */
    @Override
    public void push(Disk disk)
    {
        if (disk == null)
        {
            throw new IllegalArgumentException();
        }
        if (isEmpty() || peek().compareTo(disk) > 0)
        {
            super.push(disk);
        }
        else
        {
            throw new IllegalStateException();
        }

    }
}
