public class WhitePawn extends Piece {
    WhitePawn() {
        icon = whitePawn;
        this.setIcon(icon);
        isWhite = true;
    }

    public boolean canMove(Square newSquare) {;
        if (this.boardX != newSquare.getBoardX()) {
            return false;
        }

        if (this.boardY - newSquare.getBoardY() == 1) {
            return true;
        }
        if (this.boardY - newSquare.getBoardY() == 2 && neverMoved) {
            return true;
        }

        return false;
    }

    public boolean canCapture(Square newSquare) {
        if (this.boardX - newSquare.getBoardX() == 1 && this.boardY - newSquare.getY() == 1) {
            return true;
        }
        if (this.boardX - newSquare.getBoardX() == -1 && this.boardY - newSquare.getBoardX() == 1) {
            return true;
        }
        return false;
    }

    public void move(Square newSquare) {

    }
}
