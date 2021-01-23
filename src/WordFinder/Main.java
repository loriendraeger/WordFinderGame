package WordFinder;

import WordFinder.WordFinderBoards.BasicPieceSets;
import WordFinder.WordFinderBoards.DoubleDiamondBoard;
import WordFinder.WordFinderBoards.RectangularWordFinderBoard;
import WordFinder.WordFinderBoards.WordFinderBoard;
import WordFinder.WordLists.TreeSetWordListChecker;
import WordFinder.WordLists.WordListChecker;
import WordFinder.WordScorers.WordScorers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String FILE_NAME = "./WordFinderList.txt";

    public static void main(String[] args) {

        List<String> wordList;
        try {
            wordList = Files.readAllLines(Paths.get(FILE_NAME));
        } catch (IOException e) {
            System.out.println("Could not read file.");
            e.printStackTrace();
            return;
        }

        WordListChecker wordCheck = new TreeSetWordListChecker(wordList);
        WordFinderBoard board = new RectangularWordFinderBoard(5, 5, BasicPieceSets.getRandomizedPieceSet());
        WordFinderPlayer player = new WordFinderPlayer(board, wordCheck, WordScorers::scoreWordDefault);

        System.out.println(board.showBoard());
        player.play();
        for (String word : player.getResults(false)) {
            System.out.println(word);
        }
        System.out.println("Total score: " + player.getScore());

        board = new DoubleDiamondBoard(BasicPieceSets.getRandomizedPieceSet());
        player = new WordFinderPlayer(board, wordCheck, WordScorers::scoreWordDefault);

        System.out.println(board.showBoard());
        player.play();
        for (String word : player.getResults(false)) {
            System.out.println(word);
        }
        System.out.println("Total score: " + player.getScore());

    }
}
