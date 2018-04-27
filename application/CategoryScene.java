package application;

import java.util.ArrayList;
import java.util.List;
import dispenser.Dispenser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import utilities.CartManagement;

/**
 * The Start Scene that displays the four categories the user can choose from.
 * @author twiley
 * 
 * 
 */
public class CategoryScene
{
	private Scene scene;
	private BorderPane root;
	private Stage primaryStage;
	private Dispenser dispenser = new Dispenser();
	
	/**
	 * Non-Default Constructor that initialized the scene
	 * @param primaryStage
	 */
	public CategoryScene(Stage primaryStage)
	{
		//Initialize the Scene
		BorderPane root = new BorderPane();
		this.scene = new Scene(root,1000,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.root = root;
		this.primaryStage = primaryStage;
		root.setStyle("-fx-background-color: lightgray");
	}
	
	/**
	 * Build Method that builds the grid and the cart in their respective planes. 
	 * TODO: Get the breadcrumb bar to work properly. 
	 */
	public void build()
	{
		// TODO: Create the breadcrumb bar and set it to the Top Pane

		//Set the Name of the dispenser
		Text title = new Text("Welcome to Wiley's Vending Machine");
		title.setFill(Color.WHITE);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		BorderPane.setAlignment(title, Pos.CENTER);
		root.setTop(title);
		
		//Set the grid pane for the categories
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		BorderPane.setAlignment(grid, Pos.CENTER);
		root.setCenter(grid);
			
		//Create buttons with images for each category
		Image drink = new Image("./images/drink.png");
		ImageView drinkView = new ImageView(drink);
		drinkView.setFitWidth(200);
		drinkView.setFitHeight(115);
		Button drinks = new Button("Drinks", drinkView);
		drinks.setStyle("-fx-background-color: white");
		drinks.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		drinks.setContentDisplay(ContentDisplay.TOP);
		grid.add(drinks, 0, 0);
		drinks.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				//Create, build, and show Drink scene
				DrinkScene drinkScene = new DrinkScene(primaryStage, dispenser.getProducts("Drink"));
				drinkScene.build();
				drinkScene.show();
			}
		});
			
		Image candy = new Image("./images/candy.png");
		ImageView candyView = new ImageView(candy);
		candyView.setFitWidth(200);
		candyView.setFitHeight(115);
		Button candies = new Button("Candy", candyView);
		candies.setStyle("-fx-background-color: white");
		candies.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		candies.setContentDisplay(ContentDisplay.TOP);
		grid.add(candies, 1, 0);
		candies.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				//Create, build, and show candy scene
				CandyScene candyScene = new CandyScene(primaryStage, dispenser.getProducts("Candy"));
				candyScene.build();
				candyScene.show();
			}
		});
		
		Image chip = new Image("./images/chips.png");
		ImageView chipView = new ImageView(chip);
		chipView.setFitWidth(200);
		chipView.setFitHeight(115);
		Button chips = new Button("Chips", chipView);
		chips.setStyle("-fx-background-color: white");
		chips.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		chips.setContentDisplay(ContentDisplay.TOP);
		grid.add(chips, 0, 1);
		chips.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				//Create, build, and show Chips scene
				ChipsScene chipsScene = new ChipsScene(primaryStage, dispenser.getProducts("Chips"));
				chipsScene.build();
				chipsScene.show();
			}
		});
			
		Image gum = new Image("./images/gum.png");
		ImageView gumView = new ImageView(gum);
		gumView.setFitWidth(200);
		gumView.setFitHeight(115);
		Button gums = new Button("Gum", gumView);
		gums.setStyle("-fx-background-color: white");
		gums.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
		gums.setContentDisplay(ContentDisplay.TOP);
		grid.add(gums, 1, 1);
		gums.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				//Create, build, and show Gum scene
				GumScene gumScene = new GumScene(primaryStage, dispenser.getProducts("Gum"));
				gumScene.build();
				gumScene.show();
			}
		});
		
		//Set a bottom pane so that it doesn't bounce.
		Text bottom = new Text("       ");
		bottom.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		root.setBottom(bottom);
		
		// Create an event for running the administrator scene
		root.setOnKeyPressed((final KeyEvent adminKey) -> 
		{
			if ( adminKey.getCode() == KeyCode.F1)
			{
				// Open the administrator scene and display all of the products in the dispenser
				AdminScene admin = new AdminScene(primaryStage, dispenser.getProducts("All"));
				admin.build();
				admin.show();
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
		
		VBox leftVbox = new VBox();
		leftVbox.getChildren().add(instruction);
		leftVbox.getChildren().add(field);
		leftVbox.setSpacing(15);
		leftVbox.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(leftVbox, Pos.CENTER);
		root.setLeft(leftVbox);		
		
		// Show the cart
		showCart();
	}
	
	/**
	 * This method shows the same cart that is sceen thoughout the application
	 */
	public void showCart()
	{
		List<Product> cartItems = new ArrayList<Product>();
		// Create a cart that is visible on every screen in the right pane
		VBox rightVbox = new VBox();
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

	
	/**
	 * Show method that displays this scene.
	 */
	public void show()
	{
		//Display the Scene
		primaryStage.setScene(scene);
	}
}

