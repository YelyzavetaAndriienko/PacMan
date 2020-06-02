package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreLabel extends Label{

	public ScoreLabel(String text) {
		setPrefWidth(400);
		setPrefHeight(400);
		setPadding(new Insets(40,40,40,40));
		setText(text);
		setWrapText(true);
		setLabelFont();
		setTextFill(Color.WHITE);
	}
	
	
	public void setLabelFont() {
		setFont(Font.font("Constantia", 40));
	}
}
