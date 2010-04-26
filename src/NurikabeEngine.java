import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

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
    
    public void solve() {
    	
    }
    
<<<<<<< local
    public void solve() {
	
    }
=======
>>>>>>> other
    
<<<<<<< local
    public void hFull(TCell cell){
    	if (cell.Owner.isFull()){
    		Set<TCell> neighbors = cell.Owner.greyNBs();
    		for (TCell c : neighbors){
    			c.setBlack();
    		}   		
    	}
    	
    }
=======
>>>>>>> other
    
<<<<<<< local
    public void hFloorSplit(TCell cell){
    	Set<TCell> exts = cell.getPosExtensions();
    	for (TCell c : exts){
    		if(c.amountWall() >= 2){
    			c.setBlack();
    		}
    	}
    }
    
    public void hOneExt(TCell cell){
    	if(cell.amountGrey() == 1){
    		if(cell.isWhite() && ! cell.Owner.isFull){
    			Set<TCell> exts = cell.getPosExtensions();
    			exts.pop().setWhite();
    		}
    		else if(cell.isBlack()){
    			Set<TCell> exts = cell.getPosExtensions();
    			exts.pop().setBlack();
    		}
    	}
    	
    	if(cell.)
    		
    }
    
=======
>>>>>>> other
}
