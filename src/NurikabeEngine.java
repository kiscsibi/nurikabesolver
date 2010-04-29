import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class NurikabeEngine {

	/**
	 * The Board with the cells
	 */
	TBoard Board;
	/**
	 * File handler to load level files
	 */
	TFileHandler FileHandler;
	/**
	 * The list of Cells still to check
	 */
	PriorityQueue<TCell> ProcessList;


	/**
	 * Constructor for the engine. Generates an empty board with height width    
	 * @param height
	 * @param width
	 */
	public NurikabeEngine(int height, int width) {
		super();
		Board = new TBoard(height, width);
		FileHandler = new TFileHandler();
		ProcessList = new PriorityQueue<TCell>(height*width, new TCellComparator());
	}	


	/**
	 * sets up a new board from file
	 * @param file the file of the new board
	 * @throws Exception
	 */
	public void newBoard(File file) throws Exception {
		Board.loadLevel(FileHandler.readLevel(file));
	}    


	/******************************************
	 *   Board Access for drawing the board
	 ******************************************/    

	/**
	 * returns the height of the board
	 * @return height
	 */
	public int getHeight() {
		return Board.getHeight();
	}

	/**
	 * returns the width of the board
	 * @return width
	 */
	public int getWidth() {
		return Board.getWidth();
	}

	/**
	 * getColor returns the color of a certain cell in x, y
	 * @param x    the x position of the cell 
	 * @param y    the y position of the cell
	 * @return     color value of the cell
	 */    
	//TODO throw out of bounds exception
	public int getColor(int x, int y) {
		return Board.getColor(x, y);
	}


	/**
	 * returns the limit of a cell in x, y
	 *	@param x    the x position of the cell 
	 * @param y    the y position of the cell
	 * @return     limit of the cell
	 */
	public int getLimit(int x, int y) {
		return Board.getLimit(x,y);
	}


	/*********************************
	 *   Heuristics
	 *********************************/

	/**
	 * colors all the grays around the borders of a full wall
	 * @param cell a white cell
	 */
	public void hFull(TCell cell) {
		if(cell.hasLimit()) {
			if (cell.Owner.isFull()) {
				Set<TCell> neighbors = cell.Owner.getNBGrays();
				for (TCell c : neighbors) {
					Board.setBlack(c);
				}
				ProcessList.remove(cell.Owner.Cells);
			}
		}

	}

	/**
	 * colors cell black if it has two white neighbors from different walls
	 * @param cell a gray cell
	 */
	public void hFloorSplit(TCell cell){
		Set<TCell> whites = cell.getNBWhites();
		if(whites.size() >= 2) {
			for(TCell c1 : whites) {
				for(TCell c2 : whites) {
					if(c1.Owner != null && c2.Owner != null) {
						if(!c1.equals(c2) && !c1.Owner.equals(c2.Owner)) {
							Board.setBlack(cell);
							ProcessList.add(cell);
						}
					}
				}
			}
		}
	}

	/**
	 * colors the only possible extension of Owner to the color of cell
	 * @param cell a colored cell
	 */
	public void hOneExt(TCell cell) {
		Set<TCell> grays = null;
		if (cell.Owner != null) {
			grays = cell.Owner.getNBGrays();
		}
		else {
			grays = cell.getNBGrays();
		}

		if(grays.size() == 1) {
			TCell rem = null;
			for(TCell g : grays) {
				if(cell.isWhite()) {
					if (cell.Owner == null) {
						Board.setWhite(g);
						rem = g;
					}
					else if (!cell.Owner.isFull()) {
						Board.setWhite(g);
						rem = g;			
					}
				}
				else if(cell.isBlack()) {
					Board.setBlack(g);
					rem = g;		    
				}
			}
			Board.getGrays().remove(rem);
		}
	}

	/**
	 * Colors a Gray cell black if it is the gray one in an L form
	 * @param cell a grey cell
	 */
	public void hLForm(TCell cell) {

		boolean set = false;

		if(!cell.isGray()) {
			return;
		}
		if(cell.up != null) {
			if(cell.left != null) {
				if((cell.up.isBlack() && cell.left.isBlack() && cell.up.left.isBlack())) {
					set = true;
				}
			}
			if(cell.right != null) {
				if((cell.up.isBlack() && cell.right.isBlack() && cell.up.right.isBlack())) {
					set = true;
				}
			}
		}
		if(cell.down != null) {
			if(cell.left != null) {
				if((cell.down.isBlack() && cell.left.isBlack() && cell.down.left.isBlack())) {
					set = true;
				}
			}
			if(cell.right != null) {
				if((cell.down.isBlack() && cell.right.isBlack() && cell.down.right.isBlack())) {
					set = true;
				}
			}
		}
		if ( set )   {
			Board.setWhite(cell);
			Board.getGrays().remove(cell);
			ProcessList.offer(cell);
		}
	}

	/**
	 * colors all not reachable gray cells black
	 */
	public void hNotReachable() {
		Set<TCell> reachables = new HashSet<TCell>();
		Queue<TCell> delgrays = new LinkedList<TCell>();

		for(TWall w : Board.getWalls()) {
			reachables.addAll(w.getReachables());
		}
		for(TCell g : Board.getGrays()) {
			if(!reachables.contains(g)) {
				Board.setBlack(g);
				delgrays.add(g);
			}
		}
		Board.getGrays().removeAll(delgrays);
	}

	/**
	 * function that solves the game
	 */
	public void solve() {

		TCell c = null;

		while(!Board.getGrays().isEmpty()) {

			ProcessList.addAll((Collection<TCell>) Board.getAllCells());
			hNotReachable();

			while(!ProcessList.isEmpty()) {

				c = ProcessList.remove();

				if(c.isBlack()) {
					Board.setBlack(c);
					hOneExt(c);
				}
				else if(c.isWhite()) {
					hOneExt(c);
					hFull(c);
				}
				else {
					hLForm(c);
					hFloorSplit(c);
				}
			}
		}

		return;
	}
}