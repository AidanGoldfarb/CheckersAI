public class Piece{
    private String side;
    private int x_pos, y_pos;
    private int [][] moves; 

    public Piece(String side){ //piece in initial position
        this.side = side;
    }

    public Piece(String side, int x_pos, int y_pos){
        this.side = side;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    @Override
    public String toString(){
        if(this.side.equals("white"))
            return "X";
        else 
            return "O";
    }

    public String getSide(){
        return this.side;
    }
}