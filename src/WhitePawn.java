import java.awt.*;

public class WhitePawn extends Piece {
    boolean justMovedTwo = false;
    WhitePawn() {
        icon = whitePawn;
        this.setIcon(icon);
        isWhite = true;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {;
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getY() == newMatrixPoint.getY() + 1 && Math.abs(oldMatrixPoint.getX() - newMatrixPoint.getX()) == 1) {
            int y = (int) oldMatrixPoint.getY();
            int x = (int) newMatrixPoint.getX();

            Board board = Board.getInstance();

            if (board.squares[y][x].piece != null && board.squares[y][x].piece.isWhite != this.isWhite && board.squares[y][x].piece instanceof BlackPawn && ((BlackPawn) board.squares[y][x].piece).justMovedTwo) {
                return true;
            }
            return false;
        }

        if (oldMatrixPoint.getX() != newMatrixPoint.getX()) {
            return false;
        }

        if (oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            justMovedTwo = false;
            return true;
        }
        if (oldMatrixPoint.getY() - newMatrixPoint.getY() == 2 && neverMoved) {
            justMovedTwo = true;
            return true;
        }
        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getX() - newMatrixPoint.getX() == 1 && oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            justMovedTwo = false;
            return true;
        }
        if (oldMatrixPoint.getX() - newMatrixPoint.getX() == -1 && oldMatrixPoint.getY() - newMatrixPoint.getY() == 1) {
            justMovedTwo = false;
            return true;
        }
        return false;
    }

    public void move(Point oldPoint, Point newPoint) {
        Point oldMatrixPoint = getMatrixPoint(oldPoint);
        Point newMatrixPoint = getMatrixPoint(newPoint);

        if (oldMatrixPoint.getY() == newMatrixPoint.getY() + 1 && Math.abs(oldMatrixPoint.getX() - newMatrixPoint.getX()) == 1) {
            int y = (int) oldMatrixPoint.getY();
            int x = (int) newMatrixPoint.getX();

            Board board = Board.getInstance();

            if (board.squares[y][x].piece != null && board.squares[y][x].piece.isWhite != this.isWhite && board.squares[y][x].piece instanceof BlackPawn && ((BlackPawn) board.squares[y][x].piece).justMovedTwo) {
                board.squares[y][x].piece = null;
                board.squares[y][x].removeAll();
                board.squares[y][x].repaint();
            }
        }
    }
}
