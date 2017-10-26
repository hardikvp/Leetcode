        import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class diaGraph {
    private int V;
    private int E;
    @SuppressWarnings("rawtypes")
    private ArrayList [] adj;
    
    public diaGraph() {
        ArrayList<String> l  = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("tinyDG.txt"), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    l.add(word);
                }
            }
                this.V = Integer.parseInt(l.get(0));
                this.adj = new ArrayList [V] ;
                for(int i = 0; i < V ; i++) {
                     adj[i] = new ArrayList<Integer>();
                }
                this.E = Integer.parseInt(l.get(1));
                for (int i = 2; i < l.size() - 1; i = i+2) {
                    int v = Integer.parseInt(l.get(i));
                    int w = Integer.parseInt(l.get(i+1));
                    addEdge(v, w); 
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
    @SuppressWarnings("unchecked")
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @SuppressWarnings("unchecked")
    public void addEdge(int v, int w) {
        adj[v].add(w);
        
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V :" + this.V + "E :" + this.E + "\n");
        for(int i = 0; i < V; i++) {
            s.append(i +" :" );
            for(int j = 0; j < adj[i].size(); j++) {
                s.append(adj[i].get(j)+ " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
        

    public static void main(String[] args) {
        diaGraph g = new diaGraph();
        System.out.println(g.toString());
        /*dsfrecursive d = new dsfrecursive(g,7);
        for (int v = 0; v < g.V(); v++) {
            if (d.marked(v))
                System.out.println(v + " ");
        }*/


    }

}
