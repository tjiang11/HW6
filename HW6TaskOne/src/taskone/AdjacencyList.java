package taskone;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Implementation of AdjacencyList to represent an undirected weighted graph.
 * @author Tony
 *
 */
public class AdjacencyList {
    
    /**
     * Array to contain pointers to each neighboring vertex with the 
     * respective edge weight.
     */
    int[] array;
    /** 
     * Scanner object for parsing the input file.
     */
    
    /**
     * Total number of vertices in the graph.
     */
    int numVtcs;
    /**
     * Number of rows in grid.
     */
    int rows;
    /** 
     * Number of columns in grid.
     */
    int cols;
    /**
     * Constructor for AdjacencyList given an input file containing
     * all vertices with edge weights.
     * @param input input file
     */
    public AdjacencyList(File input) {
        try {
            Scanner scan = new Scanner(input);
            this.rows = scan.nextInt();
            this.cols = scan.nextInt();
            scan.nextLine();
            scan.nextLine();
            while (scan.next() != null) {
                System.out.println(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
        }
        
        
    }
}
