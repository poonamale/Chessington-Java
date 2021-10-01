package training.chessington.model.pieces;

import javafx.scene.input.PickResult;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {


        ArrayList<Move> pawnMoves = new ArrayList<>();
       if (this.getColour() == PlayerColour.WHITE) {

           if (from.getRow() == 6){

               Coordinates to = from.plus(-2, 0);
               Move pawnMove = new Move(from, to);
               pawnMoves.add(pawnMove);
           }


           Coordinates to = from.plus(-1, 0);
           Move pawnMove = new Move(from, to);
           pawnMoves.add(pawnMove);

           if (board.get(from.plus(-1,0)) != null) {
               pawnMoves.clear();
           }

       }



       else {


           if (from.getRow() == 1){

               Coordinates to = from.plus(2,0);
               Move pawnMove = new Move(from, to);
               pawnMoves.add(pawnMove);
           }

           Coordinates to = from.plus(1,0);
           Move pawnMove = new Move(from, to);
           pawnMoves.add(pawnMove);

           if (board.get(from.plus(1,0)) != null) {
               pawnMoves.clear();
           }


       }


        return pawnMoves;
    }
}
