import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        if (isWhite) {
            icon = whiteKnight;
        } else {
            icon = blackKnight;
        }
        this.setIcon(icon);
    }
}
