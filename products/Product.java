package products;

/**
 * This is the Product class that is the superclass to the Snack and Drink
 * classes
 * 
 * @author twiley
 *
 */
public abstract class Product implements Comparable<Product>
{
	protected String name;
	protected float price;
	protected String formattedPrice;
	protected String imagePath;
	protected int stock;

	/**
	 * non-default constructor
	 * 
	 * @param name
	 * @param price
	 * @param imagePath
	 * @param stock
	 */
	public Product(String name, float price, String imagePath, int stock)
	{
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.stock = stock;
	}

	/**
	 * copy constructor in case another dispenser is to be made
	 * 
	 * @param product
	 */
	public Product(Product product)
	{

	}

	/**
	 * getName Method
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * setName Method
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * getPrice method
	 * @return
	 */
	public float getPrice()
	{
		return price;
	}

	/**
	 * setPrice method
	 * @param price
	 */
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	/**
	 * Formats the price to a proper format
	 * @return
	 */
	public String getFormattedPrice()
	{
		formattedPrice = String.format("%.02f", price);
		return formattedPrice;
	}

	/**
	 * getImagePath method
	 * @return
	 */
	public String getImagePath()
	{
		return imagePath;
	}

	/**
	 * Decreases the Stock on the item.
	 */
	public void decrementStock()
	{
		stock--;
	}
	
	/**
	 * Increases the stock on the item.
	 */
	public void replenishStock()
	{
		stock++;
	}

	/**
	 * getStock method
	 * @return
	 */
	public int getStock()
	{
		return stock;
	}
	
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock)
	{
		this.stock = stock;
	}

	public int compareTo(Product product)
	{
		int value = this.name.compareTo(product.name);
		if(value==0)
		{
			return Float.compare(this.price, product.price);
		}
		else
		{
			return value;
		}
	}
}