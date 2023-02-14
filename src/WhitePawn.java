import java.awt.*;

public class WhitePawn extends Piece {
    WhitePawn() {
        icon = whitePawn;
        this.setIcon(icon);
        isWhite = true;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {;
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() != newMatrixPoint.getX()) {
            return false;
        }

        if (oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            return true;
        }
        if (oldMatrixPoint.getY() - newMatrixPoint.getY() == 2 && neverMoved) {
            return true;
        }
        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() - newMatrixPoint.getX() == 1 && oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            return true;
        }
        if (oldMatrixPoint.getX() - newMatrixPoint.getX() == -1 && oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            return true;
        }
        return false;
    }
}
