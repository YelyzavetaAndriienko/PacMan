package view;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ButtonMute;
import model.InfoLabel;
import model.PacManButton;
import model.PacManSubscene;
/**
 * class: ViewManager
 *creates components for main menu
 */
public class ViewManager {

	private final static int HEIGHT = 600;
	private final static int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage gameStage;
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	private PacManSubscene startSubscene;
	private PacManSubscene helpSubscene;
	private PacManSubscene creditsSubscene;
	private PacManSubscene exitSubscene;
	private PacManSubscene sceneToHide;
	public boolean isUnmute = true;
	public static MediaPlayer mediaPlayer;
	List<PacManButton> menuButtons;
	public String bip;
	public Media hit;

/**
 * constructor
 */
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubscenes();
		createButtons();
		createMuteButton();
		createLogo();
		createBackground();
		//  bip = "C:\\Users\\Liza\\Downloads\\sound.mp3";
		bip = "src/view/resources/sound.mp3";
		     hit = new Media(Paths.get("src/view/resources/sound.mp3").toUri().toString());
		   hit = new Media(Paths.get(bip).toUri().toString());
		    mediaPlayer = new MediaPlayer(hit);
		    mediaPlayer.play();
		    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		}
	
	public void createNewMenu(Stage game) {
		this.gameStage = game;
		this.gameStage.hide();
		this.gameStage.setResizable(false);
		gameStage.setResizable(false);
		mainStage.show();
	}

/**
 * getMainStage
 * @return
 */
    public Stage getMainStage() {
    	return mainStage;
    }

/**
 * showSubscene
 * @param subscene
 */
    private void showSubscene(PacManSubscene subscene) {
    	if(sceneToHide != null) {
    		sceneToHide.moveSubscene();
    	}
    	subscene.moveSubscene();
    	sceneToHide = subscene;
    }

/**
 * create subscenes
 */
    private void createSubscenes() {
    	createStartSubscene();

    	createHelpSubscene();

    	createCreditsSubscene();

    	exitSubscene = new PacManSubscene();
    	mainPane.getChildren().add(exitSubscene);
    }

/**
 * addMenuButton
 * @param button
 */
    private void addMenuButton(PacManButton button) {
    	button.setLayoutX(MENU_BUTTONS_START_X);
    	button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
    	menuButtons.add(button);
    	mainPane.getChildren().add(button);
    }

/**
 * createButtons
 */
    private void createButtons() {
    	createStartButton();
    	createHelpButton();
    	createCreditsButton();
    	createExitButton();
    }

/**
 * createStartSubscene
 */
    private void createStartSubscene() {
    	startSubscene = new PacManSubscene();
    	mainPane.getChildren().add(startSubscene);
    	InfoLabel startLabel = new InfoLabel("Are you READY ?");
    	startLabel.setLayoutX(80);
    	startLabel.setLayoutY(-70);
    	startSubscene.getPane().getChildren().add(startLabel);
    	startSubscene.getPane().getChildren().add(createButtonToStart());
    }

/**
 * createHelpSubscene
 */
    private void createHelpSubscene() {
    	helpSubscene = new PacManSubscene();
    	mainPane.getChildren().add(helpSubscene);
    	InfoLabel helpLabel = new InfoLabel("Guide Pacman around the maze to the final point. \r\n" +
    			"Try to avoid these monsters:\r\n");
    	helpLabel.setLayoutX(-30);
    	helpLabel.setLayoutY(-130);
    	helpSubscene.getPane().getChildren().add(helpLabel);

    	InfoLabel blue = new InfoLabel("BLUE");
    	blue.setLayoutX(-30);
    	blue.setLayoutY(-80);
    	helpSubscene.getPane().getChildren().add(blue);

    	Image image = new Image("view/resources/m0.gif");
     	ImageView img = new ImageView(image);
     	img.setLayoutX(10);
     	img.setLayoutY(132);
     	helpSubscene.getPane().getChildren().add(img);

   /*  	InfoLabel green = new InfoLabel("GREEN");
     	green.setLayoutX(50);
     	green.setLayoutY(-80);
    	helpSubscene.getPane().getChildren().add(green);

     	Image image1 = new Image("view/resources/m1.gif");
     	ImageView img1 = new ImageView(image1);
     	img1.setLayoutX(90);
     	img1.setLayoutY(132);
     	helpSubscene.getPane().getChildren().add(img1);
*/
     	InfoLabel purple = new InfoLabel("PURPLE");
     	purple.setLayoutX(50);
     	purple.setLayoutY(-80);
    	helpSubscene.getPane().getChildren().add(purple);

     	Image image2 = new Image("view/resources/m2.gif");
     	ImageView img2 = new ImageView(image2);
     	img2.setLayoutX(90);
     	img2.setLayoutY(132);
     	helpSubscene.getPane().getChildren().add(img2);

     	InfoLabel red = new InfoLabel("RED");
     	red.setLayoutX(160);
     	red.setLayoutY(-80);
    	helpSubscene.getPane().getChildren().add(red);

     	Image image3 = new Image("view/resources/m3.gif");
     	ImageView img3 = new ImageView(image3);
     	img3.setLayoutX(180);
     	img3.setLayoutY(132);
     	helpSubscene.getPane().getChildren().add(img3);

     	InfoLabel helpLabel1 = new InfoLabel("Try to collect these bonuses:");
    	helpLabel1.setLayoutX(-30);
    	helpLabel1.setLayoutY(0);
    	helpSubscene.getPane().getChildren().add(helpLabel1);

   /* 	Image image22 = new Image("view/resources/b2.gif");
     	ImageView img22 = new ImageView(image22);
     	img22.setLayoutX(10);
     	img22.setLayoutY(230);
     	helpSubscene.getPane().getChildren().add(img22);*/

    	Image image11 = new Image("view/resources/b1.gif");
     	ImageView img11 = new ImageView(image11);
     	img11.setLayoutX(10);
     	img11.setLayoutY(240);
     	helpSubscene.getPane().getChildren().add(img11);

     	Image image33 = new Image("view/resources/b3.png");
     	ImageView img33 = new ImageView(image33);
     	img33.setLayoutX(120);
     	img33.setLayoutY(240);
     	helpSubscene.getPane().getChildren().add(img33);
    }

