
public class QTreeNode {
	private int x,y;
	private int size;
	private int color;
	private QTreeNode parent;
	private QTreeNode[] children;
	
	
	public QTreeNode() {
		parent = null;
		children = new QTreeNode[4];
	}
	
	public QTreeNode(QTreeNode[]theChildren,int xcoord,int ycoord,int theSize,int theColor) {
		x = xcoord;
		y = ycoord;
		size = theSize;
		color = theColor;
		children = new QTreeNode[4];
		parent = null;
	}
	
	public boolean contains(int xcoord, int ycoord) {
		
		int xStart = this.x;
	    int yStart = this.y;
	    int xEnd = this.x + size;
	    int yEnd = this.y + size;
	    
	    return (xcoord >= xStart && xcoord < xEnd && ycoord >= yStart && ycoord < yEnd);
		
	}
	
	public int getx() {
		return this.x;
	}
	
	public int gety() {
		return this.y;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getColor() {
		return this.color;
	}
	public QTreeNode getParent() {
		return parent;
	}
	public QTreeNode getChild(int index) throws QTreeException{
		if (index <= -1||index >=4) {
			throw new QTreeException("getChild Exception");
		}
		return this.children[index];
	}
	public void setx(int newx) {
		this.x = newx;
	}
	public void sety(int newy) {
		this.y = newy;
	}
	public void setColor(int newColor) {
		this.color = newColor;
	}
	/**
	 * 
	 * @param node 
	 * @return returns level of the node
	 */
	public int level (QTreeNode node) {
		// Input: node of a tree
		// Output: level of the node
		if (node.getParent() == null) return 0;
		else return 1 + level(node.getParent());
		}
	
	public void setSize(int newSize) {
		this.size = newSize;
	}
	public void setParent(QTreeNode parent) {
		this.parent = parent;
	}
	public void setChild(QTreeNode newChild, int index) throws QTreeException {
		if (index >= 0 && index < 4) {
			children[index] = newChild;
		}
		else throw new  QTreeException("Exception in SetChild");
		
	}
	public boolean isLeaf() {
		for (int i = 0; i < 4; i++) {
			if (children[i] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
 
	
		 
		 
		 
		 
		 
	}

}
