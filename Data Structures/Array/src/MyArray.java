/**
 *
 * @author Hardik
 * Andrew ID: hvp.
 *
 */
public class MyArray {
    /**
     * Initializing String array.
     */
    private String[] wordsFromText;
    /**
     * Defining capacity to manipulate array.
     */
    private int capacity = 0;
    /**
     * Initializing the size of array to start with.
     */
    private int initialCapacity;
    /**
     * Default constructor for MyArray.
     */
    public MyArray() {
        /**
         * Initializing the array with a default capacity of 10.
         */
        wordsFromText = new String[10];
        initialCapacity = 1;
    }
    /**
     * @param newInitialCapacity the capacity with
     * Constructor with specifically mentioned initial capacity.
     */
    public MyArray(int newInitialCapacity) {
        initialCapacity = newInitialCapacity;
        wordsFromText = new String[initialCapacity];
            }
    /**
     * @param text to be added in the array
     * Method to add the text in the array.
     */
    void add(String text) {
        if (text == null) {
            return;
        }
        if (text.matches("[a-zA-Z]+")) {
            if (initialCapacity <= 0) {
                initialCapacity = 1;
               wordsFromText = new String[initialCapacity];
                } else if (capacity == initialCapacity) {
                    initialCapacity = (initialCapacity * 2);
                    String[] copy = new String[initialCapacity];
                    for (int i = 0; i < capacity; i++) {
                    copy[i] = wordsFromText[i];
                    }
                wordsFromText = copy;
                }
            wordsFromText[capacity] = text;
            capacity = capacity + 1;
            }
        }
    /**
     * Method to search for a key in the array.
     * @param key the key to search
     * @return boolean decision
     */
    boolean search(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (wordsFromText[i].equals(key)) {
                return true;
                }
            }
        return false;
        }
    /**
     * Method to get the size of the array.
     * @return size
     */
    int size() {
        return capacity;
    }
    /**
     * Method to get the updated capacity of the array.
     * @return capacity
     */
    int getCapacity() {
        return initialCapacity;
    }
    /**
     * Method to print the contents of the array.
     */
    void display() {
        for (int y = 0; y < capacity; y++) {
            if (y == capacity - 1) {
                System.out.print(wordsFromText[y] + "");
            } else {
            System.out.print(wordsFromText[y] + " ");
            }
        }
        System.out.println("");
    }
    /**
     * Method to remove duplicates from the array.
     */
    void removeDups() {
        for (int x = 0; x < capacity; x++) {
            for (int j = x + 1; j < capacity;) {
                if (wordsFromText[j].equals(wordsFromText[x])) {
                    for (int k = j; k < capacity; k++) {
                        wordsFromText[k] = wordsFromText[k + 1];
                        }
                    capacity--;
                    } else {
                        j++;
                    }
                }
            }
        }
    }
