package model;

import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.GameViewManager;
/**
 * class: Bonus
 * Describes behavior of the bonus
 */
public class Bonus extends Pane {
	 Image bonusImage;
	 ImageView bonusView;
	 public static MediaPlayer mediaPlayerBonusShield;
	 public static MediaPlayer mediaPlayerBonusHeart;
	 
	 public enum BonusType {
		 HEART, SHIELD, STAR
	 }
	public MyAnimation animation;
	public BonusType type;
	  
/**
 * constructor
 * @param typeB
 * @param x
 * @param y
 */
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

/**
 * removes bonuses from the panel
 */
	 public void removeBonus() {
		 GameViewManager.gamePane.getChildren().remove(this);
	 }

/**
 * choose the bonus
 * @param l
 */
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

/**
 * action of a shield
 */
    private void shieldAction() {
    	if(GameViewManager.hasShield == false) {
    		GameViewManager.hasShield = true;
    		GameViewManager.gamePane.getChildren().add(GameViewManager.shield);
    		musicBonusShield();
    		removeBonus();
    	}
	}

/**
* action of a shield
*/
	    private void heartAction(ScoreLabel l){
	    	int score = Integer.parseInt(l.getText());
	    	int newScore = 0;
	    	if(score<80) {
		    	newScore = score + 20;
		    	String textScore = String.valueOf(newScore);
		        l.setText(textScore);
		        musicBonusHeart();
		        removeBonus();
	    	}else{
		        musicBonusHeart();
		        removeBonus();
	    	}
	    }
	   
/**
 * add music for heart
 */
		public void musicBonusHeart(){
		   //String bip = "C:\\Users\\Liza\\Downloads\\bonusSound2.mp3";
		String bip = "src/view/resources/bonusSound2.mp3";
		    Media hit = new Media(Paths.get(bip).toUri().toString());
		    mediaPlayerBonusHeart = new MediaPlayer(hit);
		    mediaPlayerBonusHeart.play();
		}
		
/**
 * adds music for shield	
 */
		public void musicBonusShield(){
			 //   String bip = "C:\\Users\\Liza\\Downloads\\bonusSound1.mp3";
				String bip = "src/view/resources/bonusSound1.mp3";
			    Media hit = new Media(Paths.get(bip).toUri().toString());
			    mediaPlayerBonusShield = new MediaPlayer(hit);
			  mediaPlayerBonusShield.play();
			}
		
	}
