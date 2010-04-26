import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NurikabeEngine {

	TBoard Board;
	TFileHandler FileHandler;
    Queue<TCell> ProcessList;
    
    
	public NurikabeEngine(int height, int width) {
    	super();
    	Board = new TBoard(height, width);
    	FileHandler = new TFileHandler();
    	ProcessList = new LinkedList<TCell>();
    }

    public int getHeight() {
        return Board.getHeight();
    }

    public int getWidth() {
        return Board.getWidth();
    }
    
    /**
     * getColor returns the color of a certain block in x, y
     * @param x    the x position of the block 
     * @param y    the y position of the block
     * @return     colorvalue of the block
     */    
    //TODO throw out of bounds exception
     public int getColor(int x, int y) {
    	 return Board.getColor(x, y);
     }

    public void newBoard(File file) throws Exception {
    	Board.loadLevel(FileHandler.readLevel(file));
    }    

    public int getLimit(int w, int h) {
    	return Board.getLimit(w,h);
    }
    
/*    public boolean hasLimit(int w, int h) {
    	return Board.hasLimit(w,h);
    }
*/  

    /**
     * colors all the greys around the borders of a full wall
     */
    public void hFull(TCell cell) {
    	if (cell.Owner.isFull()) {
    		
    		Set<TCell> neighbors = cell.Owner.getNBGreys();
    		for (TCell c : neighbors) {
    			c.setBlack();
    		}   		
    	}
    	
    }

    /**
     * colors black if two different whites can reach the same grey
     * @param cell the cell to check
     */
    public void hFloorSplit(TCell cell){
    	Set<TCell> whites = cell.getNBWhites();
    	if(whites.size() >= 2) {
    		for(TCell c1 : whites) {
    			for(TCell c2 : whites) {
    				if(!c1.equals(c2) && !c1.Owner.equals(c2.Owner)) {
    					cell.setBlack();
    				}
    			}
       		}
    	}
    }
    
    /**
     * colors the next one if there is just one way
     * @param cell
     */
    public void hOneExt(TCell cell) {
    	Set<TCell> greys = cell.getNBGreys(); 
    	if(greys.size() == 1) {
    		if(cell.isWhite() && ! cell.Owner.isFull()){
    			for(TCell g : greys) {
    				g.setWhite();
    			}
    		}
    		else if(cell.isBlack()){
    			for(TCell g : greys) {
    				g.setWhite();
    			}
    		}
    	}
    }
    
	public void hLForm() {
		
	}
}
