package WordFinder.WordFinderBoards;

public interface WordFinderPiece {
    String getPieceValue();
    Iterable<WordFinderPiece> getAdjacentPieces();
}