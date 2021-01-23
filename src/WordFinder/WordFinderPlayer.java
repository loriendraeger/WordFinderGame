package WordFinder;

import WordFinder.WordFinderBoards.WordFinderBoard;
import WordFinder.WordFinderBoards.WordFinderPiece;
import WordFinder.WordLists.WordListChecker;
import WordFinder.WordScorers.WordScorer;

import java.util.*;

public class WordFinderPlayer {
    private WordFinderBoard board;
    private WordListChecker checker;
    private WordScorer scorer;
    private List<WordFinderResult> results;
    private int score;

    public WordFinderPlayer(WordFinderBoard board, WordListChecker checker, WordScorer scorer) {
        this.board = board;
        this.checker = checker;
        this.scorer = scorer;
        this.results = new ArrayList<>();
    }

    public void play() {
        for (WordFinderPiece piece : board.getBoardPieces()) {
            ArrayDeque<WordFinderPiece> pieceStack = new ArrayDeque<>();
            checkPiece(piece, pieceStack);
        }
    }

    public List<String> getResults(boolean includeZeroScore) {
        Set<String> resultCheck = new TreeSet<>();
        List<String> result = new ArrayList<>();

        for (WordFinderResult r : results)
        {
            if ((r.score > 0 || includeZeroScore) && resultCheck.add(r.word)) {
                result.add(r.word + " " + r.score);
            }
        }

        return result;
    }

    public int getScore() {
        return score;
    }

    private void checkPiece(WordFinderPiece piece, ArrayDeque<WordFinderPiece> pieceStack)
    {
        if (pieceStack.contains(piece))
        {
            return;  //not allowed to loop back on yourself
        }

        pieceStack.push(piece);

        StringBuilder currentWordBuilder = new StringBuilder();
        Iterator<WordFinderPiece> iterator = pieceStack.descendingIterator();
        while(iterator.hasNext()){
            currentWordBuilder.append(iterator.next().getPieceValue());
        }

        String word = currentWordBuilder.toString();
        WordListChecker.WordListCheckerResult result = checker.isWordOnList(word);

        if (result.isWord)
        {
            List<WordFinderPiece> pieces = new ArrayList<>();
            Iterator<WordFinderPiece> iterator2 = pieceStack.descendingIterator();
            while(iterator2.hasNext()){
                pieces.add(iterator2.next());
            }
            int pieceScore = scorer.scoreWord(word);
            results.add(new WordFinderResult(word, pieceScore, pieces));
            score = score + pieceScore;
        }

        if (result.hasWordsBelow)
        {
            for (WordFinderPiece adjacentPiece : piece.getAdjacentPieces()) {
                checkPiece(adjacentPiece, pieceStack);
            }
        }

        pieceStack.pop();
    }

    private class WordFinderResult {
        private String word;
        private int score;
        private List<WordFinderPiece> pieces;

        public WordFinderResult(String word, int score, List<WordFinderPiece> pieces) {
            this.word = word;
            this.score = score;
            this.pieces = pieces;
        }
    }
}
