import javax.swing.*;
import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite) {
        if (isWhite) {
            icon = whiteKing;
        } else {
            icon = blackKing;
        }
        this.setIcon(icon);
    }
}
