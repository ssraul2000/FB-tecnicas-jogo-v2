package fbuni;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Gamer extends JFrame implements Runnable, KeyListener {

  private int pontos = 0;
  private Thread exec = new Thread(this);
  private ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
  private Nave nave;
  private Desenho background;

  public Gamer(){
		this.background = new Desenho(0,0,"fundo.png");
		this.nave = new Nave( 300 , 750 ,"nave2.png",15);
		this.obstaculos.add(new Obstaculo(20,0,"ob.png", 10));
		this.obstaculos.add(new Obstaculo(120,-50,"ob2.png", 14));
		this.obstaculos.add(new Obstaculo(220,-100,"ob.png", 10));
		this.obstaculos.add(new Obstaculo(240,-150,"ob2.png", 14));
		
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(600, 900);
		this.createBufferStrategy(1);		
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.createBufferStrategy(2);

		this.exec.start();
		this.setTitle("Pontos => "+this.pontos +  " Vidas => "+ this.nave.getVidas()+ " Level => "+ this.nave.getLevel());
  }

  public void run(){
  while(true){
	  if(this.pontos > 500 * this.nave.getLevel()) {
		  this.nave.setLevel(this.nave.getLevel()+1);
		  this.obstaculos.add(new Life(240,-150,"life.png", 8));
		  this.setTitle("Pontos => "+this.pontos +  " Vidas => "+ this.nave.getVidas()+ " Level => "+ this.nave.getLevel());
	  }
	  if(this.pontos > ( 3000 * ( this.obstaculos.size()-3 ) )  ) {
		  Random r = new Random();
		  if(r.nextBoolean()) {
			  
			  this.obstaculos.add(new Obstaculo(120,0,"ob2.png", 14));
		  }else {
			  this.obstaculos.add(new Obstaculo(220, 0,"ob.png", 10));
		  }
	  }
		
      for(int i = 0; i< this.obstaculos.size(); i++){
		Obstaculo b = this.obstaculos.get(i);
        if(this.nave.getVidas() < 0){
			Obstaculo.setStopObs(true);
			String nome = JOptionPane.showInputDialog("Digite o seu nome, ou cancele para jogar Anônimo!");
			do {
				if(nome == null ) {
					nome = "Anônimo";
				}else if(nome.equals("")) {
					 nome = JOptionPane.showInputDialog("Cancele ou digite o seu nome!");
				}
			}while(nome.equals("") || nome == null );
			try {

				File file = new File("C:\\Users\\raul5k\\Desktop\\teste.txt");
				
				if(file.exists() == false) {
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(new Placar());
					oos.close();
					fos.close();
				}
				
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Placar p = (Placar)ois.readObject();
				p.handleChalenge(this.pontos, nome);
				p.showPlacar();
				ois.close();
				fis.close();
				
				FileOutputStream fos2 = new FileOutputStream(file);
				ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
				oos2.writeObject(p);
				oos2.close();
				fos2.close();									
			}
			catch (FileNotFoundException e) {
				System.out.println("Arquivo não encontrado");
			}
			catch (IOException e) {
				System.out.println("Erro de I/O");
			}
			catch (ClassNotFoundException e) {
				System.out.println("Erro de casting");
			} 
			JOptionPane.showMessageDialog(null, "Gamer Over!");
			System.exit(0);
		}else{
			if(b instanceof Life) {
				if(this.nave.colision(b)) {
					if(this.nave.getVidas() < 3) {
						this.nave.setVidas(this.nave.getVidas()+1);
					}
					this.obstaculos.remove(i);
				}
			}else {
				if(this.nave.colision(b)){
					
					this.nave.setVidas( this.nave.getVidas() - 1 );
					this.obstaculos.add(new Obstaculo(300,0, b.getPath(),  b.getV()  ));
					this.obstaculos.remove(i);
					this.setTitle("Pontos => "+this.pontos +  " Vidas => "+ this.nave.getVidas()+ " Level => "+ this.nave.getLevel());
				}

				if( b.getY() > 900) {
					this.pontos += 10;
					this.obstaculos.add(new Obstaculo(300,0, b.getPath(), b.getV()  ));
					this.obstaculos.remove(i);
					this.setTitle("Pontos => "+this.pontos +  " Vidas => "+ this.nave.getVidas()+ " Level => "+ this.nave.getLevel());
				}
			}
			
		}
	}
	}
  }

		
	public void renderizarGraphics() {
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
	    Graphics g = getBufferStrategy().getDrawGraphics();
	     
	    //Criando um contexto grï¿½fico
	    Graphics g2 = g.create();
	    //Limpando a tela
	    g2.setColor(Color.WHITE);        
	    g2.fillRect(0, 0, getWidth(), getHeight());

	    renderizarImagens(g2);
	     
	    //Liberando os contextos criados.
	    g.dispose(); 
	    g2.dispose();
	}
	
	//ESSE ï¿½ O Mï¿½TODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) {
		//Desenhando as imagens
		background.desenhar(g2);
		nave.desenhar(g2);
		for(int i = 0; i< this.obstaculos.size(); i++ ) {
			this.obstaculos.get(i).desenhar(g2);
		}

	}
	
	//EVITAR ALTERAR ESSE Mï¿½TODO
	public void paint(Graphics g) {
		this.renderizarGraphics();
		repaint();
	}

	public void keyPressed(KeyEvent evt) {
		//Especificando o comportamento das teclas
        if(!Obstaculo.stopObs()) {
        	if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
            	nave.direita();
            	repaint();
            }
            else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
            	nave.esquerda();
            	repaint();
            }
            else if (evt.getKeyCode() == KeyEvent.VK_UP){
            	nave.cima();
            	repaint();
            }
            else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
            	nave.baixo();
            	repaint();
            }
        }
        
     }

	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {}
  
}
