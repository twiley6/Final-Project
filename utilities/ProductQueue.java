/**
 * 
 */
package utilities;

import java.util.LinkedList;
import java.util.Queue;
import products.Product;

/**
 * This class create and manipulates a queue of Products
 * @author twiley
 *
 */
public class ProductQueue
{
	private Queue<Product> productQueue = new LinkedList<Product>();
	/**
	 * Adds an item to the queue
	 * @param product
	 * @return
	 */
	public boolean in(Product product)
	{
		// Add the product that has been passed into this method
		productQueue.add(product);
		
		// Print out the queue
//		System.out.println(productQueue);
		
		return true;
	}
	
	/**
	 * Removes the head item from the queue and displays it.
	 * @return
	 */
	public Product out()
	{
		Product product = null;
		
		// Print the product that has been removed
		System.out.println(product + " has been removed from the the queue.");
		
		return product;
	}
	
	/**
	 * Removes a specific item from the queue
	 * @param product
	 * @return
	 */
	public boolean remove(Product product)
	{
		// Remove the product from the queue
		productQueue.remove(product);
		
		//Print out the new queue
//		System.out.println("The queue now contains: " + productQueue);
		return true;
	}
	
	/**
	 * prints the head item
	 * @return
	 */
	public Product first()
	{
		Product product = productQueue.peek();
		
		// Print out the first product
		System.out.println(product);
		
		return product;
	}
	
	/**
	 * prints the length of the item
	 * @return
	 */
	public int length()
	{
		// Set a variable for the length
		int length = productQueue.size();
		
		// Print the length of the queue
		System.out.println(length);
		
		return length;
	}
	
	/**
	 * Displays whether the queue is empty or not
	 * @return
	 */
	public boolean isEmpty()
	{
		// Print out whether the queue is empty or not
		System.out.printf("The queue is empty (true or false): %b\n", productQueue.isEmpty());
		
		//Return true if the queue is empty
		if (productQueue.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * Called by the CartManagement class
	 * @return
	 */
	public Iterable<Product> getQueue()
	{
		return productQueue;
	}
	
	/**
	 * Test method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ProductQueue test = new ProductQueue();
		test.out();
		test.first();
		test.length();
		test.isEmpty();
	}
}
