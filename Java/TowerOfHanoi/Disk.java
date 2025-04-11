// Virginia Tech Honor Code Pledge:
// Project 3 Fall 2024
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Connor Stratton (connors75)

package towerofhanoi;

import student.TestableRandom;
import cs2.Shape;
import java.awt.Color;

// -------------------------------------------------------------------------
/**
 * Class representing a disk object
 * 
 * @author Connor Stratton
 * @version Oct 6, 2024
 */
public class Disk
    extends Shape
    implements Comparable<Disk>
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Constructor for disk object
     * 
     * @param width
     *            width of the disk
     */
    public Disk(int width)
    {
        super(0, 0, width, PuzzleWindow.DISK_HEIGHT);
        TestableRandom rand = new TestableRandom();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        setBackgroundColor(new Color(r, g, b));

    }

    // ~Public Methods ........................................................


    /**
     * checks if 2 disks have the same width
     * 
     * @param otherDisk
     *            Disk being compared
     * @return (this.getWidth() - otherDisk.getWidth()) positive if this is
     *             bigger, negative if otherDisk is bigger, 0 if the same
     */
    public int compareTo(Disk otherDisk)
    {
        if (otherDisk == null)
        {
            throw new IllegalArgumentException();
        }

        return (this.getWidth() - otherDisk.getWidth());
    }


    /**
     * checks if parameter is equal to current disk
     * 
     * @param other
     *            object being compared
     * @return true if object equal, false otherwise
     */
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }

        if (other == null)
        {
            return false;
        }

        if (this.getClass().equals(other.getClass()))
        {
            Disk otherDisk = (Disk)other;
            if (this.getWidth() == otherDisk.getWidth())
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Gets width as a string
     * 
     * @return str width of disk as a string
     */
    public String toString()
    {
        String str = "";
        str += this.getWidth();
        return str;
    }
}
