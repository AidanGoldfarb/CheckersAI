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
                        //System.out.print(p + "  ");
                    }else if(pt.isInList(blackPosList)){
                        Piece p = new Piece("black", x, y);
                        p.setX(x);
                        p.setY(y);
                        board[x][y] = p;
                        //currentPieces.add(pt);
                        //System.out.print(p + "  ");
                    }else{
                        //System.out.print("   ");
                    }
                }
            }
            
            //System.out.println();
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
     *Given a piece, says whether it has any legal captures
         retursn valid capture point in future... 
     */
    public Point canCapture(Piece piece){
        silentDrawBoard();
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
        if(side.equals("white")){//WHITE
            if(pieceX <= 1){
                return null;
            }
            else if(pieceY == 0){
                //just check (x-1,1) and (x-2,2)
                if(isOccupied(pieceX-1, pieceY+1) && !isOccupied(pieceX-2, pieceY+2)){
                    removePointFromList(new Point(pieceX-1, pieceY+1) ,blackPosList); //remove jumped piece
                    return p = new Point(pieceX-2, pieceY+2);
                }
            }
            else if(pieceY == DIM-1){
                 //just check (x-1,DIM-2) and (x-2,DIM-3)
                 if(isOccupied(pieceX-1, pieceY-1) && !isOccupied(pieceX-2, pieceY-2)){
                    removePointFromList(new Point(pieceX-1, pieceY-1) ,blackPosList); //remove jumped piece
                    return p = new Point(pieceX-2, pieceY-2);
                 }
            }
            else{
                //check both directions
                if((isOccupied(pieceX-1, pieceY+1) && !isOccupied(pieceX-2, pieceY+2)) ){
                    removePointFromList(new Point(pieceX-1, pieceY+1) ,blackPosList); //remove jumped piece
                    return p = new Point(pieceX-2, pieceY+2);
                }
                else if((isOccupied(pieceX-1, pieceY-1) && !isOccupied(pieceX-2, pieceY-2))){
                    removePointFromList(new Point(pieceX-1, pieceY-1) ,blackPosList); //remove jumped piece
                    return p = new Point(pieceX-2, pieceY-2);
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
                    removePointFromList(new Point(pieceX+1, pieceY+1) ,whitePosList); //remove jumped piece
                    return new Point(pieceX+2, pieceY+2);
                }
            }
            else if(pieceY == DIM-1){
                // System.out.println(piece.getX() + "," + piece.getY());
                // System.out.println(isOccupied(pieceX+1, pieceY-1) + "  " + !isOccupied(pieceX+2, pieceY-2)); //true false, should be true true
                // System.out.println(whitePosList);
                // System.out.println("is Occupied: " + isOccupied(2,1));
                if(isOccupied(pieceX+1, pieceY-1) && !isOccupied(pieceX+2, pieceY-2)){
                    removePointFromList(new Point(pieceX+1, pieceY-1) ,whitePosList); //remove jumped piece
                    return new Point(pieceX+2, pieceY-2);
                 }
            }
            else{
                if((isOccupied(pieceX+1, pieceY+1) && !isOccupied(pieceX+2, pieceY+2)) ){
                    removePointFromList(new Point(pieceX+1, pieceY+1) ,whitePosList); //remove jumped piece
                    return new Point(pieceX+2, pieceY+2);
                }
                else if((isOccupied(pieceX+1, pieceY-1) && !isOccupied(pieceX+2, pieceY-2))){
                    removePointFromList(new Point(pieceX+1, pieceY-1) ,whitePosList); //remove jumped piece
                    return new Point(pieceX+2, pieceY-2);
                }
                else{
                    return null;
                }
            }
        }
        System.out.println("failed at 2");
        return null;
    }

    /*
     *Given a piece and a potential point, returns true if it is legal for the given piece to move to the given point
     */
    public boolean isMoveLegal(Piece piece, Point point){ 
        //silentDrawBoard();
        
        if(piece.getSide().equals("white")){
            if(piece.getX() == 0)
                piece.setIsKing(true);
                board[piece.getX()][piece.getY()].setIsKing(true);
        }
        else{
            if(piece.getX() == DIM-1)
                piece.setIsKing(true);
                board[piece.getX()][piece.getY()].setIsKing(true);
        }
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
                if(pieceX == 0 && pieceY == 0){
                    //just check 1,1
                    int newX = pieceX+1;
                    int newY = pieceY+1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0 && pieceY == DIM-1){
                    //just check i+1,j-1
                    int newX = pieceX+1;
                    int newY = pieceY-1;
                    if(!isOccupied(newX, newY) && (newX == potX && newY == potY)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceY == 0){
                    //just check j+1 in both x directions
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
                else if(pieceY == DIM-1){
                    //just check j-1
                    int newX = pieceX-1;
                    int newX2 = pieceX+1;
                    int newY = pieceY-1;
                    if((!isOccupied(newX, newY) && (newX == potX && newY == potY)) || ((!isOccupied(newX2, newY) && (newX2 == potX && newY == potY)))){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(pieceX == 0){
                    System.out.println("im here thats good");
                    //check j+/-1
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
                    int newX1 = pieceX-1;
                    int newX2 = pieceX+1;
                    int newY1 = pieceY-1;
                    int newY2 = pieceY+1;
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
                if(pieceX == DIM-1 && pieceY == DIM-1){
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
                else if(pieceX == DIM-1 && pieceY == 0){
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
                else if(pieceY == DIM-1){
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
                else if(pieceY == 0){
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
                else if(pieceX == DIM-1){
                    System.out.println("im here thats good");
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
        //TODO fix king promotion on capture
        if(str.contains("-")){
            String [] parts = str.split("-"); 
            if(parts.length != 2){
                System.out.println("ERROR(move) | USAGE");
                System.exit(0);
            }
            Point p1 = cordToPoint(parts[0]);
            Point p2 = cordToPoint(parts[1]);
            if(getPiece(p1) != null && isMoveLegal(getPiece(p1), p2)){ //there is a piece on p1 and it can move to p2
                //put getpiece(p1) to p2, set p1 to null
                //Point p3 = getPiece(p1).getPoint();
                if(getPiece(p1).getSide().equals(("white"))){
                    //whitePosList.remove(p1); //p3
                    removePointFromList(p1,whitePosList);
                    getPiece(p1).setX(p2.getX());
                    getPiece(p1).setY(p2.getY());
                    whitePosList.add(p2);
                    silentDrawBoard();
                    if(p2.getX() == 0){
                        board[p2.getX()][p2.getY()].setIsKing(true);
                        System.out.println("Piece at " + p2 + " set to king");
                        System.out.println("Piece at " + p2 + " isKing: " + getPiece(p2).getIsKing());
                    }
                }
                else{
                    //board[p1.getX()][p1.getY()] = null;
                    removePointFromList(p1,blackPosList); //p3
                    getPiece(p1).setX(p2.getX());
                    getPiece(p1).setY(p2.getY());
                    blackPosList.add(p2);
                    silentDrawBoard();
                    if(p2.getX() == DIM-1){
                        board[p2.getX()][p2.getY()].setIsKing(true);
                        System.out.println("Piece at " + p2 + " set to king");
                    }
                }
                

            }
            else{
                System.out.println("Invalid move");
            }
            System.out.println("Nulling (" + p1.getX() + "," + p1.getY() + ")");
            board[p1.getX()][p1.getY()] = null;
        }
        else if(str.contains("x")){
            String [] parts = str.split("x"); 
            if(parts.length != 2){
                System.out.println("ERROR(move) | USAGE");
                System.exit(0);
            }
            Point p1 = cordToPoint(parts[0]);
            Point p2 = cordToPoint(parts[1]);
            boolean keepJumping = true;
            if(canCapture(getPiece(p1)) == null){
                System.out.println("Invalid move can. Capture returned null");
                System.exit(0);
            }
            while(keepJumping){
                if(getPiece(p1) != null && canCapture(getPiece(p1)) != null){ 
                        if(getPiece(p1).getSide().equals(("white"))){
                            Point p3 = canCapture(getPiece(p1)); //new point after jump
                            System.out.println(p3);
                            board[p1.getX()][p1.getY()] = null;
                            removePointFromList(p1,whitePosList);
                            whitePosList.add(p3);
                            silentDrawBoard();
                            if(canCapture(getPiece(p3)) == null){
                                keepJumping = false;
                            }
                            else{
                                p1 = p3; //new start state is old last state
                            }
                        }
                        else{
                            Point p3 = canCapture(getPiece(p1)); //new point after jump
                            System.out.println(p3);
                            board[p1.getX()][p1.getY()] = null;
                            removePointFromList(p1,blackPosList); //p3
                            blackPosList.add(p3);
                            silentDrawBoard();
                            if(canCapture(getPiece(p3)) == null){
                                keepJumping = false;
                            }
                            else{
                                p1 = p3; //new start state is old last state
                            }
                        }

                }
                        else{
                            System.out.println("Invalid move"); //failing here
                            System.exit(0);
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
}