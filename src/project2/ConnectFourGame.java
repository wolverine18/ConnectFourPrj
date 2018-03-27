package project2;

import java.util.*;

/**********************************************************************
 * ConnectFourGame: Two dimensional connect four game features with
 * 		two player and A.I.
 * 
 * @author Ryan Jay Brunsting
 * @version 2/27/2018
 *********************************************************************/
public class ConnectFourGame {
	/** Two-Dimensional array for the game board. */
	private int[][] board;
	
	/** Variable for the size of the board. */
	private int size;
	
	/** Variable for the current player. */
	private int player;      
	
	/** Variable for the number of players. */
	private int playerCount;
	
	/** Static variable for the users number. */
	public static final int USER = 0;     
	
	/** Static variable for the computers number. */
	public static final int COMPUTER = 1; 

	/******************************************************************
	 *Constructor for ConnectFourGame that initializes variables.
	 *
	 * @param pSize Size of the board.
	 *****************************************************************/
	public ConnectFourGame(int pSize)
	{
		size = pSize;
		board = new int[size][size];
		player = USER;
		playerCount = 2;
		reset();
	}

	/******************************************************************
	 * Finds an empty row in the column given.
	 * 
	 * @param col Column number to check for row in.
	 * @return Returns the row number or -1 if the column is full.
	 *****************************************************************/
	public int selectCol(int col) {
		
		//for loop to check for empty row
		for (int row = size - 1; row >= 0; row--) {
			if (board[row][col] == -1) {
				board[row][col] = player;
				return row;
			}
		}
		return -1;
	}

	public void reset( ) {
		
		//for nested for loop to reset board to -1
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++)
				board[row][col] = -1;
		}
	}

	/******************************************************************
	 * Switches the players from player 0 to player 1.
	 * 
	 * @return Returns the next player either 0 or 1.
	 *****************************************************************/
	public int switchPlayer( ) {
		player = (player + 1) % playerCount;
		return player;
	}

	/******************************************************************
	 * Checks if the current player has one the game.
	 * 
	 * @return Returns true if the current player won, and 
	 * 		false if not.
	 *****************************************************************/
	public boolean isWinner() {
		
		//check for horizontal winner
		int numInRow = 0;
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++)
				if(board[row][col] == player) {
					numInRow++;
					if(numInRow == 4)
						return true;
				}
				else
					numInRow = 0;				
		}

		//check for vertical winner
		numInRow = 0;
		for(int col = 0; col < size; col++) {
			for(int row = 0; row < size; row++) {
				if(board[row][col] == player) {
					numInRow++;
					if(numInRow == 4)
						return true;
				}
				else
					numInRow = 0;
			}		
		}

		//check for diagonal winner to the right
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				if(board[row][col] == player
						&& board[row - 1][col + 1] == player
						&& board[row - 2][col + 2] == player
						&& board[row - 3][col + 3] == player) 
					return true;
			}
		}

		//check for diagonal winner to the left
		for(int row = size-1; row >= 3; row--) {
			for(int col = size-1; col >= 3; col--) {
				if(board[row][col] == player
						&& board[row -1 ][col - 1] == player
						&& board[row -2 ][col - 2] == player
						&& board[row -3 ][col - 3] == player) 
					return true;
			}
		}
		return false;
	}

	/******************************************************************
	 * Controls the A.I. part of the game, selecting which column to
	 * 		lay in.
	 * 
	 * @return Returns what column to lay in for the A.I..
	 *****************************************************************/
	public int connectFourAI() {
		//check if computer can win
		for(int col = 0; col < size; col++) {
			int row = selectCol(col);
			if(row != -1) {
				if(isWinner()) {
					board[row][col] = -1;
					return col;
				}
				else
					board[row][col] = -1;
			}
		}

		//check if user can win
		switchPlayer();
		for(int col = 0; col < size; col++) {
			int row = selectCol(col);
			if(row != -1) {
				if(isWinner()) {
					board[row][col] = -1;
					switchPlayer();
					return col;
				}
				else
					board[row][col] = -1;
			}
		}
		switchPlayer();

		//if neither can win select a column to lay in
		int col = 0;
		int row = 0;
		Random r = new Random();
		do {
			col = (int) Math.round(r.nextGaussian() * 2 + 5);
			if(col >= 0 && col <= size-1)
				row = selectCol(col);
		}while(col<=0 || col>=size-1 || row == -1);
		board[row][col] = -1;
		return col;
	}

	/******************************************************************
	 * Returns the current player.
	 * 
	 * @return Returns 0 if users turn, else 1 if computers turn.
	 *****************************************************************/
	public int getCurrentPlayer() {
		return player;
	}

}
