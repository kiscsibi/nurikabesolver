import java.util.Collection;
import java.util.HashSet;
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
	
	/**
	 * checks if a wall can reach a cell
	 * @param goal The cell to be reached
	 * @return boolean
	 */
	public boolean isReachable(TCell goal) {
	    Set<TCell> hist = new HashSet<TCell>();
	    return getReachables(Cells, hist, Limit-Cells.size()).contains(goal);
	}

	/**
	 * returns the reachable grey cells of the wall
	 * @param ext the cells to extend
	 * @param hist the already visited cells
	 * @param togo the amount of steps left
	 * @return all grey cells that are reachable
	 */
	private Set<TCell> getReachables(Collection<TCell> ext, Set<TCell> hist, int togo) {
	    
	    Set<TCell> next = new HashSet<TCell>();
	    
	    if(togo == 0) {
		return hist;
	    }
	    else {
		for(TCell c : ext) {
		    for(TCell g : c.getNBGrays()) {
			if(!ext.contains(g)) {
			    ext.addAll( (Collection<TCell>) c.getNBGrays());
			}
		    }
		}
	    }
	    hist.addAll(ext);	    
	    return getReachables(next, hist, togo-1);
	}

	/**
	 * wrapper for getReachables(ext, hist, togo)
	 * @return all grey cells that are reachable
	 */
	public Set<TCell> getReachables() {
	    Set<TCell> hist = new HashSet<TCell>();
	    return getReachables(Cells, hist, Limit-Cells.size());
	}
} 
