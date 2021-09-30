package training.chessington.model.pieces;

import javafx.scene.input.PickResult;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {


        ArrayList<Move> pawnMoves = new ArrayList<>();
       if (this.getColour() == PlayerColour.WHITE) {

           int toRow = from.getRow() - 1;
           int toColumn = from.getCol();

           Coordinates to = new Coordinates(toRow, toColumn);
           Move pawnMove = new Move(from, to);
           pawnMoves.add(pawnMove);
       }

       else {

           //Coordinates to = new Coordinates(from.plus(1,0));

           int toRow = from.getRow() + 1;
           int toColumn = from.getCol();

           Coordinates to = new Coordinates(toRow, toColumn);
           Move pawnMove = new Move(from, to);
           pawnMoves.add(pawnMove);


       }


        return pawnMoves;
    }
}
