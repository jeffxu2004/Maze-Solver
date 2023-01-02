/**
 * [Visualizer.java]
 * This class visually displays the traversal of the maze using Java graphics
 * @author Jeffrey Xu
 * November 12th, 2021
 */

import javax.swing.*;
import java.awt.*;

public class Visualizer extends JFrame {
    private Maze maze;
    private boolean[][] visited, visual;
    private MazePanel panel;
    private final int SCREEN_WIDTH = (int)getToolkit().getScreenSize().getWidth();
    private final int SCREEN_HEIGHT = (int)getToolkit().getScreenSize().getHeight();
    private final int GRIDSIZE, CORNER_X, CORNER_Y;

    /**
     * Visualizer constructor
     * @param maze Maze object that is going to be displayed visually
     * @param visited 2D array storing vistied spots
     * @param visual 2D array storing incorrect paths
     */
    Visualizer(Maze maze, boolean[][] visited, boolean[][] visual) {
        this.maze = maze;
        this.visited = visited;
        this.visual = visual;
        this.panel = new MazePanel();
        this.setTitle("Maze Solver Visualizer");

        //Set gridsize based on size of maze and center map on screen
        GRIDSIZE = (SCREEN_HEIGHT + Const.MAZE_SIZE_DECREASE)/Math.max(maze.getWidth(), maze.getHeight());
        CORNER_X = (int)(SCREEN_WIDTH/2 - (maze.getWidth()/2.0)*GRIDSIZE);
        CORNER_Y = (int)(SCREEN_HEIGHT/2 - (maze.getHeight()/2.0)*GRIDSIZE + Const.TITLEBAR_OFFSET);

        //Java graphics related method calls
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setAlwaysOnTop(true);  //Forces JFrame to top of screen
        this.setResizable(false);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
        this.setAlwaysOnTop(false);
    }

    /**
     * MazePanel class
     * Accepts Graphics object as parameter and draws the maze
     */
    private class MazePanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            //Fill background with light grey
            g.setColor(Const.BACKGROUND_COLOUR);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            //Represents each character/index of maze using a coloured block
            for (int i = 0; i < maze.getHeight(); i++) {
                for (int j = 0; j < maze.getWidth(); j++) {
                    //Temporary variables to store location of current block
                    int drawX = CORNER_X + GRIDSIZE*j;
                    int drawY = CORNER_Y + GRIDSIZE*i;

                    //Make start location red
                    if (maze.getItem(i, j) == Const.START) {
                        g.setColor(Const.START_COLOUR);
                    }
                    //Make end location green
                    else if (maze.getItem(i, j) == Const.END) {
                        g.setColor(Const.END_COLOUR);
                    }
                    //Make current path chartreuse
                    else if (visited[i][j]) {
                        g.setColor(Const.PATH_COLOUR);
                    }
                    //Make wrong paths violet
                    else if (visual[i][j]) {
                        g.setColor(Const.WRONG_PATH_COLOUR);
                    }
                    //Make walls black
                    else if (maze.getItem(i, j) == Const.WALL) {
                        g.setColor(Const.WALL_COLOUR);
                    }
                    //Set colour to grey if empty space
                    else {
                        g.setColor(Const.EMPTY_COLOUR);
                    }
                    g.fillRect(drawX, drawY, GRIDSIZE, GRIDSIZE);
                }
            }
        }
    }
}