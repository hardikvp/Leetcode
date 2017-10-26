import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 1 MyArray
 */
public class HW1Driver {
    /**
     * Test driver for MyArray.
     * @param args arguments
     */
    public static void main(String[] args) {
        // creates MyArray object with initial capacity
        // 0 is allowed and the code should work
        MyArray words = new MyArray(0);
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("childrensbible.txt"), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");

                /*
                 * when inserting, duplicates are allowed
                 * add method should take care of validating words
                 * array should double its length when necessary
                 */
                for (String word : wordsFromText) {
                    words.add(word.toLowerCase());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        String testWord = new String("dav");
        // find a word in the words array
        if (words.search(testWord)) {
            System.out.println("david is found");
        } else {
            System.out.println("not found");
        }

        // print current number of words
        System.out.println("Number of words in the file is : " + words.size());

        /*
         * print capacity, or the current length, of the array
         * capacity will be increased as necessary based on doubling-up policy
         */
        System.out.println("Capacity of words array is: " + words.getCapacity());

        // print words in the words array
        words.display();

        /*
         * remove all of the duplicates in the words array
         * Think carefully about how you would perform!
         * 1. you are NOT allowed to use Java Collections Framework
         *    Arrays class is a part of Java Collections Framework. Do not use it!
         * 2. you are NOT allowed to use any other data structures
         * 3. you are NOT allowed to use any sorting algorithms
         */
        words.removeDups();

        System.out.println("Number of words w/o duplicates is : " + words.size());
        System.out.println("Capacity of words array is: " + words.getCapacity());
        words.display();
    }
}