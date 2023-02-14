import javax.swing.*;
import java.awt.*;

public class BlackPawn extends Piece {
    BlackPawn() {
        icon = blackPawn;
        this.setIcon(icon);
        isWhite = false;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() != newMatrixPoint.getX()) {
            return false;
        }

        if (newMatrixPoint.getY() - oldMatrixPoint.getY() == 1) {
            return true;
        }

        if (newMatrixPoint.getY() - oldMatrixPoint.getY() == 2 && neverMoved) {
            return true;
        }

        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (newMatrixPoint.getY() != oldMatrixPoint.getY() + 1) {
            return false;
        }

        if (Math.abs(newMatrixPoint.getX() - oldMatrixPoint.getX()) == 1) {
            return true;
        }

        return false;
    }
}
