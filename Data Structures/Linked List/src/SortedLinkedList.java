/**
 *
 * @author Hardik
 * Andrew ID: hvp.
 *
 */
public class SortedLinkedList implements MyListInterface {
    /**
     * Head of a node.
     */
    private Node head = null;
    /**
     * Class of a node.
     */
    private class Node {
        /**
         * Reference to the next node.
         */
        private Node next;
        /**
         * Data of a node.
         */
        private String item;
        /**
         * Construct a new node with the data and next node reference.
         * @param s data of the node
         */
        public Node(String s) {
            this.item = s;
            this.next = null;
        }
    }
    /**
     * Constructor of linkedList.
     */
    public SortedLinkedList() {
    }
    /**
     * Constructor of linkedList.
     * @param unsorted string array
     */
    public SortedLinkedList(String[] unsorted) {
        if (unsorted == null) {
            head = null;
            return;
        }
        if (unsorted.length == 0) {
            head = null;
            return;
        }
        SortedLinkedList s = new SortedLinkedList();
        s.addUnsorted(unsorted, s, unsorted.length);
        head = s.head;
    }
    /**
     *
     * @param unsorted array.
     * @param s sortedlinkedlist.
     * @param i length.
     * @return sortedlinkedlist.
     */
    private SortedLinkedList addUnsorted(String[] unsorted, SortedLinkedList s, int i) {
        if (i == 1) {
            s.add(unsorted[0]);
            return s;
        }
        s.add(unsorted[i - 1]);
        return addUnsorted(unsorted, s, i - 1);
    }
    /**
     * Returns true if the key value is contained in the list.
     * @param testValue to be searched
     * @return true if found, false if not found
     */
    public boolean contains(String testValue) {
        return contains(head, testValue);
    }
    /**
     * Returns true if the key value is contained in the list.
     * @param s to be searched
     * @param x Node to be accessed
     * @return true if found, false if not found
     */
    private boolean contains(Node x, String s) {
        if (s == null) {
            return false;
        }
        if (x == null) {
            return false;
        } else {
            if (x.item.equals(s)) {
                return true;
            } else {
                x = x.next;
                return contains(x, s);

            }
        }
    }
    /**
     * Inserts a new String. No duplicates allowed and maintain the order.
     * @param value
     * String to be added.
     */
    public void add(String value) {
        head = add(head, value);
        }
    /**
     * Inserts a new String. No duplicates allowed and maintain the order.
     * @param x Node
     * @param s String
     * @return head String to be added.
     */
    private Node add(Node x, String s) {
        if (s == null) {
            return head;
        }
        if ((!(contains(s))) && s.matches("[a-zA-Z]+")) {
            if (x == null) {
                Node n = new Node(s);
                x = n;
                return x;
            } else if (s.compareTo(head.item) < 0) {
                Node n = new Node(s);
                n.next = head;
                head = n;
            } else {
                Node after = x.next;
                Node before = x;
                if (after != null) {
                    if (s.compareTo(after.item) < 0) {
                        Node n = new Node(s);
                        n.next = before.next;
                        before.next = n;
                    } else {
                        x = x.next;
                        add(x, s);
                    }
                } else {
                    Node n = new Node(s);
                    x.next = n;
                }
            }
        }
        return head;
    }
    /**
     * Checks the size of the list.
     * @return the size of the list
     */
    public int size() {
        return size(head);
        }
    /**
     * Checks the size of the list.
     * @param x Node to be checked
     * @return the size of the list
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.next);
    }
    /**
     * Displays the values of the list.
     */
    public void display() {
        StringBuilder sb = new StringBuilder("[");
        head  = display(head, sb);
    }
    /**
     * Removes and returns String object at the index.
     * @param x index value to be removed
     * @param s StringBuilder
     * @return Node to be displayed
     */
    private Node display(Node x, StringBuilder s) {
        if (x == null) {
            System.out.println(s.append("]"));
            return x;
        }
        if (x.next != null) {
            s.append(x.item);
            s.append(", ");
            display(x.next, s);
        } else {
            System.out.println(s.append(x.item + "]"));
        }
        return x;
    }
    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    public boolean isEmpty() {
        return head == null;
    }
    /**
     * Removes and returns the first node's String object of the list.
     * @return String object that is removed
     */
    public String removeFirst() {
        Node t = head;
        String s = null;
        if (t == null) {
            return null;
        } else {
            s = t.item;
            head = t.next;
            t = null;
        }
        return s;
    }
    /**
     * Removes and returns String object at the index.
     * @param index index value to be removed
     * @return String object that is removed
     */
    public String removeAt(int index) {
        String s = removeAt(head, index);
        return s;
    }
    /**
     * Removes and returns String object at the index.
     * @param i index value to be removed
     * @param x Node to be accessed
     * @return String object that is removed
     */
    private String removeAt(Node x, int i) {
        if (x == null) {
            return null;
        } else if (i == 0) {
            return removeFirst();
        } else {
            Node after = x.next;
            Node before = x;
            if (i != 1) {
                x = x.next;
                return removeAt(x, i--);
            }
            String s = after.item;
            before.next = after.next;
            return s;
        }
    }
}
