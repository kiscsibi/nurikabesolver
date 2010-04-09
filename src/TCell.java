
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
	
	public boolean hasColor() {
		if (color != 2)
		return true;
		else return false;
	}

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
	
	public TPos[] getPossibilities() {
		//TODO implement
		TPos[] pos = null;
		return pos;
	}

}
