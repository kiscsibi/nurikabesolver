import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NurikabeEngine {

    /**
     * The Board with the cells
     */
    TBoard Board;
    /**
     * File handler to load level files
     */
    TFileHandler FileHandler;
    /**
     * The list of Cells still to check
     */
    Queue<TCell> ProcessList;
    
    
    /**
     * Constructor for the engine. Generates an empty board with height width    
     * @param height
     * @param width
     */
    public NurikabeEngine(int height, int width) {
	super();
	Board = new TBoard(height, width);
    	FileHandler = new TFileHandler();
    	ProcessList = new LinkedList<TCell>();
    }	


    /**
     * sets up a new board from file
     * @param file the file of the new board
     * @throws Exception
     */
    public void newBoard(File file) throws Exception {
    	Board.loadLevel(FileHandler.readLevel(file));
    }    

    
    /******************************************
     *   Board Access for drawing the board
     ******************************************/    
    
    /**
     * returns the height of the board
     * @return height
     */
    public int getHeight() {
        return Board.getHeight();
    }

    /**
     * returns the width of the board
     * @return width
     */
    public int getWidth() {
        return Board.getWidth();
    }
    
    /**
     * getColor returns the color of a certain cell in x, y
     * @param x    the x position of the cell 
     * @param y    the y position of the cell
     * @return     color value of the cell
     */    
    //TODO throw out of bounds exception
     public int getColor(int x, int y) {
    	 return Board.getColor(x, y);
     }


     /**
      * returns the limit of a cell in x, y
      *	@param x    the x position of the cell 
      * @param y    the y position of the cell
      * @return     limit of the cell
      */
    public int getLimit(int x, int y) {
    	return Board.getLimit(x,y);
    }
        
    
    /*********************************
     *   Heuristics
     *********************************/
    
    /**
     * colors all the grays around the borders of a full wall
     * @param cell a white cell
     */
    public void hFull(TCell cell) {
	if(cell.hasLimit()) {
    		if (cell.Owner.isFull()) {
    		    Set<TCell> neighbors = cell.Owner.getNBGrays();
    		    for (TCell c : neighbors) {
    			c.setBlack();
    		    }   		
    		}
	}
    	
    }

    /**
     * colors cell black if it has two white neighbors from different walls
     * @param cell a gray cell
     */
    public void hFloorSplit(TCell cell){
    	Set<TCell> whites = cell.getNBWhites();
    	if(whites.size() >= 2) {
    		for(TCell c1 : whites) {
    			for(TCell c2 : whites) {
    				if(!c1.equals(c2) && !c1.Owner.equals(c2.Owner)) {
    					Board.setBlack(cell);
    				}
    			}
       		}
    	}
    }
    
    /**
     * colors the only possible extension of Owner to the color of cell
     * @param cell a colored cell
     */
    public void hOneExt(TCell cell) {
    	Set<TCell> grays = cell.Owner.getNBGrays(); 
    	if(grays.size() == 1) {
    	    for(TCell g : grays) {
    		if(cell.isWhite() && ! cell.Owner.isFull()) {
    		    Board.setWhite(g);
    		}
    		else if(cell.isBlack()){
    		    Board.setBlack(g);
    		}
    	    }
    	}
    }
    
    /**
     * Colors a Gray cell black if it is the gray one in an L form
     * @param cell a grey cell
     */
    public void hLForm(TCell cell) {
		
	
	if(!cell.isGray())
	    return;
	try {
        	if (    (cell.up.isBlack() && cell.left.isBlack() && cell.up.left.isBlack()) || 
        		(cell.up.isBlack() && cell.right.isBlack() && cell.up.right.isBlack()) || 
        		(cell.down.isBlack() && cell.left.isBlack() && cell.down.left.isBlack()) ||
        		(cell.down.isBlack() && cell.right.isBlack() && cell.down.right.isBlack()) )   {
        	    Board.setWhite(cell);
        	}
	} catch(Exception e) {
	    //BorderException do nothing, since no L form is found
	}
    }
    
    /**
     * colors all not reachable gray cells black
     */
    public void hNotReachable() {
	Set<TCell> reachables = new HashSet<TCell>();
	
	for(TWall w : Board.getWalls()) {
	    reachables.addAll(w.getReachables());
	}
	for(TCell g : Board.getGrays()) {
	    if(!reachables.contains(g)) {
		Board.setBlack(g);
	    }
	}
    }
    
    /**
     * function that solves the game
     */
    public void solve() {
	hNotReachable();
    }
}