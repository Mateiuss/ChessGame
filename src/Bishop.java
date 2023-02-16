public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        if (isWhite) {
            icon = whiteBishop;
        } else {
            icon = blackBishop;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    public Bishop(int boardX, int boardY) {
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public boolean canMove(Square newSquare) {
        if (newSquare.getBoardX() == this.boardX && newSquare.getBoardY() == this.boardY) {
            return false;
        }

        if (Math.abs(newSquare.getBoardX() - this.boardX) != Math.abs(newSquare.getBoardY() - this.boardY)) {
            return false;
        }

        Board board = Board.getInstance();

        int xGrowth = this.boardX < newSquare.getBoardX() ? 1 : -1;
        int yGrowth = this.boardY < newSquare.getBoardY() ? 1 : -1;

        int x = this.boardX + xGrowth;
        int y = this.boardY + yGrowth;

        while (x != newSquare.getBoardX() && y != newSquare.getBoardY()) {
            if (board.squares[y][x].piece != null) {
                return false;
            }
            x += xGrowth;
            y += yGrowth;
        }

        return true;
    }

    public boolean canCapture(Square newSquare) {
        if (newSquare.piece == null || newSquare.piece.isWhite == this.isWhite) {
            return false;
        }

        return canMove(newSquare);
    }
}
