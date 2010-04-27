import java.util.HashSet;
import java.util.Set;

public class TCell {
	int color;
	TPos Position;
	TStructure Owner;

	//TODO cell information about neighbors
	//otherwise we have to link TStructure to TBoard 
	//to access neighbor fields
	TCell up;
	TCell down;
	TCell left;
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
	
	public TCell getUp() {
		return up;
	}

	public void setUp(TCell up) {
		this.up = up;
	}

	public TCell getDown() {
		return down;
	}

	public void setDown(TCell down) {
		this.down = down;
	}

	public TCell getLeft() {
		return left;
	}

	public void setLeft(TCell left) {
		this.left = left;
	}

	public TCell getRight() {
		return right;
	}

	public void setRight(TCell right) {
		this.right = right;
	}
	
	public int getColor() {
		return color;
	}	
	
	public void setColor(int color) {
		this.color = color;
	}

	public void setOwner(TStructure Owner) {
	    this.Owner = Owner;
	}

	
	/********************************
	 * extension functions
	 ********************************/
	
	public void setBlack() {
		color = 0;
	}	
	public void setWhite() {
		color = 1;
	}
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
		if( up.getColor() == color )
			ext.add(up);
		if( down.getColor() == color )
			ext.add(down);
		if( left.getColor() == color )
			ext.add(left);
		if ( right.getColor() == color )
			ext.add(right);
		return ext;	
	}
	
	public Set<TCell> getNBWhites() {
		return getNBColor(1);
	}
	public Set<TCell> getNBGrays() {
		return getNBColor(2);
	}
	public Set<TCell> getNBBlacks() {
		return getNBColor(0);
	}

	public int getLimit() {
	    if(Owner == null)
		return -1;
	    else return Owner.getLimit();
	    
	}	

	/**
	 * returns if cells owner has a limit
	 * @return boolean
	 */
	public boolean hasLimit() {
		if (Owner.getLimit() != -1) {
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
	public boolean isBlack() {
		return isColor(0);	
	}	
	public boolean isWhite() {
		return isColor(1);
	}
	public boolean isGray() {
		return isColor(2);
	}

}
