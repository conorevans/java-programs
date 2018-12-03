// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * doubles using different sort algorithms.
 *
 * @author Conor Evans
 * @version 1.4 2018-02-15
 */

class SortComparison {
	/**
	 * Sorts an array of doubles using InsertionSort.
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		if (a.length <= 1) // already sorted if 0/1 element
			return a;
		else {
			int i, j;
			double index;
			for (i = 1; i < a.length; i++) {
				index = a[i];
				j = i;
				while (j > 0 && a[j - 1] > index) {
					a[j] = a[j - 1];
					j = j - 1;
				}

				a[j] = index;
			}
			return a;
		}
	}

	/**
	 * Finds the partition point in an array of doubles to be used in QuickSort
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return int
	 *
	 */
	private static int partition(double[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		double pivot = a[lo]; // get pivot
		while (true) {
			while (a[++i] < pivot) {
				if (i == hi)
					break;
			}
			while (pivot < a[--j]) {
				// removing the if statement because it did nothing, but the
				// decrement of j was important.
				// I consulted a classmate to see if they had encountered a
				// similar problem, and they
				// said that in 1000 random 10,000 length arrays, the line of
				// code did not run once.
				// I am unsure how to fix it.
			}
			if (i >= j)
				break;

			double temp = a[i]; // get ith index in temp variable
			a[i] = a[j]; // swap i and j
			a[j] = temp; // swap j and temp (i)
		}
		a[lo] = a[j];
		a[j] = pivot;
		return j;
	}

	/**
	 * Sorts an array of doubles using quick Sort.
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */

	private static double[] quickSort(double[] a, int lo, int hi) {
		if (hi <= lo)
			return a;

		int pivotPos = partition(a, lo, hi); // find pivot to divide into two
												// halves
		a = quickSort(a, lo, pivotPos - 1); // quick sort first half
		a = quickSort(a, pivotPos + 1, hi); // quick sort second half
		return a;
	}

	static double[] quickSort(double a[]) {
		if (a.length <= 1)
			return a;
		else {
			a = quickSort(a, 0, a.length - 1); // recursively sort
		}
		return a;
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @param aux:
	 *            Auxiliary array for copying
	 * @param lo
	 * @param mid
	 * @param hi
	 * @return array sorted in ascending order
	 *
	 */
	private static double[] merge(double[] a, double[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j] < aux[i])
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		return a;
	}

	private static double[] mergeSort(double[] a, double[] aux, int lo, int hi) {
		if (hi <= lo)
			return a;
		int mid = lo + (hi - lo) / 2;
		a = mergeSort(a, aux, lo, mid);
		a = mergeSort(a, aux, mid + 1, hi);
		a = merge(a, aux, lo, mid, hi);
		return a;
	}

	static double[] mergeSort(double[] a) {
		if (a.length <= 1)
			return a;
		else {
			double[] aux = new double[a.length];
			a = mergeSort(a, aux, 0, a.length - 1);
		}
		return a;

	}

	/**
	 * Sorts an array of doubles using Shell Sort.
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @param i
	 * @param j
	 * @return array sorted in ascending order
	 *
	 */
	private static void swap(double[] a, int i, int j) {
		double temp = a[i]; // store ith index temporarily
		a[i] = a[j]; // swap i & j
		a[j] = temp; // set j to i
	}

	static double[] shellSort(double a[]) {

		int x = 1;
		while (x < (a.length / 3))
			x = (3 * x) + 1; // increment sequence

		while (x >= 1) {
			for (int i = x; i < a.length; i++) {
				for (int j = i; j >= x && (a[j] < a[j - x]); j -= x)
					swap(a, j, j - x);
			}
			x = x / 3; // move to next increment
		}

		return a;
	}

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		if (a.length <= 1)
			return a;
		else {
			for (int i = 0; i < a.length - 1; i++) {
				int minIndex = i;
				// find min of unsorted array
				for (int j = i + 1; j < a.length; j++) {
					if (a[j] < a[minIndex])
						minIndex = j;
				}
				// swap min with first element
				int temp = (int) a[minIndex];
				a[minIndex] = a[i];
				a[i] = temp;
			}
			return a;
		}

	}

	/**
	 * Sorts an array of doubles using Bubble Sort.
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] bubbleSort(double a[]) {
		double tmp = 0; // have a temp variable to store value in
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length - i; j++) {
				if (a[j - 1] > a[j]) {
					tmp = a[j - 1]; // store lower index in temp
					a[j - 1] = a[j]; // change lower index to higher index
					a[j] = tmp; // change higher index to temp (lower)
				}
			}
		}
		return a;

	}

}