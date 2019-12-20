import java.util.ArrayList;

public class Start {
    public static void main(String [] args){
        Board b1 = new Board(4);
        //b1.drawBoard();
        ArrayList<Point> wPosList = new ArrayList<Point>();
        ArrayList<Point> bPosList = new ArrayList<Point>();
        Point pt = new Point(0,1);
        bPosList.add(pt);
        b1.drawBoard(wPosList, bPosList);

        // if(pt.isInList(bPosList))
        //     System.out.println("TRUE");
        // else
        // System.out.println("FALSE");
    }

    

    
}