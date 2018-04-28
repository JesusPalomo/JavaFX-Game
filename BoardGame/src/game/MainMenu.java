package game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.*;


public class MainMenu extends Application { 
	private Stage primaryStage;
	private Scene mainScreen;
	private Stage gameStage = new Stage();
	private Tile[][] board = new Tile[Constants.WIDTH][Constants.HEIGHT];
	private Group tileGroup = new Group();
	private Group pawnGroup = new Group();

	public static class Constants {
		public static final int TILE_SIZE = 75;
		public static final int WIDTH = 5;
		public static final int HEIGHT = 5;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//Main menu screen
		VBox menu = new VBox(20);
		menu.setAlignment(Pos.CENTER);
		Button newGame = new Button("New Game");
		Button tutorial = new Button("Tutorial");
		ImageView logo = new ImageView(new Image("file:onitama.jpg"));
		menu.getChildren().addAll(logo, newGame, tutorial);

		//New game screen
		StackPane pane = new StackPane();
		Label label = new Label("This is a new game!");
		pane.getChildren().add(label);
		
		//Game Screens
		Scene mainScreen = new Scene(menu, 750, 750);
		this.mainScreen = mainScreen;
		Scene gameScreen = new Scene(pane, 750, 750);
		
		//Button actions
		tutorial.setOnAction(e -> primaryStage.setScene(new Scene(createTutorial(), 750, 750)));
		newGame.setOnAction(e -> {
				gameStage.setScene(gameScreen);
				gameStage.setTitle("Onitama Game");
				gameStage.setMinHeight(750);
				gameStage.setMinWidth(750);
				gameStage.show();
		});
		
		//Primary stage
		primaryStage.setScene(mainScreen);
		primaryStage.setTitle("Onitama");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.show();
	}
	
	public Parent createGame() {
		return null;
	}
	
	public Parent createTutorial() {
		StackPane instruct = new StackPane();
		Label instructions = new Label("Put instructions here");
		instruct.getChildren().add(instructions);
		
		HBox buttons = new HBox(50);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(20, 20, 20, 20));
		Button goBack = new Button("Return");
		Button next = new Button("Next");
		Button prev = new Button("Previous");
		goBack.setOnAction(e -> primaryStage.setScene(mainScreen));
		
		buttons.getChildren().addAll(goBack, prev, next);
		BorderPane tutMenu = new BorderPane();
		tutMenu.setCenter(instruct);
		tutMenu.setBottom(buttons);
		return tutMenu;
	}

}