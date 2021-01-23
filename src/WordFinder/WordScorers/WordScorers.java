package WordFinder.WordScorers;

import java.util.function.Function;

public class WordScorers {
    public static int scoreWordDefault(String word) {
        int wordLength = word.length();

        if (wordLength < 4) {
            return 0;
        }
        if (wordLength == 4) {
            return 1;
        }
        if (wordLength == 5) {
            return 2;
        }
        if (wordLength == 6) {
            return 3;
        }
        if (wordLength == 7) {
            return 5;
        }
        return 11;
    }
}
