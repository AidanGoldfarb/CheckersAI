import java.util.ArrayList;

public class Board {
    private final int DIM;
    private Piece [][] board; 
    private ArrayList<Point> whitePosList;
    private ArrayList<Point> blackPosList;

public Board(int size){
        DIM = size;
        board = new Piece [DIM][DIM]; 
        whitePosList = new ArrayList<Point>();
        blackPosList = new ArrayList<Point>();
    }

    /*
    draws board given two arrays. each contain points where pieces are
    */
    public void drawBoard(){
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
                    if(pt.isInList(whitePosList)){
                        Piece p = new Piece("white", x, y);
                        p.setX(x);
                        p.setY(y);
                        board[x][y] = p;
                        //currentPieces.add(pt);
                        System.out.print(p + "  ");
                    }else if(pt.isInList(blackPosList)){
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
    
    /*
    Given a point, returns the piece from board if exists, null if tile is empty
    */
    public Piece getPiece(Point p){
        if(board[p.getX()][p.getY()] == null){
            System.out.println("returning null");
            return null;
        }
        else{
            return board[p.getX()][p.getY()];
        }
    }

    /*
     *Takes string as parameter. (e.g. Dc2) and updates board to move piece from D rank to c2 (2,1), if legal
     */
    public void move(String str, ArrayList<Point> wPosList, ArrayList<Point> bPosList){
        String [] parts = str.split("-"); 
        if(parts.length != 2){
            System.out.println("ERROR(move) | USAGE");
            System.exit(0);
        }
        Point p1 = cordToPoint(parts[0]);
        Point p2 = cordToPoint(parts[1]);
        if(getPiece(p1) != null && isMoveLegal(getPiece(p1), p2)){ //there is a piece on p1 and it can move to p2
            //put getpiece(p1) to p2, set p1 to null
            Point p3 = getPiece(p1).getPoint();
            if(getPiece(p1).getSide().equals(("white"))){
                whitePosList.remove(p3);
                getPiece(p1).setX(p2.getX());
                getPiece(p1).setY(p2.getY());
                whitePosList.add(p2);
            }
            else{
                whitePosList.remove(p3);
                getPiece(p1).setX(p2.getX());
                getPiece(p1).setY(p2.getY());
                whitePosList.add(p2);
            }
            

        }
        else{
            System.out.println("Invalid move");
        }
    }
    /*
     *Give a string of a coordinate (e.g. B2), returns a point with the appropriate values 
     */
    public Point cordToPoint(String s){
        //A = 65, Z = 90
        if(s.length() != 2){
            System.out.println("ERROR(cordToPoint) | USAGE");
            return null;
        }
        int x  = ((int)s.charAt(0)) - 65;
        int y = Integer.parseInt(s.charAt(1)+"");
        return new Point(x,y-1);

    }

    public ArrayList<Point> getWhitePosList() {
        return this.whitePosList;
    }
    public void setWhitePosList(ArrayList<Point> whitePosList) {
        this.whitePosList = whitePosList;
    }
    public ArrayList<Point> getBlackPosList() {
        return this.blackPosList;
    }
    public void setBlackPosList(ArrayList<Point> blackPosList) {
        this.blackPosList = blackPosList;
    }
}