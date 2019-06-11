package fbuni;
import java.util.Random;

public class Obstaculo extends DesenhoAnimado {

	private Thread exec;
	static private boolean stopObs;
	private int v;

	
	public Obstaculo(int x, int y, String path, int v) {
		super(x, y, path);
		this.stopObs = false;
		this.v = v;
		this.exec = new Thread(this);
		this.exec.start();
		
	}
	
	public void run() {
		Random r = new Random();
		super.setX(r.nextInt(470));
		while(!stopObs) {
			super.setY( super.getY() + this.v );
			try {
				exec.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	public int getV() {
		return v;
	}
	
	public void setV(int v) {
		this.v = v;
	}

	public static boolean stopObs() {
		return stopObs;
	}

	public static void setStopObs(boolean chave) {
		Obstaculo.stopObs = chave;
	}

}
