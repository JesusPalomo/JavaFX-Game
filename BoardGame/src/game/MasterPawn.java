package game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MasterPawn extends Pawn {
	
	public MasterPawn(PawnGroup group, int x, int y) {
		super(group, x, y);
		
		Text master = new Text("M");
		master.resize(58, 58);
		master.setFill(Color.GOLD);
	}
	
}
