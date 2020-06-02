package view;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bonus;
import model.InfoLabel;
import model.ScoreLabel;

import java.io.IOException;
import java.util.ArrayList;

public class GameViewManager {

	public static AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private boolean pause = false;
	private AnimationTimer timer;
	public static ArrayList<Block> blocks = new ArrayList<>();
	private final static int GAME_HEIGHT = 800;
	private final static int GAME_WIDTH = 800;
	public final static int BLOCK_SIZE = 40;
	public final static int CHARACTER_SIZE = 35;
	public final static int HEART_SIZE = 35;
	private int levelWidth;
	private int levelNumber = 0;
	private PacMan player;
	private Bonus heart;
	private Bonus star;
	private Bonus shield;
	private ScoreLabel life;
	public GameViewManager() throws IOException {
		initializeStage();
	}
	private void initializeStage() throws IOException {
		gamePane = new AnchorPane();
		gamePane.setStyle("-fx-background-color: transparent; -fx-background-image: url('/view/resources/fon2.gif');");
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameScene.setOnKeyPressed(event-> Main.keys.put(event.getCode(), true));
		gameScene.setOnKeyReleased(event -> {
			Main.keys.put(event.getCode(), false);
		});
		levelWidth = model.LevelData.levels[levelNumber][0].length()*BLOCK_SIZE;
		for(int i = 0; i < model.LevelData.levels[levelNumber].length; i++){
			String line = model.LevelData.levels[levelNumber][i];
			for(int j = 0; j < line.length(); j++){
				switch (line.charAt(j)){
					case '0':
						break;
					case '1':
						Block platformFloor = new Block(Block.BlockType.PLATFORM, j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '2':
						Block brick = new Block(Block.BlockType.BRICK,j*BLOCK_SIZE,i*BLOCK_SIZE);
						break;
					case '3':
						Block bonus = new Block(Block.BlockType.BONUS,j*BLOCK_SIZE,i*BLOCK_SIZE);
						break;
					case '4':
						Block stone = new Block(Block.BlockType.STONE,j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '5':
						Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP,j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '6':
						Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM,j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '*':
						Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK,j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
				}
			}

		}
		player = new PacMan();
		player.setTranslateX(321);
		player.setTranslateY(320);
		gamePane.getChildren().add(player);
		
		heart = new Bonus();
		heart.setTranslateX(281);
		heart.setTranslateY(240);
		gamePane.getChildren().add(heart);
		
		Image heartImage = new Image("view/resources/smallHeart.png");
     	ImageView heartV = new ImageView(heartImage);
     	heartV.setLayoutX(340);
     	heartV.setLayoutY(0);
     	gamePane.getChildren().add(heartV);
		
		life = new ScoreLabel("80");
		life.setLayoutX(360);
		life.setLayoutY(-187);
		gamePane.getChildren().add(life);
		
		gameStage.setScene(gameScene);
		gameStage.setResizable(false);
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
		
		
		
	}
	/*	gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent t) {
	        	if(Main.isPressed(KeyCode.SPACE) && pause==true){
	        		pause = false;
	                timer.stop();
	    		}
	        	if(Main.isPressed(KeyCode.SPACE) && pause==false){
	        		pause = true;
                    timer.start();
	    		}
	        }
	    });
	}*/
		
		
	private void update(){
		if(Main.isPressed(KeyCode.UP) && player.getTranslateY()>=5){
			player.setRotate(-90);
			player.moveY(-5);
			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
				gamePane.getChildren().remove(heart);
				heart.heartAction(life);
			}
		}
		if(Main.isPressed(KeyCode.LEFT) && player.getTranslateX()>=5){
			player.setRotate(180);
			player.moveX(-5);
			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
				gamePane.getChildren().remove(heart);
				heart.heartAction(life);
			}
		}
		if(Main.isPressed(KeyCode.RIGHT) && player.getTranslateX()+40 <=levelWidth-5){
			player.setRotate(0);
			player.moveX(5);
			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
				gamePane.getChildren().remove(heart);
				heart.heartAction(life);
			}
		}
		if(Main.isPressed(KeyCode.DOWN)) {
			player.setRotate(90);
			player.moveY(5);
			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
				gamePane.getChildren().remove(heart);
				heart.heartAction(life);
			}
		}
		/*if (Main.isPressed(KeyCode.SPACE)) {
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }*/
	}
	/*
	 * if(Main.isPressed(KeyCode.SPACE)){
            showAlert();
        }
	 */
	private void pause(){
		if(Main.isPressed(KeyCode.SPACE) && pause==true){
    		pause = false;
            timer.stop();
            showAlert();
            }
    	if(Main.isPressed(KeyCode.SPACE) && pause==false){
    		pause = true;
            timer.start();
		}
	}
	
	 private void showAlert() {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("PAUSE");
	        alert.setHeaderText("The game is paused");
	        alert.setContentText("To start again, press 'Space'");
	        alert.show();
	    }
	 
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.menuStage.setResizable(false);
		menuStage.setResizable(false);
		gameStage.show();
}
}
