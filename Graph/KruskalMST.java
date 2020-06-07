import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private double weight;
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        UF uf = new UF(G.V());
        weight = 0;

        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {

            Edge e = pq.delMin();

            int v = e.either();
            int w = e.other(v);

            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
