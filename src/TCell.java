import java.util.HashSet;
import java.util.Set;

public class TCell {
    
    /**
     * The color of the cell
     */
    int color;
    
    /**
     * The x,y Position of the cell
     */
    TPos Position;
    
    /**
     * the Owner of the cell
     */
    TStructure Owner;

    /**
     * The cell above
     */
    TCell up;
    /**
     * The cell below
     */
    TCell down;
    /**
     * The cell left
     */
    TCell left;
    /**
     * the cell right
     */
    TCell right;

    /**
     * Constructor
     * @param x x Position of Cell
     * @param y y Position of Cell
     */
    public TCell(int x, int y) {
	super();
	Position = new TPos(x,y);
	up = null;
	down = null;
	left = null;
	right = null;
	Owner = null;
	setGray();
    }


    /**************************************
     * GETTERS AND SETTERS
     **************************************/

    /**
     * returns the cell above the current cell
     * @return up the cell above
     */
    public TCell getUp() {
	return up;
    }

    /**
     * sets the cell above the current cell
     * @param up the cell above
     */
    public void setUp(TCell up) {
	this.up = up;
    }

    /**
     * returns the cell below the current cell
     * @return down the cell below
     */
    public TCell getDown() {
	return down;
    }

    /**
     * sets the cell below the current cell
     * @param up the cell below
     */
    public void setDown(TCell down) {
	this.down = down;
    }

    /**
     * returns the cell left of the current cell
     * @return left the cell left
     */
    public TCell getLeft() {
	return left;
    }

    /**
     * sets the cell left of the current cell
     * @param up the cell left
     */
    public void setLeft(TCell left) {
	this.left = left;
    }

    /**
     * returns the cell right of the current cell
     * @return right the cell right
     */
    public TCell getRight() {
	return right;
    }

    /**
     * sets the cell right of the current cell
     * @param up the cell right
     */
    public void setRight(TCell right) {
	this.right = right;
    }

    /**
     * returns the color of the cell
     * @return color The color of the cell
     */
    public int getColor() {
	return color;
    }	

    /**
     * sets the color of this cell to color
     * @param color the color the cell shall have
     */
    public void setColor(int color) {
	this.color = color;
    }

    /**
     * Sets the Owner of the cell
     * @param Owner The Owner of the cell
     */
    public void setOwner(TStructure Owner) {
	this.Owner = Owner;
    }


    /********************************
     * extension functions
     ********************************/

    /**
     * set cell to the color Black
     */
    public void setBlack() {
	color = 0;
    }	
    /**
     * set cell to the color White
     */
    public void setWhite() {
	color = 1;
    }
    /**
     * set cell to the color Gray
     */
    public void setGray() {
	color = 2;
    }


    /**
     * get the neighbors with matching color
     * @param color the color to match
     * @return all neighbors of that color
     */
    private Set<TCell> getNBColor(int color) {
	Set<TCell> ext = new HashSet<TCell>();
	if(up != null) {
	    if( up.getColor() == color )
		ext.add(up);
	}
	if(down != null) {
	    if( down.getColor() == color )
		ext.add(down);
	}
	if(left != null) {
	    if( left.getColor() == color )
		ext.add(left);
	}
	if(right != null) {
	    if ( right.getColor() == color )
		ext.add(right);
	}
	return ext;	
    }

    /**
     * get all white neighbors
     * @return Set with all white neighbors
     */
    public Set<TCell> getNBWhites() {
	return getNBColor(1);
    }

    /**
     * get all gray neighbors
     * @return Set with all gray neighbors
     */
    public Set<TCell> getNBGrays() {
	return getNBColor(2);
    }

    /**
     * get all black neighbors
     * @return Set with all black neighbors
     */
    public Set<TCell> getNBBlacks() {
	return getNBColor(0);
    }

    /**
     * returns the limit of the cell if it has one,
     * otheriwse return -1
     * @return limit The limit of the cell or -1
     */
    public int getLimit() {
	if (Owner == null)
	    return -1;
	else return Owner.getLimit();

    }	

    /**
     * returns if cells owner has a limit
     * @return boolean
     */
    public boolean hasLimit() {
	if (getLimit() != -1) {
	    return true;
	}
	else return false;
    } 

    /**
     * compares the color of the cell with color and returns if it is the same
     * @param c the color
     * @return boolean
     */
    public boolean isColor(int c) {
	if (c == color) {
	    return true;
	}
	else return false;
    }
    /**
     * returns true if cell is black
     * @return boolean
     */
    public boolean isBlack() {
	return isColor(0);	
    }	
    /**
     * returns true if cell is white
     * @return boolean
     */
    public boolean isWhite() {
	return isColor(1);
    }
    /**
     * returns true if cell is gray
     * @return boolean
     */
    public boolean isGray() {
	return isColor(2);
    }

}
