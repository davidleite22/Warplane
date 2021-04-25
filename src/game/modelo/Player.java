package game.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image image;
	private int altura, largura;
	private Timer timer;
	private List<Tiro> tiros;
	private boolean isVisivel,isTurbo;

	public Player() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		isTurbo = false;

		tiros = new ArrayList<Tiro>();

		timer = new Timer(5000, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo == true) {
		turbo();
		isTurbo = false;
		}
		if (isTurbo == false) {
			load();
		}
	}

	public void load() {

		ImageIcon referencia = new ImageIcon("res\\playeraviao.png");
		image = referencia.getImage();
		altura = image.getHeight(null);
		largura = image.getWidth(null);
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + (altura / 2)));
	}

	public void turbo() {
		isTurbo = true;
		ImageIcon referencia = new ImageIcon("res\\playerturbo.png");
		image = referencia.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);

	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			turbo();
		}
		if (codigo == KeyEvent.VK_A) {
			if (isTurbo == false) {
				tiroSimples();
			}
		}
		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}
		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isTurbo() {
		return isTurbo;
	}

	public void setTurbo(boolean isTurbo) {
		this.isTurbo = isTurbo;
	}

}
