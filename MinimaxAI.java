import java.util.ArrayList;
public class MinimaxAI{

	public MinimaxAI(){

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
			v = Math.min(v, min_value(actions.get(i)));
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
		if(b.getWhitePosList().isEmpty()){
			return true;
		}
		else if(b.getBlackPosList().isEmpty()){
			return true;
		}
		else if(b.getBlackPosList().size() == 1 && b.getWhitePosList().size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
}