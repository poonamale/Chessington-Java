package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        ArrayList<Move> pawnMoves = new ArrayList<>();
       if (this.getColour() == PlayerColour.WHITE) {

           int startingRow = 6;
           int firstMoveDistance = -2;
           int normalMoveDistance = -1;

           calculateMove(from, pawnMoves, startingRow, firstMoveDistance, normalMoveDistance);
           cannotFallOffBoard(pawnMoves);
           occupiedSquareBlocked(from, board, pawnMoves, normalMoveDistance);

       }
       else {

           int startingRow = 1;
           int firstMoveDistance = 2;
           int normalMoveDistance = 1;

           calculateMove(from, pawnMoves, 1, 2, 1);
           cannotFallOffBoard(pawnMoves);
           occupiedSquareBlocked(from, board, pawnMoves, normalMoveDistance);

       }
        return pawnMoves;
    }

    private void cannotFallOffBoard(ArrayList<Move> pawnMoves) {
        List<Move> invalidPawnMoves = pawnMoves.stream()
                .filter(move -> {
                    return move.getFrom().getRow() == 0 | move.getFrom().getRow() == 7  ;
                })
                .collect(Collectors.toList());

        pawnMoves.removeAll(invalidPawnMoves);
    }

    private void occupiedSquareBlocked(Coordinates from, Board board, ArrayList<Move> pawnMoves, int normalMoveDistance) {
        if (from.getRow() > 0 && from.getRow() < 7 ) {
            if (board.get(from.plus(normalMoveDistance, 0)) != null) {
                pawnMoves.clear();
            }
        }
    }

    private void calculateMove(Coordinates from, ArrayList<Move> pawnMoves, int startingRow, int firstMoveDistance, int normalMoveDistance) {
        if (from.getRow() == startingRow) {

            Coordinates to = from.plus(firstMoveDistance, 0);
            Move pawnMove = new Move(from, to);
            pawnMoves.add(pawnMove);
        }


        Coordinates to = from.plus(normalMoveDistance, 0);
        Move pawnMove = new Move(from, to);
        pawnMoves.add(pawnMove);
    }
}
