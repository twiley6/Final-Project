package products;

/**
 * the Gum class that inherits the Snack class
 * @author twiley
 *
 */
public class Gum extends Snack
{
	private int qty;

/**
 * non-default constructor
 * @param name
 * @param price
 * @param qty
 * @param imagePath
 * @param stock
 */
	public Gum(String name, float price, int qty, String imagePath, int stock)
	{
		super(name, price, imagePath, stock);
		this.qty = qty;
	}
	
	/**
	 * getter method
	 * @return
	 */
	public int getQty()
	{
		return qty;
	}
}
