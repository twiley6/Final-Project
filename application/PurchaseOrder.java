/**
 * 
 */
package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import products.Product;

/**
 * This class displays a new scene with all of the products that have a quantity of less than 5
 * @author twiley
 *
 */
public class PurchaseOrder
{
	private Scene scene;
	private BorderPane root;
	private Stage primaryStage;
	private Product[] products;

	/**
	 * Non-default constructor
	 */
	public PurchaseOrder(Stage primaryStage, Product[] products)
	{
		this.products = products;
		//Initialize the Scene
		BorderPane root = new BorderPane();
		this.scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.primaryStage = primaryStage;
		this.root = root;
		root.setStyle("-fx-background-color: lightgray");
	}
	
	/**
	 * build method builds the scene and displays the products
	 */
	public void build() 
	{
		// Set the Name of the Dispenser
		Text title = new Text("Wiley's Vending Machine");
		title.setFill(Color.WHITE);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		BorderPane.setAlignment(title, Pos.CENTER);
		root.setTop(title);

		// Set the grid pane for the products inside a scroll pane
		ScrollPane scroll = new ScrollPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		scroll.setContent(grid);
		BorderPane.setAlignment(scroll, Pos.CENTER);
		root.setCenter(scroll);
		
		// Create a button to re-stock the dispenser
		Button restock = new Button("Restock");
		restock.setStyle("-fx-background-color: white");
		restock.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
		VBox rightVbox = new VBox();
		rightVbox.setSpacing(15);
		rightVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(rightVbox, Pos.CENTER);
		root.setRight(rightVbox);	
		restock.setOnMouseClicked(e -> restock());
		
		// Return to the admin scene
		Button back = new Button("Back to Start");
		back.setStyle("-fx-background-color: white");
		back.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		back.setMinWidth(50);
		BorderPane.setAlignment(back, Pos.CENTER);
		root.setBottom(back);
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
		
		// Display the products in the grid
		int numOfColumns = 3;
		Text[] productLabel = new Text[products.length];
		float totalCost = 0;
		
		for (int i = 0; i < products.length; i++)
		{
			// dynamically determine where to place each node in the grid
			int x = i % numOfColumns;
			int y = i / numOfColumns;
			
			// Display the products
			productLabel[i] = new Text(products[i].getName() + " $" + products[i].getFormattedPrice() + " QOH: " + products[i].getStock());
			productLabel[i].setFill(Color.BLACK);
			productLabel[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
			
			
			// Calculate the cost of re-stocking the dispenser
			int quantity = 10 - products[i].getStock();
			totalCost = totalCost + (quantity * products[i].getPrice());
			
			// add them to the grid
			grid.add(productLabel[i], x, y);
		}
		
		// Create a formatted text for the totalCost
		String stringTotal = String.format("%.02f", totalCost);
		
		// Display the total cost to re-stock
		Text cost = new Text("The total cost to re-stock is:\n$" + stringTotal);
		cost.setFill(Color.BLACK);
		cost.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		rightVbox.getChildren().add(cost);
		rightVbox.getChildren().add(restock);
		
		// Call the show method automatically
		show();
	}
	
	
	/**
	 * Method to re-stock all products back to 10
	 */
	public void restock()
	{
		for (int i=0; i<products.length; i++)
		{
			products[i].setStock(10);
		}
		
		build();
	}
	
	//Show the behavior
	public void show()
	{
		//Show the Scene
		primaryStage.setScene(scene);
	}

}
