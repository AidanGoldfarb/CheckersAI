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

	// /*
 //    Returns arraylist of all board states 1 move deep
 //    */
 //    public ArrayList<Board> getChildren(Board b){
 //        ArrayList<Board> list = new ArrayList<Board>();
 //        for(int i = 0; i<b.getDIM(); i++){
 //            for(int j= 0; j<b.getDIM(); j++){
 //                Point p = new Point(i,j);
 //                if(b.getPiece(p)!=null){ //there is a piece at (i,j)
 //                    Piece piece = b.getPiece(p);
 //                    ArrayList<Point> temp_moves = getMoves(b,piece);//get all possible moves for piece at (i,j)
 //                    System.out.println("temp moves: " + temp_moves);
 //                    for(int k = 0; k<temp_moves.size(); k++){
 //                    	Board temp = createDeepCopy(b);
 //                    	temp = b; //need to make a depp copy of b
 //                        temp = setBoardWithMove(temp, piece, temp_moves.get(k)); //generate board with move k played
 //                        list.add(temp);
 //                    }
 //                }
 //            }
 //        }
 //        return list;
 //    }

    // /*
    // Given a board state and a point
    // */
    // public Board setBoardWithMove(Board b, Piece piece, Point p){
    //     System.out.println("Piece: " + piece);
    //     Board resBoard = b;
    //     int pX = piece.getX();
    //     int pY = piece.getY();
    //     String from = b.pointToCord(new Point(pX, pY));
    //     String to = b.pointToCord(p);
    //     String cat = "";
    //     if(Math.abs(pX-p.getX()) == 1 && Math.abs(pY-p.getY()) == 1){
    //         cat = from+"-"+to;
    //     }
    //     else if(Math.abs(pX-p.getX()) == 2 && Math.abs(pY-p.getY()) == 2){
    //         cat = from+"x"+to;
    //     }
    //     System.out.println("CAT: " + cat);
    //     resBoard.move(cat,resBoard.getWhitePosList(), resBoard.getBlackPosList());
    //     return resBoard;
    // }

    // public ArrayList<Point> getMoves(Board b, Piece p){
    //     ArrayList<Point> moves = new ArrayList<Point>();
    //     int x = p.getX();
    //     int y = p.getY();
    //     Point p1 = new Point(x-1, y-1);
    //     Point p2 = new Point(x-1, y+1);
    //     Point p3 = new Point(x+1, y-1);
    //     Point p4 = new Point(x+1, y+1);
    //     Point p5 = new Point(x-2, y-2);
    //     Point p6 = new Point(x-2, y+2);
    //     Point p7 = new Point(x+2, y-2);
    //     Point p8 = new Point(x+2, y+2);
    //     if(b.isMoveLegal(p,p1)){
    //         moves.add(p1);
    //     }
    //     if(b.isMoveLegal(p,p2)){
    //         moves.add(p2);
    //     }
    //     if(b.isMoveLegal(p,p3)){
    //         moves.add(p3);
    //     }
    //     if(b.isMoveLegal(p,p4)){
    //         moves.add(p4);
    //     }
    //     if(b.canCapture(p)!= null && b.canCapture(p).getX() == p5.getX() && b.canCapture(p).getY() == p5.getY()){
    //         moves.add(p5);
    //     }
    //     if(b.canCapture(p)!= null && b.canCapture(p).getX() == p6.getX() && b.canCapture(p).getY() == p6.getY()){
    //         moves.add(p6);
    //     }
    //     if(b.canCapture(p)!= null && b.canCapture(p).getX() == p7.getX() && b.canCapture(p).getY() == p7.getY()){
    //         moves.add(p7);
    //     }
    //     if(b.canCapture(p)!= null && b.canCapture(p).getX() == p8.getX() && b.canCapture(p).getY() == p8.getY()){
    //         moves.add(p8);
    //     }
    //     return moves;
    // }
}