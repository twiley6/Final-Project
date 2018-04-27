package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Main Class that starts the user off at the Start Scene.
 * @author twiley
 * 
 */
public class Main extends Application 
{	
	/**
	 * Start method that takes in a primaryStage parameter
	 * Overrides the Java Objects method start()
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{			
			//Set the title
			primaryStage.setTitle("Wiley's Vending Machine");
			
			//Create the Start Scene
			CategoryScene startScene = new CategoryScene(primaryStage);
			
			//Build and Show the Start Scene
			startScene.build();
			startScene.show();
			
			//Show the Application
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method that drives the application
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
}
