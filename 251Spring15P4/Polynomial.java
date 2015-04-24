import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import java.util.Collections;

public class Polynomial {
	List<ExpressionAtom> infixExpression = new ArrayList<ExpressionAtom>();
	List<ExpressionAtom> reEva = new ArrayList<ExpressionAtom>();
	List<ExpressionAtom> expd = new ArrayList<ExpressionAtom>();
	Stack<List<ExpressionAtom> > myst = new Stack<List<ExpressionAtom> >();
	
	List<ExpressionAtom> finalExpression;

	ListRepresentation listRepresentation;
	int ng = 1;
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

		// Utils.printExpression(this.infixExpression);
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

							while( precedence(prefix.getNodeVal()) == precedence(trans.peek())) {
								prefix.operands = temp;
								// System.out.println("----same precedence----");
								if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
									prefix.getNodeVal().setVariablesOrOperator("+");
									// prefix.setNegative(true);
									if (prefix.operands.size() > 0) {
										// ListRepresentation setN = new ListRepresentation();
										// setN = prefix();
										if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
											prefix.operands.get(prefix.operands.size()-1).setNegative(true);
										}else {
											prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
										}

									} else {
										prefix.setNegative(true);
									}
								}
								prefix.setNodeVal(trans.pop());
								temp.add(op.remove(0));
							}
							Collections.reverse(op);
							
							prefix.operands = temp;
							// Utils.printListRepresentation(prefix);
							// System.out.println("");

							if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
								prefix.getNodeVal().setVariablesOrOperator("+");
								// prefix.setNegative(true);
								if (prefix.operands.size() > 0) {
									// ListRepresentation setN = new ListRepresentation();
									// setN = prefix();
									if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
										prefix.operands.get(prefix.operands.size()-1).setNegative(true);
									}else {
										prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
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

								while( precedence(prefix.getNodeVal()) == precedence(trans.peek())) {
									prefix.operands = temp;
									// System.out.println("----same precedence----");
									if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
										prefix.getNodeVal().setVariablesOrOperator("+");
										// prefix.setNegative(true);
										if (prefix.operands.size() > 0) {
											// ListRepresentation setN = new ListRepresentation();
											// setN = prefix();
											if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
												prefix.operands.get(prefix.operands.size()-1).setNegative(true);
											}else {
												prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
											}

										} else {
											prefix.setNegative(true);
										}
									}
									prefix.setNodeVal(trans.pop());
									temp.add(op.remove(0));
								}

								

								Collections.reverse(op);
								prefix.operands = temp;

								if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
									prefix.getNodeVal().setVariablesOrOperator("+");
									// prefix.setNegative(true);
									if (prefix.operands.size() > 0) {
										// ListRepresentation setN = new ListRepresentation();
										// setN = prefix();
										if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
											prefix.operands.get(prefix.operands.size()-1).setNegative(true);
										}else {
											prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
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

				while( precedence(prefix.getNodeVal()) == precedence(trans.peek())) {
					prefix.operands = temp;
					// System.out.println("----same precedence----");
					if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
						prefix.getNodeVal().setVariablesOrOperator("+");
						// prefix.setNegative(true);
						if (prefix.operands.size() > 0) {
							// ListRepresentation setN = new ListRepresentation();
							// setN = prefix();
							if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
								prefix.operands.get(prefix.operands.size()-1).setNegative(true);
							}else {
								prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
							}

						} else {
							prefix.setNegative(true);
						}
					}
					prefix.setNodeVal(trans.pop());
					temp.add(op.remove(0));
				}


				Collections.reverse(op);
				prefix.operands = temp;
				// op = new ArrayList<ListRepresentation>();
				if(prefix.getNodeVal().getVariablesOrOperator().equals("-")) {
					prefix.getNodeVal().setVariablesOrOperator("+");
					// prefix.setNegative(true);
					if (prefix.operands.size() > 0) {
						// ListRepresentation setN = new ListRepresentation();
						// setN = prefix();
						if(prefix.operands.get(prefix.operands.size()-1).operands.size() > 0) {
							prefix.operands.get(prefix.operands.size()-1).setNegative(true);
						}else {
							prefix.operands.get(prefix.operands.size()-1).getNodeVal().setCoefficient(prefix.operands.get(prefix.operands.size()-1).getNodeVal().getCoefficient() * -1);	
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

	List<ExpressionAtom> multiplication(List<ExpressionAtom> a, List<ExpressionAtom> b, boolean isNegative) {
		int size_a = a.size();
		int size_b = b.size();
		List<ExpressionAtom> temp = new ArrayList<ExpressionAtom>();
		// Utils.printExpression(a);
		// System.out.println("******---******");
		// Utils.printExpression(b);
		// System.out.println("******---******");
		int coef_a = 1;
		int coef_b = 1;
		for(int i = 0; i < size_a; i++) {
			String a_oper = a.get(i).getVariablesOrOperator();
			coef_a = 1;
			int neg_a = 1;
			int neg_b = 1;
			if(a.get(i).getCoefficient() < 0) {
				neg_a *= -1;
			}
			// System.out.println(a_oper == "");
			// System.out.println("-------"+a.get(i).getAtomType()+"-------");

			coef_a = a.get(i).getCoefficient();


			// a.get(i).setCoefficient(a.get(i).getCoefficient()*coef_b);
			// for(int ii = 0; ii < Math.abs(a.get(i).getCoefficient())*coef_b - 1; ii++) {
			// 	a_oper += a.get(i).getVariablesOrOperator();
			// 	System.out.println("******--"+a_oper+"--******");
			// }
			if (a.get(i).getAtomType() == AtomType.OPERATOR) {
				ExpressionAtom product = a.get(i);
				if(isNegative) {
					product.setCoefficient(product.getCoefficient() * -1);
				}
				temp.add(product);
			} else {
				for(int j = 0; j < size_b; j++) {
					neg_b = 1;
					coef_b = 1;

					String b_oper = b.get(j).getVariablesOrOperator();
					if(b.get(j).getCoefficient() < 0) {
						neg_b *= -1;
					}
					
					coef_b = b.get(j).getCoefficient();

					// System.out.println("-------"+b_oper+"-------");
					// for(int jj = 0; jj < Math.abs(b.get(j).getCoefficient())*coef_a -1; jj++) {
					// 	b_oper += b.get(j).getVariablesOrOperator();
					// }
					// b.get(j).setCoefficient(b.get(j).getCoefficient()*coef_a);
					// System.out.println("-------"+a.get(i).getCoefficient()+"\t"+b.get(j).getCoefficient()+"-------");
					if (b.get(j).getAtomType() == AtomType.OPERAND) {
						ExpressionAtom product = new ExpressionAtom(a_oper+b_oper, AtomType.OPERAND, a.get(i).getCoefficient()*b.get(j).getCoefficient());
						if(isNegative) {
							product.setCoefficient(product.getCoefficient() * -1);
						}
						temp.add(product);
					}else {
						ExpressionAtom product = b.get(j);
						if(isNegative) {
							product.setCoefficient(product.getCoefficient() * -1);
						}
						temp.add(product);
					}
				}
			}
			
		}
		// Utils.printExpression(temp);
		// System.out.println("************");
		return temp;
	}

	List<ExpressionAtom> exp(List<ExpressionAtom> a, List<ExpressionAtom> b, boolean isNegative) {
		List<ExpressionAtom> temp = new ArrayList<ExpressionAtom>();
		List<ExpressionAtom> tempN = new ArrayList<ExpressionAtom>();
		temp.addAll(a);
		int coef_b = b.get(0).getCoefficient();
		for(int i = 0; i < coef_b-1; i++) {
			temp = multiplication(temp, a, false);
		}
		// Utils.printExpression(temp);
		// System.out.println("------exp------"+temp.size()+"------");
		if(isNegative) {
			for (int i = 0; i < temp.size(); i++) {
				ExpressionAtom product = temp.get(i);
				product.setCoefficient(product.getCoefficient() * -1);
				tempN.add(product);
			}
			// Utils.printExpression(tempN);
			// System.out.println("-------tempN--------");
			return tempN;
		// // 	ExpressionAtom product = temp.get(0);
		// // 	product.setCoefficient(product.getCoefficient() * -1);
		// // 	temp = new ArrayList<ExpressionAtom>();
		// // 	temp.add(product);
		}

		return temp;
	}

	// List<ExpressionAtom> expand(ListRepresentation seg) {
		
	// 	int size = seg.operands.size();
	// 	if (size == 0) {
	// 		expd.add(seg.getNodeVal());
	// 		return expd;
	// 	} else {
	// 		for (int i = 0; i < size; i++) {
	// 			expand(seg.operands.get(i));
	// 			if(i < size-1 && precedence(seg.getNodeVal())==2) {
	// 				expd.add(seg.getNodeVal());
	// 			} 
	// 		}
	// 	}
	// 	return expd;
	// }


	List<ExpressionAtom> recurEva(ListRepresentation prefix, boolean isNegative) {
		int size = prefix.operands.size();
		
		List<ExpressionAtom> temp = new ArrayList<ExpressionAtom>();
		if(isNegative == true){
			ng = -1;
		} else {
			ng = 1;
		}
		// System.out.println("-------"+isNegative+" "+prefix.isNegative()+size+"-------");
		if(size == 0) {
			reEva = new ArrayList<ExpressionAtom>();
			reEva.add(prefix.getNodeVal());
			// System.out.println(ng);
			reEva.get(0).setCoefficient(reEva.get(0).getCoefficient());
			myst.push(reEva);
			// Utils.printExpression(reEva);
			// System.out.println("---++"+reEva.get(0).getCoefficient()+"++----");
			return null;
		} else {
			for (int i = 0; i < size; i++) {
				// System.out.println("-------- "+isNegative+" "+prefix.operands.get(i).isNegative()+prefix.operands.get(i).getNodeVal().getVariablesOrOperator()+" --------");
				// if(isNegative == true) {
				// 	prefix.operands.get(i).setNegative(isNegative);
				// }
				recurEva(prefix.operands.get(i), prefix.operands.get(i).isNegative());
				if (i > 0) {
					if (precedence(prefix.getNodeVal())==2) {
						// System.out.println("---++=="+ng+" "+prefix.getNodeVal().getCoefficient()+" "+prefix.getNodeVal().getVariablesOrOperator()+"==++----");
						reEva = new ArrayList<ExpressionAtom>();
						reEva.addAll(myst.pop());
					
						reEva.add(prefix.getNodeVal());
						reEva.addAll(myst.pop());
						myst.push(reEva);
						// Utils.printExpression(reEva);
						// System.out.println("-------"+prefix.isNegative()+"-------");
					} else if (precedence(prefix.getNodeVal())==4) {
						// System.out.println("---++=="+ng+" "+prefix.getNodeVal().getCoefficient()+" "+prefix.getNodeVal().getVariablesOrOperator()+"==++----");
						reEva = new ArrayList<ExpressionAtom>();
						temp = new ArrayList<ExpressionAtom>();
						reEva.addAll(myst.pop());
						// Utils.printExpression(reEva);
						// System.out.println("++++++-------+++++++");
						temp.addAll(myst.pop());
						// Utils.printExpression(temp);
						// System.out.println("+++++++++++++");
						reEva = multiplication(temp, reEva, isNegative);
						myst.push(reEva);
					} else {
						reEva = new ArrayList<ExpressionAtom>();
						temp = new ArrayList<ExpressionAtom>();
						reEva.addAll(myst.pop());
						temp.addAll(myst.pop());
						reEva = exp(temp, reEva, isNegative);
						myst.push(reEva);
					}
				}
			}
		}
		// reEva.addAll(myst.pop());
		return reEva;
	}
			
	private List<ExpressionAtom> evaluateExpression() {
		/*
		 * TODO: Write code here to operate on this.listRepresentation and obtain a List of
		 * ExpressionAtom objects which represent the atoms of the final expression. Note that you
		 * are allowed to have repeated variables here. This is not printed anywhere in the code, so
		 * you are free to return the ExpressionAtom objects in whatever form you wish.
		 */
		// System.out.println("====evaluateExpression====");
		ListRepresentation prefix = this.listRepresentation;
		recurEva(prefix, prefix.isNegative());
		// Utils.printListRepresentation(prefix);
		// Utils.printExpression(recurEva(prefix));
		// System.out.println();
		return myst.pop();
	}

	private List<ExpressionAtom> simplifyAndNormalize(List<ExpressionAtom> evaluatedExpression) {
		/*
		 * TODO: Write code here to operate on the List<ExpressionAtom> object obtaind from
		 * 'evaluateExpression'. Specifically, ensure that you combine terms with same variables and
		 * modify coefficients appropriately.
		 */
		// Utils.printExpression(evaluatedExpression);
		// System.out.println();
		String cmp;
		for(int i = 0; i < evaluatedExpression.size(); ++i) {
			evaluatedExpression.get(i).setVariablesOrOperator((sortString(
					evaluatedExpression.get(i).getVariablesOrOperator())));
		}
		for(int i = 0; i < evaluatedExpression.size(); ++i) {
			String str = evaluatedExpression.get(i).getVariablesOrOperator();
			// System.out.println("---------- "+str+" ----------");
			if(evaluatedExpression.get(i).getAtomType() == AtomType.OPERATOR) {
				evaluatedExpression.remove(i);
				i--;
			}
		}
		int size = evaluatedExpression.size();
		for(int i = 0; i < size; ++i) {
			String str_i = evaluatedExpression.get(i).getVariablesOrOperator();
			int coef = evaluatedExpression.get(i).getCoefficient();
			// System.out.println("---------- "+str_i+" ----------");
			for (int j = 0; j < size; ++j) {
				if(i == j) {
					break;
				}
				String str_j = evaluatedExpression.get(j).getVariablesOrOperator();
				if(str_i.equals(str_j)) {
					// System.out.println("---------- "+str_i+"\t"+str_j+" ----------");
					coef += evaluatedExpression.get(j).getCoefficient();
					evaluatedExpression.remove(j);
					size--;
					j--;
					i--;
				}
			}
			evaluatedExpression.get(i).setCoefficient(coef);
		}
		// Utils.printOutputExpression(evaluatedExpression);
		// System.out.println("----------"+evaluatedExpression.size()+"----------");

		return evaluatedExpression;
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