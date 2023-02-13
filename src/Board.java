import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    Square[][] squares = new Square[8][8];
    JLayeredPane layeredPane = new JLayeredPane();
    Piece lastClickedPiece;
    Square lastClickedSquare;

    int xAdjustment;
    int yAdjustment;

    public Board() {
        layeredPane.setPreferredSize(new Dimension(768, 768));
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

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

        layeredPane.add(this, JLayeredPane.DEFAULT_LAYER);

        addPieces();

        this.setVisible(true);
    }

    public void addPiece(Piece piece, int x, int y) {
        squares[x][y].piece = piece;
        squares[x][y].add(piece);
    }

    void testBoard() {
        PieceFactory pieceFactory = PieceFactory.getInstance();
        addPiece(pieceFactory.createPiece("Queen", true), 0, 0);
        addPiece(pieceFactory.createPiece("Queen", false), 7, 7);
    }

    public void addPieces() {
        PieceFactory pieceFactory = PieceFactory.getInstance();

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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastClickedPiece == null) {
            return;
        }

        int xMax = layeredPane.getWidth() - lastClickedPiece.getWidth();
        int x = Math.min(Math.max(e.getX() + xAdjustment, 0), xMax);

        int yMax = layeredPane.getHeight() - lastClickedPiece.getHeight();
        int y = Math.min(Math.max(e.getY() + yAdjustment, 0), yMax);


        lastClickedPiece.setLocation(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastClickedPiece = null;

        Component c = this.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            return;
        }

        Point parentLocation = c.getParent().getLocation();

        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();

        lastClickedPiece = (Piece) c;
        lastClickedPiece.setLocation(parentLocation.x, parentLocation.y);

        lastClickedSquare = (Square) lastClickedPiece.getParent();

        layeredPane.add(lastClickedPiece, JLayeredPane.DRAG_LAYER);
        layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        layeredPane.setCursor(null);

        if (lastClickedPiece == null) {
            return;
        }

        lastClickedPiece.setVisible(false);
        layeredPane.remove(lastClickedPiece);
        lastClickedPiece.setVisible(true);

        int xMax = layeredPane.getWidth() - lastClickedPiece.getWidth();
        int x = Math.min(e.getX(), xMax);
        x = Math.max(x, 0);

        int yMax = layeredPane.getHeight() - lastClickedPiece.getHeight();
        int y = Math.min(e.getY(), yMax);
        y = Math.max(y, 0);

        Component c = this.findComponentAt(x, y);

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(lastClickedPiece);
            parent.validate();
        } else {
            Container parent = (Container) c;
            parent.add(lastClickedPiece);
            parent.validate();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
