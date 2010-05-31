import java.util.LinkedList;
import java.util.Queue;


public class TLevelStruct {
    
    int width;
    int height;
    
    private class TWallInfo {
	public int x;
	public int y;
	public int size;
	
	public TWallInfo(int x, int y, int s) {
	    this.x = x;
	    this.y = y;
	    size = s;
	}
    }
    
    Queue<TWallInfo> wi;
    
    void addWallInfo(int x, int y, int size) throws Exception{
	if(x < 0 || x > width)
	    throw new Exception();
	if(y < 0 || y > height)
	    throw new Exception();
	wi.add(new TWallInfo(x, y, size));
    }
    
    public TLevelStruct(int w, int h) {
	width = w;
	height = h;
	wi = new LinkedList<TWallInfo>();
    }
    
    public int getX() {
	return wi.peek().x;
    }
    
    public int getY() {
	return wi.peek().y;
    }
    
    public int getLimit() {
	return wi.peek().size;
    }
    
    public boolean isEmpty() {
	return wi.isEmpty();
    }
    
    public void remove() throws Exception {
	   wi.remove();
    }
}	
