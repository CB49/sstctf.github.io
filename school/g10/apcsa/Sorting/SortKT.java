import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SortKT {
    public static int[] readFiles(int inputFile){
        String file = "";

        //set corresponding input files
        if(inputFile == 1){
            file = "./inputFiles/input1.txt";
        }else if(inputFile == 2){
            file = "./inputFiles/input2.txt";
        }else if(inputFile == 3){
            file = "./inputFiles/input3.txt";
        }else if(inputFile == 4){
            file = "./inputFiles/input4.txt";
        }else if(inputFile == 0){
            file = "./inputFiles/input0.txt";
        } else {
            System.out.println("No valid inputFile for " + inputFile + ", defaulting to input1.");
            file = "./inputFiles/input1.txt";
        }

        try {
                FileReader fReader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(fReader); //Wrap FileReader in a BufferedReader

                int[] inputNums = Arrays.stream(bReader.readLine().split(String.valueOf(','))).mapToInt(Integer::parseInt).toArray();

                bReader.close(); //always close readers

                return inputNums; //Return split string
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to locate file");
        } catch (IOException ex) {
            System.out.println("Unable to open file");
        } catch (NumberFormatException ex) {
            System.out.println("");
        }

        return null;
    }

    public static int[] SelectionSort(int[] fileN){
        int[] sortList = fileN;

        //SelectionSort method
        //Loop through array, increasing index each run
        for(int i=0; i<sortList.length; i++){
            int min = i; //assume this element in the lowest of the lows

            //Loop through remainder of the array
            for(int j=i; j<sortList.length; j++){
                if(sortList[j] < sortList[min]){
                    min = j; //if element j is less than min, set min to new value
                }
            }

            //swap i and min
            int temp = sortList[i];
            sortList[i] = sortList[min];
            sortList[min] = temp;
        }

        return sortList;
    }

    public static int[] InsertionSort(int[] fileN){
        int[] sortList = fileN;
        
        //loop through array
        for(int j=0; j<sortList.length; j++){

            int value = sortList[j];
            int current = j-1; //current element in an array
            //loop until j element is greater than the previous one
            while(current>=0 && value < sortList[current]){

                //swap elements in array
                int temp = sortList[current+1];
                sortList[current+1] = sortList[current];
                sortList[current] = temp;

                current = current - 1;

            }
        }
        return sortList;
    }

    public static int[] MergeSort(int[] sortList){ //ui of mergesort
        if(sortList.length > 1){ //splits until array is 1 element long
            int[] first = new int[sortList.length/2]; //splitting array
            int[] second = new int[sortList.length-(sortList.length/2)];
        
            for(int i=0; i<first.length; i++){
                first[i] = sortList[i];//setting the temp arrays here
            }

            for(int i=0; i<second.length; i++){
                second[i] = sortList[first.length + i]; //setting temp arrays here
            }

            first = MergeSort(first);//recursion
            second = MergeSort(second);//recursion

            int f = 0; int s = 0; //merging, f is index of first; s is index of second.
            for(int i=0; i<sortList.length; i++){
                if(f==first.length){//f is full
                    sortList[i] = second[s];
                    s++;
                } else if(s==second.length){//s is full
                    sortList[i] = first[f];
                    f++;
                } else if(first[f] < second[s]){
                    sortList[i] = first[f];
                    f++;
                } else {
                    sortList[i] = second[s];
                    s++;
                }
            }
            return sortList;
        }
        return sortList;
    }

    public static void printList(int[] sortList){
        System.out.println();
        for(int i=0; i<10; i++){
            System.out.print(sortList[i] + ", ");
        } //loop through first 10 elements
        System.out.print("..., "); //print ...
        for(int i=10; i>1; i--){
            System.out.print(sortList[sortList.length - i] + ", ");
        } //loop through last 10 elements
        System.out.print(sortList[sortList.length-1] + "\n");//print last element of the array without a comma 
    }

    public static void compareTable(){
        long time;
        System.out.println("       | Select | Insert | Merge |");
        System.out.print("\nFile 1 | "); 
        time = System.currentTimeMillis(); SelectionSort(readFiles(1)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); InsertionSort(readFiles(1)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); MergeSort(readFiles(1)); System.out.print((System.currentTimeMillis() - time) + " | ");
        
        System.out.print("\nFile 2 | ");
        time = System.currentTimeMillis(); SelectionSort(readFiles(2)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); InsertionSort(readFiles(2)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); MergeSort(readFiles(2)); System.out.print((System.currentTimeMillis() - time) + " | ");

        System.out.print("\nFile 3 | "); 
        time = System.currentTimeMillis(); SelectionSort(readFiles(3)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); InsertionSort(readFiles(3)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); MergeSort(readFiles(3)); System.out.print((System.currentTimeMillis() - time) + " | ");

        System.out.print("\nFile 4 | ");
        System.out.print(" NA | ");
        //time = System.currentTimeMillis(); SelectionSort(readFiles(4)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); InsertionSort(readFiles(4)); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); MergeSort(readFiles(4)); System.out.print((System.currentTimeMillis() - time) + " | ");
        System.out.println("\nNo selection for file4 cause it makes my computer die inside");
    }

    public static void userInterface(Scanner scan){
        
        //Instantiate some variables
        int fileNum = 0;
        int searchType = 0;

        try {
            System.out.println("Select mode");
            System.out.println(" 0) Single File");
            System.out.println(" 1) Comparason table");

            if(scan.nextInt() == 0) {
                System.out.println("\nSelect an input file");
                System.out.println(" 0) 10 entries");
                System.out.println(" 1) 1000 entries");
                System.out.println(" 2) 10000 entries");
                System.out.println(" 3) 100000 entries");
                System.out.println(" 4) 1000000 entries");

                //create scanner object
                
                fileNum = scan.nextInt(); //ask for user input
                    
                System.out.println("\nSelect a sorting method");
                System.out.println(" 1) Selection sort");
                System.out.println(" 2) Insertion sort");
                System.out.println(" 3) Merge sort");
                System.out.println("");
                
                searchType = scan.nextInt(); //ask for user input
                
                
            } else {
                compareTable();
            }
        } catch (InputMismatchException ex){ //check if user inputted something dumb, like a string
            System.out.println("\nEnter an integer please\n");
            userInterface(scan); //make the user try again for being a dumbo bumbo
            
        }

        //select appropriate sorting method chosen
        System.out.println();
        long time = System.currentTimeMillis();
        if(searchType == 1){
            System.out.println("Selection sort chosen for input file " + fileNum);
            printList(SelectionSort(readFiles(fileNum)));
            System.out.println("Selection sort took " + (System.currentTimeMillis() - time) + " milliseconds.");
        } else if(searchType == 2){
            System.out.println("Insertion sort chosen for input file " + fileNum);
            printList(InsertionSort(readFiles(fileNum)));
            System.out.println("Insertion sort took " + (System.currentTimeMillis() - time) + " milliseconds.");
        } else if(searchType == 3){
            System.out.println("Merge sort chosen for input file " + fileNum);
            printList(MergeSort(readFiles(fileNum)));
            System.out.println("Merge sort took " + (System.currentTimeMillis() - time) + " milliseconds.");
        } else if(searchType != 0){
            //user can't follow basic instructions, default to selection search
            System.out.println("No method found for '" + searchType + "', defaulting to Selection sort for input file " + fileNum);
            printList(SelectionSort(readFiles(fileNum)));
            System.out.println("Selection sort took " + (System.currentTimeMillis() - time) + " milliseconds.");
        }

        try{
            System.out.println("\nEnter 0 to continue searching, any other key to end program.");

            String t = scan.next();
            if(t.equals("0")){
                userInterface(scan);
            }
            scan.close();
        } catch (InputMismatchException e){
            System.out.println("ur bad");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        userInterface(scan);
    }
}