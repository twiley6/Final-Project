package application;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import utilities.CartManagement;
import utilities.MouseClickHandler;

/**
 * Product Scene that is abstract. Initialized a scene that all the products
 * will inherit.
 * 
 * @author twiley
 *
 */
public abstract class BaseProductScene
{
	protected Stage primaryStage;
	protected Scene scene;
	protected BorderPane root;
	protected GridPane grid;
	protected Product[] products;
	private int numOfColumns;
	private Button[] button;
	protected VBox rightVbox;
	
	private int i;	
	
	/**
	 * Non-Default Constructor that sets the border pane, grid pane, and the cart
	 * 
	 * @param primaryStage
	 */
	public BaseProductScene(Stage primaryStage, Product[] products)
	{
		this.products = products;
		button = new Button[products.length];

		// set the number of columns
		numOfColumns = 3;

		// Initialize the Scene
		this.root = new BorderPane();
		this.scene = new Scene(root, 1000, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.primaryStage = primaryStage;
		root.setStyle("-fx-background-color: lightgray");

		// Set the Name of the Dispenser
		Text title = new Text("Wiley's Vending Machine");
		title.setFill(Color.WHITE);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		BorderPane.setAlignment(title, Pos.CENTER);
		root.setTop(title);

		// Set the grid pane for the products inside a scroll pane
		ScrollPane scroll = new ScrollPane();
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		scroll.setContent(grid);
		BorderPane.setAlignment(scroll, Pos.CENTER);
		root.setCenter(scroll);

		// Show the cart
		showCart();
		
		// Set a bottom pane
		Text bottom = new Text("       ");
		root.setBottom(bottom);	
		
	}

	/**
	 * Displays the products appropriately depending on how many were passed through the non-default constructor.
	 */
	public void displayProduct()
	{
		for (i = 0; i < products.length; i++)
		{
			// dynamically determine where to place each node in the grid
			int x = i % numOfColumns;
			int y = i / numOfColumns;

			// create each button with an image
			Image productImage = new Image(products[i].getImagePath());
			ImageView imageView = new ImageView(productImage);
			imageView.setFitWidth(190);
			imageView.setFitHeight(110);
			button[i] = new Button(products[i].getName() + "    $" + products[i].getFormattedPrice() + "   Qty:" + products[i].getStock(), imageView);
			button[i].setStyle("-fx-background-color: white");
			button[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
			button[i].setContentDisplay(ContentDisplay.TOP);

			// add them to the grid
			grid.add(button[i], x, y);

			// call the method that will create the visual affects and decrease the stock of that item that has been clicked on
			button[i].setOnMouseClicked(new MouseClickHandler(products[i], imageView, button[i]));
			
			showCart();
		}
	}
	
	public void showCart()
	{
		List<Product> cartItems = new ArrayList<Product>();
		// Create a cart that is visible on every screen in the right pane
		rightVbox = new VBox();
		Text label = new Text("Your Cart");
		label.setFill(Color.WHITE);
		label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24));
		rightVbox.getChildren().add(label);
		for (Product item : CartManagement.getCartItems())
		{
			Hyperlink linkItems = new Hyperlink(item.getName() + "  $" + item.getFormattedPrice());

			linkItems.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent e)
				{
		    		CartManagement.removeFromCart(item);
		    		showCart();
				}
			});	
			
			rightVbox.getChildren().add(linkItems);
			cartItems.add(item);
			
		}			
		
		// Create a button that will update the cart
		Button update = new Button("Update Cart");
		update.setStyle("-fx-background-color: white");
		update.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		rightVbox.getChildren().add(update);
		rightVbox.setSpacing(15);
		rightVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(rightVbox, Pos.CENTER);
		update.setOnMouseClicked(e -> showCart());

		// Create the button for checking out
		Button checkout = new Button("Checkout");
		checkout.setStyle("-fx-background-color: white");
		checkout.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		rightVbox.getChildren().add(checkout);
		rightVbox.setSpacing(15);
		rightVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(rightVbox, Pos.CENTER);
		root.setRight(rightVbox);
		
		Product[] p = cartItems.toArray(new Product[cartItems.size()]);
		checkout.setOnMouseClicked(e -> new CheckoutScene(this.primaryStage, p));
		
		
	}

}
