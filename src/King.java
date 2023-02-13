import javax.swing.*;
import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite) {
        if (isWhite) {
            icon = whiteKing;
        } else {
            icon = blackKing;
        }
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
