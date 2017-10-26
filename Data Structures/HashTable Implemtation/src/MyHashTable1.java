/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: kratikaj
 * @author Kratika Jain
 */
public class MyHashTable1 implements MyHTInterface {
    /**
     * Deleted value for deleted items from hashArray.
     */
    private static final DataItem DELETED = new DataItem("#DEL#", 0);
    private int collisionNum = 0;
    private int count = 0;
    private int length;
    private DataItem[] item;

    

    // TODO implement constructor with initial capacity
    public MyHashTable1(int newLength) {
        this.length = newLength;
        if(this.length <= 0) {
            throw new RuntimeException("Illegal length");
        } else {
            this.item = new DataItem[this.length];
        }
    }
    // TODO implement constructor with no initial capacity
    public MyHashTable1() {
        this.item = new DataItem[10];
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
    // TODO implement required methods
    private int hashFunc(String input) {
        // TODO implement this
        int len = input.length();
        int hashVal = 0;
        for (int i = 0; i < len; i++) {
            hashVal = Math.abs(hashVal * 27) + Math.abs(((int) input.charAt(i) - 96));
            hashVal = hashVal % this.length;
        }
        return hashVal;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        System.out.print("Rehashing " + count + " items, ");
        this.collisionNum = 0;
        this.count = 0;
        int oldLength = this.length;
        int newLength = oldLength * 2;
        while (!(prime(newLength))) {
            newLength = newLength + 1;
        }
        this.length = newLength;
        System.out.println("new size is " + length);
        DataItem[] newT = new DataItem[length];
        for (int i = 0; i < oldLength; i++) {
            if (item[i] != null && !item[i].value.equals("#DEL#")) {
                int index = this.hashValue(this.item[i].value);
                if (newT[index] != null) {
                    if(this.hashFunc(newT[index].value) == (this.hashFunc(this.item[i].value))) {
                        collisionNum++;
                    }
                }
                DataItem myData = new DataItem(this.item[i].value, this.item[i].frequency);
                for (int j = index; j <= length; j++) {
                    if (j == length) {
                        j = 0;
                    }
                    if (newT[j] != null && !newT[j].value.equals("#DEL#")) {
                        continue;
                    } else {
                        newT[j] = myData;
                        this.count++;
                        break;
                    }
                }

            }
        }
        this.item = newT;
    }
    private boolean prime(int n) {
        for(int i = 2; i < n; i++) {
            if(n % i == 0)
                return false;
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

        public DataItem(String val, int fr) {
            this.value = val;
            this.frequency = fr;
        }
        // TODO implement constructor and methods
    }

    public void insert(String value) {
        if (!value.matches("[a-z]+") || value == null|| value.equals("")) {
            return;
        }
        int i = hashValue(value);
        if(!(contains(value))) {
            DataItem myData = new DataItem(value,1);
            int index = hashValue(value);
            while (item[index] != null && item[index] != DELETED) {
                if (i == hashValue(item[index].value)) {
                    collisionNum++;
                    break;
                }
                index ++;
                index = index % length;
            }
            while (item[index] != null && item[index] != DELETED) {
                index ++;
                index = index % length;
            }
            item[index] = myData;
            count ++;
            if(((double) this.size() / (double) this.length) > 0.5) {
                rehash();
            }
        } else {
            while (!item[i].value.equals(value)) {
                i++;
                i = i % length;
            }
            item[i].frequency = item[i].frequency + 1;
        }

    }

    public int size() {
        // TODO Auto-generated method stub
        return count;
    }

    public void display() {
        StringBuilder show = new StringBuilder();
        StringBuilder nullString = new StringBuilder("** ");
        StringBuilder deletedString = new StringBuilder("#DEL#");
        int length1 = this.length;
        for (int i = 0; i < (length1 - 1); i++) {
            if (this.item[i] == null) {
                show.append(nullString);
            } else if (this.item[i].value.equals("#DEL#")) {
                show.append(deletedString);
            } else {
                show.append("[");
                show.append(this.item[i].value);
                show.append(", ");
                show.append(this.item[i].frequency);
                show.append("]");
                show.append(" ");
            }
        }
        if (this.item[length1 - 1] == null) {
            show.append("**");
        } else if (this.item[length1 - 1].value.equals("#DEL#")) {
            show.append("#DEL#");
        } else {
            show.append("[");
            show.append(this.item[length1-1].value);
            show.append(", ");
            show.append(this.item[length1-1].frequency);
            show.append("]");
            show.append(" ");
        }
        System.out.println(show.toString());
        return;
    }

    public boolean contains(String key) {
        if (key == null || !(key.matches("[a-z]+"))) {
            return false;
        }
        int len = this.length;
        for (int i = 0; i < len; i++) {
            if (this.item[i] == null) {
                continue;
            } else if (this.item[i].value.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int numOfCollisions() {
        // TODO Auto-generated method stub
        return collisionNum;
    }

    public int hashValue(String value) {
        if (value == null || !(value.matches("[a-z]+"))) {
            System.out.println("Illegal Value");
        }
        int hashVal = this.hashFunc(value);
        return hashVal;
    }

    public int showFrequency(String key) {
        if (key == null || !(key.matches("[a-z]+"))) {
            return 0;
        } else {
            if (contains(key)) {
                int hashVal = hashValue(key);
                while (!(key.equals(item[hashVal].value))) {
                    hashVal++;
                    hashVal = hashVal % this.length;
                }
                return item[hashVal].frequency;
            } else {
                return 0;
            }
        }
    }

    public String remove(String key) {
        if (key == null || !(key.matches("[a-z]+"))) {
            return null;
        }
        if(contains(key)) {
            int hashVal = hashValue(key);
            while (!(key.equals(item[hashVal].value))) {
                hashVal++;
                hashVal = hashVal % this.length;
            }
            String temp = item[hashVal].value;
            item[hashVal].value = "#DEL#";
            count--;
            return temp;
        }
        return null;
    }

}