import javax.swing.*;

public class App extends JFrame {
    public App() {
        Board board = Board.getInstance();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MyChess");
        this.add(board.layeredPane);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
