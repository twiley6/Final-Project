/**
 * 
 */
package utilities;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import products.Product;

/**
 * This class handles the event handling for when the products have been clicked on
 * @author twiley
 *
 */
public class MouseClickHandler implements EventHandler<MouseEvent>
{
	private Product product;
	private ImageView imageView;
	private Button button;
	
	/**
	 * Non-default constructor
	 * @param product
	 * @param imageView
	 * @param button
	 */
	public MouseClickHandler(Product product, ImageView imageView, Button button)
	{
		this.product = product;
		this.imageView = imageView;
		this.button = button;
	}
	
	/**
	 * handle method
	 */
	@Override
	public void handle(MouseEvent e)
	{
		// Animate the product being pressed
		FadeTransition ft = new FadeTransition(Duration.millis(3000), imageView);
		// Fade Transition
		ft.setFromValue(1.0);
		ft.setToValue(.01);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
		
		// Add item to the cart
		CartManagement.addToCart(product);
		
		
		
		button.setText(product.getName() + "    $" + product.getPrice() + "   Qty:" + product.getStock());
	}
}
