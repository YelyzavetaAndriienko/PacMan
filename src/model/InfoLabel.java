package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
/**
 * class: InfoLabel
 * creates special label
 */
public class InfoLabel extends Label{

/**
 * constructor
 * @param text
 */
	public InfoLabel(String text) {
		setPrefWidth(400);
		setPrefHeight(400);
		setPadding(new Insets(40,40,40,40));
		setText(text);
		setWrapText(true);
		setLabelFont();
	}

/**
 * set font for label
 */
	public void setLabelFont() {
		setFont(Font.font("Constantia", 23));
	}
}
