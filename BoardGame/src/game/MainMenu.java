package game;
import game.Card.MoveResult;
import game.Pawn.PawnSet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	
//	private Pawn createPawn(PawnSet set, int x, int y)  {
//		Pawn pawn = new Pawn(set, x, y);
//		
//		pawn.setOnMouseReleased(e -> {
//			int newX = toBoard(pawn.getLayoutX());
//			int newY = toBoard(pawn.getLayoutY());
//			
//		});
//		
//		return pawn;
//	}
	
//	private MoveResult tryMove(Pawn pawn, int newX, int NewY) {
//		if(board[newX][newY].hasPawn() || ())
//	}
	
//	private int toBoard(double pixel) {
//		return (int)(pixel + Constants.TILE_SIZE / 2) / Constants.TILE_SIZE;
//	}
	
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
		Scene mainScreen = new Scene(menu, 750, 750);
		this.mainScreen = mainScreen;
		
		//Button actions
		tutorial.setOnAction(e -> primaryStage.setScene(new Scene(createTutorial(), 750, 750)));
		newGame.setOnAction(e -> primaryStage.setScene(new Scene(createGame(), 750, 750)));
		
		//Primary stage
		primaryStage.setScene(mainScreen);
		primaryStage.setTitle("Onitama");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.show();
	}
	
	public Parent createGame() {
		BorderPane gameScreen = new BorderPane();
//		HBox top = new HBox();
//		HBox bottom = new HBox();
		VBox left = new VBox(130);
		left.getChildren().addAll(new Button("New Game"), new Text("TURN"));
		left.setAlignment(Pos.CENTER);
		gameScreen.setLeft(left);
//		VBox right = new VBox();
		StackPane center = new StackPane();
		for(int y = 0; y < Constants.HEIGHT; y++) {
			for(int x = 0; x < Constants.WIDTH; x++) {
				Tile tile = new Tile(x, y);
				board[x][y] = tile;
				if(x == 2 && y == 0) {
					tile.setFill(Color.DODGERBLUE);
				} else if(x == 2 && y == 4) {
					tile.setFill(Color.FIREBRICK);
				}
				tileGroup.getChildren().add(tile);
//				Pawn pawn = null;
//				MasterPawn master = null;
//				if(y == 0) {
//					if(x == 2) {
//						master = 
//					}
//				}
			}
		}
		center.getChildren().addAll(tileGroup);
		gameScreen.setCenter(center);
		return gameScreen;
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