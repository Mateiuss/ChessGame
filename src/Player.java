import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final String name;
    private final boolean isWhite;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private Piece king;
    private Piece enPassantPawn;

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
            if (opponentPiece.canCapture(getKingSquare())) {
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

        Component parent = piece.getParent();
        if (parent != null) {
            ((Square) parent).piece = null;
        }

        piece.boardX = newSquare.getBoardX();
        piece.boardY = newSquare.getBoardY();

        boolean wouldCauseCheck = isCheck(opponent);

        if (oldPiece != null) {
            opponent.addPiece(oldPiece);
        }
        newSquare.piece = oldPiece;

        if (parent != null) {
            ((Square) parent).piece = piece;
        }

        piece.boardX = oldX;
        piece.boardY = oldY;

        return wouldCauseCheck;
    }

    public boolean noLegalMovesLeft(Player opponent) {
        for (Piece piece: pieces) {
            for (Square[] squares: Board.getInstance().squares) {
                for (Square square: squares) {
                    if (((square.piece == null && piece.canMove(square)) || (square.piece != null && piece.canCapture(square))) && !wouldThisMoveCauseCheck(piece, square, opponent)) {
                        System.out.println(piece.getClass().getSimpleName() + " at " + piece.boardX + " " + piece.boardY + " can move to " + square.getBoardX() + " " + square.getBoardY());
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean checkForInsufficientMaterial() {
        if (pieces.size() == 1) {
            return true;
        }
        if (pieces.size() == 2) {
            if (pieces.get(0) instanceof King && pieces.get(1) instanceof Knight) {
                return true;
            }
            if (pieces.get(0) instanceof King && pieces.get(1) instanceof Bishop) {
                return true;
            }
            if (pieces.get(0) instanceof Knight && pieces.get(1) instanceof King) {
                return true;
            }
            if (pieces.get(0) instanceof Bishop && pieces.get(1) instanceof King) {
                return true;
            }
        }

        return false;
    }

    public void setEnPassantPawn(Piece enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
    }

    public Piece getEnPassantPawn() {
        return enPassantPawn;
    }

    public boolean isWhite() {
        return isWhite;
    }

    private Square getKingSquare() {
        Board board = Board.getInstance();
        return board.squares[king.boardY][king.boardX];
    }
}
