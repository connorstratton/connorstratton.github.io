package towerofhanoi;

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Name (pid)

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The main front-end work and the view for the Tower of Hanoi puzzle (Fall
 * 2024)
 *
 * @author Name
 * @version Date
 */
public class PuzzleWindow
    implements Observer
{

    private HanoiSolver game;
    private Shape left;
    private Shape center;
    private Shape right;
    private Window window;
    /**
     * A factor in which the width of the disks are multiplied by
     */
    public static final int WIDTH_FACTOR = 15;
    /**
     * The vertical gap between each disk on the tower
     */
    public static final int DISK_GAP = 0;
    /**
     * The height of each disk on the tower
     */
    public static final int DISK_HEIGHT = 15;

    /**
     * Creates a new PuzzleWindow view for a given HanoiSolver game
     *
     * @param g
     *            the game to create a view for
     */
    public PuzzleWindow(HanoiSolver g)
    {
        this.game = g;
        game.addObserver(this);

        window = new Window("Tower Of Hanoi");
        window.setSize(
            game.disks() * WIDTH_FACTOR + 700,
            game.disks() * DISK_HEIGHT + 400);

        // The height and Y location of each pole are the same
        int poleHeight = DISK_HEIGHT * (game.disks() + 2);
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape(
            (200 - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        center = new Shape(
            ((window.getGraphPanelWidth() / 2) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        right = new Shape(
            ((window.getGraphPanelWidth() - 200) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));

        for (int width =
            (game.disks() + 1) * WIDTH_FACTOR; width > WIDTH_FACTOR; width -=
                WIDTH_FACTOR)
        {

            game.getTower(Position.LEFT).push(new Disk(width));

            moveDisk(Position.LEFT);

            window.addShape(game.getTower(Position.LEFT).peek());

        }

        window.addShape(left);
        window.addShape(center);
        window.addShape(right);

        window.moveToBack(left);
        window.moveToBack(center);
        window.moveToBack(right);

        Button solve = new Button("Solve");

        window.addButton(solve, WindowSide.SOUTH);

        solve.onClick(this, "clickedSolve");

    }


    private void moveDisk(Position position)
    {
        Disk currentDisk = game.getTower(position).peek();

        Tower currentTower = game.getTower(position);

        Shape currentPole;

        switch (position)
        {
            case LEFT:
                currentPole = left;
                break;
            case RIGHT:
                currentPole = right;
                break;
            case CENTER:
            case DEFAULT:
            default:
                currentPole = center;
                break;
        }

        currentDisk.moveTo(
            currentPole.getX()
                - ((currentDisk.getWidth() / 2) - (currentPole.getWidth() / 2)),
            currentPole.getY() + (currentPole.getHeight()
                - (currentTower.size() * DISK_HEIGHT)));

    }


    /**
     * Updates the view whenever a disk is moved in the back-end
     *
     * @param o
     *            The observable that triggered the update
     * @param arg
     *            arguments sent by the game; should be a position
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if (arg.getClass() == Position.class)
        {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }


    /**
     * Runs when the Solve button is clicked, tells the puzzle to start solving
     *
     * @param button
     *            the button that was clicked
     */
    public void clickedSolve(Button button)
    {
        button.disable();
        new Thread() {
            public void run()
            {
                game.solve();
            }
        }.start();
    }


    private void sleep()
    {
        int sleepTime = 500 - (game.disks() * 50);

        if (sleepTime < 50)
        {
            sleepTime = 50;
        }

        try
        {
            Thread.sleep(sleepTime);
        }
        catch (Exception e)
        {
        }
    }
}
