/**
 * @author Conor Evans
 **/
public class EdgeWeightedDigraph {
	private final int V; // #vertices
	private Bag<DirectedEdge>[] adj;

	@SuppressWarnings("unchecked") // @bag declaration
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<DirectedEdge>();
	}

	public int V() {
		return V;
	}

	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
}
