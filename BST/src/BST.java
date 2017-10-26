
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    
    private class Node {
     private Key key;
     private Value val;
     private Node left, right;
     private int count;
     
     public Node(Key key, Value val) {
     this.key = key;
     this.val = val;
     
     }
    }
    public void put(Key key, Value val) { 
        root = put(root, key, val); 
        }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else if (cmp == 0)
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        System.out.println(x.count);
        return x;
    }
   
   
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) 
                x = x.left;
            else if (cmp > 0) 
                x = x.right;
            else if (cmp == 0) 
                return x.val;
            }
        return null;
    }
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) 
            return null;
        return x.key;
        }
    private Node floor(Node x, Key key) {
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
            return x;
        if (cmp < 0) 
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) 
            return t;
        else 
            return x;
        } 
    public Key cieling (Key key)
    {
     Node x = cieling(root, key);
     if (x == null) return null;
     return x.key;
    }
    private Node cieling(Node x, Key key) {
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
            return x;
        if (cmp < 0) {
            Node t = cieling(x.left, key); 
        if (t != null) return t;
        else return x; 
    } 
    return cieling(x.right, key); 
    }
    public int size() { 
        return size(root); 
        }
    private int size(Node x) {
        if (x == null) 
            return 0;
        return x.count;
    }
    public void deleteMin() { 
        root = deleteMin(root); 
        }
    private Node deleteMin(Node x) {
        if (x.left == null) 
            return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    public void delete(Key key) { 
        root = delete(root, key); 
        }
    private Node delete(Node x, Key key) {
        if (x == null) 
            return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) 
        x.left = delete(x.left, key);
    else if (cmp > 0) 
        x.right = delete(x.right, key);
    else {
        if (x.right == null) 
            return x.left;
        if (x.left == null) 
            return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        }
    x.count = size(x.left) + size(x.right) + 1;
    return x;
    } 
    
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            st.put(key, i);
        }
        
    }
}