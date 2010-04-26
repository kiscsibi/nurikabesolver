import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


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
		return isReachable(goal, Cells, stillToPlace());
	}
	
	public boolean isReachable(TCell goal, Collection Col, int togo) {
		Set<TCell> pos = new HashSet<TCell>();
		
		if(Col.contains(goal.Position))
			return true;
		if(togo == 0 )
			return false;
		
		for(TCell c : Col) {
				pos.addAll(c.getPosExtensions());
		}
		isReachable(goal, pos, togo-1);	
	}
}