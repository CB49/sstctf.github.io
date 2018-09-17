import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class SearchKT {
    //Copy pasta readFiles from SortKT.java
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

    /* Copy pasta MergeSort from SortKT.java*/
    public static int[] MergeSort(int[] sortList){
        if(sortList.length > 1){
            int[] first = new int[sortList.length/2];
            int[] second = new int[sortList.length-(sortList.length/2)];
        
            for(int i=0; i<first.length; i++){
                first[i] = sortList[i];
            }

            for(int i=0; i<second.length; i++){
                second[i] = sortList[first.length + i];
            }

            first = MergeSort(first);
            second = MergeSort(second);

            int f = 0; int s = 0;
            for(int i=0; i<sortList.length; i++){
                if(f==first.length){
                    sortList[i] = second[s];
                    s++;
                } else if(s==second.length){
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

    public static int SequentialSearch(int[] searchList, int SearchQuery){
        for(int i = 0; i<searchList.length; i++){
            if(searchList[i] == SearchQuery){
                return i;//loop through array checking each index
            }
        }
        return -1; //none found
    }

    //recursive method for binary search
    public static int BinarySearch(int[] searchList, int SearchQuery, int start, int end){

        int mid = (start + end)/2; //set mid to midpoint between start and end
        //System.out.println(mid);
        //System.out.println(searchList[mid]);
        if(mid<0 || end>searchList.length-1 || start<0){
            return -1;//can't find query
        } else if(searchList[mid] == SearchQuery){
            return mid; //found the query
        } else if(searchList[mid] > SearchQuery){
            return BinarySearch(searchList, SearchQuery, start, mid-1); //query is less than midpoint
        } else if(searchList[mid] < SearchQuery){
            return BinarySearch(searchList, SearchQuery, mid+1, end); //query is greater than midpoint
        } else {
            return -1; //can't find query
        }
    }

    public static void compareTable(){
        long time;
        System.out.println("       | Sequential | Binary |");
        System.out.print("\nFile 1 | "); 
        time = System.currentTimeMillis(); SequentialSearch(readFiles(1), 443); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); BinarySearch(MergeSort(readFiles(1)), 443, 0, readFiles(1).length-1); System.out.print((System.currentTimeMillis() - time) + " | ");
        
        System.out.print("\nFile 2 | ");
        time = System.currentTimeMillis(); SequentialSearch(readFiles(2), 6727); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); BinarySearch(MergeSort(readFiles(2)), 6727, 0, readFiles(2).length-1); System.out.print((System.currentTimeMillis() - time) + " | ");

        System.out.print("\nFile 3 | "); 
        time = System.currentTimeMillis(); SequentialSearch(readFiles(3), 9742); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); BinarySearch(MergeSort(readFiles(3)), 9742, 0, readFiles(3).length-1); System.out.print((System.currentTimeMillis() - time) + " | ");

        System.out.print("\nFile 4 | ");
        time = System.currentTimeMillis(); SequentialSearch(readFiles(4), 749956); System.out.print((System.currentTimeMillis() - time) + " | ");
        time = System.currentTimeMillis(); BinarySearch(MergeSort(readFiles(4)), 749956, 0, readFiles(4).length-1); System.out.print((System.currentTimeMillis() - time) + " | ");
        System.out.println("\nSearching for last element of each file, could appear earlier in file\nTime includes mergesort for binary search.");
    }


    public static void userInterface(Scanner scan){
        //Instantiate some variables
        int fileNum = 0;
        int searchType = 0;
        int searchQuery = 0;
        
        try {
            System.out.println("\nSelect mode");
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
                    
                System.out.println("\nSelect a search method");
                System.out.println(" 1) Binary search");
                System.out.println(" 2) Sequential search");
                
                searchType = scan.nextInt(); //ask for user input
                
                System.out.println("\nInsert search query");
                searchQuery = scan.nextInt();

                
            } else {compareTable();}
        } catch (InputMismatchException ex){ //check if user inputted something dumb, like a string
            System.out.println("\nYou did not enter a valid input.\n");
        }
        long time = System.currentTimeMillis();
        if(searchType==1){
            //binary
            System.out.println("\nBinary search chosen to look for query '" + searchQuery + "' in input file " + fileNum);
            System.out.println(BinarySearch(MergeSort(readFiles(fileNum)), searchQuery, 0, readFiles(fileNum).length-1));
            System.out.println("Binary search took " + (System.currentTimeMillis() - time) + " milliseconds.");

        } else if(searchType==2){
            //sequential
            System.out.println("\nSequential search chosen to look for query '" + searchQuery + "' in input file " + fileNum);
            System.out.println(SequentialSearch(readFiles(fileNum), searchQuery));
            System.out.println("Sequential search took " + (System.currentTimeMillis() - time) + " milliseconds.");

        } else if(searchType!=0){
            System.out.println("\nUnknown choice, defaulting to sequential search.");
            System.out.println("Sequential search chosen to look for query '" + searchQuery + "' in input file " + fileNum);
            System.out.println(SequentialSearch(readFiles(fileNum), searchQuery));
            System.out.println("Sequential search took " + (System.currentTimeMillis() - time) + " milliseconds.");
        }
    
        try{
            System.out.println("\nEnter 0 to continue searching, any other key to end program.");

            String t = scan.next();
            if(Integer.valueOf(t) == 0){
                userInterface(scan);
            }
            scan.close();
        } catch (InputMismatchException e){
            System.out.println("ur bad");
        }
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        userInterface(scan);
        System.out.println("Ending program...");
    }
}
