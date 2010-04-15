
public class TWall extends TStructure {

	int Limit;
    
	 public int stillToPlace() {
		 return Limit - Cells.size();
	 }
	
	public TWall(TCell cell, int limit) {
	    super();
	    addCell(cell);
	    Limit = limit;
    }

}
