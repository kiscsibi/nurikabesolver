
public class TWall extends TStructure {
    
	public TWall(TCell cell, int Limit) {
	    super(Limit);
	    addCell(cell);
	}

<<<<<<< local
	int Limit;
    
	 public int stillToPlace() {
		 return Limit - Cells.size();
	 }
=======
	@Override
	public void colorize(TCell c) {
	    c.setWhite();
	}
>>>>>>> other
	
<<<<<<< local
	public TWall(TCell cell, int limit) {
	    super();
	    addCell(cell);
	    Limit = limit;
    }

=======
	public boolean isFull() {
	    if(Limit == Cells.size()) {
		return true;
	    }
	    return false;
	}
>>>>>>> other
}
