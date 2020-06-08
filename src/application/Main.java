package application;

import java.nio.file.Paths;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import view.ViewManager;
/**
 * class: Main
 * Implements the program
 */
public class Main extends Application {
	public static HashMap<KeyCode,Boolean> keys = new HashMap<>();
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

/**
 * main
 * @param args
 */
	public static void main(String[] args) {
		launch(args);
	}
	
/**
 * isPressed
 * @param key
 * @return
 */
	public static boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key,false);
		}
}
