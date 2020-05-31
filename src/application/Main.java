package application;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
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

	MediaPlayer mediaPlayer;
	public void music(){
	    String bip = "src/view/resources/sound.mp3";
	    Media hit = new Media(Paths.get(bip).toUri().toString());
	    mediaPlayer = new MediaPlayer(hit);
	    mediaPlayer.play();
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
}
