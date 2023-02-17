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
    Player whitePlayer = new Player("White", true);
    Player blackPlayer = new Player("Black", false);

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
                squares[i][j] = new Square(j, i);
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

    public void addPiece(Piece piece, int y, int x) {
        squares[y][x].piece = piece;
        squares[y][x].add(piece);
        piece.boardX = x;
        piece.boardY = y;
        if (piece.isWhite) {
            whitePlayer.addPiece(piece);
            piece.myPlayer = whitePlayer;
            piece.opponentPlayer = blackPlayer;
        } else {
            blackPlayer.addPiece(piece);
            piece.myPlayer = blackPlayer;
            piece.opponentPlayer = whitePlayer;
        }
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
        printBoard();
        System.out.println();
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

        boolean opponentsColor = !lastClickedPiece.isWhite;
        Player opponent = opponentsColor ? whitePlayer : blackPlayer;
        Player currentPlayer = lastClickedPiece.isWhite ? whitePlayer : blackPlayer;

        lastClickedPiece.setVisible(false);
        layeredPane.remove(lastClickedPiece);
        lastClickedPiece.setVisible(true);

        int xMax = layeredPane.getWidth() - lastClickedPiece.getWidth();
        int x = Math.min(e.getX(), xMax);
        x = Math.max(x, 0);

        int yMax = layeredPane.getHeight() - lastClickedPiece.getHeight();
        int y = Math.min(e.getY(), yMax);
        y = Math.max(y, 0);

        if (!checkWhereTheMouseWasReleased(this.findComponentAt(x, y), currentPlayer, opponent)) {
            return;
        }

        if (opponent.noLegalMovesLeft(currentPlayer)) {
            if (opponent.isCheck(currentPlayer)) {
                JOptionPane.showMessageDialog(null, "Checkmate! " + (opponentsColor ? "Black" : "White") + " wins!");
            } else {
                JOptionPane.showMessageDialog(null, "Stalemate!");
            }
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

    void moveThePieceToTheNewSquare(Component c) {
        Container parent;
        if (c instanceof JLabel) {
            parent = c.getParent();
            Piece oldPiece = (Piece) parent.getComponent(0);
            if (lastClickedPiece.isWhite) {
                blackPlayer.removePiece(oldPiece);
            } else {
                whitePlayer.removePiece(oldPiece);
            }
            parent.remove(0);
        } else {
            parent = (Container) c;
            lastClickedPiece.move((Square) c);
        }
        ((Square)parent).piece = lastClickedPiece;
        lastClickedPiece.neverMoved = false;
        lastClickedPiece.boardX = parent.getX() / 95;
        lastClickedPiece.boardY = parent.getY() / 95;
        parent.add(lastClickedPiece);
        parent.validate();
    }

    void putThePieceBack() {
        lastClickedSquare.add(lastClickedPiece);
        lastClickedSquare.piece = lastClickedPiece;
        lastClickedSquare.validate();
    }

    public boolean checkWhereTheMouseWasReleased(Component c, Player currentPlayer, Player opponent) {
        if (c instanceof JLabel) {
            if (!lastClickedPiece.canCapture((Square) c.getParent())
            || currentPlayer.wouldThisMoveCauseCheck(lastClickedPiece, (Square) c.getParent(), opponent)) {
                System.out.println("Can't capture");
                putThePieceBack();
                return false;
            }
            moveThePieceToTheNewSquare(c);
        } else {
            if (lastClickedPiece.canMove((Square) c) == false
            || currentPlayer.wouldThisMoveCauseCheck(lastClickedPiece, (Square) c, opponent)) {
                System.out.println("Can't move");
                putThePieceBack();
                return false;
            }
            moveThePieceToTheNewSquare(c);
        }

        return true;
    }

    public void piecesForNormalGame() {
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

    void testBoard() {
        PieceFactory pieceFactory = PieceFactory.getInstance();
        // add two kings of opposite color, a white rook and a white pawn, all on their starting squares
        addPiece(pieceFactory.createPiece("King", true), 7, 4);
        addPiece(pieceFactory.createPiece("King", false), 0, 4);
        addPiece(pieceFactory.createPiece("Rook", true), 7, 0);
        addPiece(pieceFactory.createPiece("Pawn", true), 6, 0);
    }

    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].piece != null) {
                    switch (squares[i][j].piece.getClass().getSimpleName()) {
                        case "WhitePawn":
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
}
