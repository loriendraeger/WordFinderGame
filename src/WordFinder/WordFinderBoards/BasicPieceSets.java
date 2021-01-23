package WordFinder.WordFinderBoards;

import java.util.*;

public class BasicPieceSets {

    public static List<String> getFixedPieceSet() {
        return Arrays.asList(new String[]{"U", "S", "A", "O", "P",
                                          "H", "B", "I", "D", "N",
                                          "U", "E", "H", "A", "E",
                                          "T", "L", "G", "L", "T",
                                          "T", "L", "A", "E", "E"});
    }

    public static List<String> getRandomizedPieceSet() {
        List<List<String>> pieceList = new ArrayList<>();
        pieceList.add(Arrays.asList(new String[]{"ER", "TH", "AN", "QU", "IN", "HE"}));
        pieceList.add(Arrays.asList(new String[]{"U", "M", "G", "E", "E", "A"}));
        pieceList.add(Arrays.asList(new String[]{"E", "E", "E", "E", "A", "M"}));
        pieceList.add(Arrays.asList(new String[]{"R", "F", "I", "P", "S", "Y"}));
        pieceList.add(Arrays.asList(new String[]{"H", "L", "O", "N", "D", "H"}));
        pieceList.add(Arrays.asList(new String[]{"N", "N", "A", "M", "E", "G"}));
        pieceList.add(Arrays.asList(new String[]{"N", "E", "T", "C", "C", "S"}));
        pieceList.add(Arrays.asList(new String[]{"K", "L", "I", "QU", "W", "U"}));
        pieceList.add(Arrays.asList(new String[]{"A", "I", "R", "A", "S", "F"}));
        pieceList.add(Arrays.asList(new String[]{"M", "O", "E", "T", "T", "T"}));
        pieceList.add(Arrays.asList(new String[]{"I", "I", "E", "L", "T", "C"}));
        pieceList.add(Arrays.asList(new String[]{"V", "O", "G", "R", "R", "W"}));
        pieceList.add(Arrays.asList(new String[]{"U", "N", "E", "S", "S", "S"}));
        pieceList.add(Arrays.asList(new String[]{"T", "I", "P", "S", "E", "C"}));
        pieceList.add(Arrays.asList(new String[]{"D", "E", "A", "N", "N", "N"}));
        pieceList.add(Arrays.asList(new String[]{"QU", "X", "J", "Z", "K", "B"}));
        pieceList.add(Arrays.asList(new String[]{"R", "F", "S", "A", "A", "A"}));
        pieceList.add(Arrays.asList(new String[]{"N", "D", "H", "T", "O", "D"}));
        pieceList.add(Arrays.asList(new String[]{"L", "O", "H", "D", "N", "R"}));
        pieceList.add(Arrays.asList(new String[]{"D", "R", "L", "H", "O", "H"}));
        pieceList.add(Arrays.asList(new String[]{"W", "U", "N", "T", "O", "O"}));
        pieceList.add(Arrays.asList(new String[]{"T", "I", "I", "T", "I", "E"}));
        pieceList.add(Arrays.asList(new String[]{"Y", "A", "S", "F", "I", "R"}));
        pieceList.add(Arrays.asList(new String[]{"E", "E", "E", "E", "A", "A"}));
        pieceList.add(Arrays.asList(new String[]{"C", "E", "L", "T", "I", "P"}));

        Collections.shuffle(pieceList);

        List<String> result = new ArrayList<>();
        Random random = new Random();
        for (List<String> piece : pieceList) {
            int index = random.nextInt(piece.size());
            result.add(piece.get(index));
        }

        return result;
    }

}
