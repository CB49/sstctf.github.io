/**
 * RNA Alignment Assignment
 * Kevin Tran
 * APCSA 2018
 */

 import java.util.Scanner;
 import java.lang.Math;
 public class RNA{

    //static variables are happy variables
    static String[] strand1;//top strand
    static String[] strand2;//left strand
    

    //happy static methods 
    //generate matrix
    public static void showMatrix(RNAgrid[][] a){
        int l = -1;  //l is the index of the strand2, since we are appending it to rows
        System.out.print("        "); //starting space
        for(int i=0; i<strand1.length; i++){ //prints out strand 1 on top row
            System.out.print(strand1[i] + "   ");
        }
        System.out.println(); //indent
        for(int j=0; j<a[0].length; j++){
            if(l == -1){
                System.out.print("  "); //if its the 2nd row, don't start printing strand2
            }
            else if(l < strand2.length){ //else append strand 2 before each row
                System.out.print(strand2[l] + " ");
            }
            l = l+1; //start indexing
                for(int i=0; i<a.length; i++){ //loop through strands
                if(a[i][j] != null){ 
                    if(a[i][j].getVal() <= -10){ //align grid nicely
                        System.out.print(a[i][j].getVal() + " ");
                    } else if(a[i][j].getVal() < 0){ 
                        System.out.print(" " + a[i][j].getVal() + " ");
                    } else {
                        System.out.print("  " + a[i][j].getVal() + " ");
                    }
                } else { 
                    System.out.print("N "); //if there is nothing here print N, should never occur though
                }
            }
            System.out.println(); // new line at the end of each line
            
        }
    }

    public static RNAgrid[][] generateMatrix(){
        RNAgrid[][] tempGrid = new RNAgrid[strand1.length + 1][strand2.length + 1];
        //Create a temporary grid to return
        tempGrid[0][0] = new RNAgrid(0, -1); //sets topleft to 0

        //sets left and top to numbers in decrementing order
        for(int i = 1; i < strand1.length + 1; i++){
            tempGrid[i][0] = new RNAgrid(i*-1, 0);
        }
        for(int i = 1; i < strand2.length + 1; i++){
            tempGrid[0][i] = new RNAgrid(i*-1, 1);
        }

        for(int x=0; x<strand1.length; x++){
            for(int y=0; y<strand2.length; y++){
                //System.out.println(strand1[x] + " " + strand2[y]);
                if(strand1[x].equals(strand2[y])){
                    tempGrid[x+1][y+1] = new RNAgrid(tempGrid[x][y].getVal()+1, 2);
                } else if(tempGrid[x+1][y].getVal()-1 > tempGrid[x][y+1].getVal()-1){
                    tempGrid[x+1][y+1] = new RNAgrid(tempGrid[x+1][y].getVal()-1, 1);
                } else {
                    tempGrid[x+1][y+1] = new RNAgrid(tempGrid[x][y+1].getVal()-1, 0);
                }
            }
        }
        return tempGrid;
    }

    //Recursive function to get best path
    public static String getBestPath(RNAgrid[][] a, int x, int y, int index, String path){

        if(x<0 || y<0){
            return path; //should never meet this unless something died inside, but just in case
        } else if(a[x][y].getPrevious() == 2){
            return getBestPath(a, x-1, y-1, index+1, path+"d"); //if tiles previous is 2, add diagonal
        } else if(a[x][y].getPrevious() == 0){
            return getBestPath(a, x-1, y, index+1, path+"l"); //0, add left
        } else if(a[x][y].getPrevious() == 1){
            return getBestPath(a, x, y-1, index+1, path+"u");//1, add up
        } else if(a[x][y].getPrevious() == -1){ //end of grid
            return path;
        }
        
        return "error";
    }

    //takes the result from getBestPath and displays it in a readable format
    public static void showPath(String[] path){
        String S1 = "";
        String S2 = "";
        int id=0;//index of string
        for(int i=0; i<path.length; i++){//iterate through index of path
            if(path[i].equals("d") || path[i].equals("l")){
                S1 = S1+strand1[strand1.length-id-1];
                id++;
            } else {
                S1 = S1+"-";
            }
        }
        
        id=0;//index of string
        for(int i=0; i<path.length; i++){//iterate through index of path
            if(path[i].equals("d") || path[i].equals("u")){
                S2 = S2+strand2[strand2.length-id-1];
                id++;
            } else {
                S2 = S2+"-";
            }
        }
        System.out.println(new StringBuilder(S1).reverse().toString() + "\n" + new StringBuilder(S2).reverse().toString()); 
        //since we are recursing from the bottom up, must reverse the string to get proper string needed
    }

    //main function
    public static void main(String args[]){
        System.out.println("Welcome to RNA Analyser, or better known as the (R)ecursion(N)ot(A)wesome Analyser!\n");
        //Get user input for grids
        Scanner strandalyser = new Scanner(System.in); //create scanner object

        System.out.println("Enter first strand"); //get user inputs
        strand1 = strandalyser.next().split("");

        System.out.println("Enter second strand");
        strand2 = strandalyser.next().split("");

        strandalyser.close(); //close scanner to avoid memory leaks


        //call functions and format nicely
        RNAgrid[][] grid = generateMatrix();
        System.out.println();
        showMatrix(grid);
        System.out.println("\nBest possible alignment:");
        showPath(getBestPath(grid, strand1.length, strand2.length, 0, "").split(""));
        System.out.println("\nPoint value for this alignment is " + grid[strand1.length][strand2.length].getVal());
    }
 }