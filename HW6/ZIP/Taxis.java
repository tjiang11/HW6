package taskthree;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class.
 * @author Suyi
 *
 */
    
class Location implements Comparable<Location> {
    
    /**
     * The name of the location.
     */
    public String name;
    
    /**
     * Array of adjacent locations' edges.
     */
    public ArrayList<Edge> adjacencies = new ArrayList<Edge>();
    
    /**
     * The minimum distance from the location to a certain location.
     */
    public double minDistance = Double.POSITIVE_INFINITY;
    
    /**
     * Its previous location in the shortest path.
     */
    public Location previous;
    
    /**
     * Construct a location.
     * @param paraName  name of the location
     */
    public Location(String paraName) { 
        this.name = paraName; 
    }
    
    /**
     * Returns the name of the location.
     * @return the name
     */
    public String toString() { 
        return this.name; 
    }
       
    /**
     * Check if there exists a smaller distance.
     * @param other another distance of another location
     * @return if there exists a smaller distance
     */
    public int compareTo(Location other) {
        return Double.compare(this.minDistance, other.minDistance);
    }
       
}   

/**
 * The roads between two locations.
 * @author Suyi
 *
 */
class Edge {
    
    /**
     * The target location.
     */
    public Location target;
    
    /**
     * The cost from the target to the previous location.
     */
    public double weight;
    
    /**
     * Constructs the edge.
     * @param paraTarget   the new location
     * @param paraWeight   the cost on the road from the previous location
     */
    public Edge(Location paraTarget, double paraWeight) { 
       
        this.target = paraTarget; 
        
        this.weight = paraWeight; 
    }
    
    @Override
    public String toString() {
        return "(" + this.target + ", " + this.weight + ")";
    }
}



/**
 * The taxis class.
 * @author Suyi
 *
 */
public final class Taxis {

    /**
     * Constant to represent the third argument.
     */
    public static final int ARG_THREE = 3;
    /**
     * Counts the number of locations.
     */
    private static int locationnumber = 0;
    
    /**
     * Counts the number of connections.
     */
    private static int connectionnumber = 0;
    
    /**
     * Counts the number of drivers.
     */
    
    
    private static int drivernumber = 0;
    /**
     * Shutup Checkstyle.
     */
    
    private Taxis() {
        
    }
    
    /**
     * Computes the shortest paths to a location using Dijkstra's algorithm.
     * @param source  the original location
     */
    
