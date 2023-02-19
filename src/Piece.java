import javax.swing.*;
import java.awt.*;

public abstract class Piece extends JLabel{
    ImageIcon icon;
    boolean neverMoved = true;
    boolean isWhite;
    int boardX;
    int boardY;
    Player myPlayer;
    Player opponentPlayer;

    public boolean canMove(Square newSquare) {
        return false;
    }

    public boolean canCapture(Square newSquare) {
        return false;
    }

    public void move(Square newSquare) {
        newSquare.piece = this;
        this.neverMoved = false;
        this.boardX = newSquare.getBoardX();
        this.boardY = newSquare.getBoardY();
        newSquare.add(this);
        newSquare.validate();
    }
    public void capture(Square newSquare) {
        newSquare.piece = this;
        this.neverMoved = false;
        this.boardX = newSquare.getBoardX();
        this.boardY = newSquare.getBoardY();
        newSquare.add(this);
        newSquare.validate();
    }

    public void promote (Square newSquare) {
        myPlayer.removePiece(this);
        Piece newPiece = PieceFactory.getInstance().createPiece("Queen", this.isWhite);
        newSquare.piece = newPiece;
        newPiece.boardX = newSquare.getBoardX();
        newPiece.boardY = newSquare.getBoardY();
        newPiece.myPlayer = myPlayer;
        newPiece.opponentPlayer = opponentPlayer;
        newSquare.add(newSquare.piece);
        newSquare.validate();
        myPlayer.addPiece(newSquare.piece);
    }

    static ImageIcon whiteQueen = new ImageIcon(new ImageIcon("PieceIcons/WhiteQueen.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackQueen = new ImageIcon(new ImageIcon("PieceIcons/BlackQueen.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteKing = new ImageIcon(new ImageIcon("PieceIcons/WhiteKing.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackKing = new ImageIcon(new ImageIcon("PieceIcons/BlackKing.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteRook = new ImageIcon(new ImageIcon("PieceIcons/WhiteRook.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackRook = new ImageIcon(new ImageIcon("PieceIcons/BlackRook.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteBishop = new ImageIcon(new ImageIcon("PieceIcons/WhiteBishop.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackBishop = new ImageIcon(new ImageIcon("PieceIcons/BlackBishop.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whiteKnight = new ImageIcon(new ImageIcon("PieceIcons/WhiteKnight.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackKnight = new ImageIcon(new ImageIcon("PieceIcons/BlackKnight.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon whitePawn = new ImageIcon(new ImageIcon("PieceIcons/WhitePawn.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    static ImageIcon blackPawn = new ImageIcon(new ImageIcon("PieceIcons/BlackPawn.png").getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
}
