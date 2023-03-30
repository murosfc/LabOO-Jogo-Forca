package br.edu.iff.jogoforca.emmemoria;

public class MemoriaRepositoryFactory {
    private static MemoriaRepositoryFactory soleInstance = null;

    private MemoriaRepositoryFactory() {
    }

    public static MemoriaRepositoryFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new MemoriaRepositoryFactory();
        return soleInstance;
    }
    
    
}
