import static org.junit.Assert.*;

import org.junit.Test;

public class KMPSearch_Test {

	@Test
	 public void testEmpty(){
	
		@SuppressWarnings("unused") //just for code coverage
		KMPSearch kmp = new KMPSearch(); 
	    assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
	    assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
	    assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
	  }
	
	@Test
	public void testNotFound(){
		assertEquals("Text where pattern is not found",-1,KMPSearch.searchFirst("aabc", "64"));
		assertEquals("Text where pattern is not found",0, KMPSearch.searchAll("abcabcabca", "64"));
		assertFalse("Text where pattern is not found",KMPSearch.contains("aabc", "6"));
	}
	
	@Test
	public void testFound(){
		assertEquals("Text where pattern is found",1,KMPSearch.searchFirst("aabc", "abc"));
		assertEquals("Text where pattern is found multiple times",3, KMPSearch.searchAll("abcabcabca", "bc"));
		assertTrue("Text where pattern is found",KMPSearch.contains("aabc", "ab"));
	}
	
	@Test
	public void testPatternLongerThanString(){
		assertEquals("Text where pattern length is longer than string length",-1,KMPSearch.searchFirst("aabc", "abbbbbb"));
		assertEquals("Text where pattern length is longer than string length",0, KMPSearch.searchAll("abcabcabca", "abbbbbbbbbbb"));
		assertFalse("Text where pattern length is longer than string length",KMPSearch.contains("aabc", "abbbb"));
	}
}

