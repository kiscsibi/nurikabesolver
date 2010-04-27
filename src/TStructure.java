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

    /**
     * constructor
     * @param cell The first cell of the Structure
     */
    public TStructure(TCell cell) {
	Cells = new LinkedList<TCell>();
	colorize(cell);
	Cells.add(cell);
    }

    /**
     * constructor with limit
     * @param limit the limit of the structure
     */
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
    
    /**
     * adds cell to structure
     * @param cell the cell to add
     */
    public void addCell(TCell cell) {
	cell.Owner = this;
	colorize(cell);
	Cells.add(cell);
    }	 

    /**
     * return the limit of structure
     * @return limit
     */
    public int getLimit() {
	return Limit;
    }

    /**
     * sets the limit of the structure
     * @param limit The limit
     */
    public void setLimit(int limit) {
	Limit = limit;
    }

    /**
     * returns the size of the structure (how many cells belong to the structure atm)
     * @return size The size
     */
    public int getSize(){
	return Cells.size();
    }

    /**
     * adds all cells from another structure
     * @param struct The structure with the cells to add
     */
    public void addAll(TStructure struct) {
	Cells.addAll((Collection<TCell>) struct.Cells);
    }
}
