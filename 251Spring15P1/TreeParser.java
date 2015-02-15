import java.util.Scanner;

public class TreeParser {
	static int position;
	
	static String treeString;
	
	public static String getCleanedString(String text) {
		/* TODO: strip blanks in 'text' */
		return text.replace(" ","");
	}
	
	public static Node createTree(String treeRepresentationText) {
		int i, j, k=1;
		String second="", thrid="";
		/* TODO: build the tree by parsing 'treeRepresentationText' */
<<<<<<< Updated upstream
		for( i=1; i<treeRepresentationText.length(); i++)
			if(treeRepresentationText.charAt(i)>'9'||
				treeRepresentationText.charAt(i)<'0')
					break;
		if(i>=treeRepresentationText.length())
			return null;
		int first = Integer.parseInt(treeRepresentationText.substring(1,i));
		//System.out.print("---"+first+"---\n");
		Node newnode = new Node(first);
		if(treeRepresentationText.charAt(i)==')')
			return newnode;
		if(treeRepresentationText.charAt(i++)==',' & treeRepresentationText.charAt(i++)!=','){
			for( j= i; j<treeRepresentationText.length() && k!=0; j++){
			if(treeRepresentationText.charAt(j)=='(')
					k++;
			if(treeRepresentationText.charAt(j)==')')
					k--;
			}
=======
        // int bracketsCount = 0;
        // int commaCount = 0;
        // Node root = new Node();
        // // String[] rlc = new String[3];
        System.out.println(treeRepresentationText);
        int i = 0;

        if (treeRepresentationText.charAt(0) == '(') {
            System.out.println("adad");
            treeRepresentationText = treeRepresentationText.substring(1);
            while (treeRepresentationText.charAt(i) != ',' && treeRepresentationText.charAt(i) != ')') {
                treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
            }
            Node node = new Node( Integer.parseInt(treeString));
            treeString = "";
            treeRepresentationText = treeRepresentationText.substring(i);
            System.out.println(treeRepresentationText);

            if (treeRepresentationText.charAt(0) == ',') {
                System.out.println("adad,,,");
                treeRepresentationText = treeRepresentationText.substring(1);
                // System.out.println(treeRepresentationText);
                if (treeRepresentationText.charAt(0) == '(') {
                    node.setLeftChild(createTree(treeRepresentationText));
                    //return node;
                    //createTree(treeRepresentationText);
                } else if (treeRepresentationText.charAt(0) == ',') {
                    node.setLeftChild(new Node());
                    treeRepresentationText = treeRepresentationText.substring(1);

                    if (treeRepresentationText.charAt(0) == ')') {
                        node.setRightChild(new Node());
                        treeRepresentationText = treeRepresentationText.substring(1);
                        return node;
                    } else {
                        i = 0;
                        while (treeRepresentationText.charAt(i) != ')') {
                            treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
                        }
                        node.setRightChild(new Node( Integer.parseInt(treeString)));
                        treeRepresentationText = treeRepresentationText.substring(i+1);
                        // System.out.println(treeRepresentationText);
                        return node;
                    }

                }    else {
                    System.out.println("adad-else");
                    i = 0;
                    while (treeRepresentationText.charAt(i) != ',' && treeRepresentationText.charAt(i) != ')') {
                        treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
                    }
                    node.setLeftChild(new Node( Integer.parseInt(treeString)));
                    treeString = "";
                    treeRepresentationText = treeRepresentationText.substring(i+1); //(1, 2[,] 3) 

                    if (treeRepresentationText.charAt(0) == '(') {
                        node.setRightChild(createTree(treeRepresentationText));
                        return node;
                    } else if (treeRepresentationText.charAt(0) == ')') {
                        node.setRightChild(new Node());
                        treeRepresentationText = treeRepresentationText.substring(1);
                        return node;
                    } else {
                        i = 0;
                        while (treeRepresentationText.charAt(i) != ')') {
                            treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
                        }
                        node.setRightChild(new Node( Integer.parseInt(treeString)));
                        treeRepresentationText = treeRepresentationText.substring(i+1);
                        treeString = "";
                        return node;
                    }
                }
            } else {
                // node.setRightChild(new Node(-1));
                // node.setLeftChild(new Node(-1));
                System.out.println("adad- )))");
                treeRepresentationText = treeRepresentationText.substring(1);
                System.out.println(treeRepresentationText);

                if (treeRepresentationText.charAt(0) == ',') {
                    treeRepresentationText = treeRepresentationText.substring(1);
                    // System.out.println(treeRepresentationText);
                    if (treeRepresentationText.charAt(0) == '(') {
                        node.setRightChild(createTree(treeRepresentationText));
                    } else if (treeRepresentationText.charAt(0) == ')') {
                        node.setRightChild(new Node());
                        treeRepresentationText = treeRepresentationText.substring(1);
                        return node;
                    } else {
                        while (treeRepresentationText.charAt(i) != ')') {
                            treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
                        }
                        node.setRightChild(new Node( Integer.parseInt(treeString)));
                        treeRepresentationText = treeRepresentationText.substring(i+1);
                        treeString = "";
                        return node;
                    }

                    // while (treeRepresentationText.charAt(i) != ')') {
                    //     treeString = treeString.concat(""+treeRepresentationText.charAt(i++));
                    // }
                    // node.setRightChild(new Node( Integer.parseInt(treeString)));
                    // treeRepresentationText = treeRepresentationText.substring(i);

                    // return node;
                }
                node.setRightChild(new Node());
                treeRepresentationText = treeRepresentationText.substring(1);
                return node;
            }
            
        } 
        return null;

     //    if(treeRepresentationText.charAt(position) == '(') {
    	// 	position++; 
    	// 	Node node = createTree(treeRepresentationText);
    	// 	node.setLeftChild(createTree(treeRepresentationText));
    	// 	node.setRightChild(createTree(treeRepresentationText));
     //        // System.out.println(position+" root: "+node.getid()+" node right: "+node.getLeftChild().getid()+" node left: "+node.getLeftChild().getid());
>>>>>>> Stashed changes

			second = treeRepresentationText.substring(i-1,j);
			i=j+1;
		//	System.out.print("---"+second+"---\n");
		}
		if(treeRepresentationText.charAt(i)=='('){
			k=1;
			for( j=i+1; j<treeRepresentationText.length() && k!=0; j++){
			if(treeRepresentationText.charAt(j)=='(')
					k++;
			if(treeRepresentationText.charAt(j)==')')
					k--;
			}

			thrid = treeRepresentationText.substring(i,j);
			i=j+1;
		//	System.out.print("---"+thrid+"---\n");
		
		}
		newnode.setLeftChild(createTree(second));
		newnode.setRightChild(createTree(thrid));


		return newnode;
	}
	
	public static String traversePath(Node root, String direction) {
		/* TODO: traverse path in tree denoted by 'direction' and print node labels */
		/* print a '*' when path would leave the tree */
		String all=root.getid()+" ";
		for(int i=0; i<direction.length();i++){
			if(direction.charAt(i)=='R'){
				
				root = root.getRightChild();
				if(root==null){
					all+="* ";
					break;
				}all+=root.getid()+" ";
			}
			if(direction.charAt(i)=='L'){
				root = root.getLeftChild();
				if(root==null){
					all+="* ";
					break;
				}
				all+=root.getid()+" ";
			}
		}
		return  all;
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
<<<<<<< Updated upstream
			
=======

            //asdasd +"left: "+root.getLeftChild().getid()+"right: "+root.getRightChild().getid()
            //System.out.println("root: "+root.getid()+"left: "+root.getLeftChild().getid()+"right: "+root.getRightChild().getid());
			System.out.println("root: "+root.getid());
            // System.out.println("left: "+root.getLeftChild().getid());
            //System.out.println("right: "+root.getRightChild().getid());


>>>>>>> Stashed changes
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
