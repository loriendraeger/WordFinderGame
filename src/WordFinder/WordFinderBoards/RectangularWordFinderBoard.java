package WordFinder.WordFinderBoards;

import java.util.*;
import java.util.function.Consumer;

public class RectangularWordFinderBoard implements WordFinderBoard {

    private final int rows;
    private final int columns;
    private final RectangularWordFinderPiece[][] board;

    public RectangularWordFinderBoard(int rows, int columns, List<String> pieceValues) {
        this.rows = rows;
        this.columns = columns;

        board = new RectangularWordFinderPiece[rows][columns];

        //fill in the board
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                int pieceValueIndex = (row * columns + column) % pieceValues.size();
                board[row][column] = new RectangularWordFinderPiece(row, column, pieceValues.get(pieceValueIndex), this);
            }
        }
    }

    @Override
    public String showBoard() {
        int maxLen = 0;

        for(RectangularWordFinderPiece[] row : board) {
            for(RectangularWordFinderPiece piece: row) {
                int pieceLen = piece.pieceValue.length();
                if (pieceLen > maxLen)
                {
                    maxLen = pieceLen;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(RectangularWordFinderPiece[] row : board) {
            for(RectangularWordFinderPiece piece: row) {
                sb.append(piece.pieceValue);
                sb.append(" ".repeat(Math.max(0, maxLen + 1 - piece.pieceValue.length())));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public Iterable<WordFinderPiece> getBoardPieces() {
        RectangularWordFinderBoard thisRef = this;
        return () -> new BoardPieceIterator(thisRef);
    }

    private RectangularWordFinderPiece getPiece(PiecePosition position) {
        return board[position.row][position.column];
    }

    private static class RectangularWordFinderPiece implements WordFinderPiece {
        private final PiecePosition key;
        private final String pieceValue;
        private final RectangularWordFinderBoard board;

        public RectangularWordFinderPiece(int row, int column, String pieceValue, RectangularWordFinderBoard board) {
            this.key = new PiecePosition();
            this.key.row = row;
            this.key.column = column;
            this.pieceValue = pieceValue;
            this.board = board;
        }

        @Override
        public String getPieceValue() {
            return pieceValue;
        }

        @Override
        public Iterable<WordFinderPiece> getAdjacentPieces() {
            return () -> new AdjacentPieceIterator(this.board, this);
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RectangularWordFinderPiece that = (RectangularWordFinderPiece) o;
            return key.equals(that.key);
        }

        public int hashCode() {
            return key.hashCode();
        }

    }

    protected static class PiecePosition {
        protected int row;
        protected int column;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PiecePosition that = (PiecePosition) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "PiecePosition{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }

    private static class AdjacentPieceIterator implements Iterator<WordFinderPiece> {
        /* Where X is the current piece, relative piece position represents these positions
        0 1 2
        3 X 4
        5 6 7
         */

        Integer relativePosition = -1;
        RectangularWordFinderBoard board;
        RectangularWordFinderPiece piece;

        public AdjacentPieceIterator(RectangularWordFinderBoard board, RectangularWordFinderPiece piece) {
            this.board = board;
            this.piece = piece;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return getNextValidRelativePosition(relativePosition) != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public WordFinderPiece next() {
            relativePosition = getNextValidRelativePosition(relativePosition);
            if (relativePosition == null)
            {
                throw new NoSuchElementException();
            }
            PiecePosition pp = getPiecePositionFromRelativePosition(relativePosition);
            if (pp == null)
            {
                throw new NoSuchElementException();
            }
            return board.getPiece(pp);
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Performs the given action for each remaining element until all elements
         * have been processed or the action throws an exception.  Actions are
         * performed in the order of iteration, if that order is specified.
         * Exceptions thrown by the action are relayed to the caller.
         * <p>
         * The behavior of an iterator is unspecified if the action modifies the
         * collection in any way (even by calling the {@link #remove remove} method
         * or other mutator methods of {@code Iterator} subtypes),
         * unless an overriding class has specified a concurrent modification policy.
         * <p>
         * Subsequent behavior of an iterator is unspecified if the action throws an
         * exception.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     while (hasNext())
         *         action.accept(next());
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEachRemaining(Consumer<? super WordFinderPiece> action) {
            while (hasNext()) {
                action.accept(next());
            }
        }

        private Integer getNextValidRelativePosition(int relativePosition) {
            relativePosition++;
            while (relativePosition < 8) {
                if (getPiecePositionFromRelativePosition(relativePosition) != null) {
                    return relativePosition;
                }
                relativePosition++;
            }
            return null;
        }

        private PiecePosition getPiecePositionFromRelativePosition(int relativePosition)
        {
            int rowOffset;
            int colOffset;
            switch (relativePosition) {
                case 0:
                    rowOffset = -1;
                    colOffset = -1;
                    break;
                case 1:
                    rowOffset = -1;
                    colOffset = 0;
                    break;
                case 2:
                    rowOffset = -1;
                    colOffset = 1;
                    break;
                case 3:
                    rowOffset = 0;
                    colOffset = -1;
                    break;
                case 4:
                    rowOffset = 0;
                    colOffset = 1;
                    break;
                case 5:
                    rowOffset = 1;
                    colOffset = -1;
                    break;
                case 6:
                    rowOffset = 1;
                    colOffset = 0;
                    break;
                case 7:
                    rowOffset = 1;
                    colOffset = 1;
                    break;
                default:
                    return null;
            }

            PiecePosition result = new PiecePosition();
            result.row = piece.key.row + rowOffset;
            result.column = piece.key.column + colOffset;

            if (result.row < 0 || result.row >= board.rows || result.column < 0 || result.column >= board.columns) {
                return null;
            }

            return result;
        }
    }

    private static class BoardPieceIterator implements Iterator<WordFinderPiece> {
        int row = 0;
        int column = -1;
        RectangularWordFinderBoard board;

        public BoardPieceIterator(RectangularWordFinderBoard board) {
            this.board = board;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return row + 1 < board.rows || column + 1 < board.columns;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public WordFinderPiece next() {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            column = column + 1;
            if (column >= board.columns) {  //go to the next row
                column = 0;
                row = row + 1;
            }
            return board.board[row][column];
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Performs the given action for each remaining element until all elements
         * have been processed or the action throws an exception.  Actions are
         * performed in the order of iteration, if that order is specified.
         * Exceptions thrown by the action are relayed to the caller.
         * <p>
         * The behavior of an iterator is unspecified if the action modifies the
         * collection in any way (even by calling the {@link #remove remove} method
         * or other mutator methods of {@code Iterator} subtypes),
         * unless an overriding class has specified a concurrent modification policy.
         * <p>
         * Subsequent behavior of an iterator is unspecified if the action throws an
         * exception.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     while (hasNext())
         *         action.accept(next());
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEachRemaining(Consumer<? super WordFinderPiece> action) {
            while (hasNext()) {
                action.accept(next());
            }
        }
    }

}
