package br.edu.iff.jogoforca.dominio.jogador.embdr;

public class BDRRodadaRepository /*implements RodadaRepository*/{
    private static BDRRodadaRepository soleInstance = null;

    private BDRRodadaRepository() {
    }

    public static BDRRodadaRepository getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new BDRRodadaRepository();
        return soleInstance;
    }
    
    
}
