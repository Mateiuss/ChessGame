import javax.swing.*;
import java.awt.*;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        if (isWhite) {
            icon = whiteRook;
        } else {
            icon = blackRook;
        }
        this.setIcon(icon);
    }
}
