import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square extends JPanel{
    Piece piece;
    static Piece lastClickedPiece;
    Square() {
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setSize(96, 96);
    }
}
