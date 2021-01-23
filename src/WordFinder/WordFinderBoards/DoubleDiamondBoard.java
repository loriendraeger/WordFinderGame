package WordFinder.WordFinderBoards;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a board in this shape
 *      0
 *    1   2
 *  3   4   5
 *    6   7
 *      8
 *    9   10
 *  11  12  13
 *    14  15
 *      16
 */
public class DoubleDiamondBoard implements WordFinderBoard {
    private static final int TOTAL_PIECES = 17;

    private final List<FigureEightPiece> pieces;

    public DoubleDiamondBoard(List<String> pieceValues) {
        pieces = new ArrayList<>();

        //first populate all the piece values
        for (int i = 0; i < TOTAL_PIECES; i++) {
            int pieceValueIndex = i % pieceValues.size();
            pieces.add(new FigureEightPiece(pieceValues.get(pieceValueIndex)));
        }

        FigureEightPiece piece;
        //now build the directed graph
        //0 -> 1, 2
        piece = pieces.get(0);
        piece.adjacentPieces.add(pieces.get(1));
        piece.adjacentPieces.add(pieces.get(2));
        //1 -> 0, 2, 3, 4
        piece = pieces.get(1);
        piece.adjacentPieces.add(pieces.get(0));
        piece.adjacentPieces.add(pieces.get(2));
        piece.adjacentPieces.add(pieces.get(3));
        piece.adjacentPieces.add(pieces.get(4));
        //2 -> 0, 1, 4, 5
        piece = pieces.get(2);
        piece.adjacentPieces.add(pieces.get(0));
        piece.adjacentPieces.add(pieces.get(1));
        piece.adjacentPieces.add(pieces.get(4));
        piece.adjacentPieces.add(pieces.get(5));
        //3 -> 1, 4, 6
        piece = pieces.get(3);
        piece.adjacentPieces.add(pieces.get(1));
        piece.adjacentPieces.add(pieces.get(4));
        piece.adjacentPieces.add(pieces.get(6));
        //4 -> 1, 2, 3, 5, 6, 7
        piece = pieces.get(4);
        piece.adjacentPieces.add(pieces.get(1));
        piece.adjacentPieces.add(pieces.get(2));
        piece.adjacentPieces.add(pieces.get(3));
        piece.adjacentPieces.add(pieces.get(5));
        piece.adjacentPieces.add(pieces.get(6));
        piece.adjacentPieces.add(pieces.get(7));
        //5 -> 2, 4, 7
        piece = pieces.get(5);
        piece.adjacentPieces.add(pieces.get(2));
        piece.adjacentPieces.add(pieces.get(4));
        piece.adjacentPieces.add(pieces.get(7));
        //6 -> 3, 4, 7, 8
        piece = pieces.get(6);
        piece.adjacentPieces.add(pieces.get(3));
        piece.adjacentPieces.add(pieces.get(4));
        piece.adjacentPieces.add(pieces.get(7));
        piece.adjacentPieces.add(pieces.get(8));
        //7 -> 4, 5, 6, 8
        piece = pieces.get(7);
        piece.adjacentPieces.add(pieces.get(4));
        piece.adjacentPieces.add(pieces.get(5));
        piece.adjacentPieces.add(pieces.get(6));
        piece.adjacentPieces.add(pieces.get(8));
        //8 -> 6, 7, 9, 10
        piece = pieces.get(8);
        piece.adjacentPieces.add(pieces.get(6));
        piece.adjacentPieces.add(pieces.get(7));
        piece.adjacentPieces.add(pieces.get(9));
        piece.adjacentPieces.add(pieces.get(10));
        //9 -> 8, 10, 11, 12
        piece = pieces.get(9);
        piece.adjacentPieces.add(pieces.get(8));
        piece.adjacentPieces.add(pieces.get(10));
        piece.adjacentPieces.add(pieces.get(11));
        piece.adjacentPieces.add(pieces.get(12));
        //10 -> 8, 9, 12, 13
        piece = pieces.get(10);
        piece.adjacentPieces.add(pieces.get(8));
        piece.adjacentPieces.add(pieces.get(9));
        piece.adjacentPieces.add(pieces.get(12));
        piece.adjacentPieces.add(pieces.get(13));
        //11 -> 9, 12, 14
        piece = pieces.get(11);
        piece.adjacentPieces.add(pieces.get(9));
        piece.adjacentPieces.add(pieces.get(12));
        piece.adjacentPieces.add(pieces.get(14));
        //12 -> 9, 10, 11, 13, 14, 15
        piece = pieces.get(12);
        piece.adjacentPieces.add(pieces.get(9));
        piece.adjacentPieces.add(pieces.get(10));
        piece.adjacentPieces.add(pieces.get(11));
        piece.adjacentPieces.add(pieces.get(13));
        piece.adjacentPieces.add(pieces.get(14));
        piece.adjacentPieces.add(pieces.get(15));
        //13 -> 10, 12, 15
        piece = pieces.get(13);
        piece.adjacentPieces.add(pieces.get(10));
        piece.adjacentPieces.add(pieces.get(12));
        piece.adjacentPieces.add(pieces.get(15));
        //14 -> 11, 12, 15, 16
        piece = pieces.get(14);
        piece.adjacentPieces.add(pieces.get(11));
        piece.adjacentPieces.add(pieces.get(12));
        piece.adjacentPieces.add(pieces.get(15));
        piece.adjacentPieces.add(pieces.get(16));
        //15 -> 12, 13, 14, 16
        piece = pieces.get(15);
        piece.adjacentPieces.add(pieces.get(12));
        piece.adjacentPieces.add(pieces.get(13));
        piece.adjacentPieces.add(pieces.get(14));
        piece.adjacentPieces.add(pieces.get(16));
        //16 -> 14, 15
        piece = pieces.get(16);
        piece.adjacentPieces.add(pieces.get(14));
        piece.adjacentPieces.add(pieces.get(15));
    }

