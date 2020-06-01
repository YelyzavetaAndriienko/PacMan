package view;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InfoLabel;
import model.ScoreLabel;

import java.io.IOException;
import java.util.ArrayList;

public class GameViewManager {

	public static AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	public static ArrayList<Block> blocks = new ArrayList<>();
	private final static int GAME_HEIGHT = 800;
	private final static int GAME_WIDTH = 800;
	public final static int BLOCK_SIZE = 40;
	public final static int CHARACTER_SIZE = 35;
	private int levelWidth;
	private int levelNumber = 0;
	private PacMan player;
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
		player.setTranslateX(160);
		player.setTranslateY(760);
		player.translateXProperty().addListener((obs,old,newValue)->{
			int offset = newValue.intValue();
			if(offset>640 && offset<levelWidth-640){
				gamePane.setLayoutX(-(offset-640));
			}
		});
		gamePane.getChildren().add(player);

		Image heart = new Image("view/resources/smallHeart.png");
     	ImageView heartV = new ImageView(heart);
     	heartV.setLayoutX(340);
     	heartV.setLayoutY(0);
     	gamePane.getChildren().add(heartV);
		
		ScoreLabel life = new ScoreLabel("100");
		life.setLayoutX(360);
		life.setLayoutY(-187);
		gamePane.getChildren().add(life);
		
		gameStage.setScene(gameScene);
		//gameStage.setResizable(false);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}
	private void update(){
		if(Main.isPressed(KeyCode.UP) && player.getTranslateY()>=5){
			player.setRotate(-90);
			player.moveY(-5);
		}
		if(Main.isPressed(KeyCode.LEFT) && player.getTranslateX()>=5){
			player.setRotate(180);
			player.moveX(-5);
		}
		if(Main.isPressed(KeyCode.RIGHT) && player.getTranslateX()+40 <=levelWidth-5){
			player.setRotate(0);
			player.moveX(5);
		}
		if(Main.isPressed(KeyCode.DOWN)) {
			player.setRotate(90);
			player.moveY(5);
		}
	}
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.menuStage.setResizable(false);
		menuStage.setResizable(false);
		gameStage.show();
}
}
