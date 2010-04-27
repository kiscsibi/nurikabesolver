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
     * does setup on the board
     */
    public void init() {
	All = new TCell[this.height][this.width];
	for(int i = 0; i < this.height; i++) {
	    for (int j = 0; j < this.width; j++) {
		All[i][j] = new TCell(i, j);
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

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public void setWalls(Queue<TWall> walls) {
	Walls = walls;
    }

    public Queue<TWall> getWalls() {
	return Walls;
    }

    public Queue<TCell> getGrays() {
	return Grays;
    }

    public int getColor(int x,int y) {
	return All[x][y].getColor();
    }

    public int getLimit(int w, int h) {
	try {
	    int n = All[w][h].getLimit();
	    return n;
	} catch(Exception e) {
	    return -1;
	}
    }


    public void loadLevel(TLevelStruct level) throws Exception {
	//TODO check exception for array and remove
	width = level.width;
	height = level.height;
	init();

	while(!level.isEmpty()) {
	    getWalls().add(new TWall(All[level.getX()-1][level.getY()-1], level.getSize()));
	    level.remove();
	}

    }




    public void checkFullWalls() {
	int walls = getWalls().size();

	for(int i = 0; i < walls; i++) {
	    TWall w = getWalls().poll();
	    if(w.isFull()) {

	    }
	}
    }

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
			cell.Owner.addCell(w);
		    }
		}
	    }			
	}
    }

    public void setBlack(TCell cell) {
	Set<TCell> blacks = cell.getNBWhites();
	if(!blacks.isEmpty()) {
	    for(TCell b : blacks) {
		if(cell.Owner != null) {
		    b.Owner.addCell(cell);				
		}
		else {
		    if(!cell.Owner.equals(b.Owner)) {
			cell.Owner.addAll(b.Owner);
			Floors.remove(b.Owner);
		    }
		}
	    }
	    cell.setBlack();
	}
	else {
	    Floors.add(new TFloor(cell));
	}
    }
}
