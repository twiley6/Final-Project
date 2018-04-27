/**
 * 
 */
package utilities;

import products.Product;


/**
 * This class manages the cart activity
 * @author twiley
 *
 */
public class CartManagement
{
	private static ProductQueue productQueue = new ProductQueue();
	
	/**
	 * Adds the selected item's name to the cart
	 */
	public static void addToCart(Product product)
	{
		// Decrement the stock of that item
		product.decrementStock();
		
		// Add product to the queue
		productQueue.in(product);
	}
	
	public static void removeFromCart(Product product)
	{
		// Call the remove method in the Queue Class
		productQueue.remove(product);
	}
	
	
	public static Iterable<Product> getCartItems()
	{
		return productQueue.getQueue();	
	}
}


