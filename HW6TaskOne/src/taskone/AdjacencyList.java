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
            this.array = (LinkedList<Vertex>[]) new LinkedList<?>[size];
            for (int k = 0; k < size; k++) {
                this.array[k] = new LinkedList<Vertex>();
            }
            scan.nextLine();
            scan.nextLine();
            /*while (scan.hasNextLine()) {
                Scanner iscan = new Scanner(scan.nextLine());
                while 
                iscan.close();
            }*/
            
       
            scan.useDelimiter("");
            int h = 0;
            while (scan.hasNextLine()) {
                while (!scan.hasNextInt()) {
                    scan.next();
                }
                
                int startposx = scan.nextInt();
                /*while (scan.hasNextInt()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(startposx);
                    String str = sb.toString();
                    startposx = Integer.parseInt(str);
                }*/
                
                System.out.print(startposx + " ");
                while (!scan.hasNextInt()) {
                    scan.next();
                }
             
            
                int startposy = scan.nextInt();
                System.out.print(startposy + " ");
                int startVertexNum = this.cols * startposx + startposy;
                
                while (!scan.hasNextInt()) {
                    scan.next();
                }
            
                int endposx = scan.nextInt();
                System.out.print(endposx + " ");
                while (!scan.hasNextInt()) {
                    scan.next();
                }
                int endposy = scan.nextInt();
                System.out.print(endposy + " ");
                int endVertexNum = this.cols * endposx + endposy;
                while (!scan.hasNextInt()) {
                    scan.next();
                }
                int edgeWeight = scan.nextInt();
                if (scan.hasNextInt()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(edgeWeight);
                    sb.append(scan.nextInt());
                    String str = sb.toString();
                    edgeWeight = Integer.parseInt(str);
                }
                System.out.println(edgeWeight);
                this.array[startVertexNum].add(
                        new Vertex(endVertexNum, edgeWeight));
                h++;
                scan.nextLine();
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
