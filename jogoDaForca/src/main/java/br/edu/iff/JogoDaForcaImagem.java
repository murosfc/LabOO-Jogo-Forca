package br.edu.iff;

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

import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) {
		PalavraAppService.createSoleInstance(temaRepository, palavraRepository, palavraFactory);
		RodadaAppService.createSoleInstance(app.getRodadaFactory(), rodadaRepository, jogadorRepostory);

		palavraAppService = PalavraAppService.getSoleInstance();
		rodadaAppService = RodadaAppService.getSoleInstance();

		boolean jogarNovamente = Boolean.TRUE;
		boolean primeiraRodada = Boolean.TRUE;

		while (jogarNovamente) {
			iniciarPalavrasETemas();
			if (primeiraRodada) {
				if (telaInicial()) {
					novaRodada();
				} else {
					telaFimDeJogo();
					jogarNovamente = Boolean.FALSE;
				}
				jogarNovamente = jogarNovamente();
				primeiraRodada = Boolean.FALSE;
			} else {
				novaRodada();
				jogarNovamente = jogarNovamente();
			}
		}
		telaFimDeJogo();
	}

	static void novaRodada() {
		try {
			Rodada rodada = rodadaAppService.novaRodada(jogador);
			telaJogo(jogador, rodada);
		} catch (JogadorNaoEncontradoException e) {
			System.err.println(e.getMessage());
		}
	}

	static boolean jogarNovamente() {
		int opcao = 2;
		while (opcao != 0 && opcao != 1) {
			opcao = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Menu", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Jogar novamente", "Sair" }, "Jogar novamente");
		}		
		return opcao == 0;
	}

	static void telaJogo(Jogador jogador, Rodada rodada) {		
		Painel painelJogo = new Painel();
		painelJogo.setNomeJogadorAtual(jogador.getNome());
		painelJogo.setTemaAtual(rodada.getTema().getNome());
		painelJogo.setQuantidadeDePalavras(rodada.getNumPalavras());
		painelJogo.setTotalErros(rodada.getQuantidadeErros());
		painelJogo.setMaxErros(Rodada.getMaxErros());
		Conteiner conteinerJogo = new Conteiner(painelJogo);
		conteinerJogo.repaint();
		while (!rodada.encerrou()) {
			
		}
	}	

	static void encerrou(Rodada rodada) {
		if (rodada.descobriu()) {
			imprimeResultadoRodada(rodada, true);
		} else if (rodada.arriscou()) {
			imprimeResultadoRodada(rodada, false);
		} else if (rodada.getQuantidadeTentativasRestantes() == 0) {
			imprimeResultadoRodada(rodada, false);
		}
	}

	static void imprimeResultadoRodada(Rodada rodada, boolean sucesso) {
		if (sucesso) {
			System.out.println("\nParabéns, você descobriu todas as palavras!");
		} else {
			System.out.println("\nPoxa. Que pena! Você não acertou todas as palavras");
			System.out.println("\nA(s) palavra(s) correta(s) era(m): ");
		}
		rodada.exibirPalavras(null);
		System.out.println("\nSua pontuação nessa rodada foi: " + rodada.calcularPontos());
		System.out.println("Sua pontuação total é: " + jogador.getPontuacao());
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
			}else if (nomeJogador.isEmpty()) {
				nomeJogador = "Jogador";
			}			
			jogador = app.getJogadorFactory().getJogador(nomeJogador);
		}
		return opcao == 0;
	}

	static void telaFimDeJogo() {
		String outputs = "\n\nObrigado por ter jogado o jogo da forca\n";
		outputs += "Implementado por Bruno Alves, Felipe Muros e Rafael Panisset\n";
		outputs += "Desenhado e supervisionado pelo professor Mark Douglas\n";
		outputs += "Curso Bacharel em Sistemas de Informação\n";
		outputs += "Instituto Federal Fluminense, campus Centro - Campos dos Goytacazes - RJ\n";
		outputs += "Abril de 2023\n";

		JOptionPane.showMessageDialog(null, outputs, "Créditos", JOptionPane.INFORMATION_MESSAGE);
	}
}
