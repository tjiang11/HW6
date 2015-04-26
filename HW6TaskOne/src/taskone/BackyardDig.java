package taskone;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/**
 * Given a collection of match-cars that lie in a grid, 
 * generate the network of tunnels that will require the 
 * least work the reach every car.
 * @author Tony Jiang, Devin Sun, Suyi Liu
 *
 */
public final class BackyardDig {

    /**
     * Shutup Checkstyle.
     */
    private BackyardDig() {   
    }
    
    /**
     * Main method.
     * @param args First argument: Input file containing 
     * grid size and car locations based on edges and respective weights.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("This assignment is long as hell.");
        System.out.println("First change.");
        File inputFile = new File(args[0]);
        String outputFile = args[1];
        
        Scanner scan;
        int rows;
        int cols;
        int[][] grid;
        
        AdjacencyList al = new AdjacencyList(inputFile);
        
        try {
            //Grab rows and columns from input
            scan = new Scanner(inputFile);
            rows = scan.nextInt();
            cols = scan.nextInt();
            grid = new int[rows][cols];
            //Skip Empty Line
            scan.nextLine();
            scan.nextLine();
            scan.close();
            
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
        }
        
        // DIJKSTRA'S ALGORITHM
        int[] dist = new int[al.getNumVtcs()];
        int[] prev = new int[al.getNumVtcs()];
        boolean[] found = new boolean[al.getNumVtcs()];
        
        int startVtx = al.array[0].getFirst().name;
        dist[startVtx] = 0;
        int numVtcs = al.getNumVtcs();
        int i;
        for (int k = 0; k < numVtcs; k++) {
            for (i = 0; i < numVtcs; i++) {
                int min = Integer.MAX_VALUE;
                int minVtx;
                if (dist[i] < min) {
                    min = dist[i];
                    minVtx = i;
                }
            }
            found[i] = true;
            for (int c = 0; c < al.array[i].size(); c++) {
                if (dist[i] + al.array[i].get(c).weight 
                        < dist[al.array[i].get(c).name]) {
                    
                }
            }
            
        }
        try {
            PrintWriter pr = new PrintWriter(outputFile, "UTF-8");
            pr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        
    }

}
