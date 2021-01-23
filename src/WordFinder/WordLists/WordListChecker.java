package WordFinder.WordLists;

public interface WordListChecker {
    WordListCheckerResult isWordOnList(String word);

    class WordListCheckerResult
    {
        public boolean isWord;
        public boolean hasWordsBelow;

        @Override
        public String toString() {
            return "WordListCheckerResult{" +
                    "isWord=" + isWord +
                    ", hasWordsBelow=" + hasWordsBelow +
                    '}';
        }
    }
}
