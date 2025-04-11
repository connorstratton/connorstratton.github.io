// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

import java.util.Observable;

// -------------------------------------------------------------------------
/**
 * Class with methods to solve towers
 * 
 * @author Connor Stratton
 * @version Oct 11, 2024
 */
public class HanoiSolver
    extends Observable
{
    // ~ Fields ................................................................

    private Tower left;
    private Tower center;
    private Tower right;
    private int numDisks;

    // ~ Constructors ..........................................................

    /**
     * Constructor for solver class
     * 
     * @param numDisks
     *            number of disks on the towers
     */
    public HanoiSolver(int numDisks)
    {
        this.numDisks = numDisks;
        left = new Tower(Position.LEFT);
        center = new Tower(Position.CENTER);
        right = new Tower(Position.RIGHT);
    }

    // ~Public Methods ........................................................


    /**
     * method to get the current tower
     * 
     * @param pos
     *            tower's position being checked
     * @return left if position is left tower, right if right, center if center,
     *             with center being the default case
     */
    public Tower getTower(Position pos)
    {
        switch (pos)
        {
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case CENTER:
            case DEFAULT:
            default:
                return center;
        }
    }


    /**
     * moves disk from one tower to another
     * 
     * @param source
     *            tower being moved from
     * @param destination
     *            tower being moved to
     */
    private void move(Tower source, Tower destination)
    {
        destination.push(source.pop());
        setChanged();
        notifyObservers(destination.position());
    }


    /**
     * Puts disks on towers into string representation
     * 
     * @return str string of which disks are in each tower
     */
    public String toString()
    {
        String str = "";

        str += left.toString();
        str += center.toString();
        str += right.toString();

        return str;
    }


    /**
     * recursive method to solve towers
     * 
     * @param currentDisks
     *            how many disks are in the tower
     * @param startPole
     *            tower to move disk from
     * @param tempPole
     *            tower variable to store tower in
     * @param endPole
     *            tower to move disk to
     */
    private void solveTowers(
        int currentDisks,
        Tower startPole,
        Tower tempPole,
        Tower endPole)
    {
        if (currentDisks == 1)
        {
            move(startPole, endPole);
        }
        else
        {
            solveTowers(currentDisks - 1, startPole, endPole, tempPole);
            move(startPole, endPole);
            solveTowers(currentDisks - 1, tempPole, startPole, endPole);
        }

    }


    /**
     * method getting how many disks are in the towers
     * 
     * @return numDisks number of disks in the towers
     */
    public int disks()
    {
        return numDisks;
    }


    /**
     * method calling recursive method
     */
    public void solve()
    {
        solveTowers(numDisks, left, center, right);
    }
}
