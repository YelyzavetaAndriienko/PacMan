package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
/**
 * class: PacManButton
 *create special style for buttons
 */
public class PacManButton extends Button{

	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/button2Pressed.png');";
	private final String BUTTON_FREE_STYLE ="-fx-background-color: transparent; -fx-background-image: url('/model/resources/button2.png');";
	
/**
 * constructor
 * @param text
 */
	public PacManButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(200);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}

/**
 * set font
 */
	private void setButtonFont() {
			setFont(Font.font("Constantia", 23));
	}

/**
 * set pressed style for button
 */
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}

/**
 * set released style for button
 */
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

/**
 * adds listeners
 */
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
    		setEffect(new DropShadow());
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
