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
                        squares[i][j].setBackground(new Color(0xb4b5b3));
                    } else {
                        squares[i][j].setBackground(new Color(0x355218));
                    }
                } else {
                    if (j % 2 == 0) {
                        squares[i][j].setBackground(new Color(0x355218));
                    } else {
                        squares[i][j].setBackground(new Color(0xb4b5b3));
                    }
                }
            }
        }

        PieceFactory pieceFactory = PieceFactory.getInstance();
        addPieces();

        this.setVisible(true);
    }

    public void addPiece(Piece piece, int x, int y) {
        squares[x][y].add(piece);
    }

    public void addPieces() {
        PieceFactory pieceFactory = PieceFactory.getInstance();
       // white should be down

        for (int i = 0; i < 8; i++) {
            addPiece(pieceFactory.createPiece("Pawn", true), 6, i);
            addPiece(pieceFactory.createPiece("Pawn", false), 1, i);
        }

        addPiece(pieceFactory.createPiece("Rook", true), 7, 0);
        addPiece(pieceFactory.createPiece("Rook", true), 7, 7);
        addPiece(pieceFactory.createPiece("Rook", false), 0, 0);
        addPiece(pieceFactory.createPiece("Rook", false), 0, 7);

        addPiece(pieceFactory.createPiece("Knight", true), 7, 1);
        addPiece(pieceFactory.createPiece("Knight", true), 7, 6);
        addPiece(pieceFactory.createPiece("Knight", false), 0, 1);
        addPiece(pieceFactory.createPiece("Knight", false), 0, 6);

        addPiece(pieceFactory.createPiece("Bishop", true), 7, 2);
        addPiece(pieceFactory.createPiece("Bishop", true), 7, 5);
        addPiece(pieceFactory.createPiece("Bishop", false), 0, 2);
        addPiece(pieceFactory.createPiece("Bishop", false), 0, 5);

        addPiece(pieceFactory.createPiece("Queen", true), 7, 3);
        addPiece(pieceFactory.createPiece("Queen", false), 0, 3);

        addPiece(pieceFactory.createPiece("King", true), 7, 4);
        addPiece(pieceFactory.createPiece("King", false), 0, 4);
    }
}
