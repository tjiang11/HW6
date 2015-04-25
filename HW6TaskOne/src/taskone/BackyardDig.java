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
        
        try {
            //Grab rows and columns from input
            scan = new Scanner(inputFile);
            rows = scan.nextInt();
            cols = scan.nextInt();
            grid = new int[rows][cols];
            
            //Skip Empty Line
            scan.nextLine();
            scan.nextLine();
            System.out.println(scan.nextLine());
            scan.close();
            
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Error: File not found");
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
