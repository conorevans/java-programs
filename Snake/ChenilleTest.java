import static org.junit.Assert.*;

import org.junit.Test;

public class ChenilleTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testConstructors() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 5);
		assertEquals("Testing constructor A", e, ch.getEspace());
		assertEquals("Testing constructor A", 1, ch.getTaille());
		assertEquals("Testing constructor A", 5, ch.getCases().length);
		assertEquals("Testing constructor A", new Case[] { c }, ch.getOccupiedCases());

		ch = new Chenille(e, 5);
		assertEquals("Testing constructor B", e, ch.getEspace());
		assertEquals("Testing constructor B", 1, ch.getTaille());
		assertEquals("Testing constructor B", 5, ch.getCases().length);

		ch = new Chenille(e);
		assertEquals("Testing constructor C", e, ch.getEspace());
		assertEquals("Testing constructor C", 1, ch.getTaille());
		assertEquals("Testing constructor C", 10, ch.getCases().length);
	}

	@Test
	public void testEstSur() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 5);
		assertEquals("Testing estSur", true, ch.estSur(c));
		assertEquals("Testing estSur", false, ch.estSur(new Case(2, 2)));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetOccupiedCases() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 5);
		assertEquals("Testing getOccupiedCases", new Case[] { c }, ch.getOccupiedCases());
		ch.avance();
		// occupies 1 space by default, avance() increases by 1
		assertEquals("Testing getOccupiedCases", 2, ch.getOccupiedCases().length);
	}

	/*
	 * As avance() works on randomness, our tests are limited to input we can
	 * predict. As such, the test class tests for when only one neighbour is
	 * free (i.e. it should have to move to this neighbour alone) as well as 
	 * when there are no neighbours free.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAvance() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 2);
		e.addChenille(ch);
		//north neighbour
		Chenille ch1 = new Chenille(e,c.nord(),1);
		e.addChenille(ch1);
		//east neighbour
		Chenille ch2 = new Chenille(e,c.est(),1);
		e.addChenille(ch2);
		//west neighbour
		Chenille ch3 = new Chenille(e,c.ouest(),1);
		e.addChenille(ch3);
		
		ch.avance();
		assertEquals("Testing avance",new Case[]{c.sud(),c},ch.getOccupiedCases());
		
		e = new Espace();
		c = new Case(5,5);
		ch = new Chenille(e, c, 5);
		e.addChenille(ch);
		//north neighbour
		ch1 = new Chenille(e,c.nord(),1);
		e.addChenille(ch1);
		//east neighbour
		ch2 = new Chenille(e,c.est(),1);
		e.addChenille(ch2);
		//west neighbour
		ch3 = new Chenille(e,c.ouest(),1);
		e.addChenille(ch3);
		//add south neighbour, so all four neighbour spots are filled
		Chenille ch4 = new Chenille(e,c.sud(),1);
		e.addChenille(ch4);
		
		ch.avance();
		assertEquals("Testing avance",false,ch.peutAvancer());
		
	}
	
	@Test
	public void testToString() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 1);
		String s = "Chenille(1/1):[(1, 1)]";
		assertEquals("Testing toString",s,ch.toString());
	}
	
	public void testGetters() {
		Espace e = new Espace();
		Case c = new Case(1, 1);
		Chenille ch = new Chenille(e, c, 5);
		assertEquals("Testing getEspace()",e,ch.getEspace());
		assertEquals("Testing getTaille()",1,ch.getTaille());
		assertEquals("Testing getCases()",5,ch.getCases().length);
		assertEquals("Testing peutAvancer",true,ch.peutAvancer());
		
	}

}
