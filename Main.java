/**
 * [Main.java]
 * Main file that runs the maze solver
 * @author Jeffrey Xu
 * November 11th, 2021
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static Maze maze;
    private static Visualizer mazeVisualizer;
    private static boolean[][] visited, visual;
    
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);  //Create scanner
        System.out.println("Please enter the name of your file: ");
        maze = new Maze(input.nextLine());
        visited = new boolean[maze.getHeight()][maze.getWidth()];
        visual = new boolean[maze.getHeight()][maze.getWidth()];
        int[] startLoc = findStart();

        mazeVisualizer = new Visualizer(maze, visited, visual);
        
        if (findExit(startLoc[0], startLoc[1])) {
            System.out.println("The maze has been solved!");
        }
        else {
            System.out.println("There is no solutiion. :(");
        }

        input.close();  //Close scanner
    }

    /**
     * findStart
     * This method loops through the entire maze until it finds the 'S' symbol marking the start
     * @return int[], the coordinates of the start of the maze
     */
    public static int[] findStart() {
        int[] coordinates = new int[2];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                coordinates[0] = i;
                coordinates[1] = j;
                if (maze.getItem(i, j) == Const.START) {
                    return coordinates;
                }
            }
        }
        return coordinates;
    }

    /**
     * findExit
     * This methods accepts a pair of coordinates and recursively traverses through the maze.
     * The program ends when an exit/solution is found.
     * @param row The current row the maze solver is at
     * @param column The current column the maze solver is at
     * @throws InterruptedException, Required for use of Thread.sleep() method
     */
    private static boolean findExit(int row, int column) throws InterruptedException {
        //Base case: If exit found
        if (maze.getItem(row, column) == Const.END) {
            return true;
        }

        visited[row][column] = true;  //Marks current spot as visited

        //Update visualizer to show current path
        Thread.sleep(Const.UPDATE_DELAY);
        mazeVisualizer.repaint();

        //For loop to do a recursive call in all possible directions
        for (int i = 0; i < Const.NUM_DIRECTIONS; i++) {
            //Don't do if maze solver will go out of bounds or if there is a wall
            if (inBounds(row, column, i) && checkWall(row, column, i)) {
                //Don't visit if the path has already been explored
                if (!visited[row + Const.DIRECTION_ROW[i]][column + Const.DIRECTION_COL[i]]) {
                    if (!visual[row + Const.DIRECTION_ROW[i]][column + Const.DIRECTION_COL[i]]) {
                        //Recursively call self (Depth First Search)
                        if (findExit(row + Const.DIRECTION_ROW[i], column + Const.DIRECTION_COL[i])) {
                            return true;
                        }
                    }
                }
            }
        }

        //For displaying incorrect paths in visualizer
        visited[row][column] = false;
        visual[row][column] = true;
        Thread.sleep(Const.UPDATE_DELAY);
        mazeVisualizer.repaint();
    
        //Default return case
        return false;
    }

    /**
     * checkWall
     * This method checks if the given coordinate is a wall, returns whether or not there is a wall
     * @param row The current row
     * @param col The current column
     * @param direction The direction the we are checking
     * @return Boolean, true if there is no wall and false if there is
     */
    public static boolean checkWall(int row, int col, int direction) {
        if (maze.getItem(row + Const.DIRECTION_ROW[direction], col + Const.DIRECTION_COL[direction]) != Const.WALL) {
            return true;
        }
        return false;
    }

    /**
     * inBounds
     * This method checks if the maze checker is about to go out of bounds
     * @param row The current row
     * @param col The current column
     * @param direction The current direction the maze checker is headed
     * @return Boolean, true is the maze checker is in bounds and false if it is about to go out of the maze
     */
    public static boolean inBounds(int row, int col, int direction) {
        if (row + Const.DIRECTION_ROW[direction] < 0 || row + Const.DIRECTION_ROW[direction] >= maze.getHeight()) {
            return false;
        }
        else if (col + Const.DIRECTION_COL[direction] < 0 || col + Const.DIRECTION_COL[direction] >= maze.getWidth()) {
            return false;
        }
        return true;
    }
}