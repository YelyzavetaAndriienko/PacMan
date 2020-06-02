package application;

import java.nio.file.Paths;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import view.ViewManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	public static HashMap<KeyCode,Boolean> keys = new HashMap<>();
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
		//	music();
			primaryStage = manager.getMainStage();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public static boolean isPressed(KeyCode key) {return keys.getOrDefault(key,false);}

	MediaPlayer mediaPlayer;
	public void music(){
	    String bip = "src/view/resources/sound.mp3";
	    Media hit = new Media(Paths.get(bip).toUri().toString());
	    mediaPlayer = new MediaPlayer(hit);
	    mediaPlayer.play();
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
}