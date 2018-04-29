package game;

import game.Pawn.PawnSet;
import javafx.scene.layout.BorderPane;

public class Card extends BorderPane {
	
	public enum CardType {
		BOAR(PawnSet.RED), CRAB(PawnSet.BLUE), CRANE(PawnSet.BLUE), COBRA(PawnSet.RED),
		DRAGON(PawnSet.RED), EEL(PawnSet.BLUE), ELEPHANT(PawnSet.RED), FROG(PawnSet.RED),
		GOOSE(PawnSet.BLUE), HORSE(PawnSet.RED), MANTIS(PawnSet.RED), MONKEY(PawnSet.BLUE),
		OX(PawnSet.BLUE), RABBIT(PawnSet.BLUE), ROOSTER(PawnSet.RED), TIGER(PawnSet.BLUE);
		
		final PawnSet set;
		CardType(PawnSet set) {
			this.set = set;
		}
	}
	
	public Card(CardType type) {
		
	}
	
	public enum MoveType {
		NONE, NORMAL, KILL;
	}
	
	public class MoveResult {
		private MoveType type;
		private Pawn pawn;
		
		public MoveResult(MoveType type, Pawn pawn) {
			this.type = type;
			this.pawn = pawn;
		}
		
		public MoveResult(MoveType type) {
			this(type, null);
		}
		
		public MoveType getType() {
			return type;
		}
		
		public Pawn getPawn() {
			return pawn;
		}
		
	}
	
}
