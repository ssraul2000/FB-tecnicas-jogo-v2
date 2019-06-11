package fbuni;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Placar implements Serializable {	
	private ArrayList<User> users;
	public Placar() {
		this.users = new ArrayList<User>();
	}
	public void handleChalenge( int point, String nome ) {
		if(this.users.size() == 0) {
			this.users.add(0, new User(nome,point, 1 ) );
		}else {
			for(int i = 0; i< this.users.size(); i++) {
				if( point > this.users.get(i).getPoint() ) {
					this.users.add(i, new User(nome,point, i+1 ) );
					while(i + 1 < this.users.size()) {
						this.users.get(i+1).setPosition(i+2);
						i++;
					}
					break;
				}else if(point == this.users.get(i).getPoint()) {
					this.users.add(i+1, new User(nome,point, i+2 ) );
					while(i + 2 < this.users.size()) {
						this.users.get(i+2).setPosition(i+3);
						i++;
					}
					break;
				}
			}
		}
		
	}
	
	public void showPlacar() {
		String msg = "";
		for(int i = 0; i< this.users.size() && i < 5; i++) {
			User user = this.users.get(i);
			msg += user.getPosition()+  "ª" + " Nome: " + user.getNome() + " => " + user.getPoint() + " pontos\n";
		}
		JOptionPane.showMessageDialog(null, msg, "Placar :/", 1);
		
	}
	
	
}
