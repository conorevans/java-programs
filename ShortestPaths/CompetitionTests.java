
/**
 * @author Conor Evans
 */

/**
*	1. Justify the choice of the data structures used in CompetitionDijkstra and
*	CompetitionFloydWarshall
*
*	DirectedEdge: a pretty simple data structure. It has three local variables to store the source vertex, the destination vertex, and the weight of the edge between them.
*	We need this DS as we need some way of representing a DirectedEdge in a graph.
*	
*	EdgeWeightedDigraph: Creates an array of vertices of size V. 
*	At each vertex, we have a bag (data structure explain further on) of directed edges functioning as an adjacency list. 
*	When adding a DirectedEdge e to our EWD, we update the adjacency list of the vertex pointing to e to include e. 
*	We need this DS as it is an efficient way of representing a graph of DirectedEdges as well as maintaining a record
*	of the DirectedEdges to/from each vertex.
*
*	Bag: A bag is a collection of items. In this case, the items are DirectedEdges. Sedgwick's Bag uses a single-linked list
*	with a nested class Node which has pointers to the current node in the list and the next node in the list.
*	We use the bag class as a way of representing the collection of edges in our EdgeWeightedDigraph DS. 
*
*	IndexMinPQ: used in DijkstraSP to represent an indexed priority queue of keys (in this case, vertices). 
*	This implementation uses a binary heap along with an array to associate keys with integers in the given range.
*	The IndexMinPQ is not the only way to manage this problem. Instead, a boolean[] could be used to track where in the graph
*	we have visited. However, IMPQ and its delMin() function allow us to relax the edge with minimal cost each time, an aspect 
*	which is harder to achieve with the boolean[] representation.
*
*	In: a basic data structure. Basically a stripped-back version of the Scanner class to include only what is needed for this
*	program.
*	
*
*	2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
*	in the given problem. Also explain how would their relative performance be affected by the
*	density of the graph. Which would you choose in which set of circumstances and why? 
*
*	Dijkstra's algorithm works on the basis of iterating through each edge of the adjacency list of edges. If the distTo from
*	the edge at the current index of the adjacency list is greater than
*	the distTo to the current edge we are relaxing + the weight of the current edge, we update the distance.Worst case performance is O(V ElogV) = O(V^3logV) when 
*	graph is dense (i.e. as Edges approach nearly all being filled - V^2).
*
*	Floyd-Warshall's algorithm is conceptually more simple. We use a 2d array to store distances between vertex a and vertex b.
*	for each path from a to b, check if the weight is more than the path from a to an intermediate point c + the weight from c to b.
*	If so, we reroute the path from a->b to a->c->b, and update the weights. Worst case performance is O(V^3).
*
*	Dijkstra for sparser graphs as when graphs are sparse - E is of the order O(V) or below, then
*	Dijkstra’s would take O(V^2log(V)) time vs Floyd Warshall at O(V^3).
*
* 	For denser graphs, FloydWarshall is preferable as Dijkstra approaches O(V^3logV) when 
*	graph is dense (i.e. as Edges approach nearly all being filled - V^2) whereas FW is O(V^3).
*
*	
*/
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class CompetitionTests {

	@Test
	public void testDijkstraConstructor() {
		CompetitionDijkstra comp = new CompetitionDijkstra("tinyEWD.txt", 200, 50, 100);
		assertEquals("Testing for existent file", true, comp.validFile());

		comp = new CompetitionDijkstra("ab.txt", 200, 50, 100);
		assertEquals("Testing for non-existent file", false, comp.validFile());

		comp = new CompetitionDijkstra("imm.txt", 200, 50, 100);
		assertEquals("Testing for existent file where input is invalid", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline.txt", 200, 50, 100);
		assertEquals("Testing for file with no first line (#v)", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline1.txt", 200, 50, 100);
		assertEquals("Testing for file with no second line (#e)", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline2.txt", 200, 50, 100);
		assertEquals("Testing for file with no third line", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline2.5.txt", 200, 50, 100);
		assertEquals("Testing for file with no start vertex on third line", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline3.txt", 200, 50, 100);
		assertEquals("Testing for file with no destinationvertex", false, comp.validFile());

		comp = new CompetitionDijkstra("nonextline4.txt", 200, 50, 100);
		assertEquals("Testing for file with no weight", false, comp.validFile());

	}

	@Test
	public void testFWConstructor() {
		CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 200, 50, 100);
		assertEquals("Testing for existent file", true, comp.validFile());

		comp = new CompetitionFloydWarshall("ab.txt", 200, 50, 100);
		assertEquals("Testing for non-existent file", false, comp.validFile());

		comp = new CompetitionFloydWarshall("imm.txt", 200, 50, 100);
		assertEquals("Testing for existent file where input is invalid", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline.txt", 200, 50, 100);
		assertEquals("Testing for file with no first line (#v)", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline1.txt", 200, 50, 100);
		assertEquals("Testing for file with no second line (#e)", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline2.txt", 200, 50, 100);
		assertEquals("Testing for file with no third line", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline2.5.txt", 200, 50, 100);
		assertEquals("Testing for file with no start vertex on third line", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline3.txt", 200, 50, 100);
		assertEquals("Testing for file with no destinationvertex", false, comp.validFile());

		comp = new CompetitionFloydWarshall("nonextline4.txt", 200, 50, 100);
		assertEquals("Testing for file with no weight", false, comp.validFile());

	}

	@Test
	public void testDijkstraSpeeds() {
		CompetitionDijkstra comp = new CompetitionDijkstra("tinyEWD.txt", 200, 50, 100);
		assertEquals("Testing for correct speed", 38, comp.timeRequiredforCompetition());

		comp = new CompetitionDijkstra("tinyEWD.txt", 0, 50, 100);
		assertEquals("Testing for invalid speed at sA", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionDijkstra("tinyEWD.txt", 100, 0, 100);
		assertEquals("Testing for invalid speed at sB", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionDijkstra("tinyEWD.txt", 100, 50, 0);
		assertEquals("Testing for invalid speed at sC", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionDijkstra("input-A.txt", 60, 50, 75);
		assertEquals("Testing for valid input where no path present", -1, comp.timeRequiredforCompetition());
	}

	@Test
	public void testFWSpeeds() {
		CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 200, 50, 100);
		assertEquals("Testing for correct speed", 38, comp.timeRequiredforCompetition());

		comp = new CompetitionFloydWarshall("tinyEWD.txt", 0, 50, 100);
		assertEquals("Testing for invalid speed at sA", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionFloydWarshall("tinyEWD.txt", 100, 0, 100);
		assertEquals("Testing for invalid speed at sB", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionFloydWarshall("tinyEWD.txt", 100, 50, 0);
		assertEquals("Testing for invalid speed at sC", -1, comp.timeRequiredforCompetition());

		comp = new CompetitionFloydWarshall("input-A.txt", 60, 50, 75);
		assertEquals("Testing for valid input where no path present", -1, comp.timeRequiredforCompetition());
	}

	@Test
	public void testMin() {
		assertEquals("Testing Min for a", 20, CompetitionDijkstra.min(20, 30, 40));
		assertEquals("Testing Min for b when <a & <c", 20, CompetitionDijkstra.min(30, 20, 40));
		assertEquals("Testing Min for b when <a & >c", 20, CompetitionDijkstra.min(40, 30, 20));
		assertEquals("Testing Min for c", 20, CompetitionDijkstra.min(30, 40, 20));
		assertEquals("Testing Min for all 3", 20, CompetitionDijkstra.min(20, 20, 20));

		assertEquals("Testing Min for a", 20, CompetitionFloydWarshall.min(20, 30, 40));
		assertEquals("Testing Min for b when <a & <c", 20, CompetitionFloydWarshall.min(30, 20, 40));
		assertEquals("Testing Min for b when <a & >c", 20, CompetitionDijkstra.min(40, 30, 20));
		assertEquals("Testing Min for c", 20, CompetitionFloydWarshall.min(30, 40, 20));
		assertEquals("Testing Min for all 3", 20, CompetitionFloydWarshall.min(20, 20, 20));

	}

	@Test
	public void testBag() {
		try {
			Bag<Integer> test = new Bag<Integer>();
			test.add(1);
			Iterator<Integer> list = test.iterator();
			list.remove();
		} catch (Exception e) {

		}
	}
}