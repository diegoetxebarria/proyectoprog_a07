package main.general;

import javax.swing.JFrame;

public class Inicio extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private void initUI() {
		setTitle(" Pac-Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(385, 420);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
