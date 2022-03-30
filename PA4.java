///////////////////////////////////////////////////////////////////////////////
// Title:              PA4.java
// Files:              MazePoint.java, input1, input2, input3, input4, input5
//
// Author:             KJ Lee

import java.io.IOException;
import java.util.Scanner;
import java.io.File;


//PA4 Class has the main components of the PA4 that reads mazes, solves them, prints them
//and what not
public class PA4 {

    // This method reads a maze in through the file given
    public MazePoint[][] readMaze(String fileToRead) throws IOException
    {

        MazePoint[][] newMaze;
        File fileByteStream = null;     // File input Stream
        Scanner inFs = null;            // Scanner object
        String car = "";

        //Try to open file
        fileByteStream = new File(fileToRead);
        inFs = new Scanner(fileByteStream);


        //Reads in sizes
        int size1 = inFs.nextInt();
        int size2 = inFs.nextInt();
        
        //Creates Maze with size1 and size 2

        newMaze = new MazePoint[size1][size2];


        while(inFs.hasNext()){
            for(int i = 0; i < size1; i++)
            {
                for(int j = 0; j < size2; j++)
                {
                    car = inFs.next();
                    if (car.equals("X")){
                        newMaze[i][j] = new MazePoint(true);
                    } 
                    else if (car.equals("-")){
                        newMaze[i][j] = new MazePoint(false);
                    }
                }
            }
        }
        inFs.close();
        return newMaze;

    }

    //Escape from maze creates a solution for the maze while only going down and to the right
    public void escapeFromMaze(MazePoint [][] maze){
        // error statement
        if (maze.length == 0)
        {
            System.out.println("Maze is Null");
            return;
        }

        for(int i = 0; i < maze.length; i++){
            for(int x = 0; x < maze[i].length; x++)
            {
                if(i != maze.length - 1 && x != maze[i].length - 1){
                    
                    if(maze[i][x].isEmpty())
                    {
                        if(maze[i][x+1].isWall())
                        {
                            maze[i][x].setToPath();
                            x -= 1;
                            i++;
                        } else {
                            maze[i][x].setToPath();
                        }
                    }
                } 
                else if (maze[i][x].isEmpty()) {
                    maze[i][x].setToPath();
                }
                }
            }
            // create a null and error return statement             
    }

    /**
    * Print the maze as a 2D grid.  You should call this method from
    * testRead and testEscape, as well as from main.
    *
    * Precondition: The maze is not null.
    * Postcondition: The maze has been printed and is unmodified.
    *
    * @param maze The maze to be printed.
    */
    //Prints the maze using a nested for loop
    private void printMaze(MazePoint[][] maze)
    {
        for(int i = 0; i < maze.length; i++)
        {
            for(int x = 0; x < maze[i].length; x++)
            {
                maze[i][x].printSymbol();
            }
            System.out.println();
        }
    }

    /**
    * Compare two maze arrays.  Return true if they have .  You should call
    * this method from testRead and testEscape.
    *
    * Precondition: The mazes are not null and have the same size.
    * Postcondition: The mazes are not modified.
    *
    * @param maze1 the first maze to compare.
    * @param maze2 the second maze to compare.
    * @return true if the mazes contain all the same symbols and false otherwise
    */
    //Maze match gives a boolean value by using the symbolmatch method from MazePoint.java and returns whether the mazes are similar
    private boolean mazeMatch(MazePoint[][] maze1, MazePoint[][] maze2)
    {
        // TODO: Implement this method
        for(int i = 0; i < maze1.length; i++)
        {
            for(int j = 0; j < maze1[i].length; j++)
            {
                if(maze1[i][j].symbolMatch(maze2[i][j]) == false)
                {
                    return false;
                }
            }
        }
        return true;
    }

    //testRead is a tester to check if the expected maze and the maze file given match
    public boolean testRead(String fileToRead, MazePoint[][] expected) throws
                            IOException
    {
        System.out.println("Expected Maze Solution: ");
        printMaze(expected);
        if(mazeMatch(readMaze(fileToRead), expected))
        {
            return true;
        } else {
            return false;
        }
    }

