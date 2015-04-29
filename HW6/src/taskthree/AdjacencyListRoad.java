package taskthree;
import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of AdjacencyList to represent an undirected weighted graph.
 * @author Tony
 *
 */
public class AdjacencyListRoad {
    
    /**
     * Array to contain pointers to each neighboring vertex with the 
     * respective edge weight.
     */
    ArrayList<Location>[] array;
    
    /**
     * A String array to match each location with a number.
     */
    String[] locs;
    /**
     * Total number of vertices in the graph.
     */
    int size;
    /**
     * Number of locations.
     */
    int rows;
    /** 
     * Number of columns in grid.
     */
    int cols;
    
    /**
     * Scanner to parse the input file.
     */
    Scanner scan;
    /**
     * Constructor for AdjacencyList given an input file containing
     * all vertices with edge weights.
     * @param input input file
     */
    public AdjacencyListRoad(File input) {
        this.size = 0;
        
        try {
            this.scan = new Scanner(input);
           
            while (this.scan.hasNextLine()) {
                this.scan.nextLine();
                this.size++;
            }
            this.array = (ArrayList<Location>[]) new ArrayList<?>[this.size];
            
            this.scan = new Scanner(input);
            
            //HashMap<String, Integer> hashLocs = 
            new HashMap<String, Integer>();
            this.locs = new String[size];
            for (int i = 0; i < size; i++) {
                String newloc = this.scan.nextLine();
                this.locs[i] = newloc;
            }
            for (int f = 0; f < size; f++) {
                System.out.print(f + ". ");
                System.out.println(this.locs[f]);
            }
            boolean[] found = new boolean[this.size];
            
            for (int k = 0; k < this.size; k++) {
                this.array[k] = new ArrayList<Location>();
            }
            
            this.scan.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
        }
    }
    
    /**
     * Add roads between the locations in the graph given an 
     * input file consisting of all connections with time
     * to travel each road.
     * @param inputFile file containing connections and times
     * @throws FileNotFoundException 
     */
    public void importConnections(File inputFile) {
        try {
            Scanner scanner = new Scanner(inputFile);
            for (int k = 0; k < this.size; k++) {
                scanner.skip("\\(");
                scanner.useDelimiter(",");
                System.out.println(scanner.next());
                
                scanner.skip(", ");
                scanner.useDelimiter("\\)");
                System.out.println(scanner.next());
                
                scanner.skip("\\)");
                while (!scanner.hasNextInt()) {
                    scanner.useDelimiter("");
                    scanner.next();
                }
                scanner.reset();
                System.out.println(scanner.nextInt());
                
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    /**
     * return number of vertices.
     * @return number of vertices
     */
    public int getNumVtcs() {
        return this.size;
    }
    
    /**
     * Return size.
     * @return the size of the grid.
     */
    public int getSize() {
        return this.rows * this.cols;
    }
    
    
    /**
     * 
     * @param scan
     */
    public void runInt() {
        while (!this.scan.hasNextInt()) {
            this.scan.next();
        }
    }
    
    /**
     * Inner vertex class to be placed in 
     * linked lists in the adjacency list.
     * @author Tony
     *
     */
    static class Location {
        
        /**
         * name (number) of the vertex.
         */
        String name;
        /**
         * Cost to get to this vertex from parent vertex.
         */
        int weight;
       
        
        /**
         * Constructor for vertex.
         * @param myname name (number) of the vertex.
         * @param myweight cost of edge to get to vertex from parent vertex.
         */
        public Location(String myname, int myweight) {
            this.name = myname;
            this.weight = myweight;
        }
    }
    
}
