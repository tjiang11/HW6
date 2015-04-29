package taskthree;

import java.io.File;

/**
 * Class.
 * @author Tony
 *
 */
final class Taxis {
    
    /**
     * Shutup Checkstyle.
     */
    private Taxis() {
        
    }
    
    /**
     * Main method.
     * @param args d
     */
    public static void main(String[] args) {
        System.out.println("Welcome!");
        String numDrivers = args[0];
        
        AdjacencyListRoad roadMap = 
                new AdjacencyListRoad(new File(args[1]));
        roadMap.importConnections(new File(args[2]));
        
    }

}
