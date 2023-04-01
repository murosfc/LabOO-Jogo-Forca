package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rodada extends ObjetoDominioImpl {

    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;

    private static BonecoFactory bonecoFactory;

    private List<Palavra> palavras;
    private Jogador jogador;
    private List<Item> itens;
    private List<Letra> certas;
    private List<Letra> erradas;

    public static int getMaxPalavras() {
        return maxPalavras;
    }

    public static void setMaxPalavras(int maxPalavras) {
        maxPalavras = maxPalavras;
    }

    public static int getMaxErros() {
        return maxErros;
    }

    public static void setMaxErros(int maxErros) {
        maxErros = maxErros;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosQuandoDescobreTodasAsPalavras;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontosQuandoDescobreTodasAsPalavras) {
        pontosQuandoDescobreTodasAsPalavras = pontosQuandoDescobreTodasAsPalavras;
    }

    public static int getPontosPorLetraEncobertar() {
        return pontosPorLetraEncoberta;
    }

    public static void setPontosPorLetraEncobertar(int pontosPorLetraEncoberta) {
        Rodada.pontosPorLetraEncoberta = pontosPorLetraEncoberta;
    }

    public static BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory) {
        bonecoFactory = bonecoFactory;
    }

    private Rodada(Long id, List<Palavra> palavras, Jogador jogador) {
        super(id);
        this.palavras = palavras;
        this.jogador = jogador;
        this.erradas = new ArrayList<>();
    }

    private Rodada(Long id, Jogador jogador, List<Item> itens, List<Letra> erradas) {
        super(id);
        this.jogador = jogador;
        this.itens = itens;
        this.erradas = erradas;
    }

    public static Rodada criar(Long id, List<Palavra> palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    public static Rodada reconstruir(Long id, Jogador jogador, List<Item> itens, List<Letra> erradas) {
        return new Rodada(id, jogador, itens, erradas);
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public Tema getTema() {
        return this.itens.get(0).getPalavra().getTema();
    }

    public List<Palavra> getPalavras() {
        return this.palavras;
    }

    public int getNumPalavras() {
        return this.palavras.size();
    }

    public void tentar(char codigo) {
        if (!this.encerrou()) {
            boolean errada = true;
            for (Item item : this.itens) {
                List<Integer> posicoesLetras = item.tentar(codigo);
                if (!posicoesLetras.isEmpty()) {
                    errada = false;
                }
            }
            if (errada) {
                this.erradas.add(Palavra.getLetraFactory().criar(codigo));
            }
        }
        if (this.encerrou())
            this.jogador.setPontuacao(this.calcularPontos());
    }

    public void arriscar(List<Palavra> palavras) {
        if (! this.encerrou()){
            if (palavras.size() != this.itens.size()){
                System.err.println("A quantidade de palpites difere da quantidade de palavras da rodada");
            }
            int i = 0;
            for (Item item : this.itens){
                item.arriscar(palavras.get(i).toString());
                i++;
            }
            this.jogador.setPontuacao(this.calcularPontos());
        }
    }

    public void exibirItens(Object contexto) {
        for (Item item : this.itens) {
            item.exibir(contexto);
        }
    }

    public void exibirBoneco(Object contexto) {
        bonecoFactory.getBoneco().exibir(contexto, this.getQuantidadeErros());
    }

    public void exibirPalavras(Object contexto) {
         for (Item item : this.itens) {
            item.getPalavra().exibir(contexto);
        }
    }

    public void exibirLetrasErradas(Object contexto) {
        for (Letra l: this.erradas){
            l.exibir(contexto);
        }
    }

    public List<Letra> getTentativas() {
        Set<Letra> setLetras = new HashSet<>();
        setLetras.addAll(this.getCertas());
        setLetras.addAll(this.getErradas());
        List<Letra> listaMesclada = new ArrayList<>(setLetras);
        return listaMesclada; 
    }

    public List<Letra> getCertas() {
        Set<Letra> setLetras = new HashSet<>();
        for (Item item : this.itens) {
            setLetras.addAll(item.getPalavra().getLetras());
        }
        List<Letra> listaMesclada = new ArrayList<>(setLetras);
        return listaMesclada;        
    }

    public List<Letra> getErradas() {        
        return this.erradas;
    }

    public int calcularPontos() {
        int totalPontos = 0;
        for (Item item : this.itens) {
            if(item.descobriu()){
                totalPontos += pontosQuandoDescobreTodasAsPalavras + item.calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
            }
        }
        return totalPontos;
    }

    public boolean encerrou() {
        return this.arriscou() || this.descobriu() || this.getQuantidadeTentativasRestantes() == 0;
    }

    public boolean descobriu() {
        boolean descobriu = true;
        for (Item item : this.itens) {
            if (!item.descobriu()) {
                descobriu = false;
            }
        }
        return descobriu;
    }

    public boolean arriscou() {
        boolean arriscou = false;
        for (Item item : this.itens) {
            if (item.arriscou()) {
                arriscou = true;
            }
        }
        return arriscou;
    }

    public int getQuantidadeTentativasRestantes() {
        return maxErros - this.getQuantidadeErros();
    }

    public int getQuantidadeErros() {
        return this.erradas.size();
    }

    public int getQuantidadeAcertos() {
        return this.getCertas().size();
    }

    public int getQuantidadeTentativas() {
        return this.getQuantidadeAcertos() + this.getQuantidadeErros();
    }

}
