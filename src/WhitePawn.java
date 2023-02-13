import javax.swing.*;
import java.awt.*;

public class WhitePawn extends Piece {
    WhitePawn() {
        icon = whitePawn;
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
