import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Piece extends JLabel{
    ImageIcon icon;
    static int curr_id = 0;
    int id;

    public Piece() {
        id = curr_id;
        curr_id++;
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        System.out.println("Clicked by " + id);
//        if (lastClicked)
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        System.out.println("Pressed by " + id);
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        System.out.println("Released by " + id);
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }

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
