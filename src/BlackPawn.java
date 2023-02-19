public class BlackPawn extends Piece {
    BlackPawn() {
        icon = blackPawn;
        this.setIcon(icon);
        isWhite = false;
    }

    public boolean canMove(Square newSquare) {
        if (Math.abs(newSquare.getBoardX() - this.boardX) == 1 && newSquare.getBoardY() - this.boardY == 1) {
            Board board = Board.getInstance();
            Piece piece = board.squares[this.boardY][newSquare.getBoardX()].piece;

            if (piece != null && piece.myPlayer.getEnPassantPawn() == piece) {
                return true;
            }

            return false;
        }

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
        if (newSquare.piece == null || newSquare.piece.isWhite == this.isWhite) {
            return false;
        }

        if (newSquare.getBoardY() - this.boardY != 1) {
            return false;
        }

        if (Math.abs(newSquare.getBoardX() - this.boardX) == 1) {
            return true;
        }

        return false;
    }

    public void move(Square newSquare) {
        if (newSquare.getBoardY() - this.boardY == 2) {
            myPlayer.setEnPassantPawn(this);
        }

        if (Math.abs(newSquare.getBoardX() - this.boardX) == 1 && newSquare.getBoardY() - this.boardY == 1) {
            Board board = Board.getInstance();
            Piece piece = board.squares[this.boardY][newSquare.getBoardX()].piece;

            piece.myPlayer.removePiece(piece);
            board.squares[this.boardY][newSquare.getBoardX()].piece = null;
            board.squares[this.boardY][newSquare.getBoardX()].remove(0);
            board.squares[this.boardY][newSquare.getBoardX()].repaint();
        }

        if (newSquare.getBoardY() == 7) {
            promote(newSquare);
        } else {
            super.move(newSquare);
        }
    }

    public void capture(Square newSquare) {
        if (newSquare.getBoardY() == 7) {
            promote(newSquare);
        } else {
            super.capture(newSquare);
        }
    }
}
