package taskone;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
    LinkedList<Vertex>[] array;
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
            int size = this.rows * this.cols;
            for (int k = 0; k < size; k++) {
                this.array[k] = new LinkedList<Vertex>();
            }
            scan.nextLine();
            scan.nextLine();
            scan.useDelimiter("");
            int charPos = 0;
            while (scan.hasNext()) {
                if (!scan.hasNextInt()) {
                    scan.next();
                } else {
                    switch (charPos) {
                        case 0:
                            int startposx = scan.nextInt();
                            int startposy = scan.nextInt();
                            int startVertexNum = this.cols * startposx + startposy;
                            
                            int endposx = scan.nextInt();
                            int endposy = scan.nextInt();
                            int endVertexNum = this.cols * endposx + endposy;
                            int edgeWeight = scan.nextInt();
                            this.array[startVertexNum].add(new Vertex(endVertexNum, edgeWeight));
                    }
                   // System.out.println(scan.nextInt());
                }
                //System.out.println(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
        }
        
        /**
         * Inner vertex class to be placed in 
         * linked lists in the adjacency list.
         * @author Tony
         *
         */
    }
    static class Vertex {
        
        int name;
        int weight;
        Vertex next;
        
        /**
         * Constructor for vertex.
         * @param myname name (number) of the vertex.
         * @param myweight cost of edge to get to vertex from parent vertex.
         */
        public Vertex(int myname, int myweight) {
            this.name = myname;
            this.weight = myweight;
            this.next = null;
        }
    }
    
}
