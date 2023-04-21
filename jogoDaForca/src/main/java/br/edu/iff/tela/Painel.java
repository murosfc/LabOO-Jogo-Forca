package br.edu.iff.tela;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Painel extends JPanel {	
	private static final long serialVersionUID = 1L;
	private int quantidadeDePalavras, totalErros, maxErros;
	private String temaAtual, nomeJogadorAtual;
	private JLabel palavra1, palavra2, palavra3;
	private JTextField letrasErradas;	
	private JLabel cabeca , olhoEsquerdo, olhoDireito, nariz, boca, tronco, bracoDireito, bracoEsquerdo, pernaEsquerda, pernaDireita;	

	public Painel() {
		setLayout(null);

		JLabel instrucoes = new JLabel("Considere letras sem acento, e sem caracteres exclusivos do português do Brasil.");
		instrucoes.setBounds(10, 36, 542, 14);
		add(instrucoes);
		
		JLabel palavra1Label = new JLabel("Palavra 1:");
		palavra1Label.setForeground(Color.MAGENTA);
		palavra1Label.setBounds(10, 95, 61, 14);
		add(palavra1Label);
		
		JLabel palavra2Label = new JLabel("Palavra 2:");
		palavra2Label.setForeground(Color.MAGENTA);
		palavra2Label.setBounds(10, 120, 61, 14);
		add(palavra2Label);
		
		JLabel palavra3Label = new JLabel("Palavra 3:");
		palavra3Label.setForeground(Color.MAGENTA);
		palavra3Label.setBounds(10, 145, 61, 14);
		add(palavra3Label);
		
		palavra1 = new JLabel("");
		palavra1.setBounds(65, 95, 200, 14);
		add(palavra1);
		
		palavra2 = new JLabel("");
		palavra2.setBounds(65, 120, 200, 14);
		add(palavra2);
		
		palavra3 = new JLabel("");
		palavra3.setBounds(65, 145, 200, 14);
		add(palavra3);		
		
		JLabel palpitesLabel = new JLabel("Letras erradas:");
		palpitesLabel.setBounds(364, 120, 102, 14);
		add(palpitesLabel);
		
		letrasErradas = new JTextField();
		letrasErradas.setEditable(false);
		letrasErradas.setBounds(359, 134, 298, 91);
		add(letrasErradas);
		letrasErradas.setColumns(10);
		
		JLabel proximaAcaoLabel = new JLabel("Escolha sua próxima ação: ");
		proximaAcaoLabel.setForeground(Color.BLUE);
		proximaAcaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		proximaAcaoLabel.setBounds(364, 337, 226, 14);
		add(proximaAcaoLabel);
		
		JButton arriscarLetraBtn = new JButton("Arriscar uma letra");
		arriscarLetraBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		arriscarLetraBtn.setBounds(359, 364, 151, 23);
		add(arriscarLetraBtn);
		
		JButton arriscarPalavrasBtn = new JButton("Arriscar as palavras");
		arriscarPalavrasBtn.setBounds(520, 364, 150, 23);
		arriscarPalavrasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(arriscarPalavrasBtn);
		
		JLabel baseForca = new JLabel("");
		baseForca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/forca.png")));
		baseForca.setBounds(85, 181, 194, 239);
		add(baseForca);
	}
	
	public void iniciar() {
		JLabel bvLabel = new JLabel("Olá " + nomeJogadorAtual);
		bvLabel.setBounds(10, 11, 87, 14);
		add(bvLabel);	
		
		JLabel infoRodada = new JLabel("Esta rodada é composta por " + quantidadeDePalavras + " palavras, com o tema: " + temaAtual + ".");
		infoRodada.setForeground(Color.BLUE);
		infoRodada.setBounds(10, 70, 364, 14);
		add(infoRodada);		
		
		JLabel erros = new JLabel("Erro " + totalErros + " de " + maxErros);
		erros.setForeground(Color.RED);
		erros.setBounds(364, 95, 81, 14);
		add(erros);		
		
		iniciaBoneco();
	}
	
	private void iniciaBoneco() {
		olhoEsquerdo = new JLabel("");
		olhoEsquerdo.setBounds(180, 210, 21, 17);
		olhoEsquerdo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olho.png")));
		add(olhoEsquerdo);
		
		cabeca = new JLabel("");
		cabeca.setBounds(175, 197, 38, 53);
		cabeca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/cabeca.png")));
		add(cabeca);
		
		olhoDireito = new JLabel("");
		olhoDireito.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olho.png")));
		olhoDireito.setBounds(195, 211, 21, 14);
		add(olhoDireito);
		
		nariz = new JLabel("");
		nariz.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/nariz.png")));
		nariz.setBounds(190, 218, 14, 14);
		add(nariz);
		
		boca = new JLabel("");
		boca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/boca.png")));
		boca.setBounds(190, 227, 17, 14);
		add(boca);
		
		tronco = new JLabel("");
		tronco.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/tronc.png")));
		tronco.setBounds(190, 230, 21, 91);
		add(tronco);
		
		bracoDireito = new JLabel("");
		bracoDireito.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membrodireito.png")));
		bracoDireito.setBounds(190, 238, 38, 53);
		add(bracoDireito);
		
		pernaDireita = new JLabel("");
		pernaDireita.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membrodireito.png")));
		pernaDireita.setBounds(190, 295, 38, 53);
		add(pernaDireita);
		
		pernaEsquerda = new JLabel("");
		pernaEsquerda.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membroesquerdo.png")));
		pernaEsquerda.setBounds(167, 295, 38, 53);
		add(pernaEsquerda);
		
		bracoEsquerdo = new JLabel("");
		bracoEsquerdo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membroesquerdo.png")));
		bracoEsquerdo.setBounds(167, 238, 38, 53);
		add(bracoEsquerdo);
	}

	public void setQuantidadeDePalavras(int quantidadeDePalavras) {
		this.quantidadeDePalavras = quantidadeDePalavras;
	}

	public void setTotalErros(int totalErros) {
		this.totalErros = totalErros;
	}

	public void setMaxErros(int maxErros) {
		this.maxErros = maxErros;
	}

	public void setTemaAtual(String tema) {
		this.temaAtual = tema;
	}

	public void setPalavra1(JLabel palavra1) {
		this.palavra1 = palavra1;
	}

	public void setPalavra2(JLabel palavra2) {
		this.palavra2 = palavra2;
	}

	public void setPalavra3(JLabel palavra3) {
		this.palavra3 = palavra3;
	}

	public void setNomeJogadorAtual(String nomeJogador) {
		this.nomeJogadorAtual = nomeJogador;
	}	
}
