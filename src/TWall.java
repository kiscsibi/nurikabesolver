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
     * very bad implementation because it loops around, 
     * we should fix this with a history
     * @param goal The cell to be reached
     * @return boolean
     */
    public boolean isReachable(TCell goal) {
	
	for (TCell c : Cells) {
	    if(isReachable(c, goal, stillToPlace()))
		    return true;
	}
	
	return false;
    }
    
    private boolean isReachable(TCell c, TCell goal, int togo) {
	if(togo < 0 || c == null)
	    return false;
	else if(c.equals(goal))
	    return true;
	else {
	    if(isReachable(c.up, goal, togo-1) || 
		    isReachable(c.down, goal, togo-1) ||
		    isReachable(c.left, goal, togo-1) ||
		    isReachable(c.right, goal, togo-1) ) {
			return true;
	    }
	}
	
	return false;
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
		    boolean otherWhite = false;
		    //check if gray one is next to whites of other walls
		    //if so, it is not reachable
		    for(TCell w : c.getNBWhites() ) {
			if (!Cells.contains(w)) {
			    otherWhite = true;
			}    
		    }

		    if (!otherWhite && conn(g)) {//&& connectsAll(g)) {
			next.add(g);
		    }
		}
	    }
	}

	return getReachables(next, hist, togo-1);
    }
    
    
    
    
    /**
     * wrapper for conn/3
     * @param c
     * @return
     */
    public boolean conn(TCell c) {
	Set<TCell> path = new HashSet<TCell>();
	boolean ret = conn(c, path, Limit); 
	return ret;
    }
    
    /**
     * checks if there is a path that connects all the ones from the wall with the one given.
     * @param c
     * @param path
     * @param togo
     * @return
     */
    public boolean conn(TCell c, Set<TCell> path, int togo) {
	//TODO or togo bigger than max cityblock
	if(togo < 0 || c == null || path.contains(c))
	    return false;
	else if(c.isBlack())
	    return false;
	else if(path.containsAll(Cells))
	    return true;
	else {
		
	    path.add(c);
	    //TCell c2 = c;
	    for(TCell c2 : path) {
	    	if(conn(c2.up, path, togo-1) || 
	    			conn(c2.down, path, togo-1) ||
	    			conn(c2.left, path, togo-1) ||
	    			conn(c2.right, path, togo-1) ) {
	    		return true;
	    	}
	    }
	    path.remove(c);
	}
	return false;
    }
    
    
    void conn2() {
    	
    	
    }
    
    /**
     * wrapper for getReachables(ext, hist, togo)
     * @return all grey cells that are reachable
     */
    public Set<TCell> getReachables() {
	//Set<TCell> s = fRwrapper();
	//return s;

	Set<TCell> hist = new HashSet<TCell>();
	return getReachables(Cells, hist, Limit-Cells.size());
    }

} 
