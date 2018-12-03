
/**
 * @author Conor Evans
 * @version 1.0 
 * @date 08/04/2018
 */
import java.util.InputMismatchException;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	/**
	 * @param filename:
	 *            A filename containing the details of the city road network
	 * @param sA,
	 *            sB, sC: speeds for 3 contestants
	 */
	private int V; // num vertices
	private double[][] distTo; // array of distances between vertices
	private double longestDistance;
	private double slowestSpeed;
	private double weight;
	private boolean validFile;
	private int startVertex;
	private int destinationVertex;

	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

		// if invalid speed returned from min(), set sS to -1
		if (sA <= 0 || sB <= 0 || sC <= 0)
			slowestSpeed = -1.0;
		// store speed of slowest contestant
		// divide by 1000 to turn speeds (km) to be in same unit
		// as weights (m)
		else
			slowestSpeed = (double) min(sA, sB, sC) / 1000;
		try {
			In in = new In(filename);
			// if it gets to this point, vF = true
			validFile = true;
			if (in.hasNextLine()) {
				V = in.readInt();
				distTo = new double[V][V];
			} else {
				validFile = false;
				return;
			}
			if (in.hasNextLine()) {
				@SuppressWarnings("unused") // having issues skipping line,
											// readLine() is maintaining the
											// line in
											// the input stream
				int edges = in.readInt();
			} else {
				validFile = false;
				return;
			}

			// set each weight to infinity, to be updated.
			for (int i = 0; i < V; i++)
				for (int j = 0; j < V; j++)
					distTo[i][j] = Double.POSITIVE_INFINITY;

			// set each vertex's distance to itself to 0
			for (int i = 0; i < V; i++)
				distTo[i][i] = 0.0;

			// if no vertex/weight details available, file invalid
			if (!in.hasNextLine()) {
				validFile = false;
				return;
			}
			while (in.hasNextLine()) {
				// get values for each line in file
				if (in.hasNextInt())
					startVertex = in.readInt();
				else {
					validFile = false;
					return;
				}
				if (in.hasNextInt())
					destinationVertex = in.readInt();
				else {
					validFile = false;
					return;
				}
				if (in.hasNextDouble())
					weight = in.readDouble();
				else {
					validFile = false;
					return;
				}
				distTo[startVertex][destinationVertex] = weight;
			}
		} catch (NullPointerException Exception) {
			validFile = false;
		} catch (InputMismatchException Exception) {
			validFile = false;
		}

		// for each path from a to c, check if the weight is more than the path
		// from a to an intermediate point b + the weight from b to c.
		// If so, we reroute the path from
		// a->c to a->b->c, and update the weights.
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				for (int k = 0; k < V; k++)
					if (distTo[j][k] > distTo[j][i] + distTo[i][k])
						distTo[j][k] = distTo[j][i] + distTo[i][k];
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition() {
		// if an invalid speed was entered, or the file was invalid, return -1.
		if (slowestSpeed == -1.0 || validFile == false)
			return -1;
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				// we have already updated the values in the constructor
				// so if any value has not been updated (i.e. if no path found)
				// we return -1.
				if (distTo[i][j] == Double.POSITIVE_INFINITY)
					return -1;
				// if current distance from vertex i to j is longer than the
				// current stored
				// longest distance, update the value of the longest distance.
				else if (distTo[i][j] > longestDistance)
					longestDistance = distTo[i][j];
		return (int) Math.ceil(longestDistance / slowestSpeed);
	}

	/**
	 * @return boolean: best to keep the variable private, so just have a getter
	 *         function for it.
	 */
	public boolean validFile() {
		return this.validFile;
	}

	/**
	 * @return int: minimum speed of 3 contestants.
	 */
	public static int min(int a, int b, int c) {
		if (a <= b && a <= c)
			return a;
		else if (b <= a && b <= c)
			return b;
		else
			return c;
	}

}