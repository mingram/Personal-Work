public class Function {

	private String sb = "";
	private ENode y;
	private double x;
	private int count = 0;

	// private static String[] func = { "sin","cos","tan", "asin", "acos",
	// "atan", "sqrt","abs", "ln", "e", "p" };

	public Function(String def) {
		qualify(def);
		// y = new ONode('+', new ONode('*', new CNode(2.0), new VNode('x')),
		// new CNode(1.0));
		y = expression();

	}

	private void qualify(String def) {

		def = def.toLowerCase();

		String chars = "0123456789epx";

		for (char c : chars.toCharArray()) {

			def = def.replace(c + "sin", c + "*sin");
			def = def.replace(c + "cos", c + "*cos");
			def = def.replace(c + "tan", c + "*tan");
			def = def.replace(c + "asin", c + "*asin");
			def = def.replace(c + "acos", c + "*acos");
			def = def.replace(c + "atan", c + "*atan");
			def = def.replace(c + "sqrt", c + "*sqrt");
			def = def.replace(c + "abs", c + "*abs");
			def = def.replace(c + "ln", c + "*ln");
			def = def.replace(c + "x", c + "*x");
			def = def.replace(c + "e", c + "*e");
			def = def.replace(c + "p", c + "*p");
			def = def.replace(")(", ")*(");

			sb = def;
		}
	}

	public double f(int which, double x) {
		this.x = x;
		return evaluate(y);

	}

	private double evaluate(ENode y) {

		switch (y.getType()) {

		case 'C':
			double val = ((Double) y.getValue());
			return val;

		case 'V':
			return x;

		case 'O':

			double left = evaluate(y.getLeft());
			double right = evaluate(y.getRight());
			switch (((Character) y.getValue()).charValue()) {

			case '+':
				return left + right;

			case '-':
				return left - right;

			case '*':
				return left * right;

			case '/':
				return left / right;

			case '^':
				return Math.pow(left, right);

			}

		case 'F':

			left = Math.toRadians(evaluate(y.getLeft()));

			switch (((Integer) y.getValue()).intValue()) {

			case 0:
				return Math.sin(left);

			case 1:
				return Math.cos(left);

			case 2:
				return Math.tan(left);

			case 3:
				return Math.asin(left);

			case 4:
				return Math.acos(left);

			case 5:
				return Math.atan(left);

			case 6:
				return Math.sqrt(left);

			case 7:
				return Math.log10(Math.toDegrees(left));

			case 8:
				return Math.log(Math.toDegrees(left));

			}

		}

		return 0;

	}

	public ENode expression() {

		ENode e = term();

		while (hasNext()
				&& (sb.charAt(count) == '+' || sb.charAt(count) == '-')) {
			e = new ONode(sb.charAt(count++), e, term());
		}

		return e;
	}

	private ENode term() {

		ENode e = factor();

		while (hasNext()
				&& (sb.charAt(count) == '*' || sb.charAt(count) == '/')) {
			e = new ONode(sb.charAt(count++), e, factor());
		}

		return e;
	}

	private ENode factor() {

		ENode e = base();

		while (hasNext() && sb.charAt(count) == '^') {
			e = new ONode(sb.charAt(count++), e, base());
		}

		return e;
	}

	private ENode base() {

		ENode e;
		
		if (sb.charAt(count) == 'e' || sb.charAt(count) == 'p' || Character.isDigit(sb.charAt(count))) {
			e = constant();
			count++;
		}

		else if (sb.charAt(count) == 'x') {
			e = variable();
			count++;
		}

		else if (sb.charAt(count) == '(' || sb.charAt(count) == ')') {
			count++;
			e = expression();
			count++;
		}
		
		else {
			e = function();
		}

		return e;
	}

	private ENode function() {

		ENode e;
	
	    if(sb.substring(count, count+3).equals("sin")){
	    	count+=4;
			e = new FNode(0,term(),null);
	    }

		else if (sb.substring(count, count+3).equals("cos")){
			count+=4;
			e = new FNode(1,term(),null);
		}
	
		else if (sb.substring(count,count+3).equals("tan")){
			count+=4;
			e = new FNode(2,term(),null);
		}
		
		else if (sb.substring(count, count+4).equals("asin")){
			count+=5;
			e = new FNode(3,term(),null);
		}

		else if (sb.substring(count, count+4).equals("acos")){
			count+=5;
			e = new FNode(4,term(),null);
		}

		else if (sb.substring(count, count+4).equals("atan")){
			count+=5;
			e = new FNode(5,term(),null);
		}

		else if (sb.substring(count, count+4).equals("sqrt")){
			count+=5;
			e = new FNode(6,term(),null);
		}

		else if (sb.substring(count, count+3).equals("log")){
			count+=4;
			e = new FNode(7,term(),null);
		}

		else {
			count+=3;
			e = new FNode(8,term(),null);
		}
		
		return e;
	}

	private ENode variable() {

		ENode e;
		e = new VNode('x');

		return e;

	}

	private ENode constant() {
		ENode e;

		if (sb.charAt(count) == 'e')
			e = new CNode(Math.E);

		else if (sb.charAt(count) == 'p')
			e = new CNode(Math.PI);

		else
			e = new CNode(Double.parseDouble(Character.toString(sb
					.charAt(count))));

		return e;
	}

	private boolean hasNext() {

		return count + 1 < sb.length();

	}

	private String traverse(ENode e) {

		if (e != null)
			return traverse(e.getLeft()) + "" + e.getType() + ""
					+ traverse(e.getRight());

		else
			return "";

	}

	public String reportStructure() {
		return traverse(y);
	}

	public String toString() {
		return sb;
	}

}
