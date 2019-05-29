package fbuni;

import javax.swing.JLabel;

final public class Nave extends DesenhoMovel {
	public int lifes;
	public Nave() {
		this.lifes = 3;
	}
	public Nave(int x, int y, String path, int velocity) {
		super(x, y, path, velocity);
		this.lifes = 3;
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	
	public boolean  getColision(Obstaculo b) {
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
	
	public void handleColision(Obstaculo b) {
		this.lifes -=1;
		b.restart();
	}
}
