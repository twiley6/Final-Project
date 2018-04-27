/**
 * 
 */
package application;

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

/**
 * This Class shows the checkout scene
 * @author twiley
 *
 */
public class CheckoutScene extends BaseProductScene
{
	private boolean promoCode = false;
	private float total;
	
	Text errorMsg = new Text("  ");


	/**
	 * @param primaryStage
	 * @param products
	 */
	public CheckoutScene(Stage primaryStage, Product[] products)
	{
		super(primaryStage, products);
		
		build();
		show();
		
	}
	
	/**
	 * build method that builds the parts that are particular to this product.
	 * 
	 */
	public void build()
	{
		Button back = new Button("Continue Shopping");
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
		
		// Override the center pane with a message
		Text message = new Text("Thank you for using Wiley's Vending Machine");
		message.setFill(Color.WHITE);
		message.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(message, Pos.CENTER);
		super.root.setCenter(message);
		
		// Override the right VBox with a List of the Cart Items and a Total
		VBox checkout = new VBox();
		Text label = new Text("Your Cart");
		label.setFill(Color.WHITE);
		label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24));
		checkout.getChildren().add(label);
		checkout.setSpacing(15);
		checkout.setStyle("-fx-background-color: lightblue");
		BorderPane.setAlignment(rightVbox, Pos.CENTER);
		for (int i=0; i<products.length; i++)
		{
			String item = products[i].getName() + "   $" + products[i].getFormattedPrice();
			checkout.getChildren().add(new Text(item));
		}
		
		// Create a textfield and label for a promotional code entry
		Text promo = new Text("Enter Promotional Code\nThen Press Enter:");
		promo.setFill(Color.WHITE);
		promo.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		TextField promoField = new TextField();
		promoField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e)
			{
				if(e.getCode() == KeyCode.ENTER)
				{
					if(promoField.getText().equals("GET50OFF"))
					{
						promoCode = true;
						promoField.clear();
						errorMsg.setText("  ");
					}
					else
					{
						errorMsg.setText("You did not enter\na valid promo code");
					}
					
					// Reload the scene
					build();
				}
			}

		});		
		
		checkout.getChildren().add(promo);
		checkout.getChildren().add(promoField);
		checkout.getChildren().add(errorMsg);
		checkout.getChildren().add(new Text("Total =    $" + getTotal()));
		
		super.root.setRight(checkout);
		
	}
	
	/**
	 * 
	 * @return the total as a formatted string
	 */
	public String getTotal()
	{
		String stringTotal = "";
		total = 0;
		
		// Calculate the total
		for (int i=0; i<products.length; i++)
		{
			total = total + products[i].getPrice();
		}
		
		// Determine if a promotional code was used
		if (promoCode == true)
		{
			// Adjust the total
			total = total / 2;
			stringTotal = String.format("%.02f", total);
			return stringTotal;
		}
		else
		{
			stringTotal = String.format("%.02f", total);
			return stringTotal;
		}
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
