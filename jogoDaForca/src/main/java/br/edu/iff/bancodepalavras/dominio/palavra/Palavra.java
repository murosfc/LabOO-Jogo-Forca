package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import java.util.ArrayList;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {

    private static LetraFactory letraFactory;
    private List<Letra> letras;
    private Tema pertence;

    public Palavra(long id, String palavra, Tema tema) {
        super(id);          
        this.letras = new ArrayList<>();
        this.pertence = tema;
        for (int i = 0; i < palavra.length(); i++) {
            this.letras.set(i, letraFactory.criar(palavra.charAt(i)));
        }
    }

    public static Palavra criar(Long id, String palavra, Tema tema) {
        if(getLetraFactory() == null){
            throw new RuntimeException("Letra factory não configurada");
        }
        return new Palavra (id, palavra, tema);
    }

    public static Palavra reconstituir(Long id, String palavra, Tema tema) {
        if(getLetraFactory() == null){
            throw new RuntimeException("Letra factory não configurada");
        }
        return new Palavra (id, palavra, tema);
    }

    public static void setLetraFactory(LetraFactory factory) {
        letraFactory = factory;        
    }

    public static LetraFactory getLetraFactory() {
        return letraFactory;
    }

    public List<Letra> getLetras() {
        return this.letras;
    }

    public Letra getLetra(int posicao) {
        return this.letras.get(posicao);
    }

    public void exibir(Object contexto) {
        for(Letra letra: this.letras){
            letra.exibir(contexto);
        }
    }

    public void exibir(Object contexto, List<Boolean> posicoes) {
        int i=0;
        for(boolean bol : posicoes){
            if(bol){
                this.letras.get(i).exibir(contexto);
            }
            i++;
        }
    }

    public List<Integer> tentar(char codigo) {
        List<Integer> posicoesCertas = new ArrayList<>();
        int i = 0;
        for (Letra letra : this.letras){
            if (letra.equals(letraFactory.criar(codigo)))
                posicoesCertas.add(i);
            i++;
        }
        return posicoesCertas;
    }

    public Tema getTema() {
        return this.pertence;
    }

    public boolean comparar(String palavra) {
        if (palavra.length() != this.getTamanho())
            return false;
        for(int i=0 ; i < this.getTamanho(); i++){
            if (palavra.charAt(i) != this.letras.get(i).getCodigo())
                return false;
        }
        return true;
    }

    public int getTamanho() {
        return this.letras.size();
    }

    @Override
    public String toString() {
        String palavra = "";
        for (Letra letra: letras)
            palavra += letra.getCodigo();        
        return palavra;
    }
}
