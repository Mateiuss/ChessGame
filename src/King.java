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

        if (Math.abs(dx) == 2 && dy == 0 && neverMoved) {
            Board board = Board.getInstance();

            if (dx == 2) {
                if (isWhite) {
                    if (board.squares[7][7].piece != null && board.squares[7][7].piece instanceof Rook && board.squares[7][7].piece.neverMoved) {
                        for (int i = 5; i <= 6; i++) {
                            if (board.squares[7][i].piece != null) {
                                return false;
                            }
                        }
                        Piece rook = board.squares[7][7].piece;
                        board.squares[7][7].piece = null;
                        board.squares[7][7].remove(rook);
                        board.squares[7][7].repaint();
                        board.squares[7][5].piece = rook;
                        rook.neverMoved = false;
                        board.squares[7][5].add(rook);
                        board.squares[7][5].repaint();
                        return true;
                    }
                } else {
                    if (board.squares[0][7].piece != null && board.squares[0][7].piece instanceof Rook && board.squares[0][7].piece.neverMoved) {
                        for (int i = 5; i <= 6; i++) {
                            if (board.squares[0][i].piece != null) {
                                return false;
                            }
                        }
                        Piece rook = board.squares[0][7].piece;
                        board.squares[0][7].piece = null;
                        board.squares[0][7].remove(rook);
                        board.squares[0][7].repaint();
                        board.squares[0][5].piece = rook;
                        rook.neverMoved = false;
                        board.squares[0][5].add(rook);
                        board.squares[0][5].repaint();
                        return true;
                    }
                }
            } else {
                if (isWhite) {
                    if (board.squares[7][0].piece != null && board.squares[7][0].piece instanceof Rook && board.squares[7][0].piece.neverMoved) {
                        for (int i = 1; i <= 3; i++) {
                            if (board.squares[7][i].piece != null) {
                                return false;
                            }
                        }
                        Piece rook = board.squares[7][0].piece;
                        board.squares[7][0].piece = null;
                        board.squares[7][0].remove(rook);
                        board.squares[7][0].repaint();
                        board.squares[7][3].piece = rook;
                        rook.neverMoved = false;
                        board.squares[7][3].add(rook);
                        board.squares[7][3].repaint();
                        return true;
                    }
                } else {
                    if (board.squares[0][0].piece != null && board.squares[0][0].piece instanceof Rook && board.squares[0][0].piece.neverMoved) {
                        for (int i = 1; i <= 3; i++) {
                            if (board.squares[0][i].piece != null) {
                                return false;
                            }
                        }
                        Piece rook = board.squares[0][0].piece;
                        board.squares[0][0].piece = null;
                        board.squares[0][0].remove(rook);
                        board.squares[0][0].repaint();
                        board.squares[0][3].piece = rook;
                        rook.neverMoved = false;
                        board.squares[0][3].add(rook);
                        board.squares[0][3].repaint();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return canMove(oldPoint, newPoint);
    }
}
