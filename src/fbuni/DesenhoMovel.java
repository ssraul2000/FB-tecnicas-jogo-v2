package fbuni;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS PELO USUÁRIO 
public class DesenhoMovel extends Desenho {
	
	public DesenhoMovel() {}
	
	public DesenhoMovel(int x, int y, String path, int velocity) {
		super(x, y, path);
		this.velocity = velocity;
	}
	
	private int velocity;
	
	
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public void moveRight(int x) {
		if(super.getX() + this.velocity <= x ) {
			super.setX(super.getX() + this.velocity);
		}
	}
	public void moveLeft() {
		if(super.getX() - this.velocity >= 0 ) {
			super.setX(super.getX() - this.velocity);
		}
	}
	public void moveUp() {
		if(super.getY() - this.velocity >= 40 ) {
			super.setY( super.getY() - this.velocity );
		}
	}
	public void moveDown(int y) {
		if(super.getY() + this.velocity <= y ) {
			super.setY( super.getY() + this.velocity );
		}
	}
}
