public class King extends Piece {
    public King(boolean isWhite) {
        if (isWhite) {
            icon = whiteKing;
        } else {
            icon = blackKing;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    public boolean canMove(Square newSquare) {
        if (newSquare.getBoardX() == this.boardX && newSquare.getBoardY() == this.boardY) {
            return false;
        }

        if (Math.abs(newSquare.getBoardY() - this.boardY) > 1 || Math.abs(newSquare.getBoardX() - this.boardX) > 2) {
            return false;
        }

        if (Math.abs(newSquare.getBoardX() - this.boardX) == 2) {
            if (this.neverMoved == false) {
                return false;
            }

            Board board = Board.getInstance();

            int GrowthOfX = this.boardX < newSquare.getBoardX() ? 1 : -1;
            int x = this.boardX + GrowthOfX;
            int steps = 1;

            while (x != 0 && x != 7) {
                if (board.squares[this.boardY][x].piece != null) {
                    return false;
                }
                if (steps <= 2) {
                    if (this.myPlayer.wouldThisMoveCauseCheck(this, board.squares[this.boardY][x], this.opponentPlayer)) {
                        return false;
                    }
                }

                x += GrowthOfX;
                steps++;
            }

            Piece rook = board.squares[this.boardY][x].piece;

            if (rook == null || rook.neverMoved == false || rook.getClass() != Rook.class || rook.isWhite != this.isWhite) {
                return false;
            }
        }

        return true;
    }

    public boolean canCapture(Square newSquare) {
        if (newSquare.piece == null || newSquare.piece.isWhite == this.isWhite) {
            return false;
        }

        if (Math.abs(newSquare.getBoardY() - this.boardY) > 1 || Math.abs(newSquare.getBoardX() - this.boardX) > 1) {
            return false;
        }

        return true;
    }

    public void move(Square newSquare) {
        if (Math.abs(newSquare.getBoardX() - this.boardX) == 2) {
            Board board = Board.getInstance();

            int growthOfX = this.boardX < newSquare.getBoardX() ? 1 : -1;
            int x = this.boardX + growthOfX;

            while (x != 0 && x != 7) {
                x += growthOfX;
            }

            Piece rook = board.squares[this.boardY][x].piece;

            board.squares[this.boardY][x].piece = null;
            board.squares[this.boardY][x].remove(0);
            board.squares[this.boardY][x].repaint();
            board.squares[this.boardY][this.boardX + growthOfX].piece = rook;
            board.squares[this.boardY][this.boardX + growthOfX].add(rook);
            rook.boardX = this.boardX + growthOfX;
            board.squares[this.boardY][this.boardX + growthOfX].repaint();
        }

        super.move(newSquare);
    }
}
