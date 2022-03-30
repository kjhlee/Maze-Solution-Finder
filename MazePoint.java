
public class MazePoint
{
    private char symbol;
    private char wall = 'X';
    private char empty = '-';
    private char path = '*';

    /**
     * Constructor for MazePoint objects.
     *
     * @param isWall A boolean variable that is true if this point is a wall,
     *  false if it is empty.
     */
    public MazePoint(boolean isWall)
    {
        if (isWall) {
            this.symbol = this.wall;
        }
        else {
            this.symbol = this.empty;
        }
    }

    /**
     * Checks if this point is a wall or not.
     *
     * Precondition: this object has been created and this.symbol has some value
     *
     * Takes no parameters.
     *
     * @return A boolean value that is true if this symbol is 'X', otherwise
     *  false.
     */
    public boolean isWall() {
        return this.symbol == this.wall;
    }

    /**
     * Checks if this point is empty or not.
     *
     * Precondition: this object has been created and this.symbol has some value
     *
     * Takes no parameters.
     *
     * @return A boolean value that is true if this symbol is '-', otherwise
     *  false.
     */
    public boolean isEmpty()
    {
        return this.symbol == this.empty;
    }

    /**
     * Checks if this point is in the escape path or not.
     *
     * Precondition: this object has been created and this.symbol has some value
     *
     * Takes no parameters.
     *
     * @return A boolean value that is true if this symbol is '*', otherwise
     *  false.
     */
    public boolean isPath()
    {
        return this.symbol == this.path;
    }

    /**
     * Sets this point to '*', indicating that it is in the escape path.
     *
     * Takes no parameters and returns nothing.
     */
    public void setToPath()
    {
        this.symbol = this.path;
    }

    /**
     * Sets this point to '-', indicating that it is empty.
     *
     * Takes no parameters and returns nothing.
     */
    public void setToEmpty()
    {
        this.symbol = this.empty;
    }

    /**
     * Sets this point to 'X', indicating that it is a wall.
     *
     * Takes no parameters and returns nothing.
     */
    public void setToWall()
    {
        this.symbol = this.wall;
    }

    /**
     * Compares this point with another point and checks if they have the same
     * symbol.
     *
     * Precondition: this object and another MazePoint object have been created
     *  and both have symbol set to some value.
     *
     * @param other Another MazePoint object to compare with this object.
     *
     * @return A boolean value that is true if this symbol of this object and
     *  the other object are the same, otherwise false.
     */
    public boolean symbolMatch(MazePoint other)
    {
        return other.symbol == this.symbol;
    }

    /**
     * Prints out the symbol representing this point to the console.
     *
     * Precondition: this object has been created and this.symbol has some value
     *
     * Takes no parameters and returns nothing.
     */
    public void printSymbol()
    {
        System.out.print(this.symbol);
    }
}
