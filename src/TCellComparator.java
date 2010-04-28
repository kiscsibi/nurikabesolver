import java.util.Comparator;


public class TCellComparator implements Comparator<TCell> {

    @Override
    public int compare(TCell o1, TCell o2) {
	if(o1.color == o2.color) {
	    return 0;
	}
	if (o1.isWhite()) {
	    return 1;
	}
	if (o2.isWhite() || o1.isGray()) {
	    return -1;
	}
	return 1;
    }

}
