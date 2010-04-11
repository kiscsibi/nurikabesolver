
public class TWall extends TStructure {
    
	public TWall(TCell cell, int Limit) {
	    super(Limit);
	    addCell(cell);
    }

	@Override
	public void colorize(TCell c) {
	    c.setColor(1);
	    
	}

}
