import java.util.ArrayList;
public class Node{

	private Board boardState;
	private ArrayList<Node> children;
	private Node parent;

	public Node(){
		boardState = null;
		children = new ArrayList<Node>();
		parent = null;
	}

	public Board getBoardState(){
		return boardState;
	}
	public ArrayList<Node> getChildren(){
		return children;
	}
	public Node getParent(){
		return parent;		
	}
	public void setBoardState(Board b){
		boardState = b;
	}
	public void setChildren(ArrayList<Node> list){
		children = list;
	}
	public void setParent(Node p){
		parent = p;
	}
}
