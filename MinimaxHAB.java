import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
public class MinimaxHAB{

	private int depth;
	private HashSet<String> visited;
	private int alpha,beta;
	private final int CUTOFF;

	public MinimaxHAB(int cutoff){
		depth = 0;
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
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
			int minValue = min_value(actions.get(i),alpha,beta);
			depth = 0; //reset depth
			if(bestAction.getUtilValue() < minValue){
				bestAction = actions.get(i);
			}
			//visited.clear(); //submitted file has this uncommented on all minimax files
		}
		visited.clear();
		return bestAction;
	}

	public int max_value(Board b, int alpha, int beta){
		if(depth == CUTOFF){
			return end_hueristic(b);
		}
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = b.getChildren();
		depth++;
		for(int i = 0; i<actions.size(); i++){
			if(!visited.contains(Arrays.deepToString(actions.get(i).getBoard()))){//actions.get(i) not seen before
				visited.add(Arrays.deepToString(actions.get(i).getBoard()));
				v = Math.max(v, min_value(actions.get(i),alpha,beta));
				if(v>=beta){
					return v;
				}
				alpha = Math.max(alpha,v);
			}
		}
		return v;
	}

	public int min_value(Board b, int alpha, int beta){
		if(depth == CUTOFF){
			return end_hueristic(b);
		}
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = b.getChildren();
		depth++;
		for(int i = 0; i<actions.size(); i++){
			if(!visited.contains(Arrays.deepToString(actions.get(i).getBoard()))){
				visited.add(Arrays.deepToString(actions.get(i).getBoard()));
				v = Math.min(v, max_value(actions.get(i),alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta = Math.min(beta,v);
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

