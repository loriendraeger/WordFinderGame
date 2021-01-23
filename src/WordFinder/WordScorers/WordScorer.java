package WordFinder.WordScorers;

@FunctionalInterface
public interface WordScorer {
    /**
     * For a given word, calculate a score. A score of 0 means the word has no score.
     * @param word a word to score
     * @return the score for 'word'
     */
    int scoreWord(String word);
}
