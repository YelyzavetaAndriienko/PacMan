package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.GameViewManager;
import view.Block.BlockType;

public class Bonus extends Pane {
	 Image bonusImage;
	 ImageView bonusView;
//	 ImageView bonusStar = new ImageView("model/resources/b2.gif");
//	 ImageView bonusShield = new ImageView("model/resources/b3.png");
//	 public final static int HEART_HP = 10;
	 public enum BonusType {
		 HEART, SHIELD, STAR
	 }
	public MyAnimation animation;
	public BonusType type;
//	    public Point2D playerVelocity = new Point2D(0,0);
//	 protected ImageView bonus;
//	 public enum BonusType{
//		 HEART, SHIELD, STAR
//	 }
	    
	 public Bonus (BonusType typeB, int x, int y) {
		 type = typeB;
		 switch (type) {
         case HEART:
             bonusImage = new Image("model/resources/b1.gif");
             break;
         case SHIELD:
        	 bonusImage = new Image("model/resources/b3.png");
             break;
         case STAR:
        	 bonusImage = new Image("model/resources/b2.gif");
             break;
		 }
		 bonusView = new ImageView(bonusImage);
	     bonusView.setFitWidth(GameViewManager.BONUS_SIZE);
	     bonusView.setFitHeight(GameViewManager.BONUS_SIZE);
	     setTranslateX(x);
	     setTranslateY(y);
	     getChildren().add(bonusView);
	     GameViewManager.bonuses.add(this);
	     GameViewManager.gamePane.getChildren().add(this);
	 }
	 
	 public void removeBonus() {
		 GameViewManager.gamePane.getChildren().remove(this);
	 }
	 
	 public void action(ScoreLabel l) {
		 switch (type) {
         case HEART:
             heartAction(l);
             break;
         case SHIELD:
        	 shieldAction();
             break;
         case STAR:
//        	 starAction();
             break;
		 }
	 }

    private void shieldAction() {
    	if(GameViewManager.hasShield == false) {
    		GameViewManager.hasShield = true;
    		GameViewManager.gamePane.getChildren().add(GameViewManager.shield);
    		removeBonus();
    	}
	}

//	    public Bonus(ImageView b){
//	      bonus = b;
//	      bonus.setFitHeight(GameViewManager.BONUS_SIZE);
//	      bonus.setFitWidth(GameViewManager.BONUS_SIZE);
//	      getChildren().addAll(bonus);
//	      bonusStar.setFitHeight(50);
//	      bonusStar.setFitWidth(40);
//	    //  getChildren().addAll(bonusStar);
//	      bonusShield.setFitHeight(50);
//	      bonusShield.setFitWidth(40);
//	     // getChildren().addAll(bonusShield);
//	    }

	    private void heartAction(ScoreLabel l){
	    	int score = Integer.parseInt(l.getText());
	    	int newScore = 0;
	    	if(score<=90) {
		    	newScore = score + 10;
		    	String textScore = String.valueOf(newScore);
		        l.setText(textScore);
		        removeBonus();
	    	}
	    }
	}
