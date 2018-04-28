package game;
import javafx.scene.shape.Circle;
import static game.MainMenu.Constants;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class Pawn extends StackPane {
	
	private PawnGroup group;
	private double mouseX, mouseY, oldX, oldY;
	
	public Pawn(PawnGroup group, int x, int y) {
		this.group = group;
		move(x,y);
		
		Circle circle = new Circle(34.5);
		circle.setFill(group == PawnGroup.BLUE ? Color.BLUE : Color.RED);
		circle.setStroke(Color.BLACK);
		circle.setTranslateX((Constants.TILE_SIZE - 69) / 2);
		circle.setTranslateY((Constants.TILE_SIZE - 69) / 2);
		
		getChildren().add(circle);
		
		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		
		setOnMouseDragged(e -> {
			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldX);
		});
	}
	
	public enum PawnGroup {
		RED, BLUE;
	}
	
	public PawnGroup getGroup() {
		return group;
	}
	
	public double getOldX() {
		return oldX;
	}
	
	public double getOldY() {
		return oldY;
	}
	
	public void move(int x, int y) {
		oldX = x * Constants.TILE_SIZE;
		oldY = y * Constants.TILE_SIZE;
		relocate(oldX, oldY);
	}
	
	public void dontMove() {
		relocate(oldX, oldY);
	}
	
	public boolean isCaptured() {
		return false;
	}
	
}
