public class Queen extends Piece {
    public Queen(boolean isWhite) {
        if (isWhite) {
            icon = whiteQueen;
        } else {
            icon = blackQueen;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    public boolean canMove(Square newSquare) {
        return new Bishop(this.boardX, this.boardY).canMove(newSquare) || new Rook(this.boardX, this.boardY).canMove(newSquare);
    }

    public boolean canCapture(Square newSquare) {
        if (newSquare.piece == null || newSquare.piece.isWhite == this.isWhite) {
            return false;
        }
        return canMove(newSquare);
    }
}
