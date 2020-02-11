import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
public class MinimaxH{

	private int depth;
	private HashSet<String> visited;
	private final int CUTOFF;

	public MinimaxH(int cutoff){
		depth = 0;
		visited = new HashSet<String>();
		CUTOFF = cutoff; 
	}

	public Board minimax_decision(Board b){
		ArrayList<Board> actions = b.getChildren();
		if(actions.size() == 0){
			return null;
		}
		Board bestAction = actions.get(0);
		for(int i = 0; i<actions.size(); i++){
			int minValue = min_value(actions.get(i));
			depth = 0; //reset depth
			if(bestAction.getUtilValue() < minValue){
				bestAction = actions.get(i);
			}
			//visited.clear();
		}
		visited.clear();
		return bestAction;
	}

	public int max_value(Board b){
		if(depth == CUTOFF){
			//System.out.println("min: ret terminal state with util value: " + utility_value(b)); 
			//b.drawBoard();
			return end_hueristic(b);
		}
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = b.getChildren();
		depth++;
		for(int i = 0; i<actions.size(); i++){
			if(!visited.contains(Arrays.deepToString(actions.get(i).getBoard()))){//actions.get(i) not seen before
				visited.add(Arrays.deepToString(actions.get(i).getBoard()));
				v = Math.max(v, min_value(actions.get(i)));
				//if(v>Integer.MIN_VALUE && v<Integer.MAX_VALUE){//not +/- infinity{
					//actions.get(i).setUtilValue(v);
					//System.out.println("max: Setting util val to " + v);
				//}
			}
			else{
				actions.get(i).setUtilValue(0);
			}
		}
		return v;
	}

	public int min_value(Board b){
		if(depth == CUTOFF){
			//System.out.println("min: ret terminal state with util value: " + utility_value(b));
			//b.drawBoard();
			return end_hueristic(b);
		}
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = b.getChildren();
		depth++;
		for(int i = 0; i<actions.size(); i++){
			if(!visited.contains(Arrays.deepToString(actions.get(i).getBoard()))){
				visited.add(Arrays.deepToString(actions.get(i).getBoard()));
				v = Math.min(v, max_value(actions.get(i)));
				//if(v>Integer.MIN_VALUE && v<Integer.MAX_VALUE){//not +/- infinity{
					//actions.get(i).setUtilValue(v);
					//System.out.println("min: Setting util val to " + v);
				//}
			}
			else{
				actions.get(i).setUtilValue(0);
			}
		}
		return v;
	}

	public int end_hueristic(Board b){
		boolean blackTurn = b.isBlackTurn();
		Piece [][] board = b.getBoard();
		int sum = 0;
		if(!blackTurn){
			//
			for(int r = 0; r<b.getDIM(); r++){
				for(int c = 0; c<b.getDIM(); c++){
					if(b.isOccupied(r,c) && b.getPiece(new Point(r,c)).getSide().equals("white")){
						sum+=(r-b.getDIM() * .1);
					}
				}
			}
		}
		else{
			for(int r = 0; r<b.getDIM(); r++){
				for(int c = 0; c<b.getDIM(); c++){
					if(b.isOccupied(r,c) && b.getPiece(new Point(r,c)).getSide().equals("white")){
						sum+=(r * .1);
					}
				}
			}
			sum = -sum;
		}
		return sum;
	}

	public boolean isTerminal(Board b){
		if(b.getWhitePosList().isEmpty()){// || depth == 500){
			return true;
		}
		else if(b.getBlackPosList().isEmpty()){
			return true;
		}
		else if(b.getBlackPosList().size() == 1 && b.getWhitePosList().size() == 1 && 
				b.getBlackKingList().size() == 1 && b.getWhiteKingList().size() == 1 &&
				b.canCapture(b.getPiece(b.getBlackPosList().get(0))) == null && 
				b.canCapture(b.getPiece(b.getWhitePosList().get(0))) == null ){ //and cant capture on the nexdt move
			return true;
		}
		else{
			return false;
		}
	}

	public Board deepCopy(Board b){
        int d = b.getDIM();
        Board res = new Board(d);
        Piece [][] arr = b.getBoard();
        Piece [][] res_arr = new Piece [d][d];
        for(int r = 0; r<d; r++){
            for(int c = 0; c<d; c++){
                res_arr[r][c] = arr[r][c];
            }
        }
        res.setBoard(res_arr);
        res.setWhitePosList(deepListSet(b.getWhitePosList())); //uncommenting deepList set causes getChildren to work 
        res.setBlackPosList(deepListSet(b.getBlackPosList()));
        // res.setWhitePosList(b.getWhitePosList());
        // res.setBlackPosList(b.getBlackPosList());
        res.setUtilValue(b.getUtilValue());
        res.setBlackTurn(b.isBlackTurn());
        res.silentDrawBoard();
        return res;
    }

    public ArrayList<Point> deepListSet(ArrayList<Point> list){
	    ArrayList<Point> res = new ArrayList<Point>();
	    int len = list.size();
	    for(int i = 0; i<len; i++){
	        res.add(list.get(i));
	    }
	    return res;
    }

}
