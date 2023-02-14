import javax.swing.*;
import java.awt.*;

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

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() == newMatrixPoint.getX() && oldMatrixPoint.getY() == newMatrixPoint.getY()) {
            return false;
        }

        if (Math.abs((int)oldMatrixPoint.getX() - (int)newMatrixPoint.getX()) != Math.abs((int)oldMatrixPoint.getY() - (int)newMatrixPoint.getY())) {
            return false;
        }
        System.out.println("Bishop can move");

        Board board = Board.getInstance();

        int xIsGrowing = oldMatrixPoint.getX() < newMatrixPoint.getX() ? 1 : -1;
        int yIsGrowing = oldMatrixPoint.getY() < newMatrixPoint.getY() ? 1 : -1;

        System.out.println(xIsGrowing + " " + yIsGrowing);

        int x = (int) oldMatrixPoint.getX() + xIsGrowing;
        int y = (int) oldMatrixPoint.getY() + yIsGrowing;
        while (x != (int) newMatrixPoint.getX() && y != (int) newMatrixPoint.getY()) {
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
