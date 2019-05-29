package fbuni;

import java.awt.Font;
import javax.swing.JLabel;
public class Placar {
	private String msg;
	private int point;
	public Placar() {
		msg = "Lifes: 3 Point: 0";
		this.point = 0;
	}
	public String getMsg(int lifes) {
		msg = "Lifes: "+lifes + " Point: "+ this.point;
		return msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
			
}
