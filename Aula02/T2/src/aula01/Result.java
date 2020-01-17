/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula01;

/**
 *
 * @author Miguel
 */
public class Result {
    
    private String word;
    private int x;
    private int y;
    private Direction direction;

    public Result(String word, int x, int y, Direction direction) {
        this.word = word;
        this.x = x + 1;
        this.y = y + 1;
        this.direction = direction;
    }
    
    @Override
    public String toString()
    {
        return String.format("%-10s %-3d %2d,%-2d    %-10s", word, word.length(), x, y, direction);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    
    
}
