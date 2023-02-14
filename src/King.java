import javax.swing.*;
import java.awt.*;

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

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        int dx = (int) newMatrixPoint.getX() - (int) oldMatrixPoint.getX();
        int dy = (int) newMatrixPoint.getY() - (int) oldMatrixPoint.getY();

        if (dx == 0 && dy == 0) {
            return false;
        }

        if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) {
            return true;
        }

        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return canMove(oldPoint, newPoint);
    }
}
