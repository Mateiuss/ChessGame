import javax.swing.*;
import java.awt.*;

public abstract class Piece extends JLabel{
    ImageIcon icon;
    static int curr_id = 0;
    int id;
    boolean neverMoved = true;
    boolean isWhite;

    public Piece() {
        id = curr_id;
        curr_id++;
    }

    public boolean canMove(Point oldPoint, Point newPoint) {
        return false;
    }

    public boolean canCapture(Point oldPoint, Point newPoint) {
        return false;
    }

    public static Point getMatrixPoint(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        x = x / 96;
        y = y / 96;
        return new Point(x, y);
    }

    static ImageIcon whiteQueen = new ImageIcon(new ImageIcon("Pieces/WhiteQueen.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackQueen = new ImageIcon(new ImageIcon("Pieces/BlackQueen.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteKing = new ImageIcon(new ImageIcon("Pieces/WhiteKing.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackKing = new ImageIcon(new ImageIcon("Pieces/BlackKing.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteRook = new ImageIcon(new ImageIcon("Pieces/WhiteRook.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackRook = new ImageIcon(new ImageIcon("Pieces/BlackRook.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteBishop = new ImageIcon(new ImageIcon("Pieces/WhiteBishop.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackBishop = new ImageIcon(new ImageIcon("Pieces/BlackBishop.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteKnight = new ImageIcon(new ImageIcon("Pieces/WhiteKnight.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackKnight = new ImageIcon(new ImageIcon("Pieces/BlackKnight.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whitePawn = new ImageIcon(new ImageIcon("Pieces/WhitePawn.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackPawn = new ImageIcon(new ImageIcon("Pieces/BlackPawn.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
}
