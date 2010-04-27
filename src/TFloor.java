
public class TFloor extends TStructure {

    public TFloor() {
	super(-1);
    }

    public TFloor(TCell cell) {
	super(cell);
    }

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
