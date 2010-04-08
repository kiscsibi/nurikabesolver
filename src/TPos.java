
public class TPos {

	int x;
	int y;
	
    public TPos(int x2, int y2) {
		x = x2;
		y = y2;
	}

	public int cityblock(TPos p) {
    	return Math.abs(x - p.x) + Math.abs(y + p.y);
    }

}
