import javax.swing.*;

public class App extends JFrame {
    public App() {
        Board board = new Board();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MyChess");
        this.add(board);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
