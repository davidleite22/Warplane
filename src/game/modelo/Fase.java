package game.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Player player;
	private Image fundo;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Nuvem> nuvem;
	private boolean emJogo;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\backgroundfase1.jpg");
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializarInimigos();
		emJogo = true;

		inicializarNuvem();
	}

	public void inicializarInimigos() {
		int coordenadas[] = new int[40];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 800);
			int y = (int) (Math.random() * 450 + 30);
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void inicializarNuvem() {

		int coordenadas[] = new int[30];
		nuvem = new ArrayList<Nuvem>();
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 800 + 0);
			int y = (int) (Math.random() * 100 + 20);
			nuvem.add(new Nuvem(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {

			graficos.drawImage(fundo, 0, 0, 800, 500, null);

			for (int p = 0; p < nuvem.size(); p++) {
				Nuvem q = nuvem.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), 100, 50, this);
			}

			graficos.drawImage(player.getImage(), player.getX(), player.getY(), this);

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		} else {
			ImageIcon fimjogo = new ImageIcon("res\\gameover.jpg");
			graficos.drawImage(fimjogo.getImage(), 0, 0, 800, 500, null);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		if (player.isTurbo()) {
			timer.setDelay(2);
		}
		if (player.isTurbo() == false) {
			timer.setDelay(5);
		}
		for (int p = 0; p < nuvem.size(); p++) {
			Nuvem nu = nuvem.get(p);
			if (nu.isVisivel()) {
				nu.update();
			} else {
				nuvem.remove(p);
			}
		}

		List<Tiro> tiros = player.getTiros();

		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
				if(player.isTurbo()) {
					tiros.get(i).setVELOCIDADE(-1);
				}
			} else {
				tiros.remove(i);
			}
		}

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}
		checarColissoes();
		repaint();
	}

	public void checarColissoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if (formaNave.intersects(formaEnemy1)) {
				if(player.isTurbo()) {
					tempEnemy1.setVisivel(false);
				}else {
					
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
				}
			}
		}

		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();
				if (formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}

	private class TecladoAdapter implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

	}
}
