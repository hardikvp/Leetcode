import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* for directed graphs*/

public class EdgeWeightedGraph {
    private int V;
    private int E;
    private ArrayList<Node> [] adj;
    
    private class Node {
        private int key;
        private double value;
        
        public Node(int k, double v) {
            this.key = k;
            this.value = v;
        }
    }
    
    public EdgeWeightedGraph() {
        ArrayList<String> l  = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("tinyEWG.txt"), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split(" ");
                for (String word : wordsFromText) {
                    l.add(word);
                }
            }
                this.V = Integer.parseInt(l.get(0));
                this.adj = new ArrayList [V] ;
                for(int i = 0; i < V ; i++) {
                     adj[i] = new ArrayList<Node>();
                }
                this.E = Integer.parseInt(l.get(1));
                for (int i = 2; i < l.size() - 1; i = i+3) {
                    int v = Integer.parseInt(l.get(i));
                    int w = Integer.parseInt(l.get(i+1));
                    double d = Double.parseDouble(l.get(i+2));
                    Node n = new Node(w,d);
                    Node n1 = new Node(v,d);
                    addEdge(v, n);
                }
               
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    public int V() {
        return V;
    }
    
    public int E() {
        return E;
    }
    
    public ArrayList<Node> adj(int v) {
        return adj[v];
    }

    
    public void addEdge(int v, Node n) {
        adj[v].add(n);
        
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V :" + this.V + " E :" + this.E + "\n");
        for(int i = 0; i < V; i++) {
            s.append(i +" : " );
            for(int j = 0; j < adj[i].size(); j++) {
                s.append((adj[i].get(j).key +"-" + adj[i].get(j).value +" "));
            }
            s.append("\n");
        }
        return s.toString();
    }
        

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph();
        System.out.println(g.toString());
        }

}
