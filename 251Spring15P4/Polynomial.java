import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Polynomial {
	List<ExpressionAtom> infixExpression = new ArrayList<ExpressionAtom>();
	
	List<ExpressionAtom> finalExpression;

	ListRepresentation listRepresentation;

	private String identifyUnaryMinuses(String expression) {
		if(expression.startsWith("-")) {
			expression = "%" + expression.substring(1); 
		}
		
		char[] expressionAtoms = expression.toCharArray();
		String returnExpression = String.valueOf(expressionAtoms[0]);
		for(int i = 1; i < expressionAtoms.length; ++i) {
			if(expressionAtoms[i] == '-' && expressionAtoms[i-1] == '(')
				returnExpression += "%";
			else
				returnExpression += String.valueOf(expressionAtoms[i]);
		}
		
		return returnExpression;
	}
	
	private String insertMultiplicationSigns(String expression) {
		char[] expressionAtoms = expression.toCharArray();
		String returnExpression = String.valueOf(expressionAtoms[0]);
		
		for(int i = 1; i < expressionAtoms.length; ++i) {
			if(!isOperator(expressionAtoms[i]) && !Character.isDigit(expressionAtoms[i]) 
					&& Character.isDigit(expressionAtoms[i-1]))
				returnExpression += "*";
			returnExpression +=  String.valueOf(expressionAtoms[i]);
		}
		
		return returnExpression;
	}
	
	private boolean isOperator(char token) {
		return token == '+' || token == '-' || token == '*' || token == '^'
				|| token == '(' || token == ')';
	}
	
	private List<ExpressionAtom> parseInputPolynomial(String inputExpression) {
		inputExpression = identifyUnaryMinuses(inputExpression);
		inputExpression = insertMultiplicationSigns(inputExpression);
		
		List<ExpressionAtom> inputExpressionTokens = new ArrayList<ExpressionAtom>();
		
		char[] inputChars = inputExpression.toCharArray();
		for(int i = 0; i < inputChars.length; ++i) {
			if(isOperator(inputChars[i]) || inputChars[i] == '%') {
				inputExpressionTokens.add(new ExpressionAtom(String.valueOf(inputChars[i]), 
						AtomType.OPERATOR, 1));
			} else {
				int lastIndex = inputExpressionTokens.size() - 1;
				if(lastIndex >= 0 && inputExpressionTokens.get(lastIndex).getAtomType() 
						== AtomType.OPERAND) {
					ExpressionAtom lastElement = inputExpressionTokens.remove(lastIndex);
					if(Character.isDigit(inputChars[i])) {
						lastElement.setCoefficient(lastElement.getCoefficient() * 10 + 
								Character.getNumericValue(inputChars[i]));
					} else {
						lastElement.setVariablesOrOperator(lastElement.getVariablesOrOperator() + 
								String.valueOf(inputChars[i]));
					}
					inputExpressionTokens.add(lastElement);
				} else if(Character.isDigit(inputChars[i])) {
					inputExpressionTokens.add(new ExpressionAtom("", AtomType.OPERAND, 
							Character.getNumericValue(inputChars[i])));
				} else {
					inputExpressionTokens.add(new ExpressionAtom(String.valueOf(
							inputChars[i]), AtomType.OPERAND, 1));
				}
			}
		}
		
		return inputExpressionTokens;
	}

	private int precedence(ExpressionAtom token) {
		if(token.getVariablesOrOperator().equals("+") || token.getVariablesOrOperator().equals("-") ) {
			return 1;
		} else if (token.getVariablesOrOperator().equals("*") || token.getVariablesOrOperator().equals("/") ) {
			return 2;
		} else if (token.getVariablesOrOperator().equals("^") ) {
			return 3;
		} else {
			return 0;
		}
	}
	
	private ListRepresentation convertToListRepresentation() {
		/*
		 * TODO: Write code here to operate on this.infixExpression and obtain a ListRepresentation
		 * object that contains a list representation of the the original expression given
		 * 
		 * NOTE THAT WE WILL BE PRINTING THE ListRepresentation object immediately after
		 * this function is called, so make sure it is in the right form.
		 */
		ListRepresentation prefix = new ListRepresentation();
		List<ListRepresentation> op = new ArrayList<ListRepresentation>();
		Stack<ExpressionAtom> trans = new Stack<ExpressionAtom>();

		ExpressionAtom token = new ExpressionAtom("#", AtomType.OPERATOR, 0);
		trans.push(token);

		// while (!trans.empty()) {
		// 	System.out.println("--------"+trans.peek().getNodeVal().getVariablesOrOperator()+"--------");
		// 	prefix = trans.pop();
		// 	// prefix.operands = operands;
		// }
		// System.out.println("--------"+trans.peek().getNodeVal().getVariablesOrOperator()+"--------");
		Utils.printExpression(this.infixExpression);
		for(int i = this.infixExpression.size(); i > 0; i--) {
			prefix = new ListRepresentation();
			// token = new ExpressionAtom();

			token = this.infixExpression.get(i-1);
			System.out.println(token.getAtomType() == AtomType.OPERAND);
			if(token.getAtomType() == AtomType.OPERAND) {

				prefix.setNodeVal(token);

				if(token.getCoefficient() < 0) {
					prefix.setNegative(true);
				} else {
					prefix.setNegative(false);
				}

				op.add(prefix);
				// prefix.operands = null;
				// Utils.printListRepresentation(prefix);
				// System.out.println("\ni == " + i +" "+ operands.size());
				for(int j = 0 ; j < op.size(); j++) {
					System.out.println(op.get(j).getNodeVal().getVariablesOrOperator() + "-----" + j);
				}



				// prefix.operands = operands;
			} else {
				for(int j = 0 ; j < op.size(); j++) {
					System.out.println(op.get(j).getNodeVal().getVariablesOrOperator() + "-----" + j);
				}
				if (token.getVariablesOrOperator().equals(")") ) {
					prefix.setNodeVal(token);

					// trans.push(prefix);
					trans.push(prefix.getNodeVal());

				} else if (token.getVariablesOrOperator().equals("(") ){
					while (!trans.peek().getVariablesOrOperator().equals(")") ) {
						prefix.setNodeVal(trans.pop() );
						// prefix.operands = operands;
						prefix.operands = op;
						op = new ArrayList<ListRepresentation>();
						op.add(prefix); 
						// op.clear();
						// op.add(prefix);
						// Utils.printListRepresentation(prefix);
						// System.out.println();
						// operands.clear();
						// operands.add(prefix);
					}
					trans.pop();
				} else {
					// System.out.println("-----+---"+trans.peek().getVariablesOrOperator()+"--------");
					prefix.setNodeVal(token);
					// prefix.operands = operands;
					// operands.clear();

					// while (!trans.empty()) {
					// 	System.out.println("--------"+trans.peek().getNodeVal().getVariablesOrOperator()+"--------");
					// 	prefix = trans.pop();
					// 	// prefix.operands = operands;
					// }

					// System.out.println("--------"+token.getVariablesOrOperator()+"--------");
					// System.out.println("--------"+trans.peek().getVariablesOrOperator()+"--------");
					// System.out.println("--------"+precedence(token)+"\t"+precedence(trans.peek() )+"--------");
					if (precedence(token) >= precedence(trans.peek() ) ) {
						// if (!trans.peek().getVariablesOrOperator().equals("#")) {
						// 	prefix.operands = op;
						// 	op = new ArrayList<ListRepresentation>();
						// 	op.add(prefix);
						// }
						trans.push(prefix.getNodeVal());
						// Utils.printListRepresentation(prefix);
					} else {
						while (precedence(token) <= precedence(trans.peek() ) ) {
							prefix.setNodeVal(trans.pop());
							// prefix.operands = operands;
							// System.out.println("asdadsasdasdasd");

							// Utils.printListRepresentation(prefix);
							// operands.clear();
							// operands.add(prefix);
						}
						prefix.setNodeVal(token);
						// prefix.operands = operands;
						// operands.clear();
						// Utils.printListRepresentation(prefix);
						// System.out.println("\n++++++++++++++++");
						trans.push(prefix.getNodeVal());
						// trans.push(prefix);
					}
				}
			}
		}
		// System.out.println("stack is empty: " + trans.empty());
		// System.out.println("--------"+trans.peek().getNodeVal().getVariablesOrOperator()+"--------");
		while (trans.peek().getVariablesOrOperator() != "#") {
			prefix = new ListRepresentation();
			
			System.out.println("--------"+trans.peek().getVariablesOrOperator()+"--------");
			prefix.setNodeVal(trans.pop());

			prefix.operands = op;
			// op = new ArrayList<ListRepresentation>();
			// op.add(prefix);
			// for(int j = 0 ; j < prefix.operands.size(); j++) {
			// 	System.out.println(prefix.operands.get(j).getNodeVal().getVariablesOrOperator() + "-----" + j);
			// }

		}

		// System.out.println("---------------------");
		Utils.printListRepresentation(prefix);
		System.out.println();
		System.out.println("--------------------- return ---------------------");
		return prefix;
	}
			
	private List<ExpressionAtom> evaluateExpression() {
		/*
		 * TODO: Write code here to operate on this.listRepresentation and obtain a List of
		 * ExpressionAtom objects which represent the atoms of the final expression. Note that you
		 * are allowed to have repeated variables here. This is not printed anywhere in the code, so
		 * you are free to return the ExpressionAtom objects in whatever form you wish.
		 */
		return this.infixExpression;
	}

	private List<ExpressionAtom> simplifyAndNormalize(List<ExpressionAtom> evaluatedExpression) {
		/*
		 * TODO: Write code here to operate on the List<ExpressionAtom> object obtaind from
		 * 'evaluateExpression'. Specifically, ensure that you combine terms with same variables and
		 * modify coefficients appropriately.
		 */
		return this.infixExpression;
	}
	
	public Polynomial(String inputPolynomial) {
		this.infixExpression = parseInputPolynomial(inputPolynomial);
		
		this.listRepresentation = convertToListRepresentation();
	}
	
	private String sortString(String termVars) {
		char[] ar = termVars.toCharArray();
		Arrays.sort(ar);
		return String.valueOf(ar);
	}

	public void evaluate() {
		List<ExpressionAtom> evaluatedExpression = evaluateExpression();
		
		for(int i = 0; i < evaluatedExpression.size(); ++i) {
			evaluatedExpression.get(i).setVariablesOrOperator((sortString(
					evaluatedExpression.get(i).getVariablesOrOperator())));
		}
		
		this.finalExpression = simplifyAndNormalize(evaluatedExpression);
	}
}