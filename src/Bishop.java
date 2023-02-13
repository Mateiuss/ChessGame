import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        if (isWhite) {
            icon = whiteBishop;
        } else {
            icon = blackBishop;
        }
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
