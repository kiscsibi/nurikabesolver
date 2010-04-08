import java.util.Queue;


public class TBoard {
	
	int height;
	int width;
	
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
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        //TODO by reshaping height/width, the whole board changes
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getColor(int x,int y) {
    	return All[x][y].getColor();
    }
}