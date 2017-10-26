import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 08722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 *
 * Andrew ID: hvp
 * @author Hardik Panchal
 */
public class Josephus {
    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        if ((size < 0) || (rotation <= 0)) {
            throw new RuntimeException("Invalid Size or rotation");
        }
        ArrayDeque<Integer> array = new ArrayDeque<Integer>();

        // Read numbers into queue
        for (int i = 0; i < size; i++) {
            array.addLast(i + 1);
        }
        // Rotate to find survivor
        while (size > 1) {
            if (size > rotation) {
                for (int i = 0; i < (rotation); i++) {
                    array.addLast(array.removeFirst());
                }
                array.removeLast();
                size = size - 1;
            } else {
                for (int i = 0; i < (rotation) % size; i++) {
                    array.addLast(array.removeFirst());
                    }
                array.removeLast();
                size = size - 1;
                }
            }
        return (Integer) array.peek();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        if ((size < 0) || (rotation <= 0)) {
            throw new RuntimeException("Invalid Size or rotation");
        }
        LinkedList<Integer> list = new LinkedList<Integer>();

        // Read numbers into queue
        for (int i = 0; i < size; i++) {
            list.addLast(i + 1);
        }
        // Rotate to find survivor
        while (size > 1) {
            if (size > rotation) {
                for (int i = 0; i < (rotation); i++) {
                    list.addLast(list.removeFirst());
                }
                list.removeLast();
                size = size - 1;
            } else {
                for (int i = 0; i < (rotation) % size; i++) {
                    list.addLast(list.removeFirst());
                    }
                list.removeLast();
                size = size - 1;
                }
            }
        return (Integer) list.peek();
        // TODO your implementation here
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        if ((size < 0) || (rotation <= 0)) {
            throw new RuntimeException("Invalid Size or rotation");
        }
        LinkedList<Integer> aList = new LinkedList<Integer>();
        int a = 0;
        for (int i = 0; i < size; i++) {
            aList.add(i + 1);
        }
        for (int i  = size; i > 1; i--) {
            a = a + (rotation - 1);
            a = a % i;
            aList.remove(a);
        }
        return aList.getFirst();
        // TODO your implementation here
    }
}
/*The most important aspect of this program is best possible time for large number of people.
 * In this case, I would use the LinkedList as a List method out of the three methods.
 * The LinkedList as a list uses the Iterator method whose runtime complexity
 * for addition and removal of elements is of O(1) i.e constant time.
 * The LinkedList as a List method is the fastest method to use as it incurs
 * zero rotations and rather eliminates people based on their lookup values.
 * Thus it is the best out of all the three data structures.
 * Out of ArrayDeque and LinkedList as a Queue,
 * ArrayDeque is better as it does not have the overhead of node allocations that
 * LinkedList has nor the overhead of shifting the array contents left
 * upon removal that ArrayDeque has.
 */

