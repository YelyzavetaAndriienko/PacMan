package view;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Bonus;
import model.ButtonMute;
import model.ScoreLabel;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * class: GameViewManager
 *creates panel for game
 */
public class GameViewManager {

	public static AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private boolean pause = false;
	private AnimationTimer timer;
	public static ArrayList<Block> blocks = new ArrayList<>();
	public static ArrayList<Monster> monsters = new ArrayList<>();
	public static ArrayList<Bonus> bonuses = new ArrayList<>();
	private final static int GAME_HEIGHT = 800;
	private final static int GAME_WIDTH = 800;
	public final static int BLOCK_SIZE = 40;
	public final static int CHARACTER_SIZE = 35;
	public final static int BONUS_SIZE = 35;
	private int levelWidth;
	private static int levelNumber = 0;
	private static int levelStatus = 1;
	public static boolean isUnmute = true;
	private static PacMan player;
	public static ImageView shield;
	public static ScoreLabel life, level;
	public static boolean hasShield = false;
	static Image theEndImage;
    static ImageView theEndImageView;

    public static ImageView mon;
    public static Monster mons;
    public static MediaPlayer mediaPlayerWin;

	
/**
 * constructor
 * @throws IOException
 */
	public GameViewManager() throws IOException {
		initializeStage();
	}
	
/**
 * initializeStage - main method
 * @throws IOException
 */
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
		
		Image shieldImg = new Image("view/resources/b3.png");
     	shield = new ImageView(shieldImg);
     	shield.setLayoutX(170);
     	shield.setLayoutY(5);
     	shield.setFitWidth(30);
	    shield.setFitHeight(30);
		
		changeLevel();
		
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
	
/**
 * allows to move to the next level
 */
	static void changeLevel() {
		String lifeLeft = "100";
		if (life != null)
			lifeLeft = life.getText();
		gamePane.getChildren().clear();
		blocks.clear();
		monsters.clear();
		bonuses.clear();
		if(levelNumber<7) {
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
						Monster monster0 = new Monster(Monster.MonsterType.TYPE_0, j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '3':
						Monster monster1 = new Monster(Monster.MonsterType.TYPE_1, j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '4':
						Monster monster2 = new Monster(Monster.MonsterType.TYPE_2, j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
					case '5':
						Bonus bonus1 = new Bonus(Bonus.BonusType.HEART,j*BLOCK_SIZE,i*BLOCK_SIZE);
						break;
					case '6':
						Bonus bonus2 = new Bonus(Bonus.BonusType.SHIELD,j*BLOCK_SIZE,i*BLOCK_SIZE);
						break;
					case '*':
						Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK,j * BLOCK_SIZE, i * BLOCK_SIZE);
						break;
				}
			}
		}
		
		
		life = new ScoreLabel(lifeLeft);
		life.setLayoutX(500);
		life.setLayoutY(-185);
		gamePane.getChildren().add(life);
		
		level = new ScoreLabel("Level " + levelStatus);
		level.setLayoutX(200);
		level.setLayoutY(-185);
		gamePane.getChildren().add(level);
		
		createMuteButton();
		
		Image heartImage = new Image("view/resources/smallHeart.png");
     	ImageView heartV = new ImageView(heartImage);
     	heartV.setLayoutX(480);
     	heartV.setLayoutY(0);
     	gamePane.getChildren().add(heartV);
     	spawnCar();
		levelStatus++;
		levelNumber++;
		player = new PacMan();
		player.setTranslateX(41);
		player.setTranslateY(81);
		gamePane.getChildren().add(player);
		}else {
			gamePane.getChildren().clear();

			ViewManager.mediaPlayer.stop();
			musicWin();

			theEndImage = new Image("/view/resources/theEnd.png");
			theEndImageView = new ImageView(theEndImage);
			theEndImageView.setTranslateX(180);
			theEndImageView.setTranslateY(300);
			gamePane.getChildren().add(theEndImageView);
			}
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
	 private static void spawnCar() {
		//	 mons = new Monster(Monster.MonsterType.TYPE_0, BLOCK_SIZE, 2*BLOCK_SIZE);
		//	 gamePane.getChildren().add(mons);
		 
	    }

	    
		
	private void update(){
		//mons.moveX();
		//mons.moveY();
//		for (int i = 0; i < monsters.size(); i++) {
//			monsters.get(i).move();
//		}
		//mon.setTranslateX(mon.getTranslateX() + Math.random() * 10);
		//if(mons.getTranslateX()+40 <=levelWidth-5){
     //   mons.moveX((int)player.getTranslateX());
     //   mons.moveY((int)player.getTranslateY());
		//}
		if(Main.isPressed(KeyCode.UP) && player.getTranslateY()>=5){
			player.setRotate(-90);
			player.moveY(-5);
//			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
//				gamePane.getChildren().remove(heart);
//				heart.heartAction(life);
//			}
		}
		if(Main.isPressed(KeyCode.LEFT) && player.getTranslateX()>=5){
			player.setRotate(180);
			player.moveX(-5);
//			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
//				gamePane.getChildren().remove(heart);
//				heart.heartAction(life);
//			}
		}
		if(Main.isPressed(KeyCode.RIGHT) && player.getTranslateX()+40 <=levelWidth-5){
			player.setRotate(0);
			player.moveX(5);
//			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
//				gamePane.getChildren().remove(heart);
//				heart.heartAction(life);
//			}
		}
		if(Main.isPressed(KeyCode.DOWN)) {
			player.setRotate(90);
			player.moveY(5);
			
//			if(heart.getTranslateX()==player.getTranslateX() & heart.getTranslateY()==player.getTranslateY()) {
//				gamePane.getChildren().remove(heart);
//				heart.heartAction(life);
//			}
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

/**
 * creates new game
 * @param menuStage
 */
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.menuStage.setResizable(false);
		menuStage.setResizable(false);
		gameStage.show();
	}
	
/**
 * return life
 * @return
 */
	public ScoreLabel getLife() {
		return life;
	}

/**
 * set life for pacman
 * @param life
 */
	public void setLife(ScoreLabel life) {
		this.life = life;
	}

/**
 * creates mute button
 */

	private static void createMuteButton() {
    	ButtonMute muteButton = new ButtonMute();
    	muteButton.setLayoutX(10);
    	muteButton.setLayoutY(5);
    	gamePane.getChildren().add(muteButton);
    	muteButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			if(isUnmute==true) {
    				ViewManager.mediaPlayer.play();
    				    isUnmute=false;
    			}else {
    				ViewManager.mediaPlayer.stop();
    				 isUnmute=true;
    			}
    		}
    	});
    }
	
/**
 * adds music for shield	
 */
			public static void musicWin(){
				  //  String bip = "C:\\Users\\Liza\\Downloads\\win2.mp3";
					String bip = "src/view/resources/win2.mp3";
				    Media hit = new Media(Paths.get(bip).toUri().toString());
				    mediaPlayerWin = new MediaPlayer(hit);
				  mediaPlayerWin.play();
				}
}
