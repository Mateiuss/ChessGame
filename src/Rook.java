public class Rook extends Piece {
    public Rook(boolean isWhite) {
        if (isWhite) {
            icon = whiteRook;
        } else {
            icon = blackRook;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    public Rook(int x, int y) {
        this.boardX = x;
        this.boardY = y;
    }

    public boolean canMove(Square newSquare) {
        if (newSquare.getBoardX() == this.boardX && newSquare.getBoardY() == this.boardY) {
            return false;
        }

        if (newSquare.getBoardX() != this.boardX && newSquare.getBoardY() != this.boardY) {
            return false;
        }

        Board board = Board.getInstance();

        int xIsGrowing = this.boardX < newSquare.getBoardX() ? 1 : this.boardX == newSquare.getBoardX() ? 0 : -1;
        int yIsGrowing = this.boardY < newSquare.getBoardY() ? 1 : this.boardY == newSquare.getBoardY() ? 0 : -1;

        int x = this.boardX + xIsGrowing;
        int y = this.boardY + yIsGrowing;

        while (x != newSquare.getBoardX() || y != newSquare.getBoardY()) {
            if (board.squares[y][x].piece != null) {
                return false;
            }
            x += xIsGrowing;
            y += yIsGrowing;
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
