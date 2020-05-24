package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label{

	public InfoLabel(String text) {
		setPrefWidth(400);
		setPrefHeight(400);
		setPadding(new Insets(40,40,40,40));
		setText(text);
		setWrapText(true);
		setLabelFont();
	}
	
	public void setLabelFont() {
		setFont(Font.font("Constantia", 23));
	}
}
