package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.ArrayList;
import java.util.List;

public abstract class LetraFactoryImpl implements LetraFactory {
    private List<Letra> pool;
    private Letra encoberta;
    
    protected LetraFactoryImpl(){ 
        pool = new ArrayList<>(26);
    } 
    
    @Override
    public final Letra getLetra(char codigo){
        return criarLetra(codigo);
    }    

    @Override
    public final Letra getLetraEncoberta() {
        return encoberta;
    }

    protected abstract Letra criarLetra(char codigo);
}
