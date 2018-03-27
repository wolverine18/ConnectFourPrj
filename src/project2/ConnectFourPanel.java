package project2;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************************************************
 * Panel class for Connect Four game.
 * 
 * @author Ryan Jay
 *@version 2/27/20180
 *********************************************************************/
@SuppressWarnings("serial")
public class ConnectFourPanel extends JPanel {
	private ConnectFourGame connectFour;
	private ConnectFourGame3D connectFour3D;
	private int gameType;
	private ButtonListener listener;

	private JLabel[][] board;
	private JButton[] selection;
	private JButton[][] selection3D;
	private int boardSize;
	private ImageIcon iconBlank;
	private ImageIcon iconPlayer1;
	private ImageIcon iconPlayer2;
	private JMenuItem gameItem;
	private JMenuItem quitItem;
	private JMenuItem restartItem;

	public ConnectFourPanel(JMenuItem pquitItem, JMenuItem pgameItem,
			JMenuItem prestartItem) {
		iconBlank = new ImageIcon("blank.png");
		iconPlayer1 = new ImageIcon("player1.png");
		iconPlayer2 = new ImageIcon("player2.png");

		quitItem = pquitItem;
		gameItem = pgameItem;
		restartItem = prestartItem;

		listener = new ButtonListener();

		quitItem.addActionListener(listener);
		gameItem.addActionListener(listener); 
		restartItem.addActionListener(listener);
		gameSetup();
	}

	private void gameSetup() {
		JOptionPane.showMessageDialog(null, "Welcome to Connect Four");

		String[] options = new String[] {"2D", "A.I.", "3D", "Cancel"};
		gameType = JOptionPane.showOptionDialog(null,"Select One:",
				"Connect Four", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if(gameType == 3)
			System.exit(1);

		String size = JOptionPane.showInputDialog(null, "Please enter"
				+ " the size of the board.");
		if(size == null) {
			JOptionPane.showMessageDialog(null, "Thank you for playing"
					+ " Connect Four");
			System.exit(1);
		}
		else if(!size.isEmpty()) {
			if(size.matches("^[0-9]*$"))
				boardSize = Integer.parseInt(size);		
			else {
				JOptionPane.showMessageDialog(null, "Entered board "
						+"size is invalid.\nSetting to default size.");
				boardSize = 10;
			}
		}

		if (gameType == 0 || gameType == 1) {
			if(boardSize <= 3 || boardSize >= 30) {
				JOptionPane.showMessageDialog(null, "Entered board "
						+"size is invalid.\nSetting to default size.");
				boardSize = 10;
			}
		}
		else {
			if(boardSize <= 3 || boardSize >= 8) {
				JOptionPane.showMessageDialog(null, "Entered board "
						+"size is invalid.\nSetting to default size.");
				boardSize = 7;
			}
		}

		if(gameType == 0 || gameType == 1) 
			setUp2DBoard();
		else if(gameType == 2)
			connectFour3D();
	}

	private void setUp2DBoard() {
		removeAll();
		connectFour = new ConnectFourGame(boardSize);

		selection = new JButton[boardSize];
		for (int col = 0; col < boardSize; col++) {
			selection[col] = new JButton("Select");
			selection[col].addActionListener(listener);
			add(selection[col]);
		}

		board = new JLabel[boardSize][boardSize];

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = new JLabel("", iconBlank, 
						SwingConstants.CENTER);
				board[row][col].repaint();
				add(board[row][col]);
			}
		}
		repaint();
		revalidate();
		setLayout(new GridLayout(boardSize + 1, boardSize));
	}

	private void connectFour3D() {
		removeAll();
		connectFour3D = new ConnectFourGame3D(boardSize);

		selection3D = new JButton[boardSize][boardSize];
		for(int width = 0; width < boardSize; width++) {
			for (int col = 0; col < boardSize; col++) {
				selection3D[col][width] = new JButton("");
				selection3D[col][width].addActionListener(listener);
				add(selection3D[col][width]);
			}
		}
		repaint();
		revalidate();
		setLayout(new GridLayout(boardSize, boardSize));
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();

			if (comp == gameItem) {    
				gameSetup();
			}			
			if (comp == quitItem)
				System.exit(1);
			if(comp == restartItem) {
				if(gameType == 0 || gameType == 1)
					setUp2DBoard();
				else
					connectFour3D();
			}

			if(gameType == 0) {
				for (int col = 0; col < boardSize; col++) {
					if (selection[col] == comp) {
						int row = connectFour.selectCol(col);
						if (row == -1) {
							JOptionPane.showMessageDialog(null,
									"Column is full!");
							connectFour.switchPlayer();
						}
						else 
							board[row][col].setIcon((
									connectFour.getCurrentPlayer() == 1)
									? iconPlayer1 : iconPlayer2);
						if(connectFour.isWinner())
							panelIsWinner();
						isCatsGame();
					}
				}
				connectFour.catsGame();
				connectFour.switchPlayer();
			}
			else if(gameType == 1) {
				for (int col = 0; col < boardSize; col++) {
					if (selection[col] == comp) {
						int row = connectFour.selectCol(col);
						if (row == -1) {
							JOptionPane.showMessageDialog(null, 
									"Column is full!");
							return;
						}
						else 
							board[row][col].setIcon(iconPlayer1);
						if(connectFour.isWinner())
							panelIsWinner();
						isCatsGame();
					}
				}
				connectFour.switchPlayer();
				JOptionPane.showMessageDialog(null, "Computers turn");
				int aICol = connectFour.connectFourAI();
				int aIRow = connectFour.selectCol(aICol);
				board[aIRow][aICol].setIcon(iconPlayer2);
				if(connectFour.isWinner())
					panelIsWinner();
				isCatsGame();
				connectFour.switchPlayer();
			}
			else if(gameType == 2){
				for(int col = 0; col < boardSize; col++) {
					for(int width = 0; width < boardSize; width++) {
						if(selection3D[col][width] == comp) {
							int row = connectFour3D.selectCol(col, 
									width);
							if(row == -1)
								JOptionPane.showMessageDialog(null,
										"Column is full!");
							else
								selection3D[col][width].setText(
										selection3D[col][width].getText() 
										+ connectFour3D.getCurrentPlayer()
										+ ",");
							if(connectFour3D.isWinner())
								panelIsWinner();
							connectFour3D.switchPlayer();
						}
					}
				}
			}	
		}

		private void isCatsGame() {
			if(connectFour.catsGame()) {
				JOptionPane.showMessageDialog(null,
						"Cat's Game");
				String[] options = new String[] {"Yes", "No"};
				int response = JOptionPane.showOptionDialog(
						null, "Would you like to play again?", 
						"Connect Four", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);

				if(response == 0)
					gameSetup();
				else
					System.exit(1);
			}
		}

		private void panelIsWinner() {
			if(gameType == 0 || gameType == 1) {
				JOptionPane.showMessageDialog(null, "Player " 
						+ connectFour.getCurrentPlayer() + " Wins!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Player " 
						+ connectFour3D.getCurrentPlayer() + " Wins!");
			}
			String[] options = new String[] {"Yes", "No"};
			int response = JOptionPane.showOptionDialog(
					null, "Would you like to play again?", 
					"Connect Four", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);

			if(response == 0)
				gameSetup();
			else
				System.exit(1);
		}
	}
}
