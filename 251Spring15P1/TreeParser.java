import java.util.Scanner;

public class TreeParser {
	static int position = 0;
	
	static String treeString = "";
	
	public static String getCleanedString(String text) {
		/* TODO: strip blanks in 'text' */
        return text.replace(" ","");
	}
	
	public static Node createTree(String treeRepresentationText) {
		/* TODO: build the tree by parsing 'treeRepresentationText' */
        int bracketsCount = 0;
        int commaCount = 0;
        Node root = new Node();
        // String[] rlc = new String[3];
        int j = 0;

        System.out.println(position +": "+treeRepresentationText.charAt(position));

        if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ',') {
        	Node node = new Node();
        	Node nil = new Node(-1);
        	node.setLeftChild(nil);
        	node.setRightChild(nil);
        	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        	position++;
        	// System.out.println(position);
        	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        	return node;
        } else if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ')') {
        	Node node = new Node();
        	Node nil = new Node(-1);
        	node.setLeftChild(nil);
        	node.setRightChild(nil);
        	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        	position++;
        	// System.out.println(position);
        	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        	return node;
        } else if(treeRepresentationText.charAt(position) == ',') {
        	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        	position++;
        	// System.out.println("49      :"+position);
        	while (treeRepresentationText.charAt(position) != ',' && treeRepresentationText.charAt(position) != ')') {
        		// System.out.println(position +": "+treeRepresentationText.charAt(position));
        		System.out.println(position +": "+treeRepresentationText.charAt(position));
    			treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
    			// System.out.println(position +": "+treeRepresentationText.charAt(position));
    			// System.out.println(position +": "+treeRepresentationText.charAt(position));
    		}
    		Node node = new Node(Integer.parseInt(treeString));
    		treeString = "";

    		return node;
        } else if(treeRepresentationText.charAt(position) == ')' /*&& treeRepresentationText.charAt(position-1) == ')'*/) {
        	Node node = new Node(-1);
        	System.out.println(position +"))): "+treeRepresentationText.charAt(position));
	        // if (position >= treeRepresentationText.length()) {
	        // 	System.out.println("WTFFFFFFFFFFFFFFF");
	        // 	return node;
	        // }
	        position++;
        	// System.out.println(treeRepresentationText.charAt(position));
        	return node;
        } else if(treeRepresentationText.charAt(position) == '(') {
    		// System.out.println(position +": "+treeRepresentationText.charAt(position));
    		position++;
    		//System.out.println("GGGGGGGG" + position + treeRepresentationText.charAt(position));
    		while (treeRepresentationText.charAt(position) != ',' && treeRepresentationText.charAt(position) != ')') {
    			System.out.println(position +": "+treeRepresentationText.charAt(position));
    			treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
    			//System.out.println("str: "+treeString);
    			// System.out.println(position +": "+treeRepresentationText.charAt(position));
    		}
    		Node node = new Node( Integer.parseInt(treeString));
    		// Node node = new Node();
    		// System.out.println("str: "+treeString + "GGGGGGGG "+ node.getid());
    		treeString = "";
    		// bracketsCount++;
    		node.setLeftChild(createTree(treeRepresentationText));
    		node.setRightChild(createTree(treeRepresentationText));

    		return node;
    	} else {
    		System.out.println(position +": "+treeRepresentationText.charAt(position));
    		treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
    		Node node = new Node( Integer.parseInt(treeString));
    		treeString = "";

    		node.setLeftChild(createTree(treeRepresentationText));
    		node.setRightChild(createTree(treeRepresentationText));
    		return node;
    	}






        // String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }

        // String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }// String[] split = treeRepresentationText.split(",");
        // for (int i = 0; i < split.length(); i++) {
        // 	for (int j = 0; j < split[i].length; j++) {
        // 		if ()
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == ',' && treeRepresentationText.charAt(i) == ',') {
        		
        // 	}
        // }


        // for (int i = 0; i < treeRepresentationText.length(); i++) {
        // 	if (treeRepresentationText.charAt(i) == '(') {
	       //      bracketsCount++;
	       //      if (bracketsCount == 2) {
	       //      	j++;
	       //      }
	       //      rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else if (treeRepresentationText.charAt(i) == ')') {
	       //  	bracketsCount--;
	       //  	if (bracketsCount == 0) {
	       //      	break;
	       //      }
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  } else {
	       //  	rlc[j].concat(treeRepresentationText.charAt(i));
	       //  }
        // }

        // for (int i = 0; i < 3; i++) {

        // 	if (i == 0) {
        // 		Node rt = new root((int)rlc[1]-48);
        // 	}

        // }
        

        // String[] split = treeRepresentationText.split(",");

        // for (int i = 0; i < split.length; i++) {
        // 	for (int j = 0; j < split[i].length(); j++) {
        // 		if (split[i].charAt(j) == '(') {
        // 			bracketsCount++;

        // 		}
        // 	}
        // }

        // for (int i = 0; i < treeRepresentationText.length(); i++) {
	      //   if (treeRepresentationText.charAt(i) == '(' {
	      //       bracketsCount++;
	      //   } else if (treeRepresentationText.charAt(i) == ')') {
	      //       bracketsCount--;
	      //   }
	      //   if (bracketsCount == 0)
		    	// return root;
        // }
	}
	
	public static String traversePath(Node root, String direction) {
		/* TODO: traverse path in tree denoted by 'direction' and print node labels */
		/* print a '*' when path would leave the tree */
		String traverse = Integer.toString(root.getid());
		// System.out.println(traverse);
		for (int i = 0; i < direction.length(); i ++) {
			if (direction.charAt(i) == 'L') {
				root = root.getLeftChild();
				if (root.getid() == 0) {
					traverse.concat(" *");
				} else if (root.getid() == -1) {
					return traverse;
				} else {
					traverse.concat(" "+Integer.toString(root.getid()));
				}
			} else {
				root = root.getRightChild();
				if (root.getid() == 0) {
					traverse.concat(" *");
				} else if (root.getid() == -1) {
					return traverse;
				} else {
					traverse.concat(" "+Integer.toString(root.getid()));
				}
			}
		}
		return traverse;
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int nCases = in.nextInt();
		in.nextLine();

		for(int i = 0; i < nCases; ++i) {
			String treeRepresentationText = in.nextLine();
			treeRepresentationText = getCleanedString(treeRepresentationText);
			System.out.println("Testcase " + (i + 1) + ": " + treeRepresentationText);
			
			Node root = createTree(treeRepresentationText);
			
			int nPaths = in.nextInt();
			in.nextLine();

			System.out.println("Output for testcase " + (i+1));
			for(int j = 0; j < nPaths; ++j) {
				String path = in.nextLine();
				path = getCleanedString(path);

				String output = traversePath(root, path);
				
				System.out.println(path.trim() + ": " + output.trim());
			}
		}
		in.close();
	}
}