/**
 * createCreditsSubscene
 */
    private void createCreditsSubscene() {
    	creditsSubscene = new PacManSubscene();
    	mainPane.getChildren().add(creditsSubscene);
    	InfoLabel creditsLabel = new InfoLabel("Game \"NEW PAC-MAN\" was created by the team NullCoderException (Andriienko Yelyzaveta, Bezruka Anastasiia, Valentyi Yaroslav).\r\n" +
    			"If you have some questions, write us an email - nullcoderexception@gmail.com");
    	creditsLabel.setLayoutX(-25);
    	creditsLabel.setLayoutY(-50);
    	creditsSubscene.getPane().getChildren().add(creditsLabel);
    }

/**
 * createButtonToStart
 * @return
 */
    private PacManButton createButtonToStart() {
    	PacManButton startButton = new PacManButton("START");
    	startButton.setLayoutX(100);
    	startButton.setLayoutY(250);
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
				GameViewManager gameManager = null;
				try {
					gameManager = new GameViewManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
				gameManager.createNewGame(mainStage);
				mediaPlayer.stop();
				//bip = "C:\\Users\\Liza\\Downloads\\fon2.mp3";
				bip = "src/view/resources/fon2.mp3";
				    hit = new Media(Paths.get(bip).toUri().toString());
				    mediaPlayer = new MediaPlayer(hit);
				    mediaPlayer.play();
				    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	}
    	});
    	return startButton;
    }

/**
 * createStartButton
 */
    private void createStartButton() {
    	PacManButton startButton = new PacManButton("PLAY");
    	addMenuButton(startButton);
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			showSubscene(startSubscene);
    		}
    	});
    }

/**
 * createHelpButton
 */
    private void createHelpButton() {
    	PacManButton helpButton = new PacManButton("HELP");
    	addMenuButton(helpButton);
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			showSubscene(helpSubscene);
    		}
    	});
    }

/**
 * createCreditsButton
 */
    private void createCreditsButton() {
    	PacManButton creditsButton = new PacManButton("CREDITS");
    	addMenuButton(creditsButton);
    	creditsButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			showSubscene(creditsSubscene);
    		}
    	});
    }

/**
 * createExitButton
 */
    private void createExitButton() {
    	PacManButton exitButton = new PacManButton("EXIT");
    	addMenuButton(exitButton);
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			mainStage.close();
    		}
    	});
    }

/**
 * createMuteButton
 */
    private void createMuteButton() {
    	ButtonMute muteButton = new ButtonMute();
    	muteButton.setLayoutX(10);
    	muteButton.setLayoutY(10);
    	mainPane.getChildren().add(muteButton);
    	muteButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			if(isUnmute==true) {
    				    mediaPlayer.play();
    				    isUnmute=false;
    			}else {
    				 mediaPlayer.stop();
    				 isUnmute=true;
    			}
    		}
    	});
    }

/**
 * createBackground
 */
    private void createBackground() {
    	Image backgroundImage = new Image("view/resources/fon2.gif", 256, 256, false, true);
    	BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
    	mainPane.setBackground(new Background(background));
    }

/**
 * createLogo
 */
    private void createLogo() {
        Image image = new Image("view/resources/logo1.png");
    	ImageView img = new ImageView(image);
    	img.setLayoutX(350);
    	img.setLayoutY(20);

    	img.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    			DropShadow d = new DropShadow();
    			d.setColor(Color.WHITE);
    			img.setEffect(d);
    		}
    	});

    	img.setOnMouseExited(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
    			img.setEffect(null);
    		}
    	});
    	mainPane.getChildren().add(img);
    }
}
