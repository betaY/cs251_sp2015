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
