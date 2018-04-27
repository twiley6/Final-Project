/**
 * 
 */
package utilities;

import products.Product;

/**
 * @author twiley
 *
 */
public class ProductSort
{
	/**
	 * Bubble Sort Algorithm that uses Recursion
	 * Method is static so you don't have to instantiate the class the use it.
	 * @param products Array, n
	 */
	public static void sortProducts(Product products[], int n)
	{		
		// Base Case
		if (n == 1)
		{
			return;
		}
		
		// One pass of bubble sort. After this pass, the large element is moved (or bubbled) to end.
		for (int i=0; i<n-1; i++)
		{
			if (products[i].compareTo(products[i+1]) > 0)
			{
				// Swap products[i], products[i+1]
				Product temp = products[i];
				products[i] = products[i+1];
				products[i+1] = temp;
			}
		}
		
		// Run next pass
		sortProducts(products, n-1);
	}
}