    public static void computePaths(Location source) {
        source.minDistance = 0.;
        PriorityQueue<Location> vertexQueue = new PriorityQueue<Location>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            Location u = vertexQueue.poll();
            for (Edge e : u.adjacencies) {
                Location v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    /**
     * The list representing the shortest path.
     * @param target  the target location from the original one
     * @return  the arraylist of the shortest path
     */
    public static List<Location> getShortestPathTo(Location target) {
        List<Location> path = new ArrayList<Location>();
        for (Location vertex = target; vertex != null;
                vertex = vertex.previous) {
            path.add(vertex);
        }
        return path;
    }
    
    /**
     * Get the part of the entire path to the destination.
     * @param vertex  the starting location
     * @return the representation of the path
     */
    public static String getPathTo(Location vertex) {
        String path = "";
        path = "(" + vertex.toString() + ", " 
                 + vertex.previous.toString() + ")";
        return path;
    }
    
    /**
     * Creates an arraylist of locations.
     * @param fileName the name of the file containing locations
     * @return the complete arraylist of locations
     */
    public static ArrayList<Location> locationsFromFile(String fileName) {
        try {
            ArrayList<Location> locations1 = new ArrayList<Location>();
            Scanner fileIn = new Scanner(new FileReader(fileName));
            while (fileIn.hasNextLine()) {
                String name = fileIn.nextLine().trim();
                if (name.isEmpty()) {
                    continue;
                }
                locations1.add(new Location(name));
                locationnumber++;
            }
            fileIn.close();
            return locations1;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        }
    }
    
    /**
     * Print a list of locations.
     * @param locationlist the input file
     */
    public static void printLocations(ArrayList<Location> locationlist) {
        for (int i = 0; i < locationlist.size(); i++) {
            int n = i + 1;
            System.out.println("      " + n + " " + locationlist.get(i));
        }
    }
    
    /**
     * Creates the map by linking locations together.
     * @param locations   the input file of locations
     * @param connectionFileName   the input file of connections
     * @return  the arraylist representation of connections
     */
    public static ArrayList<Location> createMap(
            ArrayList<Location> locations, String connectionFileName) {
        try {
            Scanner connIn = new Scanner(new FileReader(connectionFileName));
            while (connIn.hasNextLine()) {
                String edgeInfo = connIn.nextLine();
                String pattern = "\\((.*), (.*)\\)\\s*(\\d*)";
                Pattern expr = Pattern.compile(pattern);
                Matcher matcher = expr.matcher(edgeInfo);
                if (matcher.find()) {
                    String streetName1 = matcher.group(1).trim();
                    String streetName2 = matcher.group(2).trim();
                    
                    int distance = Integer.parseInt(matcher.group(ARG_THREE));
                    Location loc1 = null, loc2 = null;
                    for (int i = 0; i < locations.size(); i++) {
                        if (locations.get(i).name.equals(streetName1)) {
                            loc1 = locations.get(i);
                        } else if (locations.get(i).name.equals(streetName2)) {
                            loc2 = locations.get(i);
                        }
                    }
                    if (loc1 == null || loc2 == null) {
                        System.err.println("Not found: " + streetName1 
                                + ", " + streetName2 + ", " + distance);
                    }
                    loc1.adjacencies.add(new Edge(loc2, distance));
                    loc2.adjacencies.add(new Edge(loc1, distance));
                    connectionnumber++;
                }
            }
            connIn.close();
            return locations;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        }
    }
 
    /**
     * Counts the number of drivers available.
     * @param driversFilename  the file containing drivers' information
     */
    public static void countDriverNumbers(String driversFilename) {
        try {
            Scanner fileIn = new Scanner(new FileReader(driversFilename));
            while (fileIn.hasNext()) {
                fileIn.nextInt();
                fileIn.nextLine().trim();
                drivernumber++;
            }
            fileIn.close(); 
        } catch  (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
    
    /**
     * Creates a priority queue representing closest drivers.
     * @param driversFileName  file containing drivers' information
     * @param v    the target pickup place
     * @param locations   the locations list 
     * @param connections   the connections list
     * @return  the minimum heap
     */
    public static DriversPq createDrivers(
            String driversFileName, Location v, 
                ArrayList<Location> locations, 
                ArrayList<Location> connections) {
        try {
            computePaths(v);
            DriversPq drivers = new DriversPq();
            Scanner fileIn = new Scanner(new FileReader(driversFileName));
            Location driverspot;
            Double driverfrompickup;
            while (fileIn.hasNext()) {
                int driverid = fileIn.nextInt();
                String driverlocation = fileIn.nextLine().trim();
                for (int i = 0; i < locations.size(); i++) {
                    if (locations.get(i).name.equals(driverlocation)) {
                        driverspot = locations.get(i);
                        driverfrompickup = driverspot.minDistance;
                        drivers.insert(driverfrompickup,
                                driverid, driverlocation);
                    }
                }
            }
            fileIn.close();
            return drivers;
        } catch  (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        }
    }




    /**
     * Main method.
     * @param args   hmmmmm
     */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println();
        String numDrivers = args[0];
        String locationname = args[1];
        String connectionname = args[2];
        String driversname = args[ARG_THREE];
        
        ArrayList<Location> locations = Taxis.locationsFromFile(locationname);
        countDriverNumbers(driversname);
        System.out.println("Collecting map locations from " 
             + locationname + "...");
        System.out.println(locationnumber 
             + " locations input.");
        System.out.println();
        ArrayList<Location> locs = Taxis.createMap(locations, connectionname);
        System.out.println("Collecting map connections from "
             + connectionname + "...");
        System.out.println(connectionnumber + " connections input.");
        System.out.println();
        System.out.println("Collecting map connections from " 
             + driversname + "...");
        System.out.println(drivernumber 
             + " drivers input.");
        System.out.println();
        
        System.out.println("Map locations are: ");
        System.out.println();
        printLocations(locations);
        System.out.println();
        
        System.out.println("Enter number of recent "
             + "client pickup request location: ");
        int pickup = kb.nextInt();
        Location pickuplocation = locations.get(pickup - 1);
        
        
        DriversPq drivers = Taxis.createDrivers(driversname, 
             pickuplocation, locations, locs);
        
        int numberofdrivers = Integer.parseInt(numDrivers);
        if (drivernumber < numberofdrivers) {
            numberofdrivers = drivernumber;
        }
        System.out.println("The " + numberofdrivers
             + " drivers to alert about this pickup are: ");
        System.out.println();
        for (int i = 1; i <= numberofdrivers; i++) {
            System.out.println(drivers.findMin().getId() 
                + " at " + drivers.deleteMin().getAddress());
        }

        System.out.println();
        System.out.println("Enter the ID number of the driver who responded: ");
        int idnumber = kb.nextInt();
        kb.close();
        System.out.println();

        DriversPq driversnew = 
                Taxis.createDrivers(
                driversname, pickuplocation, locations, locs);
        for (int i = 1; i <= driversnew.size; i++) {
            if (driversnew.array[i].getId() == idnumber) {
                String addr = driversnew.array[i].getAddress();
                Double time = driversnew.array[i].getKey();
                for (int j = 0; j < locations.size(); j++) {
                    if (locations.get(j).name.equals(addr)) {
                        Location spot = locations.get(j);
                        
                        System.out.println("The recommended route for driver " 
                            + idnumber + " is: ");
                        while (spot.previous != null) {
                            System.out.println("       " + getPathTo(spot));
                            spot = spot.previous;
                        }
                        System.out.println("      Expected total time: " 
                            + time + " minutes.");
                    }
                }
            }
        }

    } 
 
}