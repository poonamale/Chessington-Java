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
           int diagonalRow = from.getRow() - 1;
           int leftColumn = from.getCol() - 1;
           int rightColumn = from.getCol() + 1;
           PlayerColour opposingPlayer = PlayerColour.BLACK;

           calculateMove(from, pawnMoves, startingRow, firstMoveDistance, normalMoveDistance);
           cannotFallOffBoard(pawnMoves);
           occupiedSquareBlocked(from, board, pawnMoves, normalMoveDistance);
           capturesDiagonally(from, board, pawnMoves, diagonalRow, leftColumn, rightColumn, opposingPlayer);

       }
       else {

           int startingRow = 1;
           int firstMoveDistance = 2;
           int normalMoveDistance = 1;
           int diagonalRow = from.getRow() + 1;
           int leftColumn = from.getCol() - 1;
           int rightColumn = from.getCol() + 1;
           PlayerColour opposingPlayer = PlayerColour.WHITE;

           calculateMove(from, pawnMoves, 1, 2, 1);
           cannotFallOffBoard(pawnMoves);
           occupiedSquareBlocked(from, board, pawnMoves, normalMoveDistance);
           capturesDiagonally(from, board, pawnMoves, diagonalRow, leftColumn, rightColumn, opposingPlayer);

       }
        return pawnMoves;
    }

    private void capturesDiagonally(Coordinates from, Board board, ArrayList<Move> pawnMoves, int diagonalRow, int leftColumn, int rightColumn, PlayerColour opposingPlayer) {

        if (from.getRow() > 0 && from.getRow() < 7 && from.getCol() <7 && from.getCol() > 0) {

            Coordinates leftDiagonal = new Coordinates(diagonalRow, leftColumn);
            Coordinates rightDiagonal = new Coordinates(diagonalRow, rightColumn);
            Move pawnMoveLeftDiagonal = new Move(from, leftDiagonal);
            Move pawnMoveRightDiagonal = new Move(from, rightDiagonal);


            if (board.get(leftDiagonal) != null) {
                if (board.get(leftDiagonal).getColour().equals(opposingPlayer)) {
                    pawnMoves.add(pawnMoveLeftDiagonal);
                }
            }

            if (board.get(rightDiagonal) != null) {
                if (board.get(rightDiagonal).getColour().equals(opposingPlayer)) {
                    pawnMoves.add(pawnMoveRightDiagonal);
                }
            }
        }
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
