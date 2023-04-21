package br.edu.iff.tela;

import javax.swing.JFrame;

public class Conteiner extends JFrame {
	private static final long serialVersionUID = 1L;
	private int WIDTH = 700, HEIGHT= 480;
	
	public Conteiner(Painel painelJogo) {
		this.setVisible(true);			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painelJogo.iniciar();
		setContentPane(painelJogo);
		setSize(WIDTH, HEIGHT);		
		setTitle("Jogo da forca");
		this.repaint();
	}
	
}
