
public class NurikabeEngine {

    private int height;
    private int width;
    
    private int color;
    
    public NurikabeEngine(int height, int width) {
	super();
	this.height = height;
	this.width = width;
	
	color = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * getColor returns the color of a certain block in x, y
     * @param x    the x position of the block 
     * @param y    the y position of the block
     * @return     colorvalue of the block
     */    
    //TODO throw out of bounds exception
     public int getColor(int x, int y) {
	if(color == 2)
	    color = 0;
	else
	    color++;
	return color;
    }
    
}
