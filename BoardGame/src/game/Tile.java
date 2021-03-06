package game;
import game.MainMenu.Constants;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {
	
	private Pawn pawn;
	
	public Tile(int x, int y) {
		setWidth(Constants.TILE_SIZE);
		setHeight(Constants.TILE_SIZE);
		setStroke(Color.BLACK);
		setFill(Color.IVORY);
		relocate(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
	}
	
	public boolean hasPawn() {
		return pawn != null;
	}
	
	public Pawn getPawn() {
		return pawn;
	}
	
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

}
