
public class dsfrecursive {
    private boolean [] marked;
    private int count;
    
    public dsfrecursive(Graph G, int s) {
        this.marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        
    }
    
    public int count() {
        return count;
    }
    public boolean marked(int v) {
        return marked[v];
    }

}
