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

        //System.out.println(position +": "+treeRepresentationText.charAt(position));

        // if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ',') {
        // 	Node node = new Node();
        // 	Node nil = new Node(-1);
        // 	node.setLeftChild(nil);
        // 	node.setRightChild(nil);
        // 	position++;
        // 	return node;
        // } else if (treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) == ')') {
        // 	Node node = new Node();
        // 	Node nil = new Node(-1);
        // 	node.setLeftChild(nil);
        // 	node.setRightChild(nil);
        // 	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        // 	position++;
        // 	// System.out.println(position);
        // 	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        // 	return node;
        // } else if(treeRepresentationText.charAt(position) == ',' && treeRepresentationText.charAt(position+1) != ')' && treeRepresentationText.charAt(position+1) != ',') {
        // 	// System.out.println(position +": "+treeRepresentationText.charAt(position));
        // 	position++;
        //     return createTree(treeRepresentationText);
        	// System.out.println("49      :"+position);
      //   	while (treeRepresentationText.charAt(position) != ',' && treeRepresentationText.charAt(position) != ')') {
      //   		// System.out.println(position +": "+treeRepresentationText.charAt(position));
      //   		System.out.println(position +": "+treeRepresentationText.charAt(position));
    		// 	treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
    		// 	// System.out.println(position +": "+treeRepresentationText.charAt(position));
    		// 	// System.out.println(position +": "+treeRepresentationText.charAt(position));
    		// }
    		// Node node = new Node(Integer.parseInt(treeString));
    		// treeString = "";
        // } else if(treeRepresentationText.charAt(position) == ')' /*&& treeRepresentationText.charAt(position-1) == ')'*/) {
        // 	Node node = new Node(-1);
        // 	System.out.println(position +"))): "+treeRepresentationText.charAt(position));
	       //  position++;
        // 	return node;
        // } else
        // if (position >= treeRepresentationText.length() ) {
        //     // System.out.println(position +": "+treeRepresentationText.charAt(position));
        //     Node node = new Node(-1);
        //     return node;
        // }

        if(treeRepresentationText.charAt(position) == '(') {
    		position++; 
    		Node node = createTree(treeRepresentationText);
            // System.out.println("root: "+node.getid());
    		node.setLeftChild(createTree(treeRepresentationText));
            // System.out.println(position+" root: "+node.getid()+" node left: "+node.getLeftChild().getid());
    		node.setRightChild(createTree(treeRepresentationText));
            // System.out.println(position+" root: "+node.getid()+" node right: "+node.getLeftChild().getid());
            System.out.println(position+" root: "+node.getid()+" node right: "+node.getLeftChild().getid()+" node left: "+node.getLeftChild().getid());

    		return node;
    	} else if (treeRepresentationText.charAt(position) == ',') {
            if (treeRepresentationText.charAt(position+1) == ',' || treeRepresentationText.charAt(position+1) == ')') {
                Node node = new Node();
                Node nil = new Node(-1);
                node.setLeftChild(nil);
                node.setRightChild(nil);
                position++;
                return node;
            }
            position++;
            // while (treeRepresentationText.charAt(position) != ',' && treeRepresentationText.charAt(position) != ')') {
                 // System.out.println(position +": "+treeRepresentationText.charAt(position));
            //     treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
            // }
            Node node = createTree(treeRepresentationText);
            // System.out.println("<,>"+node.getid());
            //treeString = "";
            // node.setLeftChild(createTree(treeRepresentationText));
            // node.setRightChild(createTree(treeRepresentationText));
            return node;
        } else if (treeRepresentationText.charAt(position) == ')') {
            Node nil = new Node(-1);
            nil.setRightChild(nil);
            nil.setLeftChild(nil);
            System.out.println("position Number:"+position);
            if (position == treeRepresentationText.length()) {
                return node;
            } else {
                position++;
                return node;
            }
        } else {

            System.out.println("Enter else!!!!");
            while (treeRepresentationText.charAt(position) != ',' && treeRepresentationText.charAt(position) != ')') {
                //System.out.println(position +": "+treeRepresentationText.charAt(position));
                treeString = treeString.concat(""+treeRepresentationText.charAt(position++));
            }
            Node node = new Node( Integer.parseInt(treeString));
            treeString = "";
            return node;
        }

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

            System.out.println("root: "+root.getid()+"left: "+root.getLeftChild().getid()+"right: "+root.getRightChild().getid());
			
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
