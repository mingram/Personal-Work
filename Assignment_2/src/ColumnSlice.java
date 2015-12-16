
public class ColumnSlice extends Slice {
	
	private Tableau _t;
	private int _colNum;
	
	public ColumnSlice(Tableau t, int colNum){
		_t = t;
		_colNum = colNum;
	}
	
	final public int size(){
		return _t.size();
	}
	
	final public int getValue(int i){
		return _t.getValue(i, _colNum);
	}

}
