import java.util.LinkedList;
import java.util.Queue;


public class TBoard {
	
	int height;
	int width;
	
	//TODO think about Errors from All OuOFBounds
    private TCell[][] All;
    private Queue<TWall> Walls;
    private Queue<TFloor> Floor;
    private Queue<TCell> Grey;

    public TBoard(int height, int width) {
		super();
		this.height = height;
		this.width = width;
		init();
	}
	
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
    	Walls = new LinkedList<TWall>();
    	Floor = new LinkedList<TFloor>();
    	Grey = new LinkedList<TCell>();
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getColor(int x,int y) {
    	return All[x][y].getColor();
    }
    
    public void loadLevel(TLevelStruct level) throws Exception {
		//TODO check exception for array and remove
		width = level.width;
		height = level.height;
		init();
		
		while(!level.isEmpty()) {
		    Walls.add(new TWall(All[level.getX()-1][level.getY()-1], level.getSize()));
		    level.remove();
		}
	
    }

    public int getLimit(int w, int h) {
	//TODO what to do with the outofboundsexception?
	    int n = All[w][h].getLimit();
	return n;
    }
    
	// TODO move to TWall
    public void checkReachable() {
		//TODO we could save the max and min x and y values which are 
		//reachable to not have to iterate through all the cells to check
    	
		int greys = Grey.size();
		int walls = Walls.size();
		
		for(int j = 0; j < greys; j++) {
		    for(int i = 0; i < walls; i++) {
		    	Walls.poll();
		    }
		}
    }
    
    public void checkFullWalls() {
    	int walls = Walls.size();
	
		for(int i = 0; i < walls; i++) {
	    	TWall w = Walls.poll();
	    	if(w.isFull()) {
	    	    
	    	}
	    }
    }
}
