package game;

public class Card {
	
	public enum WhichCard {
		BOAR, CRAB, CRANE, COBRA,
		DRAGON, EEL, ELEPHANT, FROG,
		GOOSE, HORSE, MANTIS, MONKEY,
		OX, RABBIT, ROOSTER, TIGER;
	}
	
	public Card() {
		
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
