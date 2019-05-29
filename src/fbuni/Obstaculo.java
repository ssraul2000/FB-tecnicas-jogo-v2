package fbuni;
import java.util.Random;

public class Obstaculo extends DesenhoAnimado {
	private Thread t = new Thread(this);
	private int time;
	static private boolean gameOver;
	static Random position;	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getVelocity() {
		return velocity;
	}

	public static boolean getGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		Obstaculo.gameOver = gameOver;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	private int velocity;
	
	public Obstaculo() {
		this.gameOver = false;
		this.time = 100;
		this.velocity = 5;
		this.position = new Random();
	}
	
	public Obstaculo(int x, int y, String path, int time, int velocity) {
		super(x, y, path);
		this.time = time;
		this.velocity = velocity;
		this.gameOver = false;
		this.position = new Random();
		t.start();
	}
	
	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}
	public void restart() {
		super.setX(this.position.nextInt(600));
		super.setY(0);
	}
	
	public void run() {

		super.setX(position.nextInt(600));
		while(gameOver != true) {
			super.setY( super.getY() + this.velocity );
			try {
				t.sleep(this.time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	
}
