import java.util.Scanner;

public class TreeParser {
	static int position = 0;
	
	static String treeString;
	
	public static String getCleanedString(String text) {
		/* TODO: strip blanks in 'text' */
        return text.replace(" ","");
	}
	
	public static Node createTree(String treeRepresentationText) {
		/* TODO: build the tree by parsing 'treeRepresentationText' */
        int bracketsCount = 0;
        int commaCount = 0;
        //Node root = new Node();
        String[] rlc = new String[3];
        int j = 0;

        if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ',') {
        	Node node = new Node();
        	position++;
        	return node;
        } else if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ')') {
        	Node node = new Node();
        	position++;
        	return node;
        } else if(treeRepresentationText.charAt(position++) == ',') {
        	while (treeRepresentationText.charAt(position) != ',' || treeRepresentationText.charAt(position) != ')') {
    			treeString.concat(treeRepresentationText.charAt(position++));
    		}
    		Node node = new Node( Integer.parseInt(treeString));

    		return node
        }

        if(treeRepresentationText.charAt(position) == ')') {
        	position++;
        }


    	if(treeRepresentationText.charAt(position++) == '(') {
    		while (treeRepresentationText.charAt(position) != ',' || treeRepresentationText.charAt(position) != ')') {
    			treeString.concat(treeRepresentationText.charAt(position++));
    		}
    		Node node = new Node( Integer.parseInt(treeString));
    		treeString = "";
    		bracketsCount++;
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
