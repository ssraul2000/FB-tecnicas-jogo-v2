package fbuni;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS PELO USUÁRIO 
public class DesenhoMovel extends Desenho {
	
	private int v;
	
	public DesenhoMovel() {}
	
	
	
	public DesenhoMovel(int x, int y, String path, int v) {
		super(x, y, path);
		this.v = v;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}


	public void direita() {
		if(super.getX() + this.v <= 470 ) {
			super.setX(super.getX() + this.v);
		}
	}
	public void esquerda() {
		if(super.getX() - this.v >= 0 ) {
			super.setX(super.getX() - this.v);
		}
	}
	public void cima() {
		if(super.getY() - this.v >= 40 ) {
			super.setY( super.getY() - this.v);
		}
	}
	public void baixo() {
		if(super.getY() + this.v<= 830 ) {
			super.setY( super.getY() + this.v );
		}
	}
}
