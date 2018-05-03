package game;

//Heavy inspiration from Almas Baimagambetov (almaslvl@gmail.com) to create this game

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
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
		
		VBox right = new VBox();
		Card card5 = new Card(randomCardType());
		right.getChildren().add(card5);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(0, 30, 0, 0));
		gameScreen.setRight(right);
		
		VBox left = new VBox(65);
		Text turn = new Text("TURN");
		turn.setFill(card5.getCardType().getSet().getColor());
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
		Button main = new Button("Main Menu");
		main.setOnAction(e -> {
			primaryStage.setScene(mainScreen);
		});
		left.getChildren().addAll(newGame, main, turn);
		left.setAlignment(Pos.CENTER);
		left.setPadding(new Insets(0, 0, 0, 40));
		gameScreen.setLeft(left);

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
		Text i1 = new Text("Each player has five pawns (one master + four students) of either blue or red."
				+ "The pawn's start off on five tiles across from each other with the master pawn located "
				+ "on the shrine. Five random cards are then displayed on the board. Two are given to the "
				+ "players (this is your 'hand'), and one is set off to the side. The color of the card"
				+ " placed to the side dictates who goes first. To play, choose of the cards in your hand. "
				+ "This will dictate how your pawn will move. The start square (the darkest tile in the "
				+ "middle) represents your pawn's location and the lighter, colored tiles surrounding it "
				+ "represent where you can move your pawn. You cannot move the pawn out of the board "
				+ "(you will break the game doing this too) or to a tile where one of your pawns is already "
				+ "located. If you move the pawn to a tile occupied by your opponent's pawn, you 'capture' "
				+ "it, effectively removing it from the game.");
		i1.setWrappingWidth(650);
		Text i2 = new Text("You cannot move the pawn out of the board (you will break the game doing this too) "
				+ "or to a tile where one of your pawns is already located. If you move the pawn to a tile "
				+ "occupied by your opponent's pawn, you 'capture' it, effectively removing it from the game. "
				+ "The card you used is then switched with the card set off to the side, ending your turn. It "
				+ "is then your opponent's turn, repeating the same actions as you. You must always move your "
				+ "pawn on your turn, unless it is impossible to do so. If that is the case, you may 'pass' your"
				+ " turn. But you still must exchange one of your cards with the one off to the side. There are "
				+ "two ways to win: 1.) Capturing your opponent's master pawn or 2.) Landing your master pawn "
				+ "on the opponent's shrine.");
		i2.setWrappingWidth(650);
		
		StackPane ontiamaImage = new StackPane();
		ImageView image = new ImageView(new Image("file:boardgame.PNG"));
		image.setFitHeight(500);
		image.setFitWidth(500);
		ontiamaImage.getChildren().add(image);
		
		HBox instructions = new HBox();
		instructions.setAlignment(Pos.CENTER);
		instructions.setPadding(new Insets(0, 50, 0, 50));
		instructions.getChildren().add(i1);
		
		HBox buttons = new HBox(50);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(20, 20, 20, 20));
		Button goBack = new Button("Return");
		Button next = new Button("Next");
		next.setOnAction(e -> {
			instructions.getChildren().clear();
			instructions.getChildren().add(i2);
		});
		Button prev = new Button("Previous");
		prev.setOnAction(e -> {
			instructions.getChildren().clear();
			instructions.getChildren().add(i1);
		});
		goBack.setOnAction(e -> primaryStage.setScene(mainScreen));
		buttons.getChildren().addAll(goBack, prev, next);
		
		GridPane gp = new GridPane();
		gp.setVgap(50);
		gp.add(instructions, 0, 0);
		gp.add(buttons, 0, 1);
		
		BorderPane tutMenu = new BorderPane();
		tutMenu.setCenter(ontiamaImage);
		tutMenu.setBottom(gp);
		return tutMenu;
	}

	private Pawn createPawn(PawnSet set, int x, int y)  {
		Pawn pawn = new Pawn(set, x, y);
		
		pawn.setOnMouseReleased(e -> {
			int endX = toTile(pawn.getLayoutX());
			int endY = toTile(pawn.getLayoutY());
			MoveResult result = tryMove(pawn, endX, endY);
			int startX = toTile(pawn.getOldX());
			int startY = toTile(pawn.getOldY());
			switch(result.getType()) {
				case ILLEGAL:
					pawn.dontMove();
					break;
				case LEGAL:
					pawn.move(endX, endY);
					board[startX][startY].setPawn(null);
					board[endX][endY].setPawn(pawn);
					break;
				case CAPTURE:
					pawn.move(endX, endY);
					board[startX][startY].setPawn(null);
					board[endX][endY].setPawn(pawn);
					Pawn pawn2 = result.getPawn();
					board[toTile(pawn2.getOldX())][toTile(pawn2.getOldY())].setPawn(null);
					pawnGroup.getChildren().remove(pawn2);
			}
		});
		
		return pawn;
	}
	
	private MoveResult tryMove(Pawn pawn, int endX, int endY) {
		if(board[endX][endY].hasPawn() == true) {
			if (board[endX][endY].getPawn().getSet() == pawn.getSet()) {
				return new MoveResult(MoveType.ILLEGAL);
			} else {
				int x1 = toTile(pawn.getOldX()) + (endX - toTile(pawn.getOldX()));
				int y1 = toTile(pawn.getOldY()) + (endY - toTile(pawn.getOldY()));
				return new MoveResult(MoveType.CAPTURE, board[x1][y1].getPawn());
			}
		} else {
			return new MoveResult(MoveType.LEGAL);
		}
	}
	
	private MasterPawn createMaster(PawnSet set, int x, int y)  {
		MasterPawn master = new MasterPawn(set, x, y);
		
		master.setOnMouseReleased(e -> {
			int endX = toTile(master.getLayoutX());
			int endY = toTile(master.getLayoutY());
			MoveResult result = tryMove(master, endX, endY);
			int startX = toTile(master.getOldX());
			int startY = toTile(master.getOldY());
			switch(result.getType()) {
				case ILLEGAL:
					master.dontMove();
					break;
				case LEGAL:
					master.move(endX, endY);
					board[startX][startY].setPawn(null);
					board[endX][endY].setPawn(master);
					break;
				case CAPTURE:
					master.move(endX, endY);
					board[startX][startY].setPawn(null);
					board[endX][endY].setPawn(master);
					Pawn pawn2 = result.getPawn();
					board[toTile(pawn2.getOldX())][toTile(pawn2.getOldY())].setPawn(null);
					pawnGroup.getChildren().remove(pawn2);
			}
		});
		
		return master;
	}
	
	private MoveResult tryMove(MasterPawn master, int endX, int endY) {
		if(board[endX][endY].hasPawn() == true) {
			if (board[endX][endY].getPawn().getSet() == master.getSet()) {
				return new MoveResult(MoveType.ILLEGAL);
			} else {
				int x1 = toTile(master.getOldX()) + (endX - toTile(master.getOldX()));
				int y1 = toTile(master.getOldY()) + (endY - toTile(master.getOldY()));
				return new MoveResult(MoveType.CAPTURE, board[x1][y1].getPawn());
			}
		} else {
			return new MoveResult(MoveType.LEGAL);
		}
	}
	
	private int toTile(double location) {
		return (int)(location + Constants.TILE_SIZE / 2) / Constants.TILE_SIZE;
	}
	
	
}