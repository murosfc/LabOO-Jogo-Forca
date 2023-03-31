package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;

public class LetraTextoFactory extends LetraFactoryImpl {

    private static LetraTextoFactory soleInstance;

    public static synchronized LetraTextoFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new LetraTextoFactory();

        return soleInstance;
    }
    @Override
    protected Letra criarLetra(char codigo) {
        return null;
    }
}
