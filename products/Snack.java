package products;

public abstract class Snack extends Product
{
	//no specific fields for this class
	
	/**
	 * non-default constructor
	 * @param name
	 * @param price
	 * @param imagePath
	 * @param stock
	 */
	public Snack(String name, float price, String imagePath, int stock)
	{
		super(name, price, imagePath, stock);		
	}
	
}
