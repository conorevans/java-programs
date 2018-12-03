
/**
 * @author Conor Evans
 **/
public class DirectedEdge {
	private final int v; // source
	private final int w; // destination
	private final double weight; // weight

	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}

	public double weight() {
		return weight;
	}
}
