package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import java.util.List;

public class Rodada extends ObjetoDominioImpl {

    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;

    private static BonecoFactory bonecoFactory;

    private List<Palavra> palavras;
    private Jogador jogador;
    private List<Item> itens;
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
        to do {
            
        }
    }

    public void arriscar(List<Palavra> palavras) {
        to do {
            
        }
    }

    public void exibirItens(Object contexto) {
    }

    public void exibirBoneco(Object contexto) {
        bonecoFactory.getBoneco().exibir(contexto, this.getQuantidadeErros());
    }

    public void exibirPalavra(Object contexto) {
    }

    public void letrasErradas(Object contexto) {
    }

    public List<Letra> getTentativas() {

    }

    public List<Letra> getCertas() {

    }

    public List<Letra> getErradas() {

    }

    public int calcularPontos() {

    }

    public boolean encerrou() {

    }

    public boolean descobriu() {

    }

    public boolean arriscou() {

    }
    
    public int getQuantidadeTentativasRestantes(){
        
    }
    
    public int getQuantidadeErros(){
        
    }
    
     public int getQuantidadeAcertos(){
        
    }
     
     public int getQuantidadeTentativas(){
        
    }

}
