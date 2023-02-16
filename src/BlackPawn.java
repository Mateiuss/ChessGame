import javax.swing.*;
import java.awt.*;

public class BlackPawn extends Piece {
    boolean justMovedTwo = false;
    BlackPawn() {
        icon = blackPawn;
        this.setIcon(icon);
        isWhite = false;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (newMatrixPoint.getY() == oldMatrixPoint.getY() + 1 && Math.abs(newMatrixPoint.getX() - oldMatrixPoint.getX()) == 1) {
            int y = (int) oldMatrixPoint.getY();
            int x = (int) newMatrixPoint.getX();

            Board board = Board.getInstance();

            if (board.squares[y][x].piece != null && board.squares[y][x].piece.isWhite != this.isWhite && board.squares[y][x].piece instanceof WhitePawn && ((WhitePawn) board.squares[y][x].piece).justMovedTwo) {
                return true;
            }
            return false;
        }

        if (oldMatrixPoint.getX() != newMatrixPoint.getX()) {
            return false;
        }

        if (newMatrixPoint.getY() - oldMatrixPoint.getY() == 1) {
            justMovedTwo = false;
            return true;
        }

        if (newMatrixPoint.getY() - oldMatrixPoint.getY() == 2 && neverMoved) {
            justMovedTwo = true;
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
            justMovedTwo = false;
            return true;
        }

        return false;
    }

    public void move(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (newMatrixPoint.getY() == oldMatrixPoint.getY() + 1 && Math.abs(newMatrixPoint.getX() - oldMatrixPoint.getX()) == 1) {
            int y = (int) oldMatrixPoint.getY();
            int x = (int) newMatrixPoint.getX();

            Board board = Board.getInstance();

            if (board.squares[y][x].piece != null && board.squares[y][x].piece.isWhite != this.isWhite && board.squares[y][x].piece instanceof WhitePawn && ((WhitePawn) board.squares[y][x].piece).justMovedTwo) {
                board.squares[y][x].piece = null;
                board.squares[y][x].removeAll();
                board.squares[y][x].repaint();
            }
        }
    }
}
