/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo_Galo;

/**
 *
 * @author JoaoSantos
 */
public class JGalo_model implements JGaloInterface {

    private char actualPlayer;
    private char winner;
    private char[][] board;
    private int nPlays;
    private static final int TOTAL_PLAYS = 9;

    public JGalo_model(char initialPlayer) {

        if (initialPlayer != 'X' && initialPlayer != 'x' && initialPlayer != 'O' && initialPlayer != 'o') {
            throw new IllegalArgumentException("Jogador Invalido");
        }
        actualPlayer = Character.toUpperCase(initialPlayer);
        changePlayer();
        board = new char[3][3];
        nPlays = 0;
    }

    //muda o jogador e retorna o atual
    @Override
    public char getActualPlayer() {
        changePlayer();
        nPlays++;
        return actualPlayer;
    }

    //preenche espaços da board
    @Override
    public boolean setJogada(int lin, int col) {
        lin--;
        col--;
        if (lin > board.length - 1 || lin < 0 || col > board[lin].length || col < 0 || board[lin][col] != 0) {
            return false;
        }
        board[lin][col] = actualPlayer;

        return true;
    }

    @Override
    public boolean isFinished() {

        if(searchWinningCombination()){
            winner = actualPlayer;
            return true;
        }
        else
            if(nPlays == TOTAL_PLAYS){
                winner = ' ';
                return true;
            }
        return false;
    }

    //verificar horizontais/verticais/diagonais
    private boolean searchWinningCombination() {
        
        String seqDiagonalRigthLeft = "";
        String seqDiagonalLeftRight = "";
        for (int col = 0; col < board[0].length; col++) {
            String seqVertical = "";
            String seqHorizontal = "";
            for (int line = 0; line < board.length; line++) {
                seqVertical += board[line][col];
                seqHorizontal = new String(board[line]);
                if (checkWin(seqHorizontal)) {
                    return true;
                }
            }
            seqDiagonalLeftRight += board[col][col];
            seqDiagonalRigthLeft += board[col][(board.length - 1) - col];
            if (checkWin(seqVertical)) {
                return true;
            }
        }
        if (checkWin(seqDiagonalLeftRight) || checkWin(seqDiagonalRigthLeft)) {
            return true;
        }
        return false;
    }

    @Override
    public char checkResult() {
        return winner;
    }

    //check XXX ou OOO
    private boolean checkWin(String seq) {
        if (seq.equals("XXX") || seq.equals("OOO")) {
            return true;
        }
        return false;
    }

    //mudar jogador
    private void changePlayer() {

        if (actualPlayer == 'X') {
            actualPlayer = 'O';
        } else {
            actualPlayer = 'X';
        }
    }

}
