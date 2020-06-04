package view;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Bonus;
import view.GameViewManager;
import view.ViewManager;


public class PacMan extends Pane {
    Image pacmanImage = new Image(getClass().getResourceAsStream("/model/resources/pacmanRight.gif"));
    ImageView imageView = new ImageView(pacmanImage);
    public Point2D pacmanVelocity = new Point2D(0,0);

    public PacMan() {
        imageView.setFitWidth(GameViewManager.CHARACTER_SIZE);
        imageView.setFitHeight(GameViewManager.CHARACTER_SIZE);
        getChildren().addAll(this.imageView);
    }

    public void moveX(int value) {
        boolean movingRight = value > 0;
        for(int i = 0; i<Math.abs(value); i++) {
            for (Node platform : GameViewManager.blocks) {
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.getTranslateX() + GameViewManager.CHARACTER_SIZE == platform.getTranslateX()){
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + GameViewManager.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            try {
            	for (Bonus bonus : GameViewManager.bonuses) {
	            	  if(this.getBoundsInParent().intersects(bonus.getBoundsInParent())) {
	                     bonus.action(GameViewManager.life);
	                     GameViewManager.bonuses.remove(bonus);
	            	  }
            	}
            }
            catch(ConcurrentModificationException e) {

            }
            for (Monster monster : GameViewManager.monsters) {
            	if(this.getBoundsInParent().intersects(monster.getBoundsInParent())) {
            		if (movingRight) {
                        if (this.getTranslateX() + GameViewManager.CHARACTER_SIZE == monster.getTranslateX()){
                            monster.hurt(GameViewManager.life);
                        	this.setTranslateX(this.getTranslateX() + GameViewManager.CHARACTER_SIZE);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == monster.getTranslateX() + GameViewManager.CHARACTER_SIZE) {
                        	monster.hurt(GameViewManager.life);
                        	this.setTranslateX(this.getTranslateX() - GameViewManager.CHARACTER_SIZE);
                            return;
                        }
                    }
           	    }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value){
        boolean movingDown = value > 0;
        for(int i = 0; i < Math.abs(value); i++){
            for(Block platform :GameViewManager.blocks){
                if(getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(this.getTranslateY()+ GameViewManager.CHARACTER_SIZE == platform.getTranslateY()){
                            this.setTranslateY(this.getTranslateY()-1);
                            return;
                        }
                    }
                    else{
                        if(this.getTranslateY() == platform.getTranslateY()+ GameViewManager.BLOCK_SIZE){
                            this.setTranslateY(this.getTranslateY()+1);
                            pacmanVelocity = new Point2D(0,10);
                            return;
                        }
                    }
                }
            }
            for (Bonus bonus : GameViewManager.bonuses) {
          	  if(this.getBoundsInParent().intersects(bonus.getBoundsInParent())) {
                    bonus.action(GameViewManager.life);
          	  }
            }
            for (Monster monster : GameViewManager.monsters) {
            	if(this.getBoundsInParent().intersects(monster.getBoundsInParent())) {
            		if (movingDown) {
                        if (this.getTranslateY() + GameViewManager.CHARACTER_SIZE == monster.getTranslateY()){
                            monster.hurt(GameViewManager.life);
                        	this.setTranslateY(this.getTranslateY() + GameViewManager.CHARACTER_SIZE);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == monster.getTranslateY() + GameViewManager.CHARACTER_SIZE) {
                        	monster.hurt(GameViewManager.life);
                        	this.setTranslateY(this.getTranslateY() - GameViewManager.CHARACTER_SIZE);
                            return;
                        }
                    }
           	    }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown?1:-1));
        }
    }
}

