package view;

import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.ScoreLabel;
/**
 * class: Monster
 *creates monster
 */
public class Monster extends Pane{
	
	Image monsterImage, gameOverImage;
    ImageView imageView, gameOverImageView;
    public static MediaPlayer mediaPlayerMonster, mediaPlayerOver;
    static Image  restartImage;
    static ImageView  restartImageView;
    public enum MonsterType {
        TYPE_0, TYPE_1, TYPE_2, TYPE_3
    }
    private MonsterType type;

/**
 * constructor
 * @param typeM
 * @param x
 * @param y
 */
    public Monster(MonsterType typeM, int x, int y) {
    	type = typeM;
    	switch (typeM) {
    	case TYPE_0:
    		monsterImage = new Image(getClass().getResourceAsStream("/model/resources/monster3.gif"));
    	    imageView = new ImageView(monsterImage);
     	    break;
    	case TYPE_1:
    		monsterImage = new Image(getClass().getResourceAsStream("/model/resources/monster4.gif"));
    	    imageView = new ImageView(monsterImage);
    	    break;
    	case TYPE_2:
    		monsterImage = new Image(getClass().getResourceAsStream("/model/resources/monster5.gif"));
    	    imageView = new ImageView(monsterImage);
    	    break;
    	case TYPE_3:
    		monsterImage = new Image(getClass().getResourceAsStream("/model/resources/monster3.gif"));
    	    imageView = new ImageView(monsterImage);
    	    break;
    	}
    	imageView.setFitWidth(GameViewManager.CHARACTER_SIZE);
        imageView.setFitHeight(GameViewManager.CHARACTER_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(this.imageView);
        GameViewManager.monsters.add(this);
       GameViewManager.gamePane.getChildren().add(this);
    }
    
/**
 * move of X coordinate
 * @param value
 */
	public void moveX(int value) {
        boolean movingRight = value > 0;
        for(int i = 0; i<Math.abs(value); i++) {
            for (Node platform : GameViewManager.blocks) {
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.getTranslateX() + GameViewManager.CHARACTER_SIZE == platform.getTranslateX()){
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }else {
                        	this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + GameViewManager.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }else {
                        	this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    
/**
 * move of Y coordinate    
 * @param value
 */
   public void moveY(int value){
        boolean movingDown = value > 0;
        for(int i = 0; i < Math.abs(value); i++){
            for(Block platform :GameViewManager.blocks){
                if(getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(this.getTranslateY()+ GameViewManager.CHARACTER_SIZE == platform.getTranslateY()){
                            this.setTranslateY(this.getTranslateY()-1);
                            return;
                        }else {
                        	this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                    else{
                        if(this.getTranslateY() == platform.getTranslateY()+ GameViewManager.BLOCK_SIZE){
                            this.setTranslateY(this.getTranslateY()+1);
                            return;
                        }else {
                        	this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown?1:-1));
        }
    }
   
	public void hurt(ScoreLabel l) {
		musicMonster();
		if (GameViewManager.hasShield) {
			GameViewManager.gamePane.getChildren().remove(GameViewManager.shield);
			GameViewManager.hasShield = false;
			return;
		}
		int score = Integer.parseInt(l.getText());
    	int newScore = 0;
		switch (type) {
    	case TYPE_0:
    		newScore = score - 30;
     	    break;
    	case TYPE_1:
    		newScore = score - 40;
    	    break;
    	case TYPE_2:
    		newScore = score - 50;
    	    break;
    	case TYPE_3:
    		newScore = score - 60;
    	    break;
    	}
		if(newScore > 0) {
	    	String textScore = String.valueOf(newScore);
	        l.setText(textScore);
    	}
		else {
			GameViewManager.gamePane.getChildren().clear();
			GameViewManager.blocks.clear();
			GameViewManager.monsters.clear();
			GameViewManager.bonuses.clear();
			musicOver();
			gameOverImage = new Image(getClass().getResourceAsStream("/view/resources/gameOver.png"));
			gameOverImageView = new ImageView(gameOverImage);
			gameOverImageView.setTranslateX(50);
	        gameOverImageView.setTranslateY(200);
			GameViewManager.gamePane.getChildren().add(gameOverImageView);
		/*	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
				   @Override 
				   public void handle(MouseEvent e) { 
					   GameViewManager.restart();
				   }
			};
			gameOverImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);*/
			restartImage = new Image("/view/resources/restart1.png");
			restartImageView = new ImageView(restartImage);
			restartImageView.setFitWidth(100);
			restartImageView.setFitHeight(100);
			restartImageView.setTranslateX(310);
			restartImageView.setTranslateY(530);
			GameViewManager.gamePane.getChildren().add(restartImageView);
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
				   @Override 
				   public void handle(MouseEvent e) { 
					   GameViewManager.restart();
				   }
			};
			restartImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
			
		}
	}

		public void musicMonster(){
		   // String bip = "C:\\Users\\Liza\\Downloads\\monster1.mp3";
		    String bip = "src/view/resources/monster1.mp3";
		    Media hit = new Media(Paths.get(bip).toUri().toString());
		    mediaPlayerOver = new MediaPlayer(hit);
		    mediaPlayerOver.play();
		}
		
		public void musicOver(){
			ViewManager.mediaPlayer.stop();
		 //  String bip = "C:\\Users\\Liza\\Downloads\\fail1.mp3";
		   String bip = "src/view/resources/fail1.mp3";
		    Media hit = new Media(Paths.get(bip).toUri().toString());
		    mediaPlayerMonster = new MediaPlayer(hit);
		    mediaPlayerMonster.play();
		}


		public void removeMonster() {
			GameViewManager.gamePane.getChildren().remove(this);
		}
}
