import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2018
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double[] a = {};
    	double[] b = a.clone(); //insertion
    	double[] c = a.clone(); //quick
    	double[] d = a.clone(); //merge
    	double[] e = a.clone(); //shell
    	double[] f = a.clone(); //selection
    	double[] g = a.clone(); //bubble
    	assertEquals("Testing insertionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.insertionSort(b)));
    	assertEquals("Testing quickSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.quickSort(c)));
    	assertEquals("Testing mergeSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.mergeSort(d)));
    	assertEquals("Testing shellSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.shellSort(e)));
    	assertEquals("Testing selectionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.selectionSort(f)));
    	assertEquals("Testing bubbleSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.bubbleSort(g)));
    	
    }
    
    @Test
    public void testNormal()
    {
    	double[] a = {1.00,5.00,3.00,4.00,1.00,6.00};
    	double[] aSort = {1.00,1.00,3.00,4.00,5.00,6.00};
    	double[] b = a.clone(); //insertion
    	double[] c = a.clone(); //quick
    	double[] d = a.clone(); //merge
    	double[] e = a.clone(); //shell
    	double[] f = a.clone(); //selection
    	double[] g = a.clone(); //bubble
    	assertEquals("Testing insertionSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.insertionSort(b)));
    	assertEquals("Testing quickSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.quickSort(c)));
    	assertEquals("Testing mergeSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.mergeSort(d)));
    	assertEquals("Testing shellSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.shellSort(e)));
    	assertEquals("Testing selectionSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.selectionSort(f)));
    	assertEquals("Testing bubbleSort on empty",Arrays.toString(aSort),Arrays.toString(SortComparison.bubbleSort(g)));
    	
    }
    
    @Test
    public void testAlreadySorted()
    {
    	double[] a = {1.00,1.00,3.00,4.00,5.00,6.00};
    	double[] b = a.clone(); //insertion
    	double[] c = a.clone(); //quick
    	double[] d = a.clone(); //merge
    	double[] e = a.clone(); //shell
    	double[] f = a.clone(); //selection
    	double[] g = a.clone(); //bubble
    	assertEquals("Testing insertionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.insertionSort(b)));
    	assertEquals("Testing quickSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.quickSort(c)));
    	assertEquals("Testing mergeSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.mergeSort(d)));
    	assertEquals("Testing shellSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.shellSort(e)));
    	assertEquals("Testing selectionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.selectionSort(f)));
    	assertEquals("Testing bubbleSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.bubbleSort(g)));
    }
    
    @Test
    public void testReverse()
    {
    	double[] a = {1.00,1.00,3.00,4.00,5.00,6.00};
    	double[] aReverse = {6.00,5.00,4.00,3.00,1.00,1.00};
    	double[] b = aReverse.clone(); //insertion
    	double[] c = aReverse.clone(); //quick
    	double[] d = aReverse.clone(); //merge
    	double[] e = aReverse.clone(); //shell
    	double[] f = aReverse.clone(); //selection
    	double[] g = aReverse.clone(); //bubble
    	assertEquals("Testing insertionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.insertionSort(b)));
    	assertEquals("Testing quickSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.quickSort(c)));
    	assertEquals("Testing mergeSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.mergeSort(d)));
    	assertEquals("Testing shellSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.shellSort(e)));
    	assertEquals("Testing selectionSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.selectionSort(f)));
    	assertEquals("Testing bubbleSort on empty",Arrays.toString(a),Arrays.toString(SortComparison.bubbleSort(g)));
    }
}