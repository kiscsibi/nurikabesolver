import java.util.LinkedList;
import java.util.Queue;


public abstract class TStructure {
	Queue<TCell> Cells;
	
	
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
	 
	 public void addCell(TCell c) {
	     c.Owner = this;
	     Cells.add(c);
	 }
	 
	 public TStructure() {
	     Cells = new LinkedList<TCell>();
	 }
	 

}
