import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    Square() {
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setSize(96, 96);
    }
}
