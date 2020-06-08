package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * class: InfoLabel
 * creates special label for score
 */
public class ScoreLabel extends Label{

/**
* constructor
* @param text
*/
	public ScoreLabel(String text) {
		setPrefWidth(400);
		setPrefHeight(400);
		setPadding(new Insets(40,40,40,40));
		setText(text);
		setWrapText(true);
		setLabelFont();
		setTextFill(Color.WHITE);
	}
	
/**
 * set font for label
 */
	public void setLabelFont() {
		setFont(Font.font("Constantia", 40));
	}
}
