package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

public class MemoriaRodadaRepository {
    private static MemoriaRodadaRepository soleInstance = null;

    private MemoriaRodadaRepository() {
    }  

    public static MemoriaRodadaRepository getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new MemoriaRodadaRepository();
        return soleInstance;
    }   
}
