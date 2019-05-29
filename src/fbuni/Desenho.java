package fbuni;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA.
public class Desenho {
	
	private int x;
	private int y;
	private BufferedImage img;
	
	public Desenho() {}
	
	public Desenho(int x, int y, String path) {
		this.setX(x);
		this.setY(y);

		File fileImg = new File(path);
		try {
			img = ImageIO.read(fileImg);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(String path) {
		File fileImg = new File(path);
		try {
			img = ImageIO.read(fileImg);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void desenhar(Graphics g) {
		//Desenhando a imagem na tela
		g.drawImage(this.getImg(), this.getX(), this.getY(), null);
	}
}
