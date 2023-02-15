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

    private static Board instance = null;

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    private Board() {
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

        testBoard();

        this.setVisible(true);
    }

    public void addPiece(Piece piece, int x, int y) {
        squares[x][y].piece = piece;
        squares[x][y].add(piece);
    }

    void testBoard() {
        PieceFactory pieceFactory = PieceFactory.getInstance();
        // add two kings and two white rooks
        addPiece(pieceFactory.createPiece("King", true), 0, 4);
        addPiece(pieceFactory.createPiece("King", false), 7, 4);
        addPiece(pieceFactory.createPiece("Rook", true), 0, 0);
        addPiece(pieceFactory.createPiece("Rook", true), 0, 7);
    }

    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].piece != null) {
                    switch (squares[i][j].piece.getClass().getSimpleName()) {
                        case "WhitePawn":
                            System.out.print("P");
                            break;
                        case "BlackPawn":
                            System.out.print("P");
                            break;
                        case "Rook":
                            System.out.print("R");
                            break;
                        case "Knight":
                            System.out.print("N");
                            break;
                        case "Bishop":
                            System.out.print("B");
                            break;
                        case "Queen":
                            System.out.print("Q");
                            break;
                        case "King":
                            System.out.print("K");
                            break;
                    }
                    System.out.print("  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
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

    public boolean wouldTheMovePutMeInCheck(Square square) {
        Piece tempPiece = square.piece;
        square.piece = lastClickedPiece;

        Square myKingsSquare = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].piece != null && squares[i][j].piece.isWhite == lastClickedPiece.isWhite && squares[i][j].piece instanceof King) {
                    myKingsSquare = squares[i][j];
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].piece != null && squares[i][j].piece.isWhite != lastClickedPiece.isWhite) {
                    if (squares[i][j].piece.canCapture(squares[i][j].getLocation(), myKingsSquare.getLocation())) {
                        square.piece = tempPiece;
                        return true;
                    }
                }
            }
        }

        square.piece = tempPiece;
        return false;
    }

    public boolean verifyIfTheOpponentGotCheckmated (boolean isWhite) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                lastClickedSquare = squares[i][j];
                lastClickedPiece = squares[i][j].piece;
                lastClickedSquare.piece = null;
                if (lastClickedPiece != null && lastClickedPiece.isWhite == isWhite) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (squares[k][l].piece != null && squares[k][l].piece.isWhite == isWhite) {
                                continue;
                            }
                            if (lastClickedPiece.canMove(lastClickedSquare.getLocation(), squares[k][l].getLocation()) ||
                                lastClickedPiece.canCapture(lastClickedSquare.getLocation(), squares[k][l].getLocation())) {
                                if (!wouldTheMovePutMeInCheck(squares[k][l])) {
                                    lastClickedSquare.piece = lastClickedPiece;
                                    return false;
                                }
                            }
                        }
                    }
                }
                lastClickedSquare.piece = lastClickedPiece;
            }
        }

        return true;
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
        lastClickedSquare.piece = null;

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
            if (((Piece)c).isWhite == lastClickedPiece.isWhite || lastClickedPiece
                    .canCapture(lastClickedSquare.getLocation(), c.getParent().getLocation()) == false || wouldTheMovePutMeInCheck((Square)c.getParent())) {
                lastClickedSquare.add(lastClickedPiece);
                lastClickedSquare.piece = lastClickedPiece;
                lastClickedSquare.validate();
                return;
            }
            lastClickedPiece.neverMoved = false;
            Container parent = c.getParent();
            ((Square)parent).piece = lastClickedPiece;
            parent.remove(0);
            parent.add(lastClickedPiece);
            parent.validate();
        } else {
            if (lastClickedPiece.canMove(lastClickedSquare.getLocation(), c.getLocation()) == false || wouldTheMovePutMeInCheck((Square)c) == true) {
                lastClickedSquare.add(lastClickedPiece);
                lastClickedSquare.piece = lastClickedPiece;
                lastClickedSquare.validate();
                return;
            }
            lastClickedPiece.neverMoved = false;
            Container parent = (Container) c;
            ((Square)parent).piece = lastClickedPiece;
            parent.add(lastClickedPiece);
            parent.validate();
        }

        boolean oppnentsColor = !lastClickedPiece.isWhite;
        if (verifyIfTheOpponentGotCheckmated(oppnentsColor)) {
            JOptionPane.showMessageDialog(null, "Checkmate! " + (oppnentsColor ? "Black" : "White") + " wins!");
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
