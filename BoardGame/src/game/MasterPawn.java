package game;

import game.MainMenu.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MasterPawn extends Pawn {
	
	public MasterPawn(PawnSet group, int x, int y) {
		super(group, x, y);
		
		Circle outline = new Circle(25);
		outline.setFill(Color.TRANSPARENT);
		outline.setStroke(Color.GOLD);
		outline.setStrokeWidth(3);
		outline.setTranslateX((Constants.TILE_SIZE - 69) / 2);
		outline.setTranslateY((Constants.TILE_SIZE - 69) / 2);
		
		Text m = new Text("M");
		m.setScaleX(4.3);
		m.setScaleY(4.5);
		m.setFill(Color.GOLD);
		m.setTranslateX((Constants.TILE_SIZE - 69) / 2);
		m.setTranslateY((Constants.TILE_SIZE - 77) / 2);
		getChildren().addAll(outline, m);
	}
	
}
