public class BlackPawn extends Piece {
    BlackPawn() {
        icon = blackPawn;
        this.setIcon(icon);
        isWhite = false;
    }

    public boolean canMove(Square newSquare) {
        if (this.boardX != newSquare.getBoardX()) {
            return false;
        }

        if (newSquare.getBoardY() - this.boardY == 1) {
            return true;
        }

        if (newSquare.getBoardY() - this.boardY == 2 && neverMoved) {
            return true;
        }

        return false;
    }

    public boolean canCapture(Square newSquare) {
        if (newSquare.getBoardY() != this.boardY + 1) {
            return false;
        }

        if (Math.abs(newSquare.getBoardX() - this.boardX) == 1) {
            return true;
        }

        return false;
    }

    public void move(Square newSquare) {

    }
}
