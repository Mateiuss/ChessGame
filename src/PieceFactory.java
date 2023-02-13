public class PieceFactory {
    private static PieceFactory instance = null;

    private PieceFactory() {
    }

    public static PieceFactory getInstance() {
        if (instance == null) {
            instance = new PieceFactory();
        }
        return instance;
    }

    public static Piece createPiece(String pieceType, boolean isWhite) {
        switch (pieceType) {
            case "Queen":
                return new Queen(isWhite);
            case "King":
                return new King(isWhite);
            case "Bishop":
                return new Bishop(isWhite);
            case "Knight":
                return new Knight(isWhite);
            case "Rook":
                return new Rook(isWhite);
            case "Pawn":
                if (isWhite) {
                    return new WhitePawn();
                } else {
                    return new BlackPawn();
                }
            default:
                return null;
        }
    }
}
