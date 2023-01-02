/**
 * [Maze.java]
 * This class reads the text file and stores it as a list of strings
 * @author Jeffrey Xu
 * November 11th, 2021
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze {
    private ArrayList<String> maze;
    private int width;
    private int height;

    /**
     * Maze constructor
     * @param fileName Name of the file to read
     * @throws FileNotFoundException Required exception for reading files without try-catch
     */
    public Maze(String fileName) throws FileNotFoundException {
        File myFile = new File(fileName);
        Scanner read = new Scanner(myFile);

        maze = new ArrayList<String>();

        //Maze height and read file
        this.height = 0;
        while (read.hasNextLine()) {
            maze.add(read.nextLine());
            this.height++;
        }
        read.close(); //Close scanner
        
        //Maze width
        this.width = maze.get(0).length();
    }

    /**
     * getWidth
     * @return Int, returns the width of the maze
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getHeight
     * @return Int, returns the height of the maze
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getItem
     * @param row The row in the maze
     * @param column The column in the maze
     * @return Char, the character at the given position
     */
    public char getItem(int row, int column) {
        return maze.get(row).charAt(column);
    }
}