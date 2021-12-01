package main.general;

import javax.swing.JFrame;

public class Inicio extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int nivel = 1;
	
	public Inicio (String usuario) {
		Const.usuarioLogeado = usuario;
	}
	
	private void initUI() {
		//add(new Tablero);
		setTitle(" Pac-Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(360, 420);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
