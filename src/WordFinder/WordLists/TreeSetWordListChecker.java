package WordFinder.WordLists;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetWordListChecker implements WordListChecker {
    private TreeSet<String> words;

    public TreeSetWordListChecker(Collection<String> words) {
        this.words = new TreeSet<>(words);
    }

    @Override
    public WordListCheckerResult isWordOnList(String word) {
        WordListCheckerResult result = new WordListCheckerResult();
        result.isWord = words.contains(word);

        String nextWord;
        if (result.isWord) {
            nextWord = words.higher(word);
        } else {
            nextWord = words.ceiling(word);
        }
        result.hasWordsBelow = nextWord != null && nextWord.startsWith(word);

        return result;
    }
}