    //testEscape is a testing method that checks the maze solution and the maze and make sure they match
    public boolean testEscape(MazePoint[][] maze,
                              MazePoint[][] expectedSolution)
    {
        System.out.println("Expected maze solution: ");
        escapeFromMaze(maze);
        printMaze(maze);
        if(mazeMatch(maze, expectedSolution)){
            return true;
        }
        return false;
    }

    /**
    * Run unit tests.  You will add to this method.
    * Prints a message indicating whether all tests passed or failed.
    * The method will abort on the first failed test.
    * @return true if all unit tests pass.  false if at least one test fails.
    */
    public boolean unitTests() throws IOException {
        //Manually create the expected maze to match file input1
        MazePoint[][] expected = new MazePoint[3][3];
        expected[0][0] = new MazePoint(false);
        expected[0][1] = new MazePoint(false);
        expected[0][2] = new MazePoint(true);
        expected[1][0] = new MazePoint(true);
        expected[1][1] = new MazePoint(false);
        expected[1][2] = new MazePoint(false);
        expected[2][0] = new MazePoint(true);
        expected[2][1] = new MazePoint(true);
        expected[2][2] = new MazePoint(false);
        
        
        if (!this.testRead("input1", expected)) {
            readMaze("input1");
            System.out.println("Read test 1 failed.");
            return false;
        }
        else {
          System.out.println("Read test 1 passed!");
        }

        // At this point readMaze is working, so we can use it.
        MazePoint[][] maze1 = this.readMaze("input1");

        // Modify the expected maze from above to have the path
        expected[0][0].setToPath();
        expected[0][1].setToPath();
        expected[1][1].setToPath();
        expected[1][2].setToPath();
        expected[2][2].setToPath();

        if (!this.testEscape(maze1, expected)) {
            System.out.println("Escape test 1 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 1 passed!");
        }

        //test 2
        MazePoint[][] expected2 = new MazePoint[4][8];
        expected2[0][0] = new MazePoint(false);
        expected2[0][1] = new MazePoint(true);
        expected2[0][2] = new MazePoint(true);
        expected2[0][3] = new MazePoint(false);
        expected2[0][4] = new MazePoint(true);
        expected2[0][5] = new MazePoint(true);
        expected2[0][6] = new MazePoint(true);
        expected2[0][7] = new MazePoint(true);
        expected2[1][0] = new MazePoint(false);
        expected2[1][1] = new MazePoint(false);
        expected2[1][2] = new MazePoint(false);
        expected2[1][3] = new MazePoint(false);
        expected2[1][4] = new MazePoint(true);
        expected2[1][5] = new MazePoint(false);
        expected2[1][6] = new MazePoint(true);
        expected2[1][7] = new MazePoint(true);
        expected2[1][7] = new MazePoint(false);
        expected2[2][0] = new MazePoint(true);
        expected2[2][1] = new MazePoint(true);
        expected2[2][2] = new MazePoint(true);
        expected2[2][3] = new MazePoint(false);
        expected2[2][4] = new MazePoint(true);
        expected2[2][5] = new MazePoint(true);
        expected2[2][6] = new MazePoint(true);
        expected2[2][7] = new MazePoint(false);
        expected2[3][0] = new MazePoint(false);
        expected2[3][1] = new MazePoint(true);
        expected2[3][2] = new MazePoint(false);
        expected2[3][3] = new MazePoint(false);
        expected2[3][4] = new MazePoint(false);
        expected2[3][5] = new MazePoint(false);
        expected2[3][6] = new MazePoint(false);
        expected2[3][7] = new MazePoint(false);
        if (!this.testRead("input2", expected2)) {
            readMaze("input2");
            System.out.println("Read test 2 failed.");
            return false;
        }
        else {
          System.out.println("Read test 2 passed!");
        }
         MazePoint[][] maze2 = this.readMaze("input2");
         expected2[0][0].setToPath();
         expected2[1][0].setToPath();
         expected2[1][1].setToPath();
         expected2[1][2].setToPath();
         expected2[1][3].setToPath();
         expected2[2][3].setToPath();
         expected2[3][3].setToPath();
         expected2[3][4].setToPath();
         expected2[3][5].setToPath();
         expected2[3][6].setToPath();
         expected2[3][7].setToPath();
         if (!this.testEscape(maze2, expected2)) {
            System.out.println("Escape test 2 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 2 passed!");
        }

        //my own test
        //test 3
        MazePoint[][] expected3 = new MazePoint[5][3];
        expected3[0][0] = new MazePoint(false);
        expected3[0][1] = new MazePoint(true);
        expected3[0][2] = new MazePoint(false);
        expected3[1][0] = new MazePoint(false);
        expected3[1][1] = new MazePoint(true);
        expected3[1][2] = new MazePoint(true);
        expected3[2][0] = new MazePoint(false);
        expected3[2][1] = new MazePoint(false);
        expected3[2][2] = new MazePoint(false);
        expected3[3][0] = new MazePoint(true);
        expected3[3][1] = new MazePoint(true);
        expected3[3][2] = new MazePoint(false);
        expected3[4][0] = new MazePoint(true);
        expected3[4][1] = new MazePoint(true);
        expected3[4][2] = new MazePoint(false);
        if (!this.testRead("input3", expected3)) {
            readMaze("input3");
            System.out.println("Read test 3 failed.");
            return false;
        }
        else {
          System.out.println("Read test 3 passed!");
        }
        MazePoint[][] maze3 = this.readMaze("input3");
        expected3[0][0].setToPath();
        expected3[1][0].setToPath();
        expected3[2][0].setToPath();
        expected3[2][1].setToPath();
        expected3[2][2].setToPath();
        expected3[3][2].setToPath();
        expected3[4][2].setToPath();
        if (!this.testEscape(maze3, expected3)) {
            System.out.println("Escape test 3 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 3 passed!");
        }

        //test 4
        MazePoint[][] expected4 = new MazePoint[2][2];
        expected4[0][0] = new MazePoint(false);
        expected4[0][1] = new MazePoint(true);
        expected4[1][0] = new MazePoint(false);
        expected4[1][1] = new MazePoint(false);
        if (!this.testRead("input4", expected4)) {
            readMaze("input4");
            System.out.println("Read test 4 failed.");
            return false;
        }
        else {
          System.out.println("Read test 4 passed!");
        }
        MazePoint[][] maze4 = this.readMaze("input4");
        expected4[0][0].setToPath();
        expected4[1][0].setToPath();
        expected4[1][1].setToPath();
        if (!this.testEscape(maze4, expected4)) {
            System.out.println("Escape test 4 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 4 passed!");
        }
        // error case 
        MazePoint[][] expected5 = new MazePoint[0][0];
        if (!this.testRead("input5", expected5)) {
            readMaze("input5");
            System.out.println("Read test 5 failed.");
            return false;
        }
        else {
          System.out.println("Read test 5 passed!");
        }

        MazePoint[][] maze5 = this.readMaze("input5");
        if (!this.testEscape(maze5, expected5)) {
            System.out.println("Escape test 5 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 5 passed!");
        }


        return true;
    }


    //Runs the PA4 classes and checks a multitude of differnet mazes and their solutions
    public static void main(String[] args) throws IOException
    {
        PA4 solver = new PA4();

        // Perform unitTest first
        if(solver.unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
            return;
        }

        //creates a loop and asks for a maze file to solve
        PA4 loop = new PA4();
        Scanner scan = new Scanner(System.in);
        String str = "";
        System.out.println("Enter a file: ");
        str = scan.nextLine();
        while(!str.equals("end"))
        {
            System.out.println("Maze: ");
            MazePoint[][] newMaze = loop.readMaze(str);
            loop.printMaze(newMaze);

            System.out.println("Solution obtained from maze: ");
            loop.escapeFromMaze(newMaze);
            loop.printMaze(newMaze);

            System.out.println("Enter a file: ");
            str = scan.nextLine();
        }

    }
}