    @Override
    public String showBoard() {
        int maxPieceLen = 0;
        for (FigureEightPiece p : pieces) {
            if (p.pieceValue.length() > maxPieceLen) {
                maxPieceLen = p.pieceValue.length();
            }
        }
        StringBuilder sb = new StringBuilder();
        addRowToBoardOutput(sb, maxPieceLen, 0);
        addRowToBoardOutput(sb, maxPieceLen, 1, 2);
        addRowToBoardOutput(sb, maxPieceLen, 3, 4, 5);
        addRowToBoardOutput(sb, maxPieceLen, 6, 7);
        addRowToBoardOutput(sb, maxPieceLen, 8);
        addRowToBoardOutput(sb, maxPieceLen, 9, 10);
        addRowToBoardOutput(sb, maxPieceLen, 11, 12, 13);
        addRowToBoardOutput(sb, maxPieceLen, 14, 15);
        addRowToBoardOutput(sb, maxPieceLen, 16);

        return sb.toString();
    }

    @Override
    public Iterable<WordFinderPiece> getBoardPieces() {
        return new ArrayList<>(pieces);
    }

    private void addRowToBoardOutput(StringBuilder sb, int maxPieceLen, int pieceIndex) {
        sb.append(" ".repeat(maxPieceLen + 1));
        sb.append(centerPad(pieces.get(pieceIndex).pieceValue, maxPieceLen));
        sb.append("\n");
    }

    private void addRowToBoardOutput(StringBuilder sb, int maxPieceLen, int pieceIndex, int pieceIndex2) {
        sb.append(" ".repeat(maxPieceLen / 2 + maxPieceLen % 2));
        sb.append(centerPad(pieces.get(pieceIndex).pieceValue, maxPieceLen));
        sb.append(" ").append(centerPad(pieces.get(pieceIndex2).pieceValue, maxPieceLen));
        sb.append("\n");
    }

    private void addRowToBoardOutput(StringBuilder sb, int maxPieceLen, int pieceIndex, int pieceIndex2, int pieceIndex3) {
        sb.append(centerPad(pieces.get(pieceIndex).pieceValue, maxPieceLen));
        sb.append(" ");
        sb.append(centerPad(pieces.get(pieceIndex2).pieceValue, maxPieceLen));
        sb.append(" ");
        sb.append(centerPad(pieces.get(pieceIndex3).pieceValue, maxPieceLen));
        sb.append("\n");
    }

    private static String centerPad(String word, int length)
    {
        if (word.length() > length) {
            return word.substring(0, length);
        }
        int charsNeeded = length - word.length();
        int left = charsNeeded / 2 + charsNeeded % 2;
        int right = charsNeeded / 2;
        return " ".repeat(left) + word + " ".repeat(right);
    }

    static class FigureEightPiece implements WordFinderPiece {
        private final String pieceValue;
        private final List<FigureEightPiece> adjacentPieces;

        public FigureEightPiece(String pieceValue) {
            this.pieceValue = pieceValue;
            adjacentPieces = new ArrayList<>();
        }

        @Override
        public String getPieceValue() {
            return pieceValue;
        }

        @Override
        public Iterable<WordFinderPiece> getAdjacentPieces() {
            return new ArrayList<>(adjacentPieces);
        }
    }
}
