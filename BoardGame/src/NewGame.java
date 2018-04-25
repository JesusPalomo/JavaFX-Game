import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

public class NewGame extends MainMenu {

	public NewGame() {
		BorderPane pane = new BorderPane();
		Label label = new Label("You went to the new game screen!");
		pane.setCenter(label);
		pane.getChildren().add(label);
//		Scene newGameScreen = new Scene(pane, 750, 750);
	}
	
}
