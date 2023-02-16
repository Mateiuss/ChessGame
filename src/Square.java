import javax.swing.*;
import java.awt.*;

public class Square extends JPanel{
    Piece piece;
    private final int boardX;
    private final int boardY;
    Square(int boardX, int boardY) {
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setSize(96, 96);
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }
}
