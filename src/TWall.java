
public class TWall extends TStructure {

	int Size;
    
	public TWall(TCell cell, int size) {
	    super();
	    addCell(cell);
	    Size = size;
    }

	@Override
	public void colorize(TCell c) {
	    c.setColor(1);
	    
	}

}
