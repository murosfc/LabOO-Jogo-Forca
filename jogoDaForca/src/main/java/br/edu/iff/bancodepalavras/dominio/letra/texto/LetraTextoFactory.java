package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraTextoFactory extends LetraFactoryImpl {

    private static LetraTextoFactory soleInstance;

    public static LetraTextoFactory getSoleInstance() {
        return soleInstance == null ? soleInstance = new LetraTextoFactory() : soleInstance;
    }
    
    private LetraTextoFactory(){        
    }
    
    @Override 
    protected Letra criarLetra(char codigo) {
        return new LetraTexto(codigo);
    }
}
