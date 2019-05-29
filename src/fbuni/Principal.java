package fbuni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
public class Principal extends JFrame implements KeyListener, Runnable {
	private Thread t;
	static private int w;
	static private int h;
	private Desenho espaco;
	private Nave nave;
	private ArrayList<Obstaculo> obstaculo;
	private Placar placar;
	private boolean gameOver = false;
	
	
	
	private static final long serialVersionUID = 1L;
	
	public Principal() {
		w = 600;
		h = 900;
		espaco = new Desenho(0,0,"fundo.png");
		nave = new Nave( 300 , 750 ,"nave2.png",15);
		obstaculo = new ArrayList<Obstaculo>();
		this.obstaculo.add(new Obstaculo(60,30,"ob.png", 100, 10));
		this.obstaculo.add(new Obstaculo(250,30,"ob.png", 100, 10));
		this.obstaculo.add(new Obstaculo(300,30,"ob2.png", 50, 12));
		
		placar = new Placar();
		
		this.setTitle( this.placar.getMsg());
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.t = new Thread(this);
		t.start();
	}
	  
	public void run() {
		while(gameOver != true) {
			System.out.print("t");
			
			for( int i = 0; i< this.obstaculo.size(); i++) {
				
				if(this.nave.getLifes() < 0) {
					int count = 0;
					this.obstaculo.get(i).setGameOver(true);
					JOptionPane.showMessageDialog(null, "Gamer Over!");
					
					System.exit(1);
				}else {
					if(this.obstaculo.get(i).getY() > 900) {
						this.placar.setPoint(this.placar.getPoint()+10);
						this.setTitle( this.placar.getMsg( this.nave.getLifes() ) );
						this.obstaculo.get(i).restart();
					}
					
					if(this.nave.getColision(this.obstaculo.get(i))) {
						this.nave.handleColision(this.obstaculo.get(i));
						this.setTitle( this.placar.getMsg( this.nave.getLifes() ) );
					}
				}
				
			}
		}
	}
	//EVITAR ALTERAR ESSE MÉTODO
	public static void main(String[] args) {
		//Criando uma instância da classe principal
		Principal t = new Principal(); 
		t.setSize(w, h);
		t.createBufferStrategy(1);		
		t.setVisible(true);
		//t.setResizable(false);
		t.setLocationRelativeTo(null);
		t.createBufferStrategy(2);
	}

	//EVITAR ALTERAR ESSE MÉTODO
	public void renderizarGraphics() {
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
	    Graphics g = getBufferStrategy().getDrawGraphics();
	     
	    //Criando um contexto gráfico
	    Graphics g2 = g.create();
	    //Limpando a tela
	    g2.setColor(Color.WHITE);        
	    g2.fillRect(0, 0, getWidth(), getHeight());

	    renderizarImagens(g2);
	     
	    //Liberando os contextos criados.
	    g.dispose(); 
	    g2.dispose();
	}
	
	//ESSE É O MÉTODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) {
		//Desenhando as imagens
		espaco.desenhar(g2);
		nave.desenhar(g2);
		for(int i = 0; i< this.obstaculo.size(); i++ ) {
			this.obstaculo.get(i).desenhar(g2);
			
		}
	}
	
	//EVITAR ALTERAR ESSE MÉTODO
	public void paint(Graphics g) {
		this.renderizarGraphics();
		repaint();
	}

	public void keyPressed(KeyEvent evt) {
		//Especificando o comportamento das teclas
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
        	nave.moveRight(w-140);
        	repaint();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
        	nave.moveLeft();
        	repaint();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_UP){
        	nave.moveUp();
        	repaint();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
        	nave.moveDown(h-70);
        	repaint();
        }
        
     }

	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {}
}
