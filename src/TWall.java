
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
	
	public boolean isReachable(TCell goal) {
		isReachable(goal, Cells, stillToPlace());
	}
	
	public boolean isReachable(TCell goal, Collection Col, int togo) {
		Set<TCell> pos = new Set<TCell>();
		TCell c;
		
		if(Col.contains(goal.Position))
			return true;
		if(togo == 0 )
			return false;
		
		
		for(i = 0; i < Col.size(); i++){
				c = Col.pop();
				pos.addAll(c.getPosExtensions());
		}
		isReachable(goal, pos, togo-1);	
	}
}
