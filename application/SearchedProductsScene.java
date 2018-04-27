/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.List;
import dispenser.Dispenser;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import products.Product;
import utilities.ProductSort;

/**
 * This class derrives from the BaseProductScene and shows all the products that the user has search for
 * @author twiley
 *
 */
public class SearchedProductsScene extends BaseProductScene
{

	/**
	 * Non-default constructor
	 * @param primaryStage
	 * @param products
	 */
	public SearchedProductsScene(Stage primaryStage, Product[] products)
	{
		super(primaryStage, products);
	}
	
	/**
	 * build method that builds the parts that are particular to this product.
	 */
	public void build()
	{
		Button back = new Button("Back to Start");
		back.setStyle("-fx-background-color: white");
		back.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		back.setMinWidth(50);
		BorderPane.setAlignment(back, Pos.CENTER);
		super.root.setBottom(back);
		back.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				//Create, build, and show Start Scene
				CategoryScene scene = new CategoryScene(primaryStage);
				scene.build();
				scene.show();
			}
		});	
		
		// Create the button to sort the products
		Button sort = new Button("Sort Products");
		sort.setStyle("-fx-background-color: white");
		sort.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		VBox leftVbox = new VBox();
		leftVbox.getChildren().add(sort);
		leftVbox.setSpacing(15);
		leftVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(leftVbox, Pos.CENTER);
		root.setLeft(leftVbox);
		sort.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				// Sort the products
				ProductSort.sortProducts(products, products.length);
				build();
			}
		});
		
		// Create the text field to search the products
		Text instruction = new Text("Search by name:");
		instruction.setFill(Color.WHITE);
		instruction.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24));
		TextField field = new TextField();
		field.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e)
			{
				if(e.getCode() == KeyCode.ENTER)
				{
					// Create a new array and display the matching product or products
					Dispenser dispenser = new Dispenser();
					Product[] products = dispenser.getProducts("All");
					List<Product> searchedProducts = new ArrayList<Product>();
					for (int i=0; i<products.length; i++)
					{
						if (products[i] != null && products[i].getName().contains(field.getText()))
						{
							searchedProducts.add(products[i]);
						}
					}
					Product[] searched = searchedProducts.toArray(new Product[searchedProducts.size()]);
					// Open the search scene
					SearchedProductsScene search = new SearchedProductsScene(primaryStage, searched);
					search.build();
					search.show();
				}
			}
			
		});
		
		leftVbox.getChildren().add(instruction);
		leftVbox.getChildren().add(field);
		leftVbox.setSpacing(15);
		leftVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(leftVbox, Pos.CENTER);
		root.setLeft(leftVbox);	
		
		super.displayProduct();
	}
	
	/**
	 * show method to show this scene
	 */
	public void show()
	{
		//Display the Scene
		primaryStage.setScene(scene);	
	}

}
