import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreeParser {
	static int position;
	
	static String treeString;
	
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
	
	private static boolean match(Node haystack, Node needle) {
		if(haystack == null && needle == null)
			return true;
		else if(haystack == null)
			return false;
		else if(needle == null)
			return true;
		else
			return match(haystack.getLeftChild(), needle.getLeftChild()) &&
					match(haystack.getRightChild(), needle.getRightChild());
	}
	
	private static void search(Node haystack, Node needle, List<String> positions, String curPosition) {
		if(match(haystack, needle)) {
			if(curPosition.equals(""))
				positions.add("ROOT");
			else
				positions.add(curPosition);
		}
		
		if(haystack == null)
			return;
		
		search(haystack.getLeftChild(), needle, positions, curPosition+"L");
		search(haystack.getRightChild(), needle, positions, curPosition+"R");
	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, Node needle) {
		List<String> positions = new ArrayList<String>();
		search(haystack, needle, positions, "");
		if(positions.size() == 0)
			positions.add("NONE");
		return positions;
	}
	
	private static Node createDummyTree(String direction) {
		Node root = new Node("*");
		Node cur = root;
		for (char ch : direction.toCharArray()){
	        if(ch == 'L') {
	        	cur.setLeftChild(new Node("*"));
	        	cur = cur.getLeftChild();
	        } else {
	        	cur.setRightChild(new Node("*"));
	        	cur = cur.getRightChild();
	        }
	    }
		return root;
	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, String needle) {
		return searchForNeedleInHaystack(haystack, createDummyTree(needle));
	}
	
	private static boolean partialMatch(Node haystack, String direction) {
		if(haystack == null)
			return false;
		
		boolean match = false;
		int minMatch = Math.max((3 * direction.length()) / 4, 1);
		for(int i = minMatch; i <= direction.length(); ++i) {
			for(int j = 0; j <= direction.length() - i; ++j) {
				if(match(haystack, createDummyTree(direction.substring(j, j + i)))) {
					match = true;
					break;
				}
			}
		}
		
		return match;
	}
		
	private static void partialSearch(Node haystack, String direction, List<String> positions, String curPosition) {
		if(partialMatch(haystack, direction)) {
			if(curPosition.equals(""))
				positions.add("ROOT");
			else
				positions.add(curPosition);
		}
		
		if(haystack == null)
			return;
		
		partialSearch(haystack.getLeftChild(), direction, positions, curPosition+"L");
		partialSearch(haystack.getRightChild(), direction, positions, curPosition+"R");
	}

	public static List<String> approximateSearch(Node haystack, String direction) {
		List<String> positions = new ArrayList<String>();
		partialSearch(haystack, direction, positions, "");
		if(positions.size() == 0)
			positions.add("NONE");
		return positions;
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
