package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;
import java.util.HashMap;


public class LetraTextoFactory extends LetraFactoryImpl {

    private static LetraTextoFactory soleInstance;

    HashMap<Integer,Character> pool = new HashMap<Integer,Character>();

    pool.

    public static synchronized LetraTextoFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new LetraTextoFactory();

        return soleInstance;
    }
    @Override
    protected Letra criarLetra(char codigo) {
        int i = codigo;
        Letra result = this.pool.get();
        if (result == null){
            result = this.criarLetra(codigo);
            this.pool.set( i, result);
        }
        return result;
    }

    @Override
    public Letra criar(char charAt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
