import javax.swing.*;
import java.awt.*;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        if (isWhite) {
            icon = whiteQueen;
        } else {
            icon = blackQueen;
        }
        this.setIcon(icon);
        this.isWhite = isWhite;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {
        return new Bishop(true).canMove(oldPoint, newPoint) || new Rook(false).canMove(oldPoint, newPoint);
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return canMove(oldPoint, newPoint);
    }
}
