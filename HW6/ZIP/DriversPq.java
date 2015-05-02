//package taskthree;
/**
 * Drivers priority queue.
 * @author Suyi
 * 
 */
public class DriversPq {

    /**
    Final int.
    */
    private static final int DEFAULT_CAPACITY = 10;

    /**
    Creates an array of entries.
    */
    protected Driver[] array;

    /**
    The size of the pq.
    */
    protected int size;

    /**
     * Constructs a default pq.
     */
    public DriversPq() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructs a default pq.
     * @param initial initial capacity
     */
    public DriversPq(int initial) {
        this.array = new DriversPq.Driver[initial];
        this.size = 0;
    }
    
    /**
     * Add an element.
     * @param x the entry to be added
     * @param value the value to be stored
     * @param add the address of the driver
     */
    public void insert(Double x, int value, String add) {
        Driver e = new Driver(x, value, add);
        if (this.size == this.array.length - 1) {
            this.array = this.enlargeArray(this.array.length * 2 + 1);
        }            
        int hole = ++this.size;
        for (this.array[0] = e; x
            < this.array[hole / 2].getKey(); hole /= 2) {
            this.array[hole] = this.array[hole / 2];
        }
        this.array[hole] = e;
        
    }
    
    /**
     * Enlarge the array.
     * @param number the new size
     * @return the new array
     */
    public Driver[] enlargeArray(int number) {
        Driver[] tmp = new Driver[number];
        for (int i = 0; i < this.array.length; i++) {
            tmp[i] = this.array[i];
        }
        return tmp;
    }

    
    /**
     * Delete the min value.
     * @return the deleted item
     */
    public Driver deleteMin() {
        if (this.size == 0) {
            return null;
        } else {        
            Driver item = this.findMin();
            this.array[1] = this.array[this.size--];
            this.percolateDown(1);
            return item;
        }
    }
    

    
    /**
     * Delete the min.
     * @return the min item
     */
    public Driver findMin() {
        if (this.size == 0) {
            return null;
        } 
        return this.array[1];
    }
    
    /**
     * Percolate down.
     * @param hole the position that needs to perlocate down
     */
    public void percolateDown(Integer hole) {
        int child;
        Driver tmp = this.array[hole];
        for ( ; hole * 2 <= this.size; hole = child) {
            child = hole * 2;
            if (child != this.size
                    && this.array[child + 1].getKey()
                        < this.array[child].getKey()) {
                child++;
            }
            if (this.array[child].getKey() < tmp.getKey()) {
                this.array[hole] = this.array[child];
            } else {
                break;
            }
        }
        this.array[hole] = tmp;
    }
    
    
    /**
    An entry class.
    */
    public class Driver {
   
      /** A reference to the key. */
        private Double key;
  
      /** A reference to the value. */
        private Integer id;
        
      /**
       * The address of the driver.
       */
        private String address;
        
       
      /** A reference check if the value is deleted. */
        private boolean isDeleted;

       /**
        * Construct the key entry.
        * @param parakey the key of the driver
        * @param paravalue the value of the driver
        * @param paraaddress the location of the driver
        */
        public Driver(Double parakey, int paravalue, String paraaddress) {
            this.key = parakey;
            this.id = paravalue;
            this.address = paraaddress;
            this.isDeleted = false;
        }

       /**
        * Get the key of the Entry.
        * @return the key
        */
        public Double getKey() {
            return this.key;
        }
       
       /**
        * Get the value of the Entry.
        * @return the value
        */
        public int getId() {
            return this.id;
        }
        
        /**
         * Set the key.
         * 
         */
        public void setKey() {
            this.key++;
        }
       
        /**
         * Get the address of the token.
         * @return the address of the driver
         */
        public String getAddress() {
            return this.address;
        }


       /**
        * Put entry to a string.
        * @return the string representation of the entry
        */
        public String toString() {
            if (this.isDeleted) {
                return "Entry<" + this.key + "," + this.id + ">(X)";
            }
            return "Entry<" + this.key + "," + this.id + this.address + ">";
        }

    }
}