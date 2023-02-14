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

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        int xDiff = (int) (newMatrixPoint.getX() - oldMatrixPoint.getX());
        int yDiff = (int) (newMatrixPoint.getY() - oldMatrixPoint.getY());

        for (int i = 0; i < xMoves.length; i++) {
            if (xDiff == xMoves[i] && yDiff == yMoves[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return canMove(oldPoint, newPoint);
    }
}
