package taskone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        String fileName = "digInput1.txt";
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        
    }

}
