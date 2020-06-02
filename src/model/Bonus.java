package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.GameViewManager;

public class Bonus extends Pane {
	 ImageView bonusHeart = new ImageView("model/resources/b1.gif");
	 ImageView bonusStar = new ImageView("model/resources/b2.gif");
	 ImageView bonusShield = new ImageView("model/resources/b3.png");
	 public final static int HEART_HP = 10;
	    public MyAnimation animation;
	    public Point2D playerVelocity = new Point2D(0,0);

	    public Bonus(){
	      bonusHeart.setFitHeight(GameViewManager.HEART_SIZE);
	      bonusHeart.setFitWidth(GameViewManager.HEART_SIZE);
	      getChildren().addAll(bonusHeart);
	      bonusStar.setFitHeight(50);
	      bonusStar.setFitWidth(40);
	    //  getChildren().addAll(bonusStar);
	      bonusShield.setFitHeight(50);
	      bonusShield.setFitWidth(40);
	     // getChildren().addAll(bonusShield);
	    }

	    public void heartAction(ScoreLabel l){
	    	int score = Integer.parseInt(l.getText());
	    	int newScore = score;
	    	if(score<=90) {
	    	newScore = score + HEART_HP;
	    	}
	    	String textScore = String.valueOf(newScore);
	        l.setText(textScore);
	    }
	}
