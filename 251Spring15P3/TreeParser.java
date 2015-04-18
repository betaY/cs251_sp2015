import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class TreeParser {
	static int position;
	
	static String treeString;

	static Stack<String> lr = new Stack<String>();
	static Stack<Node> h = new Stack<Node>();
	
	public static String getCleanedString(String text) {
		return text.replaceAll("\\s","");
	}
	
	private static Node parseAndCreateTree() {
		if (treeString.charAt(position) == '(') {
			if (treeString.charAt(position + 1) == ')') {
				position += 2;
				return null;
			}
			char val = treeString.charAt(position + 1);
			Node curNode = new Node(String.valueOf(val));
			position += 3;
			if (treeString.charAt(position - 1) == ')')
				return curNode;
			Node leftRoot = parseAndCreateTree();
			position += 1;
			Node rightRoot = parseAndCreateTree();
			position += 1;
			curNode.setLeftChild(leftRoot);
			curNode.setRightChild(rightRoot);
			return curNode;
		}
		return null;

	}
	
	public static Node createTree(String treeRepresentationText) {
		treeString = treeRepresentationText;
		position = 0;
		return parseAndCreateTree();
	}

	public static Boolean areSm(Node r1, Node r2) {
		if (r1 == null && r2 == null)
			return true;
		if (r1 == null && r2 != null)
			return false;
		if (r1 != null && r2 == null)
			return true;

		return (areSm(r1.getLeftChild(), r2.getLeftChild()) && areSm(r1.getRightChild(), r2.getRightChild()));
	}

	public static Boolean areSm(Node r1, String r2) {

		for (int i = 0; i < r2.length(); i++) {
			if (r1 == null)
				return false;

			if (r2.charAt(i) == 'L') {
				r1 = r1.getLeftChild();
				if (r1 == null)
					return false;
			}
			if (r2.charAt(i) == 'R') {
				r1 = r1.getRightChild();
				if (r1 == null)
					return false;
			}
		}
		return true;
	}

	public static Boolean areAp(Node r1, String r2) {
		Boolean out = false;
		int len = r2.length();
		for (int i=0; i < len*3/4; i++) {
			// out = (out || areSm(r1, r2) );
			if (len - i >= len*3/4) {
				for (int j = 0; j <= len-i-len*3/4; j++) {
					out = (out || areSm(r1, r2.substring(j, len-i)) );
					System.out.printf("-------------%s %s %s-------------\n", r2.substring(j, len-i), out, r1.getinfo());
				}
				// System.out.println();
			}
		}

		return out;
	}


	public static void preOrder(Node t, Node n, String index, List<String> out) {

		if (t == null) {
			return;
		}
		if (index.equals("ROOT")) {
			lr.push("");
			h.push(t);
		}
		if (index.equals("L")) {
			lr.push(lr.peek()+"L");
			h.push(t);
		}
		if (index.equals("R")) {
			lr.push(lr.peek()+"R");
			h.push(t);
		}

		preOrder(t.getLeftChild(), n, "L", out);
		preOrder(t.getRightChild(), n, "R", out);
		// System.out.printf("-------------%s-------------", h.peek().getinfo());
		// System.out.println("-------------"+areSm(h.peek(), n)+"-------------");
		if (areSm(h.pop(), n)) {
			// System.out.printf("%s-------------\n", lr.peek());
			if (lr.peek().equals("")) {
				out.add("ROOT");
				lr.pop();	
			} else 
				out.add(lr.pop());
		}
		else {
			// System.out.printf("%s-------------\n", lr.peek());
			lr.pop();	
		}
	}

	public static void preOrder(Node t, String n, String index, List<String> out) {

		if (t == null) {
			return;
		}
		if (index.equals("ROOT")) {
			lr.push("");
			h.push(t);
		}
		if (index.equals("L")) {
			lr.push(lr.peek()+"L");
			h.push(t);
		}
		if (index.equals("R")) {
			lr.push(lr.peek()+"R");
			h.push(t);
		}

		preOrder(t.getLeftChild(), n, "L", out);
		preOrder(t.getRightChild(), n, "R", out);
		// System.out.printf("-------------%s-------------", h.peek().getinfo());
		// System.out.println("-------------"+areSm(h.peek(), n)+"-------------");
		if (areSm(h.pop(), n)) {
			// System.out.printf("%s-------------\n", lr.peek());
			if (lr.peek().equals("")) {
				out.add("ROOT");
				lr.pop();	
			} else 
				out.add(lr.pop());
		}
		else {
			// System.out.printf("%s-------------\n", lr.peek());
			lr.pop();	
		}
	}

	public static void preOrderAp(Node t, String n, String index, List<String> out) {

		if (t == null) {
			return;
		}
		if (index.equals("ROOT")) {
			lr.push("");
			h.push(t);
		}
		if (index.equals("L")) {
			lr.push(lr.peek()+"L");
			h.push(t);
		}
		if (index.equals("R")) {
			lr.push(lr.peek()+"R");
			h.push(t);
		}

		preOrder(t.getLeftChild(), n, "L", out);
		preOrder(t.getRightChild(), n, "R", out);
		System.out.printf("-------------%s-------------\n", h.pop().getinfo());
		// System.out.println("-------------"+areSm(h.peek(), n)+"-------------");
		// if (areAp(h.pop(), n)) {
		// 	// System.out.printf("%s-------------\n", lr.peek());
		// 	if (lr.peek().equals("")) {
		// 		out.add("ROOT");
		// 		lr.pop();	
		// 	} else 
		// 		out.add(lr.pop());
		// }
		// else {
		// 	// System.out.printf("%s-------------\n", lr.peek());
		// 	lr.pop();	
		// }
	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, Node needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object) 
		 * where the tree denoted by Node object 'needle' is found in the subject tree denoted by 
		 * node object 'haystack' */
		Stack<Node> h = new Stack<Node>();
		List<String> out = new ArrayList<String>();
		preOrder(haystack, needle, "ROOT", out);
		// h.push(haystack);
		if (out.isEmpty())
			out.add("NONE");
		return out;

	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, String needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object) 
		 * where the non-branching tree denoted by the String 'needle' is found in the subject 
		 * tree denoted by the Node object 'haystack' */
		List<String> out = new ArrayList<String>();
		preOrder(haystack, needle, "ROOT", out);
		if (out.isEmpty())
			out.add("NONE");
		return out;
	}
	
	public static List<String> approximateSearch(Node haystack, String needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object)
		 * where the non-branching tree denoted by the String 'needle' is 'approximately' found
		 * in the subject tree denoted by the Node object 'haystack' */
		List<String> out = new ArrayList<String>();
		preOrderAp(haystack, needle, "ROOT", out);
		// h.push(haystack);
		if (out.isEmpty())
			out.add("NONE");
		return out;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int nCases = in.nextInt();
		in.nextLine();

		for(int i = 0; i < nCases; ++i) {
			String treeRepresentationText = in.nextLine();
			treeRepresentationText = getCleanedString(treeRepresentationText);
			System.out.println("Testcase " + (i + 1) + ": " + treeRepresentationText);
			
			Node rootSearchTree = createTree(treeRepresentationText);
			
			int nPaths = in.nextInt();
			in.nextLine();

			System.out.println("Output for testcase " + (i+1));
			for(int j = 0; j < nPaths; ++j) {
				String searchInput = in.nextLine();
				searchInput = searchInput.trim();
				
				String searchOption = searchInput.substring(0, 1);
				String searchPattern = searchInput.substring(2);
				
				searchPattern = getCleanedString(searchPattern);
				
				System.out.println("Query " + (j+1) + ": " + searchPattern);

				List<String> occurences;
				if(searchOption.equals("t")) { // Tree search
					Node rootPatternTree = createTree(searchPattern);
					occurences = searchForNeedleInHaystack(rootSearchTree, rootPatternTree);
				} else if(searchOption.equals("p")) { // Path search
					occurences = searchForNeedleInHaystack(rootSearchTree, searchPattern);
				} else { // Approximate Search
					occurences = approximateSearch(rootSearchTree, searchPattern);
				}
				Collections.sort(occurences);
				for(String position: occurences) {
					System.out.println(position);
				}
			}
		}
		in.close();
	}
}
