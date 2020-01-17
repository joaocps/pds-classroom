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
public interface JGaloInterface {
	public abstract char getActualPlayer();
	public abstract boolean setJogada(int lin, int col);
	public abstract boolean isFinished();
	public abstract char checkResult();
}