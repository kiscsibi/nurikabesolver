import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class TBoard {

    int height;
    int width;

    //TODO think about Errors from All OuOFBounds
    private TCell[][] All;
    private Queue<TWall> Walls;
    private Queue<TFloor> Floors;
    private Queue<TCell> Grays;

    /**
     * constructor
     * @param height height of the board
     * @param width width of the board
     */
    public TBoard(int height, int width) {
	super();
	this.height = height;
	this.width = width;
	init();
    }

    /**
     * sets up the board
     */
    public void init() {
	All = new TCell[this.height][this.width];
	for(int i = 0; i < this.height; i++) {
	    for (int j = 0; j < this.width; j++) {
		All[i][j] = new TCell(i, j);
	    }
	}		
	for(int i = 0; i < this.height; i++) {
	    for (int j = 0; j < this.width; j++) {

		if(i > 0)
		    All[i][j].setUp(All[i-1][j]);
		if(i < this.height - 1 )
		    All[i][j].setDown(All[i+1][j]);
		if(j > 0)
		    All[i][j].setLeft(All[i][j-1]);
		if(j < this.width - 1 )
		    All[i][j].setRight(All[i][j+1]);
	    }
	}
	setWalls(new LinkedList<TWall>());
	Floors = new LinkedList<TFloor>();
	Grays = new LinkedList<TCell>();
    }

    /*****************************
     * setter and getter
     *****************************/

    /**
     * returns height of the board
     * @return height the height of the board
     */
    public int getHeight() {
	return height;
    }

    /**
     * returns width of the board
     * @return width the width of the board 
     */
    public int getWidth() {
	return width;
    }

    /**
     * sets the Walls
     * @param walls - Queue of walls to add
     */
    public void setWalls(Queue<TWall> walls) {
	Walls = walls;
    }

    /**
     * return Queue of walls
     * @return Walls Queue of walls
     */
    public Queue<TWall> getWalls() {
	return Walls;
    }

    /**
     * returns Queue with all Grays
     * @return Grays A Queue with Grays
     */
    public Queue<TCell> getGrays() {
	return Grays;
    }

    /**
     * returns Color of a cell in x,y position
     * @param x x of the cell
     * @param y y of the cell
     * @return Color of the cell
     */
    public int getColor(int x,int y) {
	return All[x][y].getColor();
    }

    /**
     * returns limit of a cell in x,y position
     * -1 if there is no Limit or cell has no Owner
     * @param x the x position of the cell
     * @param y the y position of the cell
     * @return limit of the cell
     */
    public int getLimit(int x, int y) {
	try {
	    int n = All[x][y].getLimit();
	    return n;
	} catch(Exception e) {
	    return -1;
	}
    }

    /**
     * Loads a Level from TLevelStruct 
     * @param level TLevelStruct of the level to load
     * @throws Exception if there is an error with loading
     */
    public void loadLevel(TLevelStruct level) throws Exception {
	//TODO check exception for array and remove
	width = level.width;
	height = level.height;
	init();

	while(!level.isEmpty()) {
	    getWalls().add(new TWall(All[level.getX()-1][level.getY()-1], level.getLimit()));
	    level.remove();
	}

	for(TCell[] row : All ) {
	    for(TCell c : row) {
		if(!c.hasLimit()) {
		    Grays.add(c);
		}
	    }
	}

    }

    /**
     * Sets a cell to white and adds it to wall if possible.
     * Also checks for surrounding wall pieces with no Owner yet and add calls this function on them
     * @param cell The cell to color
     */
    public void setWhite(TCell cell) {
	Set<TCell> whites = cell.getNBWhites();
	if(!whites.isEmpty()) {

	    for(TCell w : whites) {
		if(w.hasLimit()) {
		    w.Owner.addCell(cell);				
		}
	    }
	    //if an owner was found, check for no owners around
	    if(cell.Owner != null) {
		for(TCell w : whites) {
		    if(!w.hasLimit()) {
			setWhite(w);
		    }
		}
	    }			
	}
	else {
	    cell.setWhite();
	}
    }

    /**
     * Adds a cell to Floors. checks for floors with different owners around the cell in questions and combines them
     * Otherwise create a new Floor with the cell
     * @param cell The cell to color
     */
    public void setBlack(TCell cell) {
	Set<TCell> blacks = cell.getNBBlacks();
	if(!blacks.isEmpty()) {
	    for(TCell b : blacks) {
		if(cell.Owner == null) {
		    b.Owner.addCell(cell);				
		}
		else {
		    if(!cell.Owner.equals(b.Owner)) {
			cell.Owner.addAll(b.Owner);
			/*for(TCell c : b.Owner.Cells) {
			    setBlack(c);
			}*/
			Floors.remove(b.Owner);
		    }
		}
	    }
	}
	else if(cell.Owner == null) {
	    Floors.add(new TFloor(cell));
	}
    }

    public Queue<TCell> getAllCells() {
	Queue<TCell> cells = new LinkedList<TCell>();
	for(TCell[] row : All ) {
	    for(TCell c : row) {
		    cells.add(c);
	    }
	}
	return cells;
    }

}
