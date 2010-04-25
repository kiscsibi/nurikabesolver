import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public abstract class TStructure {
	
    Queue<TCell> Cells;
    int Limit;
	
	//TODO is it intelligent to first iterate though everything 
	//and then iterate through the output once more?
	//we could do this in 1 step
	
//	 TPos[] getPossibleExt() {
//		 //TODO size of possibilities, maybe vector?
//		 TPos[] Possibilities = null;
//		 for(int i = 0; i < Cells.length() ; i++ ) {
//			 if(Cells[i].getColor() == 2) {
//				 Possibilities = Cells[i].getPossibilities();
//			 }
//		 }
//		 return Possibilities;
//	 }
//	 
//	 int countPossibleExt() {
//		 int c = 0;
//		 for(int i = 0; i < Cells.length; i++) {
//			 c += Cells[i].amountFreeNeighbours(); 
//		 }
//		 return c;
//	 }
	  
	public Set<TPos> getPosExtensions(TStructure struct) {
		TCell c;
		Set<TPos> posarr = new HashSet<TPos>();
		
		for(int j = 0; j  < getSize(); j++) {
			c = Cells.poll();
			posarr.addAll(c.getPosExtensions());
			Cells.add(c);
		}
		return posarr;
	}
	    
	 public void addCell(TCell c) {
	     c.Owner = this;
	     colorize(c);
	     Cells.add(c);
	 }
	 
	 abstract public void colorize(TCell c);
	 
	 public TStructure(int limit) {
	    Cells = new LinkedList<TCell>();
	    Limit = limit;
	 }

	 public int getLimit() {
	    return Limit;
	}

	public void setLimit(int limit) {
	    Limit = limit;
	}
	
	public int getSize(){
		return Cells.size();
	}
	
	public abstract boolean isFull();
}
