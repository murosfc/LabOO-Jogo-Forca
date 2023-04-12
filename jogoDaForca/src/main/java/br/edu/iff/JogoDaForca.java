package br.edu.iff;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JogoDaForca {

    private final static List<String> palavrasTemaComida = Arrays.asList("Pera", "Pizza", "Arroz", "Lasanha", "Sushi");
    private final static List<String> palavrasTemaPais = Arrays.asList("Brasil", "Portugal", "Austria", "China", "Argentina", "Russia", "Gana");
    private final static List<String> palavrasTemaEsporte = Arrays.asList("Futebol", "Basquete", "Volei", "Tenis", "Natacao", "Ginastica", "Atletismo", "Ciclismo", "Boxe", "Surf");
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

    private static final Scanner scanner = new Scanner(System.in);

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
        String opcao = "2";
        while (!opcao.equals("0") && !opcao.equals("1")) {
            System.out.println("\nEscolha o que deseja fazer?");
            System.out.println("(0) Encerrar ");
            System.out.println("(1) Jogar novamente ");
            if (scanner.hasNextInt()) {
                opcao = scanner.next();
            }
        }
        return opcao.equals("1");
    }

    static void telaFimDeJogo() {
        System.out.println("\n\nObrigado por ter jogado o jogo da forca");
        System.out.println("Implementado por Bruno, Felipe Muros e Rafael Panisset");
        System.out.println("Desenhado e supervisionado pelo professor Mark Douglas");
        System.out.println("Curso Bacharel em Sistemas de Informação");
        System.out.println("Instituto Federal Fluminense, campus Centro - Campos dos Goytacazes - RJ");
        System.out.println("Abril de 2023\n\n");
    }

    static void telaJogo(Jogador jogador, Rodada rodada) {
        System.out.println("\nOlá " + jogador.getNome());
        System.out.println("Considere letras sem acento, e sem caracteres exclusivos do português do Brasil");
        while (!rodada.encerrou()) {
            System.out.println("\nO tema da rodada é: " + rodada.getTema().getNome());
            System.out.println("Essa rodada é composta de " + rodada.getNumPalavras() + " palavra(s):");
            rodada.exibirItens(null);
            System.out.println("\n");

            int i = 0;
            if (!rodada.getTentativas().isEmpty()) {
                System.out.print("Letras erradas: ");
                rodada.exibirLetrasErradas(null);
            }

            System.out.println("\nErros: " + rodada.getQuantidadeErros() + " de " + Rodada.getMaxErros());
            if (rodada.getQuantidadeErros() > 0) {
                System.out.println("\nForca: ");
                rodada.exibirBoneco(null);
            }

            int opcao = getAcaoJogador();

            switch (opcao) {
                case 1:
                    int erros = rodada.getQuantidadeErros();
                    char codigo = "#".charAt(0);
                    while (!Character.isLetter(codigo)) { //garante atendimento do contrato
                        System.out.print("Digite a letra do seu palpite: ");
                        codigo = scanner.next().charAt(0);
                        if (codigo >= 'A' && codigo <= 'Z' || codigo >= 'À' && codigo <= 'Ý') { //Se a letra é maiúscula, converte para minuscula somando 32
                            codigo = (char) (codigo + 32);
                        }
                    }
                    rodada.tentar(codigo);
                    if (erros == rodada.getQuantidadeErros()) {
                        System.out.print("\nSeu palpite da letra '" + codigo + "' foi certeiro!\n");
                    } else {
                        System.out.print("\nSeu palpite da letra '" + codigo + "' foi incorreto =/\n");
                    }
                    break;
                case 2:
                    List<Palavra> palavras = new ArrayList<>();
                    for (i = 1; i <= rodada.getNumPalavras(); i++) {
                        System.out.println("Digite a palavra numero " + i);
                        String palavra = scanner.next();
                        palavras.add(palavraFactory.getPalavra(palavra, rodada.getTema()));
                    }
                    rodada.arriscar(palavras);
                    break;
                default:
                    System.err.println("Opção inválida");
            }
            encerrou(rodada);
        }
    }

    private static int getAcaoJogador() {
        String opcao = "0";
        while (!opcao.equals("1") && !opcao.equals("2")) {
            System.out.println("\nEscolha sua próxima ação!");
            System.out.println("(1) Digitar uma letra");
            System.out.println("(2) Já sabe todas as palavras? Arrisque!");
            if (scanner.hasNextInt()) {
                opcao = scanner.next();
            } else {
                opcao = "0";
                scanner.next();
            }
        }
        return Integer.parseInt(opcao);
    }

    static void encerrou(Rodada rodada) {
        if (rodada.descobriu()) {
            System.out.println("\nParabéns, você descobriu todas as palavras!");
            rodada.exibirPalavras(null);
            mostrarPontuacao(rodada);
        } else if (rodada.arriscou()) {
            System.out.println("\nPoxa. Que pena! Você não acertou todas as palavras");
            System.out.println("\nA(s) palavra(s) corretas era(m): ");
            rodada.exibirPalavras(null);
            mostrarPontuacao(rodada);
        } else if (rodada.getQuantidadeTentativasRestantes() == 0) {
            System.out.println("\nPoxa. Que pena! Suas tentativas acabaram");
            System.out.println("\nA(s) palavra(s) corretas era(m): ");
            rodada.exibirPalavras(null);
            mostrarPontuacao(rodada);
        }
    }

    static void mostrarPontuacao(Rodada rodada) {
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
        String opcao = "2";
        while (!opcao.equals("0") && !opcao.equals("1")) {
            System.out.println("\n-------------------------------");
            System.out.println("|        JOGO DA FORCA        |");
            System.out.println("-------------------------------");
            System.out.println("\nESCOLHA UMA DAS OPÇÕES ABAIXO:\n");
            System.out.println("(1) Novo Jogo ");
            System.out.println("(0) Sair ");

            if (scanner.hasNextInt()) {
                opcao = scanner.next();
            }
        }
        if (opcao.equals("1")) {
            System.out.println("Digite seu nome: ");
            String nome = scanner.next();
            jogador = app.getJogadorFactory().getJogador(nome);
        }
        return opcao.equals("1");
    }
}
