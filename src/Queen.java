import javax.swing.*;
import java.awt.*;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        if (isWhite) {
            icon = whiteQueen;
        } else {
            icon = blackQueen;
        }
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
