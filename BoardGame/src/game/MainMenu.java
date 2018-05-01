package game;

import java.util.ArrayList;

import game.Card.CardType;
import game.MoveResult.MoveType;
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
	private Tile[][] board = new Tile[Constants.WIDTH][Constants.HEIGHT];
	private Group tileGroup = new Group();
	private Group pawnGroup = new Group();
	ArrayList<CardType> cardList = new ArrayList<CardType>();

	public static class Constants {
		public static final int TILE_SIZE = 75;
		public static final int WIDTH = 5;
		public static final int HEIGHT = 5;
	}
	
	private Pawn createPawn(PawnSet set, int x, int y)  {
		Pawn pawn = new Pawn(set, x, y);
		
		pawn.setOnMouseReleased(e -> {
			int newX = toBoard(pawn.getLayoutX());
			int newY = toBoard(pawn.getLayoutY());
			MoveResult result = tryMove(pawn, newX, newY);
			int x0 = toBoard(pawn.getOldX());
			int y0 = toBoard(pawn.getOldY());
			switch(result.getType()) {
				case NONE:
					pawn.dontMove();
					break;
				case NORMAL:
					pawn.move(newX, newY);
					board[x0][y0].setPawn(null);
					board[newX][newY].setPawn(pawn);
					break;
				case KILL:
					pawn.move(newX, newY);
					board[x0][y0].setPawn(null);
					board[newX][newY].setPawn(pawn);
					Pawn pawn2 = result.getPawn();
					board[toBoard(pawn2.getOldX())][toBoard(pawn2.getOldY())].setPawn(null);
					pawnGroup.getChildren().remove(pawn2);
			}
		});
		
		return pawn;
	}
	
	private MoveResult tryMove(Pawn pawn, int newX, int newY) {
		if(board[newX][newY].hasPawn() == true) {
			if (board[newX][newY].getPawn().getSet() == pawn.getSet()) {
				return new MoveResult(MoveType.NONE);
			} else {
				return new MoveResult(MoveType.KILL);
			}
		} else {
			return new MoveResult(MoveType.NORMAL);
		}
	}
	
	private MasterPawn createMaster(PawnSet set, int x, int y)  {
		MasterPawn master = new MasterPawn(set, x, y);
		
		master.setOnMouseReleased(e -> {
			int newX = toBoard(master.getLayoutX());
			int newY = toBoard(master.getLayoutY());
			MoveResult result = tryMove(master, newX, newY);
			int x0 = toBoard(master.getOldX());
			int y0 = toBoard(master.getOldY());
			switch(result.getType()) {
				case NONE:
					master.dontMove();
					break;
				case NORMAL:
					master.move(newX, newY);
					board[x0][y0].setPawn(null);
					board[newX][newY].setPawn(master);
					break;
				case KILL:
					master.move(newX, newY);
					board[x0][y0].setPawn(null);
					board[newX][newY].setPawn(master);
					Pawn pawn2 = result.getPawn();
					board[toBoard(pawn2.getOldX())][toBoard(pawn2.getOldY())].setPawn(null);
					pawnGroup.getChildren().remove(pawn2);
			}
		});
		
		return master;
	}
	
	private MoveResult tryMove(MasterPawn master, int newX, int newY) {
		if(board[newX][newY].hasPawn() == true) {
			if (board[newX][newY].getPawn().getSet() == master.getSet()) {
				return new MoveResult(MoveType.NONE);
			} else {
				return new MoveResult(MoveType.KILL);
			}
		} else {
			return new MoveResult(MoveType.NORMAL);
		}
	}
	
	private int toBoard(double pixel) {
		return (int)(pixel + Constants.TILE_SIZE / 2) / Constants.TILE_SIZE;
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
		Scene mainScreen = new Scene(menu, 750, 750);
		this.mainScreen = mainScreen;
		
		//Button actions
		tutorial.setOnAction(e -> primaryStage.setScene(new Scene(createTutorial(), 750, 750)));
		newGame.setOnAction(e -> {
			cardList.clear();
			for(CardType type : CardType.values()) {
				cardList.add(type);
			}
			primaryStage.setScene(new Scene(createGame(), 750, 750));
		});
		
		//Primary stage
		primaryStage.setScene(mainScreen);
		primaryStage.setTitle("Onitama");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.show();
	}
	
	public Parent createGame() {
		BorderPane gameScreen = new BorderPane();
		
		HBox top = new HBox(Constants.TILE_SIZE);
		Card card1 = new Card(randomCardType());
		Card card2 = new Card(randomCardType());
		top.getChildren().addAll(card1, card2);
		top.setAlignment(Pos.CENTER);
		top.setPadding(new Insets(0, 0, 80, 65));
		top.setRotate(180);
		gameScreen.setTop(top);
		
		HBox bottom = new HBox(Constants.TILE_SIZE);
		Card card3 = new Card(randomCardType());
		Card card4 = new Card(randomCardType());
		bottom.getChildren().addAll(card3, card4);
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(0, 65, 80, 0));
		gameScreen.setBottom(bottom);
		
		VBox left = new VBox(130);
		Text turn = new Text("TURN");
		turn.setScaleX(3);
		turn.setScaleY(3);
		Button newGame = new Button("New Game");
		newGame.setOnAction(e -> {
			pawnGroup.getChildren().clear();
			cardList.clear();
			for(CardType type : CardType.values()) {
				cardList.add(type);
			}
			primaryStage.setScene(new Scene(createGame(), 750, 750));
		});
		left.getChildren().addAll(newGame, turn);
		left.setAlignment(Pos.CENTER);
		left.setPadding(new Insets(0, 0, 0, 40));
		gameScreen.setLeft(left);
		
		VBox right = new VBox();
		Card card5 = new Card(randomCardType());
		right.getChildren().add(card5);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(0, 30, 0, 0));
		gameScreen.setRight(right);
		
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
				Pawn pawn = null;
				MasterPawn master = null;
				if(y == 0) {
					if(x == 2) {
						master = createMaster(PawnSet.BLUE, x, y);
					} else {
						pawn = createPawn(PawnSet.BLUE, x, y);
					}
				} else if(y == 4) {
					if(x == 2) {
						master = createMaster(PawnSet.RED, x, y);
					} else {
						pawn = createPawn(PawnSet.RED, x, y);
					}
				}
				if(pawn != null) pawnGroup.getChildren().add(pawn);
				if(master != null) pawnGroup.getChildren().add(master);
			}
		}
		center.getChildren().addAll(tileGroup, pawnGroup);
		gameScreen.setCenter(center);
		return gameScreen;
	}
	
	private CardType randomCardType() {
		int index = (int)(Math.random() * cardList.size());
		CardType rando = cardList.get(index);
		cardList.remove(index);
		return rando;
	}
	
	
	public Parent createTutorial() {
		StackPane instruct = new StackPane();
		ImageView image = new ImageView(new Image("file:boardgame.PNG"));
		image.setFitHeight(500);
		image.setFitWidth(500);
		instruct.getChildren().add(image);
		
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