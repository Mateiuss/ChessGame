import javax.swing.*;
import java.awt.*;

public class BlackPawn extends Piece {
    BlackPawn() {
        icon = blackPawn;
        this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH)));
        this.addMouseListener(this);
    }
}
