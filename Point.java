import java.util.ArrayList;

public class Point {
    private int x,y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*
    checks if point is in a given list
    */
    public boolean isInList(ArrayList<Point> list){
        int size = list.size();
        for(int i = 0; i<size; i++){
            if(list.get(i).getX() == this.getX() && (list.get(i).getY() == this.getY())){
                return true;
            }
        }
        return false;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }


}
