import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import java.util.Collections;

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
			return 2;
		} else if (token.getVariablesOrOperator().equals("*") || token.getVariablesOrOperator().equals("/") ) {
			return 4;
		} else if (token.getVariablesOrOperator().equals("^") ) {
			return 6;
		} else {
			return 1;
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

		ExpressionAtom token = new ExpressionAtom("#", AtomType.OPERATOR, 1);
		trans.push(token);

		Utils.printExpression(this.infixExpression);
		for(int i = this.infixExpression.size(); i > 0; i--) {
			prefix = new ListRepresentation();
			// token = new ExpressionAtom();

			token = this.infixExpression.get(i-1);
			// System.out.println(token.getAtomType() == AtomType.OPERAND);
			if(token.getAtomType() == AtomType.OPERAND) {

				prefix.setNodeVal(token);

				// if(token.getCoefficient() < 0) {
				// 	prefix.setNegative(true);
				// } else {
				// 	prefix.setNegative(false);
				// }

				op.add(prefix);
				// prefix.operands = null;
				// Utils.printListRepresentation(prefix);
				// System.out.println("\ni == " + i +" "+ operands.size());
				// for(int j = 0 ; j < op.size(); j++) {
				// 	System.out.println(op.get(j).getNodeVal().getVariablesOrOperator() + "-----" + j);
				// }



				// prefix.operands = operands;
			} else {
				// for(int j = 0 ; j < op.size(); j++) {
				// 	System.out.println(op.get(j).getNodeVal().getVariablesOrOperator() + "-----" + j);
				// }
				// Utils.printListRepresentation(prefix);
				// System.out.println("Stack size " + trans.size() + " token is " + token.getVariablesOrOperator());
				// System.out.println("OP size " + op.size());

				if (token.getVariablesOrOperator().equals(")") ) {
					prefix.setNodeVal(token);
					trans.push(prefix.getNodeVal());
				} else if (token.getVariablesOrOperator().equals("(") ){
					while (!trans.peek().getVariablesOrOperator().equals(")") && trans.size() > 0) {
						prefix = new ListRepresentation();
						// System.out.println("---same precedence-----"+trans.peek().getVariablesOrOperator()+"--------");

						if (trans.size() > 1) {
							List<ListRepresentation> temp = new ArrayList<ListRepresentation>(); 
							prefix.setNodeVal(trans.pop());

							Collections.reverse(op);

							temp.add(op.remove(0));
							temp.add(op.remove(0));

							Collections.reverse(op);
							
							prefix.operands = temp;
							Utils.printListRepresentation(prefix);
							System.out.println("");

							if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
								prefix.getNodeVal().setVariablesOrOperator("+");
								// prefix.setNegative(true);
								if (prefix.operands.size() > 0) {
									// ListRepresentation setN = new ListRepresentation();
									// setN = prefix();
									if(prefix.operands.get(1).operands.size() > 0) {
										prefix.operands.get(1).setNegative(true);
									}else {
										prefix.operands.get(1).getNodeVal().setCoefficient(prefix.operands.get(1).getNodeVal().getCoefficient() * -1);	
									}

								} else {
									prefix.setNegative(true);
								}
							}

							
							// op = new ArrayList<ListRepresentation>();
							op.add(prefix);
						} else {
							Collections.reverse(op);
							prefix.operands = op;
							op.add(prefix);
						}
					}
					trans.pop();
				} else {
					prefix.setNodeVal(token);
					if (precedence(token) >= precedence(trans.peek() ) ) {
						trans.push(prefix.getNodeVal());
					} else {
						while (precedence(token) < precedence(trans.peek() ) ) {
							prefix = new ListRepresentation();
							// System.out.println("---same precedence-----"+trans.peek().getVariablesOrOperator()+"--------");
							// System.out.println(token.getVariablesOrOperator());
							if (trans.size() > 1) {
								List<ListRepresentation> temp = new ArrayList<ListRepresentation>(); 
								prefix.setNodeVal(trans.pop());
								Collections.reverse(op);

								temp.add(op.remove(0));
								temp.add(op.remove(0));

								Collections.reverse(op);
								prefix.operands = temp;

								if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
									prefix.getNodeVal().setVariablesOrOperator("+");
									// prefix.setNegative(true);
									if (prefix.operands.size() > 0) {
										// ListRepresentation setN = new ListRepresentation();
										// setN = prefix();
										if(prefix.operands.get(1).operands.size() > 0) {
											prefix.operands.get(1).setNegative(true);
										}else {
											prefix.operands.get(1).getNodeVal().setCoefficient(prefix.operands.get(1).getNodeVal().getCoefficient() * -1);	
										}

									} else {
										prefix.setNegative(true);
									}
								}

								// op = new ArrayList<ListRepresentation>();
								// Utils.printListRepresentation(prefix);
								// System.out.println(""+trans.peek().getVariablesOrOperator());
								op.add(prefix);
							} else {
								Collections.reverse(op);
								prefix.operands = op;
								op.add(prefix);
							}
						}
						prefix = new ListRepresentation();
						prefix.setNodeVal(token);
						trans.push(prefix.getNodeVal());
					}
				}
			}
		}
		// Utils.printListRepresentation(prefix);
		// System.out.println("\n\n"+trans.peek().getVariablesOrOperator());
		// System.out.println("stack is empty: " + trans.empty());
		// System.out.println("--------"+trans.peek().getNodeVal().getVariablesOrOperator()+"--------");
		while (trans.peek().getVariablesOrOperator() != "#") {
			prefix = new ListRepresentation();
			
			// System.out.println("---same precedence-----"+trans.peek().getVariablesOrOperator()+"--------");

			if (trans.size() > 1) {
				List<ListRepresentation> temp = new ArrayList<ListRepresentation>(); 
				prefix.setNodeVal(trans.pop());

				Collections.reverse(op);

				temp.add(op.remove(0));
				temp.add(op.remove(0));

				Collections.reverse(op);
				prefix.operands = temp;
				// op = new ArrayList<ListRepresentation>();
				if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
					prefix.getNodeVal().setVariablesOrOperator("+");
					// prefix.setNegative(true);
					if (prefix.operands.size() > 0) {
						// ListRepresentation setN = new ListRepresentation();
						// setN = prefix();
						if(prefix.operands.get(1).operands.size() > 0) {
							prefix.operands.get(1).setNegative(true);
						}else {
							prefix.operands.get(1).getNodeVal().setCoefficient(prefix.operands.get(1).getNodeVal().getCoefficient() * -1);	
						}

					} else {
						prefix.setNegative(true);
					}
				}
				op.add(prefix);
			} else {
				Collections.reverse(op);
				prefix.operands = op;
				op.add(prefix);
			}
		}

		// System.out.println("---------------------");
		// Utils.printListRepresentation(prefix);
		// System.out.println();
		// System.out.println("--------------------- return ---------------------");
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