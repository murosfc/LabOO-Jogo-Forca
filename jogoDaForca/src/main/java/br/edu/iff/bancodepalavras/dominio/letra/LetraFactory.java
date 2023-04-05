package br.edu.iff.bancodepalavras.dominio.letra;

public interface LetraFactory {
    public Letra getLetra(char codigo);
    public Letra getLetraEncoberta();

}
