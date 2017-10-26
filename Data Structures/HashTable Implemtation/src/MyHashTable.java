/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID:
 * @author
 */
public class MyHashTable implements MyHTInterface {
    /**
     * Deleted value for deleted items from hashArray.
     */
    private static final DataItem DELETED = new DataItem("#DEL#", 0);
    /**
     * Default capacity of hashArray.
     */
    private int length;
    /**
     * Underlying array of DataItem.
     */
    private DataItem[] d;
    /**
     * Instance variable to keep track of number of collisions.
     */
    private int collisionNum = 0;
    /**
     * Variable to keep track of hashArray size.
     */
    private int count = 0;
    /**
     * Constructor when initial capacity is passed.
     * @param newLength initial capacity of MyHashTable
     */
    public MyHashTable(int newLength) {
        this.length = newLength;
        if (this.length <= 0) {
            throw new RuntimeException("Illegal length");
        } else {
            this.d = new DataItem[this.length];
        }
    }

    /**
     * Default constructor for MyHashTable.
     */
    public MyHashTable() {
        this.d = new DataItem[10];
        this.length = 10;
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */

    private int hashFunc(String input) {
        int len = input.length();
        int hash = 0;
        for (int i = 0; i < len; i++) {
            hash = Math.abs(hash * 27) + Math.abs(((int) input.charAt(i) - 96));
            hash = hash % this.length;
        }
        return hash;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        System.out.print("Rehashing " + count + " items, ");
        int length0 = this.length;
        this.collisionNum = 0;
        this.count = 0;
        int length1 = this.length * 2;
        while (!(isPrime(length1))) {
            length1 = length1 + 1;
            }
        this.length = length1;
        System.out.println("new size is " + length);
        DataItem[] newMyTable = new DataItem[length];
        for (int i = 0; i < length0; i++) {
            if (d[i] != null && !d[i].value.equals("#DEL#")) {
                int index = this.hashValue(d[i].value);
                if (newMyTable[index] == null) {
                    newMyTable[index] = d[i];
                    count++;
                } else {
                    while (newMyTable[index] != null) {
                        if (this.hashFunc(d[i].value) == hashFunc(newMyTable[index].getValue())) {
                            collisionNum++;
                            break;
                        }
                        index++;
                        index = index % length;
                    }
                    while (newMyTable[index] != null && newMyTable[index] != DELETED) {
                        index++;
                        index = index % length;
                    }
                    newMyTable[index] = d[i];
                    this.count++;
                    }
                }
            }
        d = newMyTable;
    }
    /**
     * Finds if the given number is a prime.
     * @param n Current size it accepted as the input
     * @return true or false if number if prime or no
     */
    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;
        /**
         * Constructor of the private static nested class DataItem.
         * @param val String that needs to be stored in the Hash table
         * @param fr Number of times the String was added to the has table
         */
        public DataItem(String val, int fr) {
            this.value = val;
            this.frequency = fr;
        }
        /**
         * String value.
         * @return value of the string
         */
        public String getValue() {
            return value;
        }
        /**
         * Getter method for frequency.
         * @return frequency of DataItem
         */
        public int getFreq() {
            return frequency;
        }
        /**
         * Setter method for frequency.
         * @param f frequency of DataItem.
         */
        public void setFreq(int f) {
            frequency = f;
        }
    }
    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    public void insert(String value) {
        if (value == null || value.equals("") || !value.matches("[a-z]+")) {
            return;
        }
        int i = hashValue(value);
        if (contains(value)) {
            while (!d[i].value.equals(value)) {
                i++;
                i = i % length;
            }
            d[i].setFreq(d[i].getFreq() + 1);
        } else {
            DataItem myData = new DataItem(value, 1);
            int index = hashValue(value);
            while (d[index] != null && d[index] != DELETED) {
                if (i == hashValue(d[index].getValue())) {
                    collisionNum++;
                    break;
                }
                index++;
                index = index % length;
            }
            while (d[index] != null && d[index] != DELETED) {
                index++;
                index = index % length;
            }
            d[index] = myData;
            count++;
            if (((double) this.size() / (double) this.length) > 0.5) {
                rehash();
            }
        }
    }
    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    public int size() {
        // TODO Auto-generated method stub
        return count;
    }
    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    public void display() {
        StringBuilder show = new StringBuilder();
        StringBuilder nullString = new StringBuilder("** ");
        StringBuilder deletedString = new StringBuilder("#DEL# ");
        int len = this.length;
        for (int i = 0; i < (len - 1); i++) {
            if (this.d[i] == null) {
                show.append(nullString);
                //System.out.println(nullString.toString());
            } else if (this.d[i].value.equals("#DEL#")) {
                show.append(deletedString);
            } else {
                show.append("[");
                show.append(this.d[i].value);
                show.append(", ");
                show.append(this.d[i].getFreq());
                show.append("]");
                show.append(" ");
            }
        }
        if (this.d[len - 1] == null) {
            show.append("**");
        } else if (this.d[len - 1].value.equals("#DEL#")) {
            show.append("#DEL#");
        } else {
            show.append("[");
            show.append(this.d[len - 1].value);
            show.append(", ");
            show.append(this.d[len - 1].getFreq());
            show.append("]");
            show.append(" ");
        }
        System.out.println(show.toString());
        return;
    }
    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    public boolean contains(String key) {
        if (key != null && !key.equals("") && key.matches("[a-z]+")) {
            int hashVal = hashValue(key);
            while (d[hashVal] != null) {
                if (key.equals(d[hashVal].getValue())) {
                    return true;
                }
                hashVal++;
                hashVal = hashVal % d.length;
            }
        }
        return false;
    }
    /**
     * Returns the number of collisions in relation to insert and rehash.
     * When rehashing process happens, the number of collisions should be properly updated.
     *
     * The definition of collision is "two different keys map to the same hash value."
     * Be careful with the situation where you could overcount.
     * Try to think as if you are using separate chaining.
     * "How would you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    public int numOfCollisions() {
        return collisionNum;
    }
    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    public int hashValue(String value) {
        if (value == null) {
            System.out.println("Illegal input");
        }
        int hashVal = this.hashFunc(value);
        return hashVal;
    }
    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    public int showFrequency(String key) {
        if (key != null && !key.equals("") && key.matches("[a-z]+")) {
            if (contains(key)) {
                int hashVal = hashValue(key);
                while (!(key.equals(d[hashVal].getValue()))) {
                    hashVal++;
                    hashVal = hashVal % this.length;
                }
                return d[hashVal].getFreq();
            }
        }
        return 0;
    }
    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    public String remove(String key) {
        if (contains(key)) {
            int hashVal = hashValue(key);
            while (!(key.equals(d[hashVal].getValue()))) {
                hashVal++;
                hashVal = hashVal % this.length;
            }
            String temp = d[hashVal].getValue();
            d[hashVal] = DELETED;
            count--;
            return temp;
        }
        return null;
    }
}
