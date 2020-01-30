import java.util.ArrayList;
public class CompleteStateSpace{

	private Node root;

	//Creates state space with given board state as root
	public CompleteStateSpace(Board b){
		root = new Node();
		root.setBoardState(b);
		root.setParent(null);
		root.setChildren(null);
	}
}