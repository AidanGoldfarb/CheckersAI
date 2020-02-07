import java.util.ArrayList;
import java.util.Scanner;
public class Start {
    final static int size = 4;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        
        // Board b = new Board(size);
        // b.drawBoard();
        
        ArrayList<Point> list = new ArrayList<Point>();
        Board b2 = new Board(size);
        b2.drawBoard();

        MinimaxDL m = new MinimaxDL();
        System.out.println(m.isTerminal(b2));
        // b.move("A4-B3",b.getWhitePosList(),b.getBlackPosList());
        // b.drawBoard();
        // b.move("D3-C4", b.getWhitePosList(),b.getBlackPosList());
        // b.drawBoard();
        // b.move("A2-B1", b.getWhitePosList(),b.getBlackPosList());
        // b.drawBoard();
        // //printBoardState(b.getBoard());
        // ArrayList<Board> list = b.getChildren();
        // for(int i = 0; i<list.size(); i++){
        //     list.get(i).drawBoard();
        //     //printBoardState(list.get(i).getBoard());
        // }

        //b.setBlackTurn(false); //makes it white(X) to move first

        // MinimaxAI ai = new MinimaxAI(); //initialize minimax object
        // Board new_board = ai.minimax_decision(b); 
        // new_board.drawBoard(); //draws board, and updates board[][] values 
        // printBoardState(new_board.getBoard());

        // Board new_board2 = ai.minimax_decision(new_board); //same thing, attempting to use deepCopy
        // new_board2.drawBoard(); //draw and update
        // printBoardState(new_board2.getBoard()); //iterates through board[][], prints null if no piece is found, otherwise prints the point
        // System.out.println(new_board2.getBlackPosList());
        
        //pvc();
        // System.out.print("0 for PvP, 1 for PvC: ");
        // int input = sc.nextInt();
        // if(input == 0){
        //     pvp();
        // }
        // else if(input == 1){
        //     pvc();
        // }


    }

    public static void pvc(){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(size);
        //MinimaxAI ai = new MinimaxAI();
        MinimaxDL ai = new MinimaxDL();
        b1.silentDrawBoard();
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
                if(b1.isBlackTurn()){
                    System.out.print("Enter a move: ");
                    m = sc.next();
                    if(m.equals("quit")) System.exit(0);
                    // else if(!isLegal(m,b1)){
                    //     System.err.println("That is not a legal move in the current position");
                    //     continue;
                    // }
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
                    Board new_board = ai.minimax_decision(b1);
                    b1 = deepCopy(new_board);
                    new_board.drawBoard();
                    //b1.drawBoard();
                }
            }
        }
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
        // res.setWhitePosList(b.getWhitePosList());
        // res.setBlackPosList(b.getBlackPosList());
        res.setBlackTurn(b.isBlackTurn());
        res.silentDrawBoard();
        return res;
    }

    public static void pvp(){
        Scanner sc = new Scanner(System.in);
        Board b1 = new Board(4);
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
                System.out.print("Enter a move: ");
                m = sc.next();
                if(m.equals("quit")) System.exit(0);
                String pieceStr = m.substring(0,2);
                //System.out.println("sub is " + pieceStr);
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
        }
    }

    public static void loadInitialBoard(Board b, int dim){
        if(dim == 4){
            ArrayList<Point> wPosList = new ArrayList<Point>();
            ArrayList<Point> bPosList = new ArrayList<Point>();
            //BLACK: 0,1 and 0,3 WHITE: 3,0 and 3,2
            Point w1 = new Point(3,0);
            Point w2 = new Point(3,2);
            Point b1 = new Point(0,1);
            Point b2 = new Point(0,3);
            // Piece white1 = new Piece("white", w1.getX(), w1.getY());
            // Piece white2 = new Piece("white", w2.getX(), w2.getY());
            // Piece black1 = new Piece("black", b1.getX(), b1.getY());
            // Piece black2 = new Piece("black", b2.getX(), b2.getY());
            // Piece [][] board = new Piece [4][4];
            // board[3][0] = white1;
            // board[3][2] = white2;
            // board[0][1] = black1;
            // board[0][3] = black2;
            // b.setBoard(board);
            wPosList.add(w1);
            wPosList.add(w2);
            bPosList.add(b1);
            bPosList.add(b2);
            b.setWhitePosList(wPosList);
            b.setBlackPosList(bPosList);
            b.drawBoard();
        }
        else if(dim == 8){
            ArrayList<Point> wPosList = new ArrayList<Point>();
            ArrayList<Point> bPosList = new ArrayList<Point>();
            for(int i = 0; i<dim; i++){
                for(int j = 0; j<dim; j++){
                    if(i<=2){
                        if(i%2==0 && j%2!=0){
                            Point p = new Point(i,j);//place black piece
                            bPosList.add(p);
                        }
                        else if(i%2!=0 && j%2==0){
                            Point p = new Point(i,j);//place black piece
                            bPosList.add(p);
                        }
                    }
                    else if(i>=5){
                        if(i%2!=0 && j%2==0){
                            Point p = new Point(i,j);//place black piece
                            wPosList.add(p);
                        }
                        else if(i%2==0 && j%2!=0){
                            Point p = new Point(i,j);//place black piece
                            wPosList.add(p);
                        }
                    }
                }
            }
            b.setWhitePosList(wPosList);
            b.setBlackPosList(bPosList);
            b.drawBoard();
        }
    }

    public static void loadTestBoard(Board b, int dim){
        if(dim == 4){
            ArrayList<Point> wPosList = new ArrayList<Point>();
            ArrayList<Point> bPosList = new ArrayList<Point>();
            Point w1 = new Point(0,1);
            Point b1 = new Point(1,2);
            //Point b2 = new Point(0,1);

            wPosList.add(w1);
            bPosList.add(b1);
            //bPosList.add(b2);

            // Piece white1 = new Piece("white", w1.getX(), w1.getY());
            // white1.setIsKing(true);
            // Piece black1 = new Piece("black", b1.getX(), b1.getY());
            // Piece [][] board = new Piece [4][4];
            // board[0][0] = white1;
            // board[2][1] = black1;
            // b.setBoard(board);
            b.setWhitePosList(wPosList);
            b.setBlackPosList(bPosList);
            b.setWhiteKingList(wPosList);
            //b.setBlackKingList(bPosList);

            b.drawBoard();
        }
        else{
            ArrayList<Point> wPosList = new ArrayList<Point>();
            ArrayList<Point> bPosList = new ArrayList<Point>();
            Point w1 = new Point(2,0);
            Point b1 = new Point(1,1);
            Point b2 = new Point(1,3);

            wPosList.add(w1);
            bPosList.add(b1);
            bPosList.add(b2);

            b.setWhitePosList(wPosList);
            b.setBlackPosList(bPosList);

            b.drawBoard();
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
    
    public static boolean isLegal(String str, Board b){
        b.getChildren();
        ArrayList<ArrayList<String>> list = b.getLegalMoves();
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<list.get(i).size(); j++){
                if(list.get(i).get(j).contains(str)){
                    return true;
                }
            }
        }
        return false;
    }
    
}