/**
 * [Const.java]
 * This class file stores all constant symbols/variables used in other classes
 * @author Jeffrey Xu
 * November 11th, 2021
 */

import java.awt.Color;

public final class Const {
    //Symbols
    public static final char START = 'S';
    public static final char END = 'E';
    public static final char WALL = '#';
    public static final char EMPTY = ' ';
    
    //For checking all 4 directions (left, up, right, down) in solve method
    public static final byte NUM_DIRECTIONS = 4;
    public static final byte[] DIRECTION_ROW = {0, -1, 0, 1};
    public static final byte[] DIRECTION_COL = {-1, 0, 1, 0};

    //Graphics related constants
    public static final byte UPDATE_DELAY = 10;
    public static final Color WALL_COLOUR = new Color(0, 0, 0);
    public static final Color EMPTY_COLOUR = new Color(137, 137, 137);
    public static final Color BACKGROUND_COLOUR = new Color(181, 181, 181);
    public static final Color START_COLOUR = new Color(0, 250, 15);
    public static final Color END_COLOUR = new Color(250, 0, 15);
    public static final Color PATH_COLOUR = new Color(200, 245, 5);
    public static final Color WRONG_PATH_COLOUR = new Color(165, 90, 227);
    public static final int MAZE_SIZE_DECREASE = -140;  //Make size of maze smaller than screen
    public static final int TITLEBAR_OFFSET = -20;  //Shift maze up to accomodate for size of toolbar
    
    //Class should not be constructed
    private Const() {}
}