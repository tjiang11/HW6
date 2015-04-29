import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

/**
 * Task 2.
 * @author dsun
 *
 */
public final class CodeSort {
    /** Number of arguments. */
    private static final int NUMARGS = 3;
    /** Checkstyle. */
    private CodeSort() {
    }
    
    /** 
     * The real slim shady. 
     * @param args command line stuff specifying filenames
     */
    public static void main(String[] args) {
        if (args.length != NUMARGS) {
            System.out.println("Argument error: "
                    + "Specify the sorted input filename, "
                    + "the unsorted input filename, and the output filename");
            return;
        }
        String infile = args[0];
        String unsorted = args[1];
        String outfile = args[2];
        Scanner in;
        Scanner in2;
        ArrayList<String> wordlist = new ArrayList<String>();
        ArrayList<String> unsortedlist = new ArrayList<String>();
        ArrayList<Character> alph = new ArrayList<Character>();
        DirectedCharMap map = new DirectedCharMap();
        
        try {
            in = new Scanner(new FileReader(infile));
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading sorted file!");
            return;
        }
        while (in.hasNext()) {
            wordlist.add(in.next());
        }
        in.close();
        
        try {
            in2 = new Scanner(new FileReader(unsorted));
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading unsorted file!");
            return;
        }
        while (in2.hasNext()) {
            unsortedlist.add(in2.next());
        }
        in2.close();
        
        loadMap(wordlist, map);
        alph = map.topoSort();
        ArrayList<String> sortedlist = new ArrayList<String>();
        
        for (String s1: unsortedlist) {
            insertToSorted(s1, sortedlist, alph);
        }
        
        try {

            File output = new File(outfile);
            if (!output.exists()) {
                output.createNewFile();
            }

            PrintWriter write = new PrintWriter(output);
            for (String s: sortedlist) {
                write.println(s);
            }
            write.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    /**
     * Helper method to load up the directed graph.
     * @param wordlist arraylist of sorted words
     * @param map given graph (should be empty to begin)
     */
    public static void loadMap(ArrayList<String> wordlist, 
            DirectedCharMap map) {
        int index = 1;
        while (index < wordlist.size()) {
            String s1 = wordlist.get(index - 1);
            String s2 = wordlist.get(index);
            
            int charindex = 0;
            
            while (charindex < s1.length() && charindex < s2.length()) {
                char c1 = s1.charAt(charindex);
                char c2 = s2.charAt(charindex);
                if (c1 != c2) {
                    map.createPath(c1, c2);
                    break;
                }
                charindex++;
            }
            
            index++;
        }
    }
    
    /**
     * Tells if the first word should come before the second.
     * @param w1 first word
     * @param w2 second word
     * @param charlist sorted order of the 'alphabet'
     * @return whether or not w1 comes before w2
     */
    public static boolean comesBefore(String w1, String w2, 
            ArrayList<Character> charlist) {
        
        int charindex = 0;
        while (charindex < w1.length() && charindex < w2.length()) {
            char c1 = w1.charAt(charindex);
            char c2 = w2.charAt(charindex);
            if (charlist.indexOf(c1) == charlist.indexOf(c2)) {
                charindex++;
            } else {
                boolean result = charlist.indexOf(c1) < charlist.indexOf(c2);
                return result;
            }
        }
        return false;
    }
    
    /**
     * Inserts the word into a sorted list.
     * @param word to be inserted
     * @param sortedlist list to be inserted into
     * @param alph the alphabet in the correct order
     */
    public static void insertToSorted(String word, 
            ArrayList<String> sortedlist, ArrayList<Character> alph) {
        
        int index = 0;
        
        while (index < sortedlist.size() 
                && comesBefore(sortedlist.get(index), word, alph)) {
            index++;
        }
        sortedlist.add(index, word);
    }
}
