import java.util.ArrayList;
public class MinimaxAI{

	private int depth;

	public MinimaxAI(){
		depth = -1;
	}

	public Board minimax_decision(Board b){
		ArrayList<Board> actions = b.getChildren();
		Board bestAction = actions.get(0);
		for(int i = 1; i<actions.size(); i++){
			int minValue = min_value(actions.get(i));
			if(bestAction.getUtilValue() < minValue){
				bestAction = actions.get(i);
			}
		}
		return bestAction;
	}

	public int max_value(Board b){
		if(isTerminal(b)){
			return utility_value(b);
		}
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = b.getChildren();
		depth++;
		for(int i = 0; i<actions.size(); i++){
			v = Math.max(v, min_value(actions.get(i)));
			actions.get(i).setUtilValue(v);
		}
		return v;
	}

	public int min_value(Board b){
		if(isTerminal(b)){
			return utility_value(b);
		}
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = b.getChildren();
		for(int i = 0; i<actions.size(); i++){
			v = Math.min(v, max_value(actions.get(i)));
			actions.get(i).setUtilValue(v);
		}
		return v;
	}

	public int utility_value(Board b){
		if(b.getWhitePosList().isEmpty()){
			return 1;
		}
		else if(b.getBlackPosList().isEmpty()){
			return -1;
		}
		else{
			return 0;
		}
	}

	public boolean isTerminal(Board b){
		if(b.getWhitePosList().isEmpty() || depth == 50){
			return true;
		}
		else if(b.getBlackPosList().isEmpty()){
			return true;
		}
		else if(b.getBlackPosList().size() == 1 && b.getWhitePosList().size() == 1){ //and cant capture on the nexdt move
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