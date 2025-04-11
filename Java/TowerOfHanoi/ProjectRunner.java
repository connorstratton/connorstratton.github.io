// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Runner class for towerofhanoi package
 * 
 * @author Connor Stratton
 * @version Oct 21, 2024
 */
public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Main method for runner class
     * 
     * @param args
     *            specifies number of disks if a single argument is passed
     */
    public static void main(String[] args)
    {
        int disks = 5;

        if (args.length == 1)
        {
            disks = Integer.parseInt(args[0]);
        }

        PuzzleWindow window = new PuzzleWindow(new HanoiSolver(disks));
    }
}
