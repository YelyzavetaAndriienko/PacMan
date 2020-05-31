package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PacMan extends Pane {
    ImageView pacMan = new ImageView("model/resources/pacmanRight.gif");
    int count = 3;
    int columns = 3;
    int offsetX = 96;
    int offsetY = 33;
    int width = 16;
    int height = 16;
    public MyAnimation animation;
    public Point2D playerVelocity = new Point2D(0,0);
    private boolean canJump = true;

    public PacMan(){
        pacMan.setFitHeight(50);
        pacMan.setFitWidth(40);
        pacMan.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new MyAnimation(pacMan,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(pacMan);
    }

    public void moveX(int value){
        boolean movingRight = false;
        if (value > 0)
        	movingRight = true;
        if (!checkForColisions(value))
        	this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
    }
    
    private boolean checkForColisions(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	public void moveY(int value){
        boolean movingDown = false;
        if (value > 0);
        if (!checkForColisions(value))
            this.setTranslateY(this.getTranslateY() + (movingDown?1:-1));
    }
}
