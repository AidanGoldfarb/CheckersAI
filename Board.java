import java.util.ArrayList;

public class Board {
    private final int DIM;
    private Piece [][] board; 
    ArrayList<Point> currentPieces;
    //ArrayList<Point>

    public Board(int size){
        DIM = size;
        board = new Piece [DIM][DIM]; 
    }

    public void drawBoard(){
        int num = 0;
        int i_count = 0, j_count = 0;
        for(int i = 0; i<2*DIM+1; i++){
            for(int j = 0; j<2*DIM+1; j++){
                if(i%2==0 && j%2==0){
                    System.out.print("+");
                }
                else if(i%2==0){
                    System.out.print("----");
                }
                else if(i%2!=0 && j%2==0){
                    System.out.print("| ");
                }
                else{

                    if(num >= (DIM*DIM)-DIM && num%2 == 0){
                        board[i_count][j_count] = new Piece("white", i_count, j_count);
                        System.out.print(board[i_count][j_count] + "  ");
                    }
                    else if(num < DIM && num%2 != 0){
                        board[i_count][j_count] = new Piece("black", i_count, j_count);
                        System.out.print(board[i_count][j_count] + "  ");
                    }
                    else{
                        System.out.print("   ");
                    }
                    j_count++;
                    num++;
                    
                }
            }
            if(j_count == DIM){
                i_count++;
                j_count = 0;
            }
            System.out.println();
        }
    }

    public void drawBoard(ArrayList<Point> wPosList, ArrayList<Point> bPosList){
        int x = 0, y = 0;
        for(int i = 0; i<=2*DIM; i++){
            for(int j = 0; j<=2*DIM; j++){
                if(i%2==0 && j%2==0){
                    System.out.print("+");
                }
                else if(i%2==0){
                    System.out.print("----");
                }
                else if(i%2!=0 && j%2==0){
                    System.out.print("| ");
                }
                else{                   
                    //place piece at 0,1
                    x = (i-1)/2;
                    y = (j-1)/2;
                    Point pt = new Point(x,y);
                    if(pt.isInList(wPosList)){
                        Piece p = new Piece("white", x, y);
                        p.setX(x);
                        p.setY(y);
                        board[x][y] = p;
                        //currentPieces.add(pt);
                        System.out.print(p + "  ");
                    }else if(pt.isInList(bPosList)){
                        Piece p = new Piece("black", x, y);
                        p.setX(x);
                        p.setY(y);
                        board[x][y] = p;
                        //currentPieces.add(pt);
                        System.out.print(p + "  ");
                    }else{
                        System.out.print("   ");
                    }
                }
            }
            
            System.out.println();
        }
    }
    /*
     * Returns true if the given x,y coordinates contain a piece
     */
    public boolean isOccupied(int x, int y){
        if(x >= DIM || y >= DIM){
            System.out.println("OUT OF RANGE");
            return false;
        }
        if(board[x][y] == null){
            return false;
        }
        return true;
    }

    /*
     *Given a piece and a potential point, returns true if it is legal for the given piece to move to the given point
     */
    public boolean isMoveLegal(Piece piece, Point point){ 
        //TODO edgecase (0,0) - 1 = -1??
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        String side = piece.getSide();
        int potX = point.getX();
        int potY = point.getY();
        if(side.equals("white")){//WHITE
            if(pieceX == 0 && pieceY == 0){
                return false;
            }
            else if(pieceX == 0){
               return false;
            }
            else if(pieceY == 0){
                 //just check j+1
                 int newX = pieceX-1;
                 int newY = pieceY+1;
                 if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                     return true;
                 }
                 else{
                     return false;
                 }
            }
            else if(pieceY == DIM-1){
                 //just check j-1
                 int newX = pieceX-1;
                 int newY = pieceY-1;
                 if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                     return true;
                 }
                 else{
                     return false;
                 }
            }
            else{
                //check i-1 and j-1 AND j+1
                int newX = pieceX-1;
                int newY1 = pieceY-1;
                int newY2 = pieceY+1;
                if(!isOccupied(newX, newY1) && (newX == potX && newY1 == potY)){
                    return true;
                }
                else if(!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{//BLACK
            //check i+1 and j-1 AND j+1
            if(pieceX == DIM-1 && pieceY == DIM-1){
                return false;
            }
            else if(pieceX == DIM-1){
                return false;
            }
            else if(pieceY == 0){
                //just check j+1
                int newX = pieceX+1;
                int newY = pieceY+1;
                if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(pieceY == DIM-1){
                //just check j-1
                int newX = pieceX+1;
                int newY = pieceY-1;
                if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                int newX = pieceX+1;
                int newY1 = pieceY-1;
                int newY2 = pieceY+1;
                if(!isOccupied(newX, newY1) && (newX == potX && newY1 == potY)){
                    return true;
                }
                else if(!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }
    
    public Piece getPiece(Point p){
        if(board[p.getX()][p.getY()] == null){
            System.out.println("returning null");
            return null;
        }
        else{
            return board[p.getX()][p.getY()];
        }
    }
}