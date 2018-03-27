package project2;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ConnectFour {
	public static void main(String[] args) {
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem gameItem;   
		JMenuItem restartItem;

		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		gameItem = new JMenuItem("New Game");
		restartItem = new JMenuItem("Restart Game");

		fileMenu.add(gameItem);
		fileMenu.add(quitItem);
		fileMenu.add(restartItem);
		menus = new JMenuBar();
		frame.setJMenuBar(menus);
		menus.add(fileMenu);

		//hi
		frame.getContentPane().add(new ConnectFourPanel(quitItem, gameItem, restartItem));

		frame.pack();
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
