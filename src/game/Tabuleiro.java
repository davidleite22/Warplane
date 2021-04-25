package game;

import javax.swing.JFrame;

import game.modelo.Fase;

public class Tabuleiro extends JFrame {

	public Tabuleiro() {
		add (new Fase());
		setTitle("War Plane");
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Tabuleiro();
	}
}
