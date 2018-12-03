import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TSTTest {

  @Test
  public void testEmpty(){
    TST<Long> trie = new TST<>();
    assertEquals("size of an empty trie should be 0",0, trie.size());
    assertFalse("searching an empty trie should return false",trie.contains(""));
    assertNull("getting from an empty trie should return null",trie.get(""));
  }
  
  @Test
  public void testContains(){
	 TST<Integer> trie = new TST<Integer>();
	 trie.put("aa", 1);
	 assertTrue("Testing contains for string in TST", trie.contains("aa"));
	 assertFalse("Testing contains for string not in TST",trie.contains("bb"));
	  	
  }
  
  @Test
  public void testGet(){
	  TST<Integer> trie = new TST<Integer>();
	  trie.put("aa", 1);
	  assertEquals("Testing get for key in TST",(Integer)1, trie.get("aa"));
	  assertEquals("Testing get for key not in TST",null,trie.get("bb"));
	  assertEquals("Testing get for null key",null,trie.get(""));
		  	
  }
  
  @Test
  public void testPut(){
	  TST<Integer> trie = new TST<Integer>();
	  trie.put("aa", 1);
	  assertEquals("Test put",(Integer)1, trie.get("aa"));
	  trie.put("bb", 1);
	  assertEquals("Test put",(Integer)1,trie.get("bb"));
	  //testing put for null key
	  //prints out "invalid key"
	  trie.put(null, 2);
  }
  @Test
  public void testSize(){
	  TST<Integer> trie = new TST<Integer>();
	  trie.put("aa", 1);
	  assertEquals("Testing size of single node TST" ,1, trie.size());
	  trie.put("bb", 2);
	  assertEquals("Testing size of multi node TST",2,trie.size());
  }
  
  @Test
  public void testPrefix(){
	  TST<Integer> trie = new TST<Integer>();
	  trie.put("abcba", 1);
	  trie.put("abcbaab", 2);
	  assertEquals("Testing keysWithPrefix","[abcba, abcbaab]",trie.keysWithPrefix("abcb").toString());
	  assertEquals("Testing kWP with null prefix","[]",trie.keysWithPrefix(null).toString());
  }
}