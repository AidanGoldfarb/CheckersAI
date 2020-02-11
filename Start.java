import java.util.ArrayList;
import java.util.Scanner;
public class Start {
    static int size;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Select board size (4 or 8): ");
        int in = sc.nextInt();
        if(in == 4){
            size = 4;
        }
        else{
            size = 8;
        }
        System.out.print("0: Human v.s. Human(default) |  1: Human v AI  |  2: AI v AI: ");
        int input = sc.nextInt();
        if(input == 0){
            pvp();
        }
        else if(input == 1){
            System.out.print("0: Minimax(will run out of memory if 8x8)  |  1: MinimaxH  |  2: MinimaxH-AB: ");
            int s = sc.nextInt();
            if(s == 0){
                pvc();
            }
            else if(s == 1){
                int depth = 16;
                while(depth>15){
                    System.out.print("Specify a depth limit (10 recommended, max 15): ");
                    depth = sc.nextInt();
                    if(depth>15){
                        System.out.println("This isn't DeepBlue...");
                    }
                }
                pvcH(depth);

            }
            else if(s == 2){
                int depth = 16;
                while(depth>15){
                    System.out.print("Specify a depth limit (10 recommended, max 15): ");
                    depth = sc.nextInt();
                    if(depth>15){
                        System.out.println("This isn't DeepBlue...");
                    }
                }
                pcvAB(depth);
            }
            else{
                pvc();
            }
            pvc();
        }
        else if(input == 2){
            if(in == 4){
                cvc();
            }else{
                cvc8();
            }
        }
        else{
            pvp();
        }

    }

    public static void cvc(){
        int moves = 0;
        Board b = new Board(size);
        b.drawBoard();
        MinimaxDL ai1 = new MinimaxDL();
        MinimaxDL ai2 = new MinimaxDL();
        while(true){
            if(b.getBlackPosList().size() == 0){
                System.out.println("White wins!");
                System.exit(0);
            }
            else if(b.getWhitePosList().size() == 0){
                System.out.println("Black wins!");
                System.exit(0);
            }
            Board new_board = ai1.minimax_decision(b);
            new_board.drawBoard();
            Board new_board2 = ai2.minimax_decision(new_board);
            new_board2.drawBoard();
            b = deepCopy(new_board2);
            moves++;
        }
    } 
    public static void cvc8(){
        int moves = 0;
        Board b = new Board(size);
        b.drawBoard();
        MinimaxHAB ai1 = new MinimaxHAB(15);
        MinimaxHAB ai2 = new MinimaxHAB(15);
        while(moves<20){ //current heuristic gets in a move loop after this
            if(b.getBlackPosList().size() == 0){
                System.out.println("White wins!");
                System.exit(0);
            }
            else if(b.getWhitePosList().size() == 0){
                System.out.println("Black wins!");
                System.exit(0);
            }
            Board new_board = ai1.minimax_decision(b);
            new_board.drawBoard();
            Board new_board2 = ai2.minimax_decision(new_board);
            new_board2.drawBoard();
            b = deepCopy(new_board2);
            moves++;
        }
        System.out.println("Played to a TIE using heuristic + ab pruning");
    }
    public static void pvc(){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(size);
        MinimaxDL ai = new MinimaxDL();
        b1.drawBoard();
        String m = "";
        int move = 1;
        while(!m.equals("quit")){
            if(move<10){ //!b1.getWhitePosList().isEmpty() && !b1.getBlackPosList().isEmpty() && 
                if(b1.getWhitePosList().isEmpty()){
                    System.out.println("Black wins!");
                    System.exit(0);
                }
                else if(b1.getBlackPosList().isEmpty()){
                    System.out.println("White wins!");
                    System.exit(0);
                }
                else if(b1.getBlackPosList().size() == 1 && b1.getWhitePosList().size() == 1 && 
                        b1.getBlackKingList().size() == 1 && b1.getWhiteKingList().size() == 1&&
                        b1.canCapture(b1.getPiece(b1.getBlackPosList().get(0))) == null && 
                        b1.canCapture(b1.getPiece(b1.getWhitePosList().get(0))) == null ){
                    System.out.println("Tie!");
                    System.exit(0);
                }
                else{
                    if(b1.isBlackTurn()){
                        b1.getChildren();
                        System.out.println("Legal moves: " + clean(b1.getLegalMoves()));
                        System.out.print("Enter a move, 'quit' to quit: ");
                        m = sc.next();
                        if(m.equals("quit")) System.exit(0);
                        else if(!isLegal(m,b1)){
                            System.err.println("That is not a legal move in the current position");
                            continue;
                        }
                        String pieceStr = m.substring(0,2);
                        if(b1.isBlackTurn() && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
                            System.out.print("Black's turn, try again: ");
                        }
                        else if(!b1.isBlackTurn() && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
                            System.out.print("White's turn, try again: ");
                        }
                        else{
                            b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
                            move++;
                            System.out.println("\nYour move: ");
                            b1.drawBoard();
                        }
                    }
                    else{
                        System.out.println("\n\nThinking...");
                        try{
                        Board new_board = ai.minimax_decision(b1);
                        System.out.println("Computer has found the best move: ");
                        b1 = deepCopy(new_board);
                        new_board.drawBoard();
                        }catch(StackOverflowError e){
                            System.out.println("Ran out of memory...don't use regular minimax on 8x8 board");
                            System.exit(-1);
                        }
                    }
                }
            }else{
                System.out.println("Tie! (move limit reached)");
                System.exit(0);
            }
        }
    }
    public static void pvcH(int depth){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(size);
        MinimaxH ai = new MinimaxH(depth);
        b1.drawBoard();
        String m = "";
        int move = 1;
        while(!m.equals("quit")){
            if(b1.getWhitePosList().isEmpty()){
                System.out.println("Black wins!");
                System.exit(0);
            }
            else if(b1.getBlackPosList().isEmpty()){
                System.out.println("White wins!");
                System.exit(0);
            }
            else if(b1.getBlackPosList().size() == 1 && b1.getWhitePosList().size() == 1 && 
                    b1.getBlackKingList().size() == 1 && b1.getWhiteKingList().size() == 1&&
                    b1.canCapture(b1.getPiece(b1.getBlackPosList().get(0))) == null && 
                    b1.canCapture(b1.getPiece(b1.getWhitePosList().get(0))) == null ){
                System.out.println("Tie!");
                System.exit(0);
            }
            else{
                if(b1.isBlackTurn()){
                    b1.getChildren();
                    System.out.println("Legal moves: " + clean(b1.getLegalMoves()));
                    System.out.print("Enter a move: ");
                    m = sc.next();
                    if(m.equals("quit")) System.exit(0);
                    else if(!isLegal(m,b1)){
                            System.err.println("That is not a legal move in the current position");
                            continue;
                    }
                    String pieceStr = m.substring(0,2);
                    if(b1.isBlackTurn() && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
                        System.out.print("Black's turn, try again: ");
                    }
                    else if(!b1.isBlackTurn() && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
                        System.out.print("White's turn, try again: ");
                    }
                    else{
                        b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
                        move++;
                        System.out.println("\n");
                        b1.drawBoard();
                    }
                }
                else{
                    System.out.println("\n\nThinking...");
                    Board new_board = ai.minimax_decision(b1);
                    System.out.println("Computer has found a good move: ");
                    b1 = deepCopy(new_board);
                    new_board.drawBoard();
                }
            }
        }
    }
    public static void pcvAB(int depth){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(size);
        MinimaxHAB ai = new MinimaxHAB(depth);
        b1.drawBoard();
        String m = "";
        int move = 1;
        while(!m.equals("quit")){
            if(b1.getWhitePosList().isEmpty()){
                System.out.println("Black wins!");
                System.exit(0);
            }
            else if(b1.getBlackPosList().isEmpty()){
                System.out.println("White wins!");
                System.exit(0);
            }
            else if(b1.getBlackPosList().size() == 1 && b1.getWhitePosList().size() == 1 && 
                    b1.getBlackKingList().size() == 1 && b1.getWhiteKingList().size() == 1&&
                    b1.canCapture(b1.getPiece(b1.getBlackPosList().get(0))) == null && 
                    b1.canCapture(b1.getPiece(b1.getWhitePosList().get(0))) == null ){
                System.out.println("Tie!");
                System.exit(0);
            }
            else{
                if(b1.isBlackTurn()){
                    b1.getChildren();
                    System.out.println("Legal moves: " + clean(b1.getLegalMoves()));
                    System.out.print("Enter a move: ");
                    m = sc.next();
                    if(m.equals("quit")) System.exit(0);
                    else if(!isLegal(m,b1)){
                            System.err.println("That is not a legal move in the current position");
                            continue;
                    }
                    String pieceStr = m.substring(0,2);
                    if(b1.isBlackTurn() && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
                        System.out.print("Black's turn, try again: ");
                    }
                    else if(!b1.isBlackTurn() && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
                        System.out.print("White's turn, try again: ");
                    }
                    else{
                        b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
                        move++;
                        System.out.println("\n");
                        b1.drawBoard();
                    }
                }
                else{
                    System.out.println("\n\nThinking...");
                    Board new_board = ai.minimax_decision(b1);
                    System.out.println("Computer has found a good move: ");
                    b1 = deepCopy(new_board);
                    new_board.drawBoard();
                }
            }
        }
    }
    public static void pvp(){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(size);
        b1.drawBoard();
        String m = "";
        int move = 1;
        while(!m.equals("quit")){
            if(b1.getWhitePosList().isEmpty()){
                System.out.println("Black wins!");
                System.exit(0);
            }
            else if(b1.getBlackPosList().isEmpty()){
                System.out.println("White wins!");
                System.exit(0);
            }
            else{
                b1.getChildren();
                System.out.println("Legal moves: " + clean(b1.getLegalMoves()));
                System.out.print("Enter a move: ");
                m = sc.next();
                if(m.equals("quit")) System.exit(0);
                else if(!isLegal(m,b1)){
                    System.err.println("That is not a legal move in the current position");
                    continue;
                }
                System.out.println(b1.getLegalMoves());
                String pieceStr = m.substring(0,2);
                if(b1.isBlackTurn() && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
                    System.out.print("Black's turn, try again: ");
                }
                else if(!b1.isBlackTurn() && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
                    System.out.print("White's turn, try again: ");
                }
                else{
                    b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
                    move++;
                    System.out.println("\n");
                    b1.drawBoard();
                }
            }
            b1.clearMoves();
        }
    }
    public static void printBoardState(Piece[][] board){
        for (int r = 0; r<4; r++){
            for (int c = 0; c<4; c++){
                if(board[r][c] == null){
                    System.out.print(" null ");
                }else{
                    System.out.print(board[r][c].getX() + "," + board[r][c].getY());
                }
            }
            System.out.println();
        }
    }
    public static ArrayList<Point> deepListSet(ArrayList<Point> list){
        ArrayList<Point> res = new ArrayList<Point>();
        int len = list.size();
        for(int i = 0; i<len; i++){
            res.add(list.get(i));
        }
        return res;
    }  
    /*
    *Test whether a move on a given board state is legal
    */
    public static boolean isLegal(String str, Board b){
        b.getChildren();
        boolean capture = false;
        ArrayList<ArrayList<String>> list = b.getLegalMoves();
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<list.get(i).size(); j++){
                if(list.get(i).get(j).contains("x")){
                    //System.out.println("Capture is true ObjLongConsumer: " + list.get(i).get(j));
                    capture = true;
                }
            }
        }
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<list.get(i).size(); j++){
                if(list.get(i).get(j).contains(str) && (capture ^ str.contains("-"))){
                    return true;
                }
            }
        }
        return false;
    }
    public static ArrayList<ArrayList<String>> clean(ArrayList<ArrayList<String>> list){
        boolean capture = false;
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<list.get(i).size(); j++){
                if(list.get(i).get(j).contains("x")){
                    //System.out.println("Capture is true ObjLongConsumer: " + list.get(i).get(j));
                    capture = true;
                }
            }
        }
        if(capture){
            for(int i = 0; i<list.size(); i++){
                for(int j = 0; j<list.get(i).size(); j++){
                    if(list.get(i).get(j).contains("-")){
                        list.get(i).remove(j);
                    }
                }
            }
        }
        return list;
    }
    public static Board deepCopy(Board b){
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
        res.setBlackTurn(b.isBlackTurn());
        res.setUtilValue(b.getUtilValue());
        res.silentDrawBoard();
        return res;
    }
    
}