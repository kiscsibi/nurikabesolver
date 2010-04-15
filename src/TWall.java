
public class TWall extends TStructure {
    
	 public int stillToPlace() {
		 return Limit - Cells.size();
	 }

	 @Override
	public void colorize(TCell c) {
	    c.setWhite();
	}

	 public TWall(TCell cell, int limit) {
	    super(limit);
	    addCell(cell);
	    Limit = limit;
    }

	public boolean isFull() {
	    if(Limit == Cells.size()) {
		return true;
	    }
	    return false;
	}
}
