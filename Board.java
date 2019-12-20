import java.util.ArrayList;

public class Board {
    private final int DIM;
    private Piece [][] board; 
    ArrayList<Point> currentPieces;
    //ArrayList<Point>

    public Board(int size){
        DIM = size;
        board = new Piece [DIM][DIM]; //+1 for adding coordinates 
        //createBoard();
    }

    // private void createBoard(){
    //     for(int i = 0; i<2*DIM+1; i++){
    //         for(int j = 0; j<2*DIM+1; j++){
    //             if(i%2==0){
    //                 board[i][j] = '+';
    //             }
    //         }
    //     }
    // }


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
                        System.out.print(p + "  ");
                    }else if(pt.isInList(bPosList)){
                        Piece p = new Piece("black", x, y);
                        System.out.print(p + "  ");
                    }else{
                        System.out.print("   ");
                    }
                }
            }
            
            System.out.println();
        }
    }

}