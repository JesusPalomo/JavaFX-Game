package game;

public class MoveResult {
	private MoveType type;
	private Pawn pawn;
	
	public enum MoveType {
		ILLEGAL, LEGAL, CAPTURE;
	}
	
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
