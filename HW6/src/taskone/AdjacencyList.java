package taskone;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    ArrayList<Vertex>[] array;
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
     * Scanner to parse the input file.
     */
    Scanner scan;
    /**
     * Constructor for AdjacencyList given an input file containing
     * all vertices with edge weights.
     * @param input input file
     */
    public AdjacencyList(File input) {
        this.numVtcs = 0;
        
        try {
            this.scan = new Scanner(input);
            this.rows = this.scan.nextInt();
            this.cols = this.scan.nextInt();
            int size = this.rows * this.cols;
            boolean[] found = new boolean[size];
            
            this.array = (ArrayList<Vertex>[]) new ArrayList<?>[size];
            for (int k = 0; k < size; k++) {
                this.array[k] = new ArrayList<Vertex>();
            }
            this.scan.nextLine();
            this.scan.nextLine();
            this.scan.useDelimiter("");
            
            while (this.scan.hasNextLine()) {
                this.runInt();
                int startposx = this.scan.nextInt();
                System.out.print(startposx + " ");
                
                this.runInt();
                int startposy = this.scan.nextInt();
                System.out.print(startposy + " ");
                
                int startVertexNum = this.cols * startposx + startposy;
                
                this.runInt();
                int endposx = this.scan.nextInt();
                System.out.print(endposx + " ");
                
                this.runInt();
                int endposy = this.scan.nextInt();
                System.out.print(endposy + " ");
                
                int endVertexNum = this.cols * endposx + endposy;
                
                this.runInt();
                int edgeWeight = this.scan.nextInt();
                while (this.scan.hasNextInt()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(edgeWeight);
                    sb.append(this.scan.nextInt());
                    String str = sb.toString();
                    edgeWeight = Integer.parseInt(str);
                }
                System.out.println(edgeWeight);
                
                if (!found[endVertexNum]) {
                    this.numVtcs++;
                    found[endVertexNum] = true;
                }
                if (!found[startVertexNum]) {
                    this.numVtcs++;
                    found[startVertexNum] = true;
                }
                
                this.array[startVertexNum].add(
                        new Vertex(endVertexNum, edgeWeight));
                this.array[endVertexNum].add(
                        new Vertex(startVertexNum, edgeWeight));
                this.scan.nextLine();
            }
            this.scan.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
        }
    }
    
    /**
     * return number of vertices.
     * @return number of vertices
     */
    public int getNumVtcs() {
        return this.numVtcs;
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
    static class Vertex {
        
        /**
         * name (number) of the vertex.
         */
        int name;
        /**
         * Cost to get to this vertex from parent vertex.
         */
        int weight;
        /**
         * Pointer to next neighbor.
         */
        
        /**
         * Constructor for vertex.
         * @param myname name (number) of the vertex.
         * @param myweight cost of edge to get to vertex from parent vertex.
         */
        public Vertex(int myname, int myweight) {
            this.name = myname;
            this.weight = myweight;
        }
    }
    
}
