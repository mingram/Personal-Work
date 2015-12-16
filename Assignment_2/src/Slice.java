
public abstract class Slice {

	abstract public int size();
	abstract public int getValue(int i);
	
	

	class RowSlice extends Slice {
		
		private Tableau _t;
		private int _rowNum;
		
		public RowSlice(Tableau t, int rowNum) {
			_t = t;
			_rowNum = rowNum;
		}
		
		final public int size(){ 
			return _t.size();
		}
		
		final public int getValue(int i){
			return _t.getValue(_rowNum,i);
		}
		
		
		
	}

}
