package project2;

/**********************************************************************
 * ConnectFourGame3D: Class to handle functions of a 3D connect 
 * 		four game.
 * 
 * @author Ryan Jay Brunsting
 * @version 2/27/2018
 *********************************************************************/
public class ConnectFourGame3D {
	
	/** Three-Dimensional array to represent connect four board. */
	private int[][][] board;
	
	/** Variable for the size of the board. */
	private int size;
	
	/** Variable for the current player. */
	private int player;      
	
	/** Variable for the number of players. */
	private int playerCount;
	
	/** Static variable for the user's number. */
	public static final int USER = 0;     
	
	/** Static variable for the computer's number. */
	public static final int COMPUTER = 1; 

	/******************************************************************
	 *Constructor for the ConnectFourGame3D that initializes variables 
	 *		and resets the board.
	 *
	 *@param pSize Variable for the size of the board.
	 *****************************************************************/
	public ConnectFourGame3D(int pSize)
	{
		size = pSize;
		board = new int[size][size][size];
		player = USER;
		playerCount = 2;
		reset();
	}

	/******************************************************************
	 *Resets the board to -1.
	 *****************************************************************/
	public void reset( ) {
		
		//loops through the board and sets each position to -1
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				for(int width = 0; width < size; width++)
					board[row][col][width] = -1;
			}
		}
	}

	/******************************************************************
	 *Selects the first empty row on the board for the given column
	 *		and width.
	 *
	 *@param col Column number to find empty row in.
	 *@param width Width number to find empty row in.
	 *@return Returns an empty row, or if column is full -1.
	 *****************************************************************/
	public int selectCol(int col, int width) {
		
		//checks for empty row in the given column and width
		for (int row = size - 1; row >= 0; row--) {
			if (board[row][col][width] == -1) {
				board[row][col][width] = player;
				return row;
			}
		}
		return -1;
	}

	/******************************************************************
	 *Switches the current player.
	 *
	 *@return Returns the players number.
	 *****************************************************************/
	public int switchPlayer( ) {
		player = (player + 1) % playerCount;
		return player;
	}

	/******************************************************************
	 *Gets the current player.
	 *
	 *@return Returns the number of the current player, either 0 or 1.
	 *****************************************************************/
	public int getCurrentPlayer() {
		return player;
	}

	/******************************************************************
	 *Checks if the current player has won.
	 * 
	 *@return Returns true if the current player won, else false.
	 *****************************************************************/
	public boolean isWinner() {
		//check for horizontal winner to the back
		int numInRow = 0;
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				for(int width = 0; width < size; width++) {
					if(board[row][col][width] == player) {
						numInRow++;
						if(numInRow == 4)
							return true;
					}
					else
						numInRow = 0;	
				}
			}
		}

		//check for horizontal winner to the side
		numInRow = 0;
		for(int row = 0; row < size; row++) {
			for(int width = 0; width < size; width++) {
				for(int col = 0; col < size; col++) {
					if(board[row][col][width] == player) {
						numInRow++;
						if(numInRow == 4)
							return true;
					}
					else
						numInRow = 0;	
				}
			}
		}

		//check for vertical winner
		numInRow = 0;

		for(int col = 0; col < size; col++) {
			for(int width = 0; width < size; width++) {
				for(int row = 0; row < size; row++) {
					if(board[row][col][width] == player) {
						numInRow++;
						if(numInRow == 4)
							return true;
					}
					else
						numInRow = 0;
				}	
			}
		}

		//check for diagonal winner to the back and right
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = 0; width < size-3; width++) {
					if(board[row][col][width] == player
							&& board[row][col + 1][width + 1] == player
							&& board[row][col + 2][width + 2] == player
							&& board[row][col + 3][width + 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the back and left
		for(int row = size-1; row >= 3; row--) {
			for(int col = size-1; col >= 3; col--) {
				for(int width = 0; width < size-3; width++) {
					if(board[row][col][width] == player
							&& board[row][col - 1][width + 1] == player
							&& board[row][col - 2][width + 2] == player
							&& board[row][col - 3][width + 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the right
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = 0; width < size-3; width++) {
					if(board[row][col][width] == player
							&& board[row - 1][col + 1][width] == player
							&& board[row - 2][col + 2][width] == player
							&& board[row - 3][col + 3][width] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the left
		for(int row = size-1; row >= 3; row--) {
			for(int col = size-1; col >= 3; col--) {
				for(int width = 0; width < size - 3; width++) {
					if(board[row][col][width] == player
							&& board[row -1 ][col - 1][width] == player
							&& board[row -2 ][col - 2][width] == player
							&& board[row -3 ][col - 3][width] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the back
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = 0; width < size - 3; width++) {
					if(board[row][col][width] == player
							&& board[row - 1][col][width + 1] == player
							&& board[row - 2][col][width + 2] == player
							&& board[row - 3][col][width + 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the front
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = size - 1; width >= 3; width--) {
					if(board[row][col][width] == player
							&& board[row - 1][col][width - 1] == player
							&& board[row - 2][col][width - 2] == player
							&& board[row - 3][col][width - 3] == player) 
						return true;
				}
			}
		}
		//check for diagonal winner to the front and left and up
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = size - 1; width >= 3; width--) {
					if(board[row][col][width] == player
							&& board[row - 1][col + 1][width - 1] == player
							&& board[row - 2][col + 2][width - 2] == player
							&& board[row - 3][col + 3][width - 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the back and left and up
		for(int row = size-1; row >= 3; row--) {
			for(int col = 0; col < size - 3; col++) {
				for(int width = 0; width < size - 3; width++) {
					if(board[row][col][width] == player
							&& board[row - 1][col + 1][width + 1] == player
							&& board[row - 2][col + 2][width + 2] == player
							&& board[row - 3][col + 3][width + 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the front and right and up
		for(int row = size-1; row >= 3; row--) {
			for(int col = size - 1; col >= 3; col--) {
				for(int width = size - 1; width >= 3; width--) {
					if(board[row][col][width] == player
							&& board[row - 1][col - 1][width - 1] == player
							&& board[row - 2][col - 2][width - 2] == player
							&& board[row - 3][col - 3][width - 3] == player) 
						return true;
				}
			}
		}

		//check for diagonal winner to the back and right and up
		for(int row = size-1; row >= 3; row--) {
			for(int col = size - 1; col >= 3; col--) {
				for(int width = 0; width < size - 3; width++) {
					if(board[row][col][width] == player
							&& board[row - 1][col - 1][width + 1] == player
							&& board[row - 2][col - 2][width + 2] == player
							&& board[row - 3][col - 3][width + 3] == player) 
						return true;
				}
			}
		}
		return false;
	}
}
