import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class TWall extends TStructure {

	Queue<TCell> Reachables;
	
    /******************************
     * OVERRIDES
     ******************************/

    @Override
    public void colorize(TCell c) {
	c.setWhite();
    }

    @Override
    public boolean isFloor() {
	return false;
    }

    @Override
    public boolean isWall() {
	return true;
    }


    /**
     * constructor
     * @param cell the first cell of the wall
     * @param limit The limit of the wall
     */
    public TWall(TCell cell, int limit) {
	super(limit);
	addCell(cell);
	Limit = limit;
	Reachables = new LinkedList<TCell>();
	
    }

    /**
     * calculates the amount of still to place wall pieces
     * @return
     */
    public int stillToPlace() {
	return Limit - Cells.size();
    }


    /**
     * returns true if the Wall is full, false otherwise
     * @return boolean
     */
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

	hist.addAll(ext);

	if(togo == 0 || ext.isEmpty()) {
	    return hist;
	}

	Set<TCell> next = new HashSet<TCell>();

	for(TCell c : ext) {
	    for(TCell g : c.getNBGrays()) {
	    if(!hist.contains(g)) {
		    next.addAll( (Collection<TCell>) c.getNBGrays());
		}
	    }
	}
	    
	return getReachables(next, hist, togo-1);
    }
    

    /**
     * 
     * @return
     */
    public Set<TCell> fRwrapper() {
    	Set<TCell> path;
    	Set<TCell> Allpaths = new HashSet<TCell>();
    	Set<TCell> hist = new HashSet<TCell>();
    	
    	
    	
    	for(TCell c: Cells) {
    		hist.add(c);
    		path = filterReachables(c, hist, Limit-Cells.size());
    		if(path != null) {
    			Allpaths.addAll(path);
    		}
    		hist.remove(c);
    	}
    	
    	return Allpaths;
    }
    
    /**
     * 
     * @param r
     * @return
     */
    public Set<TCell> filterReachables(TCell c, Set<TCell> hist, int togo) {
    	
    	Set<TCell> allhist = new HashSet<TCell>();
    	Set<TCell> tmphist;
    	
    	if(togo == 0) {
    		if(hist.containsAll(Cells))
    			return hist;
    		else
    			return null;
    	}
    	else {
    		togo--;
    		hist.add(c);
    		
    		for(TCell c2 : c.getNBGrays()) {
    			tmphist = filterReachables(c2, hist, togo-1);
    			if(tmphist != null)
    				allhist.addAll(tmphist);
    		}

    		hist.remove(c);
    		return allhist;
    	}
    }
    
    /**
     * wrapper for getReachables(ext, hist, togo)
     * @return all grey cells that are reachable
     */
    public Set<TCell> getReachables() {
//    	Set<TCell> s = fRwrapper();
//    	return s;
    	
    	Set<TCell> hist = new HashSet<TCell>();
    	return getReachables(Cells, hist, Limit-Cells.size());
    }

} 
