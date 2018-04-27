package products;

/**
 * The Candy class that inherits the Snack class
 * @author twiley
 *
 */
public class Candy extends Snack
{
	private float weight;		

	/**
	 * non-default constructor
	 * @param name
	 * @param price
	 * @param weight
	 * @param imagePath
	 * @param stock
	 */
	public Candy(String name, float price, float weight, String imagePath, int stock)
	{
		super(name, price, imagePath, stock);
		this.weight = weight;		
	}
	
	public float getWeight()
	{
		return weight;
	}
}
