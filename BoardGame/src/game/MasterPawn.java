package game;

import game.MainMenu.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MasterPawn extends Pawn {
	
	public MasterPawn(PawnSet group, int x, int y) {
		super(group, x, y);
		Circle outline = new Circle(25);
		outline.setFill(Color.TRANSPARENT);
		outline.setStroke(Color.GOLD);
		outline.setStrokeWidth(3);
		outline.setTranslateX((Constants.TILE_SIZE - 69) / 2);
		outline.setTranslateY((Constants.TILE_SIZE - 69) / 2);
		getChildren().add(outline);
	}
	
}
