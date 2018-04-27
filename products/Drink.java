package products;

/**
 *  
 * This is the Drink class that extends from the Product class and implements the Comparable Interface
 * @author twiley
 *
 */
public class Drink extends Product
{
	private float fluidOz;
	
	/**
	 * non-default constructor
	 * @param name
	 * @param price
	 * @param fluidOz
	 * @param imagePath
	 * @param stock
	 */
	public Drink(String name, float price, float fluidOz, String imagePath, int stock)
	{
		super(name, price, imagePath, stock);
		this.fluidOz = fluidOz;
	}
	
	/**
	 * getter method
	 * @return
	 */
	public float getFluidOz()
	{
		return fluidOz;
	}
	
}
