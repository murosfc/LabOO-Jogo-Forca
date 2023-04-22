package br.edu.iff.tela;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;

import br.edu.iff.JogoDaForcaImagem;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Painel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel palavra1, palavra2, palavra3;
	private JLabel palavra1Label, palavra2Label, palavra3Label;
	private JLabel erros;
	private JTextField letrasErradas;
	private JLabel cabeca, olhoEsquerdo, olhoDireito, nariz, boca, tronco, bracoDireito, bracoEsquerdo, pernaEsquerda,
			pernaDireita;
	private List<JLabel> partesBoneco;
	private int quantidadeDePalavras;
	private List<Integer> tamanhosPalavras;

	public Painel() {
		setLayout(null);

		tamanhosPalavras = new ArrayList<>();

		JLabel instrucoes = new JLabel(
				"Considere letras sem acento, e sem caracteres exclusivos do português do Brasil.");
		instrucoes.setBounds(10, 36, 542, 14);
		add(instrucoes);

		palavra1Label = new JLabel("Palavra 1:");
		palavra1Label.setForeground(Color.MAGENTA);
		palavra1Label.setBounds(10, 95, 61, 14);
		palavra1Label.setVisible(false);
		add(palavra1Label);

		palavra2Label = new JLabel("Palavra 2:");
		palavra2Label.setForeground(Color.MAGENTA);
		palavra2Label.setBounds(10, 120, 61, 14);
		palavra2Label.setVisible(false);
		add(palavra2Label);

		palavra3Label = new JLabel("Palavra 3:");
		palavra3Label.setForeground(Color.MAGENTA);
		palavra3Label.setBounds(10, 145, 61, 14);
		palavra3Label.setVisible(false);
		add(palavra3Label);

		palavra1 = new JLabel("");
		palavra1.setBounds(80, 95, 200, 14);
		add(palavra1);

		palavra2 = new JLabel("");
		palavra2.setBounds(80, 120, 200, 14);
		add(palavra2);

		palavra3 = new JLabel("");
		palavra3.setBounds(80, 145, 200, 14);
		add(palavra3);

		JLabel palpitesLabel = new JLabel("Letras erradas:");
		palpitesLabel.setBounds(364, 212, 102, 14);
		add(palpitesLabel);

		letrasErradas = new JTextField();
		letrasErradas.setEditable(false);
		letrasErradas.setBounds(364, 237, 298, 58);
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
				String inputJogador = JOptionPane.showInputDialog("Digite a letra que deseja arriscar");
				if (inputJogador != null && !inputJogador.isEmpty() && Character.isLetter(inputJogador.charAt(0))) {
					limparLetrasErradas();
					int quantidadeErros = JogoDaForcaImagem.arriscarLetra(inputJogador.charAt(0));
					erros.setText("Erro " + quantidadeErros + " de " + Rodada.getMaxErros());
					limparPalavras();
					JogoDaForcaImagem.exibirItens();
				}
			}
		});
		;
		arriscarLetraBtn.setBounds(359, 364, 151, 23);
		add(arriscarLetraBtn);

		JButton arriscarPalavrasBtn = new JButton("Arriscar as palavras");
		arriscarPalavrasBtn.setBounds(520, 364, 150, 23);
		arriscarPalavrasBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> palpites = new ArrayList<>();
				for (int i = 0; i < quantidadeDePalavras; i++) {
					String palavraArriscada = JOptionPane.showInputDialog("Digite a palavra numero " + (i + 1));
					if(palavraArriscada == null)
						palavraArriscada = "";
					palpites.add(palavraArriscada);
				}
				JogoDaForcaImagem.assiscarPalavra(palpites);
			}
		});
		add(arriscarPalavrasBtn);

		JLabel baseForca = new JLabel("");
		baseForca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/forca.png")));
		baseForca.setBounds(85, 181, 194, 239);
		add(baseForca);
	}

	public void iniciar(Jogador jogador, Rodada rodada) {
		List<JLabel> palavrasLabel = List.of(palavra1Label, palavra2Label, palavra3Label);
		quantidadeDePalavras = rodada.getNumPalavras();

		JLabel bvLabel = new JLabel(
				"Olá " + jogador.getNome() + ". Sua pontuação atual é " + jogador.getPontuacao() + ".");
		bvLabel.setBounds(10, 11, 540, 14);
		add(bvLabel);

		JLabel infoRodada = new JLabel("Esta rodada é composta por " + quantidadeDePalavras + " palavras, com o tema: "
				+ rodada.getTema().getNome() + ".");
		infoRodada.setForeground(Color.BLUE);
		infoRodada.setBounds(10, 70, 364, 14);
		add(infoRodada);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/logoHangman.png")));
		logo.setBounds(364, 61, 300, 120);
		add(logo);

		erros = new JLabel("Erro " + rodada.getQuantidadeErros() + " de " + Rodada.getMaxErros());
		erros.setForeground(Color.RED);
		erros.setBounds(364, 194, 81, 14);
		add(erros);

		iniciaBoneco();

		for (int i = 0; i < quantidadeDePalavras; i++) {
			palavrasLabel.get(i).setVisible(true);
			tamanhosPalavras.add(rodada.getPalavras().get(i).getTamanho());
		}

		rodada.exibirItens(this);
	}

	public void exibirPalavras(char codigo) {
		List<JLabel> palavras = List.of(palavra1, palavra2, palavra3);
		int i = 0;
		for (JLabel label : palavras) {
			String palavra = label.getText();
			if (palavra.length() < tamanhosPalavras.get(i) && tamanhosPalavras.get(i) != null && tamanhosPalavras.get(i) != 0) {
				palavra += codigo;
				label.setText(palavra);
				break;
			}
			i++;
		}
		repaint();
	}

	public void limparPalavras() {
		List<JLabel> palavras = List.of(palavra1, palavra2, palavra3);
		for (JLabel label : palavras) {
			label.setText("");
		}
	}

	private void limparLetrasErradas() {
		letrasErradas.setText("");
	}

	public void exibirLetrasErradas(char codigo) {
		String letrasErradasExibir = letrasErradas.getText();
		letrasErradasExibir += codigo;
		letrasErradas.setText(letrasErradasExibir);
		letrasErradas.repaint();
	}

	public void exibirBoneco(int partes) {
		if (partes == 10) {
			olhoEsquerdo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olhoperda.png")));
			olhoDireito.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olhoperda.png")));
			boca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/bocaperda.png")));
		}
		for (int i = 0; i < partes; i++) {
			partesBoneco.get(i).setVisible(true);
		}
	}

	private void iniciaBoneco() {
		olhoEsquerdo = new JLabel("");
		olhoEsquerdo.setBounds(180, 210, 21, 17);
		olhoEsquerdo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olho.png")));

		cabeca = new JLabel("");
		cabeca.setBounds(175, 197, 38, 53);
		cabeca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/cabeca.png")));

		olhoDireito = new JLabel("");
		olhoDireito.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/olho.png")));
		olhoDireito.setBounds(195, 211, 21, 14);

		nariz = new JLabel("");
		nariz.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/nariz.png")));
		nariz.setBounds(190, 218, 14, 14);

		boca = new JLabel("");
		boca.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/boca.png")));
		boca.setBounds(190, 227, 17, 14);

		tronco = new JLabel("");
		tronco.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/tronc.png")));
		tronco.setBounds(190, 230, 21, 91);

		bracoDireito = new JLabel("");
		bracoDireito.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membrodireito.png")));
		bracoDireito.setBounds(190, 238, 38, 53);

		pernaDireita = new JLabel("");
		pernaDireita.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membrodireito.png")));
		pernaDireita.setBounds(190, 295, 38, 53);

		pernaEsquerda = new JLabel("");
		pernaEsquerda.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membroesquerdo.png")));
		pernaEsquerda.setBounds(167, 295, 38, 53);

		bracoEsquerdo = new JLabel("");
		bracoEsquerdo.setIcon(new ImageIcon(Painel.class.getResource("/br/edu/iff/tela/imagens/membroesquerdo.png")));
		bracoEsquerdo.setBounds(167, 238, 38, 53);

		partesBoneco = List.of(cabeca, olhoEsquerdo, olhoDireito, nariz, boca, tronco, bracoDireito, bracoEsquerdo,
				pernaEsquerda, pernaDireita);

		for (JLabel label : partesBoneco) {
			label.setVisible(false);
			add(label);
		}

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
}
