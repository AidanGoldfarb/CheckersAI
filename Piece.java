import java.util.ArrayList;
public class Piece{
    private String side;
    private int x_pos, y_pos;
    private boolean canCapture;

    public Piece(String side){ //piece in initial position
        this.side = side;
    }

    public Piece(String side, int x_pos, int y_pos){
        this.side = side;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        canCapture = false;
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

    public void setX(int x){
        x_pos = x;
    }
    
    public void setY(int y){
        y_pos = y;
    }

    public int getX(){
        return x_pos;
    }

    public int getY(){
        return y_pos;
    }

    public Point getPoint(){
        return new Point(x_pos, y_pos);
    }

    public boolean getCanCapture() {
        return this.canCapture;
    }
    public void setCanCapture(boolean canCapture) {
        this.canCapture = canCapture;
    }
}