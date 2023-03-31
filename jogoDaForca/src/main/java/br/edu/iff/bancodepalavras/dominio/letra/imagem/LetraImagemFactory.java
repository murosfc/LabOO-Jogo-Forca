package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraImagemFactory extends LetraFactoryImpl {

    private static LetraImagemFactory soleInstance;

    public static LetraImagemFactory getSoleInstance() {
        return soleInstance;
    }


    @Override
    protected Letra criarLetra(char codigo) {
        return null;
    }

    @Override
    public Letra criar(char charAt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
