
public class NurikabeEngine {

	TBoard Board;
    
    public NurikabeEngine(int height, int width) {
    	super();
    	Board = new TBoard(height, width);
    }

    public int getHeight() {
        return Board.getHeight();
    }

    public void setHeight(int height) {
        Board.setHeight(height);
    }

    public int getWidth() {
        return Board.getWidth();
    }

    public void setWidth(int width) {
        Board.setWidth(width);
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
    
}
