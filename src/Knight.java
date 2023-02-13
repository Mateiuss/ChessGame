import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        if (isWhite) {
            icon = whiteKnight;
        } else {
            icon = blackKnight;
        }
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
