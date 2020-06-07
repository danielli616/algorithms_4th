import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
    private double weight;
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        weight = 0;
        visit(G, 0);

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(G, w);
            if (!marked[w]) visit(G, v);

        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge j : G.adj(v)) {
            if (!marked[j.other(v)]) pq.insert(j);
        }
    }

    public double weight() {
        return weight;
    }
}
