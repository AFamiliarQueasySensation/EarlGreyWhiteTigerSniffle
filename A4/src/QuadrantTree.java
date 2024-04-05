
public class QuadrantTree {
	private QTreeNode root; // root of quadrant tree

	public QuadrantTree(int[][] thePixels) {
		int size = thePixels.length;
		root = constructQuadTree(thePixels, 0, 0, size);
	}

	private QTreeNode constructQuadTree(int[][] thePixels, int x, int y, int size) {
		

		int averageColor = Gui.averageColor(thePixels, x, y, size);

		QTreeNode node = new QTreeNode(null, x, y, size, averageColor);


		// leaf bass c ase
		if (size == 1) {

			return node;
		}
		// construct

		

		QTreeNode node1 = constructQuadTree(thePixels, x, y, size/2);
		node1.setParent(node);
		node.setChild(node1, 0);
		
		QTreeNode node2 = constructQuadTree(thePixels, x + size/2, y, size/2);
		node2.setParent(node);
		node.setChild(node2, 1);
	
		QTreeNode node3 = constructQuadTree(thePixels, x, y + size/2, size/2);
		node3.setParent(node);
		node.setChild(node3, 2);
	
		QTreeNode node4 = constructQuadTree(thePixels, x + size/2, y + size/2, size/2);
		node4.setParent(node);
		node.setChild(node4, 3);

		return node;
	}

	/**
	 * 
	 * @return return's its root.
	 */
	public QTreeNode getRoot() {
		return this.root;
	}

	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {

		if (r == null)
			return null;
		// if the level is larger than the tree then gets the bottom level
		if (r.getSize() < theLevel)
			theLevel = r.getSize();

		if (r.isLeaf() || theLevel <= 0) { // if leaf or root; base case
			ListNode<QTreeNode> node = new ListNode<QTreeNode>(null);
			node.setData(r);
			node.setNext(null);
			return node;
		}
		ListNode<QTreeNode> listHolder = new ListNode<>(null);

		getPixelsRecursion(r, theLevel, listHolder);
		return listHolder.getNext();

	}

	private void getPixelsRecursion(QTreeNode r, int theLevel, ListNode<QTreeNode> result) {
		if (theLevel == 0 || r.isLeaf()) {
			ListNode<QTreeNode> Holder = new ListNode<>(r);
			Holder.setNext(null);
			result.setNext(Holder);
			return;
		}
		for (int i = 0; i < 4; i++) {
			getPixelsRecursion(r.getChild(i), theLevel - 1, result);
			while (result.getNext() != null) {
				result = result.getNext();
			}
		}

	}



	private int getHeight(QTreeNode r,int start) {
		QTreeNode curr = r;
		if (curr.getParent() != null) {
			return getHeight(r.getParent(), start +1);
		}
		else {
			return start;
		}
	}
	
	 public Duple findMatching (QTreeNode r, int theColor, int theLevel) {
		
		ListNode<QTreeNode> FirstNull = new ListNode<QTreeNode>(null);
		Duple list = new Duple();
		list.setFront(FirstNull);
		findMatchingRecursion(r, theColor, theLevel, 0, list);
		return list;
	 }
	 
	private void findMatchingRecursion(QTreeNode r, int theColor, int theLevel, int CurrLevel, Duple list) {
		//bass case
		if (r == null || CurrLevel > theLevel) return;
			
		
	// on correct level
		if (r.isLeaf() || theLevel == CurrLevel) {
		if (Gui.similarColor(r.getColor(), theColor)) {
			ListNode<QTreeNode> temp = new ListNode<QTreeNode>(r);
			// skip the null 
			if (list.getFront().getData() == null) {
				list.setFront(temp);
			}
			else {//expands the list 
				ListNode<QTreeNode> tempp = list.getFront();
				while(tempp.getNext() != null)
				{
					tempp = tempp.getNext();
				}
				// find where to add a new one
				tempp.setNext(temp);
				list.setCount(list.getCount() + 1);
				return;
			}
			
		}
		}
		
		
		for (int i = 0; i < 4; ++i) {
			// test this might be wrong beacuse if the tree is not perfect
			if (r.getChild(i) != null) {
			findMatchingRecursion(r.getChild(i), theColor, theLevel, CurrLevel + 1, list);
			}
			
		}
		
	}
	
	

	
	// returns a node in the subtree rooted at r and at level (theLevel)
	// representing a quadrant containing point (x,y)
	// returns null if DNE
	// recursive 
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {

		if (r != null) {
			
			if (r.getx() == x && r.gety() == y) {
				/// use a hight private var
				if (r.getParent() == null) {
					return r; // Root

				} else {
					return r;
				}
					
			} 
			
			else {
				for (int i = 0; i < 4; ++i) {
					QTreeNode result = findNode(r.getChild(i), theLevel, x, y);
				if (result != null) {
	                return result; // Node found, return immediately
				}
	            }
			}
		}
		return null;
	}
	
	
	

	

	public static void main(String[] args) {
	}

}
