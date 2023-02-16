import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final String name;
    private final boolean isWhite;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private Piece king;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
        if (piece instanceof King) {
            king = piece;
        }
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public boolean isCheck(Player opponent) {
        for (Piece opponentPiece: opponent.pieces) {
            if (opponentPiece.canCapture((Square) king.getParent())) {
                System.out.println(opponentPiece.getClass().getSimpleName() + " at " + opponentPiece.boardX + " " + opponentPiece.boardY + " can capture " + king.getClass().getSimpleName());
                return true;
            }
        }

        return false;
    }

    public boolean wouldThisMoveCauseCheck(Piece piece, Square newSquare, Player opponent) {
        int oldX = piece.boardX;
        int oldY = piece.boardY;
        Piece oldPiece = newSquare.piece;
        newSquare.piece = piece;
        if (oldPiece != null) {
            opponent.removePiece(oldPiece);
        }

        piece.boardX = newSquare.getBoardX();
        piece.boardY = newSquare.getBoardY();

        boolean wouldCauseCheck = isCheck(opponent);

        if (oldPiece != null) {
            opponent.addPiece(oldPiece);
        }
        newSquare.piece = oldPiece;
        piece.boardX = oldX;
        piece.boardY = oldY;

        return wouldCauseCheck;
    }

    public boolean noLegalMovesLeft(Player opponent) {
        for (Piece piece: pieces) {
            for (Square[] squares: Board.getInstance().squares) {
                for (Square square: squares) {
                    if (piece.canMove(square) && !wouldThisMoveCauseCheck(piece, square, opponent)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public Piece getKing() {
        return king;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
