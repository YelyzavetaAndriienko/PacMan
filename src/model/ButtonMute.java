package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ButtonMute extends Button{
	private final String BUTTON_FREE_STYLE ="-fx-background-color: transparent; -fx-background-image: url('/model/resources/newMute.png');";
	
	public ButtonMute() {
		setPrefWidth(30);
		setPrefHeight(30);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(25);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(30);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    		if(event.getButton().equals(MouseButton.PRIMARY)) {
    			setButtonPressedStyle();
    		}
    		}
    	});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    		if(event.getButton().equals(MouseButton.PRIMARY)) {
    			setButtonReleasedStyle();
    		}
    		}
    	});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    		DropShadow d = new DropShadow();
    		d.setColor(Color.WHITE);
    		setEffect(d);
    		}
    	});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    		setEffect(null);
    		}
    	});
	}
}
