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
    
    public boolean connectsAll(TCell c) {
	Set<TCell> path = new HashSet<TCell>();
	path.addAll((Collection<TCell>) Cells);
	path.add(c);
	int steps = Limit - path.size();
	
	for(TCell g: c.getNBGrays()) {
	    path.add(g);
	    if(connect(path, steps))
		return true;
	    path.remove(g);
	}
	
	return false;
    }
    
    public boolean connect(Set<TCell> path, int togo) {
	if(togo == 0) {
	    for(TCell c1: path) {
		for(TCell c2 : path) {
		    if(c1.Position.cityblock(c2.Position) > Limit) {
			return false;
		    }
		}
	    }
	    return true;
	}
	else {
	    Set<TCell> tmppath = new HashSet<TCell>(path);
	    for(TCell c1 : tmppath) {
		boolean con = false;
		for(TCell c2: c1.getNBGrays()) {
		    path.add(c2);
		    con = connect(path, togo-1);
		    if(con == true) {
			return true;
		    }
		    else {
			path.remove(c2);
		    }
		}
	    }
	}
	return false;
    }
    
    
    public boolean conn(TCell c) {
	Set<TCell> path = new HashSet<TCell>();
	path.add(c);
	boolean ret = conn(c, path, Limit); 
	path.remove(c);
	return ret;
    }
    
    public boolean conn(TCell c, Set<TCell> path, int togo) {
	//TODO or bigger than max cityblock
	if(togo < 0 || c == null)
	    return false;
	else if(c.isBlack())
	    return false;
	else if(togo == 0 && path.containsAll(Cells))
	    return true;
	else {
	    path.add(c);
	    if(conn(c.up, path, togo-1) || 
		    conn(c.down, path, togo-1) ||
		    conn(c.left, path, togo-1) ||
		    conn(c.right, path, togo-1) ) {
			return true;
	    }
	    path.remove(c);
	}
	return false;
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
