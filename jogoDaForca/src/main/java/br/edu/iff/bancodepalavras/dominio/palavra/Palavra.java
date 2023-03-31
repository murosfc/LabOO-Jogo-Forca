package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import java.util.ArrayList;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {

    private LetraFactory letraFactory;
    private List<Letra> letras;
    private Tema tema;

    public Palavra(long id, String palavra, Tema tema) {
        super(id);
        this.letraFactory = null;
        this.letras = new ArrayList<>();
        this.tema = tema;

        for (int i = 0; i < palavra.length(); i++) {
            this.letras.set(i, letraFactory.criar(palavra.charAt(i)));
        }
    }

    public static Palavra criar(long id, String palavra, Tema tema, LetraFactory letraFactory) {
        Palavra p = new Palavra(id, palavra, tema);
        p.setLetraFactory(letraFactory);
        return p;
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema, LetraFactory letraFactory) {
        Palavra p = new Palavra(id, palavra, tema);
        p.setLetraFactory(letraFactory);
        return p;
    }

    public void setLetraFactory(LetraFactory factory) {
        this.letraFactory = factory;
        for (int i = 0; i < letras.size(); i++) {
            this.letras.get(i).setLetraFactory(factory);
        }
    }

    public LetraFactory getLetraFactory() {
        return this.letraFactory;
    }

    public List<Letra> getLetras() {
        return this.letras;
    }

    public Letra getLetra(int posicao) {
        return this.letras.get(posicao);
    }

    public void exibir(Object contexto) {
        // Implementação do método exibir
    }

    public void exibir(Object contexto, boolean[] posicoes) {
        // Implementação do método exibir com posicoes
    }

    public int[] tentar(char codigo) {
        // Implementação do método tentar
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean comparar(String palavra) {
        // Implementação do método comparar
    }

    public int getTamanho() {
        return this.letras.size();
    }

    @Override
    public String toString() {
        // Implementação do método toString
    }
}
