
public class TFloor extends TStructure {

    public TFloor(int limit) {
    	super(limit);
    }

    @Override
    public void colorize(TCell c) {
    	c.setBlack();
    }

}
