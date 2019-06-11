package fbuni;

import javax.swing.JLabel;

final public class Nave extends DesenhoMovel {
	private int vidas = 2;
	private int level = 1;

	public Nave(int x, int y, String path, int v) {
		super(x, y, path, v);
	}
	
	public boolean colision(Obstaculo b) {
		int aX = this.getX();
		int aY = this.getY();
		int ladoDireitoA = aX+this.getImg().getWidth();
		int ladoEsquerdoA= aX;
		int ladoBaixoA= aY+this.getImg().getHeight();
		int ladoCimaA= aY;
		
		int bX = b.getX();
		int bY = b.getY();
		int ladoDireitoB = bX+b.getImg().getWidth();
		int ladoEsquerdoB= bX;
		int ladoBaixoB= bY+b.getImg().getHeight();
		int ladoCimaB= bY;
		
		boolean colision = false;
		
		boolean cDireita=false;
		boolean cCima=false;
		boolean cBaixo=false;
		boolean cEsquerda=false;
		
		
		if(ladoDireitoA>=ladoEsquerdoB) {
			cDireita=true;
		}
		if(ladoCimaA<=ladoBaixoB) {
			cCima=true;
		}
		if(ladoBaixoA>=ladoCimaB) {
			cBaixo=true;
		}
		if(ladoEsquerdoA<=ladoDireitoB) {
			cEsquerda=true;
		}
		
		if(cDireita && cEsquerda && cBaixo && cCima) {
			colision = true;
		}
				
		return colision;

	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}

	
}
