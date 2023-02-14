import javax.swing.*;
import java.awt.*;

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

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() == newMatrixPoint.getX() && oldMatrixPoint.getY() == newMatrixPoint.getY()) {
            return false;
        }

        if (oldMatrixPoint.getX() != newMatrixPoint.getX() && oldMatrixPoint.getY() != newMatrixPoint.getY()) {
            return false;
        }

        Board board = Board.getInstance();

        int xIsGrowing;
        int yIsGrowing;

        if (oldMatrixPoint.getX() == newMatrixPoint.getX()) {
            xIsGrowing = 0;
            yIsGrowing = oldMatrixPoint.getY() < newMatrixPoint.getY() ? 1 : -1;
        } else {
            xIsGrowing = oldMatrixPoint.getX() < newMatrixPoint.getX() ? 1 : -1;
            yIsGrowing = 0;
        }

        int x = (int) oldMatrixPoint.getX() + xIsGrowing;
        int y = (int) oldMatrixPoint.getY() + yIsGrowing;

        while ((x != (int) newMatrixPoint.getX() && yIsGrowing == 0) || (y != (int) newMatrixPoint.getY() && xIsGrowing == 0)) {
            if (board.squares[y][x].piece != null) {
                return false;
            }
            x += xIsGrowing;
            y += yIsGrowing;
        }

        return true;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return canMove(oldPoint, newPoint);
    }
}
