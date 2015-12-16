public class Tableau {

	private int _subsize;
	private int _size;

	private int[][] _tableau;

	public int size() {
		return _size;
	}

	public int subsize() {
		return _subsize;
	}

	public int getValue(int r, int c) {
		return _tableau[r][c];
	}
	
	public void setValue(int r, int c, int val){
		_tableau[r][c] = val;
	}
	
	private Tableau(int n) {
		_size = n;
		_subsize = (int) isqrt(n);
		_tableau = new int[n][n];
	}

	public Tableau(String[] rows) {
		this(rows.length); // call the private empty-tableau constructor
		for (int i = 0; i < size(); i++)
			fillRow(rows[i], 0, _tableau[i]);
	}

	public Tableau(int n, String initial) {
		this(n); // Call the private emtpy-tableau constructor.

		int ix = 0;

		for (int rix = 0; rix < n; rix++) {
			// Each iteration advances the starting position in the string
			ix = fillRow(initial, ix, _tableau[rix]);
		}
	}

	private static int isqrt(int n) {

		int i = 0;

		while (i * i < n)
			i++;

		return i;
	}

	public void print() {

		for (int i = 0; i < _size; i++) {

			if (i > 0 && i % _subsize == 0) {
				
				for (int k = 0; k < _subsize; k++) {
				
					for(int j = 0; j<_subsize*2+1; j++)
						System.out.print("-");
			
					if(k!=2)
						System.out.print("+");
					
				}
				
				System.out.println();
			}

			for (int j = 0; j < _size; j++) {

				if (j > 0 && j % _subsize == 0)
					System.out.print(" |");

				if (_tableau[i][j] == 0)
					System.out.print(" .");

				else System.out.print(" "+_tableau[i][j]);
			}
			
			System.out.println();
		}
	}

	private static int fillRow(String s, int ix, int[] row) {
		int rix = 0; // row index.

		for (; ix < s.length() && rix < row.length; ix++) {
			char c = s.charAt(ix);

			if (!Character.isDigit(c))
				continue;

			row[rix++] = Character.digit(c, 10);

		}

		for (; rix < row.length; rix++)
			row[rix] = 0;

		return ix;
	}

}
