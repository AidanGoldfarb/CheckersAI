import java.util.ArrayList;
public class MinimaxAI{

	private int depth;

	public MinimaxAI(){
		depth = 0;
	}

	public Board minimax_decision(Board b){
		ArrayList<Board> actions = b.getChildren();
		Board bestAction = actions.get(0);
		for(int i = 1; i<actions.size(); i++){
			int minValue = min_value(actions.get(i));
			if(bestAction.getUtilValue() < minValue){
				bestAction = actions.get(i);
			}
			//bestAction = max_function(bestAction, min_value(actions.get(i)));
		}
		return bestAction;
	}

	// public Board max_function(Board b, int v){
	// 	int utilValue = b.getUtilValue();
	// 	if(utilValue >= v){
	// 		return b;
	// 	}
	// 	else{

	// 	}
	// }

	public int max_value(Board b){
		if(isTerminal(b)){
			return utility_value(b);
		}
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = b.getChildren();
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
		System.out.println("depth: " + depth++);
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
		if(b.getWhitePosList().isEmpty()){
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
        // res.setWhitePosList(b.getWhitePosList());
        // res.setBlackPosList(b.getBlackPosList());
        res.setBlackTurn(b.isBlackTurn());
        res.silentDrawBoard();
        return res;
    }

}