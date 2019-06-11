package fbuni;

import java.io.Serializable;

public class User implements Serializable {
	private String nome;
	private int point;
	private int position;
	public User(String nome, int point, int position) {
		this.nome = nome;
		this.point = point;
		this.position = position;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}
