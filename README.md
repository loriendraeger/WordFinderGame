
# WordFinderGame

This is an implementation of solving Boggle with arbitrary board dimensions and even boards with arbitrary topography. To accomplish this, a board and its contained pieces must conform to a simple interface. Also the text on the pieces can strings of any length, not just a single letter.

I developed this against OpenJDK 15, but I don't think there's anything in here that wouldn't work with Java 8+.

## Usage

A list of valid words is not included. I searched the web for 'boggle word database' and didn't get many results, but searching for 'scrabble word database' led me to [this file](https://github.com/jmlewis/valett/blob/master/scrabble/sowpods.txt). The creatively-named Main class stitches all the components together and can quickly load a file that contains 1 valid word on each line. The word file I used was sorted, but that's not a requirement. Anyway, edit the FILE_NAME variable in Main to be a path to your file, compile, and run.

All my words and pieces use capital letters exclusively, so I didn't do any handling of ignoring case or anything like that.

Right now it plays a 5x5 rectangular board and then a weird board that looks like two diamonds stacked on eachother.

## Architecture

There are four components to inside this program.

* WordFinderPlayer, which contains the actual algorithm for solving the board.
* WordFinderBoards, the interfaces and a couple sample implementations of the game board.
* WordLists, the interface and an implementation of a function to check if a string is a word or the start of a word.
* WordScorer, to calculate the score for a particular word.

## Algorithm

WordFinderPlayer consumes the board as a directed graph. The board itself is expected to be able to provide a list of its pieces. Each board piece is also expected to be able to provide a list of its adjacent pieces.

Each piece on the board is used as a beginning of a depth first search of the graph looking for words. As pieces are encountered, they are pushed onto the stack. Then the contents of the stack are checked against the WordListChecker. If the contents of the stack are a word, it's added to the result set. If the contents of the stack are the beginning of other words, its adjacent pieces are checked recursively.

Boggle does not allow looping back to previous pieces to form a word, so the stack (an ArrayDeque in my implementation) is always checked to see if the current piece is already there. This means that the WordFinderPiece objects must either implement .equals or the board setup must ensure the same piece is never duplicated in memory. The RectangularWordFinderBoard only holds one reference to each piece and DoubleDiamondBoard holds duplicate references for its adjacency list, so the default Object.equals implementation works fine.
