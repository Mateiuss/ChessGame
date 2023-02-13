import javax.swing.*;
import java.awt.*;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        if (isWhite) {
            icon = whiteQueen;
        } else {
            icon = blackQueen;
        }
        this.setIcon(icon);
    }
}
