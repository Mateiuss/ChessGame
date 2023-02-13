import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Piece extends JLabel implements MouseListener {
    static ImageIcon whiteQueen = new ImageIcon("Pieces/WhiteQueen.png");
    static ImageIcon blackQueen = new ImageIcon("Pieces/BlackQueen.png");
    static ImageIcon whiteKing = new ImageIcon("Pieces/WhiteKing.png");
    static ImageIcon blackKing = new ImageIcon("Pieces/BlackKing.png");
    static ImageIcon whiteRook = new ImageIcon("Pieces/WhiteRook.png");
    static ImageIcon blackRook = new ImageIcon("Pieces/BlackRook.png");
    static ImageIcon whiteBishop = new ImageIcon("Pieces/WhiteBishop.png");
    static ImageIcon blackBishop = new ImageIcon("Pieces/BlackBishop.png");
    static ImageIcon whiteKnight = new ImageIcon("Pieces/WhiteKnight.png");
    static ImageIcon blackKnight = new ImageIcon("Pieces/BlackKnight.png");
    static ImageIcon whitePawn = new ImageIcon("Pieces/WhitePawn.png");
    static ImageIcon blackPawn = new ImageIcon("Pieces/BlackPawn.png");
    ImageIcon icon;
    static int curr_id = 0;
    int id;

    public Piece() {
        id = curr_id;
        curr_id++;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked by " + id);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed by " + id);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released by " + id);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
