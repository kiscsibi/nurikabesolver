import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public abstract class TStructure {

    Queue<TCell> Cells;
    int Limit;

    //TODO is it intelligent to first iterate though everything 
    //and then iterate through the output once more?
    //we could do this in 1 step

    abstract public void colorize(TCell c);
    abstract public boolean isFull();
    abstract public boolean isWall();
    abstract public boolean isFloor();

    /**
     * constructor
     */
    public TStructure() {
	Cells = new LinkedList<TCell>();
	Limit = -1;
    }

    public TStructure(TCell cell) {
	Cells = new LinkedList<TCell>();
	colorize(cell);
	Cells.add(cell);
    }

    public TStructure(int limit) {
	Cells = new LinkedList<TCell>();
	Limit = limit;
    }
    
    /**
     * returns all gray Neighbors of the Structure
     * @return A Set with all gray Neighbors
     */
    public Set<TCell> getNBGrays() {
	Set<TCell> grays = new HashSet<TCell>();
	for(TCell c : Cells) {
	    grays.addAll((Collection<TCell>) c.getNBGrays());
	}
	return grays;
    }
    
    public void addCell(TCell c) {
	c.Owner = this;
	colorize(c);
	Cells.add(c);
    }	 

    public int getLimit() {
	return Limit;
    }

    public void setLimit(int limit) {
	Limit = limit;
    }

    public int getSize(){
	return Cells.size();
    }

    public void addAll(TStructure owner) {
	Cells.addAll((Collection<TCell>) owner.Cells);
    }
}
