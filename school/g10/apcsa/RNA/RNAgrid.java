
public class RNAgrid{
    int value = 0;


    //-1 = not assigned
    // 0 = from left
    // 1 = from top
    // 2 = from diagonal
    int previous = -1;
    public RNAgrid(int value, int previous){ //constructer
        this.value = value;
        this.previous = previous;
    }

    //getters and setters for instance variables
    public void setVal(int val){
        value = val;
    }

    public int getVal(){
        return value;
    }

    public void setPrevious(int prev){
        previous = prev;
    }

    public int getPrevious(){
        return previous;
    }
}