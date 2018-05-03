package game;

import game.MainMenu.Constants;
import game.Pawn.PawnSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Card extends StackPane {
	
	private CardType type;
	
	public enum CardType {
		BOAR(PawnSet.RED, new ImageView(new Image("file:Boar.jpg"))), CRAB(PawnSet.BLUE, new ImageView(new Image("file:Crab.jpg"))), 
		CRANE(PawnSet.BLUE, new ImageView(new Image("file:Crane.jpg"))), COBRA(PawnSet.RED, new ImageView(new Image("file:Cobra.jpg"))),
		DRAGON(PawnSet.RED, new ImageView(new Image("file:Dragon.jpg"))), EEL(PawnSet.BLUE, new ImageView(new Image("file:Eel.jpg"))), 
		ELEPHANT(PawnSet.RED, new ImageView(new Image("file:Elephant.jpg"))), FROG(PawnSet.RED, new ImageView(new Image("file:Frog.jpg"))),
		GOOSE(PawnSet.BLUE, new ImageView(new Image("file:Goose.jpg"))), HORSE(PawnSet.RED, new ImageView(new Image("file:Horse.jpg"))), 
		MANTIS(PawnSet.RED, new ImageView(new Image("file:Mantis.jpg"))), MONKEY(PawnSet.BLUE, new ImageView(new Image("file:Monkey.jpg"))),
		OX(PawnSet.BLUE, new ImageView(new Image("file:Ox.jpg"))), RABBIT(PawnSet.BLUE, new ImageView(new Image("file:Rabbit.jpg"))), 
		ROOSTER(PawnSet.RED, new ImageView(new Image("file:Rooster.jpg"))), TIGER(PawnSet.BLUE, new ImageView(new Image("file:Tiger.jpg")));
		
		final PawnSet set;
		final ImageView image;
		CardType(PawnSet set, ImageView image) {
			this.set = set;
			this.image = image;
		}
		
		public PawnSet getSet() {
			return set;
		}
		
		public ImageView getImage() {
			return image;
		}

	}
	
	public Card(CardType type) { //public Card(CardType type, boolean orientation) ? to flip orientation changing turns
		this.type = type;
		setWidth(Constants.TILE_SIZE * 2);
		setHeight(Constants.TILE_SIZE);
		Rectangle cardFace = new Rectangle(Constants.TILE_SIZE * 2, Constants.TILE_SIZE);
		cardFace.setStroke(Color.BLACK);
		cardFace.setFill(type.getSet() == PawnSet.BLUE ? Color.SKYBLUE : Color.LIGHTPINK);
		Label label = new Label(type.name());
		label.setPadding(new Insets(5, 5, 5, 5));
		setAlignment(label, Pos.CENTER_LEFT);
		ImageView image = type.getImage();
		image.setFitWidth(Constants.TILE_SIZE - 1);
		image.setFitHeight(Constants.TILE_SIZE - 1);
		setAlignment(image, Pos.CENTER_RIGHT);
		setMargin(image, new Insets(0, 1, 0, 0));
		getChildren().addAll(cardFace, label, image);
	}
	
	public CardType getCardType() {
		return type;
	}
	
}
