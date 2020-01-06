import java.util.ArrayList;

public class Board {
    private final int DIM;
    private Piece [][] board; 
    private ArrayList<Point> whitePosList;
    private ArrayList<Point> blackPosList;
    private ArrayList<Point> whiteKingList;
    private ArrayList<Point> blackKingList;
    private ArrayList<Point> jumpedPiece;

public Board(int size){
        DIM = size;
        board = new Piece [DIM][DIM]; 
        whitePosList = new ArrayList<Point>();
        blackPosList = new ArrayList<Point>();
        whiteKingList = new ArrayList<Point>();
        blackKingList = new ArrayList<Point>();
        jumpedPiece = new ArrayList<Point>();
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
                    x = (i-1)/2;
                    y = (j-1)/2;
                    Point pt = new Point(x,y);
                    if(pt.isInList(whitePosList)){
                        if(board[x][y] == null && !pt.isInList(whiteKingList)){
                            Piece p = new Piece("white", x, y);
                            p.setX(x);
                            p.setY(y);
                            board[x][y] = p;
                            System.out.print(p + " ");
                        }
                        else if(pt.isInList(whiteKingList)){
                            Piece p = new Piece("white", x, y);
                            p.setX(x);
                            p.setY(y);
                            p.setIsKing(true);
                            board[x][y] = p;
                            System.out.print(p + " ");
                        }
                        else{
                            System.out.print(getPiece(new Point(x,y)) + " ");
                        }
                        
                        
                    }else if(pt.isInList(blackPosList)){
                        if(board[x][y] == null && !pt.isInList(blackKingList)){
                            Piece p = new Piece("black", x, y);
                            p.setX(x);
                            p.setY(y);
                            board[x][y] = p;
                            //currentPieces.add(pt);
                            System.out.print(p + " ");
                        }
                        else if(pt.isInList(blackKingList)){
                            Piece p = new Piece("black", x, y);
                            p.setX(x);
                            p.setY(y);
                            p.setIsKing(true);
                            board[x][y] = p;
                            System.out.print(p + " ");
                        }
                        else{
                            System.out.print(getPiece(new Point(x,y)) + " ");
                        }
                    }else{
                        // x = (i-1)/2;
                        // y = (j-1)/2;
                        // String temp = pointToCord(new Point(x,y));
                        System.out.print("   "); //print coordinate?
                        //System.out.print(temp); //print coordinate?
                    }
                }
            }
            
            System.out.println();
        }
    }

    /*
     *
     */
     public String pointToCord(Point p){
         //0 = A, 1 = B....
         int x = p.getX();
         int y = p.getY();
         String f = (char)(x+65) + ""; 
         String  l = (y+1) + " ";
         return f+l;
     }

    /*
    assigns correct values without printing anything
    */
    public void silentDrawBoard(){
        int x = 0, y = 0;
        for(int i = 0; i<=2*DIM; i++){
            for(int j = 0; j<=2*DIM; j++){
                if(i%2==0 && j%2==0){
                    //System.out.print("+");
                }
                else if(i%2==0){
                    //System.out.print("----");
                }
                else if(i%2!=0 && j%2==0){
                    //System.out.print("| ");
                }
                else{                   
                    x = (i-1)/2;
                    y = (j-1)/2;
                    Point pt = new Point(x,y);
                    if(pt.isInList(whitePosList)){
                        if(board[x][y] == null && !pt.isInList(whiteKingList)){
                            Piece p = new Piece("white", x, y);
                            p.setX(x);
                            p.setY(y);
                            board[x][y] = p;
                            //System.out.print(p + " ");
                        }
                        else if(pt.isInList(whiteKingList)){
                            Piece p = new Piece("white", x, y);
                            p.setX(x);
                            p.setY(y);
                            p.setIsKing(true);
                            board[x][y] = p;
                            //System.out.print(p + " ");
                        }
                        else{
                            System.out.print(getPiece(new Point(x,y)) + " ");
                        }
                        
                        
                    }else if(pt.isInList(blackPosList)){
                        if(board[x][y] == null && !pt.isInList(blackKingList)){
                            Piece p = new Piece("black", x, y);
                            p.setX(x);
                            p.setY(y);
                            board[x][y] = p;
                            //currentPieces.add(pt);
                            //System.out.print(p + " ");
                        }
                        else if(pt.isInList(blackKingList)){
                            Piece p = new Piece("black", x, y);
                            p.setX(x);
                            p.setY(y);
                            p.setIsKing(true);
                            board[x][y] = p;
                            //System.out.print(p + " ");
                        }
                        else{
                            //System.out.print(getPiece(new Point(x,y)) + " ");
                        }
                    }else{
                        // x = (i-1)/2;
                        // y = (j-1)/2;
                        // String temp = pointToCord(new Point(x,y));
                        //System.out.print("   "); //print coordinate?
                        //System.out.print(temp); //print coordinate?
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
        if(board[x][y] != null){
            return true;
        }
        return false;
    }

    /*
     *Given an int, returns true if int is in range of the dimentions of the board
     */
    public boolean inRange(int n){
        if(n>=DIM || n<0){
            return false;
        }
        return true;
    }

    /*
     *Given a piece, says whether it has any legal captures
         returns valid capture point in future... 
     */
    public Point canCapture(Piece piece){
        //TODO add cancaptures clauses for king's
        silentDrawBoard();
        //printBoardState(board);
        System.out.println();
        if(piece == null){
            System.out.println("piece == null in canCapture");
            System.exit(0);
        }
        Point p;
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        String side = piece.getSide();
        // int potX = point.getX();
        // int potY = point.getY();
        if(!piece.getIsKing()){//not King piece
            if(side.equals("white")){//WHITE
                if(pieceX <= 1){
                    return null;
                }
                else if(pieceY == 0){
                    //just check (x-1,1) and (x-2,2)
                    if(isOccupied(pieceX-1, pieceY+1) && !isOccupied(pieceX-2, pieceY+2)){
                        if(inRange(pieceX-2) && inRange(pieceY+2)){
                            jumpedPiece.add(new Point((pieceX-1), (pieceY+1)));
                            return p = new Point(pieceX-2, pieceY+2);
                        }
                    }
                }
                else if(pieceY == DIM-1){
                    //just check (x-1,DIM-2) and (x-2,DIM-3)
                    if(isOccupied(pieceX-1, pieceY-1) && !isOccupied(pieceX-2, pieceY-2)){
                        if(inRange(pieceX-2) && inRange(pieceY-2)){
                            jumpedPiece.add(new Point(pieceX-1, pieceY-1)); //remove jumped piece
                            return p = new Point(pieceX-2, pieceY-2);
                        }
                    }
                }
                else{
                    //check both directions
                    if((isOccupied(pieceX-1, pieceY+1) && !isOccupied(pieceX-2, pieceY+2)) ){
                        //System.out.println("Removing (" + (pieceX-1) + "," + (pieceY+1) + ")");
                        //removePointFromList(new Point((pieceX-1), (pieceY+1)) ,blackPosList); //remove jumped piece
                        if(inRange(pieceX-2) && inRange(pieceY+2)){
                            jumpedPiece.add(new Point((pieceX-1), (pieceY+1)));
                            return p = new Point(pieceX-2, pieceY+2);
                        }
                    }
                    else if((isOccupied(pieceX-1, pieceY-1) && !isOccupied(pieceX-2, pieceY-2))){
                        //removePointFromList(new Point(pieceX-1, pieceY-1) ,blackPosList); //remove jumped piece
                        if(inRange(pieceX-2) && inRange(pieceY-2)){
                            jumpedPiece.add(new Point((pieceX-1), (pieceY-1)));
                            return p = new Point(pieceX-2, pieceY-2);
                        }
                    }
                    else{
                        return null;
                    }
                }
            }
            else{//BLACK
                if(pieceX >= DIM-2){ 
                    return null;
                }

                else if(pieceY == 0){
                    if(isOccupied(pieceX+1, pieceY+1) && !isOccupied(pieceX+2, pieceY+2)){
                        //removePointFromList(new Point(pieceX+1, pieceY+1) ,whitePosList); //remove jumped piece
                        if(inRange(pieceX+2) && inRange(pieceY+2)){
                            jumpedPiece.add(new Point((pieceX+1), (pieceY+1)));
                            return new Point(pieceX+2, pieceY+2);
                        }
                    }
                }

                else if(pieceY == DIM-1){
                    if(isOccupied(pieceX+1, pieceY-1) && !isOccupied(pieceX+2, pieceY-2)){
                        //removePointFromList(new Point(pieceX+1, pieceY-1) ,whitePosList); //remove jumped piece
                        if(inRange(pieceX+2) && inRange(pieceY-2)){
                            jumpedPiece.add(new Point((pieceX+1), (pieceY-1)));
                            return new Point(pieceX+2, pieceY-2);
                        }
                    }
                }

                else{
                    if((isOccupied(pieceX+1, pieceY+1) && !isOccupied(pieceX+2, pieceY+2)) ){
                        //removePointFromList(new Point(pieceX+1, pieceY+1) ,whitePosList); //remove jumped piece
                        if(inRange(pieceX+2) && inRange(pieceY+2)){
                            jumpedPiece.add(new Point((pieceX+1), (pieceY+1)));
                            return new Point(pieceX+2, pieceY+2);
                        }
                    }
                    else if((isOccupied(pieceX+1, pieceY-1) && !isOccupied(pieceX+2, pieceY-2))){
                        //removePointFromList(new Point(pieceX+1, pieceY-1) ,whitePosList); //remove jumped piece
                        if(inRange(pieceX-2) && inRange(pieceY+2)){
                            jumpedPiece.add(new Point((pieceX-1), (pieceY-1)));
                            return new Point(pieceX+2, pieceY-2);
                        }
                    }
                    else{
                        return null;
                    }
                }
            }
        }else{//King piece
            if(side.equals("white")){//WHITE KING
                if(pieceX == 0 && pieceY == 0){//top left
                    int x1 = pieceX+1;
                    int y1 = pieceY+1;
                    int x2 = pieceX+2;
                    int y2 = pieceY+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == 0 && pieceY == DIM-1){//top right
                    System.out.println("%canCapture: HERE");
                    int x1 = pieceX+1;
                    int y1 = pieceY-1;
                    int x2 = pieceX+2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        //removePointFromList(new Point(x1,y1) ,blackPosList); //removed jumped piece
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == DIM-1 && pieceY == DIM-1){//bottom right
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == DIM-1 && pieceY == 0){//bottom left
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == 0){//top row (potential out of bounds issues)
                    int x1 = pieceX+1;
                    int y1 = pieceY+1;
                    int x2 = pieceX+2;
                    int y2 = pieceY+2;

                    int y3 = pieceY-1;
                    int y4 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x1, y3) && !isOccupied(x2, y4)){
                        if(inRange(x2) && inRange(y4)){
                            jumpedPiece.add(new Point((x1), (y3)));
                            return new Point(x2,y4);
                        }
                    }
                }
                else if(pieceX == DIM-1){//bottom row
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;

                    int y3 = pieceY-1;
                    int y4 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x2), (y2)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x1, y3) && !isOccupied(x2, y4)){
                        if(inRange(x2) && inRange(y4)){
                            jumpedPiece.add(new Point((x1), (y3)));
                            return new Point(x2,y4);
                        }
                    }
                }
                else if(pieceY == 0){//left column
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;

                    int x3 = pieceX+1;
                    int x4 = pieceX+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x3, y1) && !isOccupied(x4, y2)){
                        if(inRange(x4) && inRange(y2)){
                            jumpedPiece.add(new Point((x3), (y1)));
                            return new Point(x4,y2);
                        }
                    }
                }
                else if(pieceY == DIM-1){//right column
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;

                    int x3 = pieceX+1;
                    int x4 = pieceX+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x3, y1) && !isOccupied(x4, y2)){
                        if(inRange(x4) && inRange(y2)){
                            jumpedPiece.add(new Point((x3), (y1)));
                            return new Point(x4,y2);
                        }
                    }
                }
                else{//else
                    //top left
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }

                    //top right
                    int x3 = pieceX-1;
                    int y3 = pieceY+1;
                    int x4 = pieceX-2;
                    int y4 = pieceY+2;
                    if(isOccupied(x3, y3) && !isOccupied(x4, y4)){
                        if(inRange(x4) && inRange(y4)){
                            jumpedPiece.add(new Point((x3), (y3)));
                            return new Point(x4,y4);
                        }
                    }

                    //bottom left
                    int x5 = pieceX+1;
                    int y5 = pieceY-1;
                    int x6 = pieceX+2;
                    int y6 = pieceY-2;
                    if(isOccupied(x5, y5) && !isOccupied(x6, y6)){
                        if(inRange(x6) && inRange(y6)){
                            jumpedPiece.add(new Point((x5), (y5)));
                            return new Point(x6,y6);
                        }
                    }

                    //bottom right
                    int x7 = pieceX+1;
                    int y7 = pieceY+1;
                    int x8 = pieceX+2;
                    int y8 = pieceY+2;
                    if(isOccupied(x7, y7) && !isOccupied(x8, y8)){
                        if(inRange(x8) && inRange(y8)){
                            jumpedPiece.add(new Point((x7), (y7)));
                            return new Point(x8,y8);
                        }
                    }
                    return null;
                }
            }

            else{//BLACK KING 
                if(pieceX == 0 && pieceY == 0){//top left
                    int x1 = pieceX+1;
                    int y1 = pieceY+1;
                    int x2 = pieceX+2;
                    int y2 = pieceY+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == 0 && pieceY == DIM-1){//top right
                    System.out.println("%canCapture: HERE");
                    int x1 = pieceX+1;
                    int y1 = pieceY-1;
                    int x2 = pieceX+2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        //removePointFromList(new Point(x1,y1) ,blackPosList); //removed jumped piece
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == DIM-1 && pieceY == DIM-1){//bottom right
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == DIM-1 && pieceY == 0){//bottom left
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                }
                else if(pieceX == 0){//top row (potential out of bounds issues)
                    int x1 = pieceX+1;
                    int y1 = pieceY+1;
                    int x2 = pieceX+2;
                    int y2 = pieceY+2;

                    int y3 = pieceY-1;
                    int y4 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x1, y3) && !isOccupied(x2, y4)){
                        if(inRange(x2) && inRange(y4)){
                            jumpedPiece.add(new Point((x1), (y3)));
                            return new Point(x2,y4);
                        }
                    }
                }
                else if(pieceX == DIM-1){//bottom row
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;

                    int y3 = pieceY-1;
                    int y4 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x1, y3) && !isOccupied(x2, y4)){
                        if(inRange(x2) && inRange(y4)){
                            jumpedPiece.add(new Point((x1), (y3)));
                            return new Point(x2,y4);
                        }
                    }
                }
                else if(pieceY == 0){//left column
                    int x1 = pieceX-1;
                    int y1 = pieceY+1;
                    int x2 = pieceX-2;
                    int y2 = pieceY+2;

                    int x3 = pieceX+1;
                    int x4 = pieceX+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x3, y1) && !isOccupied(x4, y2)){
                        if(inRange(x4) && inRange(y2)){
                            jumpedPiece.add(new Point((x3), (y1)));
                            return new Point(x4,y2);
                        }
                    }
                }
                else if(pieceY == DIM-1){//right column
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;

                    int x3 = pieceX+1;
                    int x4 = pieceX+2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }
                    else if(isOccupied(x3, y1) && !isOccupied(x4, y2)){
                        if(inRange(x4) && inRange(y2)){
                            jumpedPiece.add(new Point((x3), (y1)));
                            return new Point(x4,y2);
                        }
                    }
                }
                else{//else
                    //top left
                    int x1 = pieceX-1;
                    int y1 = pieceY-1;
                    int x2 = pieceX-2;
                    int y2 = pieceY-2;
                    if(isOccupied(x1, y1) && !isOccupied(x2, y2)){
                        if(inRange(x2) && inRange(y2)){
                            jumpedPiece.add(new Point((x1), (y1)));
                            return new Point(x2,y2);
                        }
                    }

                    //top right
                    int x3 = pieceX-1;
                    int y3 = pieceY+1;
                    int x4 = pieceX-2;
                    int y4 = pieceY+2;
                    if(isOccupied(x3, y3) && !isOccupied(x4, y4)){
                        if(inRange(x4) && inRange(y4)){
                            jumpedPiece.add(new Point((x3), (y3)));
                            return new Point(x4,y4);
                        }
                    }

                    //bottom left
                    int x5 = pieceX+1;
                    int y5 = pieceY-1;
                    int x6 = pieceX+2;
                    int y6 = pieceY-2;
                    if(isOccupied(x5, y5) && !isOccupied(x6, y6)){
                        if(inRange(x6) && inRange(y6)){
                            jumpedPiece.add(new Point((x5), (y5)));
                            return new Point(x6,y6);
                        }
                    }

                    //bottom right
                    int x7 = pieceX+1;
                    int y7 = pieceY+1;
                    int x8 = pieceX+2;
                    int y8 = pieceY+2;
                    if(isOccupied(x7, y7) && !isOccupied(x8, y8)){
                        if(inRange(x8) && inRange(y8)){
                            jumpedPiece.add(new Point((x7), (y7)));
                            return new Point(x8,y8);
                        }
                    }
                }    return null;
            }
        }
        System.out.println("%canCapture: failed at end");
        return null;
    }

    /*
     *Given a piece and a potential point, returns true if it is legal for the given piece to move to the given point
     */
    public boolean isMoveLegal(Piece piece, Point point){ 
        boolean isKing = piece.getIsKing();
        System.out.println("Piece at " + piece.getX() + "," + piece.getY() + " isKing = " + isKing);
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        String side = piece.getSide();
        int potX = point.getX();
        int potY = point.getY();
        if(!isKing){ //regular piece
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
        }else{//if piece is a king
            if(side.equals("white")){//WHITE
                if(pieceX == DIM-1 && pieceY == DIM-1){ //bottom right
                    //just check 1,1
                    int newX = pieceX-1;
                    int newY = pieceY-1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == DIM-1 && pieceY == 0){ //bottom left
                    //just check i+1,j-1
                    int newX = pieceX-1;
                    int newY = pieceY+1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0 && pieceY == DIM-1){ //top right
                    int newX = pieceX+1;
                    int newY = pieceY-1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }  
                else if(pieceX == 0 && pieceY == 0){ //top left
                    int newX = pieceX+1;
                    int newY = pieceY+1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceY == DIM-1){ //right column
                    //just check j+1 in both x directions
                    int newX = pieceX+1;
                    int newX2 = pieceX-1;
                    int newY = pieceY-1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX2, newY) && (newX2 == potX && newY == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceY == 0){ //left column
                    //just check j-1
                    int newX = pieceX-1;
                    int newX2 = pieceX+1;
                    int newY = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX2, newY) && (newX2 == potX && newY == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == DIM-1){ //bottom row
                    //check j+/-1
                    int newX = pieceX-1;
                    int newY = pieceY-1;
                    int newY2 = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0){ //top row
                    int newX = pieceX+1;
                    int newY = pieceY-1;
                    int newY2 = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    //check i-1 and j-1 AND j+1
                    int newX1 = pieceX+1;
                    int newX2 = pieceX-1;
                    int newY1 = pieceY+1;
                    int newY2 = pieceY-1;
                    if((!isOccupied(newX1, newY1) && (newX1 == potX && newY1 == potY)) || ((!isOccupied(newX2, newY1) && (newX2 == potX && newY1 == potY)))){ //x1,y1
                        return true;
                    }
                    else if((!isOccupied(newX1, newY2) && (newX1 == potX && newY2 == potY)) || ((!isOccupied(newX2, newY2) && (newX2 == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                
                }

            }

            else{//BLACK
                if(pieceX == DIM-1 && pieceY == DIM-1){ //bottom right
                    //just check 1,1
                    int newX = pieceX-1;
                    int newY = pieceY-1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == DIM-1 && pieceY == 0){ //bottom left
                    //just check i+1,j-1
                    int newX = pieceX-1;
                    int newY = pieceY+1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0 && pieceY == DIM-1){ //top right
                    int newX = pieceX+1;
                    int newY = pieceY-1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }           
                else if(pieceX == 0 && pieceY == 0){ //top left
                    int newX = pieceX+1;
                    int newY = pieceY+1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceY == DIM-1){ //right column
                    //just check j+1 in both x directions
                    int newX = pieceX+1;
                    int newX2 = pieceX-1;
                    int newY = pieceY-1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX2, newY) && (newX2 == potX && newY == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceY == 0){ //left column
                    //just check j-1
                    int newX = pieceX-1;
                    int newX2 = pieceX+1;
                    int newY = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX2, newY) && (newX2 == potX && newY == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == DIM-1){ //bottom row
                    //check j+/-1
                    int newX = pieceX-1;
                    int newY = pieceY-1;
                    int newY2 = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0){ //top row
                    int newX = pieceX+1;
                    int newY = pieceY-1;
                    int newY2 = pieceY+1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX, newY2) && (newX == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    //check i-1 and j-1 AND j+1
                    int newX1 = pieceX+1;
                    int newX2 = pieceX-1;
                    int newY1 = pieceY+1;
                    int newY2 = pieceY-1;
                    if((!isOccupied(newX1, newY1) && (newX1 == potX && newY1 == potY)) || ((!isOccupied(newX2, newY1) && (newX2 == potX && newY1 == potY)))){ //x1,y1
                        return true;
                    }
                    else if((!isOccupied(newX1, newY2) && (newX1 == potX && newY2 == potY)) || ((!isOccupied(newX2, newY2) && (newX2 == potX && newY2 == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                
                }
            }
        }
    }
    
    /*
    Given a point, returns the piece from board if exists, null if tile is empty
    */
    public Piece getPiece(Point p){
        //System.out.println("%getPiece: p: " + p.getX() + "," + p.getY());
        if(board[p.getX()][p.getY()] == null){
            System.out.println("returning null in getpiece on point: " + p);
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
        printBoardState(board);
        if(str.contains("-")){
            String [] parts = str.split("-"); 
            if(parts.length != 2){
                System.out.println("ERROR(move) | USAGE");
                System.exit(0);
            }
            Point p1 = cordToPoint(parts[0]);
            Point p2 = cordToPoint(parts[1]);
            if(getPiece(p1) != null && isMoveLegal(getPiece(p1), p2)){ //there is a piece on p1 and it can move to p2

                if(getPiece(p1).getSide().equals(("white"))){
                    if(getPiece(p1).getIsKing()){
                        removePointFromList(p1,whitePosList);
                        removePointFromList(p1,whiteKingList);
                        board[p1.getX()][p1.getY()] = null;
                        whiteKingList.add(p2);
                        whitePosList.add(p2);
                        silentDrawBoard();
                    }
                    else{
                        removePointFromList(p1,whitePosList);
                        board[p1.getX()][p1.getY()] = null;
                        whitePosList.add(p2);
                        silentDrawBoard();
                    }
                    
                    if(p2.getX() == 0){
                        board[p2.getX()][p2.getY()].setIsKing(true);
                        System.out.println("Piece at " + p2 + " set to king");
                        silentDrawBoard();
                    }
                }
                else{
                    if(getPiece(p1).getIsKing()){
                        removePointFromList(p1,blackPosList); //p3
                        removePointFromList(p1,blackKingList);
                        board[p1.getX()][p1.getY()] = null;
                        blackKingList.add(p2);
                        blackPosList.add(p2);
                        silentDrawBoard();
                    }
                    else{
                        removePointFromList(p1,blackPosList);
                        board[p1.getX()][p1.getY()] = null;
                        blackPosList.add(p2);
                        silentDrawBoard();
                    }
                    if(p2.getX() == DIM-1){
                        board[p2.getX()][p2.getY()].setIsKing(true);
                        System.out.println("Piece at " + p2 + " set to king");
                        silentDrawBoard();
                    }
                }
                

            }
            else{
                System.out.println("Invalid move");
            }
            // System.out.println("Nulling (" + p1.getX() + "," + p1.getY() + ")");
            // board[p1.getX()][p1.getY()] = null;
        }

        else if(str.contains("x")){
            String [] parts = str.split("x"); 
            if(parts.length != 2){
                System.out.println("ERROR(move) | USAGE");
                System.exit(0);
            }
            Point p1 = cordToPoint(parts[0]); //C2 -> 2,3
            Point p2 = cordToPoint(parts[1]); //D3 -> 3,4
            boolean keepJumping = true;
            if(canCapture(getPiece(p1)) == null){
                System.out.println("Invalid move can. Capture returned null");
                System.exit(0);
            }
            while(keepJumping){
                System.out.println((getPiece(p1) != null) + " " +  (canCapture(getPiece(p1)) != null));
                if(getPiece(p1) != null && canCapture(getPiece(p1)) != null){ 
                    System.out.println("HERE!");
                    boolean isPKing = getPiece(p1).getIsKing();
                        if(getPiece(p1).getSide().equals(("white"))){
                            Point p3 = canCapture(getPiece(p1)); //new point after jump -> 0,1
                            System.out.println("P1: " + p1);
                            System.out.println("P2: " + p2);
                            System.out.println("P3: " + p3);
                            if(p3.getX() == 0 && !getPiece(p1).getIsKing()){
                                isPKing = true;
                                System.out.println("%move: Piece at " + p3 + " set to king");
                            }
                            System.out.println("%move: Removing " + p1 + " and " + p2);
                            board[p1.getX()][p1.getY()] = null;
                            board[p2.getX()][p2.getY()] = null; //removed jumped piece
                            removePointFromList(p1,whitePosList);
                            removePointFromList(p1,whiteKingList);
                            removePointFromList(p2,blackPosList);
                            removePointFromList(p2,blackKingList);
                            for(int i = 0; i<jumpedPiece.size(); i++){
                                removePointFromList(jumpedPiece.get(i), blackPosList);
                                jumpedPiece.clear();
                            }
                            if(isPKing){
                                whiteKingList.add(p3);
                                whitePosList.add(p3);
                            }
                            else{
                                whitePosList.add(p3);
                            }
                            silentDrawBoard();
                            if(canCapture(getPiece(p3)) == null){
                                System.out.println("getPiece at " + p3 + " is " + getPiece(p3));
                                System.out.println("cancaptures=null on piece at " + p3 + " is " + (canCapture(getPiece(p3)) == null));
                                System.out.println("%move: Setting keepJump to false");
                                keepJumping = false;
                            }
                            else{
                                p1 = p3; //new start state is old last state
                            }

                        }
                        else{ //BLACK
                            Point p3 = canCapture(getPiece(p1)); //new point after jump
                            System.out.println(p3);
                            if(p3.getX() == DIM-1 && !getPiece(p1).getIsKing()){
                                isPKing = true;
                                //board[p3.getX()][p3.getY()].setIsKing(true);
                                System.out.println("%move: Piece at " + p3 + " set to king");
                            }
                            board[p1.getX()][p1.getY()] = null;
                            board[p2.getX()][p2.getY()] = null;
                            removePointFromList(p1,blackPosList);
                            removePointFromList(p1,blackKingList);
                            removePointFromList(p2,whitePosList);
                            removePointFromList(p2,whiteKingList);
                            for(int i = 0; i<jumpedPiece.size(); i++){
                                removePointFromList(jumpedPiece.get(i), whitePosList);
                                jumpedPiece.clear();
                            }
                            if(isPKing){
                                blackKingList.add(p3);
                                blackPosList.add(p3);
                            }
                            else{
                                blackPosList.add(p3);
                            }
                            
                            silentDrawBoard();
                            if(canCapture(getPiece(p3)) == null){
                                keepJumping = false;
                            }
                            else{
                                p1 = p3; //new start state is old last state
                            }
                            System.out.println("Nulling (" + p1.getX() + "," + p1.getY() + ")" + "AND " + "Nulling (" + p2.getX() + "," + p2.getY() + ")");
                            board[p1.getX()][p1.getY()] = null;
                            board[p2.getX()][p2.getY()] = null;
                        }

                }
                        else{
                            System.out.println("%move: Invalid move"); 
                            keepJumping = false;
                            //System.exit(0);
                        }
            }
    }
}
    /*
     * Removes a point from list of points. list.remove(point) was not working 
     */
    public boolean removePointFromList(Point p, ArrayList<Point> list){
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getX() == x && list.get(i).getY() == y){
                list.remove(i);
                board[x][y] = null;
                return true;
            }
        }
        return false;
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
    public Piece[][] getBoard(){
        return board;
    }
    public void setBoard(Piece[][] board){
        this.board = board;
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
    public ArrayList<Point> getWhiteKingList() {
        return this.whiteKingList;
    }
    public void setWhiteKingList(ArrayList<Point> whiteKingList) {
        this.whiteKingList = whiteKingList;
    }
    public ArrayList<Point> getBlackKingList() {
        return this.blackKingList;        }
    public void setBlackKingList(ArrayList<Point> blackKingList) {
            this.blackKingList = blackKingList;
        }
}