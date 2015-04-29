import java.util.ArrayList;

/**
 * A directed map.
 * @author dsun
 *
 */
public class DirectedCharMap {
    /** ArrayList to contain the nodes. */
    ArrayList<DirMapNode> nodes;
    

    /** Constructor. */
    public DirectedCharMap() {
        this.nodes = new ArrayList<DirMapNode>();
    }
    
    /**
     * Given 2 characters, create a path between the two.
     * If the map does not already contain a character, create
     * a node for it. Path goes from c1 -> c2.
     * @param c1 first char
     * @param c2 second char
     */
    public void createPath(char c1, char c2) {
        DirMapNode temp1 = this.getNode(c1);
        DirMapNode temp2 = this.getNode(c2);
        if (temp1 == null) {
            temp1 = this.addNewNode(c1);
        }
        
        if (temp2 == null) {
            temp2 = this.addNewNode(c2);
        }
        
        if (!temp1.pointsTo(temp2)) {
            temp1.addOut(temp2);
        }
    }
    
    /**
     * Adds a new node given a character.
     * @param c character
     * @return the node created
     */
    public DirMapNode addNewNode(char c) {
        DirMapNode node = new DirMapNode(c);
        this.nodes.add(node);
        return node;
    }
    
    /**
     * Returns the node matching the character.
     * @param c char to search for
     * @return the node or null if not found
     */
    public DirMapNode getNode(char c) {
        for (DirMapNode n: this.nodes) {
            if (n.getChar() == c) {
                return n;
            }
        }
        return null;
    }
    
    /**
     * The topological sort.
     * After sorting, any node in this.nodes will come
     * earlier than any other that it points to.
     * 
     * After running this method the map will be pretty
     * much unusable. Unsure how to get around this.
     * 
     * Will go into infinite loop if the given sorted
     * dictionary is inadequate.
     * 
     * @return a new ArrayList containing the sorted characters
     */
    public ArrayList<Character> topoSort() {
        ArrayList<Character> sorted = new ArrayList<Character>();
        
        while (this.nodes.size() > 0) {
            this.topoHelper(sorted);
        }
        
        return sorted;
    }
    
    /**
     * Helper.
     * @param sorted array
     */
    private void topoHelper(ArrayList<Character> sorted) {
        for (DirMapNode n: this.nodes) {
            if (n.indegree(this.nodes) == 0) {
                sorted.add(new Character(n.getChar()));
                this.nodes.remove(n);
                for (DirMapNode n2: this.nodes) {
                    n2.remove(n);
                }
                //System.out.println("Break here");
                break;
            }
        }
    }
    
    /**
     * Testing.
     */
    public void printMap() {
        for (DirMapNode n: this.nodes) {
            System.out.print("" + n.getChar() + ": points to - ");
            for (DirMapNode n2: n.out) {
                System.out.print("" + n2.getChar() + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Internal class for a node in a directed map.
     */
    private class DirMapNode {
        /** Char this node contains. */
        char c;
        /** List of nodes this node points to. */
        ArrayList<DirMapNode> out;
        /**
         * Constructor.
         * @param character the char this node will contain
         */
        public DirMapNode(char character) {
            this.c = character;
            this.out = new ArrayList<DirMapNode>();
        }
        
        /**
         * Getter.
         * @return the character this node contains
         */
        public char getChar() {
            return this.c;
        }
        
        /**
         * Adds a node that this one will direct to on the graph.
         * @param node the node to be added
         */
        public void addOut(DirMapNode node) {
            this.out.add(node);
        }
        
        /**
         * Determines if this node directs to the given node.
         * @param node to be compared
         * @return true/false
         */
        public boolean pointsTo(DirMapNode node) {
            for (DirMapNode n: this.out) {
                if (n.getChar() == node.getChar()) {
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Searches a given list to see how many nodes point to this one.
         * @param list a given arraylist
         * @return the indegree
         */
        public int indegree(ArrayList<DirMapNode> list) {
            int n = 0;
            for (DirMapNode node: list) {
                if (node.pointsTo(this)) {
                    n++;
                }
            }
            return n;
        }
        
        /**
         * Removes the path from this node to a specified one, if existing.
         * @param node a specified destination node
         */
        public void remove(DirMapNode node) {
            int size = this.out.size();
            for (int i = 0; i < size; i++) {
                if (this.out.get(i).getChar() == node.getChar()) {
                    this.out.remove(i);
                    return;
                }
            }
        }
    }
}
