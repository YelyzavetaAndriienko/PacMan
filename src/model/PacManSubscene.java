package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class PacManSubscene extends SubScene{
	
	private final static String BACKGROUND_IMAGE = "model/resources/subfon6.jpg";
	private boolean isHidden = true;
	
	public PacManSubscene() {
		super(new AnchorPane(), 400, 350);
		prefWidth(400);
		prefHeight(350);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 400, 350, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
	    setLayoutX(1624);
    	setLayoutY(150);
	
	}

	public void moveSubscene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if(isHidden) {
		transition.setToX(-1276);
		isHidden = false;
		}else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
