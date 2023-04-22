package br.edu.iff;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.tela.Conteiner;
import br.edu.iff.tela.Painel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class JogoDaForcaImagem {

	private final static List<String> palavrasTemaComida = Arrays.asList("Pera", "Pizza", "Arroz", "Lasanha", "Sushi");
	private final static List<String> palavrasTemaPais = Arrays.asList("Brasil", "Portugal", "Austria", "China",
			"Argentina", "Russia", "Gana");
	private final static List<String> palavrasTemaEsporte = Arrays.asList("Futebol", "Basquete", "Volei", "Tenis",
			"Natacao", "Ginastica", "Atletismo", "Ciclismo", "Boxe", "Surf");
	private final static String TEMA_COMIDA = "comida", TEMA_PAIS = "pais", TEMA_ESPORTE = "esporte";

	private final static Aplicacao app = Aplicacao.getSoleInstance();
	private final static JogadorRepository jogadorRepostory = app.getRepositoryFactory().getJogadorRepository();
	private final static RodadaRepository rodadaRepository = app.getRepositoryFactory().getRodadaRepository();
	private final static TemaRepository temaRepository = app.getRepositoryFactory().getTemaRepository();
	private final static PalavraRepository palavraRepository = app.getRepositoryFactory().getPalavraRepository();
	private final static PalavraFactory palavraFactory = app.getPalavraFactory();

	private static PalavraAppService palavraAppService;
	private static RodadaAppService rodadaAppService;

	private static Jogador jogador;
	private static Rodada rodada;
	
	private static Painel painelJogo;
	private static Conteiner conteinerJogo;

	public static void main(String[] args) {
		PalavraAppService.createSoleInstance(temaRepository, palavraRepository, palavraFactory);
		RodadaAppService.createSoleInstance(app.getRodadaFactory(), rodadaRepository, jogadorRepostory);

		palavraAppService = PalavraAppService.getSoleInstance();
		rodadaAppService = RodadaAppService.getSoleInstance();

		iniciarPalavrasETemas();

		if (telaInicial()) {
			novaRodada();
		} else {
			telaFimDeJogo();

		}
	}

	static void novaRodada() {
		try {
			rodada = rodadaAppService.novaRodada(jogador);
			telaJogo();
		} catch (JogadorNaoEncontradoException e) {
			System.err.println(e.getMessage());
		}
	}

	static void jogarNovamente() {
		int opcao = 2;
		while (opcao != 0 && opcao != 1) {
			opcao = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Menu", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Jogar novamente", "Sair" }, "Jogar novamente");
		}
		conteinerJogo.dispose();
		if( opcao == 0)
			novaRodada();
		else telaFimDeJogo();
	}

	static void telaJogo() {
		painelJogo = new Painel();
		conteinerJogo = new Conteiner(painelJogo);
		painelJogo.iniciar(jogador, rodada);
		conteinerJogo.repaint();
	}

	static void encerrou() {
		if (rodada.descobriu()) {
			imprimeResultadoRodada(true);
		} else if (rodada.arriscou()) {
			imprimeResultadoRodada(false);
		} else if (rodada.getQuantidadeTentativasRestantes() == 0) {
			imprimeResultadoRodada(false);
		}
	}

	static void imprimeResultadoRodada(boolean sucesso) {
		painelJogo.limparPalavras();
		rodada.exibirPalavras(painelJogo);
		String mensagemFinal = "";
		if (sucesso) {
			mensagemFinal += "Parabéns, você descobriu todas as palavras!";
		} else {
			mensagemFinal += "Poxa. Que pena! Você não acertou todas as palavras";			
		}
		mensagemFinal += "\nSua pontuação nessa rodada foi: " + rodada.calcularPontos();
		mensagemFinal += "\nSua pontuação total é: " + jogador.getPontuacao();
		JOptionPane.showMessageDialog(null, mensagemFinal, "Resultados", JOptionPane.INFORMATION_MESSAGE);
		jogarNovamente();
	}

	static void iniciarPalavrasETemas() {
		Tema comida = app.getTemaFactory().getTema(TEMA_COMIDA);
		Tema pais = app.getTemaFactory().getTema(TEMA_PAIS);
		Tema esporte = app.getTemaFactory().getTema(TEMA_ESPORTE);

		for (String palavra : palavrasTemaComida) {
			palavraAppService.novaPalavra(palavra, comida.getId());
		}
		for (String palavra : palavrasTemaPais) {
			palavraAppService.novaPalavra(palavra, pais.getId());
		}
		for (String palavra : palavrasTemaEsporte) {
			palavraAppService.novaPalavra(palavra, esporte.getId());
		}
	}

	static boolean telaInicial() {
		int opcao = 2;
		while (opcao != 0 && opcao != 1) {
			opcao = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Menu", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Novo Jogo", "Sair" }, "Novo Jogo");
		}
		if (opcao == 0) {
			String nomeJogador = JOptionPane.showInputDialog("Digite seu nome de jogador:");
			if (nomeJogador == null) {
				return false;
			} else if (nomeJogador.isEmpty()) {
				nomeJogador = "Jogador";
			}
			jogador = app.getJogadorFactory().getJogador(nomeJogador);
		}
		return opcao == 0;
	}

	public static int arriscarLetra(char codigo) {
		if (Character.isLetter(codigo)) { //Garante atendimento do contrato
			if (codigo >= 'A' && codigo <= 'Z' || codigo >= 'À' && codigo <= 'Ý') { // Se a letra dgitada for maiúscula, converte para minúscula somando 32																					
				codigo = (char) (codigo + 32);
			}
			rodada.tentar(codigo);
			rodada.exibirLetrasErradas(painelJogo);
			rodada.exibirBoneco(painelJogo);
			encerrou();
			return rodada.getQuantidadeErros();			
		}
		return rodada.getQuantidadeErros();
	}
	
	public static void assiscarPalavra(List<String> palavrasEmString) {
		List<Palavra> palavras = new ArrayList<>();
		for(String p: palavrasEmString) {
			palavras.add(palavraFactory.getPalavra(p, rodada.getTema()));
		}
		rodada.arriscar(palavras);		
		encerrou();
	}
	
	public static void exibirItens() {
		rodada.exibirItens(painelJogo);
	}

	static void telaFimDeJogo() {
		conteinerJogo.dispose();
		String outputs = "\n\nObrigado por ter jogado o jogo da forca\n";
		outputs += "Implementado por Bruno Alves, Felipe Muros e Rafael Panisset\n";
		outputs += "Desenhado e supervisionado pelo professor Mark Douglas\n";
		outputs += "Curso Bacharel em Sistemas de Informação\n";
		outputs += "Instituto Federal Fluminense, campus Centro - Campos dos Goytacazes - RJ\n";
		outputs += "Abril de 2023\n";

		JOptionPane.showMessageDialog(null, outputs, "Créditos", JOptionPane.INFORMATION_MESSAGE);
	}
}
