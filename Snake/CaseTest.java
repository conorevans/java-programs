import static org.junit.Assert.*;

import org.junit.Test;

public class CaseTest {

	@Test
	public void testConstructorAndGetters() {
		Case c = new Case(1, 1);
		assertEquals("Testing constructor and x-axis value getter method", 1, c.getAbscisse());
		assertEquals("Testing constructor and y-axis value getter method", 1, c.getOrdonnee());

		// illegal co-ordinate for our game grid
		c = new Case(-1, 5);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testNeighbours() {
		Case c = new Case(1,1);
		
		Case correctNorth = new Case(1,2);
		Case correctEast = new Case(2,1);
		Case correctWest = new Case(0,1);
		Case correctSouth = new Case(1,0);
		
		Case[] voisines = new Case[]{correctNorth,correctEast,correctWest,correctSouth};
		
		assertEquals("Testing north", correctNorth,c.nord());
		assertEquals("Testing east", correctEast,c.est());
		assertEquals("Testing west", correctWest,c.ouest());
		assertEquals("Testing south", correctSouth, c.sud());
		assertEquals("Testing neighbours getter", voisines,c.voisines());

	}
	
	@Test
	public void testEquals() {
		Case c = new Case(1,1);
		Case d = c;
		Case e = new Case(2,2);
		assertEquals("Testing equality",c,d);
		assertNotEquals("Testing inequality",c,e);
	}
	
	@Test
	public void testToString() {
		Case c = new Case(1,1);
		String s = "(1, 1)";
		assertEquals("Testing toString",s,c.toString());
		assertNotEquals("Testing toString","",c.toString());
	}

}
