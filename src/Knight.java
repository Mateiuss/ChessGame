import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        if (isWhite) {
            icon = whiteKnight;
        } else {
            icon = blackKnight;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    private int[] xMoves = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[] yMoves = {2, 1, -1, -2, -2, -1, 1, 2};

    public boolean canMove(Square newSquare) {
        int xDiff = (int) (newSquare.getBoardX() - this.boardX);
        int yDiff = (int) (newSquare.getBoardY() - this.boardY);

        for (int i = 0; i < xMoves.length; i++) {
            if (xDiff == xMoves[i] && yDiff == yMoves[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean canCapture(Square newSquare) {
        if (newSquare.piece == null || newSquare.piece.isWhite == this.isWhite) {
            return false;
        }
        return canMove(newSquare);
    }
}
