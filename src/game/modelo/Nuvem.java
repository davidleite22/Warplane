package game.modelo;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Nuvem {

	private Image imagem;
	private int x, y;
	private int altura, largura;
	private boolean isVisivel;

//	private static final int LARGURA = 938;
	private static int VELOCIDADE = 1;

	public Nuvem(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\nuvem.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = largura;
			Random nuvemRandomica = new Random();
			int m = nuvemRandomica.nextInt(500);
			this.x = m + 900;
//			Random nuvemRandomica2 = new Random();
//			int n = nuvemRandomica2.nextInt(500);
//			this.y = n;
		} else {
			
			this.x -= VELOCIDADE;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int Velocidade) {
		VELOCIDADE = Velocidade;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

}
