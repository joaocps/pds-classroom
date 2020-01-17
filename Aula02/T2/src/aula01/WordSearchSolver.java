/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class WordSearchSolver {

    public static void main(String[] args) {

        boolean showTime = true;
        String fileName = "sopa.txt";

        if (args.length == 1) {
            fileName = args[0];
            showTime = false;
        } else if (args.length == 2) {
            fileName = args[1];
        } else if (args.length > 2) {
            System.err.println("Invalid number of arguments.");
            System.exit(1);
        }

        long startTime = System.currentTimeMillis();

        WordPuzzle wordPuzzle = readPuzzleFromFile(fileName);
        Result[] results = wordPuzzle.solve();

        long milliseconds = System.currentTimeMillis() - startTime;
        float seconds = milliseconds / 1000f;

        if (showTime) {
            System.out.printf("Elapsed time (secs): %.3f \n", seconds);
        }

        for (Result result : results) {
            System.out.println(result);
        }
    }

    private static WordPuzzle readPuzzleFromFile(String fileName) {

        char[][] puzzle = null;
        List<String> words = new ArrayList();
        String line;
        File file = new File("src/aula01/" + fileName);

        try {
            Scanner sc = new Scanner(file);

            line = sc.nextLine();
            int size = line.length();
            puzzle = new char[size][size];
            puzzle[0] = line.toCharArray();
            int i = 1;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.contains(",")) {
                    String[] split = line.split(",");
                    for (String s : split) {
                        words.add(s.trim().toUpperCase());
                    }
                } else if (line.contains(";")) {
                    String[] split = line.split(";");
                    for (String s : split) {
                        words.add(s.trim().toUpperCase());
                    }
                } else {
                    puzzle[i] = line.toCharArray();
                    i++;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return new WordPuzzle(puzzle, words);
    }

}
