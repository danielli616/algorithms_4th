import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {
    private Edge[] edgeTo; // shortest edge from tree vertex
    private double[] distTo; // distTo[w] = edgeTo[w].weight()
    private double weight;
    private boolean[] marked;
    private Queue<Edge> mst;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());
        weight = 0;

        distTo[0] = 0.0;
        pq.insert(0, 0.0);

        while (!pq.isEmpty())
            visit(G, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge j : G.adj(v)) {

            int w = j.other(v);

            if (marked[w]) continue;

            if (j.weight() < distTo[w]) {

                edgeTo[w] = j;
                distTo[w] = j.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);

            }
        }
    }

    public double weight() {
        return weight;
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }
}
