//Tim Wiley
//CST-135
//02-04-2018
//This is my own work

package dispenser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import products.Candy;
import products.Chips;
import products.Drink;
import products.Gum;
import products.Product;

/**
 * This is the Dispenser class that is the acting driver class
 * @author twiley
 *
 */
public class Dispenser
{			
	private Product[] products;
	
	/**
	 * This dispenser default constructor is where the products are being built using polymorphism.
	 */
	public Dispenser()
	{
		// Call the method to build an array of products from a file
		readFile("Products.txt");
		
	}
	
	/**
	 * Create a method that displays the products that have been called on.
	 */
	public Product[] getProducts(String product)
	{			
		switch (product)
		{
			case "Chips":
			{
				// Parse through the array and count all of the chips				
				int numOfChips = 0;
				for(int i=0; i<products.length; ++i)
				{
					if(products[i] instanceof Chips)
					{						
						++numOfChips;
					}
				}
				
				// Create a new array to hold just the chips
				Product[] chips = new Chips[numOfChips];
				
				// Copy all of the chips from the old array to the new array
				int x = 0;
				for (int i=0; i<products.length; ++i)
				{
					if (products[i] instanceof Chips)
					{
						chips[x] = products[i];
						++x;
					}
				}
				return chips;
			}
			case ("Candy"):
			{
				// Parse through the array and count all of the candy				
				int numOfCandy = 0;
				for(int i=0; i<products.length; ++i)
				{
					if(products[i] instanceof Candy)
					{						
						++numOfCandy;
					}
				}
				
				// Create a new array to hold just the candy
				Product[] candies = new Candy[numOfCandy];
				
				// Copy all of the candy from the old array to the new array
				int x = 0;
				for (int i=0; i<products.length; ++i)
				{
					if (products[i] instanceof Candy)
					{
						candies[x] = products[i];
						++x;
					}
				}
				return candies;
			}
			case ("Drink"):
			{
				// Parse through the array and count all of the drink				
				int numOfDrinks = 0;
				for(int i=0; i<products.length; ++i)
				{
					if(products[i] instanceof Drink)
					{						
						++numOfDrinks;
					}
				}
				
				// Create a new array to hold just the drink
				Product[] drinks = new Drink[numOfDrinks];
				
				// Copy all of the drink from the old array to the new array
				int x = 0;
				for (int i=0; i<products.length; ++i)
				{
					if (products[i] instanceof Drink)
					{
						drinks[x] = products[i];
						++x;
					}
				}				
				return drinks;
			}
			case ("Gum"):
			{
				// Parse through the array and count all of the gum				
				int numOfGum = 0;
				for(int i=0; i<products.length; ++i)
				{
					if(products[i] instanceof Gum)
					{						
						++numOfGum;						
					}
				}
				
				// Create a new array to hold just the gum
				Product[] gums = new Gum[numOfGum];
				
				// Copy all of the gum from the old array to the new array
				int x = 0;
				for (int i=0; i<products.length; ++i)
				{
					if (products[i] instanceof Gum)
					{
						gums[x] = products[i];
						++x;
					}
				}
				return gums;
			}
			
			// Display all of the products
			case ("All"):
				return products;
			default:
				return null;
		}
	}
	
	/**
	 * This method uses file.io to create an array of products from a file
	 * @param inFile
	 */
	private void readFile(String inFile)
	{
		// Input and Output File Read/Writer declarations
		BufferedReader in = null;
		
		// Create Reader and Writer
		try
		{
			in = new BufferedReader(new FileReader(inFile));
			
			// Loop to read all characters from FileReader
			products = new Product[16];
			int i=0;
			String line;
			while((line = in.readLine()) != null)
			{
				String[] tokens = line.split("\\|");
				
				// Determine what kind of product that need to be created
				if(tokens[0].equals("Drink"))
				{
					products[i++] = new Drink(tokens[1], (float)Float.valueOf(tokens[2]), (float)Float.valueOf(tokens[2]), tokens[4], Integer.valueOf(tokens[5]));
				}
				if(tokens[0].equals("Candy"))
				{
					products[i++] = new Candy(tokens[1], (float)Float.valueOf(tokens[2]), (float)Float.valueOf(tokens[2]), tokens[4], Integer.valueOf(tokens[5]));
				}
				if(tokens[0].equals("Chips"))
				{
					products[i++] = new Chips(tokens[1], (float)Float.valueOf(tokens[2]), (float)Float.valueOf(tokens[2]), tokens[4], Integer.valueOf(tokens[5]));
				}
				if(tokens[0].equals("Gum"))
				{
					products[i++] = new Gum(tokens[1], (float)Float.valueOf(tokens[2]), Integer.valueOf(tokens[2]), tokens[4], Integer.valueOf(tokens[5]));
				}
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// Clean up
			try
			{
				if(in != null)
					in.close();
			}  
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
