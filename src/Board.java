import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Square[][] squares = new Square[8][8];

    public Board() {
        this.setLayout(new GridLayout(8, 8));
        this.setSize(768, 768);
        this.setPreferredSize(new Dimension(768, 768));
        this.setMinimumSize(new Dimension(768, 768));

        for (int i = 0; i < 8; i++) {
            boolean startWhite = i % 2 == 0;
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square();
                this.add(squares[i][j]);
                if (startWhite) {
                    if (j % 2 == 0) {
                        squares[i][j].setBackground(new Color(0xc99479));
                    } else {
                        squares[i][j].setBackground(new Color(0x1a0b04));
                    }
                } else {
                    if (j % 2 == 0) {
                        squares[i][j].setBackground(new Color(0x1a0b04));
                    } else {
                        squares[i][j].setBackground(new Color(0xc99479));
                    }
                }
            }
        }

        this.setVisible(true);
    }
}
