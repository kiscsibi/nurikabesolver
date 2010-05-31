
public class TFloor extends TStructure {

    /**
     * constuctor
     */
    public TFloor() {
	super(-1);
    }

    /**
     * constructor
     * @param cell The first cell of Structure
     */
    public TFloor(TCell cell) {
	super(cell);
    }

    /***************************
     * OVERRIDES
     ***************************/
    
    @Override
    public void colorize(TCell c) {
	c.setBlack();
    }

    @Override
    public boolean isFull() {
	return false;
    }

    @Override
    public boolean isFloor() {
	return true;
    }

    @Override
    public boolean isWall() {
	return false;
    }
}
