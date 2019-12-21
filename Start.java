import java.util.ArrayList;
public class Start {
    public static void main(String [] args){
        Board b1 = new Board(4);
        loadInitialBoard(b1, 4);
        System.out.println(b1.isMoveLegal(b1.getPiece(new Point(3,0)), new Point(2,1)));
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
            wPosList.add(w1);
            wPosList.add(w2);
            bPosList.add(b1);
            bPosList.add(b2);
            b.drawBoard(wPosList, bPosList);
        }
    }

    

    
}