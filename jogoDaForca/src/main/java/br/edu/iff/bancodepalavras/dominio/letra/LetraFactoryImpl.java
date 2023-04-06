package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.HashMap;

public abstract class LetraFactoryImpl implements LetraFactory {
    private final static int POOL_SIZE = 26;
    private HashMap<Integer,Letra> pool;
    private Letra encoberta;
    
    protected LetraFactoryImpl(){ 
        pool = new HashMap<Integer,Letra>(POOL_SIZE);
    } 
    
    @Override
    public final Letra getLetra(char codigo){
        if (!Character.isLetter(codigo))
            throw new IllegalArgumentException("Código de caractere inválido");
        int i = codigo;
        Letra result = this.pool.get(i);
        if (result == null){
            result = this.criarLetra(codigo);
            this.pool.put( i, result);
        }
        return result;
    }    

    @Override
    public final Letra getLetraEncoberta() {
        return encoberta;
    }

    protected abstract Letra criarLetra(char codigo);
}
