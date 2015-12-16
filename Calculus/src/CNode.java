
public class CNode extends ENode {
	
	char type = 'C';


		public CNode(Double value) {
			super(value);
			super.setType(type);
		}

		public CNode(Double value, ENode left, ENode right) {
			super(value, left, right);
			super.setType(type);
		}

	}