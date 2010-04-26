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
	
	
	public TCell(int x, int y) {
		super();
		Position = new TPos(x,y);
		up = null;
		down = null;
		left = null;
		right = null;
		color = 2;
		Owner = null;
	}
	
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
	
	public void setBlack() {
		color = 0;
	}
	
	public void setWhite() {
		color = 1;
	}
	
	public int getLimit() {
	    if(Owner == null)
		return -1;
	    else return Owner.getLimit();
	    
	}
	
	public boolean hasColor() {
		if (color != 2)
		return true;
		else return false;
	}

	// TODO Can be removed? Not useable for posExt() because neighbors can overlap
	public int amountFreeNeighbours() {
		int a = 0;
		
		if(up.hasColor())
			a++;
		if(down.hasColor())
			a++;
		if(left.hasColor())
			a++;
		if(right.hasColor())
			a++;
		
		return a;
	}
	
	public Set<TCell> getPosExtensions() {
		Set<TCell> ext = new HashSet<TCell>();
		if(! up.hasColor())
			ext.add(up);
		if(! down.hasColor())
			ext.add(down);
		if(! left.hasColor())
			ext.add(left);
		if (! right.hasColor())
			ext.add(right);
		return ext;
	}
	
	public boolean hasLimit(int w, int h) {
		if (Owner != null && Owner.getLimit() != -1) {
		    return true;
		}
		else return false;
	} 
	
	public boolean isBlack() {
		if(color == 0)
			return true;
		else return false;
	}
	
	public boolean isWhite() {
		if(color == 1)
			return true;
		else return false;
	}
	
	public boolean isGray() {
		if(color == 2)
			return true;
		else return false;
	}
}
