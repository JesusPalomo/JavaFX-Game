import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {
	
	public Tile(int x, int y) {
		setWidth(MainMenu.WIDTH);
		setHeight(MainMenu.HEIGHT);
		setFill(Color.BEIGE);
		relocate(x * MainMenu.TILE_SIZE, y * MainMenu.TILE_SIZE);
	}

}
