import java.util.ArrayList;
import java.util.Scanner;
public class Start {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        final int size = 4;
        Board b1 = new Board(size);
        b1.drawBoard();
        //loadInitialBoard(b1, size);
        String m = "";
        int move = 1;

        //getChildren test
        ArrayList<Board> children = b1.getChildren();
        for(int i = 0; i<children.size(); i++){
            children.get(i).drawBoard();
        }
        
        // while(!m.equals("quit")){
        //     if(b1.getWhitePosList().isEmpty()){
        //         System.out.println("Black wins!");
        //         System.exit(0);
        //     }
        //     else if(b1.getBlackPosList().isEmpty()){
        //         System.out.println("White wins!");
        //         System.exit(0);
        //     }
        //     else{
        //         System.out.print("Enter a move: ");
        //         m = sc.next();
        //         if(m.equals("quit")) System.exit(0);
        //         String pieceStr = m.substring(0,2);
        //         System.out.println("sub is " + pieceStr);
        //         if(b1.isBlackTurn() && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
        //             System.out.print("Black's turn, try again: ");
        //         }
        //         else if(!b1.isBlackTurn() && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
        //             System.out.print("White's turn, try again: ");
        //         }
        //         else{
        //             b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
        //             move++;
        //             System.out.println("\n");
        //             b1.drawBoard();
        //         }
        //         // String pieceStr = m.substring(0,2);
        //         // System.out.println("sub is " + pieceStr);
        //         // if(move%2 != 0 && !b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){//white's turn
        //         //     System.out.print("Black's turn, try again: ");
        //         // }
        //         // else if(move%2 == 0 && b1.getPiece(b1.cordToPoint(pieceStr)).getSide().equals("black")){
        //         //     System.out.print("White's turn, try again: ");
        //         // }
        //         // else{
        //         //     b1.move(m, b1.getWhitePosList(), b1.getWhitePosList());
        //         //     move++;
        //         //     System.out.println("\n");
        //         //     b1.drawBoard();
        //         // }
        //     }
        // }
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

    

    
}