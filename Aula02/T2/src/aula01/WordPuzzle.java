/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Miguel
 */
public class WordPuzzle {

    private char[][] puzzle;
    private List<String> words;

    public WordPuzzle(char[][] puzzle, List<String> words) {
        this.puzzle = puzzle;
        this.words = words;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int l = 0; l < puzzle.length; l++) {
            s.append(puzzle[l]);
            s.append("\n");
        }

        for (int i = 0; i < words.size(); i++) {
            s.append(words.get(i).toLowerCase());

            if (i != words.size() - 1) {
                s.append(", ");
            }

        }

        return s.toString();
    }

    //Generate
    public WordPuzzle(List<String> words) {

        this.words = new ArrayList(words);
        int numChars = sortWords(words);
        
        int size = (int) Math.sqrt(numChars) + 3;

        if (size < words.get(0).length() + 3) {
            size = words.get(0).length() + 3;
        }

        if (size < words.size() + 3) {
            size = words.size() + 3;
        }

        this.puzzle = new char[size][size];
        Random random = new Random();
        List<Direction> directions = Arrays.asList(Direction.values());

        for (int i = 0; i < words.size(); i++) {

            boolean fitted = false;
            String word = words.get(i);

            do {
                int l = random.nextInt(size);
                int c = random.nextInt(size);

                if (puzzle[l][c] == 0) {

                    Collections.shuffle(directions);

                    for (Direction direction : directions) {
                        fitted = fitWord(l, c, word, direction);
                        if (fitted) {
                            break;
                        }
                    }
                }

            } while (!fitted);

        }

        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                if (puzzle[l][c] == 0) {
                    //puzzle[l][c] = '_';
                    puzzle[l][c] = (char) (random.nextInt(26) + 'A');
                }
            }
        }
    }

    //Generate
    private boolean fitWord(int l, int c, String word, Direction direction) {

        boolean wordFitted = false;

        switch (direction) {
            case right:
                if (word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l][c + j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l][c + j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case left:
                if (word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l][c - j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l][c - j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case up:
                if (word.length() <= l + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l - j][c] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l - j][c] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case down:
                if (word.length() <= puzzle.length - l) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l + j][c] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l + j][c] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case downright:
                if (word.length() <= puzzle.length - l && word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l + j][c + j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l + j][c + j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case downleft:
                if (word.length() <= puzzle.length - l && word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l + j][c - j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l + j][c - j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case upright:
                if (word.length() <= l + 1 && word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l - j][c + j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l - j][c + j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            case upleft:
                if (word.length() <= l + 1 && word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (puzzle[l - j][c - j] != 0) {
                            return false;
                        }
                    }
                    for (int j = 0; j < word.length(); j++) {
                        puzzle[l - j][c - j] = word.charAt(j);
                    }
                    wordFitted = true;
                }
                break;

            default:
                break;

        }

        return wordFitted;
    }

    //Sort words and return the number of all chars
    public static int sortWords(List<String> arr) {

        int sum = 0;

        for (int i = 0; i < arr.size() - 1; i++) {

            int index = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j).length() > arr.get(index).length()) {
                    index = j;
                }
            }
            String aux = arr.get(index);
            arr.set(index, arr.get(i));
            arr.set(i, aux);

            sum += arr.get(i).length();
        }
        return sum;
    }

    //Solve
    public Result[] solve() {
        List<String> searchList = new ArrayList(words);
        Result[] results = new Result[words.size()];
        List<Direction> directions = Arrays.asList(Direction.values());

        for (int l = 0; l < puzzle.length; l++) {
            for (int c = 0; c < puzzle.length; c++) {
                for (int i = 0; i < searchList.size(); i++) {
                    if (searchList.get(i).charAt(0) == puzzle[l][c]) {
                        //System.out.println(searchList.get(i) + ": " + l + ", " + c);
                        String word = searchList.get(i);
                        
                        for (int j = 0; j < directions.size(); j++) {
                            Result result = searchWord(l, c, word, directions.get(j));
                            if (result != null) {
                                results[words.indexOf(word)] = result;
                                searchList.remove(word);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return results;
    }

    //Solve
    private Result searchWord(int l, int c, String word, Direction direction) {

        switch (direction) {
            case right:
                if (word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l][c + j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.right);
                }
                break;

            case left:
                if (word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l][c - j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.left);
                }
                break;

            case up:
                if (word.length() <= l + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l - j][c]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.up);
                }
                break;

            case down:
                if (word.length() <= puzzle.length - l) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l + j][c]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.down);
                }
                break;

            case downright:
                if (word.length() <= puzzle.length - l && word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l + j][c + j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.downright);
                }
                break;

            case downleft:
                if (word.length() <= puzzle.length - l && word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l + j][c - j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.downleft);
                }
                break;

            case upright:
                if (word.length() <= l + 1 && word.length() <= puzzle.length - c) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l - j][c + j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.upright);
                }
                break;

            case upleft:
                if (word.length() <= l + 1 && word.length() <= c + 1) {
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) != puzzle[l - j][c - j]) {
                            return null;
                        }
                    }
                    return new Result(word, l, c, Direction.upleft);

                }
                break;

            default:
                break;
        }
        return null;
    }

    public char[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(char[][] puzzle) {
        this.puzzle = puzzle;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
